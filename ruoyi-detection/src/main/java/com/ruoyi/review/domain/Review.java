package com.ruoyi.review.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 审核对象 ypjc_sh
 *
 * @author zjccc
 * @date 2021-07-31
 */
public class Review extends BaseEntity
{

    public Review() {
    }

    private static final long serialVersionUID = 1L;

    /** 审核ID */
    private Long reviewID;

    /** 样品ID */
    @Excel(name = "样品ID")
    private Long sampleID;

    /** 优先级
     * 1-正常；2-急；3-加急；4-特急（客户、业务员操作）默认正常
     * */
    @Excel(name = "优先级")
    private Integer priority;

    /** 检测项目结果或任务分派ID
     * 当审核类别为任务分派或检测项目结果时，需要填写
     */
    private Long assignOrResultID;

    /** 审核类别
     * 0-默认
     * 11-委托单初次审核
     * 12-委托单复审
     * 21-任务单初次审核
     * 22-任务单复审
     * 31-检测项目初次审核
     * 32-检测项目复审
     * 33-检测报告审核（签发）
     * */
    @Excel(name = "审核类别")
    private Integer reviewCategory;

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
    @Excel(name = "操作员ID")
    private Long operatorID;

    /** 操作时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "操作时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date operationTime;

    /** 备用 */
    private String remarks;

    public Review(Long sampleID, Integer priority, Long assignOrResultID, Integer reviewCategory, Date operationTime) {
        this.sampleID = sampleID;
        this.priority = priority;
        this.assignOrResultID = assignOrResultID;
        this.reviewCategory = reviewCategory;
        this.operationTime = operationTime;
    }
    public Review(Long assignOrResultID, Long sampleID, Integer priority, Integer reviewCategory , Date operationTime) {
        this.assignOrResultID=assignOrResultID;
        this.sampleID = sampleID;
        this.priority = priority;
        this.reviewCategory = reviewCategory;
        this.operationTime = operationTime;
    }

    public void setReviewID(Long reviewID)
    {
        this.reviewID = reviewID;
    }

    public Long getReviewID()
    {
        return reviewID;
    }
    public void setSampleID(Long sampleID)
    {
        this.sampleID = sampleID;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Long getSampleID()
    {
        return sampleID;
    }
    public void setAssignOrResultID(Long assignOrResultID)
    {
        this.assignOrResultID = assignOrResultID;
    }

    public Long getAssignOrResultID()
    {
        return assignOrResultID;
    }
    public void setReviewCategory(Integer reviewCategory)
    {
        this.reviewCategory = reviewCategory;
    }

    public Integer getReviewCategory()
    {
        return reviewCategory;
    }
    public void setReviewConclusion(Integer reviewConclusion)
    {
        this.reviewConclusion = reviewConclusion;
    }

    public Integer getReviewConclusion()
    {
        return reviewConclusion;
    }
    public void setReviewOpinion(String reviewOpinion)
    {
        this.reviewOpinion = reviewOpinion;
    }

    public String getReviewOpinion()
    {
        return reviewOpinion;
    }
    public void setCirculation(Integer circulation)
    {
        this.circulation = circulation;
    }

    public Integer getCirculation()
    {
        return circulation;
    }
    public void setElectronicSignature(String electronicSignature)
    {
        this.electronicSignature = electronicSignature;
    }

    public String getElectronicSignature()
    {
        return electronicSignature;
    }
    public void setOperatorID(Long operatorID)
    {
        this.operatorID = operatorID;
    }

    public Long getOperatorID()
    {
        return operatorID;
    }
    public void setOperationTime(Date operationTime)
    {
        this.operationTime = operationTime;
    }

    public Date getOperationTime()
    {
        return operationTime;
    }
    public void setRemarks(String remarks)
    {
        this.remarks = remarks;
    }

    public String getRemarks()
    {
        return remarks;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("reviewID", getReviewID())
            .append("sampleID", getSampleID())
            .append("assignOrResultID", getAssignOrResultID())
            .append("reviewCategory", getReviewCategory())
            .append("reviewConclusion", getReviewConclusion())
            .append("reviewOpinion", getReviewOpinion())
            .append("circulation", getCirculation())
            .append("electronicSignature", getElectronicSignature())
            .append("operatorID", getOperatorID())
            .append("operationTime", getOperationTime())
            .append("remarks", getRemarks())
            .toString();
    }
}
