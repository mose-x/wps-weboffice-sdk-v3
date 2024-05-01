package cn.ljserver.tool.weboffice.v3.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notify {
    @JsonProperty("file_id")
    private String fileId;
    @JsonProperty("type")
    private String type;
    @JsonProperty("content")
    private Content content;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class Content {
        @JsonProperty("session_id")
        private String sessionId;

        @JsonProperty("init_version")
        private Integer initVersion;

        @JsonProperty("readonly")
        private Boolean readonly;

        @JsonProperty("uploaded_version")
        private Integer uploadedVersion;

        @JsonProperty("last_modifier_id")
        private String lastModifierId;

        @JsonProperty("connection_id")
        private String connectionId;

        @JsonProperty("user_id")
        private String userId;

        @JsonProperty("permission")
        private String permission;

        @JsonProperty("print")
        private Boolean print;

        @JsonProperty("format")
        private String format;
    }
}
