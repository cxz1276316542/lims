package com.ruoyi.limit.service.impl;

import java.util.*;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.limit.domain.FoodType;
import com.ruoyi.limit.domain.SamplePesticide;
import com.ruoyi.limit.mapper.FoodTypeMapper;
import com.ruoyi.limit.mapper.SamplePesticideMapper;
import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.limit.mapper.FoodMapper;
import com.ruoyi.limit.domain.Food;
import com.ruoyi.limit.service.IFoodService;

/**
 * 食品管理Service业务层处理
 * 
 * @author wlf
 * @date 2021-07-20
 */
@Service
public class FoodServiceImpl implements IFoodService 
{
    private static final Logger log = LoggerFactory.getLogger(FoodServiceImpl.class);
    @Autowired
    private FoodMapper foodMapper;

    @Autowired
    private FoodTypeMapper foodTypeMapper;

    @Autowired
    private SamplePesticideMapper samplePesticideMapper;

    /**
     * 查询食品管理
     * 
     * @param foodID 食品管理ID
     * @return 食品管理
     */
    @Override
    public Food selectFoodById(Long foodID)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();

        Food food = foodMapper.selectFoodById(foodID, detectionCenterID);
        String str = this.generateFoodTypeStr(food.getFoodTypeID());
        food.setFoodTypeStr(str);
        return food;
    }

    /**
     * 查询食品管理列表
     * 
     * @param food 食品管理
     * @return 食品管理
     */
    @Override
    public List<Food> selectFoodList(Food food)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        food.setDetectionCenterID(detectionCenterID);
        List<Food> list = foodMapper.selectFoodList(food);

        //食品类别ID和食品类别层级Map
        Map<Long,String> foodTypeIDStrMap = new HashMap<Long, String>() ;
        for(Food food1:list)
        {
            if(!foodTypeIDStrMap.containsKey(food1.getFoodTypeID()))
            {
                String str = this.generateFoodTypeStr(food1.getFoodTypeID());
                food1.setFoodTypeStr(str);
                foodTypeIDStrMap.put(food1.getFoodTypeID(),str);
            }
            else
            {
                food1.setFoodTypeStr(foodTypeIDStrMap.get(food1.getFoodTypeID()));
            }
        }
        return list;
    }

    /**
     * 生成食品类别字段 例如谷物/麦类/小麦
     * @param foodTypeID 食品类别ID
     * @return String 食品类别层级
     */
    public String generateFoodTypeStr(long foodTypeID)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        FoodType foodType = foodTypeMapper.selectFoodTypeById(foodTypeID,detectionCenterID);
        List<String> foodTypeList = new ArrayList<>();

        long flag = -1;
        while(true)
        {
            foodTypeList.add(foodType.getFoodTypeName());
            flag = foodType.getFoodTypeParentID();
            if(flag == 0)
            {
                break;
            }
            foodType = foodTypeMapper.selectFoodTypeById(foodType.getFoodTypeParentID(),detectionCenterID);
        }
        Collections.reverse(foodTypeList);
        String str = String.join("/",foodTypeList);

        return str;
    }

    /**
     * 新增食品管理
     * 
     * @param food 食品管理
     * @return 结果
     */
    @Override
    public String insertFood(Food food)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        food.setDetectionCenterID(detectionCenterID);
        Food isExist = foodMapper.selectFoodExist(food);
        if(StringUtils.isNull(isExist))
        {
            int ret = foodMapper.insertFood(food);
            return ret > 0 ? "操作成功":"操作失败";
        }
        else
        {
            return "重复插入";
        }
