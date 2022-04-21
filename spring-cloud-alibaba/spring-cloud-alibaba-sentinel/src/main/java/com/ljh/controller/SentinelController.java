package com.ljh.controller;

import com.alibaba.csp.sentinel.AsyncEntry;
import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphO;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * SentinelController
 *
 * @author ljh
 * created on 2022/2/15 11:45
 */
@RestController
public class SentinelController {

    private final ApplicationContext applicationContext;

    @Autowired
    public SentinelController(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

//    /**
//     * 自定义规则
//     */
//    @PostConstruct
//    public void initFlowRules() {
//        List<FlowRule> flowRuleList = new ArrayList<>();
//        FlowRule flowRule = new FlowRule();
//        flowRule.setResource("Hello");
//        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS)
//                .setCount(3);
//        flowRuleList.add(flowRule);
//        FlowRuleManager.loadRules(flowRuleList);
//    }

    /**
     * 抛出异常方式
     */
    @GetMapping("hello")
    public String hello() {
        try (Entry entry = SphU.entry("Hello")) {
            return System.nanoTime() + ": Hello SphU!";
        } catch (BlockException e) {
            e.printStackTrace();
            return "系统繁忙，请稍后";
        }
    }

    /**
     * 返回布尔值方式
     */
    @GetMapping("hello2")
    public String hello2() {
        if (SphO.entry("Hello")) {
            try {
                return System.nanoTime() + ": Hello SphO!";
            } finally {
                SphO.exit();
            }
        } else {
            return "系统繁忙，请稍后";
        }
    }

    /**
     * 注解方式
     */
    @GetMapping("hello3")
    @SentinelResource(value = "Hello", blockHandler = "helloBlockHandler")
    public String hello3() {
        return System.nanoTime() + ": Hello Annotation!";
    }

    public String helloBlockHandler(BlockException e) {
        e.printStackTrace();
        return "系统繁忙，请稍后";
    }

    /**
     * 异步调用
     */
    @GetMapping("hello4")
    public void hello4() {
        AsyncEntry asyncEntry = null;
        try {
            asyncEntry = SphU.asyncEntry("Hello");
            applicationContext.getBean(SentinelController.class).doSomethingAsync();
        } catch (BlockException e) {
            System.out.println("系统繁忙，请稍后");
        } finally {
            if (asyncEntry != null) {
                asyncEntry.exit();
            }
        }
    }

    public void doSomethingAsync() {
        System.out.println("async start...");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("async end...");
    }

    @GetMapping("test/{times}")
    public void test(@PathVariable Integer times) {
        if (times == null || times == 0) return;
        RestTemplate restTemplate = new RestTemplate();
        for (int i = 0; i < times; i++) {
            ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:9004/hello", String.class);
            System.out.println(forEntity.getBody());
        }
    }
}
