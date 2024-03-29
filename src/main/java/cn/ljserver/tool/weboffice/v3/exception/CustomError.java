package cn.ljserver.tool.weboffice.v3.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.FORBIDDEN)
public class CustomError extends ProviderException{
    private final int code = ErrorCodes.CustomError.getCode();

    public CustomError() {
        this(ErrorCodes.CustomError.name());
    }

    public CustomError(String message) {
        super(message);
    }
}
