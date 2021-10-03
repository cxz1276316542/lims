package com.ruoyi.stock.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * 样品出入库对象 ypjc_ypcrk
 *
 * @author ruoyi
 * @date 2021-07-21
 */
public class EntryAndOutVO extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 样品出入库ID */
    private Long entryAndOutID;

    /** 样品ID */
    @Excel(name = "样品ID")
    private Long sampleID;

    /** 样品编号 由各机构确定格式自动生成 */
    @Excel(name = "样品编号")
    private String sampleNumber;

    /** 样品学名 */
    @Excel(name = "样品学名")
    private String sampleScientificName;

    /** 样品库ID */
    @Excel(name = "样品库ID")
    private Long sampleBankID;

    /** 样品详细信息 */
    private Map<String,Object> detail;

    /** 出入库类别（1-入库，2-出库） */
    @Excel(name = "出入库类别", readConverterExp = "1-入库，2-出库")
    private Integer entryAndOutCategory;

    /** 出入库类型（1-登记入库 2-回收 3-领用 4-销毁） */
    @Excel(name = "出入库类型")
    private Integer entryAndOutType;

    /** 出\入样品数量 */
    @Excel(name = "出入样品数量")
    private BigDecimal sampleQuantity;

    /** 操作员ID */
    @Excel(name = "操作员ID")
    private Long operatorID;

    /** 送库、领用人ID */
    @Excel(name = "送库、领用人ID")
    private Long receiverID;

    /** 送库、领用人名称 */
    @Excel(name = "送库、领用人名称")
    private String receiverName;

    /** 操作员名称 */
    @Excel(name = "操作员名称")
    private String operatorName;

    /** 操作时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "操作时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date operationTime;

    /**
     * 出入库开始时间
     */
    private Date entryAndOutStartTime;
    /**
     * 出入库结束时间
     */
    private Date entryAndOutEndTime;

    /** 样品流向（出库操作需填写，销毁时为销毁说明） */
    @Excel(name = "样品流向")
    private String sampleFlow;

    /** 销毁方法（销毁时需填写） */
    @Excel(name = "销毁方法")
    private String destroyMethod;

    public Date getEntryAndOutStartTime() {
        return entryAndOutStartTime;
    }

    public void setEntryAndOutStartTime(Date entryAndOutStartTime) {
        this.entryAndOutStartTime = entryAndOutStartTime;
    }

    public Date getEntryAndOutEndTime() {
        return entryAndOutEndTime;
    }

    public void setEntryAndOutEndTime(Date entryAndOutEndTime) {
        this.entryAndOutEndTime = entryAndOutEndTime;
    }

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

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public Long getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(Long receiverID) {
        this.receiverID = receiverID;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public Map<String, Object> getDetail() {
        return detail;
    }

    public void setDetail(Map<String, Object> detail) {
        this.detail = detail;
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
