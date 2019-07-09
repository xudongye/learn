package me.own.learn.pubaccount.controller;

import io.swagger.annotations.Api;
import me.own.learn.pubaccount.service.SignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yexudong
 * @date 2018/11/29 13:18
 */
@RestController
@Api(value = "PubAccountSignController", description = "获取sign签名")
public class PubAccountSignController {

    @Autowired
    private SignService signService;

    @RequestMapping(value = "/api/pub-accounts/{appId}/signatures", method = RequestMethod.POST)
    public ResponseEntity signUrl(
            HttpServletRequest request,
            @PathVariable(value = "appId") String appId,
            @RequestBody UrlSignCmd cmd
    ) {
        return new ResponseEntity<>(signService.signUrl(appId, cmd.getUrl()), HttpStatus.CREATED);
    }
}

class UrlSignCmd {
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}