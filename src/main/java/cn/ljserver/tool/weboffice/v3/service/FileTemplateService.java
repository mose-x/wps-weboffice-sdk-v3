package cn.ljserver.tool.weboffice.v3.service;

import cn.ljserver.tool.weboffice.v3.config.WebOfficeProperties;
import cn.ljserver.tool.weboffice.v3.exception.FileTypeNotSupport;
import cn.ljserver.tool.weboffice.v3.model.FileTemplate;
import cn.ljserver.tool.weboffice.v3.model.ProviderResponseEntity;
import cn.ljserver.tool.weboffice.v3.util.FileUtils;
import cn.ljserver.tool.weboffice.v3.util.HeaderUtils;
import cn.ljserver.tool.weboffice.v3.util.RequestUtils;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.squareup.okhttp.Headers;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * 文件模板服务 -> 详见： <br>
 * <a href="https://solution.wps.cn/docs/developer/files/get-template.html">wps web office 文件模板</a>
 */
@Service
public class FileTemplateService {

    @Autowired
    private WebOfficeProperties webOfficeProperties;

    /**
     * 文件模板服务 -> 详见： <br>
     * <a href="https://solution.wps.cn/docs/developer/files/get-template.html">wps web office 文件模板</a>
     * <br>
     * @return {@link ProviderResponseEntity<FileTemplate>}
     */
    @SneakyThrows
    public ProviderResponseEntity<?> getFileTemplateResponse(String officeType) {
        boolean noneMatch = Arrays.stream(FileUtils.arrTypes).noneMatch(type -> type.equalsIgnoreCase(officeType));
        if (noneMatch) {
            throw new FileTypeNotSupport();
        }

        String uri = "/api/developer/v1/files/" + officeType + "/template";
        String url = webOfficeProperties.getConvert().getDomain() + uri;
        String appid = webOfficeProperties.getConvert().getAppid();
        String secret = webOfficeProperties.getConvert().getSecret();
        Headers headers = HeaderUtils.headers("GET", uri, null, appid, secret);

        String resStr = RequestUtils.get(url, headers);

        return JSON.parseObject(resStr, ProviderResponseEntity.class);
    }

    /**
     * 文件模板服务 -> 详见： <br>
     * <a href="https://solution.wps.cn/docs/developer/files/get-template.html">wps web office 文件模板</a>
     * <br>
     * @return {@link FileTemplate}
     */
    @SneakyThrows
    public FileTemplate getFileTemplate(String officeType) {
        JSONObject jsonObject = (JSONObject) getFileTemplateResponse(officeType).getData();
        return JSON.parseObject(jsonObject.toString(), FileTemplate.class);
    }

}
