package cn.ljserver.tool.weboffice.v3.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.FORBIDDEN)
public class PermissionDenied extends ProviderException {
    private final int code = ErrorCodes.PermissionDenied.getCode();

    public PermissionDenied() {
        this(ErrorCodes.PermissionDenied.name());
    }

    public PermissionDenied(String message) {
        super(message);
    }
}
