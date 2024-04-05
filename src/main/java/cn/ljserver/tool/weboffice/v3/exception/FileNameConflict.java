package cn.ljserver.tool.weboffice.v3.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.FORBIDDEN)
public class FileNameConflict extends ProviderException {
    private final int code = ErrorCodes.FileNameConflict.getCode();

    public FileNameConflict() {
        this(ErrorCodes.FileNameConflict.name());
    }

    public FileNameConflict(String message) {
        super(message);
    }
}