package cn.ljserver.tool.weboffice.v3.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotImplementException extends ProviderException {
    // a special error code only works with provider sdk
    private final int code = ErrorCodes.NotImplementException.getCode();

    public NotImplementException() {
        super(ErrorCodes.NotImplementException.name());
    }

    public NotImplementException(String message) {
        super(message);
    }
}
