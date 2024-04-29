package cn.ljserver.tool.weboffice.v3.model.convert;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 返回img、pdf转为文档回参对象
 * <br>
 * code : 状态码
 * <br>
 * data : 响应数据
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
