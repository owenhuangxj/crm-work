package com.ss.server.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.ss.server.model.AuthModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.*;

@Data
@TableName("sys_user")
@NoArgsConstructor
@AllArgsConstructor
public class SysUser extends Model<SysUser> {
    //字符串全局唯一ID
    @TableId(type = IdType.ID_WORKER_STR)
    private String userId;          // 用户id
    private String userName;        // 登录名，不可改
    private String realName;        //用户别名
    private String userPassword;    // 已加密的登录密码
    private String salt;            // 加密盐值
    private Boolean isLock;         // 是否锁定
    private Date userCreated;       // 创建时间
    private Date userUpdated;       // 修改时间

    @TableField(exist = false)
    private List<SysRole> roleList = new ArrayList<>(); //用户所有角色值，在管理后台显示用户的角色
    @TableField(exist = false)
    private Set<AuthModel> roles = new HashSet<>();     //用户所有角色值，用于shiro做角色权限的判断
    @TableField(exist = false)
    private Set<AuthModel> perms = new HashSet<>();     //用户所有权限值，用于shiro做资源权限的判断

    @Override
    protected Serializable pkVal() {
        return userId;
    }
}
