package me.own.learn.logistics.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import me.own.commons.base.utils.request.RequestUtils;
import me.own.learn.authorization.service.AdminAuthenticationRequired;
import me.own.learn.authorization.service.model.AdminAccessToken;
import me.own.learn.logistics.bo.CompanyBo;
import me.own.learn.logistics.bo.LogisticsRequestBo;
import me.own.learn.logistics.bo.LogisticsResponseBo;
import me.own.learn.logistics.service.LogisticsService;
import me.own.learn.logistics.utils.JuheUtils;
import me.own.learn.logistics.vo.LogisticsCompanyVo;
import me.own.learn.logistics.vo.LogisticsListVo;
import me.own.learn.logistics.vo.LogisticsVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yexudong
 * @date 2019/9/8 15:19
 */
@RestController
@RequestMapping(value = "/api/v1/logistics")
@Api(value = "物流管理模块", description = "物流管理模块")
public class LogisticsController {

    @Autowired
    private LogisticsService logisticsService;

    @ApiOperation("获取物流公司信息接口")
    @AdminAuthenticationRequired
    @ApiImplicitParam(name = "a_id", value = "调试模式", paramType = "query", dataType = "String", defaultValue = "1")
    @RequestMapping(value = "/company", method = RequestMethod.GET)
    public ResponseEntity onGetSupportLogistics(HttpServletRequest request, AdminAccessToken aat) {

        Map<String, Object> responseBody = new HashMap<>();

        List<LogisticsCompanyVo> companyVos = logisticsService.getSupported();
        if (CollectionUtils.isNotEmpty(companyVos)) {
            responseBody.put("logisticsCompany", companyVos);
        } else {
            LogisticsResponseBo<CompanyBo> companyBoLogisticsResponseBo =
                    JuheUtils.onCompanySupport();
            responseBody.put("logisticsCompany", companyBoLogisticsResponseBo.getResult());
        }
        return new ResponseEntity(responseBody, HttpStatus.OK);
    }

    @ApiOperation("获取用户购物产品物流信息接口")
    @AdminAuthenticationRequired
    @ApiImplicitParam(name = "a_id", value = "调试模式", paramType = "query", dataType = "String", defaultValue = "1")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ResponseEntity onGetLogisticsInfo(HttpServletRequest request, AdminAccessToken aat,
                                             @RequestParam(required = false) LogisticsRequestBo requestBo) {

        if (requestBo == null) {
            requestBo = RequestUtils.buildQueryFilter(request, LogisticsRequestBo.class);
        }
        Map<String, Object> responseBody = new HashMap<>();
        LogisticsResponseBo<LogisticsVo> responseBo =
                JuheUtils.onLogistics(requestBo.getCom(), requestBo.getNo(), requestBo.getSenderPhone(), requestBo.getReceiverPhone());
        responseBody.put("logistics-info", responseBo);
        return new ResponseEntity(responseBody, HttpStatus.OK);
    }
}
