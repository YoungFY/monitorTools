package com.reda.controller;

import com.reda.service.MonitorReadLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Title:
 * @Description:
 * @Author: guowl
 * @version： 1.0
 * @Date:2022/3/31
 * @Copyright: Copyright(c)2022 RedaFlight.com All Rights Reserved
 */
@Controller
@RequestMapping
public class TestController {

    @Autowired
    private MonitorReadLogService monitorReadLogService;

    @GetMapping("/getData")
    public void test(){

    }







}
