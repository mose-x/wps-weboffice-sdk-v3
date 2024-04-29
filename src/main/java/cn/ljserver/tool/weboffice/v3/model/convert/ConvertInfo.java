package cn.ljserver.tool.weboffice.v3.model.convert;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.org.glassfish.gmbal.Description;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 转换结果 - 任务id  -- taskId
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ConvertInfo {
    @JsonProperty("task_id")
    @Description("转换任务id")
    private String taskId;
}
