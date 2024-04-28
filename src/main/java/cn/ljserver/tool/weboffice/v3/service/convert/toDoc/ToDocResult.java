package cn.ljserver.tool.weboffice.v3.service.convert.toDoc;

import cn.ljserver.tool.weboffice.v3.model.convert.ToDocResponse;
import cn.ljserver.tool.weboffice.v3.util.ConvertUtils;
import cn.ljserver.tool.weboffice.v3.util.FileUtils;


/**
 * 转为文档结果查询 -> 详见： <br>
 * <a href="https://solution.wps.cn/docs/convert/to-docs-status.html">wps web office 官方文档</a>
 */
public class ToDocResult {

    /**
     * 获取转换结果
     */
    public static ToDocResponse get(String officeType, String taskId) {
        FileUtils.typeMatchCheck(FileUtils.convertToDocumentTypes, officeType);
        // 转换为小写，防止报错， 其实官方是 大小写 敏感的
        String uri = "/api/developer/v1/tasks/convert/to/" + officeType.toLowerCase() + "/" + taskId;
        return ConvertUtils.get(uri, ToDocResponse.class);
    }

}
