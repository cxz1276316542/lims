package com.ruoyi.detection.domain.vo;

import com.ruoyi.detection.domain.CommissionSample;

import java.io.Serializable;
import java.util.List;

/**
 * @author liukun
 * @date 2021/8/4
 */
public class CommissionRegister implements Serializable {


    private static final long serialVersionUID = -2446716513221290983L;

    private Long customerID;
    private Long companyID;
    private String receiptAddress;
    private List<CommissionSample> sampleList;

    public Long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    public Long getCompanyID() {
        return companyID;
    }

    public void setCompanyID(Long companyID) {
        this.companyID = companyID;
    }

    public String getReceiptAddress() {
        return receiptAddress;
    }

    public void setReceiptAddress(String receiptAddress) {
        this.receiptAddress = receiptAddress;
    }

    public List<CommissionSample> getSampleList() {
        return sampleList;
    }

    public void setSampleList(List<CommissionSample> sampleList) {
        this.sampleList = sampleList;
    }

    @Override
    public String toString() {
        return "CommissionRegister{" +
                "customerID=" + customerID +
                ", companyID=" + companyID +
                ", receiptAddress='" + receiptAddress + '\'' +
                ", sampleList=" + sampleList +
                '}';
    }
}
