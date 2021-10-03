package com.ruoyi.stock.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zjccc
 * @date 2021/8/4
 * @Email:648629522@qq.com
 */
public class AbandonVO extends BaseEntity {
    /** 样品库ID */
    private Long sampleBankID;

    /** 样品ID */
    @Excel(name = "样品ID")
    private Long sampleID;

    /** 样品编号 由各机构确定格式自动生成 */
    @Excel(name = "样品编号")
    private String sampleNumber;

    /** 样品学名 */
    @Excel(name = "样品学名")
    private String sampleScientificName;

    /** 样品计量单位 */
    @Excel(name = "样品计量单位")
    private String measureUnit;

    /** 销毁样品数量 */
    @Excel(name = "出入样品数量")
    private BigDecimal sampleQuantity;

    /** 保质截止时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "保质截止时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date qualityGuaranteePeriod;

    /** 操作员ID */
    @Excel(name = "操作员ID")
    private Long operatorID;

    /** 操作员名称 */
    @Excel(name = "操作员名称")
    private String operatorName;

    /** 操作时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "操作时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date operationTime;

    private Date operationStartTime;

    private Date operationEndTime;

    /** 销毁说明（出库操作需填写，销毁时为销毁说明） */
    @Excel(name = "销毁说明")
    private String sampleFlow;

    /** 销毁方法（销毁时需填写） */
    @Excel(name = "销毁方法")
    private String destroyMethod;

    public Date getOperationStartTime() {
        return operationStartTime;
    }

    public void setOperationStartTime(Date operationStartTime) {
        this.operationStartTime = operationStartTime;
    }

    public Date getOperationEndTime() {
        return operationEndTime;
    }

    public void setOperationEndTime(Date operationEndTime) {
        this.operationEndTime = operationEndTime;
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

    public String getSampleScientificName() {
        return sampleScientificName;
    }

    public void setSampleScientificName(String sampleScientificName) {
        this.sampleScientificName = sampleScientificName;
    }

    public String getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(String measureUnit) {
        this.measureUnit = measureUnit;
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

    public Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }

    public String getSampleFlow() {
        return sampleFlow;
    }

    public void setSampleFlow(String sampleFlow) {
        this.sampleFlow = sampleFlow;
    }

    public String getDestroyMethod() {
        return destroyMethod;
    }

    public void setDestroyMethod(String destroyMethod) {
        this.destroyMethod = destroyMethod;
    }

    public Long getSampleBankID() {
        return sampleBankID;
    }

    public void setSampleBankID(Long sampleBankID) {
        this.sampleBankID = sampleBankID;
    }

    public BigDecimal getSampleQuantity() {
        return sampleQuantity;
    }

    public void setSampleQuantity(BigDecimal sampleQuantity) {
        this.sampleQuantity = sampleQuantity;
    }

    public Date getQualityGuaranteePeriod() {
        return qualityGuaranteePeriod;
    }

    public void setQualityGuaranteePeriod(Date qualityGuaranteePeriod) {
        this.qualityGuaranteePeriod = qualityGuaranteePeriod;
    }
}
