package com.ss.server;

import com.ss.server.dao.SysUserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServerApplicationTests {
    @Autowired
    SysUserMapper mapper;


    @Test
    public void contextLoads() {
    }

    @Test
    public void testGetRolesByUserId() {
        System.out.println(mapper.selectRolesByUserId("986177923098808322").getRoleList());
    }
    @Test
    public void testLogin(){

    }
}

