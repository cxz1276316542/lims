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
import com.ruoyi.limit.domain.vo.InstrumentMethodVo;
import com.ruoyi.limit.service.IInstrumentMethodVoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 检测方法-检测仪器关系Controller
 *
 * @author dlk
 * @date 2021-08-07
 */
@RestController
@RequestMapping("/limit/instrumentMethod")
public class InstrumentMethodController extends BaseController
{
    @Autowired
    private IInstrumentMethodVoService instrumentMethodService;

    /**
     * 查询检测方法-检测仪器关系列表
     */
    @PreAuthorize("@ss.hasPermi('limit:instrumentMethod:list')")
    @GetMapping("/list")
    public TableDataInfo list(InstrumentMethodVo instrumentMethodVo)
    {
        startPage();
        List<InstrumentMethodVo> list = instrumentMethodService.selectInstrumentMethodList(instrumentMethodVo);
        return getDataTable(list);
    }

    /**
     * 获取检测方法-检测仪器详细信息
     */
    @PreAuthorize("@ss.hasPermi('limit:instrumentMethod:query')")
    @GetMapping(value = "/{instrumentDetectionMethodID}")
    public AjaxResult getInfo(@PathVariable("instrumentDetectionMethodID") Long instrumentDetectionMethodID)
    {
        return AjaxResult.success(instrumentMethodService.selectInstrumentMethodById(instrumentDetectionMethodID));
    }

    /**
     * 导出检测方法-检测仪器关系列表
     */
    @PreAuthorize("@ss.hasPermi('limit:instrumentMethod:export')")
    @Log(title = "检测方法-检测仪器关系", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(InstrumentMethodVo instrumentMethodVo)
    {
        List<InstrumentMethodVo> list = instrumentMethodService.selectInstrumentMethodList(instrumentMethodVo);
        ExcelUtil<InstrumentMethodVo> util = new ExcelUtil<InstrumentMethodVo>(InstrumentMethodVo.class);
        return util.exportExcel(list, "检测方法-检测仪器关系数据");
    }


    /**
     * 新增检测方法-检测仪器关系
     */
    @PreAuthorize("@ss.hasPermi('limit:instrumentMethod:add')")
    @Log(title = "检测方法-检测仪器关系", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody InstrumentMethodVo instrumentMethodVo)
    {
        return toAjax(instrumentMethodService.insertInstrumentMethod(instrumentMethodVo));
    }

    /**
     * 修改检测方法-检测仪器关系
     */
    @PreAuthorize("@ss.hasPermi('limit:instrumentMethod:edit')")
    @Log(title = "检测方法-检测仪器关系", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody InstrumentMethodVo instrumentMethodVo)
    {
        return toAjax(instrumentMethodService.updateInstrumentMethod(instrumentMethodVo));
    }

    /**
     * 检测方法-检测仪器关系是否存在
     */
    @PreAuthorize("@ss.hasPermi('limit:instrumentMethod:list')")
    @Log(title = "检测方法-检测仪器关系")
    @PostMapping("/isExist")
    public AjaxResult isExist(@RequestBody InstrumentMethodVo instrumentMethodVo)
    {
        return AjaxResult.success(instrumentMethodService.isExist(instrumentMethodVo));
    }

    /**
     * 删除单个检测方法-检测仪器关系
     *//*
    @PreAuthorize("@ss.hasPermi('limit:instrumentMethod:remove')")
    @Log(title = "检测方法-检测仪器关系", businessType = BusinessType.DELETE)
    @DeleteMapping("/single")
    public AjaxResult remove(@RequestBody InstrumentMethodVo instrumentMethodVo)
    {
        return toAjax(instrumentMethodService.deleteInstrumentMethodById(instrumentMethodVo));
    }*/

    /**
     * 删除检测方法-检测仪器关系
     */
    @PreAuthorize("@ss.hasPermi('limit:instrumentMethod:remove')")
    @Log(title = "检测方法-检测仪器关系", businessType = BusinessType.DELETE)
    @DeleteMapping("/{instrumentDetectionMethodIDs}")
    public AjaxResult remove(@PathVariable Long[] instrumentDetectionMethodIDs)
    {
        return toAjax(instrumentMethodService.deleteInstrumentMethodByIds(instrumentDetectionMethodIDs));
    }

    /**
     * 查找所有检测方法名称
     */
    @PreAuthorize("@ss.hasPermi('limit:instrumentMethod:all')")
    @GetMapping("/detectionMethodName")
    public AjaxResult detectionMethodNameList(){
        return AjaxResult.success(instrumentMethodService.findAllDetectionMethodName());
    }

    /**
     * 导入检测仪器-检测方法管理详细信息
     */
    @Log(title = "检测仪器-检测方法管理", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('limit:instrument:importInstrumentMethod')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<InstrumentMethodVo> util = new ExcelUtil<InstrumentMethodVo>(InstrumentMethodVo.class);
        List<InstrumentMethodVo> instrumentMethodVoList = util.importExcel(file.getInputStream());
        String message = instrumentMethodService.importInstrumentMethod(instrumentMethodVoList,updateSupport);
        return AjaxResult.success(message);
    }

    /**
     * 下载限量库检测仪器-检测方法导入模块
     */
    @GetMapping("/importTemplate")
    public AjaxResult importTemplate()
    {
        ExcelUtil<InstrumentMethodVo> util = new ExcelUtil<InstrumentMethodVo>(InstrumentMethodVo.class);
        return util.importTemplateExcel("检测仪器-检测方法");
    }
}
