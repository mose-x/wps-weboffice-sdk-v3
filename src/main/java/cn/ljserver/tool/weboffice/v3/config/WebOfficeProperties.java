package cn.ljserver.tool.weboffice.v3.config;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;

/**
 * 开放平台配置对象
 */
@Data
@Configuration
@ConfigurationProperties("web-office")
public class WebOfficeProperties {

    /**
     * 转换配置
     */
    @NestedConfigurationProperty
    private ConvertConfig convert;

    /**
     * 预览配置
     */
    @NestedConfigurationProperty
    private Config preview;

    @Data
    public static class Config {

        private String appid;
        private String secret;

    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class ConvertConfig extends Config {
        private String domain = "https://solution.wps.cn";
    }

}
