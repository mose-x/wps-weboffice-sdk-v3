package cn.ljserver.tool.weboffice.v3.model.convert;

import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.jfr.Description;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 格式转换结果回参明细
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocToInfo {
    @JsonProperty("status")
    @Description("状态")
    private String status;
    @JsonProperty("progress")
    @Description("进度")
    private int progress;
    @JsonProperty("message")
    @Description("转换任务失败原因")
    private String message;
    @JsonProperty("result")
    @Description("结果")
    private DocToInfoResult result;
}
