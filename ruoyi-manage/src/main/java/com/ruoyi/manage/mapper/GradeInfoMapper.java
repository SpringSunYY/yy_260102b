package com.ruoyi.manage.mapper;

import java.util.List;
import com.ruoyi.manage.domain.GradeInfo;

/**
 * 学生成绩信息Mapper接口
 * 
 * @author ruoyi
 * @date 2026-01-03
 */
public interface GradeInfoMapper 
{
    /**
     * 查询学生成绩信息
     * 
     * @param gradeId 学生成绩信息主键
     * @return 学生成绩信息
     */
    public GradeInfo selectGradeInfoByGradeId(Long gradeId);

    /**
     * 查询学生成绩信息列表
     * 
     * @param gradeInfo 学生成绩信息
     * @return 学生成绩信息集合
     */
    public List<GradeInfo> selectGradeInfoList(GradeInfo gradeInfo);

    /**
     * 新增学生成绩信息
     * 
     * @param gradeInfo 学生成绩信息
     * @return 结果
     */
    public int insertGradeInfo(GradeInfo gradeInfo);

    /**
     * 修改学生成绩信息
     * 
     * @param gradeInfo 学生成绩信息
     * @return 结果
     */
    public int updateGradeInfo(GradeInfo gradeInfo);

    /**
     * 删除学生成绩信息
     * 
     * @param gradeId 学生成绩信息主键
     * @return 结果
     */
    public int deleteGradeInfoByGradeId(Long gradeId);

    /**
     * 批量删除学生成绩信息
     * 
     * @param gradeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteGradeInfoByGradeIds(Long[] gradeIds);
}
