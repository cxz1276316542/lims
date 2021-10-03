package com.ruoyi.detection.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 检测中心对象 xt_jczx
 *
 * @author zhangkui
 * @date 2021-09-02
 */
public class Laboratory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 检测中心ID */
    @Excel(name = "检测中心ID")
    private Long laboratoryID;

    /** 检测中心名称 */
    @Excel(name = "检测中心名称")
    private String laboratoryName;

    /** 检测中心地址 */
    @Excel(name = "检测中心地址")
    private String laboratoryAddress;

    /** 检测中心邮箱 */
    @Excel(name = "检测中心邮箱")
    private String laboratoryEmail;

    /** 电话 */
    @Excel(name = "电话")
    private String laboratoryTel;

    /** 检测中心负责人 */
    @Excel(name = "检测中心负责人")
    private String laboratoryLeader;

    /** 邮政编码 */
    @Excel(name = "邮政编码")
    private String laboratoryPostCode;

    public void setLaboratoryID(Long laboratoryID)
    {
        this.laboratoryID = laboratoryID;
    }

    public Long getLaboratoryID()
    {
        return laboratoryID;
    }
    public void setLaboratoryName(String laboratoryName)
    {
        this.laboratoryName = laboratoryName;
    }

    public String getLaboratoryName()
    {
        return laboratoryName;
    }
    public void setLaboratoryAddress(String laboratoryAddress)
    {
        this.laboratoryAddress = laboratoryAddress;
    }

    public String getLaboratoryAddress()
    {
        return laboratoryAddress;
    }
    public void setLaboratoryEmail(String laboratoryEmail)
    {
        this.laboratoryEmail = laboratoryEmail;
    }

    public String getLaboratoryEmail()
    {
        return laboratoryEmail;
    }
    public void setLaboratoryTel(String laboratoryTel)
    {
        this.laboratoryTel = laboratoryTel;
    }

    public String getLaboratoryTel()
    {
        return laboratoryTel;
    }
    public void setLaboratoryLeader(String laboratoryLeader)
    {
        this.laboratoryLeader = laboratoryLeader;
    }

    public String getLaboratoryLeader()
    {
        return laboratoryLeader;
    }
    public void setLaboratoryPostCode(String laboratoryPostCode)
    {
        this.laboratoryPostCode = laboratoryPostCode;
    }

    public String getLaboratoryPostCode()
    {
        return laboratoryPostCode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("laboratoryID", getLaboratoryID())
                .append("laboratoryName", getLaboratoryName())
                .append("laboratoryAddress", getLaboratoryAddress())
                .append("laboratoryEmail", getLaboratoryEmail())
                .append("laboratoryTel", getLaboratoryTel())
                .append("laboratoryLeader", getLaboratoryLeader())
                .append("laboratoryPostCode", getLaboratoryPostCode())
                .append("remark", getRemark())
                .toString();
    }
}