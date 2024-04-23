package cn.ljserver.tool.weboffice.v3.util;

import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName RequestUtils
 * @Description: 请求工具类
 */
public class RequestUtils {
    private RequestUtils() {
    }

    public static HttpServletRequest getCurrentRequest() {
        // #ServletUriComponentsBuilder.getCurrentRequest()
        RequestAttributes attrs = RequestContextHolder.getRequestAttributes();
        Assert.state(attrs instanceof ServletRequestAttributes, "No current ServletRequestAttributes");
        return ((ServletRequestAttributes) attrs).getRequest();
    }

}
