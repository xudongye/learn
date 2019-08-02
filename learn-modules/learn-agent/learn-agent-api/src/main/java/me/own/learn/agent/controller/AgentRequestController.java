package me.own.learn.agent.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import me.own.commons.base.dao.PageQueryResult;
import me.own.commons.base.utils.request.RequestUtils;
import me.own.learn.agent.dto.AgentRequestDto;
import me.own.learn.agent.dto.AgentRequestHandleDto;
import me.own.learn.agent.service.AgentRequestQueryCondition;
import me.own.learn.agent.service.AgentRequestService;
import me.own.learn.agent.vo.AgentRequestVo;
import me.own.learn.authorization.service.model.AdminAccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yexudong
 * @date 2019/7/5 10:44
 */
@RestController
@RequestMapping(value = "/api/v1/agent_requests")
@Api(value = "分销商申请", description = "管理用户申请分销商")
public class AgentRequestController {

    @Autowired
    private AgentRequestService agentRequestService;

    @ApiOperation("创建成为代理商申请")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity requestToAgent(HttpServletRequest request,
                                         @RequestBody AgentRequestDto requestDto) {
        Map<String, Object> response = new HashMap<>();
        AgentRequestVo agentRequestVo = agentRequestService.create(requestDto);
        response.put("code", 200);
        response.put("data", agentRequestVo);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @ApiOperation("创建成为代理商申请")
    @RequestMapping(value = "/{requestId}", method = RequestMethod.GET)
    public ResponseEntity getById(HttpServletRequest request,
                                  @PathVariable Long requestId) {
        Map<String, Object> response = new HashMap<>();
        AgentRequestVo agentRequestVo = agentRequestService.getById(requestId);
        response.put("code", 200);
        response.put("data", agentRequestVo);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @ApiOperation("分页分销商申请记录表")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity page(HttpServletRequest request,
                               @RequestParam(required = false) AgentRequestQueryCondition condition,
                               @RequestParam(value = "pageNumber", defaultValue = "1") int pageNum,
                               @RequestParam(value = "pageSize", defaultValue = "10") int pageSize
    ) {
        if (condition == null) {
            condition = RequestUtils.buildQueryFilter(request, AgentRequestQueryCondition.class);
        }
        Map<String, Object> response = new HashMap<>();
        PageQueryResult<AgentRequestVo> requestVoPageQueryResult = agentRequestService.page(pageNum, pageSize, condition);
        response.put("code", 200);
        response.put("data", requestVoPageQueryResult);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @ApiOperation("处理分销商申请")
    @RequestMapping(value = "/handler", method = RequestMethod.POST)
    @ApiImplicitParam(name = "a_id", value = "调试模式", paramType = "query", dataType = "String", defaultValue = "1")
    public ResponseEntity handle(HttpServletRequest request, AdminAccessToken aat,
                                 @RequestBody AgentRequestHandleDto handleDto) {
        Map<String, Object> response = new HashMap<>();
        handleDto.setHandlerId(aat.getAdminId());
        AgentRequestVo agentRequestVo = agentRequestService.handle(handleDto);
        response.put("code", 200);
        response.put("data", agentRequestVo);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
