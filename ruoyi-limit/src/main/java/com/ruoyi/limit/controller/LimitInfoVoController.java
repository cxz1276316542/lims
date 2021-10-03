package com.ruoyi.limit.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.limit.domain.*;
import com.ruoyi.limit.domain.vo.LimitInfoVo;
import com.ruoyi.limit.service.IFoodService;
import com.ruoyi.limit.service.ILimitInfoVoService;
import com.ruoyi.limit.service.IPesticideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 限量库详细信息Controller
 * 
 * @author wlf
 * @date 2021-07-21
 */
@RestController
@RequestMapping("/limit/limitInfo")
public class LimitInfoVoController extends BaseController
{
    @Autowired
    private ILimitInfoVoService limitInfoVoService;
    //检测农药 业务
    @Autowired
    private IPesticideService pesticideService;
    //食品
    @Autowired
    private IFoodService foodService;


    // 获取食品类别下拉树列表
    @GetMapping("/foodtypetree")
    public AjaxResult foodTree(FoodType foodType)
    {
        List<FoodType> foodsType = limitInfoVoService.selectFoodTree(foodType);
        return AjaxResult.success(limitInfoVoService.buildFoodTypeTreeSelect(foodsType));
    }


    /**
     * 查询限量库详细信息列表
     */
    @PreAuthorize("@ss.hasPermi('limit:limitInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(LimitInfoVo limitInfoVo)
    {
        startPage();
        List<LimitInfoVo> list = limitInfoVoService.selectLimitInfoVoList(limitInfoVo);
        return getDataTable(list);
    }

    /**
     * 根据食品查询限量库详细信息列表
     */
    @PreAuthorize("@ss.hasPermi('limit:limitInfo:listByFood')")
    @GetMapping("/listByFood")
    public TableDataInfo listByFood(Food food)
    {
        startPage();
        List<LimitInfoVo> list = limitInfoVoService.selectLimitInfoVoListByFood(food);
        return getDataTable(list);
    }

    /**
     * 根据农药查询限量库详细信息列表
     */
    @PreAuthorize("@ss.hasPermi('limit:limitInfo:listByPesticide')")
    @GetMapping("/listByPesticide")
    public TableDataInfo listByPesticide(Pesticide pesticide)
    {
        startPage();
        List<LimitInfoVo> list = limitInfoVoService.selectLimitInfoVoListByPesticide(pesticide);
        return getDataTable(list);
    }
}
