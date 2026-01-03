package com.ruoyi.manage.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.manage.domain.dto.StatisticsRequest;
import com.ruoyi.manage.service.IStatisticsService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 统计
 *
 * @Project: RuoYi-Vue
 * @Author: YY
 * @CreateTime: 2026-01-03  18:17
 * @Version: 1.0
 */
@RestController
@RequestMapping("/manage/statistics")
public class StatisticsController extends BaseController {
    @Resource
    private IStatisticsService statisticsService;

    /**
     * 统计通过
     * @param request
     * @return
     */
    @GetMapping("pass")
    @PreAuthorize("@ss.hasPermi('manage:statistics:statistics')")
    public AjaxResult passStatistics(StatisticsRequest request) {
        return success(statisticsService.passStatistics(request));
    }

    /**
     * 学生成绩排行
     */
    @GetMapping("rank")
    @PreAuthorize("@ss.hasPermi('manage:statistics:statistics')")
    public AjaxResult rankStatistics(StatisticsRequest request) {
        return success(statisticsService.rankStatistics(request));
    }
}
