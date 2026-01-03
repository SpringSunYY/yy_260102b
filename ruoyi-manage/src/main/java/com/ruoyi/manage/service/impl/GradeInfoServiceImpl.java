package com.ruoyi.manage.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.manage.mapper.GradeInfoMapper;
import com.ruoyi.manage.domain.GradeInfo;
import com.ruoyi.manage.service.IGradeInfoService;

/**
 * 学生成绩信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-01-03
 */
@Service
public class GradeInfoServiceImpl implements IGradeInfoService 
{
    @Autowired
    private GradeInfoMapper gradeInfoMapper;

    /**
     * 查询学生成绩信息
     * 
     * @param gradeId 学生成绩信息主键
     * @return 学生成绩信息
     */
    @Override
    public GradeInfo selectGradeInfoByGradeId(Long gradeId)
    {
        return gradeInfoMapper.selectGradeInfoByGradeId(gradeId);
    }

    /**
     * 查询学生成绩信息列表
     * 
     * @param gradeInfo 学生成绩信息
     * @return 学生成绩信息
     */
    @Override
    public List<GradeInfo> selectGradeInfoList(GradeInfo gradeInfo)
    {
        return gradeInfoMapper.selectGradeInfoList(gradeInfo);
    }

    /**
     * 新增学生成绩信息
     * 
     * @param gradeInfo 学生成绩信息
     * @return 结果
     */
    @Override
    public int insertGradeInfo(GradeInfo gradeInfo)
    {
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
    public int updateGradeInfo(GradeInfo gradeInfo)
    {
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
    public int deleteGradeInfoByGradeIds(Long[] gradeIds)
    {
        return gradeInfoMapper.deleteGradeInfoByGradeIds(gradeIds);
    }

    /**
     * 删除学生成绩信息信息
     * 
     * @param gradeId 学生成绩信息主键
     * @return 结果
     */
    @Override
    public int deleteGradeInfoByGradeId(Long gradeId)
    {
        return gradeInfoMapper.deleteGradeInfoByGradeId(gradeId);
    }
}
