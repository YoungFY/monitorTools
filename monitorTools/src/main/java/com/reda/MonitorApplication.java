package com.reda;

import com.reda.config.MonitorConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Title:
 * @Description:
 * @Author: guowl
 * @version： 1.0
 * @Date:2022/3/31
 * @Copyright: Copyright(c)2022 RedaFlight.com All Rights Reserved
 */
@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties(MonitorConfigProperties.class)
public class MonitorApplication {
    public static void main(String[] args) {
        SpringApplication.run(MonitorApplication.class, args);
    }
}
