package com.ruoyi.detection.domain.vo;

import java.io.Serializable;

//zhangkui
public class LaboratoryVO implements Serializable {

    private Long laboratoryID;
    private String laboratoryName;

    public Long getLaboratoryID() {
        return laboratoryID;
    }

    public void setLaboratoryID(Long laboratoryID) {
        this.laboratoryID = laboratoryID;
    }

    public String getLaboratoryName() {
        return laboratoryName;
    }

    public void setLaboratoryName(String laboratoryName) {
        this.laboratoryName = laboratoryName;
    }

    @Override
    public String toString() {
        return "LaboratoryVO{" +
                "laboratoryID=" + laboratoryID +
                ", laboratoryName='" + laboratoryName + '\'' +
                '}';
    }
}
