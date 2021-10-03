package com.ruoyi.limit.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/*
* 检测标准-食品-农药-检测方法关系
* */

public class LimitInfoVo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 检测标准ID */
    private Long standard;

    /** 标准编号 */
    @Excel(name = "标准编号")
    private String standardNumber;

    /** 标准类型:1-国标 2-地方标准
     3-行业标准 4-非标
     */
//    @Excel(name = "标准类型:1-国标 2-地方标准 3-行业标准 4-非标 ")
    private Integer standardType;

    /** 检测标准名称 */
//    @Excel(name = "检测标准名称")
    private String standardName;

    /** 备注 */
//    @Excel(name = "检测标准备注")
    private String standardRemark;

    /** 检测标准执行开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
//    @Excel(name = "检测标准执行开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date standardStartTime;

    /** 检测标准执行结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
//    @Excel(name = "检测标准执行结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date standardEndTime;

    /** 食品ID */
    private Long foodID;

    /** 食品类别ID */
//    @Excel(name = "食品类别ID")
    private Long foodTypeID;

    /**食品类别层级**/
    @Excel(name = "样品类别层级")
    private String foodTypeStr;
    /** 食品名称 */
    @Excel(name = "样品名称")
    private String foodNickName;

    /** 食品学名 */
    @Excel(name = "样品学名")
    private String foodName;

    /** 测定部位 */
    @Excel(name = "测定部位")
    private String detectionPart;
    //private Food food;

    /** 检测农药ID */
    private Long pesticideID;

    /** 农药名称 */
    @Excel(name = "检测项目名称")
    private String pesticideName;

    /** 农药英文名称 */
    @Excel(name = "项目英文名称")
    private String pesticideEnglishName;


    /** 功能 */
    @Excel(name = "功能")
    private String pesticideFunction;

    /** 农药最大残留量(mg/kg） */
//    @Excel(name = "农药最大残留量(mg/kg）")
    @Excel(name = "最大残留量(mg/kg）")
    private BigDecimal maxResidualAmount;

    /** 成人每千克摄入量（mg/kg bw） */
//    @Excel(name = "成人每千克摄入量(mg/kg bw)", readConverterExp = "m=g/kg,b=w")
//    @Excel(name = "成人每千克摄入量(mg/kg bw)")
    @Excel(name = "ADImgkgbw")
    private BigDecimal Adi;

    /** 豁免；0-不豁免，1-豁免 */
    @Excel(name = "豁免；0-不豁免，1-豁免")
    private Integer pesticideExempt;

    /** 残留物 */
    @Excel(name = "残留物")
    private String pesticideResidues;


    /** 检测方法ID */
    /*@Excel(name = "检测方法ID")*/
    private String detectionFunctionIDs;

    /** 检测方法名称 */
    @Excel(name = "检测方法名称")
    private String detectionNames;

    /** 操作描述 */
//    @Excel(name = "操作描述")
    private String detectionMethodInfo;

    /** 检测方法地址 */
