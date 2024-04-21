package cn.ljserver.tool.weboffice.v3.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.logging.Level;
import java.util.logging.Logger;

@Configuration
@ComponentScan("cn.ljserver.tool.weboffice.v3")
public class AutoConfig {

    private final WebOfficeProperties webOfficeProperties;

    @Autowired
    public AutoConfig(WebOfficeProperties webOfficeProperties) {
        this.webOfficeProperties = webOfficeProperties;
    }

    @PostConstruct
    public void validateProperty() {
        Logger log = Logger.getLogger(this.getClass().getName());

        if (webOfficeProperties.getAppid() == null || webOfficeProperties.getAppid().isEmpty()) {
            log.log(Level.WARNING, "web-office-v3: WARNING: Required application property 'web-office-v3.appid' is not or empty.");
        }
        if (webOfficeProperties.getSecret() == null || webOfficeProperties.getSecret().isEmpty()) {
            log.log(Level.WARNING, "web-office-v3: WARNING: Required application property 'web-office-v3.secret' is not or empty.");
        }
        if (webOfficeProperties.getDomain() == null || webOfficeProperties.getDomain().isEmpty()) {
            log.log(Level.WARNING, "web-office-v3: WARNING: Required application property 'web-office-v3.domain' is not or empty.");
        }
    }
}
