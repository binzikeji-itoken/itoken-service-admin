package com.binzikeji.itoken.service.admin.service.impl;

import com.binzikeji.itoken.common.domain.TbSysUser;
import com.binzikeji.itoken.common.mapper.TbSysUserMapper;
import com.binzikeji.itoken.common.service.impl.BaseServiceImpl;
import com.binzikeji.itoken.service.admin.service.AdminService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description
 * @Author Bin
 * @Date 2019/4/10 17:37
 **/
@Service
@Transactional(readOnly = true)
public class AdminServiceImpl extends BaseServiceImpl<TbSysUser, TbSysUserMapper> implements AdminService<TbSysUser> {

}
