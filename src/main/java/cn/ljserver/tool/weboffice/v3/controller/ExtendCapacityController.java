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

/**
 * 额外扩展功能 -> 详见： <br>
 * <a href="https://solution.wps.cn/docs/callback/extend.html">wps web office 扩展能力</a>
 */
@RestController
@RequestMapping("/v3/3rd/files")
public class ExtendCapacityController extends ProviderBaseController {

    /**
     * 文件重命名
     *
     * @param fileId  文件id
     * @param request 请求参数 <br>
     *                <a href = "https://solution.wps.cn/docs/callback/extend.html#文档重命名">-详见官方文档-</a>
     */
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

    /**
     * 文档历史版本列表
     *
     * @param fileId 文件id
     * @param offset 偏移量
     * @param limit  限制数量 <br>
     *               <a href = "https://solution.wps.cn/docs/callback/extend.html#文档历史版本列表">-详见官方文档-</a>
     */
    @GetMapping("/{file_id}/versions")
    @ProviderJsonApi
    public ProviderResponseEntity<List<FileInfo>> fileVersions(@PathVariable("file_id") String fileId,
                                                               @RequestParam(value = "offset", required = false, defaultValue = "0") int offset,
                                                               @RequestParam(value = "limit", required = false, defaultValue = "100") int limit) {
        return ProviderResponseEntity.ok(this.getServiceOrThrow().fileVersions(fileId, offset, limit));
    }

    /**
     * 获取文档指定历史版本
     *
     * @param fileId  文件id
     * @param version 版本号 <br>
     *                <a href = "https://solution.wps.cn/docs/callback/extend.html#获取文档指定历史版本">-详见官方文档-</a>
     */
    @GetMapping("/{file_id}/versions/{version}")
    @ProviderJsonApi
    public ProviderResponseEntity<FileInfo> fileVersion(@PathVariable("file_id") String fileId,
                                                        @PathVariable("version") int version) {
        return ProviderResponseEntity.ok(this.getServiceOrThrow().fileVersion(fileId, version));
    }

    /**
     * 获取历史版本下载信息
     *
     * @param fileId  文件id
     * @param version 版本号 <br>
     *                <a href = "https://solution.wps.cn/docs/callback/extend.html#获取历史版本下载信息">-详见官方文档-</a>
     */
    @GetMapping("/{file_id}/versions/{version}/download")
    @ProviderJsonApi
    public ProviderResponseEntity<DownloadInfo> fileVersionDownload(@PathVariable("file_id") String fileId,
                                                                    @PathVariable("version") int version) {
        return ProviderResponseEntity.ok(this.getServiceOrThrow().fileVersionDownload(fileId, version));
    }

    /**
     * 获取文件版本水印信息
     *
     * @param fileId 文件id <br>
     *               <a href = "https://solution.wps.cn/docs/callback/extend.html#文档水印">-详见官方文档-</a>
     */
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
            String msg = String.format("request path %s not implement with interface class %s ", getRequestPath(), "ExtendCapacityService");
            throw new NotImplementException(msg);
        }
        return this.service;
    }
}
