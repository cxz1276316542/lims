package com.ruoyi.detection.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author liukun
 * @date 2021/5/20
 */
public class ReportResultVO {
    private String sampleAcademicName;
    private String sampleNumber;
    private BigDecimal sampleCount;
    //原号
    private String oldSampleNumber;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date reachDate;
    private String sendSamplePeopleName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date detectionDate;
    private Integer detectionType;
    private String detectionUnit;
    private String detectionUnitAddress;
    private String productUnit;
    private String productAddress;
    private String standard;
    private List<String> detectionList;
    private Integer result;
    private Integer reviewResult;
    private List<ReportDetailInfoVO> reportDetailInfoVOList;


    public ReportResultVO() {
    }


    public ReportResultVO(String sampleAcademicName, String sampleNumber, BigDecimal sampleCount, Date reachDate, String sendSamplePeopleName, Date detectionDate, Integer detectionType, String detectionUnit, String detectionUnitAddress, String productUnit, String productAddress, String standard, List<String> detectionList, Integer result, Integer reviewResult, List<ReportDetailInfoVO> reportDetailInfoVOList) {
        this.sampleAcademicName = sampleAcademicName;
        this.sampleNumber = sampleNumber;
        this.sampleCount = sampleCount;
        this.reachDate = reachDate;
        this.sendSamplePeopleName = sendSamplePeopleName;
        this.detectionDate = detectionDate;
        this.detectionType = detectionType;
        this.detectionUnit = detectionUnit;
        this.detectionUnitAddress = detectionUnitAddress;
        this.productUnit = productUnit;
        this.productAddress = productAddress;
        this.standard = standard;
        this.detectionList = detectionList;
        this.result = result;
        this.reviewResult = reviewResult;
        this.reportDetailInfoVOList = reportDetailInfoVOList;
    }


    @Override
    public String toString() {
        return "ReportVO{" +
                "sampleAcademicName='" + sampleAcademicName + '\'' +
                ", sampleNumber='" + sampleNumber + '\'' +
                ", sampleCount='" + sampleCount + '\'' +
                ", oldSampleNumber='" + oldSampleNumber + '\'' +
                ", reachDate=" + reachDate +
                ", sendSamplePeopleName='" + sendSamplePeopleName + '\'' +
                ", detectionDate=" + detectionDate +
                ", detectionType='" + detectionType + '\'' +
                ", detectionUnit='" + detectionUnit + '\'' +
                ", detectionUnitAddress='" + detectionUnitAddress + '\'' +
                ", productUnit='" + productUnit + '\'' +
                ", productAddress='" + productAddress + '\'' +
                ", standard='" + standard + '\'' +
                ", detectionList=" + detectionList +
                ", result=" + result +
                ", reviewResult=" + reviewResult +
                ", reportDetailInfoVOList=" + reportDetailInfoVOList +
                '}';
    }

    public String getSampleAcademicName() {
        return sampleAcademicName;
    }

    public void setSampleAcademicName(String sampleAcademicName) {
        this.sampleAcademicName = sampleAcademicName;
    }

    public String getSampleNumber() {
        return sampleNumber;
    }

    public void setSampleNumber(String sampleNumber) {
        this.sampleNumber = sampleNumber;
    }

    public BigDecimal getSampleCount() {
        return sampleCount;
    }

    public void setSampleCount(BigDecimal sampleCount) {
        this.sampleCount = sampleCount;
    }

    public String getOldSampleNumber() {
        return oldSampleNumber;
    }

    public void setOldSampleNumber(String oldSampleNumber) {
        this.oldSampleNumber = oldSampleNumber;
    }

    public Date getReachDate() {
        return reachDate;
    }

    public void setReachDate(Date reachDate) {
        this.reachDate = reachDate;
    }

    public String getSendSamplePeopleName() {
        return sendSamplePeopleName;
    }

    public void setSendSamplePeopleName(String sendSamplePeopleName) {
        this.sendSamplePeopleName = sendSamplePeopleName;
    }

    public Date getDetectionDate() {
        return detectionDate;
    }

    public void setDetectionDate(Date detectionDate) {
        this.detectionDate = detectionDate;
    }

    public Integer getDetectionType() {
        return detectionType;
    }

    public void setDetectionType(Integer detectionType) {
        this.detectionType = detectionType;
    }

    public String getDetectionUnit() {
        return detectionUnit;
    }

    public void setDetectionUnit(String detectionUnit) {
        this.detectionUnit = detectionUnit;
    }

    public String getDetectionUnitAddress() {
        return detectionUnitAddress;
    }

    public void setDetectionUnitAddress(String detectionUnitAddress) {
        this.detectionUnitAddress = detectionUnitAddress;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    public String getProductAddress() {
        return productAddress;
    }

    public void setProductAddress(String productAddress) {
        this.productAddress = productAddress;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public List<String> getDetectionList() {
        return detectionList;
    }

    public void setDetectionList(List<String> detectionList) {
        this.detectionList = detectionList;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public List<ReportDetailInfoVO> getReportDetailInfoVOList() {
        return reportDetailInfoVOList;
    }

    public void setReportDetailInfoVOList(List<ReportDetailInfoVO> reportDetailInfoVOList) {
        this.reportDetailInfoVOList = reportDetailInfoVOList;
    }

    public Integer getReviewResult() {
        return reviewResult;
    }

    public void setReviewResult(Integer reviewResult) {
        this.reviewResult = reviewResult;
    }
}
