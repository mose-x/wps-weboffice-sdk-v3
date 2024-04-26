package cn.ljserver.tool.weboffice.v3.service;

import cn.ljserver.tool.weboffice.v3.config.WebOfficeProperties;
import cn.ljserver.tool.weboffice.v3.exception.FileTypeNotSupport;
import cn.ljserver.tool.weboffice.v3.model.FileTemplate;
import cn.ljserver.tool.weboffice.v3.model.ProviderResponseEntity;
import cn.ljserver.tool.weboffice.v3.util.FileUtils;
import cn.ljserver.tool.weboffice.v3.util.HeaderUtils;
import cn.ljserver.tool.weboffice.v3.util.RequestUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;

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
     *
     * @return {@link ProviderResponseEntity<FileTemplate>}
     */
    @SneakyThrows
    public ProviderResponseEntity<?> getFileTemplateResponse(String officeType) {
        String jsonStr = request(officeType);
        if (jsonStr != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonStr, ProviderResponseEntity.class);
        } else {
            return ProviderResponseEntity.err();
        }
    }

    /**
     * 文件模板服务 -> 详见： <br>
     * <a href="https://solution.wps.cn/docs/developer/files/get-template.html">wps web office 文件模板</a>
     * <br>
     *
     * @return {@link FileTemplate}
     */
    @SneakyThrows
    public FileTemplate getFileTemplate(String officeType) {
        String jsonStr = request(officeType);
        if (jsonStr != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> jsonMap = objectMapper
                    .readValue(jsonStr, new TypeReference<Map<String, Object>>() {
                    });

            if (jsonMap != null && jsonMap.get("data") != null) {
                String str = objectMapper.writeValueAsString(jsonMap.get("data"));
                return objectMapper.readValue(str, FileTemplate.class);
            }
        }
        return null;
    }

    private String request(String officeType) {
        boolean noneMatch = Arrays.stream(FileUtils.convertArrTypes).noneMatch(type -> type.equalsIgnoreCase(officeType));
        if (noneMatch) {
            throw new FileTypeNotSupport();
        }
        String uri = "/api/developer/v1/files/" + officeType + "/template";
        String url = webOfficeProperties.getConvert().getDomain() + uri;
        String appid = webOfficeProperties.getConvert().getAppid();
        String secret = webOfficeProperties.getConvert().getSecret();
        Map<String, String> header = HeaderUtils.header("GET", uri, null, appid, secret);
        return RequestUtils.get(url, header);
    }

}
