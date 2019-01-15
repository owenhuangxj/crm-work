package com.ss.server.config;

import com.ss.server.shiro.CrmRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {
    @Bean
    public Realm realm() {
        return new CrmRealm();
    }
    @Bean
    public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        /**
         * setUsePrefix(false)用于解决一个奇怪的bug。在引入spring aop的情况下。
         * 在@Controller注解的类的方法中加入@RequiresRole注解，会导致该方法无法映射请求，导致返回404。
         * 加入这项配置能解决这个bug
         */
        creator.setUsePrefix(true);
        return creator;
    }

    /**
     * 这里做统一鉴权，规定哪些可以不用登陆，哪些必须登陆
     * 这里不做权限控制，权限控制由注解控制
     *
     * @return
     */
    @Bean
    public ShiroFilterChainDefinition shiroFilterChain() {
        DefaultShiroFilterChainDefinition shiroFilterChain = new DefaultShiroFilterChainDefinition();
        shiroFilterChain.addPathDefinition("/auth/login", "anon");
        shiroFilterChain.addPathDefinition("/auth/logout", "anon");
        shiroFilterChain.addPathDefinition("/page/401", "anon");
        shiroFilterChain.addPathDefinition("/page/403", "anon");
        shiroFilterChain.addPathDefinition("/page/index", "anon");
        /*下面這句必須放在最後，因為鉴权是从头到尾进行控制的，扫描到路径匹配之后就会生效，如果下面这句放在开始的位置，那么所有的请求都会要求登陆了*/
        shiroFilterChain.addPathDefinition("/**", "authc");
        return shiroFilterChain;
    }

}
