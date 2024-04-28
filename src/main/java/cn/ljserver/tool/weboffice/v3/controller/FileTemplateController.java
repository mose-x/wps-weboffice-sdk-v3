package cn.ljserver.tool.weboffice.v3.controller;

import cn.ljserver.tool.weboffice.v3.model.ProviderResponseEntity;
import cn.ljserver.tool.weboffice.v3.service.FileTemplateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 文件模板 -> 详见： <br>
 * <a href="https://solution.wps.cn/docs/developer/files/get-template.html">wps web office 文件模板</a>
 */
@RestController
@RequestMapping("/v3/files/template")
public class FileTemplateController  extends ProviderBaseController{

    @GetMapping("/{officeType}")
    @ProviderJsonApi
    public ProviderResponseEntity<?> fileTemplate(@PathVariable("officeType") String officeType) {
        return FileTemplateService.getFileTemplateResponse(officeType);
    }

}
