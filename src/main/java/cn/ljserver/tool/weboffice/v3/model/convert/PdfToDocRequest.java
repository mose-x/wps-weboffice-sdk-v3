package cn.ljserver.tool.weboffice.v3.model.convert;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.org.glassfish.gmbal.Description;
import lombok.*;

/**
 * pdf转document请求参数
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PdfToDocRequest {
    @NonNull
    @JsonProperty("url")
    @Description("文档下载地址(pdf url ,根据后缀判断.pdf)")
    private String url;

    @JsonProperty("page_num_begin")
    @Description("转换起始页，起始值为1。eg: [1, 3] 转换1~3页，都为0转换整个文档。")
    private Integer pageNumBegin = 0;

    @JsonProperty("page_num_end")
    @Description("转换的结束页, 配合 page_num_begin 使用")
    private Integer pageNumEnd = 0;

    @JsonProperty("text_unify")
    @Description("统一段落字体字号，建议传true")
    private boolean textUnify = true;

    @JsonProperty("sheet_option")
    @Description("转换为表格时配置：sheet转换方式 0表示每页pdf(每页图片)一个sheet, 1表示所有页面（图片）转到一个sheet中， 默认为0")
    private Integer sheetOption = 0;

    public PdfToDocRequest(@NonNull String url) {
        this.url = url;
    }
}
