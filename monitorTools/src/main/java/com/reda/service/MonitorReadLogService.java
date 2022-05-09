package com.reda.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.net.NetUtil;
import com.reda.config.MonitorConfigProperties;
import com.reda.entry.MonitorEntry;
import com.reda.mapper.RealtimeProduceInformationMapper;
import com.reda.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.RandomAccessFile;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Title:
 * @Description:
 * @Author: guowl
 * @version： 1.0
 * @Date:2022/3/31
 * @Copyright: Copyright(c)2022 RedaFlight.com All Rights Reserved
 */
@Service
@Slf4j
@Order(6)
public class MonitorReadLogService {
    private File logFile = null;

    private static String newPath = null;

    private static String MONITOR_KEY = "MONITOR_KEY";


    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 5, 1, TimeUnit.SECONDS, new LinkedBlockingDeque<>(100));

//    @Autowired
//    RedissonClient redissonClient;

    private static ArrayList<String> logList = new ArrayList<>();

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RealtimeProduceInformationMapper realtimeProduceInformationMapper;
    public  static ConcurrentHashMap<String, String> delKeyMap= new ConcurrentHashMap<>();


    public void readLog(MonitorConfigProperties monitorConfigProperties) {
        newPath = monitorConfigProperties.getFilePath() + "/" + "monitor_" + NetUtil.getLocalhost().getHostAddress() + "/data/" + DateUtil.format(new Date(), "YYYY-MM-dd") + "/";
        log.debug("文件初始化路径成功：{}", newPath);
        String[] splitApp = null;
        String[] splitTool = null;
        if (!CollUtil.isNotEmpty(logList)) {
            log.debug("初始化文件集合");
            String appFile = monitorConfigProperties.getAppFile();
            String toolsFile = monitorConfigProperties.getToolsFile();
            splitApp = appFile.split(",");
            splitTool = toolsFile.split(",");
            for (String s : splitApp) {
                logList.add(NetUtil.getLocalhost().getHostAddress() + "_" + s);
            }
            for (String s : splitTool) {
                logList.add(NetUtil.getLocalhost().getHostAddress() + "_" + s);
            }
        }
        try {
            if (!redisTemplate.hasKey("flag")) {
                for (String s : logList) {
                    redisTemplate.opsForHash().put(MONITOR_KEY, s, "");
                }
                redisTemplate.opsForValue().set("flag", "");
            }
        } catch (Exception e) {
            log.debug("init data faild..");
        } finally {

        }
        threadPoolExecutor.execute(new MonitorThread());
    }

    private class MonitorThread implements Runnable {
        @Override
        public void run() {
            log.debug("=======" + Thread.currentThread().getName() + "read logFile" + "========");
            if (CollUtil.isEmpty(logList)) {
                log.debug("文件集合为空，未能获取文件信息");
                return;
            }
            try {
                synchronized (MonitorReadLogService.class) {
                    String dateStr = DateUtils.format(new Date());
                    long lastTimeFileSize = 0;
                    for (int i = 0; i < logList.size(); i++) {
                        logFile = new File(newPath + logList.get(i));
                        if (!logFile.exists()) {
                            log.debug("文件不存在，检查文件路径配置:{}", logFile.getPath());
                            continue;
                        }
                        long len = logFile.length();
                        if (redisTemplate.opsForHash().hasKey(MONITOR_KEY, logList.get(i) + dateStr)) {
                            if (redisTemplate.opsForHash().get(MONITOR_KEY, logList.get(i) + dateStr).toString() != "") {
                                lastTimeFileSize = Long.valueOf(redisTemplate.opsForHash().get(MONITOR_KEY, logList.get(i) + dateStr).toString());
                            }
                        } else {
                            lastTimeFileSize = 0;
                        }
                        if (len > lastTimeFileSize) {
                            log.debug("begin read log file len:{},fileName:{}", len, logList.get(i));
                            RandomAccessFile randomAccessFile = new RandomAccessFile(logFile, "r");
                            redisTemplate.opsForHash().put(MONITOR_KEY, logList.get(i) + dateStr, len);
                            delKeyMap.put(logList.get(i)+dateStr,"");
                            randomAccessFile.seek(lastTimeFileSize);
                            String tmp = null;
                            ArrayList<MonitorEntry> logDataList = new ArrayList<>();
                            while ((tmp = randomAccessFile.readLine()) != null) {
                                String[] split = tmp.split(",");
                                MonitorEntry monitorEntry = new MonitorEntry();
                                for (String s : split) {
                                    String[] split1 = s.split("=");
                                    Field declaredField = monitorEntry.getClass().getDeclaredField(split1[0]);
                                    declaredField.setAccessible(true);
                                    declaredField.set(monitorEntry, split1.length > 1 ? split1[1] : "");
                                }
                                logDataList.add(monitorEntry);
                                log.debug("====" + tmp + "====");
                            }
                            //进行数据库数据插入
                            if (CollUtil.isNotEmpty(logDataList)) {
                                for (List<MonitorEntry> spiltList : CollUtil.split(logDataList, 50)) {
                                    realtimeProduceInformationMapper.insertMonitor(spiltList);
                                }
                            }
                            randomAccessFile.close();
                        }
                    }
                }
            } catch (Exception e) {
                log.error("read log fail!" + e.getMessage());
                e.printStackTrace();
            } finally {

            }
        }
    }


}
