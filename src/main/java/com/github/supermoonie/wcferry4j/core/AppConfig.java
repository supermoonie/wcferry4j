package com.github.supermoonie.wcferry4j.core;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author super_wang
 * @since 2024/6/8
 */
@Data
@Configuration
public class AppConfig {


    @Value("${release}")
    private Boolean release;
    @Value("${spring.application.name}")
    private String appCode;


}
