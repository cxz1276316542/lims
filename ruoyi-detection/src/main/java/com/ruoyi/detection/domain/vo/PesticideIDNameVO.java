package com.ruoyi.detection.domain.vo;

import java.io.Serializable;

/**
 * @author liukun
 * @date 2021/8/8
 */
public class PesticideIDNameVO implements Serializable {

    private static final long serialVersionUID = -2830191193300955377L;

    // 农药ID
    private Long pesticideID;
    // 农药名称
    private String pesticideName;

    @Override
    public String toString() {
        return "PesticideIDNameVO{" +
                "pesticideID=" + pesticideID +
                ", pesticideName='" + pesticideName + '\'' +
                '}';
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
}
