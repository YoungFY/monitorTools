package com.reda.action;

import com.reda.config.MonitorConfigProperties;
import com.reda.service.MonitorReadLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Title:
 * @Description:
 * @Author: guowl
 * @version： 1.0
 * @Date:2022/3/31
 * @Copyright: Copyright(c)2022 RedaFlight.com All Rights Reserved
 */
@Component
@Order(10)
public class MonitorScheduleAction {

    @Value("${monitor.switch}")
    private String monitorSwitch;

    @Autowired
    private MonitorReadLogService monitorReadLogService;
    @Autowired
    private MonitorConfigProperties monitorConfigProperties;


    @Scheduled(cron = "${monitor.action}")
    public void readMonitorLog() {
        if (monitorSwitch.equals("ON")) {
            monitorReadLogService.readLog(monitorConfigProperties);
        }

    }

}
