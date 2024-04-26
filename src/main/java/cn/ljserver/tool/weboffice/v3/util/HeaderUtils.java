package cn.ljserver.tool.weboffice.v3.util;

import cn.ljserver.tool.weboffice.v3.exception.InvalidArgument;
import cn.ljserver.tool.weboffice.v3.exception.InvalidToken;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @ClassName: HeaderUtils
 * @Description: 请求头工具类
 */
public class HeaderUtils {
    private HeaderUtils() {
    }

    /**
     * 获取请求头
     *
     * @param method 请求方法
     * @param uri    请求路径,如 /api/f/1?a=1
     * @param body   请求体, post需要传递body,get请求不需要
     * @param appid  应用id
     * @param secret 应用密钥
     * @return 请求头
     */
    public static Map<String, String> header(String method, String uri, String body, String appid, String secret) {
        Map<String, String> header = new HashMap<>();
        String md5 = SignUtils.md5(method, uri, body);
        String data = DateUtils.date();
        header.put("Content-Md5", md5);
        header.put("Content-Type", "application/json");
        header.put("Date", data);
        header.put("Authorization", SignUtils.sign(appid, secret, data, md5));
        return header;
    }

    /**
     * 获取请求头
     *
     * @param request 请求
     * @return Content-Md5 -> 请求体的md5值
     */
    public static String getHeaderContentMd5(HttpServletRequest request) {
        return request.getHeader("Content-Md5");
    }

    /**
     * 获取请求头
     *
     * @param request 请求
     * @return Content-Type -> 请求体的类型
     */
    public static String getHeaderContentType(HttpServletRequest request) {
        return request.getHeader("Content-Type");
    }

    /**
     * 获取请求头
     *
     * @param request 请求
     * @return DATE -> 请求的时间
     */
    public static String getHeaderDate(HttpServletRequest request) {
        return request.getHeader("Date");
    }

    /**
     * 获取请求头
     *
     * @param request 请求
     * @return Authorization -> 请求签名
     */
    public static String getHeaderAuthorization(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    /**
     * 获取请求头
     *
     * @param request 请求
     * @return X-Weboffice-Token -> 当前请求的用户凭证，即通过初始化参数传递的token值
     */
    public static String getWebOfficeToken(HttpServletRequest request) {
        final String token;
        if (Objects.isNull((token = request.getHeader("X-Weboffice-Token")))) {
            throw new InvalidToken("weboffice token is required");
        }
        return token;
    }

    /**
     * 获取请求头
     *
     * @param request 请求
     * @return X-App-Id -> 当前请求所属的 AppId 与 URl 上的 _w_appid 值相同
     */
    public static String getAppId(HttpServletRequest request) {
        final String appId;
        if (Objects.isNull((appId = request.getHeader("X-App-Id")))) {
            throw new InvalidArgument("app id is required");
        }
        return appId;
    }

    /**
     * 获取请求头
     *
     * @param request 请求
     * @return X-Request-Id -> wps web office 本次请求 ID，方便定位问题
     */
    public static String getRequestId(HttpServletRequest request) {
        final String requestId;
        if (Objects.isNull(requestId = request.getHeader("X-Request-Id"))) {
            return "";
        }
        return requestId;
    }

    /**
     * 获取请求头
     *
     * @param request 请求
     * @return X-User-Query -> 用户传递请求数据
     */
    public static String getUserQuery(HttpServletRequest request) {
        final String userQuery;
        if (Objects.isNull(userQuery = request.getHeader("X-User-Query"))) {
            return "";
        }
        return userQuery;
    }
}
