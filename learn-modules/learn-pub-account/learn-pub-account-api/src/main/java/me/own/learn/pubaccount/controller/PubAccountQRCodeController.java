package me.own.learn.pubaccount.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.own.learn.pubaccount.service.QRCodeCreateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yexudong
 * @date 2018/12/7 16:09
 */
@RestController
@Api(value = "PubAccountQRCodeController", description = "公众号自定义二维码接口")
public class PubAccountQRCodeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PubAccountQRCodeController.class);

    @Autowired
    private QRCodeCreateService qrCodeCreateService;

    @ApiOperation("获取公众号自定义带参数二维码接口")
    @RequestMapping(value = "/api/learn/pub-accounts/{appId}/qrcode/{inviteId}/create", method = RequestMethod.GET)
    public ResponseEntity createMenu(HttpServletRequest request, @PathVariable(value = "appId") String appId,
                                     @PathVariable Long inviteId) {
        LOGGER.info("create a qrcode for invite {}", inviteId);
        return new ResponseEntity(qrCodeCreateService.createPermQRCode(appId, String.valueOf(inviteId)), HttpStatus.CREATED);
    }
}