//    @Excel(name = "检测方法地址")
    private String detectionMethodURL;


    /** 备注 */
    @Excel(name = "临时限量备注")
    private String remark;

    public Long getStandard() {
        return standard;
    }

    public void setStandard(Long standard) {
        this.standard = standard;
    }

    public String getStandardNumber() {
        return standardNumber;
    }

    public void setStandardNumber(String standardNumber) {
        this.standardNumber = standardNumber;
    }

    public Integer getStandardType() {
        return standardType;
    }

    public void setStandardType(Integer standardType) {
        this.standardType = standardType;
    }

    public String getStandardName() {
        return standardName;
    }

    public void setStandardName(String standardName) {
        this.standardName = standardName;
    }

    public String getStandardRemark() {
        return standardRemark;
    }

    public void setStandardRemark(String standardRemark) {
        this.standardRemark = standardRemark;
    }

    public Date getStandardStartTime() {
        return standardStartTime;
    }

    public void setStandardStartTime(Date standardStartTime) {
        this.standardStartTime = standardStartTime;
    }

    public Date getStandardEndTime() {
        return standardEndTime;
    }

    public void setStandardEndTime(Date standardEndTime) {
        this.standardEndTime = standardEndTime;
    }

    public Long getFoodID() {
        return foodID;
    }

    public void setFoodID(Long foodID) {
        this.foodID = foodID;
    }

    public Long getFoodTypeID() {
        return foodTypeID;
    }

    public void setFoodTypeID(Long foodTypeID) {
        this.foodTypeID = foodTypeID;
    }

    public String getFoodTypeStr() {
        return foodTypeStr;
    }

    public void setFoodTypeStr(String foodTypeStr) {
        this.foodTypeStr = foodTypeStr;
    }

    public String getFoodNickName() {
        return foodNickName;
    }

    public void setFoodNickName(String foodNickName) {
        this.foodNickName = foodNickName;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getDetectionPart() {
        return detectionPart;
    }

    public void setDetectionPart(String detectionPart) {
        this.detectionPart = detectionPart;
    }

    public Long getPesticideID() {
        return pesticideID;
    }

    public void setPesticideID(Long pesticideID) {
        this.pesticideID = pesticideID;
    }

    public String getPesticideName() {
        return pesticideName;
    }

    public void setPesticideName(String pesticideName) {
        this.pesticideName = pesticideName;
    }

    public String getPesticideEnglishName() {
        return pesticideEnglishName;
    }

    public void setPesticideEnglishName(String pesticideEnglishName) {
        this.pesticideEnglishName = pesticideEnglishName;
    }

    public String getPesticideFunction() {
        return pesticideFunction;
    }

    public void setPesticideFunction(String pesticideFunction) {
        this.pesticideFunction = pesticideFunction;
    }

    public BigDecimal getMaxResidualAmount() {
        return maxResidualAmount;
    }

    public void setMaxResidualAmount(BigDecimal maxResidualAmount) {
        this.maxResidualAmount = maxResidualAmount;
    }

    public BigDecimal getAdi() {
        return Adi;
    }

    public void setAdi(BigDecimal adi) {
        Adi = adi;
    }

    public Integer getPesticideExempt() {
        return pesticideExempt;
    }

    public void setPesticideExempt(Integer pesticideExempt) {
        this.pesticideExempt = pesticideExempt;
    }

    public String getPesticideResidues() {
        return pesticideResidues;
    }

    public void setPesticideResidues(String pesticideResidues) {
        this.pesticideResidues = pesticideResidues;
    }

    public String getDetectionFunctionIDs() {
        return detectionFunctionIDs;
    }

    public void setDetectionFunctionIDs(String detectionFunctionIDs) {
        this.detectionFunctionIDs = detectionFunctionIDs;
    }

    public String getDetectionNames() {
        return detectionNames;
    }

    public void setDetectionNames(String detectionNames) {
        this.detectionNames = detectionNames;
    }

    public String getDetectionMethodInfo() {
        return detectionMethodInfo;
    }

    public void setDetectionMethodInfo(String detectionMethodInfo) {
        this.detectionMethodInfo = detectionMethodInfo;
    }

    public String getDetectionMethodURL() {
        return detectionMethodURL;
    }

    public void setDetectionMethodURL(String detectionMethodURL) {
        this.detectionMethodURL = detectionMethodURL;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "LimitInfoVo{" +
                "standard=" + standard +
                ", standardNumber='" + standardNumber + '\'' +
                ", standardType=" + standardType +
                ", standardName='" + standardName + '\'' +
                ", standardRemark='" + standardRemark + '\'' +
                ", standardStartTime=" + standardStartTime +
                ", standardEndTime=" + standardEndTime +
                ", foodID=" + foodID +
                ", foodTypeID=" + foodTypeID +
                ", foodTypeStr='" + foodTypeStr + '\'' +
                ", foodNickName='" + foodNickName + '\'' +
                ", foodName='" + foodName + '\'' +
                ", detectionPart='" + detectionPart + '\'' +
                ", pesticideID=" + pesticideID +
                ", pesticideName='" + pesticideName + '\'' +
                ", pesticideEnglishName='" + pesticideEnglishName + '\'' +
                ", pesticideFunction='" + pesticideFunction + '\'' +
                ", maxResidualAmount=" + maxResidualAmount +
                ", Adi=" + Adi +
                ", pesticideExempt=" + pesticideExempt +
                ", pesticideResidues='" + pesticideResidues + '\'' +
                ", detectionFunctionIDs='" + detectionFunctionIDs + '\'' +
                ", detectionNames='" + detectionNames + '\'' +
                ", detectionMethodInfo='" + detectionMethodInfo + '\'' +
                ", detectionMethodURL='" + detectionMethodURL + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
