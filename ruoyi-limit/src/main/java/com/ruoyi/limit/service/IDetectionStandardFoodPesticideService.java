package com.ruoyi.limit.service;

import java.util.List;

import com.ruoyi.limit.domain.*;
import com.ruoyi.limit.domain.vo.LimitInfoVo;

/**
 * 限量库详细信息Service接口
 * 
 * @author wlf
 * @date 2021-07-21
 */
public interface IDetectionStandardFoodPesticideService 
{
    /**
     * 获取食品类别下拉树列表
     *
     * @param food 食品类别信息
     * @return 食品类别详细信息
     */
    public List<FoodType> selectFoodTree(FoodType food);

    /**
     * 构建前端所需要的食品类别树形结构
     * @param foods 食品类别
     * @return 树结构列表
     */
    public List<FoodType> buildFoodTree(List<FoodType> foods);

    /**
     * 构建前端所需要的下拉树结构
     * @param foodTypes 食品类别
     * @return 树结构列表
     */
    public List<FoodTreeSelect> buildFoodTypeTreeSelect(List<FoodType> foodTypes);
    /**
     * 查询限量库详细信息
     * 
     * @param detectionStandardID 限量库详细信息ID
     * @return 限量库详细信息
     */
    public DetectionStandardFoodPesticide selectDetectionStandardFoodPesticideById(Long detectionStandardID);



    /**
     * 查询限量库详细信息列表
     * 
     *
     * @return 限量库详细信息集合
     */
    public List<LimitInfoVo> selectLmitInfoList();

    /**
     * 查询限量库详细信息列表
     * @return 限量库详细信息集合
     */
    public List<LimitInfoVo> selectLmitInfoListByFood(Food food);

    /**
     * 查询限量库详细信息列表
     * @return 限量库详细信息集合
     */
    public List<LimitInfoVo> selectLmitInfoListByPesticide(Pesticide pesticide);

    /**
     * 新增限量库详细信息
     * 
     * @param detectionStandardFoodPesticide 限量库详细信息
     * @return 结果
     */
    public int insertDetectionStandardFoodPesticide(DetectionStandardFoodPesticide detectionStandardFoodPesticide);

    /**
     * 修改限量库详细信息
     * 
     * @param detectionStandardFoodPesticide 限量库详细信息
     * @return 结果
     */
    public int updateDetectionStandardFoodPesticide(DetectionStandardFoodPesticide detectionStandardFoodPesticide);

    /**
     * 批量删除限量库详细信息
     * 
     * @param detectionStandardIDs 需要删除的限量库详细信息ID
     * @return 结果
     */
    public int deleteDetectionStandardFoodPesticideByIds(Long[] detectionStandardIDs);

    /**
     * 删除限量库详细信息信息
     * 
     * @param detectionStandardID 限量库详细信息ID
     * @return 结果
     */
    public int deleteDetectionStandardFoodPesticideById(Long detectionStandardID);
}
