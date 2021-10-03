package com.ruoyi.limit.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.limit.domain.FoodType;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

/**
 * 食品类别管理Mapper接口
 * 
 * @author wlf
 * @date 2021-07-20
 */
public interface FoodTypeMapper 
{
    /**
     * 获取食品类别名称 和 对应 食品类别实体 map
     * 食品类别名称 - 食品类别
     */
    @MapKey("foodTypeName")
    public Map<String, FoodType> selectFoodTypeNameMap();

    /**
     * 查询某条食品类别信息是否存在
     *
     */
    public FoodType selectFoodTypeExist(FoodType foodType);

    /**
     * 查询某食品类别父ID下的所有食品类别ID
     * @param detectionCenterID 检测中心ID
     */
    public Long[] selectFoodTypeIDS(@Param("foodTypeParentID") Long foodTypeParentID, @Param("detectionCenterID") Long detectionCenterID);

    /**
     * 通过食品类别名称获取食品类别ID
     */
    public long selectFoodTypeId(FoodType foodType);

    /**
     * 查询食品类别管理
     * 
     * @param foodTypeID 食品类别管理ID
     * @param detectionCenterID 检测中心ID
     * @return 食品类别管理
     */
    public FoodType selectFoodTypeById(@Param("foodTypeID") Long foodTypeID, @Param("detectionCenterID") Long detectionCenterID);

    /**
     * 查询食品类别管理列表
     * 
     * @param foodType 食品类别管理
     * @return 食品类别管理集合
     */
    public List<FoodType> selectFoodTypeList(FoodType foodType);

    /**
     * 根据食品类别ID查询食品子类别管理列表
     *
     * @param foodTypeParentID 食品类别父ID
     * @param detectionCenterID 检测中心ID
     * @return 食品类别管理集合
     */
    public List<FoodType> selectFoodTypeChildListById(@Param("foodTypeParentID") Long foodTypeParentID, @Param("detectionCenterID") Long detectionCenterID);

    /**
     * 新增食品类别管理
     * 
     * @param foodType 食品类别管理
     * @return 结果
     */
    public int insertFoodType(FoodType foodType);

    /**
     * 修改食品类别管理
     * 
     * @param foodType 食品类别管理
     * @return 结果
     */
    public int updateFoodType(FoodType foodType);

    /**
     * 删除食品类别管理
     * 
     * @param foodTypeID 食品类别管理ID
     * @param detectionCenterID 检测中心ID
     * @return 结果
     */
        public int deleteFoodTypeById(@Param("foodTypeID")Long foodTypeID, @Param("detectionCenterID") Long detectionCenterID);

    /**
     * 批量删除食品类别管理
     * 
     * @param foodTypeIDs 需要删除的数据ID
     * @param detectionCenterID 检测中心ID
     * @return 结果
     */
    public int deleteFoodTypeByIds(@Param("foodTypeIDs")Long[] foodTypeIDs, @Param("detectionCenterID") Long detectionCenterID);
}
