package cn.ljserver.tool.weboffice.v3.model.convert;

import cn.ljserver.tool.weboffice.v3.util.FileUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.org.glassfish.gmbal.Description;
import lombok.*;

/**
 * 文档转为pdf请求对象
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DocToPdfRequest extends OfficeToInfo {

    @JsonProperty("ranges")
    @Description("自定义需要转换的分页范围，例如：1,2-4,7，则表示转换文档的 1、2、4、7 页面 (与 from_page和to_page 互斥)")
    private String ranges;

    @JsonProperty("from_page")
    @Description("转换起始页，从 1 开始计数(与 ranges互斥)")
    private Integer fromPage;

    @JsonProperty("to_page")
    @Description("转换结束页，需要大于 from_page， (与 ranges互斥)")
    private Integer toPage;

    @JsonProperty("show_comments")
    @Description("是否显示批注。默认值为 false，不显示批注")
    private boolean showComments = false;

    public DocToPdfRequest(@NonNull String url) {
        this.url = url;
        this.filename = FileUtils.name(url);
    }

    public DocToPdfRequest(@NonNull String url, @NonNull String filename) {
        this.url = url;
        this.filename = filename;
    }

    public DocToPdfRequest(@NonNull String url, @NonNull String filename, String password) {
        this.url = url;
        this.filename = filename;
        this.password = password;
    }
}
