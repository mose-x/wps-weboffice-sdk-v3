package cn.ljserver.tool.weboffice.v3.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 文档版本不存在
 */
@Getter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FileVersionNotExist extends ProviderException {
    private final int code = ErrorCodes.FileVersionNotExist.getCode();

    public FileVersionNotExist() {
        this(ErrorCodes.FileVersionNotExist.name());
    }

    public FileVersionNotExist(String message) {
        super(message);
    }
}
