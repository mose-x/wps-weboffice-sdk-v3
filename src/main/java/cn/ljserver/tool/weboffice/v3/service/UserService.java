package cn.ljserver.tool.weboffice.v3.service;

import cn.ljserver.tool.weboffice.v3.model.UserInfo;

import java.util.List;

public interface UserService {
    List<UserInfo> fetchUsers(List<String> userIds);
}
