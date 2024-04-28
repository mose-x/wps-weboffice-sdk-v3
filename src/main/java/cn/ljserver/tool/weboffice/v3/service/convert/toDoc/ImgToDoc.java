package cn.ljserver.tool.weboffice.v3.service.convert.toDoc;

import cn.ljserver.tool.weboffice.v3.exception.InvalidArgument;
import cn.ljserver.tool.weboffice.v3.model.convert.ConvertResponse;
import cn.ljserver.tool.weboffice.v3.model.convert.ImgToDocRequest;
import cn.ljserver.tool.weboffice.v3.util.ConvertUtils;
import cn.ljserver.tool.weboffice.v3.util.FileUtils;

import java.util.List;

public class ImgToDoc {
    public static ConvertResponse convert(String officeType, ImgToDocRequest r) {
        FileUtils.typeMatchCheck(FileUtils.convertToDocumentTypes, officeType);
        if (r.getImgUrls() == null || r.getImgUrls().length == 0) throw new InvalidArgument();
        String uri = "/api/developer/v1/office/img/convert/to/" + officeType.toLowerCase();
        return ConvertUtils.post(uri, r, ConvertResponse.class);
    }

    public static ConvertResponse convert(String officeType, String[] url) {
        if (url == null || url.length == 0) throw new InvalidArgument();
        return convert(officeType, new ImgToDocRequest(url));
    }

    public static ConvertResponse convert(String officeType, List<String> url) {
        if (url == null || url.isEmpty()) throw new InvalidArgument();
        return convert(officeType, new ImgToDocRequest(url));
    }
}
