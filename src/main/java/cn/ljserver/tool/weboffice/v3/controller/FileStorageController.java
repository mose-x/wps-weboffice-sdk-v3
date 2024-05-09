package cn.ljserver.tool.weboffice.v3.controller;

import cn.ljserver.tool.weboffice.v3.exception.NotImplementException;
import cn.ljserver.tool.weboffice.v3.model.FileInfo;
import cn.ljserver.tool.weboffice.v3.model.FileUploadMultiPhase;
import cn.ljserver.tool.weboffice.v3.model.FileUploadSinglePhase;
import cn.ljserver.tool.weboffice.v3.model.ProviderResponseEntity;
import cn.ljserver.tool.weboffice.v3.service.MultiPhaseFileStorageService;
import cn.ljserver.tool.weboffice.v3.service.SinglePhaseFileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;


/**
 * 文档编辑 -> 详见： <br>
 * <a href="https://solution.wps.cn/docs/callback/save.html">wps web office 文档编辑</a>
 */
@RestController
@RequestMapping("/v3/3rd/files")
public class FileStorageController extends ProviderBaseController {

    /**
     * 三阶段保存 - 准备上传阶段
     *
     * @param fileId 文件id <br>
     *               <a href = "https://solution.wps.cn/docs/callback/save.html#准备上传阶段">-详见官方文档-</a>
     */
    @GetMapping("/{file_id}/upload/prepare")
    @ProviderJsonApi
    public ProviderResponseEntity<Map<String, Object>> uploadPrepare(@PathVariable("file_id") String fileId) {
        return ProviderResponseEntity.ok(Collections
                .singletonMap("digest_types", this.getMultiPhaseServiceOrThrow().uploadPrepare(fileId)));
    }

    /**
     * 三阶段保存 - 获取上传地址
     *
     * @param fileId  文件id
     * @param request 上传的文件 <br>
     *                <a href = "https://solution.wps.cn/docs/callback/save.html#获取上传地址">-详见官方文档-</a>
     */
    @PostMapping("/{file_id}/upload/address")
    public ProviderResponseEntity<FileUploadMultiPhase.FileUploadAddress.Response> uploadAddress(@PathVariable("file_id") String fileId,
                                                                                                 @RequestBody FileUploadMultiPhase.FileUploadAddress.Request request) {
        request.setFileId(fileId);
        return ProviderResponseEntity.ok(this.getMultiPhaseServiceOrThrow().uploadAddress(request));
    }

    /**
     * 三阶段保存 - 上传完成后，回调通知上传结果
     *
     * @param fileId  文件id
     * @param request 上传的文件 <br>
     *                <a href = "https://solution.wps.cn/docs/callback/save.html#上传完成后，回调通知上传结果">-详见官方文档-</a>
     */
    @PostMapping("/{file_id}/upload/complete")
    @ProviderJsonApi
    public ProviderResponseEntity<FileInfo> uploadComplete(@PathVariable("file_id") String fileId,
                                                           @RequestBody FileUploadMultiPhase.FileUploadComplete.Request request) {
        request.setFileId(fileId);
        return ProviderResponseEntity.ok(this.getMultiPhaseServiceOrThrow().uploadComplete(request));
    }

    /**
     * 单阶段保存 - 上传文件
     *
     * @param fileId  文件id
     * @param request 上传的文件 <br>
     *                <a href = "https://solution.wps.cn/docs/callback/save.html#单阶段提交">-详见官方文档-</a>
     */
    @PostMapping(value = "/{file_id}/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ProviderJsonApi
    public ProviderResponseEntity<FileInfo> uploadFile(@PathVariable("file_id") String fileId,
                                                       @ModelAttribute FileUploadSinglePhase.Request request) {
        request.setFileId(fileId);
        return ProviderResponseEntity.ok(this.getSinglePhaseServiceOrThrow().uploadFile(request));
    }

    private MultiPhaseFileStorageService multiPhase;
    private SinglePhaseFileStorageService singlePhase;

    @Autowired(required = false)
    private void setMultiPhase(MultiPhaseFileStorageService multiPhase) {
        this.multiPhase = multiPhase;
    }

    @Autowired(required = false)
    private void setSinglePhase(SinglePhaseFileStorageService singlePhase) {
        this.singlePhase = singlePhase;
    }

    private MultiPhaseFileStorageService getMultiPhaseServiceOrThrow() {
        if (Objects.isNull(this.multiPhase)) {
            String msg = String.format("request path %s not implement with interface class %s ", getRequestPath(), "MultiPhaseFileStorageService");
            throw new NotImplementException(msg);
        }
        return this.multiPhase;
    }

    private SinglePhaseFileStorageService getSinglePhaseServiceOrThrow() {
        if (Objects.isNull(this.singlePhase)) {
            String msg = String.format("request path %s not implement with interface class %s ", getRequestPath(), "SinglePhaseFileStorageService");
            throw new NotImplementException(msg);
        }
        return this.singlePhase;
    }
}
