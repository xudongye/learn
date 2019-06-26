package me.own.learn.captcha.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.own.learn.captcha.utils.CaptchaGenerator;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author:simonye
 * @date 22:03 2019/6/26
 **/
@RestController
@RequestMapping(value = "/api/learn/v1/captchas")
@Api(value = "图片验证码", description = "图片验证码")
public class CaptchaController {

    @ApiOperation("验证图片验证码")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity verifyImageCode(HttpServletRequest request,
                                          HttpSession session,
                                          @RequestParam String checkCode) {
        Map<String, Object> response = new HashMap<>();
        // 获得验证码对象
        Object cko = session.getAttribute("simpleCaptcha");
        if (cko == null) {
            response.put("code", 400);
            response.put("message", "请重新获取验证码");
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
        String captcha = cko.toString();
        // 验证码有效时长为1分钟
        Date now = new Date();
        Long codeTime = Long.valueOf(session.getAttribute("codeTime") + "");
        // 判断验证码输入是否正确
        if (StringUtils.isEmpty(checkCode) || !(checkCode.equalsIgnoreCase(captcha))) {
            response.put("code", 400);
            response.put("message", "验证码错误");
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        } else if ((now.getTime() - codeTime) / 1000 / 60 > 1) {
            response.put("code", 400);
            response.put("message", "验证码已失效，请重新获取！");
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        } else {
            response.put("code", 200);
            response.put("message", "验证成功");
            return new ResponseEntity(response, HttpStatus.CREATED);
        }

    }

    @ApiOperation("刷新图片验证码")
    @RequestMapping(method = RequestMethod.GET)
    public String imageCode(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // 禁止图像缓存
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        // 设置响应的类型格式为图片格式
        response.setContentType("image/jpeg");

        OutputStream os = response.getOutputStream();

        //返回验证码和图片的map
        Map<String, Object> map = CaptchaGenerator.getImageCode(86, 37, os);
        String simpleCaptcha = "simpleCaptcha";
        request.getSession().setAttribute(simpleCaptcha, map.get("strEnsure").toString().toLowerCase());
        request.getSession().setAttribute("codeTime", new Date().getTime());

        try {
            ImageIO.write((BufferedImage) map.get("image"), "jpg", os);
        } catch (IOException e) {
            return null;
        } finally {
            if (os != null) {
                os.flush();
                os.close();
            }
        }
        return null;
    }
}
