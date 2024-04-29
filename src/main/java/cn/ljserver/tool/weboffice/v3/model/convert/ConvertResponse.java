package cn.ljserver.tool.weboffice.v3.model.convert;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 返回转换响应数据
 * <br>
 * code : 状态码
 * <br>
 * data : 响应数据{@link ConvertInfo}
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ConvertResponse {

    /**
     * 响应状态码
     */
    @JsonProperty("code")
    private int code;

    /**
     * 响应数据
     */
    @JsonProperty("data")
    private ConvertInfo data;
}
