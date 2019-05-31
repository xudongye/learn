package me.own.learn.pubaccount.service;

public interface QRCodeCreateService {
    void createPermQRCode(String appId, String scene_str);

    /**
     * 创建临时二维码
     *
     * @param appId
     * @param scene_id scene_id
     * @param expire_seconds 有效时间
     * @return 二维码code url
     */
    String createTempQRCode(String appId, String scene_id, long expire_seconds);

    String createQRCode(String appId, String scene_str, String logoPath, String qrCodeFilePath, String fileName, boolean needCompress);
}
