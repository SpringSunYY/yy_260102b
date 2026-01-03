package com.ruoyi.manage.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.manage.mapper.CourseInfoMapper;
import com.ruoyi.manage.domain.CourseInfo;
import com.ruoyi.manage.service.ICourseInfoService;

/**
 * 课程信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-01-03
 */
@Service
public class CourseInfoServiceImpl implements ICourseInfoService 
{
    @Autowired
    private CourseInfoMapper courseInfoMapper;

    /**
     * 查询课程信息
     * 
     * @param courseId 课程信息主键
     * @return 课程信息
     */
    @Override
    public CourseInfo selectCourseInfoByCourseId(Long courseId)
    {
        return courseInfoMapper.selectCourseInfoByCourseId(courseId);
    }

    /**
     * 查询课程信息列表
     * 
     * @param courseInfo 课程信息
     * @return 课程信息
     */
    @Override
    public List<CourseInfo> selectCourseInfoList(CourseInfo courseInfo)
    {
        return courseInfoMapper.selectCourseInfoList(courseInfo);
    }

    /**
     * 新增课程信息
     * 
     * @param courseInfo 课程信息
     * @return 结果
     */
    @Override
    public int insertCourseInfo(CourseInfo courseInfo)
    {
        courseInfo.setCreateTime(DateUtils.getNowDate());
        return courseInfoMapper.insertCourseInfo(courseInfo);
    }

    /**
     * 修改课程信息
     * 
     * @param courseInfo 课程信息
     * @return 结果
     */
    @Override
    public int updateCourseInfo(CourseInfo courseInfo)
    {
        courseInfo.setUpdateTime(DateUtils.getNowDate());
        return courseInfoMapper.updateCourseInfo(courseInfo);
    }

    /**
     * 批量删除课程信息
     * 
     * @param courseIds 需要删除的课程信息主键
     * @return 结果
     */
    @Override
    public int deleteCourseInfoByCourseIds(Long[] courseIds)
    {
        return courseInfoMapper.deleteCourseInfoByCourseIds(courseIds);
    }

    /**
     * 删除课程信息信息
     * 
     * @param courseId 课程信息主键
     * @return 结果
     */
    @Override
    public int deleteCourseInfoByCourseId(Long courseId)
    {
        return courseInfoMapper.deleteCourseInfoByCourseId(courseId);
    }
}
