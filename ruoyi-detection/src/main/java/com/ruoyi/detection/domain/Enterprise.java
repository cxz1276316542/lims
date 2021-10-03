package com.ruoyi.detection.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 企业信息对象 xt_qy
 * 
 * @author zhangkui
 * @date 2021-07-22
 */
public class Enterprise extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 企业ID */
    private Long enterpriseID;

    /** 客户ID */
    private Long userId;

    /** （0-企业，1-个人） */
    @Excel(name = "", readConverterExp = "0=-企业，1-个人")
    private Integer enterpriseType;

    /** 企业名称 */
    @Excel(name = "企业名称")
    private String enterpriseName;

    /** 企业地址 */
    @Excel(name = "企业地址")
    private String enterpriseAddress;

    /** 企业电话 */
    @Excel(name = "企业电话")
    private String enterprisePhone;

    /** 企业邮政编码 */
    @Excel(name = "企业邮政编码")
    private String postalCode ;

    /** 企业负责人 */
    @Excel(name = "企业负责人")
    private String Principal;

    /** 企业邮箱 */
    @Excel(name = "企业邮箱")
    private String enterpriseEmail;

    public void setEnterpriseID(Long enterpriseID) 
    {
        this.enterpriseID = enterpriseID;
    }

    public Long getEnterpriseID() 
    {
        return enterpriseID;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setEnterpriseType(Integer enterpriseType) 
    {
        this.enterpriseType = enterpriseType;
    }

    public Integer getEnterpriseType() 
    {
        return enterpriseType;
    }
    public void setEnterpriseName(String enterpriseName) 
    {
        this.enterpriseName = enterpriseName;
    }

    public String getEnterpriseName() 
    {
        return enterpriseName;
    }
    public void setEnterpriseAddress(String enterpriseAddress) 
    {
        this.enterpriseAddress = enterpriseAddress;
    }

    public String getEnterpriseAddress() 
    {
        return enterpriseAddress;
    }
    public void setEnterprisePhone(String enterprisePhone) 
    {
        this.enterprisePhone = enterprisePhone;
    }

    public String getEnterprisePhone() 
    {
        return enterprisePhone;
    }
    public void setPostalCode (String postalCode ) 
    {
        this.postalCode  = postalCode ;
    }

    public String getPostalCode () 
    {
        return postalCode ;
    }
    public void setPrincipal(String Principal) 
    {
        this.Principal = Principal;
    }

    public String getPrincipal() 
    {
        return Principal;
    }
    public void setEnterpriseEmail(String enterpriseEmail) 
    {
        this.enterpriseEmail = enterpriseEmail;
    }

    public String getEnterpriseEmail() 
    {
        return enterpriseEmail;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("enterpriseID", getEnterpriseID())
            .append("userId", getUserId())
            .append("enterpriseType", getEnterpriseType())
            .append("enterpriseName", getEnterpriseName())
            .append("enterpriseAddress", getEnterpriseAddress())
            .append("enterprisePhone", getEnterprisePhone())
            .append("postalCode ", getPostalCode ())
            .append("Principal", getPrincipal())
            .append("enterpriseEmail", getEnterpriseEmail())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
