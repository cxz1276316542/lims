package com.ruoyi.detection.controller;

import java.util.List;

import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.MimeTypeUtils;
import com.ruoyi.detection.constant.ReportTemplateURL;
import com.ruoyi.detection.domain.vo.LaboratoryVO;
import com.ruoyi.detection.domain.vo.ReportTemplateVO;
import com.ruoyi.limit.domain.DetectionMethod;
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
import com.ruoyi.detection.domain.ReportTemplate;
import com.ruoyi.detection.service.IReportTemplateService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 报告模板Controller
 * 
 * @author zhangkui
 * @date 2021-08-25
 */
@RestController
@RequestMapping("/detection/reportTemplate")
public class ReportTemplateController extends BaseController
{
    @Autowired
    private IReportTemplateService reportTemplateService;

    /**
     * 查询报告模板列表
     */
    @PreAuthorize("@ss.hasPermi('detection:reportTemplate:list')")
    @GetMapping("/list")
    public TableDataInfo list(ReportTemplateVO reportTemplateVO)
    {
        startPage();
//        List<ReportTemplate> list = reportTemplateService.selectReportTemplateList(reportTemplate);
        List<ReportTemplateVO> list = reportTemplateService.selectReportTemplateVOList(reportTemplateVO);
        return getDataTable(list);
    }

    /**
     * 导出报告模板列表
     */
    @PreAuthorize("@ss.hasPermi('detection:reportTemplate:export')")
    @Log(title = "报告模板", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ReportTemplate reportTemplate)
    {
        List<ReportTemplate> list = reportTemplateService.selectReportTemplateList(reportTemplate);
        ExcelUtil<ReportTemplate> util = new ExcelUtil<ReportTemplate>(ReportTemplate.class);
        return util.exportExcel(list, "报告模板数据");
    }

    /**
     * 获取报告模板详细信息
     */
    @PreAuthorize("@ss.hasPermi('detection:reportTemplate:query')")
    @GetMapping(value = "/{reportTemID}")
    public AjaxResult getInfo(@PathVariable("reportTemID") Long reportTemID)
    {
        return AjaxResult.success(reportTemplateService.selectReportTemplateById(reportTemID));
    }

    /**
     * 新增报告模板
     */
    @PreAuthorize("@ss.hasPermi('detection:reportTemplate:add')")
    @Log(title = "报告模板", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ReportTemplate reportTemplate)
    {
        System.out.println(reportTemplate.toString());
        return toAjax(reportTemplateService.insertReportTemplate(reportTemplate));
    }

    /**
     * 修改报告模板
     */
    @PreAuthorize("@ss.hasPermi('detection:reportTemplate:edit')")
    @Log(title = "报告模板", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ReportTemplate reportTemplate)
    {

        return toAjax(reportTemplateService.updateReportTemplate(reportTemplate));
    }

    /**
     * 删除报告模板
     */
    @PreAuthorize("@ss.hasPermi('detection:reportTemplate:remove')")
    @Log(title = "报告模板", businessType = BusinessType.DELETE)
	@DeleteMapping("/{reportTemIDs}")
    public AjaxResult remove(@PathVariable Long[] reportTemIDs)
    {
        return toAjax(reportTemplateService.deleteReportTemplateByIds(reportTemIDs));
    }

    /**
     * 查询实验室
     */
    @PreAuthorize("@ss.hasPermi('detection:reportTemplate:query')")
    @GetMapping("/laboratoryies")
    public AjaxResult searchLaboratoryies(){
        List<LaboratoryVO> laboratoryVOS = reportTemplateService.searchLaboratoryies();
        return AjaxResult.success(laboratoryVOS);
    }

    @Log(title = "上传模板", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('limit:detectionMethod:import')")
    @PostMapping("/importReportTem")
    public AjaxResult importReportTem(MultipartFile file, String updateSupport,String reportTemName) throws Exception
    {
        System.out.println(updateSupport+reportTemName);
        return AjaxResult.success(reportTemplateService.importReportTem(file,updateSupport,reportTemName));
    }
}
