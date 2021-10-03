package com.ruoyi.commission.controller;

import com.ruoyi.commission.domain.Commission;
import com.ruoyi.commission.domain.CommissionManage;
import com.ruoyi.commission.service.ICommissionProgressService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 委托进度Controller
 *
 * @author lkun
 * @date 2021-08-03
 */
@RestController
@RequestMapping("/commission/commissionProgress")
public class CommissionProgressController extends BaseController
{
    @Autowired
    private ICommissionProgressService commissionService;

    /**
     * 查询委托进度列表
     */
    @PreAuthorize("@ss.hasPermi('commissionProgress:commissionProgress:list')")
    @GetMapping("/list")
    public TableDataInfo list(CommissionManage commission)
    {
        startPage();
        List<Commission> list = commissionService.selectCommissionList(commission);
        return getDataTable(list);
    }

    /**
     * 导出委托进度列表
     */
    @PreAuthorize("@ss.hasPermi('commissionProgress:commissionProgress:export')")
    @Log(title = "委托进度", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(CommissionManage commission)
    {
        List<Commission> list = commissionService.selectCommissionList(commission);
        ExcelUtil<Commission> util = new ExcelUtil<Commission>(Commission.class);
        return util.exportExcel(list, "委托进度数据");
    }

    /**
     * 获取委托进度详细信息
     */
    @PreAuthorize("@ss.hasPermi('commissionProgress:commissionProgress:query')")
    @GetMapping(value = "/{sampleID}")
    public AjaxResult getInfo(@PathVariable("sampleID") Long sampleID)
    {
        return AjaxResult.success(commissionService.selectCommissionById(sampleID));
    }

    /**
     * 新增委托进度
     */
    @PreAuthorize("@ss.hasPermi('commissionProgress:commissionProgress:add')")
    @Log(title = "委托进度", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Commission commission)
    {
        return toAjax(commissionService.insertCommission(commission));
    }

    /**
     * 修改委托进度
     */
    @PreAuthorize("@ss.hasPermi('commissionProgress:commissionProgress:edit')")
    @Log(title = "委托进度", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Commission commission)
    {
        return toAjax(commissionService.updateCommission(commission));
    }

    /**
     * 删除委托进度
     */
    @PreAuthorize("@ss.hasPermi('commissionProgress:commissionProgress:remove')")
    @Log(title = "委托进度", businessType = BusinessType.DELETE)
    @DeleteMapping("/{sampleIDs}")
    public AjaxResult remove(@PathVariable Long[] sampleIDs)
    {
        return toAjax(commissionService.deleteCommissionByIds(sampleIDs));
    }

    /**
     * 获取样品进度
     * @return
     */
    @GetMapping("/getProgress")
    public AjaxResult getProgress(long sampleId){
        return AjaxResult.success(commissionService.getProgress(sampleId));
    }

    /**
     * 获取委托单进度
     * @param sampleId
     * @return
     */
    @GetMapping("getOrderProgress")
    public AjaxResult getOrderProgress(long sampleId){
        return AjaxResult.success(commissionService.getOrderProgress(sampleId));
    }
}
