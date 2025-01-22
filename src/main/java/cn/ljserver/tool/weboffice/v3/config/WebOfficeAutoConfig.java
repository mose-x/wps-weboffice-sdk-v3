package cn.ljserver.tool.weboffice.v3.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 自动配置，不需要接入端 ComponentScan
 */
@Configuration
@ComponentScan("cn.ljserver.tool.weboffice.v3")
public class WebOfficeAutoConfig {

    private final WebOfficeProperties webOfficeProperties;

    @Autowired
    public WebOfficeAutoConfig(WebOfficeProperties webOfficeProperties) {
        this.webOfficeProperties = webOfficeProperties;
    }

    /**
     * 启动验证配置
     */
    @PostConstruct
    public void validateProperty() {
        Logger log = Logger.getLogger(this.getClass().getName());
        if (webOfficeProperties.getConvert() == null) {
            log.log(Level.WARNING, "web-office-v3: WARNING: Required application property 'web-office.convert' is null.");
        } else {
            if (webOfficeProperties.getConvert().getAppid() == null || webOfficeProperties.getConvert().getAppid().isEmpty()) {
                log.log(Level.WARNING, "web-office-v3: WARNING: Required application property 'web-office.convert.appid' is null or empty.");
            }
            if (webOfficeProperties.getConvert().getSecret() == null || webOfficeProperties.getConvert().getSecret().isEmpty()) {
                log.log(Level.WARNING, "web-office-v3: WARNING: Required application property 'web-office.convert.secret' is null or empty.");
            }
        }

        if (webOfficeProperties.getPreview() == null) {
            log.log(Level.WARNING, "web-office-v3: WARNING: Required application property 'web-office.preview' is null.");
        } else {
            if (webOfficeProperties.getPreview().getAppid() == null || webOfficeProperties.getPreview().getAppid().isEmpty()) {
                log.log(Level.WARNING, "web-office-v3: WARNING: Required application property 'web-office.preview.appid' is null or empty.");
            }
            if (webOfficeProperties.getPreview().getSecret() == null || webOfficeProperties.getPreview().getSecret().isEmpty()) {
                log.log(Level.WARNING, "web-office-v3: WARNING: Required application property 'web-office.preview.secret' is null or empty.");
            }
        }
    }
}
