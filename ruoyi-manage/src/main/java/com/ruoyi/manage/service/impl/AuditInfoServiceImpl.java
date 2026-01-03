package com.ruoyi.manage.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.manage.mapper.AuditInfoMapper;
import com.ruoyi.manage.domain.AuditInfo;
import com.ruoyi.manage.service.IAuditInfoService;

/**
 * 审核信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-01-03
 */
@Service
public class AuditInfoServiceImpl implements IAuditInfoService 
{
    @Autowired
    private AuditInfoMapper auditInfoMapper;

    /**
     * 查询审核信息
     * 
     * @param auditId 审核信息主键
     * @return 审核信息
     */
    @Override
    public AuditInfo selectAuditInfoByAuditId(Long auditId)
    {
        return auditInfoMapper.selectAuditInfoByAuditId(auditId);
    }

    /**
     * 查询审核信息列表
     * 
     * @param auditInfo 审核信息
     * @return 审核信息
     */
    @Override
    public List<AuditInfo> selectAuditInfoList(AuditInfo auditInfo)
    {
        return auditInfoMapper.selectAuditInfoList(auditInfo);
    }

    /**
     * 新增审核信息
     * 
     * @param auditInfo 审核信息
     * @return 结果
     */
    @Override
    public int insertAuditInfo(AuditInfo auditInfo)
    {
        auditInfo.setCreateTime(DateUtils.getNowDate());
        return auditInfoMapper.insertAuditInfo(auditInfo);
    }

    /**
     * 修改审核信息
     * 
     * @param auditInfo 审核信息
     * @return 结果
     */
    @Override
    public int updateAuditInfo(AuditInfo auditInfo)
    {
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
    public int deleteAuditInfoByAuditIds(Long[] auditIds)
    {
        return auditInfoMapper.deleteAuditInfoByAuditIds(auditIds);
    }

    /**
     * 删除审核信息信息
     * 
     * @param auditId 审核信息主键
     * @return 结果
     */
    @Override
    public int deleteAuditInfoByAuditId(Long auditId)
    {
        return auditInfoMapper.deleteAuditInfoByAuditId(auditId);
    }
}
