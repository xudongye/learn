package me.own.learn.agent.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.own.commons.base.dao.PageQueryResult;
import me.own.commons.base.utils.request.RequestUtils;
import me.own.learn.agent.service.AgentQueryCondition;
import me.own.learn.agent.service.AgentService;
import me.own.learn.agent.vo.AgentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yexudong
 * @date 2019/7/25 10:50
 */
@RestController
@RequestMapping(value = "/api/learn/v1/agents")
@Api(value = "分销商模块", description = "管理分销商用户")
public class AgentController {

    @Autowired
    private AgentService agentService;


    @ApiOperation("分页分销用户表")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity page(HttpServletRequest request,
                               @RequestParam(required = false) AgentQueryCondition condition,
                               @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                               @RequestParam(value = "pageSize", defaultValue = "10") int pageSize
    ) {
        if (condition == null) {
            condition = RequestUtils.buildQueryFilter(request, AgentQueryCondition.class);
        }
        Map<String, Object> response = new HashMap<>();
        PageQueryResult<AgentVo> requestVoPageQueryResult = agentService.page(pageNum, pageSize, condition);
        response.put("code", 200);
        response.put("data", requestVoPageQueryResult);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
