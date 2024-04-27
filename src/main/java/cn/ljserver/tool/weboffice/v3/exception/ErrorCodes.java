package cn.ljserver.tool.weboffice.v3.exception;

import lombok.Getter;

/**
 * 错误码说明
 * <p>
 * <a href = "https://solution.wps.cn/docs/callback/error-code.html">-详见官方文档-</a>
 */
@Getter
public enum ErrorCodes {

    Unknown(-1, "未知错误"),
    FileTypeNotSupport(40001, "文档类型不支持"),
    InvalidToken(40002, "用户凭证，即 x-weboffice-token 头, 无效"),
    PermissionDenied(40003, "用户操作权限不足"),
    FileNotExist(40004, "文档不存在"),
    InvalidArgument(40005, "请求参数错误"),
    StorageNoSpace(40006, "存储空间已满"),
    CustomError(40007, "自定义错误，可以用来返回自定义错误信息"),
    FileNameConflict(40008, "文档名称冲突，例如重命名文档时"),
    FileVersionNotExist(40009, "文档版本不存在"),
    UserNotExist(40010, "用户不存在"),
    FileUploadNotComplete(41001, "文件未正确上传，例如保存文档时"),
    InternalError(50001, "系统错误导致的请求不能正常响应"),
    NotImplementException(44004, "接口未实现"),
    ConfigNotExist(44005, "配置信息不存在");

    private final int code;
    private final String msg;

    ErrorCodes(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
