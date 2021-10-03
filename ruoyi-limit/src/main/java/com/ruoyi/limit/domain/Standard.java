package com.ruoyi.limit.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 检测标准对象 xlk_jcbz
 *
 * @author wlf
 * @date 2021-07-20
 */
public class Standard extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 检测标准ID */
    private Long standard;

    /** 标准编号 */
    @Excel(name = "标准编号")
    private String standardNumber;

    /** 标准类型:1-国标 2-地方标准
        3-行业标准 4-非标
    */
    @Excel(name = "标准类型:1-国标 2-地方标准 3-行业标准 4-非标 ")
    private Integer standardType;

    /** 检测标准名称 */
    @Excel(name = "检测标准名称")
    private String standardName;

    /** 备注 */
    @Excel(name = "备注")
    private String standardRemark;

    /** 检测标准执行开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "检测标准执行开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date standardStartTime;

    /** 检测标准执行结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "检测标准执行结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date standardEndTime;

    /** 检测中心ID */
    private Long detectionCenterID;

    /** 数据有效性标识 */
    private Integer isValid;

    public void setStandard(Long standard)
    {
        this.standard = standard;
    }

    public Long getStandard()
    {
        return standard;
    }
    public void setStandardNumber(String standardNumber)
    {
        this.standardNumber = standardNumber;
    }

    public String getStandardNumber()
    {
        return standardNumber;
    }
    public void setStandardType(Integer standardType)
    {
        this.standardType = standardType;
    }

    public Integer getStandardType()
    {
        return standardType;
    }
    public void setStandardName(String standardName)
    {
        this.standardName = standardName;
    }

    public String getStandardName()
    {
        return standardName;
    }
    public void setStandardRemark(String standardRemark)
    {
        this.standardRemark = standardRemark;
    }

    public String getStandardRemark()
    {
        return standardRemark;
    }
    public void setStandardStartTime(Date standardStartTime)
    {
        this.standardStartTime = standardStartTime;
    }

    public Date getStandardStartTime()
    {
        return standardStartTime;
    }
    public void setStandardEndTime(Date standardEndTime)
    {
        this.standardEndTime = standardEndTime;
    }

    public Date getStandardEndTime()
    {
        return standardEndTime;
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
        return "Standard{" +
                "standard=" + standard +
                ", standardNumber='" + standardNumber + '\'' +
                ", standardType=" + standardType +
                ", standardName='" + standardName + '\'' +
                ", standardRemark='" + standardRemark + '\'' +
                ", standardStartTime=" + standardStartTime +
                ", standardEndTime=" + standardEndTime +
                ", detectionCenterID=" + detectionCenterID +
                ", isValid=" + isValid +
                '}';
    }
}
