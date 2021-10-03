package com.ruoyi.detection.service.impl;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.detection.constant.DetectionResultStatus;
import com.ruoyi.detection.constant.ReportResultStatus;
import com.ruoyi.detection.constant.ReportStatus;
import com.ruoyi.detection.constant.ReportTemplateURL;
import com.ruoyi.detection.domain.*;
import com.ruoyi.detection.domain.vo.*;
import com.ruoyi.detection.mapper.ReportMapper;
import com.ruoyi.detection.mapper.ReportTemplateMapper;
import com.ruoyi.detection.service.ICommissionSampleService;
import com.ruoyi.detection.service.IDetectionResultService;
import com.ruoyi.detection.service.IReportService;
import com.ruoyi.review.constant.ReviewCategory;
import com.ruoyi.review.constant.ReviewCirculation;
import com.ruoyi.review.constant.ReviewConclusion;
import com.ruoyi.review.constant.ReviewStatus;
import com.ruoyi.review.domain.Review;
import com.ruoyi.review.mapper.ReviewMapper;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.utils.AsposeUtil;
import com.ruoyi.utils.WordUtil;
import org.apache.poi.hssf.record.DVALRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.io.IOException;
import java.math.BigDecimal;
import java.security.Security;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * 检测报告打印邮寄Service业务层处理
 *
 * @author zhangkui
 * @date 2021-08-08
 */
@Service
public class ReportServiceImpl implements IReportService {
    @Autowired
    private ReportMapper reportMapper;

    @Autowired
    private ISysUserService iSysUserService;

    @Autowired
    private ICommissionSampleService commissionSampleService;

    @Autowired
    private ReviewMapper reviewMapper;

    @Autowired
    private IDetectionResultService detectionResultService;

    @Autowired
    private ReportTemplateMapper reportTemplateMapper;

    /**
     * 查询检测报告打印邮寄
     *
     * @param reportID 检测报告打印邮寄ID
     * @return 检测报告打印邮寄
     */
    @Override
    public Report selectReportById(Long reportID) {
        return reportMapper.selectReportById(reportID);
    }

    /**
     * 查询检测报告打印邮寄列表
     *
     * @param report 检测报告打印邮寄
     * @return 检测报告打印邮寄
     */
    @Override
    public List<Report> selectReportList(Report report) {
        return reportMapper.selectReportList(report);
    }

    /**
     * 新增检测报告打印邮寄
     *
     * @param report 检测报告打印邮寄
     * @return 结果
     */
    @Override
    public int insertReport(Report report) {
        return reportMapper.insertReport(report);
    }

    /**
     * 修改检测报告打印邮寄
     *
     * @param report 检测报告打印邮寄
     * @return 结果
     */
    @Override
    public int updateReport(Report report) {
        return reportMapper.updateReport(report);
    }

    /**
     * 批量删除检测报告打印邮寄
     *
     * @param reportIDs 需要删除的检测报告打印邮寄ID
     * @return 结果
     */
    @Override
    public int deleteReportByIds(Long[] reportIDs) {
        return reportMapper.deleteReportByIds(reportIDs);
    }

    /**
     * 删除检测报告打印邮寄信息
     *
     * @param reportID 检测报告打印邮寄ID
     * @return 结果
     */
    @Override
    public int deleteReportById(Long reportID) {
        return reportMapper.deleteReportById(reportID);
    }

    @Transactional
    @Override
    public List<ReportVO> selectReportVOList(ReportVO reportVO) {

        List<ReportVO> reportVOList = reportMapper.selectReportVOList(reportVO);
        for (ReportVO vo : reportVOList) {
            SysUser edit = iSysUserService.selectUserById(vo.getEditID());
            if (edit != null) vo.setEditName(edit.getNickName());

            SysUser issue = iSysUserService.selectUserById(vo.getIssueID());
            if (issue != null) vo.setIssueName(issue.getNickName());

            SysUser reviewer = iSysUserService.selectUserById(vo.getReviewerID());
            if (reviewer != null) vo.setReviewerName(reviewer.getNickName());

            SysUser print = iSysUserService.selectUserById(vo.getPrintMailID());
            if (print != null) vo.setPrintMailName(print.getNickName());
        }


        return reportVOList;
    }

