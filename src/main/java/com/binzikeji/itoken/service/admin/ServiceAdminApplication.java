package com.binzikeji.itoken.service.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Description
 * @Author Bin
 * @Date 2019/4/8 14:31
 **/
@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
@MapperScan(basePackages = {"com.binzikeji.itoken.service.admin.mapper", "com.binzikeji.itoken.common.mapper"})
public class ServiceAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceAdminApplication.class, args);
    }
}
