package com.ruoyi.system.controller;

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
import com.ruoyi.system.domain.Number;
import com.ruoyi.system.service.INumberService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 编号生成Controller
 *
 * @author lkun
 * @date 2021-08-22
 */
@RestController
@RequestMapping("/system/number")
public class NumberController extends BaseController
{
    @Autowired
    private INumberService numberService;

    /**
     * 查询编号生成列表
     */
    @PreAuthorize("@ss.hasPermi('system:number:list')")
    @GetMapping("/list")
    public TableDataInfo list(Number number)
    {
        startPage();
        List<Number> list = numberService.selectNumberList(number);
        return getDataTable(list);
    }

    /**
     * 导出编号生成列表
     */
    @PreAuthorize("@ss.hasPermi('system:number:export')")
    @Log(title = "编号生成", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Number number)
    {
        List<Number> list = numberService.selectNumberList(number);
        ExcelUtil<Number> util = new ExcelUtil<Number>(Number.class);
        return util.exportExcel(list, "编号生成数据");
    }

    /**
     * 获取编号生成详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:number:query')")
    @GetMapping(value = "/{numberID}")
    public AjaxResult getInfo(@PathVariable("numberID") Long numberID)
    {
        return AjaxResult.success(numberService.selectNumberById(numberID));
    }

    /**
     * 新增编号生成
     */
    @PreAuthorize("@ss.hasPermi('system:number:add')")
    @Log(title = "编号生成", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Number number)
    {
        return toAjax(numberService.insertNumber(number));
    }

    /**
     * 修改编号生成
     */
    @PreAuthorize("@ss.hasPermi('system:number:edit')")
    @Log(title = "编号生成", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Number number)
    {
        return toAjax(numberService.updateNumber(number));
    }

    /**
     * 删除编号生成
     */
    @PreAuthorize("@ss.hasPermi('system:number:remove')")
    @Log(title = "编号生成", businessType = BusinessType.DELETE)
	@DeleteMapping("/{numberIDs}")
    public AjaxResult remove(@PathVariable Long[] numberIDs)
    {
        return toAjax(numberService.deleteNumberByIds(numberIDs));
    }

    // 查询所有编号内容
    @GetMapping("/searchAll")
    public AjaxResult searchAll(){
        return AjaxResult.success(numberService.searchAll());
    }

    // 根据编号类型查询相关编号字段
    @GetMapping("/numberType/{type}")
    public AjaxResult findByNumberType(@PathVariable("type") Integer type){
        return AjaxResult.success(numberService.findByNumberType(type));
    }
}
