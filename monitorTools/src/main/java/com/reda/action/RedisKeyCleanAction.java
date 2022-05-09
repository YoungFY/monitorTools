package com.reda.action;

import com.reda.service.RedisKeyCleanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Title:
 * @Description:
 * @Author: guowl
 * @version： 1.0
 * @Date:2022/4/20
 * @Copyright: Copyright(c)2022 RedaFlight.com All Rights Reserved
 */
@Component
public class RedisKeyCleanAction {

    //0 01 0 * * ? 每天 1点执行一次
    @Autowired
    private RedisKeyCleanService redisKeyCleanService;

    @Scheduled(cron = "0 01 0 * * ?")
    public void readMonitorLog() {
        redisKeyCleanService.cleanKeyTask();
    }


}
