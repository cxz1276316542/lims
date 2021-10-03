package com.ruoyi.limit.domain;

import java.math.BigDecimal;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 限量库详细信息对象 xlk_jcbz_sp_ny
 * 
 * @author wlf
 * @date 2021-07-21
 */
public class DetectionStandardFoodPesticide extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 检测标准ID */
    private Long detectionStandardID;
    @Excel(name = "标准编号")
    private String standardNumber;

    /** 食品类别ID */
    private Long foodTypeID;

    @Excel(name = "食品学名")
    private String foodName;

    /** 检测农药ID */
    private Long pesticideID;

    @Excel(name = "农药名称")
    private String pesticideName;

    /** 检测方法ID */
    private String detectionFunctions;

    @Excel(name = "检测方法名称")
    private String detectionNames;

    /** 农药最大残留量(mg/kg） */
    @Excel(name = "农药最大残留量(mg/kg）")
    private BigDecimal maxResidualAmount;

    /** 备注 */
    @Excel(name = "临时限量备注")
    private String remark;

    /** 检测中心ID */
    private Long detectionCenterID;

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

    /** 数据有效性标识 */
    private Integer isValid;

    public DetectionStandardFoodPesticide() {
    }

    public DetectionStandardFoodPesticide(Long standard, Long foodTypeID, Long pesticideID, String detectionFunctions, BigDecimal maxResidualAmount, String remark) {
        this.detectionStandardID = standard;
        this.foodTypeID = foodTypeID;
        this.pesticideID = pesticideID;
        this.detectionFunctions = detectionFunctions;
        this.maxResidualAmount = maxResidualAmount;
        this.remark = remark;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getStandardNumber() {
        return standardNumber;
    }

    public void setStandardNumber(String standardNumber) {
        this.standardNumber = standardNumber;
    }

    public Long getDetectionStandardID() {
        return detectionStandardID;
    }

    public void setDetectionStandardID(Long detectionStandardID) {
        this.detectionStandardID = detectionStandardID;
    }

    public Long getFoodTypeID() {
        return foodTypeID;
    }

    public void setFoodTypeID(Long foodTypeID) {
        this.foodTypeID = foodTypeID;
    }

    public Long getPesticideID() {
        return pesticideID;
    }

    public void setPesticideID(Long pesticideID) {
        this.pesticideID = pesticideID;
    }

    public String getDetectionFunctions() {
        return detectionFunctions;
    }

    public void setDetectionFunctions(String detectionFunctions) {
        this.detectionFunctions = detectionFunctions;
    }

    public BigDecimal getMaxResidualAmount() {
        return maxResidualAmount;
    }

    public void setMaxResidualAmount(BigDecimal maxResidualAmount) {
        this.maxResidualAmount = maxResidualAmount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getPesticideName() {
        return pesticideName;
    }

    public void setPesticideName(String pesticideName) {
        this.pesticideName = pesticideName;
    }

    public String getDetectionNames() {
        return detectionNames;
    }

    public void setDetectionNames(String detectionNames) {
        this.detectionNames = detectionNames;
    }

    @Override
    public String toString() {
        return "DetectionStandardFoodPesticide{" +
                "detectionStandardID=" + detectionStandardID +
                ", standardNumber='" + standardNumber + '\'' +
                ", foodTypeID=" + foodTypeID +
                ", foodName='" + foodName + '\'' +
                ", pesticideID=" + pesticideID +
                ", pesticideName='" + pesticideName + '\'' +
                ", detectionFunctions='" + detectionFunctions + '\'' +
                ", detectionNames='" + detectionNames + '\'' +
                ", maxResidualAmount=" + maxResidualAmount +
                ", remark='" + remark + '\'' +
                ", detectionCenterID=" + detectionCenterID +
                ", isValid=" + isValid +
                '}';
    }
}
