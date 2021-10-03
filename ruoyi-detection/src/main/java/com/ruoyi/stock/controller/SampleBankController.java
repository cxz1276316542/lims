package com.ruoyi.stock.controller;

import java.util.Date;
import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.stock.constant.StockConstants;
import com.ruoyi.stock.domain.vo.AbandonVO;
import com.ruoyi.stock.domain.vo.EntryAndOutVO;
import com.ruoyi.stock.domain.vo.SampleBankVO;
import org.apache.poi.ss.formula.functions.T;
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
import com.ruoyi.stock.domain.SampleBank;
import com.ruoyi.stock.service.ISampleBankService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 样品库Controller
 *
 * @author ruoyi
 * @date 2021-07-21
 */
@RestController
@RequestMapping("/stock/sampleBank")
public class SampleBankController extends BaseController
{
    @Autowired
    private ISampleBankService sampleBankService;

    /**
     * 查询样品库列表
     */
    @PreAuthorize("@ss.hasPermi('stock:sampleBank:list')")
    @GetMapping("/list")
    public TableDataInfo list(SampleBankVO sampleBankVO)
    {
        sampleBankVO.setStatus(StockConstants.BANK_STATUS_VALID);
        return sampleBankService.selectSampleBankList(sampleBankVO);
    }

    /**
     * 查询废弃样品列表
     */
    @PreAuthorize("@ss.hasPermi('stock:sampleBank:list')")
    @GetMapping("/abandonList")
    public TableDataInfo abandonList(AbandonVO abandonVO)
    {
        return sampleBankService.selectAbandonList(abandonVO);
    }

    /**
     * 导出样品库列表
     */
    @PreAuthorize("@ss.hasPermi('stock:sampleBank:export')")
    @Log(title = "样品库", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SampleBankVO sampleBankVO)
    {
        List<SampleBankVO> list = (List<SampleBankVO>)sampleBankService.selectSampleBankList(sampleBankVO).getRows();
        ExcelUtil<SampleBankVO> util = new ExcelUtil<SampleBankVO>(SampleBankVO.class);
        return util.exportExcel(list, "样品库数据");
    }

    /**
     * 获取样品库详细信息
     */
    @PreAuthorize("@ss.hasPermi('stock:sampleBank:query')")
    @GetMapping(value = "/getInfo/{sampleBankID}")
    public AjaxResult getInfo(@PathVariable("sampleBankID") Long sampleBankID)
    {
        return AjaxResult.success(sampleBankService.selectSampleBankById(sampleBankID));
    }

    /**
     * 获取样品库人员信息
     */
    @GetMapping(value = "/getInventoryManagerList")
    public AjaxResult getInventoryManagerList()
    {
        return AjaxResult.success(sampleBankService.selectInventoryManager());
    }

    /**
     * 新增样品入库
     */
    @PreAuthorize("@ss.hasPermi('stock:sampleBank:add')")
    @Log(title = "样品库", businessType = BusinessType.INSERT)
    @PostMapping("/addSample")
    public AjaxResult add(@RequestBody SampleBankVO sampleBankVO) {
        try{
            return toAjax(sampleBankService.insertSampleBank(sampleBankVO));
        }catch (Exception e){
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 修改样品库
     */
    @PreAuthorize("@ss.hasPermi('stock:sampleBank:edit')")
    @Log(title = "样品库", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SampleBankVO sampleBankVO)
    {
        return toAjax(sampleBankService.updateSampleBank(sampleBankVO));
    }

    /**
     * 批量废弃样品库
     */
    @PreAuthorize("@ss.hasPermi('stock:sampleBank:remove')")
    @Log(title = "样品库", businessType = BusinessType.DELETE)
	@PostMapping("/batchAbandon/{sampleBankIDs}")
    public AjaxResult abandonList(@PathVariable Long[] sampleBankIDs)
    {
        return toAjax(sampleBankService.abandonSampleBankByIds(sampleBankIDs));
    }

    /**
     * 废弃样品库
     */
    @PreAuthorize("@ss.hasPermi('stock:sampleBank:remove')")
    @Log(title = "样品库", businessType = BusinessType.DELETE)
    @PostMapping("/abandon")
    public AjaxResult abandon(@RequestBody EntryAndOutVO entryAndOutVO)
    {
        return toAjax(sampleBankService.abandonSampleBank(entryAndOutVO));
    }

    /**
     * 废弃样品库
     */
    @PreAuthorize("@ss.hasPermi('stock:sampleBank:remove')")
    @Log(title = "样品库", businessType = BusinessType.DELETE)
    @PostMapping("/returnTO")
    public AjaxResult returnTO(@RequestBody EntryAndOutVO entryAndOutVO)
    {
        return toAjax(sampleBankService.returnSampleBank(entryAndOutVO));
    }

    /**
     * 回收样品库
     */
    @PreAuthorize("@ss.hasPermi('stock:sampleBank:remove')")
    @Log(title = "样品库", businessType = BusinessType.DELETE)
    @PostMapping("/recycle")
    public AjaxResult recycle(@RequestBody EntryAndOutVO entryAndOutVO)
    {
        return toAjax(sampleBankService.recycleSampleBank(entryAndOutVO));
    }

    /**
     * 领用样品库
     */
    @PreAuthorize("@ss.hasPermi('stock:sampleBank:remove')")
    @Log(title = "样品库", businessType = BusinessType.DELETE)
    @PostMapping("/use")
    public AjaxResult use(@RequestBody EntryAndOutVO entryAndOutVO) throws Exception {
        return toAjax(sampleBankService.useSampleBank(entryAndOutVO));
    }

    /**
     * 获取样品库详细信息
     */
    @PreAuthorize("@ss.hasPermi('stock:sampleBank:query')")
    @GetMapping(value = "/info/{sampleBankID}")
    public AjaxResult getSampleBank(@PathVariable("sampleBankID") Long sampleBankID)
    {
        return AjaxResult.success(sampleBankService.selectSampleBankById(sampleBankID));
    }

    /**
     * 获取计量单位
     */
    @Log(title = "样品库", businessType = BusinessType.DELETE)
    @GetMapping("/getMeasureUnit")
    public AjaxResult getMeasureUnit()
    {
        return AjaxResult.success(sampleBankService.getMeasureUnit());
    }
}
