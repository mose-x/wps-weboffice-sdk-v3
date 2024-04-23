package cn.ljserver.tool.weboffice.v3.model;

import lombok.Builder;
import lombok.Getter;

/**
 * 程序请求属性
 */
@Getter
@Builder
public class ProviderRequestAttribute {
    public static final String KEY = "weboffice_provider_request_attribute";
    private String appId;
    private String webofficeToken;
    private String requestId;
}
