package me.own.learn.pubaccount.service;

import java.io.IOException;

public interface MediaService {
    long downloadMedia(String appId, String mediaId, String destinationFilePath) throws IOException;
}
