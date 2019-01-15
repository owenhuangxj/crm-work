package com.ss.server.service;

import com.baomidou.mybatisplus.service.IService;
import com.ss.server.entity.SysPerm;
import com.ss.server.model.AuthModel;

import java.util.List;
import java.util.Set;

public interface SysPermService extends IService<SysPerm> {
    Set<AuthModel> getPermsByUserId(String userId);
    List<SysPerm> getPermsByRoleId(String roleId);
    void saveOrUpdate(List<SysPerm> perms);
}
