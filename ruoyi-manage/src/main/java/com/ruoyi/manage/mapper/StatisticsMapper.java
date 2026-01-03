package com.ruoyi.manage.mapper;

import com.ruoyi.manage.domain.dto.StatisticsRequest;
import com.ruoyi.manage.domain.statistics.po.StatisticsPo;

import java.util.List;

public interface StatisticsMapper {
    List<StatisticsPo> passStatistics(StatisticsRequest request);

    List<StatisticsPo> rankStatistics(StatisticsRequest request);
}
