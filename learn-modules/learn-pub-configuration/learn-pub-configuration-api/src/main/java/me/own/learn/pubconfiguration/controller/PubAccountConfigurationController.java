package me.own.learn.pubconfiguration.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.own.learn.pubconfiguration.dto.PubConfigurationDto;
import me.own.learn.pubconfiguration.service.PubConfigurationService;
import me.own.learn.pubconfiguration.vo.PubConfigurationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yexudong
 * @date 2019/5/31 11:10
 */
@RestController
@RequestMapping(value = "/api/learn/v1/pub-configurations")
@Api(tags = "公众号配置功能模块", description = "公众号配置模块")
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
}
