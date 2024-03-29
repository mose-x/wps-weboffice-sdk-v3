package cn.ljserver.tool.weboffice.v3.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FileNotExist extends ProviderException {
    private final int code = ErrorCodes.FileNotExist.getCode();

    public FileNotExist() {
        this(ErrorCodes.FileNotExist.name());
    }

    public FileNotExist(String message) {
        super(message);
    }
}
