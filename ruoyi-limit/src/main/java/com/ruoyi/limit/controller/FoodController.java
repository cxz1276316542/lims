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
import com.ruoyi.limit.domain.Food;
import com.ruoyi.limit.service.IFoodService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 食品管理Controller
 * 
 * @author wlf
 * @date 2021-07-20
 */
@RestController
@RequestMapping("/limit/food")
public class FoodController extends BaseController
{
    @Autowired
    private IFoodService foodService;

    /**
     * 查询食品管理列表
     */
    @PreAuthorize("@ss.hasPermi('limit:food:list')")
    @GetMapping("/list")
    public TableDataInfo list(Food food)
    {
        startPage();
        List<Food> list = foodService.selectFoodList(food);
        return getDataTable(list);
    }

    /**
     * 下载食品信息导入模块
     */
    @GetMapping("/importTemplate")
    public AjaxResult importTemplate()
    {
        ExcelUtil<Food> util = new ExcelUtil<Food>(Food.class);
        return util.importTemplateExcel("样品信息");
    }

    /**
     * 导入食品信息
     */
    @Log(title = "食品信息管理", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('limit:food:import')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<Food> util = new ExcelUtil<Food>(Food.class);
        List<Food> foodList = util.importExcel(file.getInputStream());
        String message = foodService.importFood(foodList, updateSupport);
        return AjaxResult.success(message);
    }


    /**
     * 导出食品管理列表
     */
    @PreAuthorize("@ss.hasPermi('limit:food:export')")
    @Log(title = "食品管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Food food)
    {
        List<Food> list = foodService.selectFoodList(food);
        ExcelUtil<Food> util = new ExcelUtil<Food>(Food.class);
        return util.exportExcel(list, "样品管理数据");
    }

    /**
     * 获取食品管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('limit:food:query')")
    @GetMapping(value = "/{foodID}")
    public AjaxResult getInfo(@PathVariable("foodID") Long foodID)
    {
        return AjaxResult.success(foodService.selectFoodById(foodID));
    }

    /**
     * 新增食品管理
     */
    @PreAuthorize("@ss.hasPermi('limit:food:add')")
    @Log(title = "食品管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Food food)
    {
        String msg = foodService.insertFood(food);
        if(msg.equals("操作成功")){
            return AjaxResult.success(msg);
        }
        return AjaxResult.error(msg);
    }

    /**
     * 修改食品管理
     */
    @PreAuthorize("@ss.hasPermi('limit:food:edit')")
    @Log(title = "食品管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Food food)
    {
        return toAjax(foodService.updateFood(food));
    }

    /**
     * 删除食品管理
     */
    @PreAuthorize("@ss.hasPermi('limit:food:remove')")
    @Log(title = "食品管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{foodTypeIDs}")
    public AjaxResult remove(@PathVariable Long[] foodTypeIDs)
    {
        return toAjax(foodService.deleteFoodByTypeIds(foodTypeIDs));
    }
}
