package com.ruoyi.stock.constant;

/**
 * @author zjccc
 * @date 2021/7/27
 * @Email:648629522@qq.com
 */
public class StockConstants {

    /** 预警天数 */
    public static final int WARNING_DAY = 3;

    /** 出入库类别-入库 */
    public static final int CATEGORY_STOCK_IN = 1;

    /** 出入库类别-出库 */
    public static final int CATEGORY_DELIVERY = 2;

    /** 入库类型-登记入库 */
    public static final int TYPE_REGISTER = 1;

    /** 入库类别-回收 */
    public static final int TYPE_RECYCLE = 2;

    /** 出库类别-领用 */
    public static final int TYPE_CLAIM = 3;

    /** 出库类别-销毁 */
    public static final int TYPE_DESTROY = 4;

    /** 出库类别-退样 */
    public static final int TYPE_RETURN = 5;

    /** 样品库状态-有效 */
    public static final int BANK_STATUS_VALID = 1;

    /** 样品库状态-无效 */
    public static final int BANK_STATUS_INVALID = 2;
}
