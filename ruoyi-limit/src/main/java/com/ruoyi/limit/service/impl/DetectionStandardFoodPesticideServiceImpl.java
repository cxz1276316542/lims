package com.ruoyi.limit.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.limit.domain.*;
import com.ruoyi.limit.mapper.FoodTypeMapper;
import com.ruoyi.limit.service.ILimitInfoVoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 限量库详细信息Service业务层处理
 * 
 * @author wlf
 * @date 2021-07-21
 */
@Service
public class DetectionStandardFoodPesticideServiceImpl
{
    @Resource
    private FoodTypeMapper foodTypeMapper;

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<FoodType> list, FoodType t)
    {
        return getChildList(list, t).size() > 0 ? true : false;
    }
    /**
     * 得到子节点列表
     */
    private List<FoodType> getChildList(List<FoodType> list, FoodType t)
    {
        List<FoodType> tlist = new ArrayList<FoodType>();
        Iterator<FoodType> it = list.iterator();
        while (it.hasNext())
        {
            FoodType n = (FoodType) it.next();
            if (StringUtils.isNotNull(n.getFoodTypeParentID()) && n.getFoodTypeParentID().longValue() == t.getFoodTypeID().longValue())
            {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 递归列表
     */
    private void recursionFn(List<FoodType> list, FoodType t)
    {
        // 得到子节点列表
        List<FoodType> childList = getChildList(list, t);
        t.setChildren(childList);
        for (FoodType tChild : childList)
        {
            if (hasChild(list, tChild))
            {
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 构建前端所需要的食品类别树形结构
     * @param foods 食品类别
     * @return 树结构列表
     */

    public List<FoodType> buildFoodTree(List<FoodType> foods) {
        List<FoodType> returnList = new ArrayList<FoodType>();
        List<Long> tempList = new ArrayList<Long>();
        for (FoodType food : foods)
        {
            tempList.add(food.getFoodTypeID());
        }
        for (Iterator<FoodType> iterator = foods.iterator(); iterator.hasNext();)
        {
            FoodType food = (FoodType) iterator.next();
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(food.getFoodTypeParentID()))
            {
                recursionFn(foods, food);
                returnList.add(food);
            }
        }
        if (returnList.isEmpty())
        {
            returnList = foods;
        }
        return returnList;
    }

    /**
     * 构建前端所需要的下拉树结构
     * @param foodsType 食品类别
     * @return 树结构列表
     */

    public List<FoodTreeSelect> buildFoodTypeTreeSelect(List<FoodType> foodsType) {
        List<FoodType> foodTypeTrees = buildFoodTree(foodsType);
        return foodTypeTrees.stream().map(FoodTreeSelect::new).collect(Collectors.toList());
    }

    /**
     * 查询限量库中食品类别信息
     *
     * @param foodType 某个食品类别
     * @return 限量库中食品类别关系
     */

    public List<FoodType> selectFoodTree(FoodType foodType) {
        return foodTypeMapper.selectFoodTypeList(foodType);
    }

}
