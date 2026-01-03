package com.ruoyi.manage.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 学生成绩信息对象 tb_grade_info
 *
 * @author ruoyi
 * @date 2026-01-03
 */
public class GradeInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 成绩编号
     */
    @Excel(name = "成绩编号")
    private Long gradeId;

    /**
     * 课程名称
     */
    @Excel(name = "课程名称")
    private Long courseId;
    @Excel(name = "课程名称",type = Excel.Type.EXPORT)
    private String courseName;
    @Excel(name = "绩点",type = Excel.Type.EXPORT)
    private BigDecimal credit;

    /**
     * 学生成绩
     */
    @Excel(name = "学生成绩")
    private BigDecimal score;

    /**
     * 及格
     */
    @Excel(name = "及格")
    private String isPassed;

    /**
     * 审核状态
     */
    @Excel(name = "审核状态")
    private String status;

    /**
     * 成绩描述
     */
    @Excel(name = "成绩描述")
    private String gradeDesc;

    /**
     * 老师
     */
    @Excel(name = "老师")
    private Long teacherId;
    @Excel(name = "老师",type = Excel.Type.EXPORT)
    private String teacherName;

    /**
     * 学生
     */
    @Excel(name = "学生")
    private Long userId;
    @Excel(name = "学生",type = Excel.Type.EXPORT)
    private String userName;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }

    public Long getGradeId() {
        return gradeId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setIsPassed(String isPassed) {
        this.isPassed = isPassed;
    }

    public String getIsPassed() {
        return isPassed;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setGradeDesc(String gradeDesc) {
        this.gradeDesc = gradeDesc;
    }

    public String getGradeDesc() {
        return gradeDesc;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("gradeId", getGradeId())
                .append("courseId", getCourseId())
                .append("score", getScore())
                .append("isPassed", getIsPassed())
                .append("status", getStatus())
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
