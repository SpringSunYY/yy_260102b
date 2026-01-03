package com.ruoyi.manage.service;

import java.util.List;
import com.ruoyi.manage.domain.AuditInfo;

/**
 * 审核信息Service接口
 * 
 * @author ruoyi
 * @date 2026-01-03
 */
public interface IAuditInfoService 
{
    /**
     * 查询审核信息
     * 
     * @param auditId 审核信息主键
     * @return 审核信息
     */
    public AuditInfo selectAuditInfoByAuditId(Long auditId);

    /**
     * 查询审核信息列表
     * 
     * @param auditInfo 审核信息
     * @return 审核信息集合
     */
    public List<AuditInfo> selectAuditInfoList(AuditInfo auditInfo);

    /**
     * 新增审核信息
     * 
     * @param auditInfo 审核信息
     * @return 结果
     */
    public int insertAuditInfo(AuditInfo auditInfo);

    /**
     * 修改审核信息
     * 
     * @param auditInfo 审核信息
     * @return 结果
     */
    public int updateAuditInfo(AuditInfo auditInfo);

    /**
     * 批量删除审核信息
     * 
     * @param auditIds 需要删除的审核信息主键集合
     * @return 结果
     */
    public int deleteAuditInfoByAuditIds(Long[] auditIds);

    /**
     * 删除审核信息信息
     * 
     * @param auditId 审核信息主键
     * @return 结果
     */
    public int deleteAuditInfoByAuditId(Long auditId);
}
