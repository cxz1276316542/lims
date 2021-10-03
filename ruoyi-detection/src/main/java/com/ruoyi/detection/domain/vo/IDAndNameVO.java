package com.ruoyi.detection.domain.vo;

import java.io.Serializable;

/**
 * @author liukun
 * @date 2021/8/29
 */
public class IDAndNameVO implements Serializable {
    private static final long serialVersionUID = -1013744260772536116L;
    private Long ID;
    private String name;

    @Override
    public String toString() {
        return "IDAndNameVO{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                '}';
    }

    public IDAndNameVO() {
    }

    public IDAndNameVO(Long ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
