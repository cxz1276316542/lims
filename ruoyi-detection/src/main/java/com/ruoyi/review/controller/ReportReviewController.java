package com.ruoyi.review.controller;

import java.util.List;
import java.util.Map;

import com.ruoyi.review.constant.ReviewCategory;
import com.ruoyi.review.constant.ReviewCirculation;
import com.ruoyi.review.constant.ReviewStatus;
import com.ruoyi.review.domain.VO.ReviewVO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.review.domain.Review;
import com.ruoyi.review.service.IReviewService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 审核Controller
 *
 * @author zjccc
 * @date 2021-07-31
 */
@RestController
@RequestMapping("/review/reportReview")
public class ReportReviewController extends BaseController
{
    @Autowired
    private IReviewService reviewService;

    /**
     * 查询报告审核员列表
     */
    @PreAuthorize("@ss.hasPermi('review:reportReview:list')")
    @GetMapping("/reviewerList")
    public AjaxResult getReviewerList()
    {
        return AjaxResult.success(reviewService.selectReviewerList(15L));
    }

    /**
     * 获取报告撰写人员信息
     */
    @GetMapping(value = "/getReportWriterList")
    public AjaxResult getReportWriterList()
    {
        return AjaxResult.success(reviewService.selectInventoryManager());
    }

    /**
     * 查询审核列表
     */
    @PreAuthorize("@ss.hasPermi('review:reportReview:list')")
    @GetMapping("/reviewedList")
    public TableDataInfo list(ReviewVO reviewVO)
    {
        return reviewService.selectReportReviewList(reviewVO, ReviewStatus.REVIEWED);
    }

    /**
     * 查询未审核列表
     */
    @PreAuthorize("@ss.hasPermi('review:reportReview:list')")
    @GetMapping("/notReviewList")
    public TableDataInfo notReviewList(ReviewVO reviewVO)
    {
        return reviewService.selectReportReviewList(reviewVO, ReviewStatus.NOT_REVIEW);
    }

    /**
     * 报告审核通过
     */
    @PreAuthorize("@ss.hasPermi('review:reportReview:edit')")
    @Log(title = "审核", businessType = BusinessType.UPDATE)
    @PostMapping("/pass")
    public AjaxResult reviewAdopt(@RequestBody Review review)
    {
        return toAjax(reviewService.adoptReportReview(review));
    }

    /**
     * 检测报告审核退回重新分派
     */
    @PreAuthorize("@ss.hasPermi('review:reportReview:edit')")
    @Log(title = "审核", businessType = BusinessType.UPDATE)
    @PostMapping("/returnToAssign")
    public AjaxResult reportReturnAssign(@RequestBody Review review)
    {
        return toAjax(reviewService.failReportReview(review, ReviewCirculation.REPORT_TO_ASSIGN));
    }

    /**
     * 批量报告审核不通过,重新分派
     */
    @PreAuthorize("@ss.hasPermi('review:sampleReview:edit')")
    @Log(title = "审核", businessType = BusinessType.UPDATE)
    @PostMapping("/batchReturnToAssign/{reviewIDs}")
    public AjaxResult batchReturnToAssign(@RequestBody Review review,@PathVariable Long[] reviewIDs)
    {
        return toAjax(reviewService.batchFailReportReview(review,reviewIDs ,ReviewCirculation.REPORT_TO_ASSIGN));
    }

    /**
     * 检测报告审核退回报告撰写人
     */
    @PreAuthorize("@ss.hasPermi('review:reportReview:edit')")
    @Log(title = "审核", businessType = BusinessType.UPDATE)
    @PostMapping("/returnToWriter")
    public AjaxResult reportReturnWriter(@RequestBody Review review)
    {
        return toAjax(reviewService.failReportReview(review, ReviewCirculation.REPORT_TO_WRITER));
    }

