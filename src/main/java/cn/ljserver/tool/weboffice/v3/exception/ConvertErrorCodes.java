package cn.ljserver.tool.weboffice.v3.exception;

import lombok.Getter;
/**
 * 文档转换错误码说明
 * <p>
 * <a href = "https://solution.wps.cn/docs/convert/error.html">-详见官方文档-</a>
 */
@Getter
public enum ConvertErrorCodes {

    InvalidArgument(10001, "参数无效"),
    Timeout(10002, "超时"),
    PermissionDenied(10003, "接口无权限"),
    AppNotExist(20002, "应用不存在"),
    InvalidScope(20003, "授权范围无效"),
    InvalidRedirectUri(20004, "重定向链接无效"),
    InvalidResponseType(20005, "response_type 无效"),
    AuditNotPass(20006, "开发者/应用 审核未通过"),
    UserUnauthorized(30001, "用户未登录"),
    RateLimiter(30002, "API 限频"),
    InvalidSignature(30003, "签名无效"),
    InternalError(30004, "内部错误"),
    NoImpl(30005, "未实现"),
    OnlineFileType(30006, "在线文件类型，无法下载"),
    UserAccountRestrictedAccess(30007, "账号不允许访问"),
    FileSizeLimited(30008, "文件太大限制"),
    DownloadFail(30009, "异步URL下载失败"),
    TooManyTask(30010, "异步下载失败，系统保护"),
    ExecuteFailed(30011, "执行编辑指令失败，例如：向表格中插入数据"),
    FileNotExists(30012, "文件（夹）不存在"),
    LinkNotExists(30014, "分享地址不存在"),
    FileNameConflict(30015, "文件（夹）同名冲突"),
    TooManyWebhooks(30016, "文档内 webhook 超过10个"),
    InvalidPermissionResponse(40001, "第三方权限回调接口错误"),
    AccountUpgrading(40008, "帐号正在升级中，暂时无法使用（临时状态，很快会恢复）");

    private final int code;
    private final String msg;

    ConvertErrorCodes(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
