package com.reda.entry;

import com.reda.util.DateUtils;
import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;

/**
 * @Title:
 * @Description:
 * @Author: guowl
 * @version： 1.0
 * @Date:2022/3/31
 * @Copyright: Copyright(c)2022 RedaFlight.com All Rights Reserved
 */
public class MonitorEntry {
    private String type;
    private String NAME;
    private String total;
    private String IP;
    private String port;
    private String err_port;
    private String openfile;
    private String CPU;
    private String buffCache;
    private String disk;
    private String diskUsed;
    private String totalOpenfilesNum;
    private String openfilesNum;
    private String cpuUsed;
    private String used;
    private String update_time;
    private String free;
    private String buff;
    private String total_openfile;
    private Long updateTime;
    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyymmdd mmddss");
   private static DateTimeFormatter formatter = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd hh:mm:ss")
            .toFormatter();
    private static DateTimeFormatter formatter2= new DateTimeFormatterBuilder()
            .appendPattern("yyyymmdd mmddss")
            .toFormatter();
    public String getFree() {
        return free;
    }

    public void setFree(String free) {
        this.free = free;
    }

    public String getBuff() {
        return buff;
    }

    public void setBuff(String buff) {
        this.buff = buff;
    }

    public String getTotal_openfile() {
        return total_openfile;
    }

    public void setTotal_openfile(String total_openfile) {
        this.total_openfile = total_openfile;
    }

    public String getUsed() {
        return used;
    }

    public void setUsed(String used) {
        this.used = used;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getErr_port() {
        return err_port;
    }

    public void setErr_port(String err_port) {
        this.err_port = err_port;
    }

    public String getOpenfile() {
        return openfile;
    }

    public void setOpenfile(String openfile) {
        this.openfile = openfile;
    }

    public String getCPU() {
        return CPU;
    }

    public void setCPU(String CPU) {
        this.CPU = CPU;
    }

    public String getBuffCache() {
        return buffCache;
    }

    public void setBuffCache(String buffCache) {
        this.buffCache = buffCache;
    }

    public String getDisk() {
        return disk;
    }

    public void setDisk(String disk) {
        this.disk = disk;
    }

    public String getDiskUsed() {
        return diskUsed;
    }

    public void setDiskUsed(String diskUsed) {
        this.diskUsed = diskUsed;
    }

    public String getTotalOpenfilesNum() {
        return totalOpenfilesNum;
    }

    public void setTotalOpenfilesNum(String totalOpenfilesNum) {
        this.totalOpenfilesNum = totalOpenfilesNum;
    }

    public String getOpenfilesNum() {
        return openfilesNum;
    }

    public void setOpenfilesNum(String openfilesNum) {
        this.openfilesNum = openfilesNum;
    }

    public String getCpuUsed() {
        return cpuUsed;
    }

    public void setCpuUsed(String cpuUsed) {
        this.cpuUsed = cpuUsed;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {

        if (update_time.contains("/")) {
            try {

                LocalDateTime localDateTime = LocalDateTime.parse(update_time, DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
                this.updateTime = localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            if(update_time==null){
                this.updateTime=System.currentTimeMillis();
            }
            try {
                LocalDateTime localDateTime = LocalDateTime.parse(update_time, DateTimeFormatter.ofPattern("yyyymmdd mmddss"));
                this.updateTime = localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.update_time = update_time;
    }

    public Long getUpdateTime() {
        synchronized (MonitorEntry.class) {
            if (update_time.contains("/")) {
                try {
                    LocalDateTime localDateTime = LocalDateTime.parse(update_time, DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
                    this.updateTime = localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                if (update_time == null) {
                    this.updateTime = System.currentTimeMillis();
                }
                try {
                    LocalDateTime localDateTime = LocalDateTime.parse(update_time, DateTimeFormatter.ofPattern("yyyymmdd mmddss"));
                    this.updateTime = localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            this.update_time = update_time;
            return updateTime;
        }
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public static void main(String[] args) throws ParseException {

        Date date = new Date();

        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd hh:mm:ss")
                .toFormatter();

        LocalDateTime parse = LocalDateTime.parse("2022-05-06 12:12:12",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(parse);




    }
}
