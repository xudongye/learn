package me.own.learn.file.service.impl;

import me.own.learn.file.bo.PictureSizeBo;
import me.own.learn.file.exception.RemotePictureReadException;
import me.own.learn.file.service.PictureFileService;
import org.springframework.stereotype.Service;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author yexudong
 * @date 2019/6/14 13:06
 */
@Service
public class PictureFileServiceImpl implements PictureFileService {
    @Override
    public PictureSizeBo readSize(String remoteUrl) throws RemotePictureReadException {
        throw new UnsupportedOperationException("");
    }

    @Override
    public BufferedImage rotate(BufferedImage bufferedimage, int degree) {
        if(degree == 90){
            return rotateImageBy90(bufferedimage);
        }else if(degree == 180){
            bufferedimage = rotateImageBy90(bufferedimage);
            bufferedimage = rotateImageBy90(bufferedimage);
            return bufferedimage;
        }else if(degree == 270){
            bufferedimage = rotateImageBy90(bufferedimage);
            bufferedimage = rotateImageBy90(bufferedimage);
            bufferedimage = rotateImageBy90(bufferedimage);
            return bufferedimage;
        }else{
            return bufferedimage;
        }
    }

    @Override
    public void rotate(File file, int degree) throws IOException {
        BufferedImage bi = ImageIO.read(file);
        BufferedImage rotateImage = this.rotate(bi, degree);
        String ext = file.getName().substring(file.getName().indexOf(".") + 1);
        ImageIO.write(rotateImage, ext, file);
    }

    @Override
    public BufferedImage cut(BufferedImage image, double x, double y, double w, double h) {
        if (x < 0 || y < 0 || w <= 0 || h <= 0) {
            throw new IllegalArgumentException("x,y,w,h must be positive.");
        }
        if (x >= 1 || y >= 1 || w > 1 || h > 1) {
            throw new IllegalArgumentException("x,y,w,h must less than 100%.");
        }
        if ((x + w) > 1 || (y + h) > 1) {
            throw new IllegalArgumentException("can not cut larger than 100%.");
        }
        int width = image.getWidth();
        int height = image.getHeight();
        int xPx = (int) (width * x);
        int yPx = (int) (height * y);
        int wPx = (int) (width * w);
        int hPx = (int) (height * h);
        return image.getSubimage(xPx, yPx, wPx, hPx);
    }

    @Override
    public void cut(File file, double x, double y, double w, double h) throws IOException {
        BufferedImage bi = ImageIO.read(file);
        BufferedImage biAfterCut = this.cut(bi, x, y, w, h);
        String ext = file.getName().substring(file.getName().indexOf(".") + 1);
        ImageIO.write(biAfterCut, ext, file);
    }

    @Override
    public void compress(BufferedImage image, float quality, String dest, String formatName) throws IOException {
        if (quality < 0 || quality > 1) {
            throw new IllegalArgumentException("quality:(0,1]");
        }
        OutputStream outputStream = null;
        ImageOutputStream imageOutputStream = null;
        ImageWriter writer = null;
        try {
            outputStream = new FileOutputStream(dest);
            writer = ImageIO.getImageWritersByFormatName(formatName).next();
            imageOutputStream = ImageIO.createImageOutputStream(outputStream);
            writer.setOutput(imageOutputStream);
            ImageWriteParam imageWriteParam = writer.getDefaultWriteParam();

            //Set the compress quality metrics
            if (imageWriteParam.canWriteCompressed()) {
                imageWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
                imageWriteParam.setCompressionQuality(quality);
            }
            writer.write(null, new IIOImage(image, null, null), imageWriteParam);
        } finally {
            if (null != outputStream) {
                outputStream.close();
            }
            if (null != imageOutputStream) {
                imageOutputStream.close();
            }
            if (null != writer) {
                writer.dispose();
            }
        }
    }

    @Override
    public void compress(File file, float quality) throws IOException {
        BufferedImage bi = ImageIO.read(file);
        String ext = file.getName().substring(file.getName().indexOf(".") + 1);
        this.compress(bi, quality, file.getAbsolutePath(), ext);
    }

    public BufferedImage rotateImageBy90(BufferedImage bufferedimage) {
        int h = bufferedimage.getHeight();
        int w = bufferedimage.getWidth();
        AffineTransform xform = new AffineTransform();
        xform.translate(0.5 * h, 0.5 * w);
        xform.rotate(Math.toRadians(90));
        xform.translate(-0.5 * w, -0.5 * h);
        AffineTransformOp op = new AffineTransformOp(xform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        return op.filter(bufferedimage, null);
    }
}
