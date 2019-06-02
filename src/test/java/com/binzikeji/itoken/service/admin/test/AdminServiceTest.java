package com.binzikeji.itoken.service.admin.test;

import com.binzikeji.itoken.service.admin.ServiceAdminApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description
 * @Author Bin
 * @Date 2019/4/10 17:20
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceAdminApplication.class)
@ActiveProfiles(value = "prod")
@Transactional
@Rollback
public class AdminServiceTest {
}
