package cn.ljserver.tool.weboffice.v3.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.org.glassfish.gmbal.Description;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConvertInfo {
    @JsonProperty("task_id")
    @Description("转换任务id")
    private String taskId;
}
