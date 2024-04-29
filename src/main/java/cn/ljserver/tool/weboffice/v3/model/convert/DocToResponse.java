package cn.ljserver.tool.weboffice.v3.model.convert;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 格式转换结果回参对象
 * <br>
 * code : 状态码
 * <br>
 * data : 响应数据{@link DocToInfo}
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocToResponse {

    /**
     * 响应状态码
     */
    @JsonProperty("code")
    private int code;

    /**
     * 响应数据
     */
    @JsonProperty("data")
    private DocToInfo data;
}
