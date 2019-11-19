package me.own.learn.authorization.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.own.learn.authorization.service.TokenService;
import me.own.learn.authorization.utils.CookieUtils;
import me.own.learn.authorization.vo.CustomerTokenVo;
import me.own.learn.authorization.vo.TokenVo;
import me.own.learn.customer.dto.CustomerDto;
import me.own.learn.customer.exception.CustomerNotFoundException;
import me.own.learn.customer.service.CustomerService;
import me.own.learn.customer.vo.CustomerVo;
import me.own.learn.pubaccount.service.PubAccountAuthorizeService;
import me.own.learn.pubaccount.service.PubAccountUserService;
import me.own.learn.pubaccount.service.model.PubAccountAuthToken;
import me.own.learn.pubaccount.service.model.UserInfoBo;
import me.own.learn.pubconfiguration.service.PubConfigurationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static me.own.commons.wechat.pubaccount.media.impl.MediaServiceImpl.LOGGER;

@RestController
@Api(value = "公众号授权接口", description = "处理非关注用户通过授权登录,获取 User info, 并创建新用户")
public class PubAccountAuthController {

    @Autowired
    private PubAccountUserService pubAccountUserService;

    @Autowired
    private PubAccountAuthorizeService pubAccountAuthorizeService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PubConfigurationService pubConfigurationService;

    @Autowired
    private TokenService tokenService;


    /**
     * 处理非关注用户通过授权登录,获取 User info, 并创建新用户
     * <p>
     * scope=snsapi_userinfo
     * <p>
     * (https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=此地址&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect)回调，用户同时登录了网站
     *
     * @param request
     * @param type    callback handle type, base | info, base only get user's openid, info get user's informations
     * @param code
     * @param state
     * @return
     */
    @ApiOperation("处理非关注用户通过授权登录,获取 User info, 并创建新用户")
    @RequestMapping(value = "/api/pub-accounts/{appId}/code-auth/{type}/callback", method = RequestMethod.GET)
    public ResponseEntity handleWxUserInfoCallBack(
            HttpServletRequest request,
            HttpServletResponse response,
            @PathVariable(value = "appId") String appId,
            @PathVariable("type") String type,
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "state", required = false) String state
    ) {
        if (StringUtils.isEmpty(code)) {
            return new ResponseEntity("use rejected authorization", HttpStatus.UNAUTHORIZED);
        }
        LOGGER.debug("pub account code auth callback entry: " + code + " state: " + state);
        // get token by code
        PubAccountAuthToken authToken = pubAccountAuthorizeService.getAccessTokenByAuthCode(appId, code);
        if (authToken == null) {
            return new ResponseEntity("get token failed", HttpStatus.UNAUTHORIZED);
        }
        Long tenantId = pubConfigurationService.getByAppId(appId).getId();
        CustomerVo customerVo = customerService.getByOpenId(authToken.getOpenid(), tenantId);
        if (customerVo == null) {
            // Get user's info and create a new customer
            if (type.equals("info")) {
                // get user info by token
                UserInfoBo userInfoBo = pubAccountUserService.getUserInfoByToken(authToken.getAccessToken(), authToken.getOpenid());
                customerVo = customerService.createFromPubAccount(adapt(userInfoBo));
                LOGGER.info("new customer {} (openid {}, nickname: {}) created by subscribe pub account.", customerVo.getId(), userInfoBo.getOpenid(), userInfoBo.getNickname());
            } else {
                // Not our customer
                return new ResponseEntity(HttpStatus.UNAUTHORIZED);
            }
        }

        // customer login
        setCustomerCookie(request, response, customerVo.getId());

        LOGGER.debug("auth success for customer " + customerVo.getId() + " return url: " + state);
        HttpHeaders headers = new HttpHeaders();
        if (StringUtils.isNotEmpty(state)) {
            if (state.indexOf("#wechat_redirect") != -1) {
                state = state.substring(0, state.indexOf("#wechat_redirect"));
            }
            headers.add(HttpHeaders.LOCATION, state);
        }
        return new ResponseEntity(headers, HttpStatus.FOUND);
    }

    @ApiOperation("公众号重定向页面接口")
    @RequestMapping(value = "/api/pub-accounts/{appId}/auth", method = RequestMethod.GET)
    public ResponseEntity handlePubAccountOpenIdAccess(
            HttpServletRequest request,
            HttpServletResponse response,
            @PathVariable(value = "appId") String appId,
            @RequestParam(value = "openid", required = true) String openid,
            @RequestParam(value = "redirect_url", required = false) String redirect_url) {

        if (!isFromPubAccountAccess(request)) {
            return new ResponseEntity("请在微信客户端访问", HttpStatus.UNAUTHORIZED);
        }

        if (StringUtils.isEmpty(openid)) {
            return new ResponseEntity("openid can not be empty", HttpStatus.UNAUTHORIZED);
        }

        LOGGER.debug("pub account openid auth entry: " + openid + " redirect_url: " + redirect_url);

        Long tenantId = pubConfigurationService.getByAppId(appId).getId();
        CustomerVo customerVo = customerService.getByOpenId(openid, tenantId);
        if (customerVo == null) {
            throw new CustomerNotFoundException();
        }
        long customerId = customerVo.getId();
        setCustomerCookie(request, response, customerId);

        LOGGER.debug("openid auth success for customer " + customerId + " return url: " + redirect_url);

        if (StringUtils.isEmpty(redirect_url)) {
            return new ResponseEntity(HttpStatus.OK);
        }

        HttpHeaders headers = new HttpHeaders();
        if (StringUtils.isNotEmpty(redirect_url)) {
            headers.add(HttpHeaders.LOCATION, redirect_url);
        }
        return new ResponseEntity(headers, HttpStatus.FOUND);
    }


    private boolean isFromPubAccountAccess(HttpServletRequest request) {
        String ua = request.getHeader("user-agent").toLowerCase();
        return ua.indexOf("micromessenger") != -1;
    }


    private void setCustomerCookie(HttpServletRequest request, HttpServletResponse response, long customerId) {
        String cookie_token = CookieUtils.getCustomerTokenInCookie(request);
        boolean needRefreshToken = true;
        if (StringUtils.isNotEmpty(cookie_token)) {
            if (!tokenService.isExpired(cookie_token)) {
                TokenVo token = tokenService.getByTokenValue(cookie_token);
                needRefreshToken = token.getCustomerId() != customerId;
                LOGGER.debug("check request existing cookie {} and token customer {} for auth customer {} result:  needRefreshToken {}",
                        cookie_token, token.getCustomerId(), customerId, needRefreshToken);
            }
        }
        if (needRefreshToken) {
            TokenVo tokenVo = tokenService.createFromCustomer(customerId);
            CookieUtils.setCustomerTokenInCookie(response, tokenVo.getValue());
            LOGGER.debug("set cookie to response {} for customer {}", tokenVo.getValue(), customerId);
        }
    }


    private CustomerDto adapt(UserInfoBo userInfoModel) {
        CustomerDto createDto = new CustomerDto();
        createDto.setOpenid(userInfoModel.getOpenid());
        createDto.setNickName(userInfoModel.getNickname());
        createDto.setSubscribed(userInfoModel.getSubscribe());
        createDto.setSex(userInfoModel.getSex());
        createDto.setHeadImage(userInfoModel.getHeadimgurl());
        createDto.setProvince(userInfoModel.getProvince());
        createDto.setCity(userInfoModel.getCity());
        return createDto;
    }

}
