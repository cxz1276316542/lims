package com.ruoyi.detection.domain.vo;

import com.ruoyi.common.annotation.Excel;

import java.io.Serializable;
import java.util.Date;

/**
 * @author liukun
 * @date 2021/7/24
 */
public class PrepareAssignSampleVO implements Serializable {
    /**
     * 样品ID
     */
    @Excel(name = "样品ID")
    private Integer sampleID;
    /**
     * 样品编号
     */
    @Excel(name = "样品编号")
    private String sampleNumber;
    /**
     * 采样方式
     */
    @Excel(name = "采样方式")
    private Integer samplingMode;
    /**
     * 采样时间
     */
    @Excel(name = "采样时间")
    private Date samplingTime;
    /**
     * 样品名称
     */
    @Excel(name = "样品名称")
    private String sampleName;
    /**
     * 样品学名
     */
    @Excel(name = "样品学名")
    private String sampleAcademicName;
    /**
     * 样品状态
     */
    @Excel(name = "样品状态")
    private String sampleStatus;

    public Integer getSampleID() {
        return sampleID;
    }

    public void setSampleID(Integer sampleID) {
        this.sampleID = sampleID;
    }

    public String getSampleNumber() {
        return sampleNumber;
    }

    public void setSampleNumber(String sampleNumber) {
        this.sampleNumber = sampleNumber;
    }

    public Integer getSamplingMode() {
        return samplingMode;
    }

    public void setSamplingMode(Integer samplingMode) {
        this.samplingMode = samplingMode;
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

    public String getSampleAcademicName() {
        return sampleAcademicName;
    }

    public void setSampleAcademicName(String sampleAcademicName) {
        this.sampleAcademicName = sampleAcademicName;
    }

    public String getSampleStatus() {
        return sampleStatus;
    }

    public void setSampleStatus(String sampleStatus) {
        this.sampleStatus = sampleStatus;
    }

    public PrepareAssignSampleVO(Integer sampleID) {
        this.sampleID = sampleID;
    }

    public PrepareAssignSampleVO(Integer sampleID, String sampleNumber, Integer samplingMode, Date samplingTime, String sampleName, String sampleAcademicName, String sampleStatus) {
        this.sampleID = sampleID;
        this.sampleNumber = sampleNumber;
        this.samplingMode = samplingMode;
        this.samplingTime = samplingTime;
        this.sampleName = sampleName;
        this.sampleAcademicName = sampleAcademicName;
        this.sampleStatus = sampleStatus;
    }

    @Override
    public String toString() {
        return "PrepareAssignSampleVO{" +
                "sampleID=" + sampleID +
                ", sampleNumber='" + sampleNumber + '\'' +
                ", samplingMode=" + samplingMode +
                ", samplingTime=" + samplingTime +
                ", sampleName='" + sampleName + '\'' +
                ", sampleAcademicName='" + sampleAcademicName + '\'' +
                ", sampleStatus='" + sampleStatus + '\'' +
                '}';
    }
}
