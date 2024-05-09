package cn.ljserver.tool.weboffice.v3.controller;

import cn.ljserver.tool.weboffice.v3.exception.NotImplementException;
import cn.ljserver.tool.weboffice.v3.model.ProviderResponseEntity;
import cn.ljserver.tool.weboffice.v3.service.ExtendCapacityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 附件对象相关接口 -> 详见： <br>
 * <a href="https://solution.wps.cn/docs/callback/extend.html#%E9%99%84%E4%BB%B6%E5%AF%B9%E8%B1%A1%E7%9B%B8%E5%85%B3%E6%8E%A5%E5%8F%A3">-官方文档-</a>
 */
@RestController
@RequestMapping("/v3/3rd/object")
public class AttachmentController extends ProviderBaseController {

    @PutMapping("/{key}")
    @ProviderJsonApi
    public ProviderResponseEntity<?> uploadAttachment(@PathVariable String key,
                                                      @RequestParam String name,
                                                      @RequestBody MultipartFile files) {
        this.getServiceOrThrow().uploadAttachment(key, name, files);
        return ProviderResponseEntity.ok("");
    }


    @GetMapping("/{key}/url")
    @ProviderJsonApi
    public ProviderResponseEntity<?> getAttachment(@PathVariable String key,
                                                   @RequestParam int scale_max_fit_width,
                                                   @RequestParam int scale_max_fit_height,
                                                   @RequestParam int scale_long_edge) {
        Map<String, String> res = new HashMap<>();
        String url = this.getServiceOrThrow().getAttachment(key, scale_max_fit_width, scale_max_fit_height, scale_long_edge);
        res.put("url", url);
        return ProviderResponseEntity.ok(res);
    }


    @PostMapping("/copy")
    @ProviderJsonApi
    public ProviderResponseEntity<?> copyAttachment(@RequestBody Map<String, String> keyDict) {
        this.getServiceOrThrow().copyAttachment(keyDict);
        return ProviderResponseEntity.ok();
    }

    private ExtendCapacityService service;

    @Autowired(required = false)
    public void setService(ExtendCapacityService service) {
        this.service = service;
    }

    private ExtendCapacityService getServiceOrThrow() {
        if (Objects.isNull(this.service)) {
            String msg = String.format("request path %s not implement with interface class %s ", getRequestPath(), "ExtendCapacityService");
            throw new NotImplementException(msg);
        }
        return this.service;
    }
}
