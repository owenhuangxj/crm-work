package com.ss.server.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@TableName("sys_perm")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysPerm extends Model<SysPerm> {

    @TableId(type = IdType.INPUT)
    private String permVal;     // 权限值，shiro的权限控制表达式
    private String parentPerm;  // 父节点权限值
    private String permName;    // 权限名称
    private Integer permType;   // 权限类型：1.菜单；2.按钮
    private Boolean isLeaf;     // 是否叶子节点
    private Date permCreated;   // 创建时间
    private Date permUpdated;   // 修改时间

    @TableField(exist = false)
    private List<SysPerm> children = new ArrayList<>();

    @Override
    protected Serializable pkVal() {
        return permVal;
    }
}
