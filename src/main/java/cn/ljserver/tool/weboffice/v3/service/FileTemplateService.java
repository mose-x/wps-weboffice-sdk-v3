package cn.ljserver.tool.weboffice.v3.service;

import cn.ljserver.tool.weboffice.v3.exception.ConvertErrorCodes;
import cn.ljserver.tool.weboffice.v3.model.FileTemplateInfo;
import cn.ljserver.tool.weboffice.v3.model.FileTemplateResponse;
import cn.ljserver.tool.weboffice.v3.model.ProviderResponseEntity;
import cn.ljserver.tool.weboffice.v3.util.ConvertUtils;
import cn.ljserver.tool.weboffice.v3.util.FileUtils;


/**
 * 文件模板服务 -> 详见： <br>
 * <a href="https://solution.wps.cn/docs/developer/files/get-template.html">wps web office 文件模板</a>
 */
public class FileTemplateService {

    /**
     * 文件模板服务 -> 详见： <br>
     * <a href="https://solution.wps.cn/docs/developer/files/get-template.html">wps web office 文件模板</a>
     * <br>
     *
     * @return {@link ProviderResponseEntity<FileTemplateInfo>}
     */
    public static ProviderResponseEntity<?> getFileTemplateResponse(String officeType) {
        FileTemplateResponse response = getFileTemplate(officeType);
        if (response != null && response.getCode() == 0) {
            return ProviderResponseEntity.ok(response.getData());
        } else {
            if (response == null) return ProviderResponseEntity.err(ConvertErrorCodes.Unknown);
            return ProviderResponseEntity.err(ConvertErrorCodes.getByCode(response.getCode()));
        }
    }

    /**
     * 文件模板服务 -> 详见： <br>
     * <a href="https://solution.wps.cn/docs/developer/files/get-template.html">wps web office 文件模板</a>
     * <br>
     *
     * @return {@link FileTemplateResponse}
     */
    public static FileTemplateResponse getFileTemplate(String officeType) {
        FileUtils.typeMatchCheck(FileUtils.templateTypes, officeType);
        // 转换为小写，防止报错， 其实官方是 大小写 敏感的
        String uri = "/api/developer/v1/files/" + officeType.toLowerCase() + "/template";
        return ConvertUtils.get(uri, FileTemplateResponse.class);
    }

}
