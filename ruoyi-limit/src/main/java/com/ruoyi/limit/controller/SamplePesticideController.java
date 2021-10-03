package com.ruoyi.limit.controller;

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
import com.ruoyi.limit.domain.SamplePesticide;
import com.ruoyi.limit.service.ISamplePesticideService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 样品-检测项目Controller
 * 
 * @author wlf
 * @date 2021-08-28
 */
@RestController
@RequestMapping("/limit/samplePesticide")
public class SamplePesticideController extends BaseController
{
    @Autowired
    private ISamplePesticideService samplePesticideService;

    /**
     * 查询样品-检测项目列表
     */
    @PreAuthorize("@ss.hasPermi('limit:samplePesticide:list')")
    @GetMapping("/list")
    public TableDataInfo list(SamplePesticide samplePesticide)
    {
        startPage();
        List<SamplePesticide> list = samplePesticideService.selectSamplePesticideList(samplePesticide);
        return getDataTable(list);
    }

    /**
     * 导出样品-检测项目列表
     */
    @PreAuthorize("@ss.hasPermi('limit:samplePesticide:export')")
    @Log(title = "样品-检测项目", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SamplePesticide samplePesticide)
    {
        List<SamplePesticide> list = samplePesticideService.selectSamplePesticideList(samplePesticide);
        ExcelUtil<SamplePesticide> util = new ExcelUtil<SamplePesticide>(SamplePesticide.class);
        return util.exportExcel(list, "样品-检测项目数据");
    }

    /**
     * 获取样品-检测项目详细信息
     */
    @PreAuthorize("@ss.hasPermi('limit:samplePesticide:query')")
    @GetMapping(value = "/{samplePesticideID}")
    public AjaxResult getInfo(@PathVariable("samplePesticideID") Long samplePesticideID)
    {
        return AjaxResult.success(samplePesticideService.selectSamplePesticideById(samplePesticideID));
    }

    /**
     * 新增样品-检测项目
     */
    @PreAuthorize("@ss.hasPermi('limit:samplePesticide:add')")
    @Log(title = "样品-检测项目", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SamplePesticide samplePesticide)
    {
        String  msg = samplePesticideService.insertSamplePesticide(samplePesticide);
        if(msg.equals("操作成功"))
        {
            return AjaxResult.success(msg);
        }
        return AjaxResult.error(msg);
    }

    /**
     * 修改样品-检测项目
     */
    @PreAuthorize("@ss.hasPermi('limit:samplePesticide:edit')")
    @Log(title = "样品-检测项目", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SamplePesticide samplePesticide)
    {
        return toAjax(samplePesticideService.updateSamplePesticide(samplePesticide));
    }

    /**
     * 删除样品-检测项目
     */
    @PreAuthorize("@ss.hasPermi('limit:samplePesticide:remove')")
    @Log(title = "样品-检测项目", businessType = BusinessType.DELETE)
	@DeleteMapping("/{samplePesticideIDs}")
    public AjaxResult remove(@PathVariable Long[] samplePesticideIDs)
    {
        return toAjax(samplePesticideService.deleteSamplePesticideByIds(samplePesticideIDs));
    }
}
