package com.ruoyi.manage.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.manage.domain.AuditInfo;
import com.ruoyi.manage.service.IAuditInfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 审核信息Controller
 * 
 * @author ruoyi
 * @date 2026-01-03
 */
@RestController
@RequestMapping("/manage/auditInfo")
public class AuditInfoController extends BaseController
{
    @Autowired
    private IAuditInfoService auditInfoService;

    /**
     * 查询审核信息列表
     */
    @PreAuthorize("@ss.hasPermi('manage:auditInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(AuditInfo auditInfo)
    {
        startPage();
        List<AuditInfo> list = auditInfoService.selectAuditInfoList(auditInfo);
        return getDataTable(list);
    }

    /**
     * 导出审核信息列表
     */
    @PreAuthorize("@ss.hasPermi('manage:auditInfo:export')")
    @Log(title = "审核信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AuditInfo auditInfo)
    {
        List<AuditInfo> list = auditInfoService.selectAuditInfoList(auditInfo);
        ExcelUtil<AuditInfo> util = new ExcelUtil<AuditInfo>(AuditInfo.class);
        util.exportExcel(response, list, "审核信息数据");
    }

    /**
     * 获取审核信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('manage:auditInfo:query')")
    @GetMapping(value = "/{auditId}")
    public AjaxResult getInfo(@PathVariable("auditId") Long auditId)
    {
        return success(auditInfoService.selectAuditInfoByAuditId(auditId));
    }

    /**
     * 新增审核信息
     */
    @PreAuthorize("@ss.hasPermi('manage:auditInfo:add')")
    @Log(title = "审核信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AuditInfo auditInfo)
    {
        return toAjax(auditInfoService.insertAuditInfo(auditInfo));
    }

    /**
     * 修改审核信息
     */
    @PreAuthorize("@ss.hasPermi('manage:auditInfo:edit')")
    @Log(title = "审核信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AuditInfo auditInfo)
    {
        return toAjax(auditInfoService.updateAuditInfo(auditInfo));
    }

    /**
     * 删除审核信息
     */
    @PreAuthorize("@ss.hasPermi('manage:auditInfo:remove')")
    @Log(title = "审核信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{auditIds}")
    public AjaxResult remove(@PathVariable Long[] auditIds)
    {
        return toAjax(auditInfoService.deleteAuditInfoByAuditIds(auditIds));
    }
}
