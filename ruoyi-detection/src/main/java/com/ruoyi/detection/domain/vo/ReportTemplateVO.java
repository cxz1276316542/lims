package com.ruoyi.detection.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;

import java.util.Date;

public class ReportTemplateVO {

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

    /** 检测中心ID */
    @Excel(name = "检测中心名称")
    private String LaboratoryName;

    /** 检测中心地址 */
    @Excel(name = "检测中心地址")
    private String LaboratoryAddress;

    /** 操作员ID */
    @Excel(name = "操作员ID")
    private Long operatorID;

    @Excel(name = "操作员名称")
    private String  operatorName;

    @Excel(name = "备注")
    private String  remark;


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    /** 操作时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "操作时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date operatorTime;

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

    public String getReportTemURL() {
        return reportTemURL;
    }

    public void setReportTemURL(String reportTemURL) {
        this.reportTemURL = reportTemURL;
    }

    public Long getLaboratoryID() {
        return LaboratoryID;
    }

    public void setLaboratoryID(Long laboratoryID) {
        LaboratoryID = laboratoryID;
    }

    public String getLaboratoryName() {
        return LaboratoryName;
    }

    public void setLaboratoryName(String laboratoryName) {
        LaboratoryName = laboratoryName;
    }

    public String getLaboratoryAddress() {
        return LaboratoryAddress;
    }

    public void setLaboratoryAddress(String laboratoryAddress) {
        LaboratoryAddress = laboratoryAddress;
    }

    public Long getOperatorID() {
        return operatorID;
    }

    public void setOperatorID(Long operatorID) {
        this.operatorID = operatorID;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public Date getOperatorTime() {
        return operatorTime;
    }

    public void setOperatorTime(Date operatorTime) {
        this.operatorTime = operatorTime;
    }

    @Override
    public String toString() {
        return "ReportTemplateVO{" +
                "reportTemID=" + reportTemID +
                ", reportTemName='" + reportTemName + '\'' +
                ", reportTemURL='" + reportTemURL + '\'' +
                ", LaboratoryID=" + LaboratoryID +
                ", LaboratoryName='" + LaboratoryName + '\'' +
                ", LaboratoryAddress='" + LaboratoryAddress + '\'' +
                ", operatorID=" + operatorID +
                ", operatorName='" + operatorName + '\'' +
                ", remark='" + remark + '\'' +
                ", operatorTime=" + operatorTime +
                '}';
    }
}
