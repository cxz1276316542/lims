package com.ruoyi.detection.domain.vo;

import com.ruoyi.common.annotation.Excel;

public class TemplateVO {

    /** 报告模板ID */
    @Excel(name = "报告模板ID")
    private Long reportTemID;

    /** 报告模板名称 */
    @Excel(name = "报告模板名称")
    private String reportTemName;

    public Long getReportTemID() {
        return reportTemID;
    }

    public void setReportTemID(Long reportTemID) {
        this.reportTemID = reportTemID;
    }

    public String getReportTemName() {
        return reportTemName;
    }

    public void setReportTemName(String reportTemName) {
        this.reportTemName = reportTemName;
    }

    @Override
    public String toString() {
        return "TemplateVO{" +
                "reportTemID=" + reportTemID +
                ", reportTemName='" + reportTemName + '\'' +
                '}';
    }
}
