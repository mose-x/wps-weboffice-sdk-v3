package cn.ljserver.tool.weboffice.v3.service;

import cn.ljserver.tool.weboffice.v3.model.FileInfo;
import cn.ljserver.tool.weboffice.v3.model.FileUploadSinglePhase;

/**
 * 文档编辑-单阶段提交 -> 详见： <br>
 * <a href="https://solution.wps.cn/docs/callback/save.html#单阶段提交">wps web office 文档编辑</a>
 */
public interface SinglePhaseFileStorageService {

    /**
     * 单阶段保存 - 上传文件
     *
     * @param request 上传的文件 <br>
     *                <a href = "https://solution.wps.cn/docs/callback/save.html#单阶段提交">-详见官方文档-</a>
     */
    FileInfo uploadFile(FileUploadSinglePhase.Request request);
}

