package com.ruoyi.manage.service.impl;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.manage.domain.CourseInfo;
import com.ruoyi.manage.domain.GradeInfo;
import com.ruoyi.manage.enums.GradeStatusEnum;
import com.ruoyi.manage.enums.IsPassedEnum;
import com.ruoyi.manage.mapper.CourseInfoMapper;
import com.ruoyi.manage.mapper.GradeInfoMapper;
import com.ruoyi.manage.service.IGradeInfoService;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * 学生成绩信息Service业务层处理
 *
 * @author ruoyi
 * @date 2026-01-03
 */
@Service
public class GradeInfoServiceImpl implements IGradeInfoService {
    @Autowired
    private GradeInfoMapper gradeInfoMapper;

    @Autowired
    private CourseInfoMapper courseInfoMapper;

    @Resource
    private ISysUserService sysUserService;

    /**
     * 查询学生成绩信息
     *
     * @param gradeId 学生成绩信息主键
     * @return 学生成绩信息
     */
    @Override
    public GradeInfo selectGradeInfoByGradeId(Long gradeId) {
        return gradeInfoMapper.selectGradeInfoByGradeId(gradeId);
    }

    /**
     * 查询学生成绩信息列表
     *
     * @param gradeInfo 学生成绩信息
     * @return 学生成绩信息
     */
    @Override
    public List<GradeInfo> selectGradeInfoList(GradeInfo gradeInfo) {
        //如果是老师
        if (SecurityUtils.hasRole("teacher")&&!SecurityUtils.isAdmin(SecurityUtils.getUserId())) {
            gradeInfo.setTeacherId(SecurityUtils.getUserId());
        }
        //如果是学生
        if (SecurityUtils.hasRole("student")&&!SecurityUtils.isAdmin(SecurityUtils.getUserId())) {
            gradeInfo.setUserId(SecurityUtils.getUserId());
        }
        List<GradeInfo> gradeInfos = gradeInfoMapper.selectGradeInfoList(gradeInfo);
        for (GradeInfo info : gradeInfos) {
            SysUser sysUser = sysUserService.selectUserById(info.getTeacherId());
            if (StringUtils.isNotNull(sysUser)) {
                info.setTeacherName(sysUser.getUserName());
            }
            SysUser studentUser = sysUserService.selectUserById(info.getUserId());
            if (StringUtils.isNotNull(studentUser)) {
                info.setUserName(studentUser.getUserName());
            }
            CourseInfo courseInfo = courseInfoMapper.selectCourseInfoByCourseId(info.getCourseId());
            if (StringUtils.isNotNull(courseInfo)) {
                info.setCourseName(courseInfo.getCourseName());
                info.setCredit(courseInfo.getCredit());
            }
        }
        return gradeInfos;
    }

    /**
     * 新增学生成绩信息
     *
     * @param gradeInfo 学生成绩信息
     * @return 结果
     */
    @Override
    public int insertGradeInfo(GradeInfo gradeInfo) {
        //查询课程是否存在
        CourseInfo courseInfo = courseInfoMapper.selectCourseInfoByCourseId(gradeInfo.getCourseId());
        if (StringUtils.isNull(courseInfo)) {
            throw new RuntimeException("课程不存在");
        }
        //如果已选择课程
        GradeInfo gradeQuery = new GradeInfo();
        Long userId = SecurityUtils.getUserId();
        gradeQuery.setUserId(userId);
        gradeQuery.setCourseId(gradeInfo.getCourseId());
        List<GradeInfo> gradeInfos = gradeInfoMapper.selectGradeInfoList(gradeQuery);
        if (StringUtils.isNotEmpty(gradeInfos)) {
            throw new RuntimeException("已选择课程");
        }
        //初始化状态
        gradeInfo.setStatus(GradeStatusEnum.GRADE_STATUS_0.getValue());
        gradeInfo.setTeacherId(courseInfo.getTeacherId());
        gradeInfo.setUserId(userId);
        gradeInfo.setCreateBy(SecurityUtils.getUsername());
        gradeInfo.setCreateTime(DateUtils.getNowDate());
        return gradeInfoMapper.insertGradeInfo(gradeInfo);
    }

    /**
     * 修改学生成绩信息
     *
     * @param gradeInfo 学生成绩信息
     * @return 结果
     */
    @Override
    public int updateGradeInfo(GradeInfo gradeInfo) {
        //查询课程是否存在
        CourseInfo courseInfo = courseInfoMapper.selectCourseInfoByCourseId(gradeInfo.getCourseId());
        if (StringUtils.isNull(courseInfo)) {
            throw new RuntimeException("课程不存在");
        }
        //如果分数大于60
        if (gradeInfo.getScore().compareTo(new BigDecimal(60)) > 0) {
            gradeInfo.setIsPassed(IsPassedEnum.IS_PASSED_0.getValue());
        } else {
            gradeInfo.setIsPassed(IsPassedEnum.IS_PASSED_1.getValue());
        }
        //如果课程审核已经通过
        if (gradeInfo.getStatus().equals(GradeStatusEnum.GRADE_STATUS_1.getValue())) {
            throw new RuntimeException("课程审核已经通过,不可修改");
        }
        gradeInfo.setUpdateTime(DateUtils.getNowDate());
        return gradeInfoMapper.updateGradeInfo(gradeInfo);
    }

    /**
     * 批量删除学生成绩信息
     *
     * @param gradeIds 需要删除的学生成绩信息主键
     * @return 结果
     */
    @Override
    public int deleteGradeInfoByGradeIds(Long[] gradeIds) {
        return gradeInfoMapper.deleteGradeInfoByGradeIds(gradeIds);
    }

    /**
     * 删除学生成绩信息信息
     *
     * @param gradeId 学生成绩信息主键
     * @return 结果
     */
    @Override
    public int deleteGradeInfoByGradeId(Long gradeId) {
        return gradeInfoMapper.deleteGradeInfoByGradeId(gradeId);
    }
}
