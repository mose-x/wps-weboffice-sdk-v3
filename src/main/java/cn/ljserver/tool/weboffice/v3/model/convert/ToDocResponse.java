package cn.ljserver.tool.weboffice.v3.model.convert;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * img、pdf转为文档回参对象
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ToDocResponse {

    /**
     * 响应状态码
     */
    @JsonProperty("code")
    private int code;

    /**
     * 响应数据
     */
    @JsonProperty("data")
    private ToDocInfo data;
}
