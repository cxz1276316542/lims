package com.ruoyi.review.constant;

/**
 * @author zjccc
 * @date 2021/8/1
 * @Email:648629522@qq.com
 * 审核流转位置标注字段
 */
public class ReviewCirculation {

    //对检品审核
    /** 回转到业务员 */
    public static final int SAMPLE_TO_SALESMAN = 1;

    /** 回转到客户 */
    public static final int SAMPLE_TO_CUSTOMER = 2;

    /** 回转到客户 */
    public static final int SAMPLE_TO_CUSTOMER_MODIFY = 3;

    //对检测审核
    /** 回转到检测员 */
    public static final int RESULT_TO_INSPECTOR = 1;

    /** 回转到任务分派 */
    public static final int RESULT_TO_ASSIGN = 2;

    //对报告审核
    /** 回转到报告撰写员 */
    public static final int REPORT_TO_WRITER = 1;

    /** 回转到检测员 */
    public static final int REPORT_TO_INSPECTOR = 2;

    /** 回转到任务分派 */
    public static final int REPORT_TO_ASSIGN = 3;

}
