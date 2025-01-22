package cn.ljserver.tool.weboffice.v3.model.convert;

import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.jfr.Description;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 转为文档详细信息
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ToDocInfo {
    @JsonProperty("download_url")
    @Description("下载连接")
    private String downloadUrl;

    @JsonProperty("status")
    @Description("状态")
    private int status;

    @JsonProperty("duration")
    @Description("用时时间")
    private double duration;

    @JsonProperty("task_id")
    @Description("任务id")
    private String taskId;

    @JsonProperty("progress")
    @Description("进度")
    private int progress;

    @JsonProperty("start_time")
    @Description("开始时间")
    private long startTime;

    @JsonProperty("page_count")
    @Description("页数")
    private int pageCount;

    @JsonProperty("errMsgs")
    @Description("错误信息")
    private String errMsgs;
}
