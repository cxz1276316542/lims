package com.ruoyi.limit.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 检测方法-检测标准对象 xlk_jcff_jcbz
 * 
 * @author wlf
 * @date 2021-08-29
 */
public class DetectionMethodStandard extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long detectionMethodStandardID;

    /**检测方法ID*/
    private Long detectionMethodID;
    /**检测方法名称*/
    @Excel(name = "检测方法名称")
    private String detectionMethodName;

    /** 标准ID */
    private Long standardID;
    /** 标准编号 */
    @Excel(name = "标准编号")
    private String standardNumber;

    /** 检测中心ID */
//    @Excel(name = "检测中心ID")
    private Long detectionCenterID;

    /** 数据有效性标识 0-无效 1-有效 2-绑定 */
    @Excel(name = "数据有效性标识 0-无效 1-有效 2-绑定")
    private Integer isValid;

    public void setDetectionMethodStandardID(Long detectionMethodStandardID) 
    {
        this.detectionMethodStandardID = detectionMethodStandardID;
    }

    public Long getDetectionMethodStandardID() 
    {
        return detectionMethodStandardID;
    }
    public void setDetectionMethodID(Long detectionMethodID) 
    {
        this.detectionMethodID = detectionMethodID;
    }

    public Long getDetectionMethodID() 
    {
        return detectionMethodID;
    }
    public void setStandardID(Long standardID) 
    {
        this.standardID = standardID;
    }

    public Long getStandardID() 
    {
        return standardID;
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

    public String getDetectionMethodName() {
        return detectionMethodName;
    }

    public void setDetectionMethodName(String detectionMethodName) {
        this.detectionMethodName = detectionMethodName;
    }

    public String getStandardNumber() {
        return standardNumber;
    }

    public void setStandardNumber(String standardNumber) {
        this.standardNumber = standardNumber;
    }

    @Override
    public String toString() {
        return "DetectionMethodStandard{" +
                "detectionMethodStandardID=" + detectionMethodStandardID +
                ", detectionMethodID=" + detectionMethodID +
                ", detectionMethodName='" + detectionMethodName + '\'' +
                ", standardID=" + standardID +
                ", standardNumber='" + standardNumber + '\'' +
                ", detectionCenterID=" + detectionCenterID +
                ", isValid=" + isValid +
                '}';
    }
}
