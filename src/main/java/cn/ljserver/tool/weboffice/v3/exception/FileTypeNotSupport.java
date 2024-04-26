package cn.ljserver.tool.weboffice.v3.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 用户不存在
 */
@Getter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FileTypeNotSupport extends ProviderException {
    private final int code = ErrorCodes.FileTypeNotSupport.getCode();

    public FileTypeNotSupport() {
        this(ErrorCodes.FileTypeNotSupport.name());
    }

    public FileTypeNotSupport(String message) {
        super(message);
    }
}
