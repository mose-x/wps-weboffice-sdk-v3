package cn.ljserver.tool.weboffice.v3.service;

import cn.ljserver.tool.weboffice.v3.model.UserInfo;

import java.util.List;

/**
 * 用户信息 -> 详见： <br>
 * <a href="https://solution.wps.cn/docs/callback/user.html">wps web office 用户信息</a>
 */
public interface UserService {
    /**
     * 批量获取用户信息
     *
     * @param userIds 用户id列表 <br>
     *                <a href = "https://solution.wps.cn/docs/callback/user.html#批量获取用户信息">-详见官方文档-</a>
     */
    List<UserInfo> fetchUsers(List<String> userIds);
}
