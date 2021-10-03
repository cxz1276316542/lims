package com.ruoyi.detection.constant;

/**
 * zhangkui
 */
public class DetectionResultStatus {

    //默认（待初检）
    public final static int DETECTION_DEFAULT = 0;

    //审核通过
    public final static int DETECTION_REVIEW_PASS = 1;

    //审核失败(待复检)
    public final static int DETECTION_REVIEW_FAIL = 2;

    //检测提交审核状态(初审)
    public final static int DETECTION_SUBMIT_ONE = 3;

    //检测提交审核状态（复审）
    public final static int DETECTION_SUBMIT_TWO = 4;

    //未选中
    public final static int DETECTION_NOCHOSEN =0;

    //选中
    public final static int DETECTION_ISCHOSEN =1;



}
