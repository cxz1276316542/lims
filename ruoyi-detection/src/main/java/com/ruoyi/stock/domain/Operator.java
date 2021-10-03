package com.ruoyi.stock.domain;

import com.ruoyi.common.annotation.Excel;

/**
 * @author zjccc
 * @date 2021/8/8
 * @Email:648629522@qq.com
 */
public class Operator {
    /** 操作员ID */
    private Long operatorID;

    /** 操作员名称 */
    private String operatorName;

    public Long getOperatorID() {
        return operatorID;
    }

    public void setOperatorID(Long operatorID) {
        this.operatorID = operatorID;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }
}
