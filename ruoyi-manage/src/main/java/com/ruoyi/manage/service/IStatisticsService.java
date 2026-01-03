package com.ruoyi.manage.service;

import com.ruoyi.manage.domain.dto.StatisticsRequest;
import com.ruoyi.manage.domain.statistics.vo.BarStatisticsVo;
import com.ruoyi.manage.domain.statistics.vo.PieStatisticsVo;

import java.util.List;

public interface IStatisticsService {
    List<PieStatisticsVo> passStatistics(StatisticsRequest request);

    BarStatisticsVo rankStatistics(StatisticsRequest request);
}
