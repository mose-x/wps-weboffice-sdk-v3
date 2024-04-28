package cn.ljserver.tool.weboffice.v3.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 开放平台配置对象
 */
@Configuration
@ConfigurationProperties("web-office")
public class WebOfficeProperties {

    /**
     * 转换配置
     */
    private ConvertConfig convert;

    /**
     * 预览配置
     */
    private Config preview;

    public Config getPreview() {
        return preview;
    }

    public void setPreview(Config preview) {
        this.preview = preview;
    }

    public ConvertConfig getConvert() {
        return convert;
    }

    public void setConvert(ConvertConfig convert) {
        this.convert = convert;
    }


    public static class Config {

        private String appid;
        private String secret;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getSecret() {
            return secret;
        }

        public void setSecret(String secret) {
            this.secret = secret;
        }
    }


    public static class ConvertConfig extends Config {
        private String domain = "https://solution.wps.cn";

        public String getDomain() {
            return domain;
        }

        public void setDomain(String domain) {
            this.domain = domain;
        }
    }

}
