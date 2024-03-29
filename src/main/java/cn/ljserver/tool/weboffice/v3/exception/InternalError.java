package cn.ljserver.tool.weboffice.v3.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalError extends ProviderException {
    private final int code = ErrorCodes.InternalError.getCode();

    public InternalError() {
        this(ErrorCodes.InternalError.name());
    }

    public InternalError(String message) {
        super(message);
    }
}
