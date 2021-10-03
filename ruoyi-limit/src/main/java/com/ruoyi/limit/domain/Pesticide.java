package com.ruoyi.limit.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.math.BigDecimal;

/**
 * 检测农药管理对象 xlk_jcny
 * 
 * @author wlf
 * @date 2021-07-20
 */
public class Pesticide extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 检测农药ID */
    private Long pesticideID;

    /** 农药名称 */
    @Excel(name = "项目名称")
    private String pesticideName;

    /** 农药英文名称 */
    @Excel(name = "项目英文名称")
    private String pesticideEnglishName;

    /** 豁免；0-不豁免，1-豁免 */
    @Excel(name = "豁免；0-不豁免，1-豁免")
    private Integer pesticideExempt;

    /** 成人每千克摄入量（mg/kg bw） */
//    @Excel(name = "成人每千克摄入量", readConverterExp = "m=g/kg,b=w")
    @Excel(name = "成人每千克摄入量(mg/kg bw)")
    private BigDecimal Adi;

    /** 残留物 */
    @Excel(name = "残留物")
    private String pesticideResidues;

    /** 功能 */
    @Excel(name = "功能")
    private String pesticideFunction;

    /** 检测中心ID */
    private Long detectionCenterID;

    /** 数据有效性标识 */
    private Integer isValid;


    public Pesticide() {
    }

    public Pesticide(String pesticideName, String pesticideEnglishName, Integer pesticideExempt, BigDecimal adi, String pesticideResidues, String pesticideFunction) {
        this.pesticideName = pesticideName;
        this.pesticideEnglishName = pesticideEnglishName;
        this.pesticideExempt = pesticideExempt;
        this.Adi = adi;
        this.pesticideResidues = pesticideResidues;
        this.pesticideFunction = pesticideFunction;
    }


    public void setPesticideID(Long pesticideID)
    {
        this.pesticideID = pesticideID;
    }

    public Long getPesticideID() 
    {
        return pesticideID;
    }
    public void setPesticideName(String pesticideName) 
    {
        this.pesticideName = pesticideName;
    }

    public String getPesticideName() 
    {
        return pesticideName;
    }
    public void setPesticideEnglishName(String pesticideEnglishName) 
    {
        this.pesticideEnglishName = pesticideEnglishName;
    }

    public String getPesticideEnglishName() 
    {
        return pesticideEnglishName;
    }
    public void setPesticideExempt(Integer pesticideExempt) 
    {
        this.pesticideExempt = pesticideExempt;
    }

    public Integer getPesticideExempt() 
    {
        return pesticideExempt;
    }

    public BigDecimal getAdi() {
        return Adi;
    }

    public void setAdi(BigDecimal adi) {
        this.Adi = adi;
    }

    public void setPesticideResidues(String pesticideResidues)
    {
        this.pesticideResidues = pesticideResidues;
    }

    public String getPesticideResidues() 
    {
        return pesticideResidues;
    }
    public void setPesticideFunction(String pesticideFunction) 
    {
        this.pesticideFunction = pesticideFunction;
    }

    public String getPesticideFunction() 
    {
        return pesticideFunction;
    }

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

    @Override
    public String toString() {
        return "Pesticide{" +
                "pesticideID=" + pesticideID +
                ", pesticideName='" + pesticideName + '\'' +
                ", pesticideEnglishName='" + pesticideEnglishName + '\'' +
                ", pesticideExempt=" + pesticideExempt +
                ", Adi=" + Adi +
                ", pesticideResidues='" + pesticideResidues + '\'' +
                ", pesticideFunction='" + pesticideFunction + '\'' +
                ", detectionCenterID=" + detectionCenterID +
                ", isValid=" + isValid +
                '}';
    }
}
