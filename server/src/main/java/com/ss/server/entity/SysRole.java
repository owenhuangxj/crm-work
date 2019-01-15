package com.ss.server.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@TableName("sys_role")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysRole extends Model<SysRole> {

    @TableId(type = IdType.ID_WORKER_STR)
    private String roleId;      // 角色id
    private String roleName;    // 角色名，用于显示
    private String roleDesc;    // 角色描述
    private String roleVal;     // 角色值，用于权限判断
    private Date roleCreated;   // 创建时间
    private Date roleUpdated;   // 修改时间

    @Override
    protected Serializable pkVal() {
        return roleId;
    }
}
