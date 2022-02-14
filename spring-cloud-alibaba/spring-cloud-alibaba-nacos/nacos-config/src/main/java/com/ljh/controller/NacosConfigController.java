package com.ljh.controller;

import com.ljh.config.Ljh;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * NacosConfigController
 *
 * @author ljh
 * created on 2022/2/13 19:09
 */
@RestController
@RequestMapping("nacos")
@RefreshScope
public class NacosConfigController {

    @Value("${ljh.name}")
    private String name;

    @Value("${ljh.age}")
    private Integer age;

    @Resource
    private Ljh ljh;

    @GetMapping("/getConfig")
    public String getConfig() {
        return name + " : " + age;
    }

    @GetMapping("/getConfig2")
    public Ljh getConfig2() {
        return ljh;
    }

    @Value("${mysql.common}")
    private String mysql;

    @Value("${redis.common}")
    private String redis;

    @Value("${crm.config}")
    private String crm;

    @Value("${oa.config}")
    private String oa;

    @GetMapping("/getCrmConfig")
    public String getCrmConfig() {
        return mysql + " : " + redis + " : " + crm;
    }

    @GetMapping("/getOaConfig")
    public String getOaConfig() {
        return mysql + " : " + redis + " : " + oa;
    }
}
