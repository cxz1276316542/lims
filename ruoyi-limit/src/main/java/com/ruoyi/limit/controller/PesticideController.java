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
import com.ruoyi.limit.domain.Pesticide;
import com.ruoyi.limit.service.IPesticideService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;


/**
 * 检测农药管理Controller
 * 
 * @author wlf
 * @date 2021-07-20
 */
@RestController
@RequestMapping("/limit/pesticide")
public class PesticideController extends BaseController
{
    @Autowired
    private IPesticideService pesticideService;

    /**
     * 查询豁免清单
     * @param pesticide 检测农药信息
     */
    @GetMapping("/exemption")
    public TableDataInfo selectExemptionList(Pesticide pesticide)
    {
        startPage();
        List<Pesticide> list = pesticideService.selectExemptionList(pesticide);
        return getDataTable(list);
    }
    /**
     * 查询检测农药管理列表
     */
    @PreAuthorize("@ss.hasPermi('limit:pesticide:list')")
    @GetMapping("/list")
    public TableDataInfo list(Pesticide pesticide)
    {
        startPage();
        List<Pesticide> list = pesticideService.selectPesticideList(pesticide);
        return getDataTable(list);
    }

    /**
     * 下载检测农药导入模块
     */
    @GetMapping("/importTemplate")
    public AjaxResult importTemplate()
    {
        ExcelUtil<Pesticide> util = new ExcelUtil<Pesticide>(Pesticide.class);
        return util.importTemplateExcel("检测项目");
    }

    /**
     * 导入检测农药信息
     */
    @Log(title = "检测农药管理", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('limit:pesticide:import')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<Pesticide> util = new ExcelUtil<Pesticide>(Pesticide.class);
        List<Pesticide> pesticideList = util.importExcel(file.getInputStream());
        String message = pesticideService.importPesticide(pesticideList, updateSupport);
        return AjaxResult.success(message);
    }

    /**
     * 导出检测农药管理列表
     */
    @PreAuthorize("@ss.hasPermi('limit:pesticide:export')")
    @Log(title = "检测农药管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Pesticide pesticide)
    {
        List<Pesticide> list = pesticideService.selectPesticideList(pesticide);
        ExcelUtil<Pesticide> util = new ExcelUtil<Pesticide>(Pesticide.class);
        return util.exportExcel(list, "检测项目管理数据");
    }

    /**
     * 获取检测农药管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('limit:pesticide:query')")
    @GetMapping(value = "/{pesticideID}")
    public AjaxResult getInfo(@PathVariable("pesticideID") Long pesticideID)
    {
        return AjaxResult.success(pesticideService.selectPesticideById(pesticideID));
    }

    /**
     * 新增检测农药管理
     */
    @PreAuthorize("@ss.hasPermi('limit:pesticide:add')")
    @Log(title = "检测农药管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Pesticide pesticide)
    {
        String  msg = pesticideService.insertPesticide(pesticide);
        if(msg.equals("操作成功"))
        {
            return AjaxResult.success(msg);
        }
        return AjaxResult.error(msg);
    }

    /**
     * 修改检测农药管理
     */
    @PreAuthorize("@ss.hasPermi('limit:pesticide:edit')")
    @Log(title = "检测农药管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Pesticide pesticide)
    {
        return toAjax(pesticideService.updatePesticide(pesticide));
    }

    /**
     * 删除检测农药管理
     */
    @PreAuthorize("@ss.hasPermi('limit:pesticide:remove')")
    @Log(title = "检测农药管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{pesticideIDs}")
    public AjaxResult remove(@PathVariable Long[] pesticideIDs)
    {
        return toAjax(pesticideService.deletePesticideByIds(pesticideIDs));
    }
}
