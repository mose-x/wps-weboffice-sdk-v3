package cn.ljserver.tool.weboffice.v3.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidArgument extends ProviderException {
    private final int code = ErrorCodes.InvalidArgument.getCode();

    public InvalidArgument() {
        this(ErrorCodes.InvalidArgument.name());
    }

    public InvalidArgument(String message) {
        super(message);
    }
}
