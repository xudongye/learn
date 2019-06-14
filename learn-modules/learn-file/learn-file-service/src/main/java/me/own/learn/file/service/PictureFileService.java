package me.own.learn.file.service;

import me.own.learn.file.bo.PictureSizeBo;
import me.own.learn.file.exception.RemotePictureReadException;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author yexudong
 * @date 2019/6/14 13:04
 */
public interface PictureFileService {

    PictureSizeBo readSize(String remoteUrl) throws RemotePictureReadException;

    BufferedImage rotate(BufferedImage image, int degree);

    void rotate(File file, int degree) throws IOException;

    BufferedImage cut(BufferedImage image, double x, double y, double w, double h);

    void cut(File file, double x, double y, double w, double h) throws IOException;

    void compress(BufferedImage image, float quality, String dest, String formatName) throws IOException;

    void compress(File file, float quality) throws IOException;
}
