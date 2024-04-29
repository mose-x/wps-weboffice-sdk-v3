package cn.ljserver.tool.weboffice.v3.model.convert;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.org.glassfish.gmbal.Description;
import lombok.*;

/**
 * 文档转换基础类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OfficeToInfo {
    @NonNull
    @JsonProperty("url")
    @Description("文档下载地址")
    public String url;

    @NonNull
    @JsonProperty("filename")
    @Description("文档名称，包含扩展名，例如： 文字文稿.docx")
    public String filename;

    @JsonProperty("password")
    @Description("文档打开密码(如果文档有加密，该项则必填)")
    public String password;
}
