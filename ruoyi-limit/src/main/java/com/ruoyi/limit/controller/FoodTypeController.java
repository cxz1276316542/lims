package com.ruoyi.limit.controller;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.limit.domain.FoodType;
import com.ruoyi.limit.service.IFoodTypeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 食品类别管理Controller
 * 
 * @author wlf
 * @date 2021-07-20
 */
@RestController
@RequestMapping("/limit/foodType")
public class FoodTypeController extends BaseController
{
    @Autowired
    private IFoodTypeService foodTypeService;

    /**
     * 查询食品类别管理列表
     */
    @PreAuthorize("@ss.hasPermi('limit:foodType:list')")
    @GetMapping("/list")
    public TableDataInfo list(FoodType foodType)
    {
        startPage();
        List<FoodType> list = foodTypeService.selectFoodTypeList(foodType);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('limit:foodType:listAll')")
    @GetMapping("/listAll")
    public AjaxResult listAll(FoodType foodType)
    {
        return AjaxResult.success(foodTypeService.selectFoodTypeList(foodType));
    }

    /**
     * 导出食品类别管理列表
     */
    @PreAuthorize("@ss.hasPermi('limit:foodType:export')")
    @Log(title = "食品类别管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(FoodType foodType)
    {
        List<FoodType> list = foodTypeService.selectFoodTypeList(foodType);
        ExcelUtil<FoodType> util = new ExcelUtil<FoodType>(FoodType.class);
        return util.exportExcel(list, "食品类别管理数据");
    }

    /**
     * 获取食品类别管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('limit:foodType:query')")
    @GetMapping(value = "/{foodTypeID}")
    public AjaxResult getInfo(@PathVariable("foodTypeID") Long foodTypeID)
    {
        return AjaxResult.success(foodTypeService.selectFoodTypeById(foodTypeID));
    }

    /**
     * 新增食品类别管理
     */
    @PreAuthorize("@ss.hasPermi('limit:foodType:add')")
    @Log(title = "食品类别管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FoodType foodType)
    {
        String msg = foodTypeService.insertFoodType(foodType);
        if(msg.equals("操作成功"))
        {
            return AjaxResult.success(msg);
        }
        return AjaxResult.error(msg);
    }

    /**
     * 修改食品类别管理
     */
    @PreAuthorize("@ss.hasPermi('limit:foodType:edit')")
    @Log(title = "食品类别管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FoodType foodType)
    {
        return toAjax(foodTypeService.updateFoodType(foodType));
    }

    /**
     * 删除食品类别管理
     */
    @PreAuthorize("@ss.hasPermi('limit:foodType:remove')")
    @Log(title = "食品类别管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{foodTypeIDs}")
    public AjaxResult remove(@PathVariable Long[] foodTypeIDs)
    {
        return toAjax(foodTypeService.deleteFoodTypeByIds(foodTypeIDs));
    }
}
