package com.ruoyi.detection.domain.vo;

import java.io.Serializable;

/**
 * @author liukun
 * @date 2021/8/3
 */
public class MeasureUnitVO implements Serializable {

    private static final long serialVersionUID = 7130414515389667015L;
    private Long measureID;
    private String measureName;

    @Override
    public String toString() {
        return "MeasureUnitVO{" +
                "measureID=" + measureID +
                ", measureName='" + measureName + '\'' +
                '}';
    }

    public Long getMeasureID() {
        return measureID;
    }

    public void setMeasureID(Long measureID) {
        this.measureID = measureID;
    }

    public String getMeasureName() {
        return measureName;
    }

    public void setMeasureName(String measureName) {
        this.measureName = measureName;
    }
}
