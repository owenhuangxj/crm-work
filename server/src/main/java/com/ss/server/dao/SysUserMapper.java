package com.ss.server.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ss.server.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
    SysUser selectRolesByUserId(String userId);
}
