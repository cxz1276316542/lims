package com.ruoyi.commission.domain.dto;

import com.ruoyi.detection.domain.vo.IDAndNameVO;

import java.math.BigDecimal;
import java.util.*;

public class EntrustList {

    private int priority;
    private int detectionCategory;
    private int detectionStandardID;
    private int reportReceiveWay;
    private String productionUnit;
    private String productionUnitAddress;
    private Date samplingTime;
    private String sampleName;
    private String sampleScientificName;
    private int sampleStatus;
    private BigDecimal sampleQuantity;
    private int measureUnit;
    private String samplingBase;
    private String sampleDescription;
    private int recycle;
    private String sampleImage;
    private String detectionDescription;
//    private List<IDAndNameVO> sampleDescriptionArray;

//    public List<IDAndNameVO> getSampleDescriptionArray() {
//        return sampleDescriptionArray;
//    }
//
//    public void setSampleDescriptionArray(List<IDAndNameVO> sampleDescriptionArray) {
//        this.sampleDescriptionArray = sampleDescriptionArray;
//    }

    public String getDetectionDescription() {
        return detectionDescription;
    }

    public void setDetectionDescription(String detectionDescription) {
        this.detectionDescription = detectionDescription;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getDetectionCategory() {
        return detectionCategory;
    }

    public void setDetectionCategory(int detectionCategory) {
        this.detectionCategory = detectionCategory;
    }

    public int getDetectionStandardID() {
        return detectionStandardID;
    }

    public void setDetectionStandardID(int detectionStandardID) {
        this.detectionStandardID = detectionStandardID;
    }

    public int getReportReceiveWay() {
        return reportReceiveWay;
    }

    public void setReportReceiveWay(int reportReceiveWay) {
        this.reportReceiveWay = reportReceiveWay;
    }

    public String getProductionUnit() {
        return productionUnit;
    }

    public void setProductionUnit(String productionUnit) {
        this.productionUnit = productionUnit;
    }

    public String getProductionUnitAddress() {
        return productionUnitAddress;
    }

    public void setProductionUnitAddress(String productionUnitAddress) {
        this.productionUnitAddress = productionUnitAddress;
    }

    public Date getSamplingTime() {
        return samplingTime;
    }

    public void setSamplingTime(Date samplingTime) {
        this.samplingTime = samplingTime;
    }

    public String getSampleName() {
        return sampleName;
    }

    public void setSampleName(String sampleName) {
        this.sampleName = sampleName;
    }

    public String getSampleScientificName() {
        return sampleScientificName;
    }

    public void setSampleScientificName(String sampleScientificName) {
        this.sampleScientificName = sampleScientificName;
    }

    public int getSampleStatus() {
        return sampleStatus;
    }

    public void setSampleStatus(int sampleStatus) {
        this.sampleStatus = sampleStatus;
    }

    public BigDecimal getSampleQuantity() {
        return sampleQuantity;
    }

    public void setSampleQuantity(BigDecimal sampleQuantity) {
        this.sampleQuantity = sampleQuantity;
    }

    public int getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(int measureUnit) {
        this.measureUnit = measureUnit;
    }

    public String getSamplingBase() {
        return samplingBase;
    }

    public void setSamplingBase(String samplingBase) {
        this.samplingBase = samplingBase;
    }

    public String getSampleDescription() {
        return sampleDescription;
    }

    public void setSampleDescription(String sampleDescription) {
        this.sampleDescription = sampleDescription;
    }

    public int getRecycle() {
        return recycle;
    }

    public void setRecycle(int recycle) {
        this.recycle = recycle;
    }

    public String getSampleImage() {
        return sampleImage;
    }

    public void setSampleImage(String sampleImage) {
        this.sampleImage = sampleImage;
    }
}
