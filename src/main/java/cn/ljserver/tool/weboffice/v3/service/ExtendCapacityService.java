package cn.ljserver.tool.weboffice.v3.service;

import cn.ljserver.tool.weboffice.v3.exception.NotImplementException;
import cn.ljserver.tool.weboffice.v3.model.DownloadInfo;
import cn.ljserver.tool.weboffice.v3.model.FileInfo;
import cn.ljserver.tool.weboffice.v3.model.Watermark;

import java.util.List;

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
}
