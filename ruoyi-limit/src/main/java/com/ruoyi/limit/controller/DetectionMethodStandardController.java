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
import com.ruoyi.limit.domain.DetectionMethodStandard;
import com.ruoyi.limit.service.IDetectionMethodStandardService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 检测方法-检测标准Controller
 * 
 * @author wlf
 * @date 2021-08-29
 */
@RestController
@RequestMapping("/limit/DetectionMethodStandard")
public class DetectionMethodStandardController extends BaseController
{
    @Autowired
    private IDetectionMethodStandardService detectionMethodStandardService;

    /**
     * 查询检测方法-检测标准列表
     */
    @PreAuthorize("@ss.hasPermi('limit:DetectionMethodStandard:list')")
    @GetMapping("/list")
    public TableDataInfo list(DetectionMethodStandard detectionMethodStandard)
    {
        startPage();
        List<DetectionMethodStandard> list = detectionMethodStandardService.selectDetectionMethodStandardList(detectionMethodStandard);
        return getDataTable(list);
    }

    /**
     * 导出检测方法-检测标准列表
     */
    @PreAuthorize("@ss.hasPermi('limit:DetectionMethodStandard:export')")
    @Log(title = "检测方法-检测标准", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(DetectionMethodStandard detectionMethodStandard)
    {
        List<DetectionMethodStandard> list = detectionMethodStandardService.selectDetectionMethodStandardList(detectionMethodStandard);
        ExcelUtil<DetectionMethodStandard> util = new ExcelUtil<DetectionMethodStandard>(DetectionMethodStandard.class);
        return util.exportExcel(list, "检测方法-检测标准数据");
    }

    /**
     * 获取检测方法-检测标准详细信息
     */
    @PreAuthorize("@ss.hasPermi('limit:DetectionMethodStandard:query')")
    @GetMapping(value = "/{detectionMethodStandardID}")
    public AjaxResult getInfo(@PathVariable("detectionMethodStandardID") Long detectionMethodStandardID)
    {
        return AjaxResult.success(detectionMethodStandardService.selectDetectionMethodStandardById(detectionMethodStandardID));
    }

    /**
     * 新增检测方法-检测标准
     */
    @PreAuthorize("@ss.hasPermi('limit:DetectionMethodStandard:add')")
    @Log(title = "检测方法-检测标准", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DetectionMethodStandard detectionMethodStandard)
    {
        String msg = detectionMethodStandardService.insertDetectionMethodStandard(detectionMethodStandard);
        if(msg.equals("操作成功"))
        {
            return AjaxResult.success(msg);
        }
        return AjaxResult.error(msg);
    }

    /**
     * 修改检测方法-检测标准
     */
    @PreAuthorize("@ss.hasPermi('limit:DetectionMethodStandard:edit')")
    @Log(title = "检测方法-检测标准", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DetectionMethodStandard detectionMethodStandard)
    {
        return toAjax(detectionMethodStandardService.updateDetectionMethodStandard(detectionMethodStandard));
    }

    /**
     * 删除检测方法-检测标准
     */
    @PreAuthorize("@ss.hasPermi('limit:DetectionMethodStandard:remove')")
    @Log(title = "检测方法-检测标准", businessType = BusinessType.DELETE)
	@DeleteMapping("/{detectionMethodStandardIDs}")
    public AjaxResult remove(@PathVariable Long[] detectionMethodStandardIDs)
    {
        return toAjax(detectionMethodStandardService.deleteDetectionMethodStandardByIds(detectionMethodStandardIDs));
    }
}
