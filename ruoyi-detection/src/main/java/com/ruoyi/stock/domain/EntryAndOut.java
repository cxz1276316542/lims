package com.ruoyi.stock.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 样品出入库对象 ypjc_ypcrk
 *
 * @author ruoyi
 * @date 2021-07-21
 */
public class EntryAndOut extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 样品出入库ID */
    private Long entryAndOutID;

    /** 样品ID */
    @Excel(name = "样品ID")
    private Long sampleID;

    /** 样品库ID */
    @Excel(name = "样品库ID")
    private Long sampleBankID;

    /** 出入库类别（1-入库，2-出库） */
    @Excel(name = "出入库类别", readConverterExp = "1=-入库，2-出库")
    private Integer entryAndOutCategory;

    /** 出入库类型（1-登记入库 2-回收 3-领用 4-销毁） */
    @Excel(name = "出入库类型")
    private Integer entryAndOutType;

    /** 出\入样品数量 */
    @Excel(name = "出入样品数量")
    private BigDecimal sampleQuantity;

    /** 送库人/领用人ID */
    private Long receiverID;

    /** 操作员ID */
    @Excel(name = "操作员ID")
    private Long operatorID;

    /** 操作时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "操作时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date operationTime;

    /** 样品流向（出库操作需填写，销毁时为销毁说明） */
    @Excel(name = "样品流向")
    private String sampleFlow;

    /** 销毁方法（销毁时需填写） */
    @Excel(name = "销毁方法")
    private String destroyMethod;

    public void setEntryAndOutID(Long entryAndOutID)
    {
        this.entryAndOutID = entryAndOutID;
    }

    public Long getEntryAndOutID()
    {
        return entryAndOutID;
    }
    public void setSampleID(Long sampleID)
    {
        this.sampleID = sampleID;
    }

    public Long getSampleID()
    {
        return sampleID;
    }
    public void setSampleBankID(Long sampleBankID)
    {
        this.sampleBankID = sampleBankID;
    }

    public Long getSampleBankID()
    {
        return sampleBankID;
    }
    public void setEntryAndOutCategory(Integer entryAndOutCategory)
    {
        this.entryAndOutCategory = entryAndOutCategory;
    }

    public Integer getEntryAndOutCategory()
    {
        return entryAndOutCategory;
    }
    public void setSampleQuantity(BigDecimal sampleQuantity)
    {
        this.sampleQuantity = sampleQuantity;
    }

    public BigDecimal getSampleQuantity()
    {
        return sampleQuantity;
    }
    public void setOperatorID(Long operatorID)
    {
        this.operatorID = operatorID;
    }

    public Long getOperatorID()
    {
        return operatorID;
    }
    public void setOperationTime(Date operationTime)
    {
        this.operationTime = operationTime;
    }

    public Date getOperationTime()
    {
        return operationTime;
    }

    public Integer getEntryAndOutType() {
        return entryAndOutType;
    }

    public void setEntryAndOutType(Integer entryAndOutType) {
        this.entryAndOutType = entryAndOutType;
    }

    public Long getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(Long receiverID) {
        this.receiverID = receiverID;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("entryAndOutID", getEntryAndOutID())
            .append("sampleID", getSampleID())
            .append("sampleBankID", getSampleBankID())
            .append("entryAndOutCategory", getEntryAndOutCategory())
            .append("sampleQuantity", getSampleQuantity())
            .append("operatorID", getOperatorID())
            .append("operationTime", getOperationTime())
            .toString();
    }
}