//        return foodMapper.insertFood(food);
    }

    /**
     * 导入食品信息
     * @param foodList 待导入的食品列表
     * @param isUpdateSupport 是否支持更新
     * @return 导入返回结果
     */
    public String importFood(List<Food> foodList, Boolean isUpdateSupport){
        if(StringUtils.isNull(foodList) || foodList.size() == 0)
        {
            throw new CustomException("导入食品数据不能为空！");
        }

        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();

        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();

        int row = 1;
        for(Food food : foodList){
            try{
                row++;
                String foodTypeStr = food.getFoodTypeStr();
                if(foodTypeStr == null)
                {
                    failureNum++;
                    failureMsg.append("<br/>" + "Excel表中第" + row + "行数据出错，样品类别层级不能为空！！！");
                    continue;
                }

                long typeParentID = 0;
                String foodTypeName = "";
                String[] typeList = foodTypeStr.split("/");
                if(typeList.length > 1)
                {
                    if(!typeList[typeList.length - 1].equals(food.getFoodName())){
                        failureNum++;
                        failureMsg.append("<br/>" + "Excel表中第" + row + "行数据出错，样品类别层级和样品学名不匹配！！！");
                        continue;
                    }
                    for(int i = 0; i <= typeList.length - 2; i++)
                    {
                        foodTypeName = typeList[i];
                        FoodType foodType = new FoodType(null, typeParentID, foodTypeName);
                        FoodType isExist = foodTypeMapper.selectFoodTypeExist(foodType);
                        if(StringUtils.isNull(isExist))
                        {
                            foodType.setIsValid(1);
                            foodTypeMapper.insertFoodType(foodType);
                            typeParentID = foodType.getFoodTypeID();
                        }
                        else
                        {
                            typeParentID = isExist.getFoodTypeID();
                        }
                    }
                    foodTypeName = typeList[typeList.length - 1];
                }
                else
                {
                    foodTypeName = typeList[0];
                }

                //插入食品类别 食品表
                FoodType foodType = new FoodType(typeParentID, foodTypeName);
                FoodType IsFoodTypeExist = foodTypeMapper.selectFoodTypeExist(foodType);

                //食品类别层级不存在
               if(StringUtils.isNull(IsFoodTypeExist))
               {
                   foodType.setIsValid(1);
                   foodTypeMapper.insertFoodType(foodType);
                   food.setFoodTypeID(foodType.getFoodTypeID());
                   food.setDetectionCenterID(detectionCenterID);
                   food.setIsValid(1);
                   foodMapper.insertFood(food);
               }
               else //食品类别层级存在
               {
                   food.setFoodTypeID(IsFoodTypeExist.getFoodTypeID());
                   Food IsFoodExist = foodMapper.selectFoodExist(food);
                    if(StringUtils.isNull(IsFoodExist))
                    {
                        successNum++;
                        food.setDetectionCenterID(detectionCenterID);
                        food.setIsValid(1);
                        foodMapper.insertFood(food);
                    }
                    else if(isUpdateSupport){
                        successNum++;
                        food.setDetectionCenterID(detectionCenterID);
                        foodMapper.updateFood(food);
                    }
                    else
                    {
                        failureNum++;
                        failureMsg.append("<br/>" + "Excel表中第" + row + "行数据已存在。如需更新，请点击更新按钮！");

                    }
               }
            }
            catch (Exception e){
                failureNum++;
                String msg = "<br/>" + "Excel表中第" + row + "行数据出错";
                failureMsg.append(msg + e.getMessage());
                log.error(msg,e);
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉,部分数据导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new CustomException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条");
        }
        return successMsg.toString();
    }


    /**
     * 修改食品管理
     * 
     * @param food 食品管理
     * @return 结果
     */
    @Override
    public int updateFood(Food food)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        food.setDetectionCenterID(detectionCenterID);
        int ret = foodMapper.updateFood(food);
        if(ret > 0)
        {
            FoodType foodType = new FoodType(food.getFoodTypeID(),null,food.getFoodName());
            ret = foodTypeMapper.updateFoodType(foodType);
        }
        if(food.getIsValid() == 0){
            SamplePesticide samplePesticide = new SamplePesticide();
            samplePesticide.setSampleID(food.getFoodTypeID());
            samplePesticide.setIsValid(0);
            samplePesticide.setDetectionCenterID(detectionCenterID);
            samplePesticideMapper.updateSamplePesticide(samplePesticide);
        }
        return ret;
    }

    /**
     * 批量删除食品管理
     * @param foodTypeIDs 需要删除的食品管理ID
     * @return 结果
     */
    @Override
    public int deleteFoodByTypeIds(Long[] foodTypeIDs)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        //删除样品-检测项目
        samplePesticideMapper.deleteSamplePesticideByFoodIds(foodTypeIDs, detectionCenterID);
        //删除样品类别
        int result = foodMapper.deleteFoodByIds(foodTypeIDs, detectionCenterID);
        if(result > 0)
        {
            //删除样品
            result = foodTypeMapper.deleteFoodTypeByIds(foodTypeIDs,detectionCenterID);
        }
        return result;
    }

    /**
     * 删除食品管理信息
     * @param foodTypeID 食品管理ID
     * @return 结果
     */
    @Override
    public int deleteFoodByTypeId(Long foodTypeID)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        int ret = foodMapper.deleteFoodById(foodTypeID, detectionCenterID);
        if(ret > 0)
        {
            ret = foodTypeMapper.deleteFoodTypeById(foodTypeID,detectionCenterID);
        }
        return ret;
    }
}
