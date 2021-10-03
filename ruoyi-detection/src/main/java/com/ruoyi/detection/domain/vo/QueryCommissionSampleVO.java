package com.ruoyi.detection.domain.vo;

import java.io.Serializable;

/**
 * @author liukun
 * @date 2021/8/3
 */
// 查询委托样品VO
public class QueryCommissionSampleVO implements Serializable {

    private static final long serialVersionUID = 6952055987255414575L;
    // 客户账号
    private String customerNumber;
    // 样品编号
    private String sampleNumber;
    // 优先级
    private Integer priority;
    // 采样方式
    private Integer sampleMethod;
    // 检测类型
    private Integer detectionType;
    // 检测标准
    private Integer detectionStandardID;
    // 送样人电话
    private String sendSamplePersonPhone;
    // 送样人名称
    private String sendSamplePersonName;
    // 采样开始时间
    private String samplingStartTime;
    // 采样结束时间
    private String samplingEndTime;
    // 样品名称
    private String sampleName;
    // 样品学名
    private String sampleAcademicName;
    // 生产单位
    private String productionUnit;
    // 查询来源
    private Integer searchSource;
    // 操作ID
    private Long operatorID;

    @Override
    public String toString() {
        return "QueryCommissionSampleVO{" +
                "customerNumber='" + customerNumber + '\'' +
                ", sampleNumber='" + sampleNumber + '\'' +
                ", priority=" + priority +
                ", sampleMethod=" + sampleMethod +
                ", detectionType=" + detectionType +
                ", detectionStandardID=" + detectionStandardID +
                ", sendSamplePersonPhone='" + sendSamplePersonPhone + '\'' +
                ", sendSamplePersonName='" + sendSamplePersonName + '\'' +
                ", samplingStartTime='" + samplingStartTime + '\'' +
                ", samplingEndTime='" + samplingEndTime + '\'' +
                ", sampleName='" + sampleName + '\'' +
                ", sampleAcademicName='" + sampleAcademicName + '\'' +
                ", productionUnit='" + productionUnit + '\'' +
                ", searchSource=" + searchSource +
                ", operatorID=" + operatorID +
                '}';
    }

    public Long getOperatorID() {
        return operatorID;
    }

    public void setOperatorID(Long operatorID) {
        this.operatorID = operatorID;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
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

    public Integer getSampleMethod() {
        return sampleMethod;
    }

    public void setSampleMethod(Integer sampleMethod) {
        this.sampleMethod = sampleMethod;
    }

    public Integer getDetectionType() {
        return detectionType;
    }

    public void setDetectionType(Integer detectionType) {
        this.detectionType = detectionType;
    }

    public Integer getDetectionStandardID() {
        return detectionStandardID;
    }

    public void setDetectionStandardID(Integer detectionStandardID) {
        this.detectionStandardID = detectionStandardID;
    }

    public String getSendSamplePersonPhone() {
        return sendSamplePersonPhone;
    }

    public void setSendSamplePersonPhone(String sendSamplePersonPhone) {
        this.sendSamplePersonPhone = sendSamplePersonPhone;
    }

    public String getSendSamplePersonName() {
        return sendSamplePersonName;
    }

    public void setSendSamplePersonName(String sendSamplePersonName) {
        this.sendSamplePersonName = sendSamplePersonName;
    }

    public String getSamplingStartTime() {
        return samplingStartTime;
    }

    public void setSamplingStartTime(String samplingStartTime) {
        this.samplingStartTime = samplingStartTime;
    }

    public String getSamplingEndTime() {
        return samplingEndTime;
    }

    public void setSamplingEndTime(String samplingEndTime) {
        this.samplingEndTime = samplingEndTime;
    }

    public String getSampleName() {
        return sampleName;
    }

    public void setSampleName(String sampleName) {
        this.sampleName = sampleName;
    }

    public String getSampleAcademicName() {
        return sampleAcademicName;
    }

    public void setSampleAcademicName(String sampleAcademicName) {
        this.sampleAcademicName = sampleAcademicName;
    }

    public String getProductionUnit() {
        return productionUnit;
    }

    public void setProductionUnit(String productionUnit) {
        this.productionUnit = productionUnit;
    }

    public Integer getSearchSource() {
        return searchSource;
    }

    public void setSearchSource(Integer searchSource) {
        this.searchSource = searchSource;
    }
}
