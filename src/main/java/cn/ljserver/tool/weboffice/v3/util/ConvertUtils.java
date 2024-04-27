package cn.ljserver.tool.weboffice.v3.util;

import cn.ljserver.tool.weboffice.v3.config.WebOfficeProperties;
import cn.ljserver.tool.weboffice.v3.exception.ConfigNotExist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 转换服务请求封装工具类
 */
@Component
public class ConvertUtils {

    private static WebOfficeProperties webOfficeProperties;

    @Autowired
    private ConvertUtils(WebOfficeProperties webOfficeProperties) {
        ConvertUtils.webOfficeProperties = webOfficeProperties;
    }

    private static final Logger log = Logger.getLogger(ConvertUtils.class.getName());

    private ConvertUtils() {
    }

    /**
     * GET请求
     *
     * @param uri 请求URI
     * @return 请求结果
     */
    public static String get(String uri) {
        checkProperties();
        String method = HttpMethod.GET.name();
        String url = webOfficeProperties.getConvert().getDomain() + uri;
        String appid = webOfficeProperties.getConvert().getAppid();
        String secret = webOfficeProperties.getConvert().getSecret();
        Map<String, String> header = HeaderUtils.header(method, uri, null, appid, secret);
        return RequestUtils.request(method, url, header, null);
    }

    /**
     * POST请求
     *
     * @param uri  请求URI
     * @param body 请求体
     * @return 请求结果
     */
    public static String post(String uri, String body) {
        checkProperties();
        String method = HttpMethod.POST.name();
        String url = webOfficeProperties.getConvert().getDomain() + uri;
        String appid = webOfficeProperties.getConvert().getAppid();
        String secret = webOfficeProperties.getConvert().getSecret();
        Map<String, String> header = HeaderUtils.header(method, null, body, appid, secret);
        return RequestUtils.request(method, url, header, body);
    }

    private static void checkProperties() {
        if (webOfficeProperties.getConvert() == null) {
            log.log(Level.SEVERE, "web-office-v3: ERROR: Required application property 'web-office.preview' is null.");
            throw new ConfigNotExist();
        } else {
            if (webOfficeProperties.getConvert().getAppid() == null || webOfficeProperties.getConvert().getAppid().isEmpty()) {
                log.log(Level.SEVERE, "web-office-v3: ERROR: Required application property 'web-office.preview.appid' is null or empty.");
                throw new ConfigNotExist();
            }
            if (webOfficeProperties.getConvert().getSecret() == null || webOfficeProperties.getConvert().getSecret().isEmpty()) {
                log.log(Level.SEVERE, "web-office-v3: ERROR: Required application property 'web-office.preview.secret' is null or empty.");
                throw new ConfigNotExist();
            }
        }
    }
}
