package com.ruoyi.detection.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.detection.domain.DetectionResult;
import com.ruoyi.detection.domain.vo.DetectionResultVO;
import com.ruoyi.detection.service.IDetectionResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 检测结果Controller
 * 
 * @author zhangkui
 * @date 2021-08-01
 */
@RestController
@RequestMapping("/detection/detectionResult")
public class DetectionResultController extends BaseController
{
    @Autowired
    private IDetectionResultService detectionResultService;


    /**
     * 查询初检检测结果列表
     */
    @PreAuthorize("@ss.hasPermi('detection:detectionResult:list')")
    @GetMapping("/list")
    public TableDataInfo list(DetectionResultVO detectionResultVO)
    {
        startPage();
        //只显示当前检测员的检测项
        detectionResultVO.setInspectorID(SecurityUtils.getLoginUser().getUser().getUserId());
        System.out.println(SecurityUtils.getLoginUser().getUser().getUserId());
//        List<DetectionResult> list = detectionResultService.selectDetectionResultList(detectionResult);
        List<DetectionResultVO> list=detectionResultService.selectResultVOList(detectionResultVO);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString());
        }
        return getDataTable(list);
    }


    /**
     * 查询复检检测结果列表
     */
    @PreAuthorize("@ss.hasPermi('detection:detectionResult:list')")
    @GetMapping("/reList")
    public TableDataInfo reList(DetectionResultVO detectionResultVO)
    {
        startPage();
//        List<DetectionResult> list = detectionResultService.selectDetectionResultList(detectionResult);
        //只显示当前检测员的检测项
        detectionResultVO.setInspectorID(SecurityUtils.getLoginUser().getUser().getUserId());
        System.out.println(SecurityUtils.getLoginUser().getUser().getUserId());
        List<DetectionResultVO> list=detectionResultService.selectReResultVOList(detectionResultVO);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString());
        }
        return getDataTable(list);
    }
    /**
     * 导出检测结果列表
     */
    @PreAuthorize("@ss.hasPermi('detection:detectionResult:export')")
    @Log(title = "检测结果", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(DetectionResult detectionResult)
    {
        List<DetectionResult> list = detectionResultService.selectDetectionResultList(detectionResult);
        ExcelUtil<DetectionResult> util = new ExcelUtil<DetectionResult>(DetectionResult.class);
        return util.exportExcel(list, "检测结果数据");
    }

    /**
     * 获取初检检测结果详细信息
     */
    @PreAuthorize("@ss.hasPermi('detection:detectionResult:query')")
    @GetMapping(value = "/{detectionResultID}")
    public AjaxResult getInfo(@PathVariable("detectionResultID") Long detectionResultID)
    {

        DetectionResultVO detectionResultVO=new DetectionResultVO();
        detectionResultVO.setDetectionResultID(detectionResultID);
        return AjaxResult.success(detectionResultService.selectResultVOList(detectionResultVO).get(0));
    }

    /**
     * 获取复检检测结果详细信息
     */
    @PreAuthorize("@ss.hasPermi('detection:detectionResult:query')")
    @GetMapping(value = "/getReDetectionResult{detectionResultID}")
    public AjaxResult getReDetectionResult(@PathVariable("detectionResultID") Long detectionResultID)
    {
        System.out.println(detectionResultID);
        DetectionResultVO detectionResultVO=new DetectionResultVO();
        detectionResultVO.setDetectionResultID(detectionResultID);
        return AjaxResult.success(detectionResultService.selectReResultVOList(detectionResultVO).get(0));
    }

    /**
     * 新增检测结果
     */
    @PreAuthorize("@ss.hasPermi('detection:detectionResult:add')")
    @Log(title = "检测结果", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DetectionResult detectionResult)
    {
        return toAjax(detectionResultService.insertDetectionResult(detectionResult));
    }

    /**
     * 修改检测结果
     */
    @PreAuthorize("@ss.hasPermi('detection:detectionResult:edit')")
    @Log(title = "检测结果", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DetectionResult detectionResult)
    {
        return toAjax(detectionResultService.updateDetectionResult(detectionResult));
    }

    /**
     * 删除检测结果
     */
    @PreAuthorize("@ss.hasPermi('detection:detectionResult:remove')")
    @Log(title = "检测结果", businessType = BusinessType.DELETE)
	@DeleteMapping("/{detectionResultIDs}")
    public AjaxResult remove(@PathVariable Long[] detectionResultIDs)
    {
        return toAjax(detectionResultService.deleteDetectionResultByIds(detectionResultIDs));
    }


    /**
     * 批量初检审核
     */
    @PreAuthorize("@ss.hasPermi('detection:detectionResult:edit')")
    @Log(title = "批量提交初检审核", businessType = BusinessType.UPDATE)
    @PutMapping("/submitBatchReview")
    public AjaxResult submitBatchReview(@RequestBody List<DetectionResultVO>  detectionResultVOList)
    {
        return toAjax(detectionResultService.submitReviewMethod(detectionResultVOList));
    }

    /**
     * 提交初检审核
     */
    @PreAuthorize("@ss.hasPermi('detection:detectionResult:edit')")
    @Log(title = "提交初检审核", businessType = BusinessType.UPDATE)
    @PutMapping("/submitReview")
    public AjaxResult submitReview(@RequestBody DetectionResultVO detectionResultVO)
    {
        List<DetectionResultVO>  detectionResultVOList=new ArrayList<>();
        detectionResultVOList.add(detectionResultVO);
        return toAjax(detectionResultService.submitReviewMethod(detectionResultVOList));
    }

    /**
     * 批量提交复检审核
     */
    @PreAuthorize("@ss.hasPermi('detection:detectionResult:edit')")
    @Log(title = "批量提交复检审核", businessType = BusinessType.UPDATE)
    @PutMapping("/submitBatchReReview")
    public AjaxResult submitBatchReReview(@RequestBody List<DetectionResultVO>  detectionResultVOList)
    {
        return toAjax(detectionResultService.submitReReviewMethod(detectionResultVOList));
    }

    /**
     * 提交复检审核
     */
    @PreAuthorize("@ss.hasPermi('detection:detectionResult:edit')")
    @Log(title = "提交复检审核", businessType = BusinessType.UPDATE)
    @PutMapping("/submitReReview")
    public AjaxResult submitReReview(@RequestBody DetectionResultVO detectionResultVO)
    {
        List<DetectionResultVO>  detectionResultVOList=new ArrayList<>();
        detectionResultVOList.add(detectionResultVO);
        return toAjax(detectionResultService.submitReReviewMethod(detectionResultVOList));
    }


}
