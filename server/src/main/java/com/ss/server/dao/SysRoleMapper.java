package com.ss.server.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ss.server.entity.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleMapper extends BaseMapper<SysRole> {
    List<SysRole> getRolesByUserId(@Param("userId") String userId);

    List<String> getRoleIdsByUserId(@Param("userId") String userId);

    Boolean isRoleIdsContainRoleVal(@Param("roleIds")List<String> roleIds,@Param("roleVal")String roleVal);

    Boolean isUserContainRoleVal(@Param("userId")String userId,@Param("roleVal")String roleVal);
}
