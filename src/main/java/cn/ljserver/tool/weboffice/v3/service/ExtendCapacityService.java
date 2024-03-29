package cn.ljserver.tool.weboffice.v3.service;

import cn.ljserver.tool.weboffice.v3.exception.NotImplementException;
import cn.ljserver.tool.weboffice.v3.model.DownloadInfo;
import cn.ljserver.tool.weboffice.v3.model.FileInfo;
import cn.ljserver.tool.weboffice.v3.model.Watermark;

import java.util.List;

public interface ExtendCapacityService {
    default void renameFile(String fileId, String name) {
        throw new NotImplementException();
    }

    default List<FileInfo> fileVersions(String fileId, int offset, int limit) {
        throw new NotImplementException();
    }

    default FileInfo fileVersion(String fileId, int version) {
        throw new NotImplementException();
    }

    default DownloadInfo fileVersionDownload(String fileId, int version) {
        throw new NotImplementException();
    }

    default Watermark fileWatermark(String fileId) {
        throw new NotImplementException();
    }
}
