package cn.ljserver.tool.weboffice.v3.service;

import cn.ljserver.tool.weboffice.v3.exception.FileTypeNotSupport;
import cn.ljserver.tool.weboffice.v3.model.FileTemplate;
import cn.ljserver.tool.weboffice.v3.model.ProviderResponseEntity;
import cn.ljserver.tool.weboffice.v3.util.ConvertUtils;
import cn.ljserver.tool.weboffice.v3.util.FileUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;

/**
 * 文件模板服务 -> 详见： <br>
 * <a href="https://solution.wps.cn/docs/developer/files/get-template.html">wps web office 文件模板</a>
 */
@Service
public class FileTemplateService {

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

    /**
     * 具体请求实现
     */
    private String request(String officeType) {
        boolean noneMatch = Arrays.stream(FileUtils.templateTypes)
                .noneMatch(type -> type.equalsIgnoreCase(officeType));
        if (noneMatch) {
            throw new FileTypeNotSupport();
        }
        // 转换为小写，防止报错， 其实官方是 大小写 敏感的
        String uri = "/api/developer/v1/files/" + officeType.toLowerCase() + "/template";
        return ConvertUtils.get(uri);
    }

}
