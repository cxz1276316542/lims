package com.ruoyi.detection.domain.vo;

import java.io.Serializable;

/**
 * @author liukun
 * @date 2021/9/1
 */
public class ItemAndMethodAndInspectorVO implements Serializable {

    private static final long serialVersionUID = 5689469671627926583L;
    private Long detectionItemID;
    private Long detectionMethodID;
    private Long inspectorID;

    public Long getDetectionItemID() {
        return detectionItemID;
    }

    public void setDetectionItemID(Long detectionItemID) {
        this.detectionItemID = detectionItemID;
    }

    public Long getDetectionMethodID() {
        return detectionMethodID;
    }

    public void setDetectionMethodID(Long detectionMethodID) {
        this.detectionMethodID = detectionMethodID;
    }

    public Long getInspectorID() {
        return inspectorID;
    }

    public void setInspectorID(Long inspectorID) {
        this.inspectorID = inspectorID;
    }

    @Override
    public String toString() {
        return "ItemAndMethodAndInspectorVO{" +
                "detectionItemID=" + detectionItemID +
                ", detectionMethodID=" + detectionMethodID +
                ", inspectorID=" + inspectorID +
                '}';
    }
}
