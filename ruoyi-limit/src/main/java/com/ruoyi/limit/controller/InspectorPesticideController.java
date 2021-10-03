package com.ruoyi.limit.controller;

import java.util.List;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.limit.domain.InspectorPesticide;
import com.ruoyi.limit.service.IInspectorPesticideService;
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
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 检测员-检测项目Controller
 *
 * @author dlk
 * @date 2021-08-28
 */
@RestController
@RequestMapping("/limit/inspectorPesticide")
public class InspectorPesticideController extends BaseController
{
    @Autowired
    private IInspectorPesticideService inspectorPesticideService;

    /**
     * 查询检测员-检测项目列表
     */
    @PreAuthorize("@ss.hasPermi('limit:inspectorPesticide:list')")
    @GetMapping("/list")
    public TableDataInfo list(InspectorPesticide inspectorPesticide)
    {
        startPage();
        List<InspectorPesticide> list = inspectorPesticideService.selectinspectorPesticideList(inspectorPesticide);
        return getDataTable(list);
    }

    /**
     * 导出检测员-检测项目列表
     */
    @PreAuthorize("@ss.hasPermi('limit:inspectorPesticide:export')")
    @Log(title = "检测员-检测项目", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(InspectorPesticide inspectorPesticide)
    {
        List<InspectorPesticide> list = inspectorPesticideService.selectinspectorPesticideList(inspectorPesticide);
        ExcelUtil<InspectorPesticide> util = new ExcelUtil<InspectorPesticide>(InspectorPesticide.class);
        return util.exportExcel(list, "检测员-检测项目数据");
    }

    /**
     * 获取检测员-检测项目详细信息
     */
    @PreAuthorize("@ss.hasPermi('limit:inspectorPesticide:query')")
    @GetMapping(value = "/{inspectorPesticideID}")
    public AjaxResult getInfo(@PathVariable("inspectorPesticideID") Long inspectorPesticideID)
    {
        return AjaxResult.success(inspectorPesticideService.selectinspectorPesticideById(inspectorPesticideID));
    }

    /**
     * 新增检测员-检测项目
     */
    @PreAuthorize("@ss.hasPermi('limit:inspectorPesticide:add')")
    @Log(title = "检测员-检测项目", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody InspectorPesticide inspectorPesticide)
    {
        String msg = inspectorPesticideService.insertInspectorPesticide(inspectorPesticide);
        if(msg.equals("操作成功")){
            return AjaxResult.success(msg);
        }

        return AjaxResult.error(msg);
    }

    /**
     * 修改检测员-检测项目
     */
    @PreAuthorize("@ss.hasPermi('limit:inspectorPesticide:edit')")
    @Log(title = "检测员-检测项目", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody InspectorPesticide inspectorPesticide)
    {
        return toAjax(inspectorPesticideService.updateinspectorPesticide(inspectorPesticide));
    }

    /**
     * 删除检测员-检测项目
     */
    @PreAuthorize("@ss.hasPermi('limit:inspectorPesticide:remove')")
    @Log(title = "检测员-检测项目", businessType = BusinessType.DELETE)
	@DeleteMapping("/{inspectorPesticideIDs}")
    public AjaxResult remove(@PathVariable Long[] inspectorPesticideIDs)
    {
        return toAjax(inspectorPesticideService.deleteinspectorPesticideByIds(inspectorPesticideIDs));
    }
}
