package com.ruoyi.manage.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 课程信息对象 tb_course_info
 *
 * @author ruoyi
 * @date 2026-01-03
 */
public class CourseInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 课程编号
     */
    @Excel(name = "课程编号")
    private Long courseId;

    /**
     * 课程名称
     */
    @Excel(name = "课程名称")
    private String courseName;

    /**
     * 绩点
     */
    @Excel(name = "绩点")
    private BigDecimal credit;

    /**
     * 课程描述
     */
    @Excel(name = "课程描述")
    private String courseDesc;

    /**
     * 老师
     */
    @Excel(name = "老师")
    private Long teacherId;
    private String teacherName;

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public void setCourseDesc(String courseDesc) {
        this.courseDesc = courseDesc;
    }

    public String getCourseDesc() {
        return courseDesc;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("courseId", getCourseId())
                .append("courseName", getCourseName())
                .append("credit", getCredit())
                .append("courseDesc", getCourseDesc())
                .append("teacherId", getTeacherId())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}
