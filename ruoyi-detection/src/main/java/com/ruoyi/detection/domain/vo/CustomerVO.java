package com.ruoyi.detection.domain.vo;

import java.io.Serializable;

public class CustomerVO implements Serializable {
    private Long customerID;
    private String name;

    public Long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CustomerVO{" +
                "customerID=" + customerID +
                ", name='" + name + '\'' +
                '}';
    }
}
