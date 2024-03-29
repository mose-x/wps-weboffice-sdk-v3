package cn.ljserver.tool.weboffice.v3.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadSinglePhase {
    @Data
    @NoArgsConstructor
    public static class Request {
        private String fileId;

        private MultipartFile file;

        private String name;

        private int size;

        private String sha1;

        private boolean isManual;

        private int attachmentSize;

        private String contentType;
    }
}
