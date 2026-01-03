package com.ruoyi.manage.service.impl;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.manage.domain.dto.StatisticsRequest;
import com.ruoyi.manage.domain.statistics.po.StatisticsPo;
import com.ruoyi.manage.domain.statistics.vo.PieStatisticsVo;
import com.ruoyi.manage.enums.GradeStatusEnum;
import com.ruoyi.manage.enums.IsPassedEnum;
import com.ruoyi.manage.mapper.StatisticsMapper;
import com.ruoyi.manage.service.IStatisticsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 统计服务
 *
 * @Project: RuoYi-Vue
 * @Author: YY
 * @CreateTime: 2026-01-03  18:18
 * @Version: 1.0
 */
@Service
public class StatisticsServiceImpl implements IStatisticsService {
    @Resource
    private StatisticsMapper statisticsMapper;

    /**
     * 获取通过率统计
     *
     * @param request
     * @return
     */
    @Override
    public List<PieStatisticsVo> passStatistics(StatisticsRequest request) {
        if (!SecurityUtils.hasPermi("manage:gradeInfo:teacher")) {
            request.setTeacherId(SecurityUtils.getUserId());
        }
        request.setStatus(GradeStatusEnum.GRADE_STATUS_1.getValue());
        List<StatisticsPo> statisticsPos = statisticsMapper.passStatistics(request);
        if (StringUtils.isEmpty(statisticsPos)) {
            return new ArrayList<>();
        }
        List<PieStatisticsVo> pieStatisticsVos = new ArrayList<>();
        for (StatisticsPo statisticsPo : statisticsPos) {
            Optional<IsPassedEnum> enumByValue = IsPassedEnum.getEnumByValue(statisticsPo.getName());
            if (enumByValue.isPresent()) {
                PieStatisticsVo pieStatisticsVo = new PieStatisticsVo();
                pieStatisticsVo.setName(enumByValue.get().getLabel());
                pieStatisticsVo.setValue(statisticsPo.getTotal());
                pieStatisticsVos.add(pieStatisticsVo);
            }
        }
        return pieStatisticsVos;
    }
}
