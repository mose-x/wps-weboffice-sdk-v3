package cn.ljserver.tool.weboffice.v3.model.convert;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.org.glassfish.gmbal.Description;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 格式转换结果回参-result明细
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DocToInfoResult {
    @JsonProperty("task")
    @Description("任务信息")
    private TaskInfo task;

    @JsonProperty("url")
    @Description("转换后地址")
    private String url;

    @JsonProperty("size")
    @Description("大小")
    private Long size;

    @JsonProperty("pdfs")
    @Description("pdf集合")
    private List<ResultInfo> pdfs;

    @JsonProperty("txts")
    @Description("txt集合")
    private List<ResultInfo> txts;

    @JsonProperty("images")
    @Description("图片集合")
    private List<ResultInfo> images;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TaskInfo {
        @JsonProperty("elapsed")
        @Description("耗时")
        private long elapsed;

        @JsonProperty("resource_size")
        @Description("源文件大小")
        private long resourceSize;
    }


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ResultInfo {
        @JsonProperty("url")
        @Description("转换后地址")
        private String url;

        @JsonProperty("size")
        @Description("大小")
        private Long size;
    }

}
