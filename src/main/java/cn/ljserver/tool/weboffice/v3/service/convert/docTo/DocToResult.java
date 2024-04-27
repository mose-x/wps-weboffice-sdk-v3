package cn.ljserver.tool.weboffice.v3.service.convert.docTo;

import cn.ljserver.tool.weboffice.v3.util.ConvertUtils;

/**
 * 格式转换结果查询-> 详见： <br>
 * <a href="https://solution.wps.cn/docs/convert/tasks-status.html">wps web office 官方文档</a>
 */
public class DocToResult {

    /**
     * 获取转换结果
     */
    public static String get(String taskId) {
        String uri = "/api/developer/v1/tasks/" + taskId;
        return ConvertUtils.get(uri);
    }
}
