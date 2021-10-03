package com.ruoyi.stock.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * @author zjccc
 * @date 2021/7/24
 * @Email:648629522@qq.com
 */
public class SampleBankVO implements Serializable {
    /** 样品库ID */
    private Long sampleBankID;

    /** 样品ID */
    private Long sampleID;

    /** 样品编号 */
    @Excel(name = "样品编号")
    private String sampleNumber;

    /** 样品库地点 */
    @Excel(name = "样品库地点")
    private String sampleAddress;

    /** 样品位置 */
    @Excel(name = "样品位置")
    private String sampleLocation;

    /** 样品剩余数量 */
    @Excel(name = "样品剩余数量")
    private BigDecimal remainQuantity;

    /** 样品总数量 */
    @Excel(name = "样品总数量")
    private BigDecimal totalQuantity;

//    /** 样品计量单位ID */
//    private Long measureUnitID;

    /** 样品计量单位 */
    @Excel(name = "样品计量单位")
    private String measureUnit;

    /** 状态预警 0-正常 1-即将到期 2-已过期*/
    private int warning;

    /** 样品学名 */
    @Excel(name = "样品学名")
    private String sampleScientificName;

    /** 进库时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "进库时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date storageTime;

    /** 保质截止时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "保质截止时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date qualityGuaranteePeriod;

    /** 样品详细信息 */
    private Map<String,Object> detail;

    /** 操作员ID */
    private Long operatorID;

    /** 操作员名称 */
    @Excel(name = "接收人名称")
    private String operatorName;

    /** 送样人ID */
    private Long deliverID;

    /** 送样人名称 */
    private String deliverName;

    /** 状态 1-有效 2-无效 */
    @Excel(name = "状态")
    private Integer status;

    /**
     * 进库开始时间
     */
    private Date storageStartTime;
    /**
     * 进库结束时间
     */
    private Date storageEndTime;

    public Date getStorageStartTime() {
        return storageStartTime;
    }

    public void setStorageStartTime(Date storageStartTime) {
        this.storageStartTime = storageStartTime;
    }

    public Date getStorageEndTime() {
        return storageEndTime;
    }

    public void setStorageEndTime(Date storageEndTime) {
        this.storageEndTime = storageEndTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDeliverName() {
        return deliverName;
    }

    public void setDeliverName(String deliverName) {
        this.deliverName = deliverName;
    }

    public Long getDeliverID() {
        return deliverID;
    }

    public void setDeliverID(Long deliverID) {
        this.deliverID = deliverID;
    }

    public void setSampleBankID(Long sampleBankID)
    {
        this.sampleBankID = sampleBankID;
    }

    public Long getSampleBankID()
    {
        return sampleBankID;
    }

    public String getSampleNumber() {
        return sampleNumber;
    }


    public String getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(String measureUnit) {
        this.measureUnit = measureUnit;
    }

    public int getWarning() {
        return warning;
    }

    public void setWarning(int warning) {
        this.warning = warning;
    }


    public void setSampleNumber(String sampleNumber) {
        this.sampleNumber = sampleNumber;
    }



    public void setSampleAddress(String sampleAddress)
    {
        this.sampleAddress = sampleAddress;
    }

    public String getSampleAddress()
    {
        return sampleAddress;
    }
    public void setSampleLocation(String sampleLocation)
    {
        this.sampleLocation = sampleLocation;
    }

    public String getSampleLocation()
    {
        return sampleLocation;
    }
    public void setRemainQuantity(BigDecimal remainQuantity)
    {
        this.remainQuantity = remainQuantity;
    }

    public BigDecimal getRemainQuantity()
    {
        return remainQuantity;
    }
    public void setTotalQuantity(BigDecimal totalQuantity)
    {
        this.totalQuantity = totalQuantity;
    }

    public BigDecimal getTotalQuantity()
    {
        return totalQuantity;
    }
//    public void setMeasureUnitID(Long measureUnitID)
//    {
//        this.measureUnitID = measureUnitID;
//    }
//
//    public Long getMeasureUnitID()
//    {
//        return measureUnitID;
//    }
    public void setStorageTime(Date storageTime)
    {
        this.storageTime = storageTime;
    }

    public Date getStorageTime()
    {
        return storageTime;
    }
    public void setQualityGuaranteePeriod(Date qualityGuaranteePeriod)
    {
        this.qualityGuaranteePeriod = qualityGuaranteePeriod;
    }

    public Long getSampleID() {
        return sampleID;
    }

    public void setSampleID(Long sampleID) {
        this.sampleID = sampleID;
    }

    public Long getOperatorID() {
        return operatorID;
    }

    public void setOperatorID(Long operatorID) {
        this.operatorID = operatorID;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public Date getQualityGuaranteePeriod()
    {
        return qualityGuaranteePeriod;
    }

    public String getSampleScientificName() {
        return sampleScientificName;
    }

    public void setSampleScientificName(String sampleScientificName) {
        this.sampleScientificName = sampleScientificName;
    }

    public Map<String, Object> getDetail() {
        return detail;
    }

    public void setDetail(Map<String, Object> detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "SampleBankVO{" +
                "sampleBankID=" + sampleBankID +
                ", sampleNumber=" + sampleNumber +
                ", sampleAddress='" + sampleAddress + '\'' +
                ", sampleLocation='" + sampleLocation + '\'' +
                ", remainQuantity=" + remainQuantity +
                ", totalQuantity=" + totalQuantity +
                ", storageTime=" + storageTime +
                ", qualityGuaranteePeriod=" + qualityGuaranteePeriod +
                '}';
    }
}
