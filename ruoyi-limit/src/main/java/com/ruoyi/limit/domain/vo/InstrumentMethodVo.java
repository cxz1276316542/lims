package com.ruoyi.limit.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 检测方法-检测仪器关系对象 xlk_jcff_jcyq
 * 
 * @author dlk
 * @date 2021-08-07
 */
public class InstrumentMethodVo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 仪器方法主键ID */
    private Long instrumentDetectionMethodID;

    /** 检测方法ID */
    private Long detectionMethodID;

    /** 检测方法名称 */
    @Excel(name = "检测方法名称")
    private String detectionMethodName;

    /** 操作描述 */
    private String detectionMethodInfo;

    /** 检测方法地址 */
    private String detectionMethodURL;

    /** 采购员ID(用户表ID) */
    private Long purchaserID;

    /** 采购员名字 */
    private String purchaserName;

    /** 检测仪器ID */
    private Long instrumentID;

    /** 仪器编号 */
    @Excel(name = "仪器编号")
    private String instrumentNumber;

    /** 检测仪器名称 */
    @Excel(name = "检测仪器名称")
    private String instrumentName;

    /** 生产厂家 */
    private String manufacturer;

    /** 启用日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date launchDate;

    /** 采购日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date purchaseDate;

    /** 目前状态:0-未使用，1-正常，2-损坏 */
    private Integer status;

    /** 检测中心ID */
    @Excel(name = "检测中心ID")
    private Long detectionCenterID;

    /** 数据有效性标识 0-无效 1-有效 2-绑定 */
    @Excel(name = "数据有效性标识 0-无效 1-有效 2-绑定")
    private Integer isValid;

    public InstrumentMethodVo() {
    }

    public Long getInstrumentDetectionMethodID() {
        return instrumentDetectionMethodID;
    }

    public void setInstrumentDetectionMethodID(Long instrumentDetectionMethodID) {
        this.instrumentDetectionMethodID = instrumentDetectionMethodID;
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

    public String getDetectionMethodInfo() {
        return detectionMethodInfo;
    }

    public void setDetectionMethodInfo(String detectionMethodInfo) {
        this.detectionMethodInfo = detectionMethodInfo;
    }

    public String getDetectionMethodURL() {
        return detectionMethodURL;
    }

    public void setDetectionMethodURL(String detectionMethodURL) {
        this.detectionMethodURL = detectionMethodURL;
    }

    public Long getPurchaserID() {
        return purchaserID;
    }

    public void setPurchaserID(Long purchaserID) {
        this.purchaserID = purchaserID;
    }

    public String getPurchaserName() {
        return purchaserName;
    }

    public void setPurchaserName(String purchaserName) {
        this.purchaserName = purchaserName;
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

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Date getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(Date launchDate) {
        this.launchDate = launchDate;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
        return "InstrumentMethodVo{" +
                "instrumentDetectionMethodID=" + instrumentDetectionMethodID +
                ", detectionMethodID=" + detectionMethodID +
                ", detectionMethodName='" + detectionMethodName + '\'' +
                ", detectionMethodInfo='" + detectionMethodInfo + '\'' +
                ", detectionMethodURL='" + detectionMethodURL + '\'' +
                ", purchaserID=" + purchaserID +
                ", purchaserName='" + purchaserName + '\'' +
                ", instrumentID=" + instrumentID +
                ", instrumentNumber='" + instrumentNumber + '\'' +
                ", instrumentName='" + instrumentName + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", launchDate=" + launchDate +
                ", purchaseDate=" + purchaseDate +
                ", status=" + status +
                ", detectionCenterID=" + detectionCenterID +
                ", isValid=" + isValid +
                '}';
    }
}
