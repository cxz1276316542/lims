package com.ruoyi.limit.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.limit.domain.Food;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

/**
 * 食品管理Mapper接口
 * 
 * @author wlf
 * @date 2021-07-20
 */
public interface FoodMapper 
{
    /**
     * 获取食品名称 和 对应 食品实体 map
     * 食品学名 - 食品类别ID
     */
    @MapKey("foodName")
    public Map<String, Food> selectFoodIDNameMap(@Param("detectionCenterID") Long detectionCenterID);
    /**
     * 查询某条食品信息是否存在
     */
    public Food selectFoodExist(Food food);

    /**
     * 查询某条食品信息是否发生修改
     * @param food 食品信息
     */
    public Food selectFoodChange(Food food);

    /**
     * 查询食品管理
     * @param foodID 食品管理ID
     * @param detectionCenterID 检测中心ID
     * @return 食品管理
     */
    public Food selectFoodById(@Param("foodID") Long foodID, @Param("detectionCenterID") Long detectionCenterID);

    /**
     * 查询食品管理
     *
     * @param foodTypeID 食品类别管理ID
     * @param detectionCenterID 检测中心ID
     * @return 食品管理
     */
    public Food selectFoodByFoodTypeId(@Param("foodTypeID") Long foodTypeID, @Param("detectionCenterID") Long detectionCenterID);

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
    public int insertFood(Food food);

    /**
     * 修改食品管理
     * 
     * @param food 食品管理
     * @return 结果
     */
    public int updateFood(Food food);

    /**
     * 删除食品管理
     * 
     * @param foodTypeID 食品管理ID
     * @param detectionCenterID 检测中心ID
     * @return 结果
     */
    public int deleteFoodById(@Param("foodTypeID") Long foodTypeID, @Param("detectionCenterID")Long detectionCenterID);

    /**
     * 批量删除食品管理
     * 
     * @param foodTypeIDs 需要删除的数据ID
     * @param detectionCenterID 检测中心ID
     * @return 结果
     */
    public int deleteFoodByIds(@Param("foodTypeIDs") Long[] foodTypeIDs, @Param("detectionCenterID")Long detectionCenterID);
}
