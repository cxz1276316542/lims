package com.ruoyi.detection.service;

import com.ruoyi.detection.domain.DetectionTree;
import com.ruoyi.detection.domain.Report;
import com.ruoyi.detection.domain.vo.DetectionResultVO;
import com.ruoyi.detection.domain.vo.ReportTemplateVO;
import com.ruoyi.detection.domain.vo.ReportVO;
import com.ruoyi.detection.domain.vo.TemplateVO;

import java.io.IOException;
import java.util.List;

/**
 * 检测报告打印邮寄Service接口
 * 
 * @author zhangkui
 * @date 2021-08-08
 */
public interface IReportService 
{
    /**
     * 查询检测报告打印邮寄
     * 
     * @param reportID 检测报告打印邮寄ID
     * @return 检测报告打印邮寄
     */
    public Report selectReportById(Long reportID);

    /**
     * 查询检测报告打印邮寄列表
     * 
     * @param report 检测报告打印邮寄
     * @return 检测报告打印邮寄集合
     */
    public List<Report> selectReportList(Report report);

    /**
     * 新增检测报告打印邮寄
     * 
     * @param report 检测报告打印邮寄
     * @return 结果
     */
    public int insertReport(Report report);

    /**
     * 修改检测报告打印邮寄
     * 
     * @param report 检测报告打印邮寄
     * @return 结果
     */
    public int updateReport(Report report);

    /**
     * 批量删除检测报告打印邮寄
     * 
     * @param reportIDs 需要删除的检测报告打印邮寄ID
     * @return 结果
     */
    public int deleteReportByIds(Long[] reportIDs);

    /**
     * 删除检测报告打印邮寄信息
     * 
     * @param reportID 检测报告打印邮寄ID
     * @return 结果
     */
    public int deleteReportById(Long reportID);

    List<ReportVO> selectReportVOList(ReportVO reportVO);


    List<DetectionTree> selectPassList();

    int createReport(List<DetectionResultVO> detectionResultVOList,Long reportTempID) throws Exception;

    int reportPass(Report report);

    int reportFail(Report report) throws IOException;

    List<DetectionTree> selectNoPassList();

    int reCreateReport(List<DetectionResultVO> detectionResultVOList,Long reportTempID) throws Exception;

    List<TemplateVO> selectAllTem();
}
