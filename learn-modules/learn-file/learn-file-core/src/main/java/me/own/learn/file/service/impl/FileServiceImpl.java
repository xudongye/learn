package me.own.learn.file.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.own.commons.base.utils.image.ImageRotateUtils;
import me.own.commons.base.utils.mapper.Mapper;
import me.own.learn.configuration.delegate.LearnConfigurationServiceDelegate;
import me.own.learn.file.bo.FileConfiguration;
import me.own.learn.file.bo.FileUploadResultBo;
import me.own.learn.file.bo.PictureFileUploadResultBo;
import me.own.learn.file.bo.PictureOperationBo;
import me.own.learn.file.service.FileService;
import me.own.learn.file.service.PictureFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;

/**
 * @author yexudong
 * @date 2019/6/14 10:41
 */
@Service
public class FileServiceImpl implements FileService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileServiceImpl.class);

    private LearnConfigurationServiceDelegate delegate = LearnConfigurationServiceDelegate.getInstance();

    private static FileConfiguration fileConfigurationInstance;

    @Autowired
    private PictureFileService pictureFileService;

    @Override
    public FileUploadResultBo saveFileFromRequest(MultipartFile multipartFile, String directoryPath) throws IOException {
        FileUploadProcessor fileUploadProcessor = new FileUploadProcessor(multipartFile, directoryPath).invoke();
        File physicalFile = fileUploadProcessor.getPhysicalFile();
        FileUploadResultBo result = fileUploadProcessor.getResult();
        multipartFile.transferTo(physicalFile);
        return result;
    }

    @Override
    public PictureFileUploadResultBo saveImageFileFromRequest(MultipartFile file, String directory, PictureOperationBo operation) throws IOException {
        FileUploadProcessor fileUploadProcessor = new FileUploadProcessor(file, directory).invoke();
        FileUploadResultBo fileUploadProcessorResult = fileUploadProcessor.getResult();

        String ext = fileUploadProcessorResult.getExt().substring(1);
        if (!Arrays.asList("jpg", "jpeg", "png", "bmp").contains(ext)) {
            throw new IllegalArgumentException("unknown file ext: " + ext);
        }

        File physicalFile = fileUploadProcessor.getPhysicalFile();
        file.transferTo(physicalFile);

        try {
            rotateExif(new FileInputStream(physicalFile), physicalFile);

            cut(physicalFile, operation.getX(), operation.getY(), operation.getW(), operation.getH());

            rotate(physicalFile, operation.getRotateDegree());

            compress(physicalFile);

            PictureFileUploadResultBo resultBo = Mapper.Default().map(fileUploadProcessorResult, PictureFileUploadResultBo.class);

            BufferedImage imageAfterCompress = ImageIO.read(physicalFile);
            resultBo.setWidth(imageAfterCompress.getWidth());
            resultBo.setHeight(imageAfterCompress.getHeight());
            resultBo.setSize(physicalFile.length());

            return resultBo;
        } catch (IOException e) {
            physicalFile.deleteOnExit();
            throw e;
        }
    }

    @Override
    public FileConfiguration getConfig() {
        if (fileConfigurationInstance == null) {
            fileConfigurationInstance = initFileConfiguration();
        }
        return fileConfigurationInstance;
    }

    private FileConfiguration initFileConfiguration() {
        InputStream configInputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("file.config.json");
        try {
            return new ObjectMapper().readValue(configInputStream, FileConfiguration.class);
        } catch (IOException e) {
            LOGGER.error("Fatal: file configuration json read error.", e);
            throw new RuntimeException(e);
        }
    }

    private void compress(File physicalFile) throws IOException {
        long fileSize = physicalFile.length();
        float quality = (1024 * 1024F) / fileSize;
        if (quality < 1) {
            pictureFileService.compress(physicalFile, quality);
            LOGGER.debug("compress image {} quality {}", physicalFile.getAbsolutePath(), quality);
        }
    }

    private void rotate(File physicalFile, Integer degree) throws IOException {
        if (degree != null && degree != 0) {
            pictureFileService.rotate(physicalFile, degree);
            LOGGER.debug("rotate image {} degree {}", physicalFile.getAbsolutePath(), degree);
        }
    }

    private void cut(File physicalFile, Double x, Double y, Double w, Double h) throws IOException {
        if (w != null && h != null) {
            pictureFileService.cut(physicalFile, x, y, w, h);
            LOGGER.debug("cut image {}, x:{}-->{}, y:{}-->{}", physicalFile.getAbsolutePath(), x, w, y, h);
        }
    }

    private void rotateExif(InputStream inputStream, File physicalFile) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(inputStream);
        ImageRotateUtils.rotateImageIfNecessary(bufferedImage, physicalFile, true);
        inputStream.close();
    }


    private String randomFilename() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        return format.format(System.currentTimeMillis()) + (int) (Math.random() * 1000);
    }

    private class FileUploadProcessor {
        private MultipartFile multipartFile;
        private String directoryPath;
        private FileUploadResultBo result;
        private File physicalFile;

        public FileUploadProcessor(MultipartFile multipartFile, String directoryPath) {
            this.multipartFile = multipartFile;
            this.directoryPath = directoryPath;
        }

        public FileUploadResultBo getResult() {
            return result;
        }

        public File getPhysicalFile() {
            return physicalFile;
        }

        public FileUploadProcessor invoke() throws IOException {
            result = new FileUploadResultBo();
            String originalFilename = multipartFile.getOriginalFilename();
            result.setOriginalFilename(originalFilename);
            String ext = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
            result.setExt(ext);
            String filenameAfterTransfer = randomFilename() + ext;
            result.setTitle(filenameAfterTransfer);
            physicalFile = new File(
                    delegate.getConfiguration().getFile().getRoot() + File.separator + directoryPath, filenameAfterTransfer);
            result.setUrl(replaceFileSeparatorToUrlSlash(directoryPath) + "/" + filenameAfterTransfer);
            File parentFile = physicalFile.getParentFile();
            if (!parentFile.exists()) {
                boolean mkdirs = parentFile.mkdirs();
                if (!mkdirs) {
                    throw new IOException("directory make failed:" + physicalFile.getAbsolutePath());
                }
            }
            long fileSize = multipartFile.getSize();
            LOGGER.info("save file from request: original filename: {}, server disk file path: {}, size: {}", result.getOriginalFilename(), physicalFile.getAbsoluteFile(), fileSize);
            result.setSize(fileSize);
            result.setUrlPrefix(delegate.getConfiguration().getFile().getUrlPrefix());
            return this;
        }

        private String replaceFileSeparatorToUrlSlash(String path) {
            return path.replace(File.separator, "/");
        }
    }
}
