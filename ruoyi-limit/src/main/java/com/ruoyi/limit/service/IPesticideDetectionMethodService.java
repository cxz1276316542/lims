package com.ruoyi.limit.service;

import java.util.List;
import com.ruoyi.limit.domain.PesticideDetectionMethod;

/**
 * 检测项目-检测方法Service接口
 * 
 * @author wlf
 * @date 2021-08-28
 */
public interface IPesticideDetectionMethodService
{
    /**
     * 查询检测项目-检测方法
     * 
     * @param pesticideDetectionMethodID 检测项目-检测方法ID
     * @return 检测项目-检测方法
     */
    public PesticideDetectionMethod selectPesticideDetectionMethodById(Long pesticideDetectionMethodID);

    /**
     * 查询检测项目-检测方法列表
     * 
     * @param pesticideDetectionMethod 检测项目-检测方法
     * @return 检测项目-检测方法集合
     */
    public List<PesticideDetectionMethod> selectPesticideDetectionMethodList(PesticideDetectionMethod pesticideDetectionMethod);

    /**
     * 新增检测项目-检测方法
     * 
     * @param pesticideDetectionMethod 检测项目-检测方法
     * @return 结果
     */
    public String insertPesticideDetectionMethod(PesticideDetectionMethod pesticideDetectionMethod);

    /**
     * 修改检测项目-检测方法
     * 
     * @param pesticideDetectionMethod 检测项目-检测方法
     * @return 结果
     */
    public int updatePesticideDetectionMethod(PesticideDetectionMethod pesticideDetectionMethod);

    /**
     * 批量删除检测项目-检测方法
     * 
     * @param pesticideDetectionMethodIDs 需要删除的检测项目-检测方法ID
     * @return 结果
     */
    public int deletePesticideDetectionMethodByIds(Long[] pesticideDetectionMethodIDs);

    /**
     * 删除检测项目-检测方法信息
     * 
     * @param pesticideDetectionMethodID 检测项目-检测方法ID
     * @return 结果
     */
    public int deletePesticideDetectionMethodById(Long pesticideDetectionMethodID);
}
