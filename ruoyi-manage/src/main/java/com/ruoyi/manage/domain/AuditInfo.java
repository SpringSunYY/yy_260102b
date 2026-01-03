package com.ruoyi.manage.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 审核信息对象 tb_audit_info
 *
 * @author ruoyi
 * @date 2026-01-03
 */
public class AuditInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 审核编号
     */
    @Excel(name = "审核编号")
    private Long auditId;

    /**
     * 课程名称
     */
    @Excel(name = "课程名称", type = Excel.Type.IMPORT)
    private Long courseId;
    @Excel(name = "课程名称", type = Excel.Type.EXPORT)
    private String courseName;

    /**
     * 成绩编号
     */
    @Excel(name = "成绩编号")
    private Long gradeId;

    /**
     * 学生成绩
     */
    @Excel(name = "学生成绩")
    private BigDecimal score;

    /**
     * 审核状态
     */
    @Excel(name = "审核状态")
    private String status;

    /**
     * 审核原因
     */
    @Excel(name = "审核原因")
    private String auditDesc;

    /**
     * 审核人
     */
    @Excel(name = "审核人", type = Excel.Type.IMPORT)
    private Long userId;
    @Excel(name = "审核人", type = Excel.Type.EXPORT)
    private String userName;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setAuditId(Long auditId) {
        this.auditId = auditId;
    }

    public Long getAuditId() {
        return auditId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }

    public Long getGradeId() {
        return gradeId;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setAuditDesc(String auditDesc) {
        this.auditDesc = auditDesc;
    }

    public String getAuditDesc() {
        return auditDesc;
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
                .append("auditId", getAuditId())
                .append("courseId", getCourseId())
                .append("gradeId", getGradeId())
                .append("score", getScore())
                .append("status", getStatus())
                .append("auditDesc", getAuditDesc())
                .append("userId", getUserId())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}
