package com.ruoyi.detection.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * zhangkui
 */
public class TaskAssginVO extends BaseEntity {

    /** 自增主键 */
    @Excel(name="任务分派自增主键")
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

    /** 优先级
     * 1-正常；2-急；3-加急；4-特急（客户、业务员操作）默认正常
     * */
    @Excel(name = "优先级")
    private Integer priority;


    /** 检测人员名称 */
    @Excel(name = "检测人员名称")
    private String inspectorName;

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
    private Float maxContent;


    /** 成人每公斤摄入量(mg/kg) */
    @Excel(name = "成人每公斤摄入量(mg/kg)")
    private Float adi;


    /** 检测员ID */
    @Excel(name = "检测员ID")
    private Long inspectorID;

    public Long getInspectorID() {
        return inspectorID;
    }

    public void setInspectorID(Long inspectorID) {
        this.inspectorID = inspectorID;
    }

    /** 审核状态(0-提交审核，审核中，1-审核通过，2-审核不通过，3-保存，4-认领失败，5-认领) */

    @Excel(name = "审核状态(0-提交审核，审核中，1-审核通过，2-审核不通过，3-保存，4-认领失败，5-认领)")
    private Integer auditStatus;

    /** 操作时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "操作时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date operationTime;

    /** 备注 */
    @Excel(name = "备注")
    private String remark;

    public Long getTaskAssignID() {
        return taskAssignID;
    }

    public void setTaskAssignID(Long taskAssignID) {
        this.taskAssignID = taskAssignID;
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

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
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
        return maxContent;
    }

    public void setMaxContent(Float maxContent) {
        this.maxContent = maxContent;
    }

    public Float getAdi() {
        return adi;
    }

    public void setAdi(Float adi) {
        this.adi = adi;
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

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "TaskAssginVO{" +
                "taskAssignID=" + taskAssignID +
                ", sampleNumber='" + sampleNumber + '\'' +
                ", taskNumber='" + taskNumber + '\'' +
                ", pesticideName='" + pesticideName + '\'' +
                ", sampleAcademicName='" + sampleAcademicName + '\'' +
                ", priority=" + priority +
                ", inspectorName='" + inspectorName + '\'' +
                ", inspectionRoom='" + inspectionRoom + '\'' +
                ", standard='" + standard + '\'' +
                ", inspectionMethodName='" + inspectionMethodName + '\'' +
                ", maxContent=" + maxContent +
                ", adi=" + adi +
                ", inspectorID=" + inspectorID +
                ", auditStatus=" + auditStatus +
                ", operationTime=" + operationTime +
                ", remark='" + remark + '\'' +
                '}';
    }
}
