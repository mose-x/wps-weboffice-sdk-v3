package cn.ljserver.tool.weboffice.v3.util;

import com.squareup.okhttp.Headers;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import lombok.SneakyThrows;
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

    @SneakyThrows
    public static String get(String uri, Headers headers) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(uri)
                .get()
                .headers(headers)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }



}
