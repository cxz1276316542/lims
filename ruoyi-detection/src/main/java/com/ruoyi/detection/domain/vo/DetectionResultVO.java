package com.ruoyi.detection.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

public class DetectionResultVO extends BaseEntity {


    /** 项目检测数据记录ID */
    @Excel(name = "项目检测数据记录ID")
    private Long detectionResultID;

    /** 任务分派ID */
    @Excel(name = "任务分派ID")
    private Long taskAssignID;

    /** 样品编号 */
    @Excel(name = "样品编号")
    private String sampleNumber;

    /** 检测任务单编号 */
    @Excel(name = "检测任务单编号")
    private String taskNumber;

    /** 检测项目名称 */
    @Excel(name = "检测项目名称")
    private String pesticideName;

    /** 样品学名 */
    @Excel(name = "样品学名")
    private String sampleAcademicName;

    /** 样品单位 */
    @Excel(name = "样品单位")
    private String sampleUnit;

    /** 优先级
     * 1-正常；2-急；3-加急；4-特急（客户、业务员操作）默认正常
     * */
    @Excel(name = "优先级")
    private Integer priority;


    /** 检测人员名称 */
    @Excel(name = "检测人员名称")
    private String inspectorName;

    /** 检测员ID */
    @Excel(name = "检测员ID")
    private Long inspectorID;

    /** 检测室 */
    @Excel(name = "检测室")
    private String inspectionRoom;

    /** 检测标准 */
    @Excel(name = "检测标准")
    private String standard;

    /** 检测方法 */
    @Excel(name = "检测方法")
    private String inspectionMethodName;

    /** 最大含量标准 */
    @Excel(name = "最大含量标准")
    private Float MaxContent;


    /** 成人每公斤摄入量(mg/kg) */
    @Excel(name = "成人每公斤摄入量(mg/kg)")
    private Float adi;

    /** 检测数据 */
    @Excel(name = "检测数据")
    private BigDecimal detectionData;

    /** 审核状态(0-提交审核，审核中，1-审核通过，2-审核不通过，3-保存) */
    @Excel(name = "审核状态(0-提交审核，审核中，1-审核通过，2-审核不通过，3-保存)")
    private Integer auditStatus;

    /** 操作时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "操作时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date operationTime;

    /** 备注 */
    @Excel(name = "备注")
    private String remark;


    /** 是否被选中生成报告（0-默认，1-选中） */
    @Excel(name = "是否被选中生成报告（0-默认（未选中），1-选中）")
    private Integer isChosen;

    public Integer getIsChosen() {
        return isChosen;
    }

    public void setIsChosen(Integer isChosen) {
        this.isChosen = isChosen;
    }


    public Long getTaskAssignID() {
        return taskAssignID;
    }

    public void setTaskAssignID(Long taskAssignID) {
        this.taskAssignID = taskAssignID;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getDetectionResultID() {
        return detectionResultID;
    }

    public void setDetectionResultID(Long detectionResultID) {
        this.detectionResultID = detectionResultID;
    }

    public String getSampleNumber() {
        return sampleNumber;
    }

    public void setSampleNumber(String sampleNumber) {
        this.sampleNumber = sampleNumber;
    }

    public String getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(String taskNumber) {
        this.taskNumber = taskNumber;
    }

    public String getPesticideName() {
        return pesticideName;
    }

    public void setPesticideName(String pesticideName) {
        this.pesticideName = pesticideName;
    }

    public String getSampleAcademicName() {
        return sampleAcademicName;
    }

    public void setSampleAcademicName(String sampleAcademicName) {
        this.sampleAcademicName = sampleAcademicName;
    }

    public String getInspectorName() {
        return inspectorName;
    }

    public void setInspectorName(String inspectorName) {
        this.inspectorName = inspectorName;
    }

    public String getInspectionRoom() {
        return inspectionRoom;
    }

    public void setInspectionRoom(String inspectionRoom) {
        this.inspectionRoom = inspectionRoom;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getInspectionMethodName() {
        return inspectionMethodName;
    }

    public void setInspectionMethodName(String inspectionMethodName) {
        this.inspectionMethodName = inspectionMethodName;
    }

    public Float getMaxContent() {
        return MaxContent;
    }

    public void setMaxContent(Float maxContent) {
        MaxContent = maxContent;
    }

    public Float getAdi() {
        return adi;
    }

    public void setAdi(Float adi) {
        this.adi = adi;
    }

    public BigDecimal getDetectionData() {
        return detectionData;
    }

    public void setDetectionData(BigDecimal detectionData) {
        this.detectionData = detectionData;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getSampleUnit() {
        return sampleUnit;
    }

    public void setSampleUnit(String sampleUnit) {
        this.sampleUnit = sampleUnit;
    }


    public Long getInspectorID() {
        return inspectorID;
    }

    public void setInspectorID(Long inspectorID) {
        this.inspectorID = inspectorID;
    }

    @Override
    public String toString() {
        return "DetectionResultVO{" +
                "detectionResultID=" + detectionResultID +
                ", taskAssignID=" + taskAssignID +
                ", sampleNumber='" + sampleNumber + '\'' +
                ", taskNumber='" + taskNumber + '\'' +
                ", pesticideName='" + pesticideName + '\'' +
                ", sampleAcademicName='" + sampleAcademicName + '\'' +
                ", sampleUnit='" + sampleUnit + '\'' +
                ", priority=" + priority +
                ", inspectorName='" + inspectorName + '\'' +
                ", inspectorID=" + inspectorID +
                ", inspectionRoom='" + inspectionRoom + '\'' +
                ", standard='" + standard + '\'' +
                ", inspectionMethodName='" + inspectionMethodName + '\'' +
                ", MaxContent=" + MaxContent +
                ", adi=" + adi +
                ", detectionData=" + detectionData +
                ", auditStatus=" + auditStatus +
                ", operationTime=" + operationTime +
                ", remark='" + remark + '\'' +
                ", isChosen=" + isChosen +
                '}';
    }
}
