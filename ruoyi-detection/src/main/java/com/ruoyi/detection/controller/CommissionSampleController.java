package com.ruoyi.detection.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.constant.CommissionStatus;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.detection.domain.CommissionSample;
import com.ruoyi.detection.domain.vo.CommissionRegister;
import com.ruoyi.detection.domain.vo.CommissionSampleRegisterVO;
import com.ruoyi.detection.domain.vo.CompanyVO;
import com.ruoyi.detection.domain.vo.QueryCommissionSampleVO;
import com.ruoyi.detection.service.ICommissionSampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 委托样品Controller
 *
 * @author lkun
 * @date 2021-07-21
 */
@RestController
@RequestMapping("/detection/commissionSample")
public class CommissionSampleController extends BaseController
{
    @Autowired
    private ICommissionSampleService commissionSampleService;

    /**
     * 查询委托样品列表
     */
    @PreAuthorize("@ss.hasPermi('detection:commissionSample:list')")
    @GetMapping("/list")
    public TableDataInfo list(QueryCommissionSampleVO queryCommissionSampleVO)
    {
        startPage();
        List<CommissionSampleRegisterVO> list = commissionSampleService.searchAllCommissionSampleRegister(queryCommissionSampleVO);
        return getDataTable(list);
    }


    /**
     * 查询委托样品历史列表
     */
    @PreAuthorize("@ss.hasPermi('detection:commissionSample:list')")
    @GetMapping("/history/list")
    public TableDataInfo listHistory(QueryCommissionSampleVO queryCommissionSampleVO)
    {
        startPage();
        List<CommissionSampleRegisterVO> list = commissionSampleService.searchAllCommissionSampleRegisterHistory(queryCommissionSampleVO);
        return getDataTable(list);
    }



    /**
     * 导出委托样品列表
     */
    @PreAuthorize("@ss.hasPermi('detection:commissionSample:export')")
    @Log(title = "委托样品", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(CommissionSample commissionSample)
    {
        List<CommissionSample> list = commissionSampleService.selectCommissionSampleList(commissionSample);
        ExcelUtil<CommissionSample> util = new ExcelUtil<CommissionSample>(CommissionSample.class);
        return util.exportExcel(list, "委托样品数据");
    }

    /**
     * 获取委托样品详细信息
     */
    @PreAuthorize("@ss.hasPermi('detection:commissionSample:query')")
    @GetMapping(value = "/{sampleID}")
    public AjaxResult getInfo(@PathVariable("sampleID") Long sampleID)
    {
        return AjaxResult.success(commissionSampleService.selectCommissionSampleById(sampleID));
    }

    /**
     * 新增委托样品
     */
    @PreAuthorize("@ss.hasPermi('detection:commissionSample:add')")
    @Log(title = "委托样品", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CommissionRegister CommissionRegister)
    {
        return toAjax(commissionSampleService.insertCommissionSampleBatch(CommissionRegister));
    }

    /**
     * 修改委托样品
     */
    @PreAuthorize("@ss.hasPermi('detection:commissionSample:edit')")
    @Log(title = "委托样品", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CommissionSample commissionSample)
    {
        return toAjax(commissionSampleService.updateCommissionSample(commissionSample));
    }

    /**
     * 委托样品任务派发
     */
    @Log(title = "委托样品", businessType = BusinessType.UPDATE)
    @GetMapping(value = "/update/{sampleIDs}")
    public AjaxResult registerSuccess(@PathVariable Long[] sampleIDs)
    {
        //修改样品为任务派发状态
        return toAjax(commissionSampleService.updateStatus(sampleIDs, CommissionStatus.BUSINESS_SUBMIT));
    }


    /**
     * 删除委托样品
     */
    @PreAuthorize("@ss.hasPermi('detection:commissionSample:remove')")
    @Log(title = "委托样品", businessType = BusinessType.DELETE)
    @DeleteMapping("/{sampleIDs}")
    public AjaxResult remove(@PathVariable Long[] sampleIDs)
    {
        return toAjax(commissionSampleService.deleteCommissionSampleByIds(sampleIDs));
    }

    /**
     * 查找所有的有效计量单位
     */
    @PreAuthorize("@ss.hasPermi('detection:commissionSample:add')")
    @GetMapping("/measureUnits")
    public AjaxResult findMeasureUnit(){
        return AjaxResult.success(commissionSampleService.findAllMeasureUnit());
    }

    /**
     * 上传样品图片
     */
    //@PreAuthorize("@ss.hasPermi('detection:commissionSample:add')")
    @Log(title = "委托样品图片",businessType = BusinessType.INSERT)
    @PostMapping("/sample")
    public AjaxResult upload(@RequestParam("sampleImage")MultipartFile file) throws IOException {
        if(null != file){
            String sampleImage = FileUploadUtils.upload(RuoYiConfig.getSampleImagePath(),file);
            return AjaxResult.success("上传图片成功",sampleImage);
        }
        return AjaxResult.error("上传图片异常");
    }

    /**
     * 查找所有的检测标准
     */
    @GetMapping("/detectionStandards")
    public AjaxResult detectionStandardList(){
        return AjaxResult.success(commissionSampleService.findAllStandard());
    }

    /**
     * 查找所有样品学名
     */
//    @PreAuthorize("@ss.hasPermi('detection:commissionSample:add')")
    @GetMapping("/sampleAcademicName")
    public AjaxResult sampleAcademicNameList(){
        return AjaxResult.success(commissionSampleService.findAllSampleAcademicName());
    }

    /**
     * 批量提交样品登记
     */
    @PreAuthorize("@ss.hasPermi('detection:commissionSample:edit')")
    @PostMapping("/submit")
    public AjaxResult submitSampleBatch(@RequestBody Long[] sampleIDList){
        return toAjax(commissionSampleService.submitSampleBatch(sampleIDList));
    }

    /**
     * 查询所有客户
     */
    @PreAuthorize("@ss.hasPermi('detection:commissionSample:add')")
    @GetMapping("/customers")
    public AjaxResult searchAllCustomers(){
        return AjaxResult.success(commissionSampleService.searchAllCustomers());
    }

    /**
     * 查询所有的公司ID和地址
     * @return
     */
    @PreAuthorize("@ss.hasPermi('detection:commissionSample:add')")
    @GetMapping("/companies/{id}")
    public AjaxResult searchAllCompanies(@PathVariable("id") Long id){
        return AjaxResult.success(commissionSampleService.searchAllCompanies(id));
    }

    /**
     * 根据客户ID查找到最新的一条记录
     */
    @GetMapping("/lastRecord/{id}")
    public AjaxResult searchLastRecord(@PathVariable("id") Long id){
        return AjaxResult.success(commissionSampleService.searchLastRecordByCustomerID(id));
    }

    /**
     * 根据检品ID，查询到所有的检测项目
     */
    @GetMapping("/findItemList")
    public AjaxResult findItemListBySampleID(@RequestParam("sampleScientificName") String sampleName){
        return AjaxResult.success(commissionSampleService.findItemListBySampleID(sampleName));
    }

}