    /**
     * 批量报告审核不通过,退回报告撰写人
     */
    @PreAuthorize("@ss.hasPermi('review:sampleReview:edit')")
    @Log(title = "审核", businessType = BusinessType.UPDATE)
    @PostMapping("/batchReturnToWriter/{reviewIDs}")
    public AjaxResult batchReturnToWriter(@RequestBody Review review,@PathVariable Long[] reviewIDs)
    {
        return toAjax(reviewService.batchFailReportReview(review,reviewIDs ,ReviewCirculation.REPORT_TO_WRITER));
    }

    /**
     * 检测报告审核退回检测员复检
     */
    @PreAuthorize("@ss.hasPermi('review:reportReview:edit')")
    @Log(title = "审核", businessType = BusinessType.UPDATE)
    @PostMapping("/returnToRecheck")
    public AjaxResult reportReturnRecheck(@RequestBody Review review)
    {
        return toAjax(reviewService.failReportReview(review, ReviewCirculation.REPORT_TO_INSPECTOR));
    }

    /**
     * 批量报告审核不通过,退回复检
     */
    @PreAuthorize("@ss.hasPermi('review:sampleReview:edit')")
    @Log(title = "审核", businessType = BusinessType.UPDATE)
    @PostMapping("/batchReturnToRecheck/{reviewIDs}")
    public AjaxResult batchReturnToRecheck(@RequestBody Review review,@PathVariable Long[] reviewIDs)
    {
        return toAjax(reviewService.batchFailReportReview(review,reviewIDs ,ReviewCirculation.REPORT_TO_INSPECTOR));
    }

    /**
     * 导出审核列表
     */
    @PreAuthorize("@ss.hasPermi('review:reportReview:export')")
    @Log(title = "审核", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Review review)
    {
        List<Review> list = reviewService.selectReviewList(review);
        ExcelUtil<Review> util = new ExcelUtil<Review>(Review.class);
        return util.exportExcel(list, "审核数据");
    }

    /**
     * 获取审核详细信息
     */
    @PreAuthorize("@ss.hasPermi('review:reportReview:query')")
    @GetMapping(value = "/getInfo/{reviewID}")
    public AjaxResult getInfo(@PathVariable("reviewID") Long reviewID)
    {
        return AjaxResult.success(reviewService.selectReviewInfoById(reviewID));
    }

    /**
     * 获取已审核详细信息
     */
    @PreAuthorize("@ss.hasPermi('review:reportReview:query')")
    @GetMapping(value = "/getReviewedInfo/{reviewID}")
    public AjaxResult getReviewedInfo(@PathVariable("reviewID") Long reviewID)
    {
        return AjaxResult.success(reviewService.selectReviewedInfoById(reviewID));
    }

    /**
     * 新增审核
     */
    @PreAuthorize("@ss.hasPermi('review:reportReview:add')")
    @Log(title = "审核", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Review review)
    {
        return toAjax(reviewService.insertReview(review));
    }

    /**
     * 修改审核
     */
    @PreAuthorize("@ss.hasPermi('review:reportReview:edit')")
    @Log(title = "审核", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Review review)
    {
        return toAjax(reviewService.updateReview(review));
    }

    /**
     * 删除审核
     */
    @PreAuthorize("@ss.hasPermi('review:reportReview:remove')")
    @Log(title = "审核", businessType = BusinessType.DELETE)
	@DeleteMapping("/{reviewIDs}")
    public AjaxResult remove(@PathVariable Long[] reviewIDs)
    {
        return toAjax(reviewService.deleteReviewByIds(reviewIDs));
    }

    /**
     * 批量审核通过
     */
    @PreAuthorize("@ss.hasPermi('review:reportReview:edit')")
    @Log(title = "审核", businessType = BusinessType.UPDATE)
    @PostMapping("/batchPass/{reviewIDs}")
    public AjaxResult batchPass(@RequestBody Review review,@PathVariable Long[] reviewIDs)
    {
        return toAjax(reviewService.batchReviewPass(reviewIDs, ReviewCategory.REPORT_REVIEW[0],review));
    }
}
