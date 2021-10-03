package com.ruoyi.stock.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 样品库对象 ypjc_ypk
 *
 * @author ruoyi
 * @date 2021-07-21
 */
public class SampleBank extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 样品库ID */
    private Long sampleBankID;

    /** 样品ID */
    @Excel(name = "样品ID")
    private Long sampleID;

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

    /** 样品计量单位 */
    @Excel(name = "样品计量单位")
    private String measureUnit;

    /** 进库时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "进库时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date storageTime;

    /** 保质截止时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "保质截止时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date qualityGuaranteePeriod;

    /** 操作员ID */
    @Excel(name = "接受人ID")
    private Long operatorID;

    /** 状态 1-有效 2-无效 */
    @Excel(name = "状态")
    private Integer status;

    public void setSampleBankID(Long sampleBankID)
    {
        this.sampleBankID = sampleBankID;
    }

    public Long getSampleBankID()
    {
        return sampleBankID;
    }
    public void setSampleID(Long sampleID)
    {
        this.sampleID = sampleID;
    }

    public Long getSampleID()
    {
        return sampleID;
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

    public String getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(String measureUnit) {
        this.measureUnit = measureUnit;
    }

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

    public Date getQualityGuaranteePeriod()
    {
        return qualityGuaranteePeriod;
    }

    public Long getOperatorID() {
        return operatorID;
    }

    public void setOperatorID(Long operatorID) {
        this.operatorID = operatorID;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getStatus()
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("sampleBankID", getSampleBankID())
            .append("sampleID", getSampleID())
            .append("sampleAddress", getSampleAddress())
            .append("sampleLocation", getSampleLocation())
            .append("remainQuantity", getRemainQuantity())
            .append("totalQuantity", getTotalQuantity())
            .append("storageTime", getStorageTime())
            .append("qualityGuaranteePeriod", getQualityGuaranteePeriod())
            .append("status", getStatus())
            .toString();
    }
}
