package com.ruoyi.detection.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 任务分派对象 ypjc_jcxm_rwfp
 *
 * @author lkun
 * @date 2021-08-05
 */
public class TaskAssign extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 委托样品ID */
    private Long sampleID;

    /** 自增主键 */
    private Long taskAssignID;

    /** 样品编号 */
    @Excel(name = "样品编号")
    private String sampleNumber;

    /** 任务分派编号 */
    @Excel(name = "任务分派编号")
    private String taskAssignNumber;

    /** 检测农药ID */
    @Excel(name = "检测农药ID")
    private Long pesticideID;

    /** 检测方法ID */
    @Excel(name = "检测方法ID")
    private Long detectionMethod;

    /** 检测人ID */
    @Excel(name = "检测人ID")
    private Long inspectorID;

    /** 标识 是否为初检（0-否，1-是） */
    @Excel(name = "标识 是否为初检", readConverterExp = "0=-否，1-是")
    private Integer flag;

    /** 审核状态(0-提交审核，审核中，1-审核通过，2-审核不通过，3-保存，
4-认领失败，5-认领)
 */
    @Excel(name = "审核状态(0-提交审核，审核中，1-审核通过，2-审核不通过，3-保存， 4-认领失败，5-认领")
    private Integer reviewStatus;

    /** 操作员ID */
    @Excel(name = "操作员ID")
    private Long operatorID;

    /** 操作时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "操作时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date operationTime;

    public void setSampleID(Long sampleID)
    {
        this.sampleID = sampleID;
    }

    public Long getSampleID()
    {
        return sampleID;
    }
    public void setTaskAssignID(Long taskAssignID)
    {
        this.taskAssignID = taskAssignID;
    }

    public Long getTaskAssignID()
    {
        return taskAssignID;
    }
    public void setSampleNumber(String sampleNumber)
    {
        this.sampleNumber = sampleNumber;
    }

    public String getSampleNumber()
    {
        return sampleNumber;
    }
    public void setTaskAssignNumber(String taskAssignNumber)
    {
        this.taskAssignNumber = taskAssignNumber;
    }

    public String getTaskAssignNumber()
    {
        return taskAssignNumber;
    }
    public void setPesticideID(Long pesticideID)
    {
        this.pesticideID = pesticideID;
    }

    public Long getPesticideID()
    {
        return pesticideID;
    }
    public void setDetectionMethod(Long detectionMethod)
    {
        this.detectionMethod = detectionMethod;
    }

    public Long getDetectionMethod()
    {
        return detectionMethod;
    }
    public void setInspectorID(Long inspectorID)
    {
        this.inspectorID = inspectorID;
    }

    public Long getInspectorID()
    {
        return inspectorID;
    }
    public void setFlag(Integer flag)
    {
        this.flag = flag;
    }

    public Integer getFlag()
    {
        return flag;
    }
    public void setReviewStatus(Integer reviewStatus)
    {
        this.reviewStatus = reviewStatus;
    }

    public Integer getReviewStatus()
    {
        return reviewStatus;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("sampleID", getSampleID())
            .append("taskAssignID", getTaskAssignID())
            .append("sampleNumber", getSampleNumber())
            .append("taskAssignNumber", getTaskAssignNumber())
            .append("pesticideID", getPesticideID())
            .append("detectionMethod", getDetectionMethod())
            .append("inspectorID", getInspectorID())
            .append("flag", getFlag())
            .append("reviewStatus", getReviewStatus())
            .append("operatorID", getOperatorID())
            .append("operationTime", getOperationTime())
            .append("remark", getRemark())
            .toString();
    }
}
