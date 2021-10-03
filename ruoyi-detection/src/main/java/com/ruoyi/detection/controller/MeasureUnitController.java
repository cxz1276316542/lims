package com.ruoyi.detection.controller;

import java.util.List;
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
import com.ruoyi.detection.domain.MeasureUnit;
import com.ruoyi.detection.service.IMeasureUnitService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 计量单位（检测项目单位、样品单位）Controller
 * 
 * @author ruoyi
 * @date 2021-07-27
 */
@RestController
@RequestMapping("/detection/measureUnit")
public class MeasureUnitController extends BaseController
{
    @Autowired
    private IMeasureUnitService measureUnitService;

    /**
     * 查询计量单位（检测项目单位、样品单位）列表
     */
    @PreAuthorize("@ss.hasPermi('detection:measureUnit:list')")
    @GetMapping("/list")
    public TableDataInfo list(MeasureUnit measureUnit)
    {
        startPage();
        List<MeasureUnit> list = measureUnitService.selectMeasureUnitList(measureUnit);
        return getDataTable(list);
    }

    /**
     * 导出计量单位（检测项目单位、样品单位）列表
     */
    @PreAuthorize("@ss.hasPermi('detection:measureUnit:export')")
    @Log(title = "计量单位（检测项目单位、样品单位）", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(MeasureUnit measureUnit)
    {
        List<MeasureUnit> list = measureUnitService.selectMeasureUnitList(measureUnit);
        ExcelUtil<MeasureUnit> util = new ExcelUtil<MeasureUnit>(MeasureUnit.class);
        return util.exportExcel(list, "计量单位（检测项目单位、样品单位）数据");
    }

    /**
     * 获取计量单位（检测项目单位、样品单位）详细信息
     */
    @PreAuthorize("@ss.hasPermi('detection:measureUnit:query')")
    @GetMapping(value = "/{measureUnitID}")
    public AjaxResult getInfo(@PathVariable("measureUnitID") Long measureUnitID)
    {
        return AjaxResult.success(measureUnitService.selectMeasureUnitById(measureUnitID));
    }

    /**
     * 新增计量单位（检测项目单位、样品单位）
     */
    @PreAuthorize("@ss.hasPermi('detection:measureUnit:add')")
    @Log(title = "计量单位（检测项目单位、样品单位）", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MeasureUnit measureUnit)
    {
        return toAjax(measureUnitService.insertMeasureUnit(measureUnit));
    }

    /**
     * 修改计量单位（检测项目单位、样品单位）
     */
    @PreAuthorize("@ss.hasPermi('detection:measureUnit:edit')")
    @Log(title = "计量单位（检测项目单位、样品单位）", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MeasureUnit measureUnit)
    {
        return toAjax(measureUnitService.updateMeasureUnit(measureUnit));
    }

    /**
     * 删除计量单位（检测项目单位、样品单位）
     */
    @PreAuthorize("@ss.hasPermi('detection:measureUnit:remove')")
    @Log(title = "计量单位（检测项目单位、样品单位）", businessType = BusinessType.DELETE)
	@DeleteMapping("/{measureUnitIDs}")
    public AjaxResult remove(@PathVariable Long[] measureUnitIDs)
    {
        return toAjax(measureUnitService.deleteMeasureUnitByIds(measureUnitIDs));
    }
}
