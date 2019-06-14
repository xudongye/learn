package me.own.learn.file.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.own.learn.authorization.service.AdminAuthenticationRequired;
import me.own.learn.authorization.service.model.AdminAccessToken;
import me.own.learn.authorization.service.model.CustomerAccessToken;
import me.own.learn.file.bo.PictureOperationBo;
import me.own.learn.file.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;

/**
 * @author yexudong
 * @date 2019/6/14 13:11
 */
@RestController
@RequestMapping(value = "/api/learn/v1")
@Api(value = "文件上传", description = "文件上传功能")
public class FileController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileService fileService;

    @ApiOperation("会员上传文件接口")
    @RequestMapping(value = "/customer/files", method = RequestMethod.POST, consumes = "multipart/form-data")
    public ResponseEntity customerUpload(
            MultipartHttpServletRequest request, CustomerAccessToken cat,
            @RequestPart(value = "file") MultipartFile file,
            @RequestParam(value = "directory") String directory
    ) throws IOException {
//        LOGGER.info("customer {} upload file {} to directory {}.", cat.getCustomerId(), file.getOriginalFilename(), directory);
        return new ResponseEntity<>(fileService.saveFileFromRequest(file, directory), HttpStatus.CREATED);
    }

    @ApiOperation("管理员上传文件接口")
    @RequestMapping(value = "/admin/files", method = RequestMethod.POST, consumes = "multipart/form-data")
//    @AdminAuthenticationRequired
    public ResponseEntity adminUserUpload(
            MultipartHttpServletRequest request, AdminAccessToken aat,
            @RequestPart(value = "file") MultipartFile file,
            @RequestParam(value = "directory") String directory
    ) throws IOException {
//        LOGGER.info("admin user {} upload file {} to directory {}.", aat.getAdminId(), file.getOriginalFilename(), directory);
        return new ResponseEntity<>(fileService.saveFileFromRequest(file, directory), HttpStatus.CREATED);
    }


    @ApiOperation("上传图片接口-支持裁剪，旋转")
    @RequestMapping(value = "/files/images", method = RequestMethod.POST, consumes = "multipart/form-data")
    public ResponseEntity uploadImage(
            MultipartHttpServletRequest request, CustomerAccessToken cat,
            @RequestPart(value = "file") MultipartFile file,
            @RequestParam(value = "directory") String directory,
            PictureOperationBo operationBo
    ) throws IOException {
//        LOGGER.info("customer {} upload image file {}.", cat.getCustomerId(), file.getOriginalFilename());
        return new ResponseEntity<>(fileService.saveImageFileFromRequest(file, directory, operationBo), HttpStatus.CREATED);
    }

}