    @Transactional
    //查询可以生成报告的树
    @Override
    public List<DetectionTree> selectPassList() {

        List<Long> passList = reportMapper.selectPassList();
        TreeSet<String> treeSet = new TreeSet<>();
        for (Long pass : passList) {
            CommissionSample commissionSample = commissionSampleService.selectCommissionSampleById(pass);
            treeSet.add(commissionSample.getPrincipalNumber());
        }

        List<DetectionTree> detectionTrees = new ArrayList<>();
        for (String priID : treeSet) {
            List<DetectionTree> childs = new ArrayList<>();
            for (Long pass : passList) {

                CommissionSample commissionSample = commissionSampleService.selectCommissionSampleById(pass);
                if (commissionSample.getPrincipalNumber().equals(priID)) {
                    DetectionTree child = new DetectionTree(commissionSample.getSampleNumber());
                    childs.add(child);
                }

            }
            DetectionTree detectionTree = new DetectionTree(priID, childs);
            detectionTrees.add(detectionTree);
        }

        return detectionTrees;
    }

    @Transactional
    @Override
    public List<DetectionTree> selectNoPassList() {
        List<Long> passList = reportMapper.selectNoPassList();
        TreeSet<String> treeSet = new TreeSet<>();
        for (Long pass : passList) {
            CommissionSample commissionSample = commissionSampleService.selectCommissionSampleById(pass);
            treeSet.add(commissionSample.getPrincipalNumber());
        }

        List<DetectionTree> detectionTrees = new ArrayList<>();
        for (String priID : treeSet) {
            List<DetectionTree> childs = new ArrayList<>();
            for (Long pass : passList) {

                CommissionSample commissionSample = commissionSampleService.selectCommissionSampleById(pass);
                if (commissionSample.getPrincipalNumber().equals(priID)) {
                    DetectionTree child = new DetectionTree(commissionSample.getSampleNumber());
                    childs.add(child);
                }

            }
            DetectionTree detectionTree = new DetectionTree(priID, childs);
            detectionTrees.add(detectionTree);
        }

        return detectionTrees;
    }

    //重新生成报告
    @Transactional
    @Override
    public int reCreateReport(List<DetectionResultVO> detectionResultVOList,Long reportTempID) throws Exception {
        //查出有多少个样品
        TreeSet<String> sampleNumbers = new TreeSet<>();
        for (DetectionResultVO detectionResultVO : detectionResultVOList) {
            System.out.println(detectionResultVO);
            sampleNumbers.add(detectionResultVO.getSampleNumber());
        }

        //对样品进行赋值
        for (String sampleNumber : sampleNumbers) {

            QueryCommissionSampleVO queryCommissionSampleVO = new QueryCommissionSampleVO();
            queryCommissionSampleVO.setSampleNumber(sampleNumber);
            List<CommissionSampleRegisterVO> commissionSamples = commissionSampleService.searchAllCommissionSampleRegister(queryCommissionSampleVO);

            //封装报告
            List<String> detectionList = new ArrayList<>();
            List<ReportDetailInfoVO> reportDetailInfoVOList = new ArrayList<>();
            for (DetectionResultVO detectionResultVO : detectionResultVOList) {
                if (detectionResultVO.getSampleNumber().equals(sampleNumber)) {
                    ReportDetailInfoVO reportDetailInfoVO = new ReportDetailInfoVO(detectionResultVO.getPesticideName(), detectionResultVO.getDetectionData(), detectionResultVO.getMaxContent(), true, detectionResultVO.getInspectionMethodName());
                    reportDetailInfoVOList.add(reportDetailInfoVO);
                    detectionList.add(reportDetailInfoVO.getDetectionItem());
                }
            }

            ReportResultVO reportResultVO = new ReportResultVO(commissionSamples.get(0).getSampleName(), commissionSamples.get(0).getSampleNumber(), commissionSamples.get(0).getSampleQuantity(),
                    commissionSamples.get(0).getSamplingTime(), commissionSamples.get(0).getSampleSenderName(), commissionSamples.get(0).getOperationTime(), commissionSamples.get(0).getDetectionCategory(),
                    commissionSamples.get(0).getProductionUnit(), commissionSamples.get(0).getProductionUnitAddress(), commissionSamples.get(0).getProductionUnit(), commissionSamples.get(0).getProductionUnitAddress(),
                    commissionSamples.get(0).getDetectionStandardName(), detectionList, 1, 1, reportDetailInfoVOList);

            //获取模板地址
            ReportTemplate reportTemplate = reportTemplateMapper.selectReportTemplateById(reportTempID);
            String reportTemURL = reportTemplate.getReportTemURL();

            //生成报告
            WordUtil.generateWord(WordUtil.getWordData(reportResultVO), sampleNumber,reportTemURL);
            //doc转pdf
            AsposeUtil.docTurnPdf(ReportTemplateURL.WORD_PATH + sampleNumber + ".doc", ReportTemplateURL.PDF_PATH + sampleNumber + ".pdf");
            //往报告结果表更新数据

            Report report = new Report();
            report.setSampleID(commissionSamples.get(0).getSampleID());
            report.setSampleNumber(commissionSamples.get(0).getSampleNumber());
            List<Report> reports = reportMapper.selectReportList(report);
            reports.get(0).setReportStatus(ReportStatus.REPORT_DEFAULT);
            reports.get(0).setReportResult(ReportResultStatus.REPORT_DEFAULT);
            reports.get(0).setReviewerID(null);
            reports.get(0).setReviewerTime(null);
            reportMapper.updateReport(reports.get(0));

            //往审核表更新数据
            Review review=new Review();
            review.setSampleID(reports.get(0).getSampleID());
            review.setAssignOrResultID(reports.get(0).getReportID());
            List<Review> reviews = reviewMapper.selectReviewList(review);
            reviews.get(0).setReviewConclusion(0);
            reviews.get(0).setCirculation(0);
//            reviews.get(0).setOperationTime(null);
//            reviews.get(0).setOperatorID(null);
            int i = reviewMapper.updateReview(reviews.get(0));
            List<Review> review1 = reviewMapper.selectReviewList(review);
            System.out.println(review1);
            System.out.println(i);
            System.out.println(sampleNumber + "重新生成成功");

            //生成成功后，将选中的检测结果置为选中
            for (DetectionResultVO detectionResultVO : detectionResultVOList) {
                if (detectionResultVO.getSampleNumber().equals(sampleNumber)) {
                    DetectionResult detectionResult = detectionResultService.selectDetectionResultById(detectionResultVO.getDetectionResultID());
                    detectionResult.setIsChosen(DetectionResultStatus.DETECTION_ISCHOSEN);
                    detectionResultService.updateDetectionResult(detectionResult);
                }
            }
        }
        return 1;
    }

