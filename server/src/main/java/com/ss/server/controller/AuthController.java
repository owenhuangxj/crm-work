package com.ss.server.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ss.server.entity.SysUser;
import com.ss.server.model.JsonModel;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private static Logger log = LoggerFactory.getLogger(AuthController.class);
    @PostMapping("/login")
    public JsonModel login(@RequestBody String body){
        String oper = "user login";
        log.info("{}, body: {}",oper,body);
        JSONObject json = JSON.parseObject(body);
        String username = json.getString("username");
        String password = json.getString("password");
//        log.info("username : {} password : {}",username,password);
        if(StringUtils.isBlank(username)) return JsonModel.fail(oper,"用户名不能为空");
        if(StringUtils.isBlank(password)) return JsonModel.fail(oper,"密码不能为空");
        Subject subject = SecurityUtils.getSubject();
        /**
         * 执行登录操作。如果不成功，则抛出AuthenticationException，而AuthenticationException的子类会指明具体失败的原因。
         * 如果成功，与提交的Subject/User关联的帐户数据将与此Subject关联，并且该方法将悄悄返回。在悄悄地返回时，可以将此主题实例视为已验证，
         * getPrincipal()、 getPrincipal()都是非空数据，isAuthenticated() 、isAuthenticated()将返回true
         */
        try {
            subject.login(new UsernamePasswordToken(username, password));
            SysUser user = (SysUser)subject.getPrincipal();
            return JsonModel.succ(oper,"登陆成功")
                            .setReturnData("token", UUID.randomUUID().timestamp())
                            .setReturnData("userId",user.getUserId())
                            .setReturnData("roles",user.getRoleList())
                            .setReturnData("perms",user.getPerms());
        }catch (LockedAccountException lk){
            return JsonModel.fail(oper,"账号已经被锁定");
        }catch (AuthenticationException ex){
            return JsonModel.fail(oper,"用户名或密码错误");
        }
    }
}
