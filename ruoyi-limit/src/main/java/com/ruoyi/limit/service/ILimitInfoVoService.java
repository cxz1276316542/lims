package com.ruoyi.limit.service;

import com.ruoyi.limit.domain.*;
import com.ruoyi.limit.domain.vo.LimitInfoVo;

import java.util.List;

/**
 * 限量库详细信息Service接口
 * 
 * @author wlf
 * @date 2021-07-21
 */
public interface ILimitInfoVoService
{
    /**
     * 导入限量库详细信息
     *
     * @param limitInfoVoList 限量库数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 操作结果
     */
    public String importLimit(List<LimitInfoVo> limitInfoVoList, boolean isUpdateSupport);

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
     * 查询限量库详细信息列表
     * @return 限量库详细信息集合
     */

    public List<LimitInfoVo> selectLimitInfoVoList(LimitInfoVo limitInfoVo);

    /**
     * 根据食品信息查询限量库详细信息列表
     * @return 限量库详细信息集合
     */
    public List<LimitInfoVo> selectLimitInfoVoListByFood(Food food);

    /**
     * 根据农药信息查询限量库详细信息列表
     * @return 限量库详细信息集合
     */
    public List<LimitInfoVo> selectLimitInfoVoListByPesticide(Pesticide pesticide);

    /**
     * 根据检测标准编号查询限量库详细信息集合
     */
    public List<LimitInfoVo> selectLimitInfoVoListByStandard(Standard standard);

}
