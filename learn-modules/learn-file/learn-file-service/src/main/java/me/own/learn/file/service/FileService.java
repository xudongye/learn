package me.own.learn.file.service;

import me.own.learn.file.bo.FileConfiguration;
import me.own.learn.file.bo.FileUploadResultBo;
import me.own.learn.file.bo.PictureFileUploadResultBo;
import me.own.learn.file.bo.PictureOperationBo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 文件服务接口
 *
 * @author yexudong
 * @date 2019/6/14 10:22
 */
public interface FileService {

    /**
     * 从http请求中保存文件到磁盘
     * @param file http请求中的文件流，由于本项目使用spring作为基础框架，所以此处file必须是MultipartFile的实例
     * @param directoryPath 保存文件的路径，不包含文件根路径，如用户上传图片到作品2017022200001，路径为/alidata/learn/upload/images/friendcircle/2017022200001/，那么只需要传递参数/friendcircle/2017022200001/即可
     * @return 文件上传结果信息
     * @throws IOException 磁盘io异常，如文件传输失败、创建文件权限不足、磁盘空间已满等
     */
    FileUploadResultBo saveFileFromRequest(MultipartFile file, String directoryPath) throws IOException;

    PictureFileUploadResultBo saveImageFileFromRequest(MultipartFile file, String directory, PictureOperationBo operation) throws IOException;

    FileConfiguration getConfig();
}
