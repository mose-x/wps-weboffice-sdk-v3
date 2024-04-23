package cn.ljserver.tool.weboffice.v3.service;

import cn.ljserver.tool.weboffice.v3.model.DigestType;
import cn.ljserver.tool.weboffice.v3.model.FileInfo;
import cn.ljserver.tool.weboffice.v3.model.FileUploadMultiPhase;

import java.util.List;

/**
 * 文档编辑-三阶段保存 -> 详见： <br>
 * <a href="https://solution.wps.cn/docs/callback/save.html#三阶段保存">wps web office 文档编辑</a> <br>
 * your need to implement all these methods
 */
public interface MultiPhaseFileStorageService {

    /**
     * 三阶段保存 - 准备上传阶段
     *
     * @param fileId 文件id <br>
     *               <a href = "https://solution.wps.cn/docs/callback/save.html#准备上传阶段">-详见官方文档-</a>
     */
    List<DigestType> uploadPrepare(String fileId);

    /**
     * 三阶段保存 - 获取上传地址
     *
     * @param request 上传的文件 <br>
     *                <a href = "https://solution.wps.cn/docs/callback/save.html#获取上传地址">-详见官方文档-</a>
     */
    FileUploadMultiPhase.FileUploadAddress.Response uploadAddress(FileUploadMultiPhase.FileUploadAddress.Request request);


    /**
     * 三阶段保存 - 上传完成后，回调通知上传结果
     *
     * @param request 上传的文件 <br>
     *                <a href = "https://solution.wps.cn/docs/callback/save.html#上传完成后，回调通知上传结果">-详见官方文档-</a>
     */
    FileInfo uploadComplete(FileUploadMultiPhase.FileUploadComplete.Request request);
}


