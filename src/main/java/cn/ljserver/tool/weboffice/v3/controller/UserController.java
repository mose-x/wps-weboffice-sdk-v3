package cn.ljserver.tool.weboffice.v3.controller;

import cn.ljserver.tool.weboffice.v3.exception.NotImplementException;
import cn.ljserver.tool.weboffice.v3.model.ProviderResponseEntity;
import cn.ljserver.tool.weboffice.v3.model.UserInfo;
import cn.ljserver.tool.weboffice.v3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/v3/3rd/users")
public class UserController extends ProviderBaseController {
    @GetMapping
    @ProviderJsonApi
    public ProviderResponseEntity<List<UserInfo>> fetchUsers(@RequestParam("user_ids") List<String> userIds) {
        return ProviderResponseEntity.ok(this.getUserServiceOrThrow().fetchUsers(userIds));
    }

    private UserService userService;

    @Autowired(required = false)
    private void setUserService(UserService userService) {
        this.userService = userService;
    }

    private UserService getUserServiceOrThrow() {
        if (Objects.isNull(this.userService)) {
            throw new NotImplementException(String.format("request path %s not implement", getRequestPath()));
        }
        return this.userService;
    }

}
