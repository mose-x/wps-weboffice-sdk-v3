package cn.ljserver.tool.weboffice.v3.controller;

import cn.ljserver.tool.weboffice.v3.exception.InvalidArgument;
import cn.ljserver.tool.weboffice.v3.exception.NotImplementException;
import cn.ljserver.tool.weboffice.v3.model.*;
import cn.ljserver.tool.weboffice.v3.service.ExtendCapacityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@RestController
@ResponseBody
@RequestMapping("/v3/3rd/files")
public class ExtendCapacityController extends ProviderBaseController {

    @PutMapping("/{file_id}/name")
    @ProviderJsonApi
    public ProviderResponseEntity<Map<String, String>> fileRename(@PathVariable("file_id") String fileId,
                                                                  @RequestBody FileRenameRequest request) {
        final String name = request.getName();
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidArgument("new filename is empty");
        }
        getServiceOrThrow().renameFile(fileId, name);
        return ProviderResponseEntity.ok(Collections.emptyMap());
    }

    @GetMapping("/{file_id}/versions")
    @ProviderJsonApi
    public ProviderResponseEntity<List<FileInfo>> fileVersions(@PathVariable("file_id") String fileId,
                                                               @RequestParam(value = "offset", required = false, defaultValue = "0") int offset,
                                                               @RequestParam(value = "limit", required = false, defaultValue = "100") int limit) {
        return ProviderResponseEntity.ok(this.getServiceOrThrow().fileVersions(fileId, offset, limit));
    }

    @GetMapping("/{file_id}/versions/{version}")
    @ProviderJsonApi
    public ProviderResponseEntity<FileInfo> fileVersion(@PathVariable("file_id") String fileId,
                                                        @PathVariable("version") int version) {
        return ProviderResponseEntity.ok(this.getServiceOrThrow().fileVersion(fileId, version));
    }

    @GetMapping("/{file_id}/versions/{version}/download")
    @ProviderJsonApi
    public ProviderResponseEntity<DownloadInfo> fileVersionDownload(@PathVariable("file_id") String fileId,
                                                                    @PathVariable("version") int version) {
        return ProviderResponseEntity.ok(this.getServiceOrThrow().fileVersionDownload(fileId, version));
    }

    @GetMapping("/{file_id}/watermark")
    @ProviderJsonApi
    public ProviderResponseEntity<Watermark> fileWatermark(@PathVariable("file_id") String fileId) {
        return ProviderResponseEntity.ok(this.getServiceOrThrow().fileWatermark(fileId));
    }

    private ExtendCapacityService service;

    @Autowired(required = false)
    public void setService(ExtendCapacityService service) {
        this.service = service;
    }

    private ExtendCapacityService getServiceOrThrow() {
        if (Objects.isNull(this.service)) {
            throw new NotImplementException(getRequestPath());
        }
        return this.service;
    }
}
