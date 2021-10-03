package com.ruoyi.detection.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 检测报告打印邮寄对象 ypjc_jcbg
 *
 * @author zhangkui
 * @date 2021-08-08
 */
public class Report extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 检测报告ID */
    @Excel(name = "检测报告ID")
    private Long reportID;

    /** 报告编号（由各机构确定格式自动生成） */
    @Excel(name = "报告编号", readConverterExp = "由=各机构确定格式自动生成")
    private String reportNumber;

    /** 委托样品ID */
    @Excel(name = "委托样品ID")
    private Long sampleID;

    /** 样品编号 */
    @Excel(name = "样品编号")
    private String sampleNumber;

    /** 检测报告判定结果:1-判定合格，2-判定不合格，3-不做判定 */
    @Excel(name = "检测报告判定结果:1-判定合格，2-判定不合格，3-不做判定")
    private Integer reportResult;

    /** 检测报告地址 */
    @Excel(name = "检测报告地址")
    private String reportURL;

    /** 状态 0-默认
        1-拟稿
        2-审核
        3-签发
        4-打印、邮寄
 */
    @Excel(name = "状态 0-默认 1-拟稿 2-审核 3-签发 4-打印、邮寄 ")
    private Integer reportStatus;

    /** 审核人签字 */
    @Excel(name = "审核人签字")
    private String reviewerSign;

    /** 领导签字 */
    @Excel(name = "领导签字")
    private String leaderSign;

    /** 拟稿人ID */
    @Excel(name = "拟稿人ID")
    private Long editID;

    /** 拟稿时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "拟稿时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date editTime;

    /** 签发人ID */
    @Excel(name = "签发人ID")
    private Long issueID;

    /** 签发时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "签发时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date issueTime;

    /** 打印邮寄人ID */
    @Excel(name = "打印邮寄人ID")
    private Long printMailID;

    /** 打印邮寄时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "打印邮寄时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date printMailTime;

    /** 审核人ID */
    @Excel(name = "审核人ID")
    private Long reviewerID;

    /** 审核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "审核时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date reviewerTime;

    public Report(String reportNumber, Long sampleID, String sampleNumber, Integer reportResult, Integer reportStatus) {
        this.reportNumber = reportNumber;
        this.sampleID = sampleID;
        this.sampleNumber = sampleNumber;
        this.reportResult = reportResult;
        this.reportStatus = reportStatus;
    }

    public Report() {

    }

    public void setReportID(Long reportID)
    {
        this.reportID = reportID;
    }

    public Long getReportID()
    {
        return reportID;
    }
    public void setReportNumber(String reportNumber)
    {
        this.reportNumber = reportNumber;
    }

    public String getReportNumber()
    {
        return reportNumber;
    }
    public void setSampleID(Long sampleID)
    {
        this.sampleID = sampleID;
    }

    public Long getSampleID()
    {
        return sampleID;
    }
    public void setSampleNumber(String sampleNumber)
    {
        this.sampleNumber = sampleNumber;
    }

    public String getSampleNumber()
    {
        return sampleNumber;
    }
    public void setReportResult(Integer reportResult)
    {
        this.reportResult = reportResult;
    }

    public Integer getReportResult()
    {
        return reportResult;
    }
    public void setReportURL(String reportURL)
    {
        this.reportURL = reportURL;
    }

    public String getReportURL()
    {
        return reportURL;
    }
    public void setReportStatus(Integer reportStatus)
    {
        this.reportStatus = reportStatus;
    }

    public Integer getReportStatus()
    {
        return reportStatus;
    }
    public void setReviewerSign(String reviewerSign)
    {
        this.reviewerSign = reviewerSign;
    }

    public String getReviewerSign()
    {
        return reviewerSign;
    }
    public void setLeaderSign(String leaderSign)
    {
        this.leaderSign = leaderSign;
    }

    public String getLeaderSign()
    {
        return leaderSign;
    }
    public void setEditID(Long editID)
    {
        this.editID = editID;
    }

    public Long getEditID()
    {
        return editID;
    }
    public void setEditTime(Date editTime)
    {
        this.editTime = editTime;
    }

    public Date getEditTime()
    {
        return editTime;
    }
    public void setIssueID(Long issueID)
    {
        this.issueID = issueID;
    }

    public Long getIssueID()
    {
        return issueID;
    }
    public void setIssueTime(Date issueTime)
    {
        this.issueTime = issueTime;
    }

    public Date getIssueTime()
    {
        return issueTime;
    }
    public void setPrintMailID(Long printMailID)
    {
        this.printMailID = printMailID;
    }

    public Long getPrintMailID()
    {
        return printMailID;
    }
    public void setPrintMailTime(Date printMailTime)
    {
        this.printMailTime = printMailTime;
    }

    public Date getPrintMailTime()
    {
        return printMailTime;
    }
    public void setReviewerID(Long reviewerID)
    {
        this.reviewerID = reviewerID;
    }

    public Long getReviewerID()
    {
        return reviewerID;
    }
    public void setReviewerTime(Date reviewerTime)
    {
        this.reviewerTime = reviewerTime;
    }

    public Date getReviewerTime()
    {
        return reviewerTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("reportID", getReportID())
            .append("reportNumber", getReportNumber())
            .append("sampleID", getSampleID())
            .append("sampleNumber", getSampleNumber())
            .append("reportResult", getReportResult())
            .append("reportURL", getReportURL())
            .append("reportStatus", getReportStatus())
            .append("reviewerSign", getReviewerSign())
            .append("leaderSign", getLeaderSign())
            .append("editID", getEditID())
            .append("editTime", getEditTime())
            .append("issueID", getIssueID())
            .append("issueTime", getIssueTime())
            .append("printMailID", getPrintMailID())
            .append("printMailTime", getPrintMailTime())
            .append("reviewerID", getReviewerID())
            .append("reviewerTime", getReviewerTime())
            .append("remark", getRemark())
            .toString();
    }
}
