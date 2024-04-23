package cn.ljserver.tool.weboffice.v3.exception;

import lombok.Getter;

/**
 * 错误码说明
 * <p>
 * <a href = "https://solution.wps.cn/docs/callback/error-code.html">-详见官方文档-</a>
 */
@Getter
public enum ErrorCodes {

    InvalidToken(40002),
    PermissionDenied(40003),
    FileNotExist(40004),
    InvalidArgument(40005),
    StorageNoSpace(40006),
    CustomError(40007),
    FileNameConflict(40008),
    FileVersionNotExist(40009),
    UserNotExist(40010),
    FileUploadNotComplete(41001),
    InternalError(50001),

    NotImplementException(44004);

    private final int code;

    ErrorCodes(int code) {
        this.code = code;
    }

}
