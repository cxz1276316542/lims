package com.ruoyi.review.domain.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.Map;

/**
 * 审核对象 ypjc_sh
 *
 * @author zjccc
 * @date 2021-07-31
 */
public class ReviewVO extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 审核ID */
    private Long reviewID;

    /** 审核主体，用于展示需审核内容的基本信息 */
    private Map<String,Object> subject;

    /** 样品ID */
    private Long sampleID;

    /** 样品编号 由各机构确定格式自动生成 */
    @Excel(name = "样品编号 由各机构确定格式自动生成")
    private String sampleNumber;

    /** 样品学名 */
    @Excel(name = "样品学名")
    private String sampleScientificName;

    /** 优先级
     * 1-正常；2-急；3-加急；4-特急（客户、业务员操作）默认正常
     * */
    @Excel(name = "优先级")
    private Integer priority;

    /** 审核类别
     * 0-默认
     * 11-委托单初次审核
     * 12-委托单复审
     * 21-任务单初次审核
     * 22-任务单复审
     * 31-检测项目初次审核
     * 32-检测项目复审
     * 33-检测报告审核（签发）
     * 其中委托、任务单、检测项目为初审或复审，检测报告审核统一初审
     * 即 11、21、31、33-初审 22、32-复审
     * */
    @Excel(name = "审核类别")
    private Integer reviewCategory;

    /** 检测项目结果或任务分派ID
     * 当审核类别为任务分派或检测项目结果时，需要填写
     */
    private Long assignOrResultID;

    /** 审核结论
     * 1-通过，2-不通过
     * */
    @Excel(name = "审核结论")
    private Integer reviewConclusion;

    /** 审核意见
     * 结论不通过需填写
     * */
    @Excel(name = "审核意见")
    private String reviewOpinion;

    /** 流转标识
     0--默认
     对检品审核：
     1-回转到业务员；
     2-回转到客户;
     对检测审核：
     1-回转到检测员；
     2-回转到任务分派；
     对报告审核：
     1-回转到报告撰写人；
     2-回转到检测员；
     3-回转到任务分派；
     */
    private Integer circulation;

    /** 电子签名的地址 */
    private String electronicSignature;

    /** 操作员ID */
    private Long operatorID;

    /** 操作员名称 */
    @Excel(name = "操作员名称")
    private String operatorName;

    /** 操作时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "操作时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date operationTime;

    /**
     * 审核开始时间
     */
    private Date reviewStartTime;
    /**
     * 审核结束时间
     */
    private Date reviewEndTime;

    /** 备用 */
    private String remarks;

    //以下字段用于各个审核的特殊查询条件

    /** 报告撰写人ID */
    private Long reportWriterID;

    /** 报告撰写人名称 */
    private String reportWriterName;

    /** 任务分派人ID */
    private Long assignorID;

    /** 任务分派人名称 */
    private String assignorName;

    /** 样品登记人ID */
    private Long registrantID;

    /** 样品登记人名称 */
    private String registrantName;

    /** 检测人ID */
    private Long inspectorID;

    /** 检测人名称 */
    private String inspectorName;

    public Long getInspectorID() {
        return inspectorID;
    }

    public void setInspectorID(Long inspectorID) {
        this.inspectorID = inspectorID;
    }

    public String getReportWriterName() {
        return reportWriterName;
    }

    public void setReportWriterName(String reportWriterName) {
        this.reportWriterName = reportWriterName;
    }

    public Long getAssignorID() {
        return assignorID;
    }

    public String getAssignorName() {
        return assignorName;
    }

    public void setAssignorName(String assignorName) {
        this.assignorName = assignorName;
    }

    public Long getRegistrantID() {
        return registrantID;
    }

    public void setRegistrantID(Long registrantID) {
        this.registrantID = registrantID;
    }

    public String getRegistrantName() {
        return registrantName;
    }

    public void setRegistrantName(String registrantName) {
        this.registrantName = registrantName;
    }

    public String getInspectorName() {
        return inspectorName;
    }

    public void setInspectorName(String inspectorName) {
        this.inspectorName = inspectorName;
    }

    public void setAssignorID(Long assignorID) {
        this.assignorID = assignorID;
    }

    public Long getReportWriterID() {
        return reportWriterID;
    }

    public void setReportWriterID(Long reportWriterID) {
        this.reportWriterID = reportWriterID;
    }

    public Date getReviewStartTime() {
        return reviewStartTime;
    }

    public void setReviewStartTime(Date reviewStartTime) {
        this.reviewStartTime = reviewStartTime;
    }

    public Date getReviewEndTime() {
        return reviewEndTime;
    }

    public void setReviewEndTime(Date reviewEndTime) {
        this.reviewEndTime = reviewEndTime;
    }

    public Long getReviewID() {
        return reviewID;
    }

    public void setReviewID(Long reviewID) {
        this.reviewID = reviewID;
    }

    public Map<String, Object> getSubject() {
        return subject;
    }

    public void setSubject(Map<String, Object> subject) {
        this.subject = subject;
    }

    public Long getSampleID() {
        return sampleID;
    }

    public void setSampleID(Long sampleID) {
        this.sampleID = sampleID;
    }

    public String getSampleNumber() {
        return sampleNumber;
    }

    public void setSampleNumber(String sampleNumber) {
        this.sampleNumber = sampleNumber;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getReviewCategory() {
        return reviewCategory;
    }

    public void setReviewCategory(Integer reviewCategory) {
        this.reviewCategory = reviewCategory;
    }

    public Integer getReviewConclusion() {
        return reviewConclusion;
    }

    public void setReviewConclusion(Integer reviewConclusion) {
        this.reviewConclusion = reviewConclusion;
    }

    public String getReviewOpinion() {
        return reviewOpinion;
    }

    public void setReviewOpinion(String reviewOpinion) {
        this.reviewOpinion = reviewOpinion;
    }

    public Integer getCirculation() {
        return circulation;
    }

    public void setCirculation(Integer circulation) {
        this.circulation = circulation;
    }

    public String getElectronicSignature() {
        return electronicSignature;
    }

    public void setElectronicSignature(String electronicSignature) {
        this.electronicSignature = electronicSignature;
    }

    public Long getOperatorID() {
        return operatorID;
    }

    public void setOperatorID(Long operatorID) {
        this.operatorID = operatorID;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public Date getOperationTime() {
        return operationTime;
    }

    public String getSampleScientificName() {
        return sampleScientificName;
    }

    public void setSampleScientificName(String sampleScientificName) {
        this.sampleScientificName = sampleScientificName;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Long getAssignOrResultID() {
        return assignOrResultID;
    }

    public void setAssignOrResultID(Long assignOrResultID) {
        this.assignOrResultID = assignOrResultID;
    }

    @Override
    public String toString() {
        return "ReviewVO{" +
                "reviewID=" + reviewID +
                ", subject=" + subject +
                ", sampleID=" + sampleID +
                ", sampleNumber='" + sampleNumber + '\'' +
                ", priority=" + priority +
                ", reviewCategory='" + reviewCategory + '\'' +
                ", reviewConclusion=" + reviewConclusion +
                ", reviewOpinion='" + reviewOpinion + '\'' +
                ", circulation=" + circulation +
                ", electronicSignature='" + electronicSignature + '\'' +
                ", operatorID=" + operatorID +
                ", operatorName=" + operatorName +
                ", operationTime=" + operationTime +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
