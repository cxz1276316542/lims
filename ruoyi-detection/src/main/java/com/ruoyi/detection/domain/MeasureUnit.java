package com.ruoyi.detection.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * @author liukun
 * @date 2021/7/21
 */
public class MeasureUnit extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 计量单位ID */
    private Long measureUnitID;

    /** 计量单位名称 */
    @Excel(name = "计量单位名称")
    private String measureUnitName;

    /** 计量单位说明 */
    @Excel(name = "计量单位说明")
    private String explain;

    /**  标识 :0-删除，1-有效 */
    @Excel(name = " 标识 :0-删除，1-有效")
    private Integer sign;

    public void setMeasureUnitID(Long measureUnitID)
    {
        this.measureUnitID = measureUnitID;
    }

    public Long getMeasureUnitID()
    {
        return measureUnitID;
    }
    public void setMeasureUnitName(String measureUnitName)
    {
        this.measureUnitName = measureUnitName;
    }

    public String getMeasureUnitName()
    {
        return measureUnitName;
    }
    public void setExplain(String explain)
    {
        this.explain = explain;
    }

    public String getExplain()
    {
        return explain;
    }
    public void setSign(Integer sign)
    {
        this.sign = sign;
    }

    public Integer getSign()
    {
        return sign;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("measureUnitID", getMeasureUnitID())
                .append("measureUnitName", getMeasureUnitName())
                .append("explain", getExplain())
                .append("sign", getSign())
                .toString();
    }
}
