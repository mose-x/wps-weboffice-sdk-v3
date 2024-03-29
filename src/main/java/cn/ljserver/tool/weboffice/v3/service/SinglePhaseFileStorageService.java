package cn.ljserver.tool.weboffice.v3.service;

import cn.ljserver.tool.weboffice.v3.model.FileInfo;
import cn.ljserver.tool.weboffice.v3.model.FileUploadSinglePhase;

public interface SinglePhaseFileStorageService {
    FileInfo uploadFile(FileUploadSinglePhase.Request request);
}

