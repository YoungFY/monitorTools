package com.reda.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Title:
 * @Description:
 * @Author: guowl
 * @version： 1.0
 * @Date:2022/4/20
 * @Copyright: Copyright(c)2022 RedaFlight.com All Rights Reserved
 */
@Service
@Slf4j
public class RedisKeyCleanService {
    @Autowired
    private RedisTemplate redisTemplate;

    public void cleanKeyTask() {
        ConcurrentHashMap<String, String> delKeyMap = MonitorReadLogService.delKeyMap;
        DateUtil.offsetDay(new Date(), 1);
        DateTime dateTime = DateUtil.offsetDay(new Date(), -1);
        String preDay = DateUtil.format(dateTime, "yyyy-MM-dd");
        if (CollUtil.isNotEmpty(delKeyMap)) {
            for (String s : delKeyMap.keySet()) {
                if (s.contains(preDay)) {
                    redisTemplate.opsForHash().delete("MONITOR_KEY", s);
                }
            }
        }


    }
}
