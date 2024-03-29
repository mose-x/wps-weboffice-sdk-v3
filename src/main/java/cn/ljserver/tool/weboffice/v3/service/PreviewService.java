package cn.ljserver.tool.weboffice.v3.service;

import cn.ljserver.tool.weboffice.v3.model.DownloadInfo;
import cn.ljserver.tool.weboffice.v3.model.FileInfo;
import cn.ljserver.tool.weboffice.v3.model.UserPermission;

public interface PreviewService {
    FileInfo fetchFileInfo(String fileId);
    DownloadInfo fetchDownloadInfo(String fileId);

    UserPermission fetchUserPermission(String fileId);
}
