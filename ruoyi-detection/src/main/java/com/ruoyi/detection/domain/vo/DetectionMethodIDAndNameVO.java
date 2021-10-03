package com.ruoyi.detection.domain.vo;

import java.io.Serializable;

/**
 * @author liukun
 * @date 2021/8/8
 */
public class DetectionMethodIDAndNameVO implements Serializable {

    private static final long serialVersionUID = -7937486167143583874L;
    // 检测方法ID
    private Long detectionMethodID;
    // 检测方法名称
    private String detectionMethodName;

    @Override
    public String toString() {
        return "DetectionMethodIDAndNameVO{" +
                "detectionMethodID=" + detectionMethodID +
                ", detectionMethodName='" + detectionMethodName + '\'' +
                '}';
    }

    public Long getDetectionMethodID() {
        return detectionMethodID;
    }

    public void setDetectionMethodID(Long detectionMethodID) {
        this.detectionMethodID = detectionMethodID;
    }

    public String getDetectionMethodName() {
        return detectionMethodName;
    }

    public void setDetectionMethodName(String detectionMethodName) {
        this.detectionMethodName = detectionMethodName;
    }
}
