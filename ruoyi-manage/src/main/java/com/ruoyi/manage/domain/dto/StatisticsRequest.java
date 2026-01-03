package com.ruoyi.manage.domain.dto;

/**
 * 统计请求
 *
 * @Project: RuoYi-Vue
 * @Author: YY
 * @CreateTime: 2026-01-03  18:22
 * @Version: 1.0
 */
public class StatisticsRequest {
    /**
     * 课程编号
     */
    private Long courseId;

    /**
     * 状态
     */
    private String status;

    /**
     * 老师
     */
    private Long teacherId;

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