    @Override
    public List<TemplateVO> selectAllTem() {
        SysUser sysUser = iSysUserService.selectUserById(SecurityUtils.getLoginUser().getUser().getUserId());

        List<TemplateVO> templateVOS = reportMapper.selectAllTem(1l);
        return templateVOS;
    }

    //生成报告
    @Transactional
    @Override
    public int createReport(List<DetectionResultVO> detectionResultVOList,Long reportTempID) throws Exception {
        //查出有多少个样品
        TreeSet<String> sampleNumbers = new TreeSet<>();
        for (DetectionResultVO detectionResultVO : detectionResultVOList) {
            System.out.println(detectionResultVO);
            sampleNumbers.add(detectionResultVO.getSampleNumber());
        }
        //对样品进行赋值
        for (String sampleNumber : sampleNumbers) {

            QueryCommissionSampleVO queryCommissionSampleVO = new QueryCommissionSampleVO();
            queryCommissionSampleVO.setSampleNumber(sampleNumber);
            List<CommissionSampleRegisterVO> commissionSamples = commissionSampleService.searchAllCommissionSampleRegister(queryCommissionSampleVO);

            //封装报告
            List<String> detectionList = new ArrayList<>();
            List<ReportDetailInfoVO> reportDetailInfoVOList = new ArrayList<>();
            for (DetectionResultVO detectionResultVO : detectionResultVOList) {
                if (detectionResultVO.getSampleNumber().equals(sampleNumber)) {
                    ReportDetailInfoVO reportDetailInfoVO = new ReportDetailInfoVO(detectionResultVO.getPesticideName(), detectionResultVO.getDetectionData(), detectionResultVO.getMaxContent(), true, detectionResultVO.getInspectionMethodName());
                    reportDetailInfoVOList.add(reportDetailInfoVO);
                    detectionList.add(reportDetailInfoVO.getDetectionItem());
                }
            }

            ReportResultVO reportResultVO = new ReportResultVO(commissionSamples.get(0).getSampleName(), commissionSamples.get(0).getSampleNumber(), commissionSamples.get(0).getSampleQuantity(),
                    commissionSamples.get(0).getSamplingTime(), commissionSamples.get(0).getSampleSenderName(), commissionSamples.get(0).getOperationTime(), commissionSamples.get(0).getDetectionCategory(),
                    commissionSamples.get(0).getProductionUnit(), commissionSamples.get(0).getProductionUnitAddress(), commissionSamples.get(0).getProductionUnit(), commissionSamples.get(0).getProductionUnitAddress(),
                    commissionSamples.get(0).getDetectionStandardName(), detectionList, 1, 1, reportDetailInfoVOList);

            //获取报告模板
            ReportTemplate reportTemplate = reportTemplateMapper.selectReportTemplateById(reportTempID);
            String reportTemURL = reportTemplate.getReportTemURL();
            //生成报告
            WordUtil.generateWord(WordUtil.getWordData(reportResultVO), sampleNumber,reportTemURL);
            //doc转pdf
            AsposeUtil.docTurnPdf(ReportTemplateURL.WORD_PATH + sampleNumber + ".doc", ReportTemplateURL.PDF_PATH + sampleNumber + ".pdf");
            //往报告结果表里插入数据
            String reportNumber = "BG-" + sampleNumber;
            Report report = new Report(reportNumber, commissionSamples.get(0).getSampleID(), commissionSamples.get(0).getSampleNumber(), ReportResultStatus.REPORT_DEFAULT, ReportStatus.REPORT_DEFAULT);
            reportMapper.insertReport(report);
            List<Report> reports = reportMapper.selectReportList(report);

            //往审核表里插数据
            Review review = new Review(reports.get(0).getReportID(), commissionSamples.get(0).getSampleID(), commissionSamples.get(0).getPriority(), ReviewCategory.REPORT_REVIEW[0], DateUtils.getNowDate());
            reviewMapper.insertReview(review);

            System.out.println(sampleNumber + "生成成功");

            //生成成功后，将选中的检测结果置为选中
            for (DetectionResultVO detectionResultVO : detectionResultVOList) {
                if (detectionResultVO.getSampleNumber().equals(sampleNumber)) {
                    DetectionResult detectionResult = detectionResultService.selectDetectionResultById(detectionResultVO.getDetectionResultID());
                    detectionResult.setIsChosen(DetectionResultStatus.DETECTION_ISCHOSEN);
                    detectionResultService.updateDetectionResult(detectionResult);
                }
            }
        }
        return 1;
    }

