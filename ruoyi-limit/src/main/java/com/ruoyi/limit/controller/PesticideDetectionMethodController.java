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
import com.ruoyi.limit.domain.PesticideDetectionMethod;
import com.ruoyi.limit.service.IPesticideDetectionMethodService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 检测项目-检测方法Controller
 * 
 * @author wlf
 * @date 2021-08-28
 */
@RestController
@RequestMapping("/limit/pesticideDetectionMethod")
public class PesticideDetectionMethodController extends BaseController
{
    @Autowired
    private IPesticideDetectionMethodService pesticideDetectionMethodService;

    /**
     * 查询检测项目-检测方法列表
     */
    @PreAuthorize("@ss.hasPermi('limit:pesticideDetectionMethod:list')")
    @GetMapping("/list")
    public TableDataInfo list(PesticideDetectionMethod pesticideDetectionMethod)
    {
        startPage();
        List<PesticideDetectionMethod> list = pesticideDetectionMethodService.selectPesticideDetectionMethodList(pesticideDetectionMethod);
        return getDataTable(list);
    }

    /**
     * 导出检测项目-检测方法列表
     */
    @PreAuthorize("@ss.hasPermi('limit:pesticideDetectionMethod:export')")
    @Log(title = "检测项目-检测方法", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(PesticideDetectionMethod pesticideDetectionMethod)
    {
        List<PesticideDetectionMethod> list = pesticideDetectionMethodService.selectPesticideDetectionMethodList(pesticideDetectionMethod);
        ExcelUtil<PesticideDetectionMethod> util = new ExcelUtil<PesticideDetectionMethod>(PesticideDetectionMethod.class);
        return util.exportExcel(list, "检测项目-检测方法数据");
    }

    /**
     * 获取检测项目-检测方法详细信息
     */
    @PreAuthorize("@ss.hasPermi('limit:pesticideDetectionMethod:query')")
    @GetMapping(value = "/{pesticideDetectionMethodID}")
    public AjaxResult getInfo(@PathVariable("pesticideDetectionMethodID") Long pesticideDetectionMethodID)
    {
        return AjaxResult.success(pesticideDetectionMethodService.selectPesticideDetectionMethodById(pesticideDetectionMethodID));
    }

    /**
     * 新增检测项目-检测方法
     */
    @PreAuthorize("@ss.hasPermi('limit:pesticideDetectionMethod:add')")
    @Log(title = "检测项目-检测方法", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PesticideDetectionMethod pesticideDetectionMethod)
    {
        String  msg = pesticideDetectionMethodService.insertPesticideDetectionMethod(pesticideDetectionMethod);
        if(msg.equals("操作成功"))
        {
            return AjaxResult.success(msg);
        }
        return AjaxResult.error(msg);
    }

    /**
     * 修改检测项目-检测方法
     */
    @PreAuthorize("@ss.hasPermi('limit:pesticideDetectionMethod:edit')")
    @Log(title = "检测项目-检测方法", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PesticideDetectionMethod pesticideDetectionMethod)
    {
        return toAjax(pesticideDetectionMethodService.updatePesticideDetectionMethod(pesticideDetectionMethod));
    }

    /**
     * 删除检测项目-检测方法
     */
    @PreAuthorize("@ss.hasPermi('limit:pesticideDetectionMethod:remove')")
    @Log(title = "检测项目-检测方法", businessType = BusinessType.DELETE)
	@DeleteMapping("/{pesticideDetectionMethodIDs}")
    public AjaxResult remove(@PathVariable Long[] pesticideDetectionMethodIDs)
    {
        return toAjax(pesticideDetectionMethodService.deletePesticideDetectionMethodByIds(pesticideDetectionMethodIDs));
    }
}
