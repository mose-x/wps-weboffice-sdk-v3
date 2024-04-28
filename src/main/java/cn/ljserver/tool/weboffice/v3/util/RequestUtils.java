package cn.ljserver.tool.weboffice.v3.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * 请求工具类
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
    public static <T> String request(String method, String url, Map<String, String> headers, T body) {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStringBody = body == null ? "" : objectMapper.writeValueAsString(body);
        return request(method, url, headers, jsonStringBody);
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public static String request(String method, String url, Map<String, String> headers, String jsonStringBody) {
        try {
            // 转换请求方法为大写
            method = method.toUpperCase();
            // 创建url对象
            URL thisUrl = new URL(url);
            // 打开连接
            HttpURLConnection connection = (HttpURLConnection) thisUrl.openConnection();
            // 设置请求方法
            connection.setRequestMethod(method);
            // 设置请求头
            headers.forEach(connection::setRequestProperty);
            // 设置输出
            connection.setDoOutput(true);

            // 这里的顺序不能错
            // ****************************************************
            // 处理post请求的body
            if (jsonStringBody != null && !jsonStringBody.isEmpty()) {
                OutputStream os = connection.getOutputStream();
                byte[] outputInBytes = jsonStringBody.getBytes(StandardCharsets.UTF_8);
                os.write(outputInBytes);
                os.close();
            }

            // 获取响应
            InputStream in = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(in, StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr);
            // ****************************************************

            // 处理响应...
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line);
            }

            // 关闭资源
            in.close();
            isr.close();
            br.close();

            // 关闭连接
            connection.disconnect();

            // 返回响应结果
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
