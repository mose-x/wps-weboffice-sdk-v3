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

@RestController
@ResponseBody
@RequestMapping("/v3/3rd/files")
public class FileStorageController extends ProviderBaseController {

    @GetMapping("/{file_id}/upload/prepare")
    @ProviderJsonApi
    public ProviderResponseEntity<Map<String, Object>> uploadPrepare(@PathVariable("file_id") String fileId) {
        return ProviderResponseEntity.ok(Collections
                .singletonMap("digest_types", this.getMultiPhaseServiceOrThrow().uploadPrepare(fileId)));
    }

    @PostMapping("/{file_id}/upload/address")
    public ProviderResponseEntity<FileUploadMultiPhase.FileUploadAddress.Response> uploadAddress(@PathVariable("file_id") String fileId,
                                                                                                 @RequestBody FileUploadMultiPhase.FileUploadAddress.Request request) {
        request.setFileId(fileId);
        return ProviderResponseEntity.ok(this.getMultiPhaseServiceOrThrow().uploadAddress(request));
    }

    @PostMapping("/{file_id}/upload/complete")
    @ProviderJsonApi
    public ProviderResponseEntity<FileInfo> uploadComplete(@PathVariable("file_id") String fileId,
                                                           @RequestBody FileUploadMultiPhase.FileUploadComplete.Request request) {
        request.setFileId(fileId);
        return ProviderResponseEntity.ok(this.getMultiPhaseServiceOrThrow().uploadComplete(request));
    }


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
            throw new NotImplementException(String.format("request path %s not implement", getRequestPath()));
        }
        return this.multiPhase;
    }

    private SinglePhaseFileStorageService getSinglePhaseServiceOrThrow() {
        if (Objects.isNull(this.singlePhase)) {
            throw new NotImplementException(String.format("request path %s not implement", getRequestPath()));
        }
        return this.singlePhase;
    }
}
