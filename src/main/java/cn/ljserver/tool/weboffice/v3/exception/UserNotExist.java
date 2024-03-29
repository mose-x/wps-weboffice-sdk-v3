package cn.ljserver.tool.weboffice.v3.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserNotExist extends ProviderException {
    private final int code = ErrorCodes.UserNotExist.getCode();

    public UserNotExist() {
        this(ErrorCodes.UserNotExist.name());
    }

    public UserNotExist(String message) {
        super(message);
    }
}
