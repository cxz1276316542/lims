package com.ruoyi.detection.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.detection.constant.DetectionResultStatus;
import com.ruoyi.detection.constant.ReportStatus;
import com.ruoyi.detection.domain.CommissionSample;
import com.ruoyi.detection.domain.Report;
import com.ruoyi.detection.domain.ReportTemplate;
import com.ruoyi.detection.domain.vo.*;
import com.ruoyi.detection.service.ICommissionSampleService;
import com.ruoyi.detection.service.IDetectionResultService;
import com.ruoyi.detection.service.IReportService;
import com.ruoyi.detection.service.impl.DetectionResultServiceImpl;
import com.ruoyi.review.domain.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sun.reflect.generics.tree.Tree;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * 检测报告Controller
 * 
 * @author zhangkui
 * @date 2021-08-08
 */
@RestController
@RequestMapping("/detection/report")
public class ReportController extends BaseController
{
    @Autowired
    private IReportService reportService;

    @Autowired
    private IDetectionResultService detectionResultService;

    @Autowired
    private ICommissionSampleService commissionSampleService;
    /**
     * 查询检测报告列表
     */
    @PreAuthorize("@ss.hasPermi('detection:report:list')")
    @GetMapping("/list")
    public TableDataInfo list(ReportVO reportVO)
    {
        startPage();
//        List<Report> list = reportService.selectReportList(report);
        List<ReportVO> list =reportService.selectReportVOList(reportVO);
        return getDataTable(list);
    }

    /**
     * 导出检测报告列表
     */
    @PreAuthorize("@ss.hasPermi('detection:report:export')")
    @Log(title = "检测报告打印邮寄", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Report report)
    {
        List<Report> list = reportService.selectReportList(report);
        ExcelUtil<Report> util = new ExcelUtil<Report>(Report.class);
        return util.exportExcel(list, "检测报告打印邮寄数据");
    }

    /**
     * 获取检测报告详细信息
     */
    @PreAuthorize("@ss.hasPermi('detection:report:query')")
    @GetMapping(value = "/{reportID}")
    public AjaxResult getInfo(@PathVariable("reportID") Long reportID)
    {
        return AjaxResult.success(reportService.selectReportById(reportID));
    }

    /**
     * 新增检测报告
     */
    @PreAuthorize("@ss.hasPermi('detection:report:add')")
    @Log(title = "检测报告打印邮寄", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Report report)
    {
        return toAjax(reportService.insertReport(report));
    }

    /**
     * 修改检测报告
     */
    @PreAuthorize("@ss.hasPermi('detection:report:edit')")
    @Log(title = "检测报告打印邮寄", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Report report)
    {
        return toAjax(reportService.updateReport(report));
    }

    /**
     * 删除检测报告
     */
    @PreAuthorize("@ss.hasPermi('detection:report:remove')")
    @Log(title = "检测报告打印邮寄", businessType = BusinessType.DELETE)
	@DeleteMapping("/{reportIDs}")
    public AjaxResult remove(@PathVariable Long[] reportIDs)
    {
        return toAjax(reportService.deleteReportByIds(reportIDs));
    }

    /**
     * 查询树，撰写报告
     * @return
     */

    @GetMapping("/selectPassList")
    public AjaxResult selectPassList()
    {
        return AjaxResult.success(reportService.selectPassList());
    }

    /**
     * 查询重新撰写的树
     */

    @GetMapping("/selectNoPassList")
    public AjaxResult selectNoPassList()
    {
        return AjaxResult.success(reportService.selectNoPassList());
    }

