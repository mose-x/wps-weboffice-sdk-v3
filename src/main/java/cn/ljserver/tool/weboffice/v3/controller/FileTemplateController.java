package cn.ljserver.tool.weboffice.v3.controller;

import cn.ljserver.tool.weboffice.v3.model.FileTemplate;
import cn.ljserver.tool.weboffice.v3.model.ProviderResponseEntity;
import cn.ljserver.tool.weboffice.v3.service.FileTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 文件模板 -> 详见： <br>
 * <a href="https://solution.wps.cn/docs/developer/files/get-template.html">wps web office 文件模板</a>
 */
@RestController
@RequestMapping("/v3/provider/files")
public class FileTemplateController  extends ProviderBaseController{

    @Autowired
    private FileTemplateService fileTemplateService;

    @GetMapping("/{officeType}")
    @ProviderJsonApi
    public ProviderResponseEntity<?> fileTemplate(@PathVariable("officeType") String officeType) {
        return fileTemplateService.getFileTemplateResponse(officeType);
    }

}
