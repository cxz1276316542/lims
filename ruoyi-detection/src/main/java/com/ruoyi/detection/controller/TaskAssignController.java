package com.ruoyi.detection.controller;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.detection.domain.vo.*;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.detection.domain.TaskAssign;
import com.ruoyi.detection.service.ITaskAssignService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 任务分派Controller
 *
 * @author lkun
 * @date 2021-08-05
 */
@RestController
@RequestMapping("/detection/taskAssign")
public class TaskAssignController extends BaseController
{
    @Autowired
    private ITaskAssignService taskAssignService;

    /**
     * 查询任务分派列表
     */
    @PreAuthorize("@ss.hasPermi('detection:taskAssign:list')")
    @GetMapping("/list")
    public TableDataInfo list(TaskAssign taskAssign)
    {
        startPage();
        List<TaskAssign> list = taskAssignService.selectTaskAssignList(taskAssign);
        return getDataTable(list);
    }


    /**
     * 查询任务分派列表展示内容
     */
    @PreAuthorize("@ss.hasPermi('detection:taskAssign:list')")
    @GetMapping("/listVO")
    public TableDataInfo listVO(TaskAssignQueryParamVO taskAssignQueryParamVO)
    {
        startPage();
        List<TaskAssignSearchVO> list = taskAssignService.findTaskAssignList(taskAssignQueryParamVO);
        return getDataTable(list);
    }

    /**
     * 查询任务分派列表展示内容
     */
    @PreAuthorize("@ss.hasPermi('detection:taskAssign:list')")
    @GetMapping("/listReassignVO")
    public TableDataInfo listReassignVO(TaskAssignQueryParamVO taskAssignQueryParamVO)
    {
        startPage();
        List<TaskAssignSearchVO> list = taskAssignService.findTaskReassignList(taskAssignQueryParamVO);
        return getDataTable(list);
    }

    /**
     * 认领VO
     */
    @PreAuthorize("@ss.hasPermi('detection:detectionResult:edit')")
    @GetMapping("/listTaskAssignVO")
    public TableDataInfo listTaskAssignVO(TaskAssginVO taskAssginVO)
    {
        startPage();
        taskAssginVO.setInspectorID(SecurityUtils.getLoginUser().getUser().getUserId());
        System.out.println(SecurityUtils.getLoginUser().getUser().getUserId());
//        List<TaskAssign> list = taskAssignService.selectTaskAssignList(taskAssign);
        List<TaskAssginVO> list=taskAssignService.selectTaskAssignVOList(taskAssginVO);
        return getDataTable(list);
    }

    /**
     * 批量领取
     */
    @PreAuthorize("@ss.hasPermi('detection:detectionResult:edit')")
    @Log(title = "批量领取", businessType = BusinessType.UPDATE)
    @PutMapping("/submitBatchClaim")
    public AjaxResult submitBatchClaim(@RequestBody List<TaskAssginVO>  taskAssginVOList)
    {
        return toAjax(taskAssignService.submitClaim(taskAssginVOList));
    }

    /**
     * 单行领取
     */
    @PreAuthorize("@ss.hasPermi('detection:detectionResult:edit')")
    @Log(title = "单行领取", businessType = BusinessType.UPDATE)
    @PutMapping("/submitClaim")
    public AjaxResult submitClaim(@RequestBody TaskAssginVO taskAssginVO)
    {
        List<TaskAssginVO>  taskAssginVOList  =new ArrayList<>();
        taskAssginVOList.add(taskAssginVO);
        return toAjax(taskAssignService.submitClaim(taskAssginVOList));
    }

    /**
     * 批量拒领
     */
    @PreAuthorize("@ss.hasPermi('detection:detectionResult:edit')")
    @Log(title = "批量拒领", businessType = BusinessType.UPDATE)
    @PutMapping("/submitBatchNoClaim")
    public AjaxResult submitBatchNoClaim(@RequestBody List<TaskAssginVO>  taskAssginVOList)
    {
        return toAjax(taskAssignService.submitNoClaim(taskAssginVOList));
    }

    /**
     * 单行拒领
     */
    @PreAuthorize("@ss.hasPermi('detection:detectionResult:edit')")
    @Log(title = "单行拒领", businessType = BusinessType.UPDATE)
    @PutMapping("/submitNoClaim")
    public AjaxResult submitNoClaim(@RequestBody TaskAssginVO taskAssginVO)
    {
        List<TaskAssginVO>  taskAssginVOList  =new ArrayList<>();
        taskAssginVOList.add(taskAssginVO);
        return toAjax(taskAssignService.submitNoClaim(taskAssginVOList));
    }

    /**
     * 导出任务分派列表
     */
    @PreAuthorize("@ss.hasPermi('detection:taskAssign:export')")
    @Log(title = "任务分派", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TaskAssign taskAssign)
    {
        List<TaskAssign> list = taskAssignService.selectTaskAssignList(taskAssign);
        ExcelUtil<TaskAssign> util = new ExcelUtil<TaskAssign>(TaskAssign.class);
        return util.exportExcel(list, "任务分派数据");
    }

    /**
     * 获取任务分派详细信息
     */
    @PreAuthorize("@ss.hasPermi('detection:taskAssign:query')")
    @GetMapping(value = "/{sampleID}")
    public AjaxResult getInfo(@PathVariable("sampleID") Long sampleID)
    {
//        return AjaxResult.success(taskAssignService.selectTaskAssignById(sampleID));
        TaskAssginVO taskAssginVO=new TaskAssginVO();
        taskAssginVO.setTaskAssignID(sampleID);
        return AjaxResult.success(taskAssignService.selectTaskAssignVOList(taskAssginVO).get(0));
    }

