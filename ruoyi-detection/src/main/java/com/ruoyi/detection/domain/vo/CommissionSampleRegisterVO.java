package com.ruoyi.detection.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author liukun
 * @date 2021/8/3
 */
public class CommissionSampleRegisterVO implements Serializable {

    private static final long serialVersionUID = -8773766621836617819L;

    /** 委托样品ID */
    @Excel(name = "委托样品ID")
    private Long sampleID;

    /**
     * 客户ID
     */
    @Excel(name = "客户ID")
    private String customerID;

    /**
     * 客户账号
     */
    @Excel(name = "客户账号")
    private String customerNumber;

    /** 样品编号 由各机构确定格式自动生成 */
    @Excel(name = "样品编号 由各机构确定格式自动生成")
    private String sampleNumber;

    /** 优先级 1-正常；2-急；3-加急；4-特急（客户、业务员操作）默认正常 */
    @Excel(name = "优先级 1-正常；2-急；3-加急；4-特急", readConverterExp = "客户、业务员操作")
    private Integer priority;

    /** 采样方式（1-抽检、2-送检）默认送检 */
    @Excel(name = "采样方式", readConverterExp = "1=-抽检、2-送检")
    private Integer samplingMode;

    /** 检测类别 （1-委托、2-仲裁、3-其他）默认委托 */
    @Excel(name = "检测类别 ", readConverterExp = "1=-委托、2-仲裁、3-其他")
    private Integer detectionCategory;

    /**
     * 检测标准
     */
    @Excel(name = "检测标准")
    private String detectionStandardName;

    /** 报告领取方式 0-邮寄，1-自取 */
    @Excel(name = "报告领取方式 0-邮寄，1-自取")
    private Integer reportReceiveWay;

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

    /** 样品状态：1-固体；2-液体；3-胶体；4-气体；5-其他 */
    @Excel(name = "样品状态：1-固体；2-液体；3-胶体；4-气体；5-其他")
    private Integer sampleStatus;

    /** 样品数量 */
    @Excel(name = "样品数量")
    private BigDecimal sampleQuantity;

    /** 样品计量单位 */
    @Excel(name = "样品计量单位")
    private String measureUnit;

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
    private String sampleDescription;

    /** 样品描述 */
    @Excel(name = "样品描述")
    private List<String> sampleDescriptionArray;

    /** 是否回收剩余样品 0-否 1-是 */
    @Excel(name = "是否回收剩余样品 0-否 1-是")
    private Integer recycle;

    /** 样品图像地址 */
    @Excel(name = "样品图像地址")
    private String sampleImage;

    /** 二维码地址 */
    @Excel(name = "二维码地址")
    private String QRcodeAddress;

    /** 操作者名称 */
    @Excel(name = "操作者名称")
    private String operator;

    /** 操作时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "操作时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date operationTime;

    /**
     * 状态   委托保存（01）默认
     * 委托修改（02）
     * 委托退出（00）
     * 业务保存（11）默认
     * 业务提交（12）
     * 退回修改（10）
     * 受理审核通过（21）
     * 受理审核不通过（20）
     */
    @Excel(name = "状态")
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public Long getSampleID() {
        return sampleID;
    }

    public void setSampleID(Long sampleID) {
        this.sampleID = sampleID;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getSampleNumber() {
        return sampleNumber;
    }

    public void setSampleNumber(String sampleNumber) {
        this.sampleNumber = sampleNumber;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getSamplingMode() {
        return samplingMode;
    }

    public void setSamplingMode(Integer samplingMode) {
        this.samplingMode = samplingMode;
    }

    public Integer getDetectionCategory() {
        return detectionCategory;
    }

    public void setDetectionCategory(Integer detectionCategory) {
        this.detectionCategory = detectionCategory;
    }


    public String getDetectionStandardName() {
        return detectionStandardName;
    }

    public void setDetectionStandardName(String detectionStandardName) {
        this.detectionStandardName = detectionStandardName;
    }

    public Integer getReportReceiveWay() {
        return reportReceiveWay;
    }

    public void setReportReceiveWay(Integer reportReceiveWay) {
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

    public Integer getSampleStatus() {
        return sampleStatus;
    }

    public void setSampleStatus(Integer sampleStatus) {
        this.sampleStatus = sampleStatus;
    }

    public BigDecimal getSampleQuantity() {
        return sampleQuantity;
    }

    public void setSampleQuantity(BigDecimal sampleQuantity) {
        this.sampleQuantity = sampleQuantity;
    }

    public String getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(String measureUnit) {
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

    public String getSampleDescription() {
        return sampleDescription;
    }

    public void setSampleDescription(String sampleDescription) {
        this.sampleDescription = sampleDescription;
    }

    public Integer getRecycle() {
        return recycle;
    }

    public void setRecycle(Integer recycle) {
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

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }

    public List<String> getSampleDescriptionArray() {
        return sampleDescriptionArray;
    }

    public void setSampleDescriptionArray(List<String> sampleDescriptionArray) {
        this.sampleDescriptionArray = sampleDescriptionArray;
    }

    @Override
    public String toString() {
        return "CommissionSampleRegisterVO{" +
                "sampleID=" + sampleID +
                ", customerID='" + customerID + '\'' +
                ", customerNumber='" + customerNumber + '\'' +
                ", sampleNumber='" + sampleNumber + '\'' +
                ", priority=" + priority +
                ", samplingMode=" + samplingMode +
                ", detectionCategory=" + detectionCategory +
                ", detectionStandardName='" + detectionStandardName + '\'' +
                ", reportReceiveWay=" + reportReceiveWay +
                ", receiptAddress='" + receiptAddress + '\'' +
                ", sampleSenderPhone='" + sampleSenderPhone + '\'' +
                ", sampleSenderName='" + sampleSenderName + '\'' +
                ", samplingTime=" + samplingTime +
                ", sampleName='" + sampleName + '\'' +
                ", sampleScientificName='" + sampleScientificName + '\'' +
                ", sampleStatus=" + sampleStatus +
                ", sampleQuantity=" + sampleQuantity +
                ", measureUnit='" + measureUnit + '\'' +
                ", samplingBase='" + samplingBase + '\'' +
                ", productionUnit='" + productionUnit + '\'' +
                ", productionUnitAddress='" + productionUnitAddress + '\'' +
                ", sampleDescription='" + sampleDescription + '\'' +
                ", sampleDescriptionArray=" + sampleDescriptionArray +
                ", recycle=" + recycle +
                ", sampleImage='" + sampleImage + '\'' +
                ", QRcodeAddress='" + QRcodeAddress + '\'' +
                ", operator='" + operator + '\'' +
                ", operationTime=" + operationTime +
                ", status=" + status +
                '}';
    }
}
