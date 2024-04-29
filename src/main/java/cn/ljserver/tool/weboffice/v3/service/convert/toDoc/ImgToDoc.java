package cn.ljserver.tool.weboffice.v3.service.convert.toDoc;

import cn.ljserver.tool.weboffice.v3.exception.InvalidArgument;
import cn.ljserver.tool.weboffice.v3.model.convert.ConvertResponse;
import cn.ljserver.tool.weboffice.v3.model.convert.ImgToDocRequest;
import cn.ljserver.tool.weboffice.v3.util.ConvertUtils;
import cn.ljserver.tool.weboffice.v3.util.FileUtils;

import java.util.Collections;
import java.util.List;

/**
 * 图片转文档服务 -> 详见： <br>
 * <a href="https://solution.wps.cn/docs/convert/img-to-docs.html">wps web office 图片转文档</a>
 */
public class ImgToDoc {

    /**
     * 图片转文档服务 -> 详见： <br>
     * <a href="https://solution.wps.cn/docs/convert/img-to-docs.html">wps web office 图片转文档</a>
     * <br>
     * -> 这里传递的是请求对象{@link ImgToDocRequest}
     * <br>
     * 返回：{@link ConvertResponse}
     */
    public static ConvertResponse convert(String officeType, ImgToDocRequest r) {
        checkUrl(officeType, r.getImgUrls());
        String uri = "/api/developer/v1/office/img/convert/to/" + officeType.toLowerCase();
        return ConvertUtils.post(uri, r, ConvertResponse.class);
    }

    /**
     * 图片转文档服务 -> 详见： <br>
     * <a href="https://solution.wps.cn/docs/convert/img-to-docs.html">wps web office 图片转文档</a>
     * <br>
     * -> 这里传递的是String[]数组{@link java.util.Arrays}
     * <br>
     * 返回：{@link ConvertResponse}
     */
    public static ConvertResponse convert(String officeType, String[] url) {
        if (url == null || url.length == 0) throw new InvalidArgument();
        return convert(officeType, new ImgToDocRequest(url));
    }

    /**
     * 图片转文档服务 -> 详见： <br>
     * <a href="https://solution.wps.cn/docs/convert/img-to-docs.html">wps web office 图片转文档</a>
     * <br>
     * -> 这里传递的是List string数组{@link List<String>}
     * <br>
     * 返回：{@link ConvertResponse}
     */
    public static ConvertResponse convert(String officeType, List<String> url) {
        if (url == null || url.isEmpty()) throw new InvalidArgument();
        return convert(officeType, new ImgToDocRequest(url));
    }

    /**
     * 图片转文档服务 -> 详见： <br>
     * <a href="https://solution.wps.cn/docs/convert/img-to-docs.html">wps web office 图片转文档</a>
     * <br>
     * <p style="color:red;">-> 注意：这里传递的是字符串，即单张图片地址{@link String}</p>
     * 返回：{@link ConvertResponse}
     */
    public static ConvertResponse convert(String officeType, String url) {
        if (url == null || url.isEmpty()) throw new InvalidArgument();
        return convert(officeType, new ImgToDocRequest(Collections.singletonList(url)));
    }

    /**
     * 校验url文件格式是否合规
     */
    private static void checkUrl(String officeType, String[] url) {
        if (url == null || url.length == 0) throw new InvalidArgument();
        FileUtils.typeMatchCheck(FileUtils.convertToDocumentTypes, officeType);
        for (String s : url) {
            FileUtils.typeMatchCheck(FileUtils.imgConvertToDocumentTypes, s);
        }
    }
}
