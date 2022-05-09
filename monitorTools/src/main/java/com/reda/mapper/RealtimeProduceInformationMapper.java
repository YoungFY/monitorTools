package com.reda.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.reda.entry.MonitorEntry;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Title:
 * @Description:
 * @Author: guowl
 * @version： 1.0
 * @Date:2022/3/31
 * @Copyright: Copyright(c)2022 RedaFlight.com All Rights Reserved
 */
@Mapper
public interface RealtimeProduceInformationMapper extends BaseMapper<MonitorEntry> {


    public void insertMonitor(@Param("list") List<MonitorEntry> list);


}
