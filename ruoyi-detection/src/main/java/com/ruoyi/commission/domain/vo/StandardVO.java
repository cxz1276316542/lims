package com.ruoyi.commission.domain.vo;

public class StandardVO {

    private int id;
    private String standardCode;

    public StandardVO() {
    }

    public StandardVO(Integer id, String standardCode) {
        this.id = id;
        this.standardCode = standardCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStandardCode() {
        return standardCode;
    }

    public void setStandardCode(String standardCode) {
        this.standardCode = standardCode;
    }
}
