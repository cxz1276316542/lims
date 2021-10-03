package com.ruoyi.limit.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 检测仪器-检测方法管理对象 xlk_jcff_jcyq
 * 
 * @author dlk
 * @date 2021-07-20
 */
public class InstrumentDetectionMethod extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 仪器方法主键ID */
    private Long instrumentDetectionMethodID;

    /** 检测仪器ID */
    private Long instrumentID;

    /** 仪器编号 */
    @Excel(name = "仪器编号")
    private String instrumentNumber;

    /** 检测仪器名称 */
    @Excel(name = "检测仪器名称")
    private String instrumentName;

    /** 检测方法ID */
    private Long detectionMethodID;

    /** 检测方法名称 */
    @Excel(name = "检测方法名称")
    private String detectionMethodName;

    /** 检测中心ID */
    @Excel(name = "检测中心ID")
    private Long detectionCenterID;

    /** 数据有效性标识 0-无效 1-有效 2-绑定 */
    @Excel(name = "数据有效性标识 0-无效 1-有效 2-绑定")
    private Integer isValid;

    public InstrumentDetectionMethod() {
    }

    public Long getInstrumentDetectionMethodID() {
        return instrumentDetectionMethodID;
    }

    public void setInstrumentDetectionMethodID(Long instrumentDetectionMethodID) {
        this.instrumentDetectionMethodID = instrumentDetectionMethodID;
    }

    public Long getInstrumentID() {
        return instrumentID;
    }

    public void setInstrumentID(Long instrumentID) {
        this.instrumentID = instrumentID;
    }

    public String getInstrumentNumber() {
        return instrumentNumber;
    }

    public void setInstrumentNumber(String instrumentNumber) {
        this.instrumentNumber = instrumentNumber;
    }

    public String getInstrumentName() {
        return instrumentName;
    }

    public void setInstrumentName(String instrumentName) {
        this.instrumentName = instrumentName;
    }

    public Long getDetectionMethodID() {
        return detectionMethodID;
    }

    public void setDetectionMethodID(Long detectionMethodID) {
        this.detectionMethodID = detectionMethodID;
    }

    public String getDetectionMethodName() {
        return detectionMethodName;
    }

    public void setDetectionMethodName(String detectionMethodName) {
        this.detectionMethodName = detectionMethodName;
    }

    public Long getDetectionCenterID() {
        return detectionCenterID;
    }

    public void setDetectionCenterID(Long detectionCenterID) {
        this.detectionCenterID = detectionCenterID;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    @Override
    public String toString() {
        return "InstrumentDetectionMethod{" +
                "instrumentDetectionMethodID=" + instrumentDetectionMethodID +
                ", instrumentID=" + instrumentID +
                ", instrumentNumber='" + instrumentNumber + '\'' +
                ", instrumentName='" + instrumentName + '\'' +
                ", detectionMethodID=" + detectionMethodID +
                ", detectionMethodName='" + detectionMethodName + '\'' +
                ", detectionCenterID=" + detectionCenterID +
                ", isValid=" + isValid +
                '}';
    }
}