    /**
     * 查询检测通过的结果
     */
    @GetMapping("/listPassDetectionResult")
    public TableDataInfo listPassDetectionResult(DetectionResultVO detectionResultVO)
    {
        startPage();
        List<DetectionResultVO> ansList=new ArrayList<>();
        if (detectionResultVO.getSampleNumber()!=null){
        System.out.println(detectionResultVO.getSampleNumber());
        CommissionSample commissionSample=new CommissionSample();
        commissionSample.setPrincipalNumber(detectionResultVO.getSampleNumber());
        List<CommissionSample> commissionSamples = commissionSampleService.selectCommissionSampleList(commissionSample);

        if (commissionSamples.size()!=0){
            for (CommissionSample sample : commissionSamples) {
                DetectionResultVO d1=new DetectionResultVO();
                d1.setSampleNumber(sample.getSampleNumber());
                d1.setAuditStatus(DetectionResultStatus.DETECTION_REVIEW_PASS);
                d1.setIsChosen(DetectionResultStatus.DETECTION_NOCHOSEN);
                List<DetectionResultVO> list= detectionResultService.selectResultVOList(d1);
                for (DetectionResultVO resultVO : list) {
                    ansList.add(resultVO);
                }
            }
        }else{
                detectionResultVO.setAuditStatus(DetectionResultStatus.DETECTION_REVIEW_PASS);
                List<DetectionResultVO> list=detectionResultService.selectResultVOList(detectionResultVO);
                for (DetectionResultVO resultVO : list) {
                    ansList.add(resultVO);
                }
            }
        }

        return getDataTable(ansList);
    }




    /**
     * 生成报告
     */
    @PreAuthorize("@ss.hasPermi('detection:report:edit')")
    @Log(title = "生成报告", businessType = BusinessType.UPDATE)
    @PutMapping("/createReport")
    public AjaxResult createReport(@RequestBody ReportReviceVO reportReviceVO) throws Exception {
        System.out.println("进入生成报告");

        List<DetectionResultVO>  detectionResultVOList =reportReviceVO.getDetectionResultVOList();
        Long reportTempID=reportReviceVO.getInspectorId();

        return  toAjax(reportService.createReport(detectionResultVOList,reportTempID));
    }

    /**
     * 重新生成报告
     */
    @PreAuthorize("@ss.hasPermi('detection:report:edit')")
    @Log(title = "重新生成报告", businessType = BusinessType.UPDATE)
    @PutMapping("/reCreateReport")
    public AjaxResult reCreateReport(@RequestBody ReportReviceVO reportReviceVO) throws Exception {
        System.out.println("进入重新生成报告");
        List<DetectionResultVO>  detectionResultVOList =reportReviceVO.getDetectionResultVOList();
        Long reportTempID=reportReviceVO.getInspectorId();
//        return toAjax(detectionResultService.submitReReviewMethod(detectionResultVOList));
        return  toAjax(reportService.reCreateReport(detectionResultVOList,reportTempID));
    }


    /*
    查询刚生成报告（生成预览）
     */
    @PreAuthorize("@ss.hasPermi('detection:report:list')")
    @Log(title = "查询刚上传报告", businessType = BusinessType.UPDATE)
    @PutMapping("/selectReport")
    public TableDataInfo selectReport(@RequestBody List<DetectionResultVO>  detectionResultVOList)
    {
        System.out.println("进入查询");
        startPage();
        List<ReportVO> ansList=new ArrayList<>();
        TreeSet<String> treeSet=new TreeSet<>();
        for (DetectionResultVO detectionResultVO : detectionResultVOList) {
            treeSet.add(detectionResultVO.getSampleNumber());

        }
        for (String s : treeSet) {
            ReportVO reportVO=new ReportVO();
            reportVO.setSampleNumber(s);
            reportVO.setReportStatus(ReportStatus.REPORT_DEFAULT);
            List<ReportVO> list =reportService.selectReportVOList(reportVO);
            for (ReportVO vo : list) {
                ansList.add(vo);
            }
        }

        return getDataTable(ansList);
    }





    /**
     * 签发成功
     */

    @Log(title = "签发成功", businessType = BusinessType.UPDATE)
    @PostMapping("/reportPass")
    public AjaxResult reportPass(@RequestBody Report report)
    {
        System.out.println(report);
        return toAjax(reportService.reportPass(report));
    }

    /**
     * 打回撰写
     */

    @Log(title = "打回撰写", businessType = BusinessType.UPDATE)
    @PostMapping("/reportFail")
    public AjaxResult reportFail(@RequestBody Report report) throws IOException {
        System.out.println(report);
        return toAjax(reportService.reportFail(report));
    }

    /**
     * 查询模板
     */

    @GetMapping("/selectAllTem")
    public AjaxResult selectAllTem(){
        List<TemplateVO> ReportTemplateVOs = reportService.selectAllTem();
        return AjaxResult.success(ReportTemplateVOs);
    }





}
