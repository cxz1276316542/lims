package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 编号生成对象 xt_bhzd
 *
 * @author lkun
 * @date 2021-08-22
 */
public class Number extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号字段ID */
    private Long numberID;

    /** 编号种类ID */
    @Excel(name = "编号种类ID")
    private Integer numberTypeID;

    /** 编号字段内容 */
    @Excel(name = "编号字段内容")
    private String content;

    /** 编号字段长度 */
    @Excel(name = "编号字段长度")
    private Integer contentLength;

    /** 编号字段描述 */
    @Excel(name = "编号字段描述")
    private String description;

    /** 顺序 */
    @Excel(name = "顺序")
    private Integer order;

    public void setNumberID(Long numberID)
    {
        this.numberID = numberID;
    }

    public Long getNumberID()
    {
        return numberID;
    }
    public void setNumberTypeID(Integer numberTypeID)
    {
        this.numberTypeID = numberTypeID;
    }

    public Integer getNumberTypeID()
    {
        return numberTypeID;
    }
    public void setContent(String content)
    {
        this.content = content;
    }

    public String getContent()
    {
        return content;
    }
    public void setContentLength(Integer contentLength)
    {
        this.contentLength = contentLength;
    }

    public Integer getContentLength()
    {
        return contentLength;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getDescription()
    {
        return description;
    }
    public void setOrder(Integer order)
    {
        this.order = order;
    }

    public Integer getOrder()
    {
        return order;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("numberID", getNumberID())
            .append("numberTypeID", getNumberTypeID())
            .append("content", getContent())
            .append("contentLength", getContentLength())
            .append("description", getDescription())
            .append("order", getOrder())
            .append("remark", getRemark())
            .toString();
    }
}
