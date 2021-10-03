package com.ruoyi.detection.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 报告模板对象 xt_bgmb
 * 
 * @author zhangkui
 * @date 2021-08-25
 */
public class ReportTemplate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 报告模板ID */
    @Excel(name = "报告模板ID")
    private Long reportTemID;

    /** 报告模板名称 */
    @Excel(name = "报告模板名称")
    private String reportTemName;

    /** 报告模板地址 */
      @Excel(name = "报告模板地址")
    private String reportTemURL;

    /** 检测中心ID */
    @Excel(name = "检测中心ID")
    private Long LaboratoryID;

    /** 操作员ID */
    @Excel(name = "操作员ID")
    private Long operatorID;

    /** 操作时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "操作时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date operatorTime;

    public void setReportTemID(Long reportTemID) 
    {
        this.reportTemID = reportTemID;
    }

    public Long getReportTemID() 
    {
        return reportTemID;
    }
    public void setReportTemName(String reportTemName) 
    {
        this.reportTemName = reportTemName;
    }

    public String getReportTemName() 
    {
        return reportTemName;
    }
    public void setReportTemURL(String reportTemURL) 
    {
        this.reportTemURL = reportTemURL;
    }

    public String getReportTemURL() 
    {
        return reportTemURL;
    }
    public void setLaboratoryID(Long LaboratoryID) 
    {
        this.LaboratoryID = LaboratoryID;
    }

    public Long getLaboratoryID() 
    {
        return LaboratoryID;
    }
    public void setOperatorID(Long operatorID) 
    {
        this.operatorID = operatorID;
    }

    public Long getOperatorID() 
    {
        return operatorID;
    }
    public void setOperatorTime(Date operatorTime) 
    {
        this.operatorTime = operatorTime;
    }

    public Date getOperatorTime() 
    {
        return operatorTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("reportTemID", getReportTemID())
            .append("reportTemName", getReportTemName())
            .append("reportTemURL", getReportTemURL())
            .append("LaboratoryID", getLaboratoryID())
            .append("operatorID", getOperatorID())
            .append("operatorTime", getOperatorTime())
            .append("remark", getRemark())
            .toString();
    }
}
