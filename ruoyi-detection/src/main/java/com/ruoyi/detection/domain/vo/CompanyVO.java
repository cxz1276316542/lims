package com.ruoyi.detection.domain.vo;

import java.io.Serializable;

/**
 * @author liukun
 * @date 2021/8/3
 */
public class CompanyVO implements Serializable {

    private static final long serialVersionUID = 6860770899531258073L;
    private Long companyID;
    private String companyAddress;

    public Long getCompanyID() {
        return companyID;
    }

    public void setCompanyID(Long companyID) {
        this.companyID = companyID;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    @Override
    public String toString() {
        return "CompanyVO{" +
                "companyID=" + companyID +
                ", companyAddress='" + companyAddress + '\'' +
                '}';
    }
}
