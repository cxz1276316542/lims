package com.ruoyi.detection.domain.vo;

import com.ruoyi.common.core.domain.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author liukun
 * @date 2021/8/12
 */
public class TaskAssignQueryParamVO extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 7423134198320619340L;
    // 检品编号
    private String sampleNumber;
    // 任务分派编号
    private String taskAssignNumber;
    // 检测农药ID
    private Long pesticideID;
    // 检测方法ID
    private Long detectionMethodID;
    // 检测人ID
    private Long inspectorID;
    // 标识
    private Integer flag;
    // 状态，1-主检员，2-副检员
    private Integer status;
    // 检测员ID
    private Long operatorID;
    // 操作开始时间
    private Date operationStartTime;
    // 操作结束时间
    private Date operationEndTime;

    @Override
    public String toString() {
        return "TaskAssignQueryParamVO{" +
                "sampleNumber='" + sampleNumber + '\'' +
                ", taskAssignNumber='" + taskAssignNumber + '\'' +
                ", pesticideID=" + pesticideID +
                ", detectionMethodID=" + detectionMethodID +
                ", inspectorID=" + inspectorID +
                ", flag=" + flag +
                ", status=" + status +
                ", operatorID=" + operatorID +
                ", operationStartTime=" + operationStartTime +
                ", operationEndTime=" + operationEndTime +
                '}';
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Long getPesticideID() {
        return pesticideID;
    }

    public void setPesticideID(Long pesticideID) {
        this.pesticideID = pesticideID;
    }

    public Long getDetectionMethodID() {
        return detectionMethodID;
    }

    public void setDetectionMethodID(Long detectionMethodID) {
        this.detectionMethodID = detectionMethodID;
    }

    public Long getInspectorID() {
        return inspectorID;
    }

    public void setInspectorID(Long inspectorID) {
        this.inspectorID = inspectorID;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Long getOperatorID() {
        return operatorID;
    }

    public void setOperatorID(Long operatorID) {
        this.operatorID = operatorID;
    }

    public Date getOperationStartTime() {
        return operationStartTime;
    }

    public void setOperationStartTime(Date operationStartTime) {
        this.operationStartTime = operationStartTime;
    }

    public Date getOperationEndTime() {
        return operationEndTime;
    }

    public void setOperationEndTime(Date operationEndTime) {
        this.operationEndTime = operationEndTime;
    }
}
