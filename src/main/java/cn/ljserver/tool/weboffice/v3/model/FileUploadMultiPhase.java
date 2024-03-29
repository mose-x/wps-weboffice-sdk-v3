package cn.ljserver.tool.weboffice.v3.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

public class FileUploadMultiPhase {
    public static class FileUploadAddress {
        @Data
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Request {
            @JsonProperty("file_id")
            private String fileId;

            @JsonProperty("name")
            private String name;

            @JsonProperty("size")
            private int size;

            @JsonProperty("digest")
            private Map<String, String> digest;

            @JsonProperty("is_manual")
            private boolean manual;

            @JsonProperty("attachment_size")
            private int attachmentSize;

            @JsonProperty("content_type")
            private String contentType;
        }

        @Data
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        public static class Response {
            public enum Method {
                POST, PUT;

                @JsonValue
                public String jsonValue() {
                    return this.name().toUpperCase();
                }
            }

            @JsonProperty("url")
            private String url;

            @JsonProperty("method")
            private Method method;

            @JsonProperty("headers")
            private Map<String, String> extraHeaders;

            @JsonProperty("params")
            private Map<String, String> extraParams;

            @JsonProperty("send_back_params")
            private Map<String, String> sendBackParams;
        }
    }

    public static class FileUploadComplete {
        @Data
        @NoArgsConstructor
        public static class UploadResponse {
            @JsonProperty("status_code")
            private int status;

            @JsonProperty("headers")
            private Map<String, String> headers;

            @JsonProperty("body")
            private String body;
        }

        @Data
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Request {
            @JsonProperty("file_id")
            private String fileId;

            @JsonProperty("request")
            private FileUploadAddress.Request request;

            @JsonProperty("response")
            private UploadResponse response;

            @JsonProperty("send_back_params")
            private Map<String, String> sendBackParams;
        }
    }
}
