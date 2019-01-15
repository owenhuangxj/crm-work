package com.ss.server.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ss.server.dao.SysPermMapper;
import com.ss.server.entity.SysPerm;
import com.ss.server.model.AuthModel;
import com.ss.server.service.SysPermService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SysPermServiceImpl extends ServiceImpl<SysPermMapper, SysPerm> implements SysPermService {
    @Override
    public Set<AuthModel> getPermsByUserId(String userId) {
        List<SysPerm> perms = baseMapper.getPermsByUserId(userId);
        return perms.stream().map(perm->new AuthModel(perm.getPermName(),perm.getPermVal())).collect(Collectors.toSet());
    }

    @Override
    public List<SysPerm> getPermsByRoleId(String roleId) {
        return  baseMapper.getPermsByRoleId(roleId);
    }

    @Override
    public void saveOrUpdate(List<SysPerm> perms) {
        if(null != perms)baseMapper.saveOrUpdate(perms);
    }
}
