package cn.ljserver.tool.weboffice.v3.controller;

import cn.ljserver.tool.weboffice.v3.model.Notify;
import cn.ljserver.tool.weboffice.v3.model.ProviderResponseEntity;
import cn.ljserver.tool.weboffice.v3.service.ExtendCapacityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 事件通知 -> 详见<br>
 * <a href = "https://solution.wps.cn/docs/callback/extend.html#%E4%BA%8B%E4%BB%B6%E9%80%9A%E7%9F%A5">-详见官方文档-</a>
 */
@RestController
@RequestMapping("/v3/3rd/notify")
public class NotifyController extends ProviderBaseController {

    /**
     * 事件通知 -> 详见<br>
     * <a href = "https://solution.wps.cn/docs/callback/extend.html#%E4%BA%8B%E4%BB%B6%E9%80%9A%E7%9F%A5">-详见官方文档-</a>
     */
    @PostMapping
    @ProviderJsonApi
    public ProviderResponseEntity<?> notify(@RequestBody Notify notify) {
        this.service.notify(notify);
        return ProviderResponseEntity.ok();
    }

    private ExtendCapacityService service;

    @Autowired(required = false)
    public void setService(ExtendCapacityService service) {
        this.service = service;
    }
}
