package com.ruoyi.detection.domain;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 委托样品对象 ypjc_wtyp
 *
 * @author lkun
 * @date 2021-07-21
 */
public class CommissionSample extends BaseEntity
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

    /** 委托编号*/
    @Excel(name = "委托编号")
    private String principalNumber;

    /** 优先级 1-正常；2-急；3-加急；4-特急（客户、业务员操作）默认正常 */
    @Excel(name = "优先级 1-正常；2-急；3-加急；4-特急", readConverterExp = "客户、业务员操作")
    private Integer priority;

    /** 采样方式（1-抽检、2-送检）默认送检 */
    @Excel(name = "采样方式", readConverterExp = "1=-抽检、2-送检")
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

    /**
     * 报告邮寄地址
     */
    @Excel(name = "报告邮寄地址")
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

    /** 样品描述 */
    @Excel(name = "样品描述")
    private String sampleDescription;

    /**样品描述数组*/
    private List<String> sampleDescriptionArray;

    /** 样品状态：1-固体；2-液体；3-胶体；4-气体；5-其他 */
    @Excel(name = "样品状态：1-固体；2-液体；3-胶体；4-气体；5-其他")
    private Long sampleStatus;

    /** 样品数量 */
    @Excel(name = "样品数量")
    private BigDecimal sampleQuantity;

    /** 样品计量单位ID */
    @Excel(name = "样品计量单位ID")
    private Integer measureUnit;

    /** 抽样基数 */
    @Excel(name = "抽样基数")
    private String samplingBase;

    /** 样品生产单位 */
    @Excel(name = "样品生产单位")
    private String productionUnit;

    /** 生产单位地址 */
    @Excel(name = "生产单位地址")
    private String productionUnitAddress;

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

    /** 审核状态 0-委托1-审核通过，2-审核不通过，3-保存 */
    @Excel(name = "审核状态 0-委托待审核1-审核通过，2-审核不通过，3-委托单保存，4-样品登记保存，5登记成功")
    private Integer reviewStatus;

    /** 标志 本记录是否有效（1-正常，0-删除） */
    @Excel(name = "标志 本记录是否有效", readConverterExp = "1=-正常，0-删除")
    private Integer sign;

    /** 操作者ID */
    @Excel(name = "操作者ID")
    private Long operatorID;

    /** 操作时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "操作时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date operationTime;

    private Integer sampleStage;

    private Integer flag;

    public List<String> getSampleDescriptionArray() {
        return sampleDescriptionArray;
    }

    public void setSampleDescriptionArray(List<String> sampleDescriptionArray) {
        this.sampleDescriptionArray = sampleDescriptionArray;
    }

    public Long getSampleID() {
        return sampleID;
    }

    public void setSampleID(Long sampleID) {
        this.sampleID = sampleID;
    }

    public Long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    public Long getCompanyID() {
        return companyID;
    }

    public void setCompanyID(Long companyID) {
        this.companyID = companyID;
    }

    public String getSampleNumber() {
        return sampleNumber;
    }

    public void setSampleNumber(String sampleNumber) {
        this.sampleNumber = sampleNumber;
    }

    public String getPrincipalNumber() {
        return principalNumber;
    }

    public void setPrincipalNumber(String principalNumber) {
        this.principalNumber = principalNumber;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Long getSamplingMode() {
        return samplingMode;
    }

    public void setSamplingMode(Long samplingMode) {
        this.samplingMode = samplingMode;
    }

    public Long getDetectionCategory() {
        return detectionCategory;
    }

    public void setDetectionCategory(Long detectionCategory) {
        this.detectionCategory = detectionCategory;
    }

    public Long getDetectionStandardID() {
        return detectionStandardID;
    }

    public void setDetectionStandardID(Long detectionStandardID) {
        this.detectionStandardID = detectionStandardID;
    }

    public Long getReportReceiveWay() {
        return reportReceiveWay;
    }

    public void setReportReceiveWay(Long reportReceiveWay) {
        this.reportReceiveWay = reportReceiveWay;
    }

    public String getReceiptAddress() {
        return receiptAddress;
    }

    public void setReceiptAddress(String receiptAddress) {
        this.receiptAddress = receiptAddress;
    }

    public String getSampleSenderPhone() {
        return sampleSenderPhone;
    }

    public void setSampleSenderPhone(String sampleSenderPhone) {
        this.sampleSenderPhone = sampleSenderPhone;
    }

    public String getSampleSenderName() {
        return sampleSenderName;
    }

    public void setSampleSenderName(String sampleSenderName) {
        this.sampleSenderName = sampleSenderName;
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

    public String getSampleScientificName() {
        return sampleScientificName;
    }

    public void setSampleScientificName(String sampleScientificName) {
        this.sampleScientificName = sampleScientificName;
    }

    public String getSampleDescription() {
        return sampleDescription;
    }

    public void setSampleDescription(String sampleDescription) {
        this.sampleDescription = sampleDescription;
    }

    public Long getSampleStatus() {
        return sampleStatus;
    }

    public void setSampleStatus(Long sampleStatus) {
        this.sampleStatus = sampleStatus;
    }

    public BigDecimal getSampleQuantity() {
        return sampleQuantity;
    }

    public void setSampleQuantity(BigDecimal sampleQuantity) {
        this.sampleQuantity = sampleQuantity;
    }

    public Integer getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(Integer measureUnit) {
        this.measureUnit = measureUnit;
    }

    public String getSamplingBase() {
        return samplingBase;
    }

    public void setSamplingBase(String samplingBase) {
        this.samplingBase = samplingBase;
    }

    public String getProductionUnit() {
        return productionUnit;
    }

    public void setProductionUnit(String productionUnit) {
        this.productionUnit = productionUnit;
    }

    public String getProductionUnitAddress() {
        return productionUnitAddress;
    }

    public void setProductionUnitAddress(String productionUnitAddress) {
        this.productionUnitAddress = productionUnitAddress;
    }

    public Long getRecycle() {
        return recycle;
    }

    public void setRecycle(Long recycle) {
        this.recycle = recycle;
    }

    public String getSampleImage() {
        return sampleImage;
    }

    public void setSampleImage(String sampleImage) {
        this.sampleImage = sampleImage;
    }

    public String getQRcodeAddress() {
        return QRcodeAddress;
    }

    public void setQRcodeAddress(String QRcodeAddress) {
        this.QRcodeAddress = QRcodeAddress;
    }

    public String getElectronicSignature() {
        return electronicSignature;
    }

    public void setElectronicSignature(String electronicSignature) {
        this.electronicSignature = electronicSignature;
    }

    public Integer getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(Integer reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public Integer getSign() {
        return sign;
    }

    public void setSign(Integer sign) {
        this.sign = sign;
    }

    public Long getOperatorID() {
        return operatorID;
    }

    public void setOperatorID(Long operatorID) {
        this.operatorID = operatorID;
    }

    public Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }

    public Integer getSampleStage() {
        return sampleStage;
    }

    public void setSampleStage(Integer sampleStage) {
        this.sampleStage = sampleStage;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "CommissionSample{" +
                "sampleID=" + sampleID +
                ", customerID=" + customerID +
                ", companyID=" + companyID +
                ", sampleNumber='" + sampleNumber + '\'' +
                ", principalNumber='" + principalNumber + '\'' +
                ", priority=" + priority +
                ", samplingMode=" + samplingMode +
                ", detectionCategory=" + detectionCategory +
                ", detectionStandardID=" + detectionStandardID +
                ", reportReceiveWay=" + reportReceiveWay +
                ", receiptAddress='" + receiptAddress + '\'' +
                ", sampleSenderPhone='" + sampleSenderPhone + '\'' +
                ", sampleSenderName='" + sampleSenderName + '\'' +
                ", samplingTime=" + samplingTime +
                ", sampleName='" + sampleName + '\'' +
                ", sampleScientificName='" + sampleScientificName + '\'' +
                ", sampleDescription='" + sampleDescription + '\'' +
                ", sampleDescriptionArray=" + sampleDescriptionArray +
                ", sampleStatus=" + sampleStatus +
                ", sampleQuantity=" + sampleQuantity +
                ", measureUnit=" + measureUnit +
                ", samplingBase='" + samplingBase + '\'' +
                ", productionUnit='" + productionUnit + '\'' +
                ", productionUnitAddress='" + productionUnitAddress + '\'' +
                ", recycle=" + recycle +
                ", sampleImage='" + sampleImage + '\'' +
                ", QRcodeAddress='" + QRcodeAddress + '\'' +
                ", electronicSignature='" + electronicSignature + '\'' +
                ", reviewStatus=" + reviewStatus +
                ", sign=" + sign +
                ", operatorID=" + operatorID +
                ", operationTime=" + operationTime +
                ", sampleStage=" + sampleStage +
                ", flag=" + flag +
                '}';
    }
}
