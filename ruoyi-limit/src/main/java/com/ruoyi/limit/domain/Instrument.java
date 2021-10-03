package com.ruoyi.limit.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 检测仪器管理对象 xlk_jcyq
 *
 * @author lkun
 * @date 2021-07-20
 */
public class Instrument extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 检测仪器ID */
    private Long instrumentID;

    /** 采购员ID(用户表ID) */
    private Long purchaserID;

    /** 采购员名字 */
    @Excel(name = "采购员名字",defaultValue = "业务员1")
    private String purchaserName;

    /** 仪器编号 */
    @Excel(name = "仪器编号",type= Excel.Type.EXPORT)
    private String instrumentNumber;

    /** 检测仪器名称 */
    @Excel(name = "检测仪器名称")
    private String instrumentName;

    /** 生产厂家 */
    @Excel(name = "生产厂家")
    private String manufacturer;

    /** 启用日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "启用日期", type = Excel.Type.EXPORT ,width = 30, dateFormat = "yyyy-MM-dd")
    private Date launchDate;

    /** 采购日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "采购日期", width = 30,type = Excel.Type.EXPORT, dateFormat = "yyyy-MM-dd")
    private Date purchaseDate;

    /** 目前状态:0-未使用，1-正常，2-损坏 */
    @Excel(name = "目前状态", prompt="0-未使用，1-正常，2-损坏")
    private Integer status;

    /** 检测中心ID */
//    @Excel(name = "检测中心ID")
    private Long detectionCenterID;

    /** 数据有效性标识 0-无效 1-有效*/
    @Excel(name = "数据有效性标识", prompt="0-无效 1-有效")
    private Integer isValid;

    public Instrument() {
    }

    public void setInstrumentID(Long instrumentID)
    {
        this.instrumentID = instrumentID;
    }

    public Long getInstrumentID()
    {
        return instrumentID;
    }
    public void setPurchaserID(Long purchaserID)
    {
        this.purchaserID = purchaserID;
    }

    public Long getPurchaserID()
    {
        return purchaserID;
    }

    public String getPurchaserName() {
        return purchaserName;
    }

    public void setPurchaserName(String purchaserName) {
        this.purchaserName = purchaserName;
    }

    public void setInstrumentName(String instrumentName)
    {
        this.instrumentName = instrumentName;
    }

    public void setInstrumentNumber(String instrumentNumber) {
        this.instrumentNumber = instrumentNumber;
    }
    public String getInstrumentNumber() {
        return instrumentNumber;
    }

    public String getInstrumentName()
    {
        return instrumentName;
    }
    public void setManufacturer(String manufacturer)
    {
        this.manufacturer = manufacturer;
    }

    public String getManufacturer()
    {
        return manufacturer;
    }
    public void setLaunchDate(Date launchDate)
    {
        this.launchDate = launchDate;
    }

    public Date getLaunchDate()
    {
        return launchDate;
    }
    public void setPurchaseDate(Date purchaseDate)
    {
        this.purchaseDate = purchaseDate;
    }

    public Date getPurchaseDate()
    {
        return purchaseDate;
    }
    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getStatus()
    {
        return status;
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
        return "Instrument{" +
                "instrumentID=" + instrumentID +
                ", purchaserID=" + purchaserID +
                ", purchaserName='" + purchaserName + '\'' +
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
