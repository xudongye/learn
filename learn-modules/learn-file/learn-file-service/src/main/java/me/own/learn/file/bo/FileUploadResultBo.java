package me.own.learn.file.bo;

/**
 * @author yexudong
 * @date 2019/6/14 10:25
 */
public class FileUploadResultBo {
    /**
     * 上传后的文件名称
     */
    private String title;

    /**
     * 上传后的文件路径，不包括固定的前缀
     *  即与url前缀拼接为完整的文件网络访问路径
     *  与file root前缀拼接为完整的服务器磁盘文件系统访问路径
     */
    private String url;

    /**
     * url前缀，如：http://www.lebooks.me/static/images/
     * 包括协议名、主机名、端口号、url前缀等
     * 此参数与文件服务器的配置相耦合，不随着业务的变化而变化
     */
    private String urlPrefix;

    /**
     * 文件后缀名，包含'.'符号
     */
    private String ext;

    /**
     * 文件上传前的原始文件名
     */
    private String originalFilename;

    /**
     * 保存后的文件大小，单位是B
     * @see java.io.File#length()
     */
    private Long size;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlPrefix() {
        return urlPrefix;
    }

    public void setUrlPrefix(String urlPrefix) {
        this.urlPrefix = urlPrefix;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public void setOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }
}
