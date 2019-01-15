package com.ss.server.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ss.server.dao.SysRoleMapper;
import com.ss.server.entity.SysRole;
import com.ss.server.model.AuthModel;
import com.ss.server.service.SysRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Override
    public Set<AuthModel> getRolesByUserId(String userId) {

        List<SysRole> roles = baseMapper.getRolesByUserId(userId);
        return roles.stream().map(role->new AuthModel(role.getRoleName(),role.getRoleVal())).collect(Collectors.toSet());
    }

    @Override
    public List<String> getRoleIdsByUserId(String userId) {
        return baseMapper.getRoleIdsByUserId(userId);
    }

    @Override
    public boolean isRidsContainRoleVal(List<String> roleIds, String roleVal) {
        if(roleIds.isEmpty()) return false;
        Boolean b = baseMapper.isRoleIdsContainRoleVal(roleIds,roleVal);
        return b == null ? false:b.booleanValue();
    }

    @Override
    public boolean isUserContainRoleVal(String userId, String roleVal) {
        if(StringUtils.isBlank(userId)) return false;
        Boolean b = baseMapper.isUserContainRoleVal(userId,roleVal);
        return  b == null ? false : b.booleanValue();
    }
}
