package me.own.learn.image.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.own.commons.base.dao.PageQueryResult;
import me.own.commons.base.utils.request.RequestUtils;
import me.own.learn.image.dto.ImageDto;
import me.own.learn.image.service.ImageQueryCondition;
import me.own.learn.image.service.ImageService;
import me.own.learn.image.vo.ImageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yexudong
 * @date 2019/7/3 14:54
 */
@RestController
@RequestMapping(value = "/api/v1/images")
@Api(value = "图片服务器", description = "管理图片")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @ApiOperation("存储图片相关路径")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity imageUrlSave(HttpServletRequest request,
                                       @RequestBody ImageDto imageDto) {
        Map<String, Object> response = new HashMap<>();

        ImageVo imageVo = imageService.save(imageDto);
        response.put("code", 200);
        response.put("data", imageVo);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @ApiOperation("分页查图片列表")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity pageImage(HttpServletRequest request,
                                    @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                    @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                    @RequestParam(required = false) ImageQueryCondition condition) {

        if (condition == null) {
            condition = RequestUtils.buildQueryFilter(request, ImageQueryCondition.class);
        }
        Map<String, Object> response = new HashMap<>();
        PageQueryResult<ImageVo> result = imageService.page(pageNum, pageSize, condition);
        response.put("code", 200);
        response.put("data", result);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @ApiOperation("删除图片")
    @RequestMapping(value = "/{imageId}", method = RequestMethod.DELETE)
    public ResponseEntity delete(HttpServletRequest request,
                                 @PathVariable long imageId) {
        imageService.delete(imageId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
