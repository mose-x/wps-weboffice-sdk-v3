package cn.ljserver.tool.weboffice.v3.controller;

import cn.ljserver.tool.weboffice.v3.model.DownloadInfo;
import cn.ljserver.tool.weboffice.v3.model.FileInfo;
import cn.ljserver.tool.weboffice.v3.model.ProviderResponseEntity;
import cn.ljserver.tool.weboffice.v3.model.UserPermission;
import cn.ljserver.tool.weboffice.v3.service.PreviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
@RequestMapping("/v3/3rd/files")
public class PreviewController extends ProviderBaseController {

    @GetMapping("/{file_id}")
    @ProviderJsonApi
    public ProviderResponseEntity<FileInfo> fetchFile(@PathVariable("file_id") final String fileId) {
        return ProviderResponseEntity.ok(this.previewService.fetchFileInfo(fileId));
    }

    @GetMapping("/{file_id}/download")
    @ProviderJsonApi
    public ProviderResponseEntity<DownloadInfo> fetchDownloadInfo(@PathVariable("file_id") final String fileId) {
        return ProviderResponseEntity.ok(this.previewService.fetchDownloadInfo(fileId));
    }

    @GetMapping("/{file_id}/permission")
    @ProviderJsonApi
    public ProviderResponseEntity<UserPermission> fetchUserPermission(@PathVariable("file_id") final String fileId) {
        return ProviderResponseEntity.ok(this.previewService.fetchUserPermission(fileId));
    }

    private PreviewService previewService;

    @Autowired(required = false)
    private void setPreviewService(PreviewService previewService) {
        this.previewService = previewService;
    }
}
