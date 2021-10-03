package com.ruoyi.limit.service.impl;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.limit.domain.Food;
import com.ruoyi.limit.mapper.FoodMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.limit.mapper.FoodTypeMapper;
import com.ruoyi.limit.domain.FoodType;
import com.ruoyi.limit.service.IFoodTypeService;

import javax.annotation.Resource;

/**
 * 食品类别管理Service业务层处理
 * 
 * @author wlf
 * @date 2021-07-20
 */
@Service
public class FoodTypeServiceImpl implements IFoodTypeService 
{
    @Autowired
    private FoodTypeMapper foodTypeMapper;
    @Autowired
    private FoodMapper foodMapper;
    @Resource
    private FoodServiceImpl foodService;
    /**
     * 查询食品类别管理
     * 
     * @param foodTypeID 食品类别管理ID
     * @return 食品类别管理
     */
    @Override
    public FoodType selectFoodTypeById(Long foodTypeID)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        FoodType foodType = foodTypeMapper.selectFoodTypeById(foodTypeID,detectionCenterID);
        String str = foodService.generateFoodTypeStr(foodType.getFoodTypeID());
        foodType.setFoodTypeStr(str);
        return foodType;
    }

    /**
     * 查询食品类别管理列表
     * 
     * @param foodType 食品类别管理
     * @return 食品类别管理
     */
    @Override
    public List<FoodType> selectFoodTypeList(FoodType foodType)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        foodType.setDetectionCenterID(detectionCenterID);
        List<FoodType> list = foodTypeMapper.selectFoodTypeList(foodType);
        for(FoodType foodType1:list){
            String str = foodService.generateFoodTypeStr(foodType1.getFoodTypeID());
            foodType1.setFoodTypeStr(str);
        }
        return list;
    }

    /**
     * 新增食品类别管理
     * 
     * @param foodType 食品类别管理
     * @return 结果
     */
    @Override
    public String insertFoodType(FoodType foodType)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        foodType.setDetectionCenterID(detectionCenterID);
        FoodType isExist = foodTypeMapper.selectFoodTypeExist(foodType);
        if(StringUtils.isNull(isExist)){
            int ret = foodTypeMapper.insertFoodType(foodType);
            return ret > 0 ? "操作成功":"操作失败";
        }
        return "重复插入";
    }

    /**
     * 修改食品类别管理
     * 
     * @param foodType 食品类别管理
     * @return 结果
     */
    @Override
    public int updateFoodType(FoodType foodType)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();

        List<FoodType> foodTypeList = foodTypeMapper.selectFoodTypeChildListById(foodType.getFoodTypeID(),detectionCenterID);
        if(foodTypeList.size()==0){
            Food food2 = foodMapper.selectFoodByFoodTypeId(foodType.getFoodTypeID(), detectionCenterID);
            food2.setIsValid(foodType.getIsValid());
            foodMapper.updateFood(food2);
        }
        foodTypeMapper.updateFoodType(foodType);
        for (FoodType type:foodTypeList) {
            Food food = foodMapper.selectFoodByFoodTypeId(type.getFoodTypeID(), detectionCenterID);
            if(StringUtils.isNotNull(food)){
                food.setIsValid(foodType.getIsValid());
                foodMapper.updateFood(food);
            }
            type.setIsValid(foodType.getIsValid());
            updateFoodType(type);
        }
        return 1;
    }

    /**
     * 批量删除食品类别管理
     * 
     * @param foodTypeIDs 需要删除的食品类别管理ID
     * @return 结果
     */
    @Override
    public int deleteFoodTypeByIds(Long[] foodTypeIDs)
    {
        return deleteFoodAndType(foodTypeIDs);
    }

    public int deleteFoodAndType(Long[] foodTypeIDs){
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        for(Long foodTypeID:foodTypeIDs){
            Long[] foodIDS = foodTypeMapper.selectFoodTypeIDS(foodTypeID,detectionCenterID);
            foodTypeMapper.deleteFoodTypeById(foodTypeID,detectionCenterID);
            Food food = new Food(foodTypeID,null,null,null);
            Food isExist = foodMapper.selectFoodExist(food);
            if(!StringUtils.isNull(isExist))
            {
                foodMapper.deleteFoodById(food.getFoodTypeID(), detectionCenterID);
            }
            foodTypeMapper.deleteFoodTypeById(foodTypeID,detectionCenterID);
            deleteFoodAndType(foodIDS);
        }
        return 1;
    }

    /**
     * 删除食品类别管理信息
     * 
     * @param foodTypeID 食品类别管理ID
     * @return 结果
     */
    @Override
    public int deleteFoodTypeById(Long foodTypeID)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        return foodTypeMapper.deleteFoodTypeById(foodTypeID,detectionCenterID);
    }
}
