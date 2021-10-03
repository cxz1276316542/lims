package com.ruoyi.detection.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author liukun
 * @date 2021/8/11
 */
public class TaskAssignSearchVO extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 任务分派ID */
    @Excel(name = "任务分派ID ")
    private Long taskAssignID;

    /** 委托样品ID */
    @Excel(name = "委托检品ID")
    private Long sampleID;

    /** 样品编号 */
    @Excel(name = "样品编号")
    private String sampleNumber;

    /** 任务分派编号 */
    @Excel(name = "任务分派编号")
    private String taskAssignNumber;

    /** 检测农药ID */
    @Excel(name = "检测农药")
    private String pesticideName;

    /** 检测方法 */
    @Excel(name = "检测方法")
    private String detectionMethod;

    /** 检测人*/
    @Excel(name = "检测人")
    private String inspectorName;


    /** 标识 是否为初检（0-否，1-是） */
    @Excel(name = "标识")
    private Integer flag;

    @Excel(name = "状态")
    private Integer status;

    @Excel(name = "操作员")
    private String operatorName;

    @Excel(name = "操作时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date operationTime;

    @Excel(name = "备注")
    private String remark;

    @Override
    public String toString() {
        return "TaskAssignSearchVO{" +
                "taskAssignID=" + taskAssignID +
                ", sampleID=" + sampleID +
                ", sampleNumber='" + sampleNumber + '\'' +
                ", taskAssignNumber='" + taskAssignNumber + '\'' +
                ", pesticideName=" + pesticideName +
                ", detectionMethod=" + detectionMethod +
                ", inspectorName=" + inspectorName +
                ", flag=" + flag +
                ", status=" + status +
                ", operatorName='" + operatorName + '\'' +
                ", operationTime='" + operationTime + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }



    public Long getTaskAssignID() {
        return taskAssignID;
    }

    public void setTaskAssignID(Long taskAssignID) {
        this.taskAssignID = taskAssignID;
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

    public String getTaskAssignNumber() {
        return taskAssignNumber;
    }

    public void setTaskAssignNumber(String taskAssignNumber) {
        this.taskAssignNumber = taskAssignNumber;
    }

    public String getPesticideName() {
        return pesticideName;
    }

    public void setPesticideName(String pesticideName) {
        this.pesticideName = pesticideName;
    }

    public String getDetectionMethod() {
        return detectionMethod;
    }

    public void setDetectionMethod(String detectionMethod) {
        this.detectionMethod = detectionMethod;
    }

    public String getInspectorName() {
        return inspectorName;
    }

    public void setInspectorName(String inspectorName) {
        this.inspectorName = inspectorName;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
