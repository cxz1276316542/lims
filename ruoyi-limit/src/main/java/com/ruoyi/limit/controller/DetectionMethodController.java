package com.ruoyi.limit.controller;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.limit.domain.Pesticide;
import com.ruoyi.limit.domain.vo.InstrumentMethodVo;
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
import com.ruoyi.limit.domain.DetectionMethod;
import com.ruoyi.limit.service.IDetectionMethodService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 检测方法管理Controller
 * 
 * @author dlk
 * @date 2021-07-20
 */
@RestController
@RequestMapping("/limit/detectionMethod")
public class DetectionMethodController extends BaseController
{
    
    @Autowired
    private IDetectionMethodService detectionMethodService;

    /**
     * 查询检测方法管理列表
     */
    @PreAuthorize("@ss.hasPermi('limit:detectionMethod:list')")
    @GetMapping("/list")
    public TableDataInfo list(DetectionMethod detectionMethod)
    {
        startPage();
        List<DetectionMethod> list = detectionMethodService.selectDetectionMethodList(detectionMethod);
        return getDataTable(list);
    }

    /**
     * 导入检测方法excel文件插入到数据库
     * @param file excel文件
     * @param updateSupport 是否支持更新
     */
    @Log(title = "检测方法管理", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('limit:detectionMethod:import')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<DetectionMethod> util = new ExcelUtil<DetectionMethod>(DetectionMethod.class);
        List<DetectionMethod> detectionMethodList = util.importExcel(file.getInputStream());
        String message = detectionMethodService.importDetectionMethod(detectionMethodList, updateSupport);
        return AjaxResult.success(message);
    }

    /**
     * 下载限量库导入模块
     */
    @GetMapping("/importTemplate")
    public AjaxResult importTemplate()
    {
        ExcelUtil<DetectionMethod> util = new ExcelUtil<DetectionMethod>(DetectionMethod.class);
        return util.importTemplateExcel("检测方法");
    }

    /**
     * 导出检测方法管理列表
     */
    @PreAuthorize("@ss.hasPermi('limit:detectionMethod:export')")
    @Log(title = "检测方法管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(DetectionMethod detectionMethod)
    {
        List<DetectionMethod> list = detectionMethodService.selectDetectionMethodList(detectionMethod);
        ExcelUtil<DetectionMethod> util = new ExcelUtil<DetectionMethod>(DetectionMethod.class);
        return util.exportExcel(list, "检测方法管理数据");
    }

    /**
     * 获取检测方法管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('limit:detectionMethod:query')")
    @GetMapping(value = "/{detectionMethodID}")
    public AjaxResult getInfo(@PathVariable("detectionMethodID") Long detectionMethodID)
    {
        return AjaxResult.success(detectionMethodService.selectDetectionMethodById(detectionMethodID));
    }

    /**
     * 新增检测方法管理
     */
    @PreAuthorize("@ss.hasPermi('limit:detectionMethod:add')")
    @Log(title = "检测方法管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DetectionMethod detectionMethod)
    {
        String msg = detectionMethodService.insertDetectionMethod(detectionMethod);
        if(msg.equals("操作成功")){
            return AjaxResult.success(msg);
        }
        return AjaxResult.error(msg);
    }

    /**
     * 修改检测方法管理
     */
    @PreAuthorize("@ss.hasPermi('limit:detectionMethod:edit')")
    @Log(title = "检测方法管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DetectionMethod detectionMethod)
    {
        return toAjax(detectionMethodService.updateDetectionMethod(detectionMethod));
    }

    /**
     * 删除检测方法管理
     */
    @PreAuthorize("@ss.hasPermi('limit:detectionMethod:remove')")
    @Log(title = "检测方法管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{detectionMethodIDs}")
    public AjaxResult remove(@PathVariable Long[] detectionMethodIDs)
    {
        return toAjax(detectionMethodService.deleteDetectionMethodByIds(detectionMethodIDs));
    }
}
