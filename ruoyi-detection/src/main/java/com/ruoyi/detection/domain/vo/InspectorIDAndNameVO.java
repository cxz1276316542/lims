package com.ruoyi.detection.domain.vo;

import java.io.Serializable;

/**
 * @author liukun
 * @date 2021/8/8
 */
public class InspectorIDAndNameVO implements Serializable {

    private static final long serialVersionUID = 3259200716659705923L;
    // 检测员ID
    private Long inspectorID;
    // 检测员名称
    private String inspectorName;

    @Override
    public String toString() {
        return "InspectorIDAndNameVO{" +
                "inspectorID=" + inspectorID +
                ", inspectorName='" + inspectorName + '\'' +
                '}';
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
}
