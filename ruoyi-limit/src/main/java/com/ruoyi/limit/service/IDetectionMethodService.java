package com.ruoyi.limit.service;

import java.util.List;
import com.ruoyi.limit.domain.DetectionMethod;

/**
 * 检测方法管理Service接口
 * 
 * @author dlk
 * @date 2021-07-20
 */
public interface IDetectionMethodService 
{
    /**
     * 查询检测方法管理
     * @param detectionMethodID 检测方法管理ID
     * @return 检测方法管理
     */
    public DetectionMethod selectDetectionMethodById(Long detectionMethodID);

    /**
     * 查询检测方法管理列表
     * 
     * @param detectionMethod 检测方法管理
     * @return 检测方法管理集合
     */
    public List<DetectionMethod> selectDetectionMethodList(DetectionMethod detectionMethod);

    /**
     * 新增检测方法管理
     * 
     * @param detectionMethod 检测方法管理
     * @return 结果
     */
    public String insertDetectionMethod(DetectionMethod detectionMethod);

    /**
     * 修改检测方法管理
     * 
     * @param detectionMethod 检测方法管理
     * @return 结果
     */
    public int updateDetectionMethod(DetectionMethod detectionMethod);

    /**
     * 批量删除检测方法管理
     * 
     * @param detectionMethodIDs 需要删除的检测方法管理ID
     * @return 结果
     */
    public int deleteDetectionMethodByIds(Long[] detectionMethodIDs);

    /**
     * 删除检测方法管理信息
     * 
     * @param detectionMethodID 检测方法管理ID
     * @return 结果
     */
    public int deleteDetectionMethodById(Long detectionMethodID);

    /**
     * 导入检测方法管理信息
     *
     * @param detectionMethodList 检测方法管理集合
     * @param isUpdateSupport 是否支持更新
     * @return 结果
     */
    public String importDetectionMethod(List<DetectionMethod> detectionMethodList, Boolean isUpdateSupport);

    /**
     * 数据库是否存在该数据
     * @param detectionMethod 检测方法管理
     * @return 数据库是否存在该数据
     *//*
    public int isExistDetectionMethod(DetectionMethod detectionMethod);*/
}
