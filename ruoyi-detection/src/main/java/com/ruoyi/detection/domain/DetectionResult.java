package com.ruoyi.detection.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 检测结果对象 ypjc_jcjg
 * 
 * @author zhangkui
 * @date 2021-08-01
 */
public class DetectionResult extends BaseEntity
{
    private static final long serialVersionUID = 1L;




    /** 项目检测数据记录ID */
    @Excel(name = "项目检测数据记录ID")
    private Long detectionResultID;

    /** 任务分派ID */
    @Excel(name = "任务分派ID")
    private Long taskAssignID;


    /** 委托样品ID */
    @Excel(name = "委托样品ID")
    private Long sampleID;

    /** 样品编号 */
    @Excel(name = "样品编号")
    private String sampleNumber;

    /** 检测任务单编号 */
    @Excel(name = "检测任务单编号")
    private String taskNumber;

    /** 检测农药ID */
    @Excel(name = "检测农药ID")
    private Long pesticideID;

    /** 检测员ID */
    @Excel(name = "检测员ID")
    private Long inspectorID;

    /** 检测数据 */
    @Excel(name = "检测数据")
    private BigDecimal detectionData;

    /** 审核状态(0-提交审核，审核中，1-审核通过，2-审核不通过，3-保存) */
    @Excel(name = "审核状态(0-提交审核，审核中，1-审核通过，2-审核不通过，3-保存)")
    private Integer auditStatus;



       /** 是否被选中生成报告（0-默认，1-选中） */
    @Excel(name = "是否被选中生成报告（0-默认（未选中），1-选中）")
    private Integer isChosen;

    public Integer getIsChosen() {
        return isChosen;
    }

    public void setIsChosen(Integer isChosen) {
        this.isChosen = isChosen;
    }

    /** 操作时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "操作时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date operationTime;

    public DetectionResult(){

    }

    public DetectionResult(String taskNumber, Long sampleID, String sampleNumber, Long pesticideID, Long inspectorID, Integer auditStatus, Date operationTime, Long taskAssignID) {
        this.taskAssignID=taskAssignID;
        this.taskNumber=taskNumber;
        this.sampleID = sampleID;
        this.sampleNumber = sampleNumber;
        this.pesticideID = pesticideID;
        this.inspectorID = inspectorID;
        this.auditStatus = auditStatus;
        this.operationTime = operationTime;
    }

    public Long getTaskAssignID() {
        return taskAssignID;
    }

    public void setTaskAssignID(Long taskAssignID) {
        this.taskAssignID = taskAssignID;
    }

    public String getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(String taskNumber) {
        this.taskNumber = taskNumber;
    }

    public void setDetectionResultID(Long detectionResultID)
    {
        this.detectionResultID = detectionResultID;
    }

    public Long getDetectionResultID() 
    {
        return detectionResultID;
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
    public void setPesticideID(Long pesticideID) 
    {
        this.pesticideID = pesticideID;
    }

    public Long getPesticideID() 
    {
        return pesticideID;
    }
    public void setInspectorID(Long inspectorID) 
    {
        this.inspectorID = inspectorID;
    }

    public Long getInspectorID() 
    {
        return inspectorID;
    }
    public void setDetectionData(BigDecimal detectionData) 
    {
        this.detectionData = detectionData;
    }

    public BigDecimal getDetectionData() 
    {
        return detectionData;
    }
    public void setAuditStatus(Integer auditStatus) 
    {
        this.auditStatus = auditStatus;
    }

    public Integer getAuditStatus() 
    {
        return auditStatus;
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
        return "DetectionResult{" +
                "detectionResultID=" + detectionResultID +
                ", taskAssignID=" + taskAssignID +
                ", sampleID=" + sampleID +
                ", sampleNumber='" + sampleNumber + '\'' +
                ", taskNumber='" + taskNumber + '\'' +
                ", pesticideID=" + pesticideID +
                ", inspectorID=" + inspectorID +
                ", detectionData=" + detectionData +
                ", auditStatus=" + auditStatus +
                ", isChosen=" + isChosen +
                ", operationTime=" + operationTime +
                '}';
    }
}
