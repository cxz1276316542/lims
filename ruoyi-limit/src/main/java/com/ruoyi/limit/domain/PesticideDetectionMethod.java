package com.ruoyi.limit.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 检测项目-检测方法对象 xlk_jcxm_jcff
 * 
 * @author wlf
 * @date 2021-08-28
 */
public class PesticideDetectionMethod extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增 */
    private Long pesticideDetectionMethodID;

    /** 检测项目ID */
//    @Excel(name = "检测项目ID")
    private Long pesticideID;
    @Excel(name = "检测项目名称")
    private String pesticideName;

    /** 检测方法ID */
//    @Excel(name = "检测方法ID")
    private Long detectionMethodID;
    @Excel(name = "检测方法名称")
    private String detectionMethodName;

    /** 检测中心ID */
//    @Excel(name = "检测中心ID")
    private Long detectionCenterID;

    /** 数据有效性标识 0-无效 1-有效 2-绑定 */
    @Excel(name = "数据有效性标识 0-无效 1-有效 2-绑定")
    private Integer isValid;

    public void setPesticideDetectionMethodID(Long pesticideDetectionMethodID) 
    {
        this.pesticideDetectionMethodID = pesticideDetectionMethodID;
    }

    public Long getPesticideDetectionMethodID() 
    {
        return pesticideDetectionMethodID;
    }
    public void setPesticideID(Long pesticideID) 
    {
        this.pesticideID = pesticideID;
    }

    public Long getPesticideID() 
    {
        return pesticideID;
    }
    public void setDetectionMethodID(Long detectionMethodID) 
    {
        this.detectionMethodID = detectionMethodID;
    }

    public Long getDetectionMethodID() 
    {
        return detectionMethodID;
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

    public String getPesticideName() {
        return pesticideName;
    }

    public void setPesticideName(String pesticideName) {
        this.pesticideName = pesticideName;
    }

    public String getDetectionMethodName() {
        return detectionMethodName;
    }

    public void setDetectionMethodName(String detectionMethodName) {
        this.detectionMethodName = detectionMethodName;
    }

    @Override
    public String toString() {
        return "PesticideDetectionMethod{" +
                "pesticideDetectionMethodID=" + pesticideDetectionMethodID +
                ", pesticideID=" + pesticideID +
                ", pesticideName='" + pesticideName + '\'' +
                ", detectionMethodID=" + detectionMethodID +
                ", detectionMethodName='" + detectionMethodName + '\'' +
                ", detectionCenterID=" + detectionCenterID +
                ", isValid=" + isValid +
                '}';
    }
}
