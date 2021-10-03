package com.ruoyi.limit.controller;

import java.util.List;
import com.ruoyi.limit.domain.vo.LimitInfoVo;
import com.ruoyi.limit.service.ILimitInfoVoService;
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
import com.ruoyi.limit.domain.Standard;
import com.ruoyi.limit.service.IStandardService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 检测标准Controller
 *
 * @author wlf
 * @date 2021-07-20
 */
@RestController
@RequestMapping("/limit/standard")
public class StandardController extends BaseController
{
    @Autowired
    private IStandardService standardService;

    @Autowired
    private ILimitInfoVoService iLimitInfoVoService;

    /**
     * 导入限量库明细
     */
    @PreAuthorize("@ss.hasPermi('limit:standard:import')")
    @PostMapping("/importLimitInfo")
    public AjaxResult importLimitInfo(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<LimitInfoVo> util = new ExcelUtil<LimitInfoVo>(LimitInfoVo.class);
        List<LimitInfoVo> limitInfoVoList = util.importExcel(file.getInputStream());

        String message = iLimitInfoVoService.importLimit(limitInfoVoList, updateSupport);
        return AjaxResult.success(message);
    }

    /**
     * 下载检测标准导入模块
     */
    @GetMapping("/importTemplate")
    public AjaxResult importTemplate()
    {
        ExcelUtil<LimitInfoVo> util = new ExcelUtil<LimitInfoVo>(LimitInfoVo.class);
        return util.importTemplateExcel("基础库明细");
    }
    /**
     * 导出某检测标准的基础库明细
     * 传入检测标准编号
     */
    @PreAuthorize("@ss.hasPermi('limit:standard:exportLimitInfo')")
    @Log(title = "检测标准基础库明细", businessType = BusinessType.EXPORT)
    @GetMapping("/exportLimitInfo")
    public AjaxResult exportLimitInfo(Standard standard){
        //根据检测标准查找基础库明细
        List<LimitInfoVo> limitInfoVoList = iLimitInfoVoService.selectLimitInfoVoListByStandard(standard);
        ExcelUtil<LimitInfoVo> util = new ExcelUtil<LimitInfoVo>(LimitInfoVo.class);
        return util.exportExcel(limitInfoVoList, "基础库明细");
    }

    /**
     * 导出检测标准列表
     */
    @PreAuthorize("@ss.hasPermi('limit:standard:export')")
    @Log(title = "检测标准", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Standard standard)
    {
        List<Standard> list = standardService.selectStandardList(standard);
        ExcelUtil<Standard> util = new ExcelUtil<Standard>(Standard.class);
        return util.exportExcel(list, "检测标准数据");
    }

    /**
     * 查询检测标准列表
     */
    //@PreAuthorize("@ss.hasPermi('limit:standard:list')")
    @GetMapping("/list")
    public TableDataInfo list(Standard standard)
    {
        startPage();
        List<Standard> list = standardService.selectStandardList(standard);
        return getDataTable(list);
    }
    /**
     * 获取检测标准ID-检测编号map集合
     */
    @GetMapping("/map")
    public AjaxResult map()
    {
        return AjaxResult.success(standardService.selectStandardMap());
    }

    /**
     * 获取检测标准详细信息
     */
    @PreAuthorize("@ss.hasPermi('limit:standard:query')")
    @GetMapping(value = "/{standard}")
    public AjaxResult getInfo(@PathVariable("standard") Long standard)
    {
        return AjaxResult.success(standardService.selectStandardById(standard));
    }

    /**
     * 新增检测标准
     */
    @PreAuthorize("@ss.hasPermi('limit:standard:add')")
    @Log(title = "检测标准", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Standard standard)
    {
        String msg = standardService.insertStandard(standard);
        if(msg.equals("操作成功"))
        {
            return AjaxResult.success(msg);
        }
        return AjaxResult.error(msg);
    }

    /**
     * 修改检测标准
     */
    @PreAuthorize("@ss.hasPermi('limit:standard:edit')")
    @Log(title = "检测标准", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Standard standard)
    {
        return toAjax(standardService.updateStandard(standard));
    }

    /**
     * 删除检测标准
     */
    @PreAuthorize("@ss.hasPermi('limit:standard:remove')")
    @Log(title = "检测标准", businessType = BusinessType.DELETE)
	@DeleteMapping("/{standards}")
    public AjaxResult remove(@PathVariable Long[] standards)
    {
        return toAjax(standardService.deleteStandardByIds(standards));
    }
}
