package cn.ljserver.tool.weboffice.v3.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 用户凭证，即 x-weboffice-token 头, 无效
 */
@Getter
@ResponseStatus(HttpStatus.FORBIDDEN)
public class InvalidToken extends ProviderException {
    private final int code = ErrorCodes.InvalidToken.getCode();

    public InvalidToken() {
        this(ErrorCodes.InvalidToken.name());
    }

    public InvalidToken(String message) {
        super(message);
    }
}
