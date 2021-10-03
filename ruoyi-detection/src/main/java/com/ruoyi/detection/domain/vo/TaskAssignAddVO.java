package com.ruoyi.detection.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author liukun
 * @date 2021/8/5
 */
public class TaskAssignAddVO implements Serializable {

    private static final long serialVersionUID = 4852959669657152628L;
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
     * 检品描述
     */
    private String sampleDescription;
    /**
     * 默认检测项目,检测方法,检测员
     */
    private List<ItemAndMethodAndInspectorVO> itemAndMethodAndInspectorVOList;

    /**
     * 所有的选项
     */
    private List<DetectionItemVO> detectionItemList;
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
     * 检测标准名称
     */
    private Long detectionStandardID;
    /**
     * 检品登记时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date sampleRegisterTime;
    /**
     * 备注
     */
    private String remark;

    @Override
    public String toString() {
        return "TaskAssignAddVO{" +
                "sampleID=" + sampleID +
                ", sampleNumber='" + sampleNumber + '\'' +
                ", sampleAcademicName='" + sampleAcademicName + '\'' +
                ", sampleDescription='" + sampleDescription + '\'' +
                ", itemAndMethodAndInspectorVOList=" + itemAndMethodAndInspectorVOList +
                ", detectionItemVOList=" + detectionItemList +
                ", priority=" + priority +
                ", sampleStatus=" + sampleStatus +
                ", detectionStandardName='" + detectionStandardName + '\'' +
                ", detectionStandardID=" + detectionStandardID +
                ", sampleRegisterTime=" + sampleRegisterTime +
                ", remark='" + remark + '\'' +
                '}';
    }

    public List<DetectionItemVO> getDetectionItemList() {
        return detectionItemList;
    }

    public void setDetectionItemList(List<DetectionItemVO> detectionItemList) {
        this.detectionItemList = detectionItemList;
    }

    public List<ItemAndMethodAndInspectorVO> getItemAndMethodAndInspectorVOList() {
        return itemAndMethodAndInspectorVOList;
    }

    public void setItemAndMethodAndInspectorVOList(List<ItemAndMethodAndInspectorVO> itemAndMethodAndInspectorVOList) {
        this.itemAndMethodAndInspectorVOList = itemAndMethodAndInspectorVOList;
    }

    public String getSampleDescription() {
        return sampleDescription;
    }

    public void setSampleDescription(String sampleDescription) {
        this.sampleDescription = sampleDescription;
    }

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

    public Date getSampleRegisterTime() {
        return sampleRegisterTime;
    }

    public void setSampleRegisterTime(Date sampleRegisterTime) {
        this.sampleRegisterTime = sampleRegisterTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
