package com.ruoyi.manage.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 学生成绩信息对象 tb_grade_info
 * 
 * @author ruoyi
 * @date 2026-01-03
 */
public class GradeInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 成绩编号 */
    @Excel(name = "成绩编号")
    private Long gradeId;

    /** 课程名称 */
    @Excel(name = "课程名称")
    private Long courseId;

    /** 学生成绩 */
    @Excel(name = "学生成绩")
    private BigDecimal score;

    /** 及格 */
    @Excel(name = "及格")
    private String isPassed;

    /** 成绩描述 */
    @Excel(name = "成绩描述")
    private String gradeDesc;

    /** 老师 */
    @Excel(name = "老师")
    private Long teacherId;

    /** 学生 */
    @Excel(name = "学生")
    private Long userId;

    public void setGradeId(Long gradeId) 
    {
        this.gradeId = gradeId;
    }

    public Long getGradeId() 
    {
        return gradeId;
    }

    public void setCourseId(Long courseId) 
    {
        this.courseId = courseId;
    }

    public Long getCourseId() 
    {
        return courseId;
    }

    public void setScore(BigDecimal score) 
    {
        this.score = score;
    }

    public BigDecimal getScore() 
    {
        return score;
    }

    public void setIsPassed(String isPassed) 
    {
        this.isPassed = isPassed;
    }

    public String getIsPassed() 
    {
        return isPassed;
    }

    public void setGradeDesc(String gradeDesc) 
    {
        this.gradeDesc = gradeDesc;
    }

    public String getGradeDesc() 
    {
        return gradeDesc;
    }

    public void setTeacherId(Long teacherId) 
    {
        this.teacherId = teacherId;
    }

    public Long getTeacherId() 
    {
        return teacherId;
    }

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("gradeId", getGradeId())
            .append("courseId", getCourseId())
            .append("score", getScore())
            .append("isPassed", getIsPassed())
            .append("gradeDesc", getGradeDesc())
            .append("teacherId", getTeacherId())
            .append("userId", getUserId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
