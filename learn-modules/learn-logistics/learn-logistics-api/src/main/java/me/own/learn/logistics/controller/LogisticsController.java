package me.own.learn.logistics.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import me.own.learn.authorization.service.AdminAuthenticationRequired;
import me.own.learn.authorization.service.model.AdminAccessToken;
import me.own.learn.logistics.bo.CompanyBo;
import me.own.learn.logistics.bo.LogisticsResponseBo;
import me.own.learn.logistics.service.LogisticsService;
import me.own.learn.logistics.utils.JuheUtils;
import me.own.learn.logistics.vo.LogisticsCompanyVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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

    @ApiOperation("获取管理员基本信息接口")
    @AdminAuthenticationRequired
    @ApiImplicitParam(name = "a_id", value = "调试模式", paramType = "query", dataType = "String", defaultValue = "1")
    @RequestMapping(method = RequestMethod.GET)
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
}
