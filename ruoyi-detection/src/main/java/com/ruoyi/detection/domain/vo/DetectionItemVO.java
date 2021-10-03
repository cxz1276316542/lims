package com.ruoyi.detection.domain.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author liukun
 * @date 2021/8/6
 */
// 具体的检测项目
public class DetectionItemVO implements Serializable {
    // 检测标准ID
    private Long detectionStandardID;
    // 检品ID
    private Long sampleID;
    // 农药ID
    private Long pesticideID;
    // 检测项目名称
    private String pesticideName;
    // 检测方法list
    private List<IDAndNameVO> methods;
    // 检测员list
    private List<IDAndNameVO> inspectors;
    // 检测方法ID
    private List<Long> detectionMethodIDList;
    // 检测方法IDs
    private String detectionMethodIDs;
    // 默认的检测方法
    private Long detectionMethodID;
    // 最大限量
    private BigDecimal maxResidue;
    // 主检人ID
    private Long mainInspectorID;
    // 副检人ID
    private Long deputyInspectorID;


    public String getPesticideName() {
        return pesticideName;
    }

    public void setPesticideName(String pesticideName) {
        this.pesticideName = pesticideName;
    }

    public List<IDAndNameVO> getMethods() {
        return methods;
    }

    public void setMethods(List<IDAndNameVO> methods) {
        this.methods = methods;
    }

    public List<IDAndNameVO> getInspectors() {
        return inspectors;
    }

    public void setInspectors(List<IDAndNameVO> inspectors) {
        this.inspectors = inspectors;
    }

    public Long getSampleID() {
        return sampleID;
    }

    public void setSampleID(Long sampleID) {
        this.sampleID = sampleID;
    }

    public Long getDetectionStandardID() {
        return detectionStandardID;
    }

    public void setDetectionStandardID(Long detectionStandardID) {
        this.detectionStandardID = detectionStandardID;
    }

    public Long getMainInspectorID() {
        return mainInspectorID;
    }

    public void setMainInspectorID(Long mainInspectorID) {
        this.mainInspectorID = mainInspectorID;
    }

    public Long getDeputyInspectorID() {
        return deputyInspectorID;
    }

    public void setDeputyInspectorID(Long deputyInspectorID) {
        this.deputyInspectorID = deputyInspectorID;
    }

    public Long getDetectionMethodID() {
        return detectionMethodID;
    }

    public void setDetectionMethodID(Long detectionMethodID) {
        this.detectionMethodID = detectionMethodID;
    }

    public Long getPesticideID() {
        return pesticideID;
    }

    public void setPesticideID(Long pesticideID) {
        this.pesticideID = pesticideID;
    }

    public List<Long> getDetectionMethodIDList() {
        return detectionMethodIDList;
    }

    public void setDetectionMethodIDList(List<Long> detectionMethodIDList) {
        this.detectionMethodIDList = detectionMethodIDList;
    }

    public String getDetectionMethodIDs() {
        return detectionMethodIDs;
    }

    public void setDetectionMethodIDs(String detectionMethodIDs) {
        this.detectionMethodIDs = detectionMethodIDs;
    }

    public BigDecimal getMaxResidue() {
        return maxResidue;
    }

    public void setMaxResidue(BigDecimal maxResidue) {
        this.maxResidue = maxResidue;
    }

    @Override
    public String toString() {
        return "DetectionItemVO{" +
                "detectionStandardID=" + detectionStandardID +
                ", sampleID=" + sampleID +
                ", pesticideID=" + pesticideID +
                ", pesticideName='" + pesticideName + '\'' +
                ", methods=" + methods +
                ", inspectors=" + inspectors +
                ", detectionMethodIDList=" + detectionMethodIDList +
                ", detectionMethodIDs='" + detectionMethodIDs + '\'' +
                ", detectionMethodID=" + detectionMethodID +
                ", maxResidue=" + maxResidue +
                ", mainInspectorID=" + mainInspectorID +
                ", deputyInspectorID=" + deputyInspectorID +
                '}';
    }
}
