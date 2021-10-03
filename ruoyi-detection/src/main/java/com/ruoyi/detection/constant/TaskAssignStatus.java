package com.ruoyi.detection.constant;

/**
 * @author zjccc
 * @date 2021/8/11
 * @Email:648629522@qq.com
 */
public class TaskAssignStatus {


    //认领成功
    public final static int ASSIGN_CLIAM_PASS = 1;

    //认领失败
    public final static int ASSIGN_CLIAM_FAIL = 2;

    /**审核通过*/
    public final static int ASSIGN_REVIEW_PASS = 3;

    /**审核不通过*/
    public final static int ASSIGN_REVIEW_FAIL = 4;

    /**审核检测结果不通过退回*/
    public final static int RESULT_REVIEW_FAIL = 5;
}
