package cn.ljserver.tool.weboffice.v3.service.convert.docTo;

import cn.ljserver.tool.weboffice.v3.exception.InvalidArgument;
import cn.ljserver.tool.weboffice.v3.model.convert.ConvertResponse;
import cn.ljserver.tool.weboffice.v3.model.convert.DocToImgRequest;
import cn.ljserver.tool.weboffice.v3.util.ConvertUtils;
import cn.ljserver.tool.weboffice.v3.util.FileUtils;
import lombok.NonNull;

/**
 * 文档转换成 IMG 图片  -> 详见： <br>
 * <a href="https://solution.wps.cn/docs/convert/to-jpg.html">wps web office 官方文档-转为JPG</a>
 * <br>
 * <a href="https://solution.wps.cn/docs/convert/to-jpg.html">wps web office 官方文档-转为PNG</a>
 */
public class DocToImg {

    /**
     * 文档转TXT服务 -> 详见： <br>
     * <a href="https://solution.wps.cn/docs/convert/to-jpg.html">wps web office 官方文档-转为JPG</a>
     * <br>
     * <a href="https://solution.wps.cn/docs/convert/to-jpg.html">wps web office 官方文档-转为PNG</a>
     * <br>
     * -> 这里传递的是请求对象{@link DocToImgRequest}
     * <br>
     * imgType : 需要转换成图片的类型，目前只支持 jpg 和 png
     * <br>
     * 返回：{@link ConvertResponse}
     */
    public static ConvertResponse convert(DocToImgRequest r, @NonNull String imgType) {
        checkUrl(r.getUrl(), imgType);
        String uri = "";
        if (FileUtils.JPG.equals(imgType)) uri = "/api/developer/v1/office/convert/to/jpg";
        if (FileUtils.PNG.equals(imgType)) uri = "/api/developer/v1/office/convert/to/png";
        return ConvertUtils.post(uri, r, ConvertResponse.class);
    }

    /**
     * 文档转TXT服务 -> 详见： <br>
     * <a href="https://solution.wps.cn/docs/convert/to-jpg.html">wps web office 官方文档-转为JPG</a>
     * <br>
     * <a href="https://solution.wps.cn/docs/convert/to-jpg.html">wps web office 官方文档-转为PNG</a>
     * <br>
     * -> 这里传递的是文件下载连接，名称不传递可自动获取url上的名称
     * <br>
     * imgType : 需要转换成图片的类型，目前只支持 jpg 和 png
     * <br>
     * 返回：{@link ConvertResponse}
     */
    public static ConvertResponse convert(String url, String imgType) {
        return convert(new DocToImgRequest(url), imgType);
    }

    /**
     * 文档转TXT服务 -> 详见： <br>
     * <a href="https://solution.wps.cn/docs/convert/to-jpg.html">wps web office 官方文档-转为JPG</a>
     * <br>
     * <a href="https://solution.wps.cn/docs/convert/to-jpg.html">wps web office 官方文档-转为PNG</a>
     * <br>
     * -> 这里传递的是文件下载连接 和 文件名称
     * <br>
     * imgType : 需要转换成图片的类型，目前只支持 jpg 和 png
     * <br>
     * 返回：{@link ConvertResponse}
     */
    public static ConvertResponse convert(String url, String fileName, String imgType) {
        return convert(new DocToImgRequest(url, fileName), imgType);
    }

    /**
     * 文档转TXT服务 -> 详见： <br>
     * <a href="https://solution.wps.cn/docs/convert/to-jpg.html">wps web office 官方文档-转为JPG</a>
     * <br>
     * <a href="https://solution.wps.cn/docs/convert/to-jpg.html">wps web office 官方文档-转为PNG</a>
     * <br>
     * -> 这里传递的是文件下载连接 、 文件名称 、 文件加密密码（如果存在密码，必须传递）
     * <br>
     * imgType : 需要转换成图片的类型，目前只支持 jpg 和 png
     * <br>
     * 返回：{@link ConvertResponse}
     */
    public static ConvertResponse convert(String url, String fileName, String password, String imgType) {
        return convert(new DocToImgRequest(url, fileName, password), imgType);
    }

    /**
     * 校验文件url是否为支持的文档类型
     * @param url 文件下载地址
     */
    private static void checkUrl(String url, String imgType){
        if (url == null || url.isEmpty() || imgType.isEmpty()) throw new InvalidArgument();
        FileUtils.typeMatchCheck(FileUtils.imgTypes, imgType);
        String fileType = FileUtils.suffix(url);
        FileUtils.typeMatchCheck(FileUtils.documentConvertTypes, fileType);
    }
}
