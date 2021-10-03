package com.ruoyi.limit.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 样品-检测项目对象 xlk_yp_jcxm
 * 
 * @author wlf
 * @date 2021-08-28
 */
public class SamplePesticide extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增 */
    private Long samplePesticideID;

    /** 样品ID */
//    @Excel(name = "样品ID")
    private Long sampleID;

    /**食品类别*/
    @Excel(name = "样品类别层级")
    private String sampleTypeStr;

    /**食品学名*/
    @Excel(name = "样品学名")
    private String sampleName;

    /** 检测项目ID */
//    @Excel(name = "检测项目ID")
    private Long pesticideID;

    /** 农药名称 */
    @Excel(name = "检测项目名称")
    private String pesticideName;

    /** 检测中心ID */
//    @Excel(name = "检测中心ID")
    private Long detectionCenterID;

    /** 数据有效性标识 0-无效 1-有效 2-绑定 */
    @Excel(name = "数据有效性标识 0-无效 1-有效 2-绑定")
    private Integer isValid;

    public void setSamplePesticideID(Long samplePesticideID) 
    {
        this.samplePesticideID = samplePesticideID;
    }

    public Long getSamplePesticideID() 
    {
        return samplePesticideID;
    }
    public void setSampleID(Long sampleID) 
    {
        this.sampleID = sampleID;
    }

    public Long getSampleID() 
    {
        return sampleID;
    }
    public void setPesticideID(Long pesticideID) 
    {
        this.pesticideID = pesticideID;
    }

    public Long getPesticideID() 
    {
        return pesticideID;
    }
    public void setDetectionCenterID(Long detectionCenterID) 
    {
        this.detectionCenterID = detectionCenterID;
    }

    public Long getDetectionCenterID() 
    {
        return detectionCenterID;
    }
    public void setIsValid(Integer isValid) 
    {
        this.isValid = isValid;
    }

    public Integer getIsValid() 
    {
        return isValid;
    }

    public String getSampleTypeStr() {
        return sampleTypeStr;
    }

    public void setSampleTypeStr(String sampleTypeStr) {
        this.sampleTypeStr = sampleTypeStr;
    }

    public String getSampleName() {
        return sampleName;
    }

    public void setSampleName(String sampleName) {
        this.sampleName = sampleName;
    }

    public String getPesticideName() {
        return pesticideName;
    }

    public void setPesticideName(String pesticideName) {
        this.pesticideName = pesticideName;
    }

    @Override
    public String toString() {
        return "SamplePesticide{" +
                "samplePesticideID=" + samplePesticideID +
                ", sampleID=" + sampleID +
                ", sampleTypeStr='" + sampleTypeStr + '\'' +
                ", sampleName='" + sampleName + '\'' +
                ", pesticideID=" + pesticideID +
                ", pesticideName='" + pesticideName + '\'' +
                ", detectionCenterID=" + detectionCenterID +
                ", isValid=" + isValid +
                '}';
    }
}
