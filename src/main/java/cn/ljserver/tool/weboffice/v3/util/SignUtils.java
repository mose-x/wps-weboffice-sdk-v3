package cn.ljserver.tool.weboffice.v3.util;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 签名工具
 */
public class SignUtils {

    private SignUtils() {
    }


    /**
     * 获取md5
     *
     * @param method 请求方法
     * @param uri    请求路径,如 /api/f/1?a=1
     * @param body   请求体, post需要传递body,get请求不需要
     * @return md5
     */
    public static String md5(String method, String uri, String body) {
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

    /**
     * 签名
     *
     * @param appid  应用id
     * @param secret 应用密钥
     * @param date   日期
     * @param md5    md5
     * @return 签名
     */
    public static String sign(String appid, String secret, String date, String md5) {
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


    /**
     * 签名
     *
     * @param appid     应用id
     * @param secret    应用密钥
     * @param date      日期
     * @param md5       md5
     * @param algorithm 算法
     * @return 签名
     */
    public static String sign(String appid, String secret, String date, String md5, String algorithm) {
        MessageDigest md;
        String signStr = secret + md5 + "application/json" + date;
        try {
            md = MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        md.update(signStr.getBytes());
        byte[] hashBytes = md.digest();
        String sign = DatatypeConverter.printHexBinary(hashBytes);
        return "WPS-2:" + appid + ":" + sign;
    }
}
