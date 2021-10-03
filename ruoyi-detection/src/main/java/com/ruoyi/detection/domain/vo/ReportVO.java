package com.ruoyi.detection.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

public class ReportVO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 检测报告ID */
    @Excel(name = "检测报告ID")
    private Long reportID;

    /** 报告编号（由各机构确定格式自动生成） */
    @Excel(name = "报告编号", readConverterExp = "由=各机构确定格式自动生成")
    private String reportNumber;

    /** 样品编号 */
    @Excel(name = "样品编号")
    private String sampleNumber;

    /** 样品学名 */
    @Excel(name = "样品学名")
    private String sampleAcademicName;

    /** 优先级
     * 1-正常；2-急；3-加急；4-特急（客户、业务员操作）默认正常
     * */
    @Excel(name = "优先级")
    private Integer priority;

    /** 检测标准 */
    @Excel(name = "检测标准")
    private String standard;

    /** 检测报告判定结果:1-判定合格，2-判定不合格，3-不做判定 */
    @Excel(name = "检测报告判定结果:1-判定合格，2-判定不合格，3-不做判定")
    private Integer reportResult;

    /** 状态 0-默认
     1-拟稿
     2-审核
     3-签发
     4-打印、邮寄
     */
    @Excel(name = "状态 0-默认 1-拟稿 2-审核 3-签发 4-打印、邮寄 ")
    private Integer reportStatus;


    /** 拟稿人ID */
    @Excel(name = "拟稿人ID")
    private Long editID;

    /** 拟稿人名称 */
    @Excel(name = "拟稿人名称")
    private String editName;

    /** 拟稿时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "拟稿时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date editTime;

    /** 签发人ID */
    @Excel(name = "签发人ID")
    private Long issueID;

    /** 签发人名称 */
    @Excel(name = "签发人名称")
    private String issueName;

    /** 签发时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "签发时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date issueTime;

    /** 打印邮寄人ID */
    @Excel(name = "打印邮寄人ID")
    private Long printMailID;

    /** 打印邮寄人名称 */
    @Excel(name = "打印邮寄人名称")
    private String printMailName;

    /** 打印邮寄时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "打印邮寄时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date printMailTime;

    /** 审核人ID */
    @Excel(name = "审核人ID")
    private Long reviewerID;

    /** 审核人名称 */
    @Excel(name = "审核人名称")
    private String reviewerName;

    /** 审核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "审核时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date reviewerTime;

    @Excel(name = "备注")
    private String remark;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getReportID() {
        return reportID;
    }

    public void setReportID(Long reportID) {
        this.reportID = reportID;
    }

    public String getReportNumber() {
        return reportNumber;
    }

    public void setReportNumber(String reportNumber) {
        this.reportNumber = reportNumber;
    }

    public String getSampleNumber() {
        return sampleNumber;
    }

    public void setSampleNumber(String sampleNumber) {
        this.sampleNumber = sampleNumber;
    }

    public String getSampleAcademicName() {
        return sampleAcademicName;
    }

    public void setSampleAcademicName(String sampleAcademicName) {
        this.sampleAcademicName = sampleAcademicName;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public Integer getReportResult() {
        return reportResult;
    }

    public void setReportResult(Integer reportResult) {
        this.reportResult = reportResult;
    }

    public Integer getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(Integer reportStatus) {
        this.reportStatus = reportStatus;
    }

    public Long getEditID() {
        return editID;
    }

    public void setEditID(Long editID) {
        this.editID = editID;
    }

    public String getEditName() {
        return editName;
    }

    public void setEditName(String editName) {
        this.editName = editName;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    public Long getIssueID() {
        return issueID;
    }

    public void setIssueID(Long issueID) {
        this.issueID = issueID;
    }

    public String getIssueName() {
        return issueName;
    }

    public void setIssueName(String issueName) {
        this.issueName = issueName;
    }

    public Date getIssueTime() {
        return issueTime;
    }

    public void setIssueTime(Date issueTime) {
        this.issueTime = issueTime;
    }

    public Long getPrintMailID() {
        return printMailID;
    }

    public void setPrintMailID(Long printMailID) {
        this.printMailID = printMailID;
    }

    public String getPrintMailName() {
        return printMailName;
    }

    public void setPrintMailName(String printMailName) {
        this.printMailName = printMailName;
    }

    public Date getPrintMailTime() {
        return printMailTime;
    }

    public void setPrintMailTime(Date printMailTime) {
        this.printMailTime = printMailTime;
    }

    public Long getReviewerID() {
        return reviewerID;
    }

    public void setReviewerID(Long reviewerID) {
        this.reviewerID = reviewerID;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public Date getReviewerTime() {
        return reviewerTime;
    }

    public void setReviewerTime(Date reviewerTime) {
        this.reviewerTime = reviewerTime;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "ReportVO{" +
                "reportID=" + reportID +
                ", reportNumber='" + reportNumber + '\'' +
                ", sampleNumber='" + sampleNumber + '\'' +
                ", sampleAcademicName='" + sampleAcademicName + '\'' +
                ", priority=" + priority +
                ", standard='" + standard + '\'' +
                ", reportResult=" + reportResult +
                ", reportStatus=" + reportStatus +
                ", editID=" + editID +
                ", editName='" + editName + '\'' +
                ", editTime=" + editTime +
                ", issueID=" + issueID +
                ", issueName='" + issueName + '\'' +
                ", issueTime=" + issueTime +
                ", printMailID=" + printMailID +
                ", printMailName='" + printMailName + '\'' +
                ", printMailTime=" + printMailTime +
                ", reviewerID=" + reviewerID +
                ", reviewerName='" + reviewerName + '\'' +
                ", reviewerTime=" + reviewerTime +
                ", remark='" + remark + '\'' +
                '}';
    }
}
