package com.ruoyi.detection.domain.vo;

import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * @author liukun
 * @date 2021/8/6
 */
public class TaskAssignAddSearchVO extends BaseEntity {

    private static final long serialVersionUID = -1301394867838160608L;

    /**
     * 检品ID
     */
    private Long sampleID;
    /**
     * 检品编号
     */
    private String sampleNumber;
    /**
     * 检品学名
     */
    private String sampleAcademicName;
    /**
     * 检品优先级
     */
    private Integer priority;
    /**
     * 检品状态
     */
    private Integer sampleStatus;
    /**
     * 检测标准名称
     */
    private String detectionStandardName;
    /**
     * 检测标准ID
     */
    private Long detectionStandardID;
    /**
     * 检品登记开始时间
     */
    private Date sampleRegisterStartTime;
    /**
     * 检品登记结束时间
     */
    private Date sampleRegisterEndTime;

    public Long getDetectionStandardID() {
        return detectionStandardID;
    }

    public void setDetectionStandardID(Long detectionStandardID) {
        this.detectionStandardID = detectionStandardID;
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

    public Integer getSampleStatus() {
        return sampleStatus;
    }

    public void setSampleStatus(Integer sampleStatus) {
        this.sampleStatus = sampleStatus;
    }

    public String getDetectionStandardName() {
        return detectionStandardName;
    }

    public void setDetectionStandardName(String detectionStandardName) {
        this.detectionStandardName = detectionStandardName;
    }

    public Date getSampleRegisterStartTime() {
        return sampleRegisterStartTime;
    }

    public void setSampleRegisterStartTime(Date sampleRegisterStartTime) {
        this.sampleRegisterStartTime = sampleRegisterStartTime;
    }

    public Date getSampleRegisterEndTime() {
        return sampleRegisterEndTime;
    }

    public void setSampleRegisterEndTime(Date sampleRegisterEndTime) {
        this.sampleRegisterEndTime = sampleRegisterEndTime;
    }

    @Override
    public String toString() {
        return "TaskAssignAddSearchVO{" +
                "sampleID=" + sampleID +
                ", sampleNumber='" + sampleNumber + '\'' +
                ", sampleAcademicName='" + sampleAcademicName + '\'' +
                ", priority=" + priority +
                ", sampleStatus=" + sampleStatus +
                ", detectionStandardName='" + detectionStandardName + '\'' +
                ", detectionStandardID='" + detectionStandardID + '\'' +
                ", sampleRegisterStartTime=" + sampleRegisterStartTime +
                ", sampleRegisterEndTime=" + sampleRegisterEndTime +
                '}';
    }
}
