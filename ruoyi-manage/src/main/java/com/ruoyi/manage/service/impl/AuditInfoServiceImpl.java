package com.ruoyi.manage.service.impl;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.manage.domain.AuditInfo;
import com.ruoyi.manage.domain.CourseInfo;
import com.ruoyi.manage.domain.GradeInfo;
import com.ruoyi.manage.enums.GradeStatusEnum;
import com.ruoyi.manage.mapper.AuditInfoMapper;
import com.ruoyi.manage.mapper.CourseInfoMapper;
import com.ruoyi.manage.mapper.GradeInfoMapper;
import com.ruoyi.manage.service.IAuditInfoService;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 审核信息Service业务层处理
 *
 * @author ruoyi
 * @date 2026-01-03
 */
@Service
public class AuditInfoServiceImpl implements IAuditInfoService {
    @Autowired
    private AuditInfoMapper auditInfoMapper;

    @Autowired
    private GradeInfoMapper gradeInfoMapper;

    @Autowired
    private CourseInfoMapper courseInfoMapper;

    @Resource
    private ISysUserService sysUserService;

    /**
     * 查询审核信息
     *
     * @param auditId 审核信息主键
     * @return 审核信息
     */
    @Override
    public AuditInfo selectAuditInfoByAuditId(Long auditId) {
        return auditInfoMapper.selectAuditInfoByAuditId(auditId);
    }

    /**
     * 查询审核信息列表
     *
     * @param auditInfo 审核信息
     * @return 审核信息
     */
    @Override
    public List<AuditInfo> selectAuditInfoList(AuditInfo auditInfo) {
        List<AuditInfo> auditInfos = auditInfoMapper.selectAuditInfoList(auditInfo);
        for (AuditInfo info : auditInfos) {
            CourseInfo courseInfo = courseInfoMapper.selectCourseInfoByCourseId(info.getCourseId());
            if (StringUtils.isNotNull(courseInfo)) {
                info.setCourseName(courseInfo.getCourseName());
            }
            SysUser sysUser = sysUserService.selectUserById(info.getUserId());
            if (StringUtils.isNotNull(sysUser)) {
                info.setUserName(sysUser.getUserName());
            }
        }
        return auditInfos;
    }

    /**
     * 新增审核信息
     *
     * @param auditInfo 审核信息
     * @return 结果
     */
    @Override
    public int insertAuditInfo(AuditInfo auditInfo) {
        //查询成绩是否存在
        GradeInfo gradeInfo = gradeInfoMapper.selectGradeInfoByGradeId(auditInfo.getGradeId());
        if (StringUtils.isNull(gradeInfo)) {
            throw new ServiceException("成绩不存在");
        }
        //如果已经同意
        if (gradeInfo.getStatus().equals(GradeStatusEnum.GRADE_STATUS_1.getValue())) {
            throw new RuntimeException("课程审核已经通过,不可修改");
        }
        if (StringUtils.isNull(gradeInfo.getScore())) {
            throw new ServiceException("请先评分");
        }
        auditInfo.setCourseId(gradeInfo.getCourseId());
        auditInfo.setScore(gradeInfo.getScore());
        auditInfo.setUserId(SecurityUtils.getUserId());
        auditInfo.setCreateTime(DateUtils.getNowDate());
        gradeInfo.setStatus(auditInfo.getStatus());
        gradeInfoMapper.updateGradeInfo(gradeInfo);
        return auditInfoMapper.insertAuditInfo(auditInfo);
    }

    /**
     * 修改审核信息
     *
     * @param auditInfo 审核信息
     * @return 结果
     */
    @Override
    public int updateAuditInfo(AuditInfo auditInfo) {
        auditInfo.setUpdateTime(DateUtils.getNowDate());
        return auditInfoMapper.updateAuditInfo(auditInfo);
    }

    /**
     * 批量删除审核信息
     *
     * @param auditIds 需要删除的审核信息主键
     * @return 结果
     */
    @Override
    public int deleteAuditInfoByAuditIds(Long[] auditIds) {
        return auditInfoMapper.deleteAuditInfoByAuditIds(auditIds);
    }

    /**
     * 删除审核信息信息
     *
     * @param auditId 审核信息主键
     * @return 结果
     */
    @Override
    public int deleteAuditInfoByAuditId(Long auditId) {
        return auditInfoMapper.deleteAuditInfoByAuditId(auditId);
    }
}
