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
import com.ruoyi.limit.domain.Standard;
import com.ruoyi.limit.service.IStandardService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 检测标准Controller
 * 
 * @author lkun
 * @date 2021-07-20
 */
@RestController
@RequestMapping("/limit/standard")
public class StandardController extends BaseController
{
    @Autowired
    private IStandardService standardService;

    /**
     * 查询检测标准列表
     */
    @PreAuthorize("@ss.hasPermi('limit:standard:list')")
    @GetMapping("/list")
    public TableDataInfo list(Standard standard)
    {
        startPage();
        List<Standard> list = standardService.selectStandardList(standard);
        return getDataTable(list);
    }

    /**
     * 导出检测标准列表
     */
    @PreAuthorize("@ss.hasPermi('limit:standard:export')")
    @Log(title = "检测标准", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Standard standard)
    {
        List<Standard> list = standardService.selectStandardList(standard);
        ExcelUtil<Standard> util = new ExcelUtil<Standard>(Standard.class);
        return util.exportExcel(list, "检测标准数据");
    }

    /**
     * 获取检测标准详细信息
     */
    @PreAuthorize("@ss.hasPermi('limit:standard:query')")
    @GetMapping(value = "/{standard}")
    public AjaxResult getInfo(@PathVariable("standard") Long standard)
    {
        return AjaxResult.success(standardService.selectStandardById(standard));
    }

    /**
     * 新增检测标准
     */
    @PreAuthorize("@ss.hasPermi('limit:standard:add')")
    @Log(title = "检测标准", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Standard standard)
    {
        return toAjax(standardService.insertStandard(standard));
    }

    /**
     * 修改检测标准
     */
    @PreAuthorize("@ss.hasPermi('limit:standard:edit')")
    @Log(title = "检测标准", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Standard standard)
    {
        return toAjax(standardService.updateStandard(standard));
    }

    /**
     * 删除检测标准
     */
    @PreAuthorize("@ss.hasPermi('limit:standard:remove')")
    @Log(title = "检测标准", businessType = BusinessType.DELETE)
	@DeleteMapping("/{standards}")
    public AjaxResult remove(@PathVariable Long[] standards)
    {
        return toAjax(standardService.deleteStandardByIds(standards));
    }
}
