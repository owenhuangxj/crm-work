package com.ss.server;

import com.ss.server.dao.SysUserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServerApplicationTests {
    @Autowired
    SysUserMapper mapper;
    @Test
    public void contextLoads() {
    }
    @Test
    public void testGetRolesByUserId(){
        System.out.println(mapper.selectRolesByUserId("986177923098808322").getRoleList());
    }
}

