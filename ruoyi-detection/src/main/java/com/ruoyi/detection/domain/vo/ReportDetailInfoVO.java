package com.ruoyi.detection.domain.vo;

import java.math.BigDecimal;

/**
 * @author liukun
 * @date 2021/5/21
 */
public class ReportDetailInfoVO {
    private String detectionItem;
    private BigDecimal detectionResult;
    private Float  maxContent;
    private Boolean judgmentResult;
    private String detectionMethod;

    public ReportDetailInfoVO() {
    }

    public ReportDetailInfoVO(String detectionItem, BigDecimal detectionResult, Float maxContent, Boolean judgmentResult, String detectionMethod) {
        this.detectionItem = detectionItem;
        this.detectionResult = detectionResult;
        this.maxContent = maxContent;
        this.judgmentResult = judgmentResult;
        this.detectionMethod = detectionMethod;
    }

    @Override
    public String toString() {
        return "ReportDetailInfoVO{" +
                "detectionItem='" + detectionItem + '\'' +
                ", detectionResult=" + detectionResult +
                ", maxContent=" + maxContent +
                ", judgmentResult=" + judgmentResult +
                ", detectionMethod='" + detectionMethod + '\'' +
                '}';
    }

    public String getDetectionItem() {
        return detectionItem;
    }

    public void setDetectionItem(String detectionItem) {
        this.detectionItem = detectionItem;
    }

    public BigDecimal getDetectionResult() {
        return detectionResult;
    }

    public void setDetectionResult(BigDecimal detectionResult) {
        this.detectionResult = detectionResult;
    }

    public Float getMaxContent() {
        return maxContent;
    }

    public void setMaxContent(Float maxContent) {
        this.maxContent = maxContent;
    }

    public Boolean getJudgmentResult() {
        return judgmentResult;
    }

    public void setJudgmentResult(Boolean judgmentResult) {
        this.judgmentResult = judgmentResult;
    }

    public String getDetectionMethod() {
        return detectionMethod;
    }

    public void setDetectionMethod(String detectionMethod) {
        this.detectionMethod = detectionMethod;
    }
}