    /**
     * 获取单个检测项目详细信息
     */
    @PreAuthorize("@ss.hasPermi('detection:taskAssign:query')")
    @GetMapping(value = "/taskAssignInfo/{taskAssignID}")
    public AjaxResult getOneTaskAssignInfo(@PathVariable("taskAssignID")Long taskAssignID){
        return AjaxResult.success(taskAssignService.selectTaskAssignById(taskAssignID));
    }

    /**
     * 新增任务分派
     */
    @PreAuthorize("@ss.hasPermi('detection:taskAssign:add')")
    @Log(title = "任务分派", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TaskAssign taskAssign)
    {
        return toAjax(taskAssignService.insertTaskAssign(taskAssign));
    }

    /**
     * 修改任务分派
     */
    @PreAuthorize("@ss.hasPermi('detection:taskAssign:edit')")
    @Log(title = "任务分派", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TaskAssign taskAssign)
    {
        List<TaskAssginVO>  taskAssginVOList  =new ArrayList<>();
        TaskAssginVO taskAssginVO=new TaskAssginVO();
        taskAssginVO.setTaskAssignID(taskAssign.getTaskAssignID());
        taskAssginVOList.add(taskAssginVO);
        taskAssignService.submitNoClaim(taskAssginVOList);
        return toAjax(taskAssignService.updateTaskAssign(taskAssign));
    }

    /**
     * 修改单个检测项目
     */
    @PreAuthorize("@ss.hasPermi('detection:taskAssign:edit')")
    @Log(title = "任务分派", businessType = BusinessType.UPDATE)
    @PutMapping("/updateDetectionItem")
    public AjaxResult updateDetectionItem(@RequestBody TaskAssign taskAssign)
    {
        return toAjax(taskAssignService.updateDetectionItem(taskAssign));
    }

    /**
     * 删除任务分派
     */
    @PreAuthorize("@ss.hasPermi('detection:taskAssign:remove')")
    @Log(title = "任务分派", businessType = BusinessType.DELETE)
    @DeleteMapping("/{sampleIDs}")
    public AjaxResult remove(@PathVariable Long[] sampleIDs)
    {
        return toAjax(taskAssignService.deleteTaskAssignByIds(sampleIDs));
    }

    /**
     * 查询未分派的任务
     */
    @PreAuthorize("@ss.hasPermi('detection:taskAssign:add')")
    @GetMapping("unAssignedTaskList")
    public TableDataInfo unAssignedTaskList(TaskAssignAddSearchVO taskAssignAddSearchVO){
        startPage();
        List<TaskAssignAddVO> list = taskAssignService.unAssignedTaskList(taskAssignAddSearchVO);
        return getDataTable(list);
    }
    /**
     * 根据样品ID查询到样品的基本信息
     */
    @PreAuthorize("@ss.hasPermi('detection:taskAssign:add')")
    @GetMapping("/unsignedTask/{sampleID}")
    public AjaxResult getUnsignedTaskByID(@PathVariable("sampleID")Long sampleID){
        return AjaxResult.success(taskAssignService.getUnsignedTaskByID(sampleID));
    }

    /**
     * 根据检品学名和检测标准查找到需要的所有检测项目
     */
    @GetMapping("/detectionItemList")
    public AjaxResult getDetectionItemList(@RequestParam("sampleAcademicName")String sampleAcademicName,@RequestParam("standardID")Long standardID){
        return AjaxResult.success(taskAssignService.getDetectionItemList(sampleAcademicName,standardID));
    }

    /**
     * 获取检测农药ID和检测农药名称
     */
    @GetMapping("/pesticideList")
    public AjaxResult getPesticideList(@RequestParam("academicName")String academicName,@RequestParam("standardID")Long standardID){
        return AjaxResult.success(taskAssignService.getPesticideListByAcademicNameAndStandardID(academicName,standardID));
    }

    /**
     * 获取检测农药ID和检测农药名称
     */
    @GetMapping("/pesticideListOption")
    public AjaxResult getDetectionMethodListOption(){
        return AjaxResult.success(taskAssignService.getDetectionMethodListOption());
    }

    /**
     * 获取检测方法ID和检测方法名称
     */
    @GetMapping("/detectionMethodList")
    public AjaxResult getDetectionMethodList(){
        return AjaxResult.success(taskAssignService.getDetectionMethodList());
    }


    /**
     * 获取检测人员的ID和名称
     */
    @GetMapping("/inspectorList")
    public AjaxResult getInspectorList(){
        return AjaxResult.success(taskAssignService.getInspectorList());
    }

    /**
     * 新增单个检测任务
     */
    @PostMapping("/addTask")
    public AjaxResult addTask(@RequestBody List<DetectionItemVO> detectionItemVO){
        return toAjax(taskAssignService.addTask(detectionItemVO));
    }

    /**
     * 查找所有的业务员
     */
    @GetMapping("/servicerList")
    public AjaxResult findAllServicer(){
        return AjaxResult.success(taskAssignService.findAllServicer());
    }

     /**
     * 根据检测项目ID查找到需要检测人员，检测方法
     */
    @GetMapping("/methodsAndInspectors/{id}")
    public AjaxResult methodsAndInspectors(@PathVariable("id") Long id){
        return AjaxResult.success(taskAssignService.methodsAndInspector(id));
    }
}
