package com.ss.server.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ss.server.entity.SysPerm;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysPermMapper extends BaseMapper<SysPerm> {
    List<SysPerm> getPermsByUserId(@Param("userId") String userId);

    List<SysPerm> getPermsByRoleId(@Param("roleId") String roleId);

    void saveOrUpdate(@Param("perms") List<SysPerm> perms);
}