    //签发
    @Transactional
    @Override
    public int reportPass(Report report) {


        report.setReportStatus(ReportStatus.REPORT_PASS);
        List<Report> reports = reportMapper.selectReportList(report);
        reports.get(0).setReportStatus(ReportStatus.REPORT_ISSUE_PASS);
        reports.get(0).setIssueID(SecurityUtils.getLoginUser().getUser().getUserId());
        reports.get(0).setIssueTime(DateUtils.getNowDate());
        return reportMapper.updateReport(reports.get(0));
    }

    //打回撰写
    @Transactional
    @Override
    public int reportFail(Report report) throws IOException {
        String remark = report.getRemark();
        report.setReportStatus(ReportStatus.REPORT_PASS);
        report.setRemark(new String());
        //更新报告结果
        List<Report> reports = reportMapper.selectReportList(report);
        reports.get(0).setReportStatus(ReportStatus.REPORT_ISSUE_FAIL);
        reports.get(0).setRemark(remark);
        reportMapper.updateReport(reports.get(0));
        //将该生成报告被选中复原
        DetectionResult detectionResult = new DetectionResult();
        detectionResult.setSampleNumber(report.getSampleNumber());
        detectionResult.setIsChosen(1);

        List<DetectionResult> detectionResults = detectionResultService.selectDetectionResultList(detectionResult);
        if (detectionResults.size() != 0) {
            for (DetectionResult result : detectionResults) {
                result.setIsChosen(0);
                detectionResultService.updateDetectionResult(result);
            }
        }
        //将报告删除
        AsposeUtil.deleteFile(ReportTemplateURL.WORD_PATH + report.getReportNumber() + ".doc");
        AsposeUtil.deleteFile(ReportTemplateURL.PDF_PATH + report.getReportNumber() + ".pdf");


        return 1;


    }


}

