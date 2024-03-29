package cn.ljserver.tool.weboffice.v3.service;

import cn.ljserver.tool.weboffice.v3.model.DigestType;
import cn.ljserver.tool.weboffice.v3.model.FileInfo;
import cn.ljserver.tool.weboffice.v3.model.FileUploadMultiPhase;

import java.util.List;

// your need to implement all these methods
public interface MultiPhaseFileStorageService {
    List<DigestType> uploadPrepare(String fileId);

    FileUploadMultiPhase.FileUploadAddress.Response uploadAddress(FileUploadMultiPhase.FileUploadAddress.Request request);

    FileInfo uploadComplete(FileUploadMultiPhase.FileUploadComplete.Request request);
}


