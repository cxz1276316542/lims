package com.ruoyi.limit.service;

import java.util.List;
import com.ruoyi.limit.domain.Food;

/**
 * 食品管理Service接口
 * 
 * @author wlf
 * @date 2021-07-20
 */
public interface IFoodService 
{
    /**
     * 导入检测农药信息
     */
    public String importFood(List<Food> foodList, Boolean isUpdateSupport);


    /**
     * 查询食品管理
     * 
     * @param foodID 食品管理ID
     * @return 食品管理
     */
    public Food selectFoodById(Long foodID);

    /**
     * 查询食品管理列表
     * 
     * @param food 食品管理
     * @return 食品管理集合
     */
    public List<Food> selectFoodList(Food food);

    /**
     * 新增食品管理
     * 
     * @param food 食品管理
     * @return 结果
     */
    public String insertFood(Food food);

    /**
     * 修改食品管理
     * 
     * @param food 食品管理
     * @return 结果
     */
    public int updateFood(Food food);

    /**
     * 批量删除食品管理
     * 
     * @param foodTypeIDs 需要删除的食品类别ID
     * @return 结果
     */
    public int deleteFoodByTypeIds(Long[] foodTypeIDs);

    /**
     * 删除食品管理信息
     * 
     * @param foodTypeID 食品管理ID
     * @return 结果
     */
    public int deleteFoodByTypeId(Long foodTypeID);

    /**
     * 生成食品类别字段 例如谷物/麦类/小麦
     */
    public String generateFoodTypeStr(long foodTypeID);

}
