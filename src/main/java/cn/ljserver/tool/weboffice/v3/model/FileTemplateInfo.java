package cn.ljserver.tool.weboffice.v3.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * 文件模板
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FileTemplateInfo {

    @NonNull
    @JsonProperty("url")
    private String url;

    @NonNull
    @JsonProperty("name")
    private String name;

}
