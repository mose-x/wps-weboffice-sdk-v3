package cn.ljserver.tool.weboffice.v3.model;

public class NotifyType {
    /**
     * 文件首次在 WebOffice 服务中打开时，会发送该通知
     * <br>
     * 后续加入的协作用户，不会创建新会话，因此也不会触发 session_open 通知
     */
    public static final String SESSION_OPEN = "session_open";
    /**
     * WebOffice 服务关闭文档时发送该通知，与 session_open 相对应。
     */
    public static final String SESSION_QUIT = "session_quit";
    /**
     * 用户加入会话通知。
     */
    public static final String USER_JOIN = "user_join";
    /**
     * 用户退出会话通知，与 user_join 相对应。
     */
    public static final String USER_QUIT = "user_quit";
    /**
     * 导出、打印操作通知。
     */
    public static final String OPERATE_RECORD_EXPORT = "operate_record_export";
}
