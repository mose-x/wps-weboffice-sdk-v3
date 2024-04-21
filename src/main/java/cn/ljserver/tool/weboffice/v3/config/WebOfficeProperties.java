package cn.ljserver.tool.weboffice.v3.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 开放平台配置对象
 */
@Configuration
@ConfigurationProperties("web-office-v3")
public class WebOfficeProperties {

    private String appid;
    private String secret;
    private String domain;

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

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

}
