package com.ruoyi.stock.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.stock.constant.StockConstants;
import com.ruoyi.stock.domain.EntryAndOut;
import com.ruoyi.stock.domain.vo.EntryAndOutVO;
import com.ruoyi.stock.service.IEntryAndOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 样品出库Controller
 *
 * @author ruoyi
 * @date 2021-07-21
 */
@RestController
@RequestMapping("/stock/stockIn")
public class StockInController extends BaseController
{
    @Autowired
    private IEntryAndOutService entryAndOutService;

    /**
     * 查询样品入库列表
     */
    @PreAuthorize("@ss.hasPermi('stock:entryAndOut:list')")
    @GetMapping("/list")
    public TableDataInfo list(EntryAndOutVO entryAndOutVO)
    {
        entryAndOutVO.setEntryAndOutCategory(StockConstants.CATEGORY_STOCK_IN);
        return entryAndOutService.selectEntryAndOutList(entryAndOutVO);
    }

    /**
     * 导出样品入库列表
     */
    @PreAuthorize("@ss.hasPermi('stock:entryAndOut:export')")
    @Log(title = "样品出入库", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(EntryAndOutVO entryAndOutVO)
    {
        List<EntryAndOut> list = (List<EntryAndOut>)entryAndOutService.selectEntryAndOutList(entryAndOutVO).getRows();
        ExcelUtil<EntryAndOut> util = new ExcelUtil<EntryAndOut>(EntryAndOut.class);
        return util.exportExcel(list, "样品出入库数据");
    }

    /**
     * 获取样品出入库详细信息
     */
    @PreAuthorize("@ss.hasPermi('stock:entryAndOut:query')")
    @GetMapping(value = "/{entryAndOutID}")
    public AjaxResult getInfo(@PathVariable("entryAndOutID") Long entryAndOutID)
    {
        return AjaxResult.success(entryAndOutService.selectEntryAndOutById(entryAndOutID));
    }

    /**
     * 新增样品出入库
     */
    @PreAuthorize("@ss.hasPermi('stock:entryAndOut:add')")
    @Log(title = "样品出入库", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EntryAndOut entryAndOut)
    {
        return toAjax(entryAndOutService.insertEntryAndOut(entryAndOut));
    }

    /**
     * 修改样品出入库
     */
    @PreAuthorize("@ss.hasPermi('stock:entryAndOut:edit')")
    @Log(title = "样品出入库", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EntryAndOut entryAndOut)
    {
        return toAjax(entryAndOutService.updateEntryAndOut(entryAndOut));
    }

    /**
     * 删除样品出入库
     */
    @PreAuthorize("@ss.hasPermi('stock:entryAndOut:remove')")
    @Log(title = "样品出入库", businessType = BusinessType.DELETE)
	@DeleteMapping("/{entryAndOutIDs}")
    public AjaxResult remove(@PathVariable Long[] entryAndOutIDs)
    {
        return toAjax(entryAndOutService.deleteEntryAndOutByIds(entryAndOutIDs));
    }
}
