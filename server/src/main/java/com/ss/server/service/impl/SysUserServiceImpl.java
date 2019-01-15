package com.ss.server.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ss.server.dao.SysUserMapper;
import com.ss.server.entity.SysUser;
import com.ss.server.service.SysUserService;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
}
