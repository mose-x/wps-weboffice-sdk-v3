package cn.ljserver.tool.weboffice.v3.util;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ConvertHeader {
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
        String md5 = md5(method, uri, body);
        String data = date();
        header.put("Content-Md5", md5);
        header.put("Content-Type", "application/json");
        header.put("DATE", data);
        header.put("Authorization", sign(appid, secret, data, md5));
        return header;
    }

    private static String md5(String method, String uri, String body) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            if ("GET".equalsIgnoreCase(method)) {
                md5.update(uri.getBytes());
                byte[] md5Bytes = md5.digest();
                return DatatypeConverter.printHexBinary(md5Bytes);
            } else if ("POST".equalsIgnoreCase(method)) {
                md5.update(body.getBytes(StandardCharsets.UTF_8));
                byte[] md5Bytes = md5.digest();
                return DatatypeConverter.printHexBinary(md5Bytes);
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return "";
    }

    private static String date() {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        return sdf.format(new Date());
    }

    private static String sign(String appid, String secret, String date, String md5) {
        MessageDigest md;
        String signStr = secret + md5 + "application/json" + date;
        try {
            md = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        md.update(signStr.getBytes());
        byte[] hashBytes = md.digest();
        String sign = DatatypeConverter.printHexBinary(hashBytes);
        return "WPS-2:" + appid + ":" + sign;
    }
}
