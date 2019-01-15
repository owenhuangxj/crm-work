package com.ss.server.service;

import com.baomidou.mybatisplus.service.IService;
import com.ss.server.entity.SysRole;
import com.ss.server.model.AuthModel;

import java.util.List;
import java.util.Set;

public interface SysRoleService extends IService<SysRole> {
    Set<AuthModel> getRolesByUserId(String userId);

    List<String> getRoleIdsByUserId(String userId);

    boolean isRidsContainRoleVal(List<String> rids, String roleVal);

    boolean isUserContainRoleVal(String userId, String roleVal);
}
