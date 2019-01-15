package com.ss.server.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@TableName("sys_role_perm")
@NoArgsConstructor
@AllArgsConstructor
public class SysRolePerm implements Serializable {
    @TableField("role_id")
    private String roleId;
    @TableField("perm_val")
    private String permVal;
    @TableField("perm_type")
    private Integer permType;
}
