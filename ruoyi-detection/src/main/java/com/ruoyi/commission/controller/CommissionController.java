package com.ruoyi.commission.controller;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.ruoyi.commission.domain.dto.CommissionDTO;
import com.ruoyi.commission.domain.dto.EntrustList;
import com.ruoyi.commission.service.IProgressService;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.SecurityUtils;
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
import com.ruoyi.commission.domain.Commission;
import com.ruoyi.commission.service.ICommissionService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 委托样品Controller
 * 
 * @author deng
 * @date 2021-07-23
 */
@RestController
@RequestMapping("/commission/commissionPrototype")
public class CommissionController extends BaseController
{
    @Autowired
    private ICommissionService commissionService;

    @Autowired
    private IProgressService progressService;

    @Autowired
    private RedisCache redisCache;

    /**
     * 查询委托样品列表
     */
//    @PreAuthorize("@ss.hasPermi('commission:commissionPrototype:list')")
    @GetMapping("/list")
    public TableDataInfo list(Commission commission)
    {
        startPage();
        List<Commission> list = commissionService.selectCommissionList(commission);
        return getDataTable(list);
    }

    /**
     * 导出委托样品列表
     */
//    @PreAuthorize("@ss.hasPermi('commission:commissionPrototype:export')")
    @Log(title = "委托样品", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Commission commission)
    {
        List<Commission> list = commissionService.selectCommissionList(commission);
        ExcelUtil<Commission> util = new ExcelUtil<Commission>(Commission.class);
        return util.exportExcel(list, "委托样品数据");
    }

    /**
     * 获取委托样品详细信息
     */
//    @PreAuthorize("@ss.hasPermi('commission:commissionPrototype:query')")
    @GetMapping(value = "/{sampleID}")
    public AjaxResult getInfo(@PathVariable("sampleID") Long sampleID)
    {
        return AjaxResult.success(commissionService.selectCommissionById(sampleID));
    }

    /**
     * 新增委托样品
     */
//    @PreAuthorize("@ss.hasPermi('commission:commissionPrototype:add')")
    @Log(title = "委托样品", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Commission commission)
    {
        return toAjax(commissionService.insertCommission(commission));
    }

    /**
     * 修改委托样品
     */
//    @PreAuthorize("@ss.hasPermi('commission:commissionPrototype:edit')")
    @Log(title = "委托样品", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Commission commission)
    {
        return toAjax(commissionService.updateCommission(commission));
    }

    /**
     * 删除委托样品
     */
//    @PreAuthorize("@ss.hasPermi('commission:commissionPrototype:remove')")
    @Log(title = "委托样品", businessType = BusinessType.DELETE)
	@DeleteMapping("/{sampleIDs}")
    public AjaxResult remove(@PathVariable Long[] sampleIDs)
    {
        return toAjax(commissionService.deleteCommissionByIds(sampleIDs));
    }

    /**
     * 获取检测标准
     * @return
     */
    @GetMapping("/standards")
    public AjaxResult getDetectionStandard(){
        return AjaxResult.success(commissionService.getDetectionStandard());
    }

    /**
     * 获取计量单位
     * @return
     */
    @GetMapping("/units")
    public AjaxResult getUnits(){
        return AjaxResult.success(commissionService.getUnits());
    }

    /**
     * 提交委托
     * @param commissionDTO
     * @return
     */
    @PostMapping("/submit")
    public AjaxResult submit(@RequestBody CommissionDTO commissionDTO){
        boolean result = commissionService.submit(commissionDTO);
        return toAjax(result);
    }

    /**
     * 获取最近的一次输入
     * @return
     */
    @GetMapping("getLastInput")
    public AjaxResult getLastInput(){
        SysUser user = SecurityUtils.getLoginUser().getUser();
        String key = "commissionInput:"+user.getUserName()+":"+user.getUserId();
        CommissionDTO dto =  redisCache.getCacheObject(key);
        if(dto == null){
            dto = new CommissionDTO();
            List<EntrustList> list = new ArrayList<>();
            EntrustList e = new EntrustList();
            list.add(e);
            dto.setEntrustList(list);
        }
        return AjaxResult.success(JSON.toJSON(dto));
    }

    /**
     * 根据样品学名后取用户填入的数据
     * @param sampleName
     * @return
     */
    @GetMapping("/getHistory")
    public AjaxResult getHistory(String sampleName){
        SysUser user = SecurityUtils.getLoginUser().getUser();
        CommissionDTO dto  = commissionService.getHistory(user,sampleName);
        return AjaxResult.success(JSON.toJSON(dto));
    }

    @GetMapping("customerData")
    public AjaxResult getCustomerData(){
        return AjaxResult.success(commissionService.getCustomerData());
    }

    @GetMapping("orderID")
    public AjaxResult getOrderId(){
        return AjaxResult.success("操作成功",commissionService.getOrderId());
    }

    @GetMapping("dpesticide")
    public AjaxResult getDpesticide(String sampleName){
        return AjaxResult.success(commissionService.getDpesticide(sampleName));
    }
}
