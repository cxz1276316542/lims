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
import com.ruoyi.detection.domain.DetectionStatus;
import com.ruoyi.detection.service.IDetectionStatusService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 样品检测状态Controller
 *
 * @author ruoyi
 * @date 2021-07-21
 */
@RestController
@RequestMapping("/detection/detectionStatus")
public class DetectionStatusController extends BaseController
{
    @Autowired
    private IDetectionStatusService detectionStatusService;

    /**
     * 查询样品检测状态列表
     */
    @PreAuthorize("@ss.hasPermi('detection:detectionStatus:list')")
    @GetMapping("/list")
    public TableDataInfo list(DetectionStatus detectionStatus)
    {
        startPage();
        List<DetectionStatus> list = detectionStatusService.selectDetectionStatusList(detectionStatus);
        return getDataTable(list);
    }

    /**
     * 导出样品检测状态列表
     */
    @PreAuthorize("@ss.hasPermi('detection:detectionStatus:export')")
    @Log(title = "样品检测状态", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(DetectionStatus detectionStatus)
    {
        List<DetectionStatus> list = detectionStatusService.selectDetectionStatusList(detectionStatus);
        ExcelUtil<DetectionStatus> util = new ExcelUtil<DetectionStatus>(DetectionStatus.class);
        return util.exportExcel(list, "样品检测状态数据");
    }

    /**
     * 获取样品检测状态详细信息
     */
    @PreAuthorize("@ss.hasPermi('detection:detectionStatus:query')")
    @GetMapping(value = "/{sampleID}")
    public AjaxResult getInfo(@PathVariable("sampleID") Long sampleID)
    {
        return AjaxResult.success(detectionStatusService.selectDetectionStatusById(sampleID));
    }

    /**
     * 新增样品检测状态
     */
    @PreAuthorize("@ss.hasPermi('detection:detectionStatus:add')")
    @Log(title = "样品检测状态", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DetectionStatus detectionStatus)
    {
        return toAjax(detectionStatusService.insertDetectionStatus(detectionStatus));
    }

    /**
     * 修改样品检测状态
     */
    @PreAuthorize("@ss.hasPermi('detection:detectionStatus:edit')")
    @Log(title = "样品检测状态", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DetectionStatus detectionStatus)
    {
        return toAjax(detectionStatusService.updateDetectionStatus(detectionStatus));
    }

    /**
     * 删除样品检测状态
     */
    @PreAuthorize("@ss.hasPermi('detection:detectionStatus:remove')")
    @Log(title = "样品检测状态", businessType = BusinessType.DELETE)
	@DeleteMapping("/{sampleIDs}")
    public AjaxResult remove(@PathVariable Long[] sampleIDs)
    {
        return toAjax(detectionStatusService.deleteDetectionStatusByIds(sampleIDs));
    }
}
