package cn.ljserver.tool.weboffice.v3.model;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 签名算法类型
 */
public enum DigestType {
    NONE(""),
    MD5("md5"),
    SHA1("sha1"),
    SHA256("sha256");

    @JsonValue
    private final String jsonValue;

    DigestType(String jsonValue) {
        this.jsonValue = jsonValue;
    }
}
