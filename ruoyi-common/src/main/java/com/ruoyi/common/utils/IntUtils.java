package com.ruoyi.common.utils;

/**
 * @author zjccc
 * @date 2021/8/5
 * @Email:648629522@qq.com
 */
public class IntUtils {
    /**
     * 判断int数组中是否存在某个值的方法
     * @param Arr 待查找int数组
     * @param a   需要查找的值
     * @return :存在 true :不存在 false
     */
    public static boolean IntArrLookupInt(int[] Arr, int a) {
        for (int i : Arr) {
            if (a == i) {
                return true;
            }
        }
        return false;
    }
}
