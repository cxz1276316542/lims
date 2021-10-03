package com.ruoyi.limit.controller;

import java.util.List;

import com.ruoyi.limit.domain.DetectionMethod;
import com.ruoyi.limit.domain.vo.InstrumentMethodVo;
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
import com.ruoyi.limit.domain.Instrument;
import com.ruoyi.limit.service.IInstrumentService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 检测仪器管理Controller
 * 
 * @author dlk
 * @date 2021-07-20
 */
@RestController
@RequestMapping("/limit/instrument")
public class InstrumentController extends BaseController
{
    @Autowired
    private IInstrumentService instrumentService;

    /**
     * 查询检测仪器管理列表
     */
    @PreAuthorize("@ss.hasPermi('limit:instrument:list')")
    @GetMapping("/list")
    public TableDataInfo list(Instrument instrument)
    {
        startPage();
        List<Instrument> list = instrumentService.selectInstrumentList(instrument);
        return getDataTable(list);
    }

    /**
     * 获取检测仪器管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('limit:instrument:query')")
    @GetMapping(value = "/{instrumentID}")
    public AjaxResult getInfo(@PathVariable("instrumentID") Long instrumentID)
    {
        return AjaxResult.success(instrumentService.selectInstrumentById(instrumentID));
    }

    /**
     * 导入检测仪器管理详细信息
     */
    @Log(title = "检测仪器管理", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('limit:instrument:import')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<Instrument> util = new ExcelUtil<Instrument>(Instrument.class);
        List<Instrument> instrumentList = util.importExcel(file.getInputStream());
        String message = instrumentService.importInstrument(instrumentList,updateSupport);
        return AjaxResult.success(message);
    }

    /**
     * 下载检测仪器导入模块
     */
    @GetMapping("/importTemplate")
    public AjaxResult importTemplate()
    {
        ExcelUtil<Instrument> util = new ExcelUtil<Instrument>(Instrument.class);
        return util.importTemplateExcel("检测仪器");
    }

    /**
     * 导出检测仪器管理列表
     */
    @PreAuthorize("@ss.hasPermi('limit:instrument:export')")
    @Log(title = "检测仪器管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Instrument instrument)
    {
        List<Instrument> list = instrumentService.selectInstrumentList(instrument);
        ExcelUtil<Instrument> util = new ExcelUtil<Instrument>(Instrument.class);
        return util.exportExcel(list, "检测仪器管理数据");
    }

    /**
     * 导入检测仪器-检测方法管理详细信息
     */
/*
    @Log(title = "检测仪器-检测方法管理", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('limit:instrument:importInstrumentMethod')")
    @PostMapping("/importInstrumentMethodData")
    public AjaxResult importInstrumentMethodData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<InstrumentDetectionMethod> util = new ExcelUtil<InstrumentDetectionMethod>(InstrumentDetectionMethod.class);
        List<InstrumentDetectionMethod> instrumentDetectionMethodList = util.importExcel(file.getInputStream());
        String message = instrumentService.importInstrumentMethod(instrumentDetectionMethodList,updateSupport);
        return AjaxResult.success(message);
    }
*/

    /**
     * 导出检测仪器-检测方法管理列表
     */
    @PreAuthorize("@ss.hasPermi('limit:instrumentMethod:export')")
    @Log(title = "检测仪器管理", businessType = BusinessType.EXPORT)
    @GetMapping("/exportInstrumentMethod")
    public AjaxResult exportInstrumentMethod(Instrument instrument)
    {
        List<InstrumentMethodVo> list = instrumentService.selectInstrumentDetectionMethodList(instrument);

        ExcelUtil<InstrumentMethodVo> util = new ExcelUtil<InstrumentMethodVo>(InstrumentMethodVo.class);
        return util.exportExcel(list, "检测仪器-检测方法管理数据");
    }

    /**
     * 下载限量库检测仪器-检测方法导入模块
     */
    @GetMapping("/importInstrumentMethodTemplate")
    public AjaxResult importInstrumentMethodTemplate()
    {
        ExcelUtil<InstrumentMethodVo> util = new ExcelUtil<InstrumentMethodVo>(InstrumentMethodVo.class);
        return util.importTemplateExcel("检测仪器-检测方法");
    }

    /**
     * 新增检测仪器管理
     */
    @PreAuthorize("@ss.hasPermi('limit:instrument:add')")
    @Log(title = "检测仪器管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Instrument instrument)
    {
        return toAjax(instrumentService.insertInstrument(instrument));
    }

    /**
     * 修改检测仪器管理
     */
    @PreAuthorize("@ss.hasPermi('limit:instrument:edit')")
    @Log(title = "检测仪器管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Instrument instrument)
    {
        return toAjax(instrumentService.updateInstrument(instrument));
    }

    /**
     * 删除检测仪器管理
     */
    @PreAuthorize("@ss.hasPermi('limit:instrument:remove')")
    @Log(title = "检测仪器管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{instrumentIDs}")
    public AjaxResult remove(@PathVariable Long[] instrumentIDs)
    {
        return toAjax(instrumentService.deleteInstrumentByIds(instrumentIDs));
    }
}
