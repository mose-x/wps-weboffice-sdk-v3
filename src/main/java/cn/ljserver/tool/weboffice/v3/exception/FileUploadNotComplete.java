package cn.ljserver.tool.weboffice.v3.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.FORBIDDEN)
public class FileUploadNotComplete extends ProviderException {
    private final int code = ErrorCodes.FileUploadNotComplete.getCode();

    public FileUploadNotComplete() {
        this(ErrorCodes.FileUploadNotComplete.name());
    }

    public FileUploadNotComplete(String message) {
        super(message);
    }
}
