package cn.ljserver.tool.weboffice.v3.service.convert.toDoc;

import cn.ljserver.tool.weboffice.v3.exception.InvalidArgument;
import cn.ljserver.tool.weboffice.v3.model.convert.ConvertResponse;
import cn.ljserver.tool.weboffice.v3.model.convert.ImgToDocRequest;
import cn.ljserver.tool.weboffice.v3.util.ConvertUtils;
import cn.ljserver.tool.weboffice.v3.util.FileUtils;
import lombok.SneakyThrows;

public class ImgToDoc {
    @SneakyThrows
    public static ConvertResponse convert(String officeType, ImgToDocRequest r) {
        FileUtils.typeMatchCheck(FileUtils.convertToDocumentTypes, officeType);
        if (r.getImgUrls() == null || r.getImgUrls().length == 0) throw new InvalidArgument();
        String uri = "/api/developer/v1/office/img/convert/to/" + officeType.toLowerCase();
        return ConvertUtils.post(uri, r, ConvertResponse.class);
    }
}
