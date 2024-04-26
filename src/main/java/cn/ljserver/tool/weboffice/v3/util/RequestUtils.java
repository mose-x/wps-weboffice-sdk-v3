package cn.ljserver.tool.weboffice.v3.util;

import lombok.SneakyThrows;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

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

    @SuppressWarnings("CallToPrintStackTrace")
    @SneakyThrows
    public static String get(String uri, Map<String, String> headers) {
        URL url = new URL(uri);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        // 设置请求方法为 GET
        connection.setRequestMethod("GET");
        headers.forEach(connection::setRequestProperty);
        connection.setDoOutput(true);
        try (

                InputStream in = connection.getInputStream();
                InputStreamReader isr = new InputStreamReader(in, StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr);
        )
        {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            connection.disconnect();
            return response.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }



}
