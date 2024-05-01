package cn.ljserver.tool.weboffice.v3.service;

import cn.ljserver.tool.weboffice.v3.exception.NotImplementException;
import cn.ljserver.tool.weboffice.v3.model.DownloadInfo;
import cn.ljserver.tool.weboffice.v3.model.FileInfo;
import cn.ljserver.tool.weboffice.v3.model.Notify;
import cn.ljserver.tool.weboffice.v3.model.Watermark;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 额外扩展功能 -> 详见： <br>
 * <a href="https://solution.wps.cn/docs/callback/extend.html">wps web office 扩展能力</a>
 */
public interface ExtendCapacityService {

    /**
     * 文件重命名
     *
     * @param fileId 文件id
     * @param name   文件name <br>
     *               <a href = "https://solution.wps.cn/docs/callback/extend.html#文档重命名">-详见官方文档-</a>
     */
    default void renameFile(String fileId, String name) {
        throw new NotImplementException();
    }

    /**
     * 文档历史版本列表
     *
     * @param fileId 文件id
     * @param offset 偏移量
     * @param limit  限制数量 <br>
     *               <a href = "https://solution.wps.cn/docs/callback/extend.html#文档历史版本列表">-详见官方文档-</a>
     */
    default List<FileInfo> fileVersions(String fileId, int offset, int limit) {
        throw new NotImplementException();
    }

    /**
     * 获取文档指定历史版本
     *
     * @param fileId  文件id
     * @param version 版本号 <br>
     *                <a href = "https://solution.wps.cn/docs/callback/extend.html#获取文档指定历史版本">-详见官方文档-</a>
     */
    default FileInfo fileVersion(String fileId, int version) {
        throw new NotImplementException();
    }

    /**
     * 获取历史版本下载信息
     *
     * @param fileId  文件id
     * @param version 版本号 <br>
     *                <a href = "https://solution.wps.cn/docs/callback/extend.html#获取历史版本下载信息">-详见官方文档-</a>
     */
    default DownloadInfo fileVersionDownload(String fileId, int version) {
        throw new NotImplementException();
    }

    /**
     * 获取文件版本水印信息
     *
     * @param fileId 文件id <br>
     *               <a href = "https://solution.wps.cn/docs/callback/extend.html#文档水印">-详见官方文档-</a>
     */
    default Watermark fileWatermark(String fileId) {
        throw new NotImplementException();
    }

    /**
     * 事件通知
     *
     * @param notify 通知信息 <br>
     *               <a href = "https://solution.wps.cn/docs/callback/extend.html#%E4%BA%8B%E4%BB%B6%E9%80%9A%E7%9F%A5">-详见官方文档-</a>
     */
    default void notify(Notify notify) {
    }

    /**
     * 上传附件
     *
     * @param key   附件对象 ID <br>
     * @param name  附件名 <br>
     * @param files 附件实体 <br>
     *              <a href = "https://solution.wps.cn/docs/callback/extend.html#%E4%B8%8A%E4%BC%A0%E9%99%84%E4%BB%B6">-详见官方文档-</a>
     */
    default void uploadAttachment(String key, String name, MultipartFile files) {
    }

    /**
     * 事件通知
     *
     * @param key               附件对象 ID <br>
     * @param scaleMaxFitWidth  缩略图最大拟合宽度 <br>
     * @param scaleMaxFitHeight 缩略图最大拟合高度 <br>
     * @param scaleLongEdge     缩略图限定长边长度 <br>
     * @return 附件下载地址 <br> <br>
     * <a href = "https://solution.wps.cn/docs/callback/extend.html#%E8%8E%B7%E5%8F%96%E9%99%84%E4%BB%B6%E5%AF%B9%E8%B1%A1%E4%B8%8B%E8%BD%BD%E5%9C%B0%E5%9D%80">-详见官方文档-</a>
     */
    default String getAttachment(String key, int scaleMaxFitWidth, int scaleMaxFitHeight, int scaleLongEdge) {
        return "";
    }

    /**
     * 拷贝附件对象
     *
     * @param keyDict 附件对象 ID 键值对, 如<源附件对象 ID:目标附件对象 ID> <br>
     *                <a href = "https://solution.wps.cn/docs/callback/extend.html#%E6%8B%B7%E8%B4%9D%E9%99%84%E4%BB%B6%E5%AF%B9%E8%B1%A1">-详见官方文档-</a>
     */
    default void copyAttachment(Map<String, String> keyDict) {
    }
}
