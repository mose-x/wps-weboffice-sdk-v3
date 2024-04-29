package cn.ljserver.tool.weboffice.v3.model.convert;

import cn.ljserver.tool.weboffice.v3.util.FileUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.org.glassfish.gmbal.Description;
import lombok.*;

/**
 * 文档转换为TXT请求对象
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocToTxtRequest {
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

    @JsonProperty("long_txt")
    @Description("是否转换成长文本，设置为 true 时，可以将需要导出的页中的文字合并导出。默认值为 false，按页导出 txt")
    private boolean longTxt = false;

    @JsonProperty("hold_line_feed")
    @Description("转换 txt 时是否保留文档中的换行符，设置为 true 时，导出内容包含换行符。默认值为 false，导出内容不包含换行。")
    private boolean holdLineFeed = false;

    @JsonProperty("ranges")
    @Description("自定义需要转换的分页范围，例如：1,2-4,7，则表示转换文档的 1、2、4、7 页面 (与 from_page和to_page 互斥)")
    private String ranges;

    @JsonProperty("from_page")
    @Description("转换起始页，从 1 开始计数(与 ranges互斥)")
    private Integer fromPage;

    @JsonProperty("to_page")
    @Description("转换结束页，需要大于 from_page， (与 ranges互斥)")
    private Integer toPage;

    public DocToTxtRequest(@NonNull String url) {
        this.url = url;
        this.filename = FileUtils.name(url);
    }

    public DocToTxtRequest(@NonNull String url, @NonNull String filename) {
        this.url = url;
        this.filename = filename;
    }

    public DocToTxtRequest(@NonNull String url, @NonNull String filename, String password) {
        this.url = url;
        this.filename = filename;
        this.password = password;
    }
}
