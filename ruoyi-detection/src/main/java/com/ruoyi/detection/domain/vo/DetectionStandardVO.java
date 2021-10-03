package com.ruoyi.detection.domain.vo;

import java.io.Serializable;

/**
 * @author liukun
 * @date 2021/7/22
 */
public class DetectionStandardVO implements Serializable {

    private static final long serialVersionUID = 8318524210242011378L;

    private Long detectionStandardID;
    private String detectionNumber;

    public Long getDetectionStandardID() {
        return detectionStandardID;
    }

    public void setDetectionStandardID(Long detectionStandardID) {
        this.detectionStandardID = detectionStandardID;
    }

    public String getDetectionNumber() {
        return detectionNumber;
    }

    public void setDetectionNumber(String detectionNumber) {
        this.detectionNumber = detectionNumber;
    }

    public DetectionStandardVO(Long detectionStandardID, String detectionNumber) {
        this.detectionStandardID = detectionStandardID;
        this.detectionNumber = detectionNumber;
    }

    @Override
    public String toString() {
        return "DetectionStandardVO{" +
                "detectionStandardID=" + detectionStandardID +
                ", detectionNumber='" + detectionNumber + '\'' +
                '}';
    }
}
