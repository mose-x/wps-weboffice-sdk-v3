package cn.ljserver.tool.weboffice.v3.service.convert.toDoc;

import cn.ljserver.tool.weboffice.v3.exception.InvalidArgument;
import cn.ljserver.tool.weboffice.v3.model.convert.ConvertResponse;
import cn.ljserver.tool.weboffice.v3.model.convert.PdfToDocRequest;
import cn.ljserver.tool.weboffice.v3.util.ConvertUtils;
import cn.ljserver.tool.weboffice.v3.util.FileUtils;

/**
 * PDF转文档服务 -> 详见： <br>
 * <a href="https://solution.wps.cn/docs/convert/pdf-to-docs.html">wps web office PDF转文档</a>
 */
public class PdfToDoc {

    /**
     * PDF转文档服务 -> 详见： <br>
     * <a href="https://solution.wps.cn/docs/convert/pdf-to-docs.html">wps web office PDF转文档</a>
     * <br>
     * -> 这里传递的是请求对象{@link PdfToDocRequest}
     * <br>
     * 返回：{@link ConvertResponse}
     */
    public static ConvertResponse convert(String officeType, PdfToDocRequest r) {
        checkUrl(officeType, r.getUrl());
        String uri = "/api/developer/v1/office/pdf/convert/to/" + officeType.toLowerCase();
        return ConvertUtils.post(uri, r, ConvertResponse.class);
    }

    /**
     * PDF转文档服务 -> 详见： <br>
     * <a href="https://solution.wps.cn/docs/convert/pdf-to-docs.html">wps web office PDF转文档</a>
     * <br>
     * -> 这里传递的是PDF的网络文件路径{@link String}
     * <br>
     * 返回：{@link ConvertResponse}
     */
    public static ConvertResponse convert(String officeType, String url) {
        if (url == null || url.isEmpty()) throw new InvalidArgument();
        return convert(officeType, new PdfToDocRequest(url));
    }

    /**
     * 校验url文件格式是否合规
     */
    private static void checkUrl(String officeType, String url) {
        if (url == null || url.isEmpty()) throw new InvalidArgument();
        if (!"pdf".equalsIgnoreCase(FileUtils.suffix(url))) throw new InvalidArgument("url file is not a pdf file !");
        FileUtils.typeMatchCheck(FileUtils.convertToDocumentTypes, officeType);
    }
}
