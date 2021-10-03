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
import com.ruoyi.detection.domain.Enterprise;
import com.ruoyi.detection.service.IEnterpriseService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 企业信息Controller
 * 
 * @author zhangkui
 * @date 2021-07-22
 */
@RestController
@RequestMapping("/detection/Enterprise")
public class EnterpriseController extends BaseController
{
    @Autowired
    private IEnterpriseService enterpriseService;

    /**
     * 查询企业信息列表
     */
    @PreAuthorize("@ss.hasPermi('detection:Enterprise:list')")
    @GetMapping("/list")
    public TableDataInfo list(Enterprise enterprise)
    {
        startPage();
        List<Enterprise> list = enterpriseService.selectEnterpriseList(enterprise);
        return getDataTable(list);
    }

    /**
     * 导出企业信息列表
     */
    @PreAuthorize("@ss.hasPermi('detection:Enterprise:export')")
    @Log(title = "企业信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Enterprise enterprise)
    {
        List<Enterprise> list = enterpriseService.selectEnterpriseList(enterprise);
        ExcelUtil<Enterprise> util = new ExcelUtil<Enterprise>(Enterprise.class);
        return util.exportExcel(list, "企业信息数据");
    }

    /**
     * 获取企业信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('detection:Enterprise:query')")
    @GetMapping(value = "/{enterpriseID}")
    public AjaxResult getInfo(@PathVariable("enterpriseID") Long enterpriseID)
    {
        return AjaxResult.success(enterpriseService.selectEnterpriseById(enterpriseID));
    }

    /**
     * 新增企业信息
     */
    @PreAuthorize("@ss.hasPermi('detection:Enterprise:add')")
    @Log(title = "企业信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Enterprise enterprise)
    {
        return toAjax(enterpriseService.insertEnterprise(enterprise));
    }

    /**
     * 修改企业信息
     */
    @PreAuthorize("@ss.hasPermi('detection:Enterprise:edit')")
    @Log(title = "企业信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Enterprise enterprise)
    {
        return toAjax(enterpriseService.updateEnterprise(enterprise));
    }

    /**
     * 删除企业信息
     */
    @PreAuthorize("@ss.hasPermi('detection:Enterprise:remove')")
    @Log(title = "企业信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{enterpriseIDs}")
    public AjaxResult remove(@PathVariable Long[] enterpriseIDs)
    {
        return toAjax(enterpriseService.deleteEnterpriseByIds(enterpriseIDs));
    }
}
