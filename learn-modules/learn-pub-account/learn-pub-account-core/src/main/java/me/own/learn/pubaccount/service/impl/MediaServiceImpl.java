package me.own.learn.pubaccount.service.impl;

import me.own.learn.pubaccount.service.MediaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MediaServiceImpl implements MediaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MediaServiceImpl.class);

    private me.own.commons.wechat.pubaccount.media.MediaService mediaService = new me.own.commons.wechat.pubaccount.media.impl.MediaServiceImpl();

    @Override
    public long downloadMedia(String appId, String mediaId, String destinationFilePath) throws IOException {
        long fileLength = mediaService.download(appId, mediaId, destinationFilePath);
        LOGGER.debug("download media file {} from WeChat server to local file directory {}", mediaId, destinationFilePath);
        return fileLength;
    }
}
