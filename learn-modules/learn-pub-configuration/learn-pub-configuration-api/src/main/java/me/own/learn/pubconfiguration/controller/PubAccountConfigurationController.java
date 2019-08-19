package me.own.learn.pubconfiguration.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.own.commons.base.dao.PageQueryResult;
import me.own.commons.base.utils.request.RequestUtils;
import me.own.learn.pubconfiguration.dto.PubConfigurationDto;
import me.own.learn.pubconfiguration.service.PubConfigurationQueryCondition;
import me.own.learn.pubconfiguration.service.PubConfigurationService;
import me.own.learn.pubconfiguration.vo.PubConfigurationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yexudong
 * @date 2019/5/31 11:10
 */
@RestController
@RequestMapping(value = "/api/v1/pub-configurations")
@Api(value = "公众号配置功能模块", description = "公众号配置模块")
public class PubAccountConfigurationController {

    @Autowired
    private PubConfigurationService pubConfigurationService;


    @ApiOperation("新增公众号及配置")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createPubAccountConfiguration(HttpServletRequest request,
                                                        @RequestBody PubConfigurationDto pubConfigurationDto) {
        Map<String, Object> response = new HashMap<>();
        PubConfigurationVo pubConfigurationVo = pubConfigurationService.create(pubConfigurationDto);
        response.put("code", 200);
        response.put("data", pubConfigurationVo);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @ApiOperation("修改公众号及配置")
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity updatePubAccountConfiguration(HttpServletRequest request,
                                                        @RequestBody PubConfigurationDto pubConfigurationDto) {
        Map<String, Object> response = new HashMap<>();
        PubConfigurationVo pubConfigurationVo = pubConfigurationService.update(pubConfigurationDto);
        response.put("code", 200);
        response.put("data", pubConfigurationVo);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @ApiOperation("分页获取")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity page(HttpServletRequest request,
                               @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                               @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                               @RequestParam(required = false) PubConfigurationQueryCondition condition) {
        if (condition == null) {
            condition = RequestUtils.buildQueryFilter(request, PubConfigurationQueryCondition.class);
        }
        Map<String, Object> response = new HashMap<>();
        PageQueryResult<PubConfigurationVo> result = pubConfigurationService.page(pageNum, pageSize, condition);
        response.put("code", 200);
        response.put("data", result);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @ApiOperation("公众号配置开关")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity onActive(HttpServletRequest request,
                                   @PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        boolean result = pubConfigurationService.onActive(id);

        response.put("code", 200);
        response.put("data", result);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }
}
