package com.ss.server.shiro;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.ss.server.entity.SysUser;
import com.ss.server.model.AuthModel;
import com.ss.server.service.SysPermService;
import com.ss.server.service.SysRoleService;
import com.ss.server.service.SysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.stream.Collectors;

public class CrmRealm extends AuthorizingRealm {
    @Autowired
    private SysUserService userService;
    @Autowired
    private SysRoleService roleService;
    @Autowired
    private SysPermService permService;
    private static Logger logger = LoggerFactory.getLogger(CrmRealm.class);
    //默认使用的是SimpleCredentialMatcher,最常用的是HashCredentialMatcher
    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        logger.info("config credentialsMatcher : {}",credentialsMatcher.getClass().getName());
        //设置用于匹配密码的CredentialsMatcher
        HashedCredentialsMatcher hashMatcher = new HashedCredentialsMatcher();
        hashMatcher.setHashAlgorithmName(Sha256Hash.ALGORITHM_NAME);
        hashMatcher.setStoredCredentialsHexEncoded(false);
        hashMatcher.setHashIterations(1024);
        super.setCredentialsMatcher(hashMatcher);
    }

    //查询用户的角色和权限存到SimpleAuthenticationInfo中，这样在其它地方
    //SecurityUtils.getSubject().getPrincipal()就能拿出用户的所有信息，包括角色和权限
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();
        String password = new String(upToken.getPassword());
        if(username == null || password == null){
            throw new AccountException("用户名或密码不能为空");
        }
        SysUser user = userService.selectOne(new EntityWrapper<SysUser>().eq("user_name",username).eq("user_password",password));
        if(null == user) throw new AccountException("用户名或密码错误");

        Set<AuthModel> roles = roleService.getRolesByUserId(user.getUserId());
        Set<AuthModel> perms = permService.getPermsByUserId(user.getUserId());
        user.setPerms(perms);
        user.setRoles(roles);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,user.getUserName(),user.getUserPassword());
        if(null != user.getSalt())  info.setCredentialsSalt(ByteSource.Util.bytes(user.getSalt()));
        return info;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        if(null == principals) throw  new AuthenticationException("argument : \"principals\" can not be null");
        SysUser user = (SysUser) getAvailablePrincipal(principals);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<AuthModel> roles = user.getRoles();
        Set<AuthModel> perms = user.getPerms();
        info.setRoles(roles.stream().map(AuthModel::getVal).collect(Collectors.toSet()));
        info.setStringPermissions(perms.stream().map(AuthModel::getVal).collect(Collectors.toSet()));
        return info;
    }
}
