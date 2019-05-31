package me.own.learn.pubaccount.service.impl;

import me.lebooks.commons.base.utils.qrcode.QRUtils;
import me.lebooks.commons.wechat.pubaccount.account.ParameterizedQRCodeService;
import me.lebooks.commons.wechat.pubaccount.account.impl.ParameterizedQRCodeServiceImpl;
import me.lebooks.commons.wechat.pubaccount.account.model.QRCodeCreateResult;
import me.lebooks.lishu.pubaccount.service.QRCodeCreateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class QRCodeCreateServiceImpl implements QRCodeCreateService {

    private static Logger logger = LoggerFactory.getLogger(QRCodeCreateServiceImpl.class);

    ParameterizedQRCodeService qrCodeService = new ParameterizedQRCodeServiceImpl();

    @Override
    public void createPermQRCode(String appId, String scene_str) {
        qrCodeService.createPermQRCode(appId, scene_str);
    }

    @Override
    public String createTempQRCode(String appId, String scene_id, long expire_seconds) {
        return qrCodeService.createTempQRCode(appId, scene_id, expire_seconds).getUrl();
    }

    @Override
    public String createQRCode(String appId, String scene_str, String logoPath, String qrCodeFilePath, String fileName, boolean needCompress) {
        QRCodeCreateResult result = qrCodeService.createPermQRCode(appId, scene_str);
        String url = result.getUrl();
        try {
            QRUtils.encode(url,qrCodeFilePath,logoPath,fileName,needCompress);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
        return url;
    }
}
