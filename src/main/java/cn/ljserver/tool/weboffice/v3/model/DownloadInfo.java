package cn.ljserver.tool.weboffice.v3.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Map;

/**
 * @ClassName: DownloadInfo
 * @Description: 下载信息
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DownloadInfo {
    @NonNull
    @JsonProperty("url")
    private String url;

    @JsonProperty("digest_type")
    private DigestType digestType;

    @JsonProperty("digest")
    private String digestValue;

    @JsonProperty(value = "headers")
    private Map<String, String> headers;
}
