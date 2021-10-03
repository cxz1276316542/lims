package com.ruoyi.limit.service;

import java.util.List;

import com.ruoyi.limit.domain.FoodType;

/**
 * 食品类别管理Service接口
 * 
 * @author wlf
 * @date 2021-07-20
 */
public interface IFoodTypeService 
{
    /**
     * 查询食品类别管理
     * 
     * @param foodTypeID 食品类别管理ID
     * @return 食品类别管理
     */
    public FoodType selectFoodTypeById(Long foodTypeID);

    /**
     * 查询食品类别管理列表
     * 
     * @param foodType 食品类别管理
     * @return 食品类别管理集合
     */
    public List<FoodType> selectFoodTypeList(FoodType foodType);


    /**
     * 新增食品类别管理
     * 
     * @param foodType 食品类别管理
     * @return 结果
     */
    public String insertFoodType(FoodType foodType);

    /**
     * 修改食品类别管理
     * 
     * @param foodType 食品类别管理
     * @return 结果
     */
    public int updateFoodType(FoodType foodType);

    /**
     * 批量删除食品类别管理
     * 
     * @param foodTypeIDs 需要删除的食品类别管理ID
     * @return 结果
     */
    public int deleteFoodTypeByIds(Long[] foodTypeIDs);

    /**
     * 删除食品类别管理信息
     * 
     * @param foodTypeID 食品类别管理ID
     * @return 结果
     */
    public int deleteFoodTypeById(Long foodTypeID);
}
