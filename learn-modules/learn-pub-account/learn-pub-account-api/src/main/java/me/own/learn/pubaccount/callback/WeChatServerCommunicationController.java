package me.own.learn.pubaccount.callback;
import io.swagger.annotations.Api;
import me.own.commons.wechat.pubaccount.message.MessageHandler;
import me.own.commons.wechat.pubaccount.message.MessageResultListener;
import me.own.commons.wechat.pubaccount.sign.Sign;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author yexudong
 * @date 2018/11/14 16:42
 */
@RestController
@Api(value = "WeChatServerCommunicationController", description = "公众号回调接口")
public class WeChatServerCommunicationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeChatServerCommunicationController.class);


    private MessageHandler messageHandler = new MessageHandler();

    @Autowired
    private MessageResultListener listener;

    @RequestMapping(value = "/pub-accounts/{appId}/communicate", method = RequestMethod.POST, produces = {"text/xml;charset=UTF-8"})
    public String handleWeChatMessage(
            HttpServletRequest request,
            @PathVariable(value = "appId") String appId,
            @RequestParam(value = "signature") String signature,
            @RequestParam(value = "timestamp") String timestamp,
            @RequestParam(value = "nonce") String nonce
    ) {
        LOGGER.debug("wechat pubaccount: communicate ai system with appId: {} ,signature: {},timestamp :{},nonce:{}", appId, signature, timestamp, nonce);
        if (Sign.checkSignature(appId, signature, timestamp, nonce)) {
            try {
                StringBuilder sb = new StringBuilder();
                InputStream is = request.getInputStream();
                InputStreamReader isr = new InputStreamReader(is, "UTF-8");
                BufferedReader br = new BufferedReader(isr);
                String s;
                while ((s = br.readLine()) != null) {
                    sb.append(s);
                }
                String receivedData = sb.toString();
                LOGGER.debug("wechat pubaccount: send data :{}", receivedData);
                return messageHandler.handle(appId, receivedData, listener);
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        } else {
            LOGGER.warn("check signature failed");
        }
        return "";
    }

    /**
     * checkSignature是验证签名的，当开发者在公众平台提交URL和token时，微信服务器将发送GET请求到填写的URL上，并且带上四个参数:
     *
     * @param request
     * @param appId
     * @param signature 微信加密签名
     * @param timestamp 时间戳
     * @param nonce     随机数
     * @param echostr   随机字符串
     * @return
     */

    @RequestMapping(value = "/pub-accounts/{appId}/communicate", method = RequestMethod.GET)
    public String validateServerAddress(
            HttpServletRequest request,
            @PathVariable(value = "appId") String appId,
            @RequestParam(value = "signature") String signature,
            @RequestParam(value = "timestamp") String timestamp,
            @RequestParam(value = "nonce") String nonce,
            @RequestParam(value = "echostr") String echostr
    ) {
        if (Sign.checkSignature(appId, signature, timestamp, nonce)) {
            return echostr;
        } else {
            return "";
        }
    }
}
