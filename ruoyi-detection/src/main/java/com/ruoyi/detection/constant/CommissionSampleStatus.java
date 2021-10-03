package com.ruoyi.detection.constant;

/**
 * @author liukun
 * @date 2021/8/5
 */
public class CommissionSampleStatus {
    /**
     *委托保存（01）默认
     * 委托修改（02）
     * 委托退出（00）
     * 业务保存（11）默认
     * 业务提交（12）
     * 退回修改（10）
     * 受理审核通过（21）
     * 受理审核不通过（20）
     */
    /**委托保存（01）默认*/
    public final static int COMMISSION_SAVE = 1;

    /**委托修改（02）*/
    public final static int COMMISSION_EDIT = 2;

    /**委托修改（00）*/
    public final static int COMMISSION_EXIT = 2;

    /**业务保存（11）默认*/
    public final static int BUSINESS_SAVE = 11;

    /**业务提交（12）*/
    public final static int BUSINESS_SUBMIT = 12;

    /**退回修改（10）*/
    public final static int BUSINESS_EDIT = 10;

    /**受理审核通过（21）*/
    public final static int REVIEW_PASS = 21;

    /**受理审核不通过（20）*/
    public final static int REVIEW_NOT_PASS = 20;

}
