package com.ruoyi.commission.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.commission.domain.dto.CommissionDTO;
import com.ruoyi.commission.domain.dto.EntrustList;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 委托样品对象 ypjc_wtyp
 *
 * @author lkun
 * @date 2021-07-23
 */
public class CommissionManage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 委托样品ID */
    private Long sampleID;

    /** 客户ID 外部送检、抽检单位或个人 */
    @Excel(name = "客户ID 外部送检、抽检单位或个人")
    private Long customerID;

    /** 单位ID 由单位和客户可确定邮寄地址 */
    @Excel(name = "单位ID 由单位和客户可确定邮寄地址")
    private Long companyID;

    /** 样品编号 由各机构确定格式自动生成 */
    @Excel(name = "样品编号 由各机构确定格式自动生成")
    private String sampleNumber;

    /** 委托单编号 由各机构确定格式自动生成 */
    @Excel(name = "样品编号 由各机构确定格式自动生成")
    private String commissionNumber;

    /** 采样方式 1-正常；2-急；3-加急；4-特急（客户、业务员操作）默认正常 */
    @Excel(name = "采样方式 1-正常；2-急；3-加急；4-特急", readConverterExp = "客=户、业务员操作")
    private Long priority;

    /** （1-抽检、2-送检）默认送检 */
    @Excel(name = "", readConverterExp = "1=-抽检、2-送检")
    private Long samplingMode;

    /** 检测类别 （1-委托、2-仲裁、3-其他）默认委托 */
    @Excel(name = "检测类别 ", readConverterExp = "1=-委托、2-仲裁、3-其他")
    private Long detectionCategory;

    /** 检测标准ID 默认国家标准（若采用非标方法，需要检测人员判断） */
    @Excel(name = "检测标准ID 默认国家标准", readConverterExp = "若=采用非标方法，需要检测人员判断")
    private Long detectionStandardID;

    /** 报告领取方式 0-邮寄，1-自取 */
    @Excel(name = "报告领取方式 0-邮寄，1-自取")
    private Long reportReceiveWay;

    /** 收货地址 */
    @Excel(name = "收货地址")
    private String receiptAddress;

    /** 送样人电话 客户填写或业务员代写 */
    @Excel(name = "送样人电话 客户填写或业务员代写")
    private String sampleSenderPhone;

    /** 送样人名称 */
    @Excel(name = "送样人名称")
    private String sampleSenderName;

    /** 采样时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "采样时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date samplingTime;

    /** 样品名称 */
    @Excel(name = "样品名称")
    private String sampleName;

    /** 样品学名 */
    @Excel(name = "样品学名")
    private String sampleScientificName;

    /** 1-固体；2-液体；3-胶体；4-气体；5-其他 */
    @Excel(name = "1-固体；2-液体；3-胶体；4-气体；5-其他")
    private Long sampleStatus;

    /** 样品数量 */
    @Excel(name = "样品数量")
    private BigDecimal sampleQuantity;

    /** 样品计量单位ID */
    @Excel(name = "样品计量单位ID")
    private Long measureUnit;

    /** 抽样基数 */
    @Excel(name = "抽样基数")
    private String samplingBase;

    /** 样品生产单位 */
    @Excel(name = "样品生产单位")
    private String productionUnit;

    /** 生产单位地址 */
    @Excel(name = "生产单位地址")
    private String productionUnitAddress;

    /** 样品描述 */
    @Excel(name = "样品描述")
    private String sampleDescription;

    /** 是否回收剩余样品 0-否 1-是 */
    @Excel(name = "是否回收剩余样品 0-否 1-是")
    private Long recycle;

    /** 样品图像地址 */
    @Excel(name = "样品图像地址")
    private String sampleImage;

    /** 二维码地址 */
    @Excel(name = "二维码地址")
    private String QRcodeAddress;

    /** 电子签名地址 */
    @Excel(name = "电子签名地址")
    private String electronicSignature;

    /** 审核状态委托保存（01）默认
     委托修改（02）
     委托退出（00）
     业务保存（11）默认
     业务提交（12）
     退回修改（10）
     受理审核通过（21）
     受理审核不通过（20）
     */
    @Excel(name = "审核状态 0-委托待审核1-审核通过，2-审核不通过，3-委托单保存，4-样品登记保存，5登记成功,6-任务派发")
    private Long reviewStatus;

    /** 标志 本记录是否有效（1-正常，0-删除） */
    @Excel(name = "标志 本记录是否有效", readConverterExp = "1=-正常，0-删除")
    private Long sign;

    /** 操作者ID */
    @Excel(name = "操作者ID")
    private Long operatorID;

    /** 操作时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "操作时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date operationTime;

    /** 样品检测要求 */
    @Excel(name = "检测要求")
    private String detectionDescription;

    public Date getOperationFromTime() {
        return operationFromTime;
    }

    public Date getOperationToTime() {
        return operationToTime;
    }

    public void setOperationFromTime(Date operationFromTime) {
        this.operationFromTime = operationFromTime;
    }

    public void setOperationToTime(Date operationToTime) {
        this.operationToTime = operationToTime;
    }

    /** 操作时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "操作起始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date operationFromTime;

    /** 操作时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "操作结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date operationToTime;

    /** 0-委托样品，1-样品登记的样品 */
    @Excel(name = "0-委托样品，1-样品登记的样品")
    private Integer sampleStage;

    /** 样品登记才有分发状态，0-未分发，1-已分发 */
    @Excel(name = "样品登记才有分发状态，0-未分发，1-已分发")
    private Integer sampleDistribute;

    public CommissionManage(){};

    public CommissionManage(EntrustList entrustList){
        priority = (long)entrustList.getPriority();
        samplingMode = 2L;
        detectionCategory = 1L;
        detectionStandardID = (long)entrustList.getDetectionStandardID();
        reportReceiveWay = (long)entrustList.getReportReceiveWay();
        samplingTime = entrustList.getSamplingTime();
        sampleName = entrustList.getSampleName();
        sampleScientificName = entrustList.getSampleScientificName();
        sampleStatus = (long)entrustList.getSampleStatus();
        sampleQuantity = entrustList.getSampleQuantity();
        measureUnit = (long)entrustList.getMeasureUnit();
        samplingBase = entrustList.getSamplingBase();
        productionUnit = entrustList.getProductionUnit();
        productionUnitAddress = entrustList.getProductionUnit();
        sampleDescription = entrustList.getSampleDescription();
        recycle = (long)entrustList.getRecycle();
        sampleImage = entrustList.getSampleImage();
        operationTime =  new Date(System.currentTimeMillis());
        sampleStage = 0;
        sampleDistribute = 0;
        sign = 1L;
    }

    public void setSampleID(Long sampleID)
    {
        this.sampleID = sampleID;
    }

    public Long getSampleID()
    {
        return sampleID;
    }
    public void setCustomerID(Long customerID)
    {
        this.customerID = customerID;
    }

    public Long getCustomerID()
    {
        return customerID;
    }
    public void setCompanyID(Long companyID)
    {
        this.companyID = companyID;
    }

    public Long getCompanyID()
    {
        return companyID;
    }
    public void setSampleNumber(String sampleNumber)
    {
        this.sampleNumber = sampleNumber;
    }

    public String getSampleNumber()
    {
        return sampleNumber;
    }
    public void setPriority(Long priority)
    {
        this.priority = priority;
    }

    public Long getPriority()
    {
        return priority;
    }
    public void setSamplingMode(Long samplingMode)
    {
        this.samplingMode = samplingMode;
    }

    public Long getSamplingMode()
    {
        return samplingMode;
    }
    public void setDetectionCategory(Long detectionCategory)
    {
        this.detectionCategory = detectionCategory;
    }

    public Long getDetectionCategory()
    {
        return detectionCategory;
    }
    public void setDetectionStandardID(Long detectionStandardID)
    {
        this.detectionStandardID = detectionStandardID;
    }

    public Long getDetectionStandardID()
    {
        return detectionStandardID;
    }
    public void setReportReceiveWay(Long reportReceiveWay)
    {
        this.reportReceiveWay = reportReceiveWay;
    }

    public Long getReportReceiveWay()
    {
        return reportReceiveWay;
    }
    public void setReceiptAddress(String receiptAddress)
    {
        this.receiptAddress = receiptAddress;
    }

    public String getReceiptAddress()
    {
        return receiptAddress;
    }
    public void setSampleSenderPhone(String sampleSenderPhone)
    {
        this.sampleSenderPhone = sampleSenderPhone;
    }

    public String getSampleSenderPhone()
    {
        return sampleSenderPhone;
    }
    public void setSampleSenderName(String sampleSenderName)
    {
        this.sampleSenderName = sampleSenderName;
    }

    public String getSampleSenderName()
    {
        return sampleSenderName;
    }
    public void setSamplingTime(Date samplingTime)
    {
        this.samplingTime = samplingTime;
    }

    public Date getSamplingTime()
    {
        return samplingTime;
    }
    public void setSampleName(String sampleName)
    {
        this.sampleName = sampleName;
    }

    public String getSampleName()
    {
        return sampleName;
    }
    public void setSampleScientificName(String sampleScientificName)
    {
        this.sampleScientificName = sampleScientificName;
    }

    public String getSampleScientificName()
    {
        return sampleScientificName;
    }
    public void setSampleStatus(Long sampleStatus)
    {
        this.sampleStatus = sampleStatus;
    }

    public Long getSampleStatus()
    {
        return sampleStatus;
    }
    public void setSampleQuantity(BigDecimal sampleQuantity)
    {
        this.sampleQuantity = sampleQuantity;
    }

    public BigDecimal getSampleQuantity()
    {
        return sampleQuantity;
    }
    public void setMeasureUnit(Long measureUnit)
    {
        this.measureUnit = measureUnit;
    }

    public Long getMeasureUnit()
    {
        return measureUnit;
    }
    public void setSamplingBase(String samplingBase)
    {
        this.samplingBase = samplingBase;
    }

    public String getSamplingBase()
    {
        return samplingBase;
    }
    public void setProductionUnit(String productionUnit)
    {
        this.productionUnit = productionUnit;
    }

    public String getProductionUnit()
    {
        return productionUnit;
    }
    public void setProductionUnitAddress(String productionUnitAddress)
    {
        this.productionUnitAddress = productionUnitAddress;
    }

    public String getProductionUnitAddress()
    {
        return productionUnitAddress;
    }
    public void setSampleDescription(String sampleDescription)
    {
        this.sampleDescription = sampleDescription;
    }

    public String getSampleDescription()
    {
        return sampleDescription;
    }
    public void setRecycle(Long recycle)
    {
        this.recycle = recycle;
    }

    public Long getRecycle()
    {
        return recycle;
    }
    public void setSampleImage(String sampleImage)
    {
        this.sampleImage = sampleImage;
    }

    public String getSampleImage()
    {
        return sampleImage;
    }
    public void setQRcodeAddress(String QRcodeAddress)
    {
        this.QRcodeAddress = QRcodeAddress;
    }

    public String getQRcodeAddress()
    {
        return QRcodeAddress;
    }
    public void setElectronicSignature(String electronicSignature)
    {
        this.electronicSignature = electronicSignature;
    }

    public String getElectronicSignature()
    {
        return electronicSignature;
    }
    public void setReviewStatus(Long reviewStatus)
    {
        this.reviewStatus = reviewStatus;
    }

    public Long getReviewStatus()
    {
        return reviewStatus;
    }
    public void setSign(Long sign)
    {
        this.sign = sign;
    }

    public Long getSign()
    {
        return sign;
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
    public void setSampleStage(Integer sampleStage)
    {
        this.sampleStage = sampleStage;
    }

    public Integer getSampleStage()
    {
        return sampleStage;
    }
    public void setSampleDistribute(Integer sampleDistribute)
    {
        this.sampleDistribute = sampleDistribute;
    }

    public Integer getSampleDistribute()
    {
        return sampleDistribute;
    }

    public String getCommissionNumber() {
        return commissionNumber;
    }

    public void setCommissionNumber(String commissionNumber) {
        this.commissionNumber = commissionNumber;
    }

    public String getDetectionDescription() {
        return detectionDescription;
    }

    public void setDetectionDescription(String detectionDescription) {
        this.detectionDescription = detectionDescription;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("sampleID", getSampleID())
                .append("customerID", getCustomerID())
                .append("companyID", getCompanyID())
                .append("sampleNumber", getSampleNumber())
                .append("priority", getPriority())
                .append("samplingMode", getSamplingMode())
                .append("detectionCategory", getDetectionCategory())
                .append("detectionStandardID", getDetectionStandardID())
                .append("reportReceiveWay", getReportReceiveWay())
                .append("receiptAddress", getReceiptAddress())
                .append("sampleSenderPhone", getSampleSenderPhone())
                .append("sampleSenderName", getSampleSenderName())
                .append("samplingTime", getSamplingTime())
                .append("sampleName", getSampleName())
                .append("sampleScientificName", getSampleScientificName())
                .append("sampleStatus", getSampleStatus())
                .append("sampleQuantity", getSampleQuantity())
                .append("measureUnit", getMeasureUnit())
                .append("samplingBase", getSamplingBase())
                .append("productionUnit", getProductionUnit())
                .append("productionUnitAddress", getProductionUnitAddress())
                .append("sampleDescription", getSampleDescription())
                .append("recycle", getRecycle())
                .append("sampleImage", getSampleImage())
                .append("QRcodeAddress", getQRcodeAddress())
                .append("electronicSignature", getElectronicSignature())
                .append("reviewStatus", getReviewStatus())
                .append("sign", getSign())
                .append("operatorID", getOperatorID())
                .append("operationTime", getOperationTime())
                .append("sampleStage", getSampleStage())
                .append("sampleDistribute", getSampleDistribute())
                .toString();
    }
}
