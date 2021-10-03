package com.ruoyi.limit.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 检测员-检测项目对象 xlk_jcy_jcxm
 * 
 * @author dlk
 * @date 2021-08-28
 */
public class InspectorPesticide extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long inspectorPesticideID;

    /** 检测员ID */
    @Excel(name = "检测员ID")
    private Long inspectorID;

    /** 检测员名字 */
    @Excel(name = "检测员名字")
    private String inspectorName;

    /** 检测项目ID */
    @Excel(name = "检测项目ID")
    private Long pesticideID;

    /** 检测项目名字 */
    @Excel(name = "检测项目名字")
    private String pesticideName;

    /** 检测中心ID */
    @Excel(name = "检测中心ID")
    private Long detectionCenterID;

    /** 数据有效性标识 0-无效 1-有效 2-绑定 */
    @Excel(name = "数据有效性标识 0-无效 1-有效 2-绑定")
    private Integer isValid;

    public InspectorPesticide() {
    }

    public Long getInspectorPesticideID() {
        return inspectorPesticideID;
    }

    public void setInspectorPesticideID(Long inspectorPesticideID) {
        this.inspectorPesticideID = inspectorPesticideID;
    }

    public Long getInspectorID() {
        return inspectorID;
    }

    public void setInspectorID(Long inspectorID) {
        this.inspectorID = inspectorID;
    }

    public String getInspectorName() {
        return inspectorName;
    }

    public void setInspectorName(String inspectorName) {
        this.inspectorName = inspectorName;
    }

    public Long getPesticideID() {
        return pesticideID;
    }

    public void setPesticideID(Long pesticideID) {
        this.pesticideID = pesticideID;
    }

    public String getPesticideName() {
        return pesticideName;
    }

    public void setPesticideName(String pesticideName) {
        this.pesticideName = pesticideName;
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
        return "InspectorPesticide{" +
                "inspectorPesticideID=" + inspectorPesticideID +
                ", inspectorID=" + inspectorID +
                ", inspectorName='" + inspectorName + '\'' +
                ", pesticideID=" + pesticideID +
                ", pesticideName='" + pesticideName + '\'' +
                ", detectionCenterID=" + detectionCenterID +
                ", isValid=" + isValid +
                '}';
    }
}
