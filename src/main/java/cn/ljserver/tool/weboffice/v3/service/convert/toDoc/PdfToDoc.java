package cn.ljserver.tool.weboffice.v3.service.convert.toDoc;

import cn.ljserver.tool.weboffice.v3.exception.InvalidArgument;
import cn.ljserver.tool.weboffice.v3.model.convert.ConvertResponse;
import cn.ljserver.tool.weboffice.v3.model.convert.PdfToDocRequest;
import cn.ljserver.tool.weboffice.v3.util.ConvertUtils;
import cn.ljserver.tool.weboffice.v3.util.FileUtils;

public class PdfToDoc {

    public static ConvertResponse convert(String officeType, PdfToDocRequest r) {
        FileUtils.typeMatchCheck(FileUtils.convertToDocumentTypes, officeType);
        if (r == null || r.getUrl().isEmpty()) throw new InvalidArgument();
        String uri = "/api/developer/v1/office/pdf/convert/to/" + officeType.toLowerCase();
        return ConvertUtils.post(uri, r, ConvertResponse.class);
    }

    public static ConvertResponse convert(String officeType, String url) {
        if (url == null || url.isEmpty()) throw new InvalidArgument();
        return convert(officeType, new PdfToDocRequest(url));
    }
}
