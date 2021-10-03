package com.ruoyi.detection.domain.vo;

import java.util.List;

public class ReportReviceVO {


    private List<DetectionResultVO> detectionResultVOList;
    private Long inspectorId;



    public List<DetectionResultVO> getDetectionResultVOList() {
        return detectionResultVOList;
    }

    public void setDetectionResultVOList(List<DetectionResultVO> detectionResultVOList) {
        this.detectionResultVOList = detectionResultVOList;
    }

    public Long getInspectorId() {
        return inspectorId;
    }

    public void setInspectorId(Long inspectorId) {
        this.inspectorId = inspectorId;
    }

    @Override
    public String toString() {
        return "ReportReviceVO{" +
                "detectionResultVOList=" + detectionResultVOList +
                ", inspectorId=" + inspectorId +
                '}';
    }
}
