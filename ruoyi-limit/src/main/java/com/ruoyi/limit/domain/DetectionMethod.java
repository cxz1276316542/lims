package com.ruoyi.limit.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 检测方法管理对象 xlk_jcff
 * 
 * @author
 * @date 2021-07-20
 */
public class DetectionMethod extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 检测方法ID */
    private Long detectionMethodID;

    /** 检测方法名称 */
    @Excel(name = "检测方法名称")
    private String detectionMethodName;

    /** 操作描述 */
    @Excel(name = "操作描述")
    private String detectionMethodInfo;

    /** 检测方法地址 */
    @Excel(name = "检测方法地址")
    private String detectionMethodURL;

    /** 检测中心ID */
    private Long detectionCenterID;

    /** 数据有效性标识 */
    @Excel(name = "数据有效性", prompt="0-无效 1-有效")
    private Integer isValid;

    public DetectionMethod() {
    }

    public DetectionMethod(String detectionMethodName) {
        this.detectionMethodName = detectionMethodName;
    }

    public DetectionMethod(String detectionMethodName, String detectionMethodInfo, String detectionMethodURL, Long detectionCenterID, Integer isValid) {
        this.detectionMethodName = detectionMethodName;
        this.detectionMethodInfo = detectionMethodInfo;
        this.detectionMethodURL = detectionMethodURL;
        this.detectionCenterID = detectionCenterID;
        this.isValid = isValid;
    }

    public void setDetectionMethodID(Long detectionMethodID)
    {
        this.detectionMethodID = detectionMethodID;
    }

    public Long getDetectionMethodID() 
    {
        return detectionMethodID;
    }
    public void setDetectionMethodName(String detectionMethodName) 
    {
        this.detectionMethodName = detectionMethodName;
    }

    public String getDetectionMethodName() 
    {
        return detectionMethodName;
    }
    public void setDetectionMethodInfo(String detectionMethodInfo) 
    {
        this.detectionMethodInfo = detectionMethodInfo;
    }

    public String getDetectionMethodInfo() 
    {
        return detectionMethodInfo;
    }
    public void setDetectionMethodURL(String detectionMethodURL) 
    {
        this.detectionMethodURL = detectionMethodURL;
    }

    public String getDetectionMethodURL() 
    {
        return detectionMethodURL;
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
        return "DetectionMethod{" +
                "detectionMethodID=" + detectionMethodID +
                ", detectionMethodName='" + detectionMethodName + '\'' +
                ", detectionMethodInfo='" + detectionMethodInfo + '\'' +
                ", detectionMethodURL='" + detectionMethodURL + '\'' +
                ", detectionCenterID=" + detectionCenterID +
                ", isValid=" + isValid +
                '}';
    }
}
