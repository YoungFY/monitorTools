package com.reda.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Title:
 * @Description:
 * @Author: guowl
 * @version： 1.0
 * @Date:2022/4/1
 * @Copyright: Copyright(c)2022 RedaFlight.com All Rights Reserved
 */

@ConfigurationProperties(prefix = "monitor")
@Configuration
@Component(value = "monitorConfigProperties")
@Order(3)
public class MonitorConfigProperties {

    private volatile String appFile;

    private volatile String toolsFile;

    private volatile String filePath;

    private volatile String caacServerIp;

    public String getAppFile() {
        return appFile;
    }

    public void setAppFile(String appFile) {
        this.appFile = appFile;
    }

    public String getToolsFile() {
        return toolsFile;
    }

    public void setToolsFile(String toolsFile) {
        this.toolsFile = toolsFile;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getCaacServerIp() {
        return caacServerIp;
    }

    public void setCaacServerIp(String caacServerIp) {
        this.caacServerIp = caacServerIp;
    }
}
