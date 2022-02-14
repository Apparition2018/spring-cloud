package com.ljh.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Ljh
 *
 * @author ljh
 * created on 2022/2/14 15:32
 */
@Data
@Component
@ConfigurationProperties(prefix = "ljh")
public class Ljh {
    private String name;
    private Integer age;
}
