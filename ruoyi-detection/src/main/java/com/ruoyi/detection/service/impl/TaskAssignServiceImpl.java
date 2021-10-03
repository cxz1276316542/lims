package com.ruoyi.detection.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ruoyi.commission.service.IProgressService;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.detection.constant.TaskAssignStatus;
import com.ruoyi.detection.domain.CommissionSample;
import com.ruoyi.detection.domain.DetectionResult;
import com.ruoyi.detection.domain.vo.*;
import com.ruoyi.detection.mapper.CommissionSampleMapper;
import com.ruoyi.detection.mapper.DetectionResultMapper;

import com.ruoyi.detection.service.ICommissionSampleService;
import com.ruoyi.review.domain.Review;
import com.ruoyi.review.service.IReviewService;
import com.ruoyi.system.service.INumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.detection.mapper.TaskAssignMapper;
import com.ruoyi.detection.domain.TaskAssign;
import com.ruoyi.detection.service.ITaskAssignService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 任务分派Service业务层处理
 *
 * @author lkun
 * @date 2021-08-05
 */
@Service
public class TaskAssignServiceImpl implements ITaskAssignService
{
    @Resource
    private TaskAssignMapper taskAssignMapper;

    @Resource
    private DetectionResultMapper detectionResultMapper;

    @Resource
    private CommissionSampleMapper commissionSampleMapper;

    @Autowired
    private ICommissionSampleService commissionSampleService;

    @Autowired
    private IReviewService reviewService;

    @Autowired
    private INumberService numberService;

    @Autowired
    private IProgressService progressService;

    /**
     * 查询任务分派
     *
     * @param sampleID 任务分派ID
     * @return 任务分派
     */
    @Override
    public TaskAssign selectTaskAssignById(Long sampleID)
    {
        return taskAssignMapper.selectTaskAssignById(sampleID);
    }

    /**
     * 查询任务分派列表
     *
     * @param taskAssign 任务分派
     * @return 任务分派
     */
    @Override
    public List<TaskAssign> selectTaskAssignList(TaskAssign taskAssign)
    {
        return taskAssignMapper.selectTaskAssignList(taskAssign);
    }

    @Transactional
    @Override
    public List<TaskAssginVO> selectTaskAssignVOList(TaskAssginVO taskAssginVO) {
        //只显示待领取，领取失败和领取成功
        return taskAssignMapper.selectTaskAssignVOList(taskAssginVO);
    }

    @Transactional
    @Override
    public int submitNoClaim(List<TaskAssginVO> taskAssginVOList) {

        for (TaskAssginVO taskAssginVO : taskAssginVOList) {
            //认领失败
            TaskAssign taskAssign = taskAssignMapper.selectTaskAssignById(taskAssginVO.getTaskAssignID());
            taskAssign.setReviewStatus(TaskAssignStatus.ASSIGN_CLIAM_FAIL);
            taskAssignMapper.updateTaskAssign(taskAssign);
        }
        return 1;
    }

    @Transactional
    @Override
    public int submitClaim(List<TaskAssginVO> taskAssginVOList) {

        for (TaskAssginVO taskAssginVO : taskAssginVOList) {
            //认领成功
            TaskAssign taskAssign = taskAssignMapper.selectTaskAssignById(taskAssginVO.getTaskAssignID());
            taskAssign.setReviewStatus(TaskAssignStatus.ASSIGN_CLIAM_PASS);
            taskAssignMapper.updateTaskAssign(taskAssign);
            DetectionResult detectionResult =new DetectionResult(taskAssign.getTaskAssignNumber(),taskAssign.getSampleID(),taskAssign.getSampleNumber(),taskAssign.getPesticideID(),taskAssign.getInspectorID(),0, DateUtils.getNowDate(),taskAssign.getTaskAssignID());
            detectionResultMapper.insertDetectionResult(detectionResult);
        }
        return 1;
    }


    /**
     * 新增任务分派
     *
     * @param taskAssign 任务分派
     * @return 结果
     */
    @Override
    public int insertTaskAssign(TaskAssign taskAssign)
    {
        return taskAssignMapper.insertTaskAssign(taskAssign);
    }

    /**
     * 修改任务分派
     *
     * @param taskAssign 任务分派
     * @return 结果
     */
    @Override
    public int updateTaskAssign(TaskAssign taskAssign)
    {
        return taskAssignMapper.updateTaskAssign(taskAssign);
    }

    /**
     * 批量删除任务分派
     *
     * @param sampleIDs 需要删除的任务分派ID
     * @return 结果
     */
    @Override
    public int deleteTaskAssignByIds(Long[] sampleIDs)
    {
        return taskAssignMapper.deleteTaskAssignByIds(sampleIDs);
    }

    /**
     * 删除任务分派信息
     *
     * @param sampleID 任务分派ID
     * @return 结果
     */
    @Override
    public int deleteTaskAssignById(Long sampleID)
    {
        return taskAssignMapper.deleteTaskAssignById(sampleID);
    }

    /**
     * 查询未分派的任务列表
     * @return
     */
    @Override
    public List<TaskAssignAddVO> unAssignedTaskList(TaskAssignAddSearchVO taskAssignAddSearchVO) {
        return taskAssignMapper.unAssignedTaskList(taskAssignAddSearchVO);
    }

    /**
     * 根据ID查询到未分派任务
     * @param sampleID 检品ID
     * @return
     */
    @Override
    public TaskAssignAddVO getUnsignedTaskByID(Long sampleID) {
        TaskAssignAddVO unsignedTaskByID = taskAssignMapper.getUnsignedTaskByID(sampleID);
        List<Long> detectionItems = stringToList(unsignedTaskByID.getSampleDescription());
        List<ItemAndMethodAndInspectorVO> list = new ArrayList<>();
        List<DetectionItemVO> itemVOS = new ArrayList<>();
        for(Long id : detectionItems){
            ItemAndMethodAndInspectorVO vo = new ItemAndMethodAndInspectorVO();
            vo.setDetectionItemID(id);
            vo.setDetectionMethodID(taskAssignMapper.findDefaultDetectionMethodID(id));
            vo.setInspectorID(taskAssignMapper.findDefaultDetectionInspector(id));
            list.add(vo);
            DetectionItemVO item = new DetectionItemVO();
            // 设置默认的检测项目、方法、检测员
            item.setMainInspectorID(vo.getInspectorID());
            item.setDetectionMethodID(vo.getDetectionMethodID());
            item.setPesticideID(id);
            item.setSampleID(sampleID);
            item.setPesticideName(taskAssignMapper.findDetectionItemNameByID(id));
            // 一个检测项目对应的检测方法和检测员
            item.setMethods(taskAssignMapper.findDetectionMethodsByID(id));
            item.setInspectors(taskAssignMapper.findInspectorsByID(id));
            itemVOS.add(item);
        }
        unsignedTaskByID.setItemAndMethodAndInspectorVOList(list);
        unsignedTaskByID.setDetectionItemList(itemVOS);
        return unsignedTaskByID;
    }

    private List<Long> stringToList(String str){
        if(StringUtils.isEmpty(str)){
            return null;
        }
        List<Long> res = new ArrayList<>();
        String[] strs = str.split(",");
        for(String s : strs){
            res.add(Long.valueOf(s));
        }
        return res;
    }

    /**
     * 根据检品学名和检测标准查找到需要的所有检测项目
     */
    @Override
    public List<DetectionItemVO> getDetectionItemList(String sampleAcademicName, Long standardID) {
        List<DetectionItemVO> detectionItemList = taskAssignMapper.getDetectionItemList(sampleAcademicName, standardID);
        for (DetectionItemVO itemVO : detectionItemList) {
            List<Long> detectionMethodIDList = new ArrayList<>();
            String ids = itemVO.getDetectionMethodIDs();
            if(StringUtils.isEmpty(ids)){
                continue;
            }
            for (String s : ids.split(",")) {
                detectionMethodIDList.add(Long.valueOf(s));
            }
            itemVO.setDetectionMethodID(detectionMethodIDList.get(0));
            itemVO.setDetectionMethodIDList(detectionMethodIDList);
        }
        return detectionItemList;
    }

    /**
     * 根据检品学名和检测标准查找农药信息
     * @param academicName 学名
     * @param standardID 检测标准ID
     * @return
     */
    @Override
    public List<PesticideIDNameVO> getPesticideListByAcademicNameAndStandardID(String academicName, Long standardID) {
        return taskAssignMapper.getPesticideListByAcademicNameAndStandardID(academicName, standardID);
    }

    /**
     * 查询到检测方法ID和检测方法名称列表
     * @return
     */
    @Override
    public List<DetectionMethodIDAndNameVO> getDetectionMethodList() {
        return taskAssignMapper.getDetectionMethodList();
    }

    /**
     * 找到所有的检测人员ID和检测人员名称
     * @return
     */
    @Override
    public List<InspectorIDAndNameVO> getInspectorList() {
        return taskAssignMapper.getInspectorList();
    }

    /**
     * 新增单个检测任务
     * @param detectionItemVOs
     * @return
     */
    @Transactional
    @Override
    public int addTask(List<DetectionItemVO> detectionItemVOs) {
        if(detectionItemVOs == null || detectionItemVOs.size() == 0){
            return 0;
        }
        // 任务单编号
        String taskNumber = numberService.generateTaskNumber();
        // 获取检品优先级
        Long sampleID = detectionItemVOs.get(0).getSampleID();
        CommissionSample commissionSample = commissionSampleService.selectCommissionSampleById(sampleID);
        Long userId = SecurityUtils.getLoginUser().getUser().getUserId();
        for (DetectionItemVO itemVO : detectionItemVOs) {
            TaskAssign taskAssign = new TaskAssign();
            // 需要自动生成，现在固定
            taskAssign.setTaskAssignNumber(taskNumber);
            taskAssign.setSampleID(sampleID);
            taskAssign.setSampleNumber(commissionSample.getSampleNumber());
            taskAssign.setPesticideID(itemVO.getPesticideID());
            taskAssign.setDetectionMethod(itemVO.getDetectionMethodID());
            // 主检人
            taskAssign.setInspectorID(itemVO.getMainInspectorID());
            taskAssign.setFlag(1);
            taskAssign.setOperatorID(userId);
            taskAssign.setOperationTime(new Date());
            taskAssignMapper.insertTaskAssign(taskAssign);

            Review review = new Review();
            review.setSampleID(sampleID);
            review.setPriority(commissionSample.getPriority());
            review.setAssignOrResultID(taskAssign.getTaskAssignID());
            // 设置为任务单初次审核--21
            review.setReviewCategory(21);
            reviewService.insertReview(review);

            // 副检人
            // 副检人可以为空
            if(itemVO.getDeputyInspectorID() == null){
                continue;
            }
            taskAssign.setTaskAssignID(null);
            taskAssign.setInspectorID(itemVO.getDeputyInspectorID());
            taskAssign.setFlag(2);
            taskAssignMapper.insertTaskAssign(taskAssign);
            review.setAssignOrResultID(taskAssign.getTaskAssignID());
            reviewService.insertReview(review);
        }
        // 1--表示该检品已分派
        commissionSampleMapper.updateAssignFlagBySampleID(sampleID,1);
        // 更新检品状态,审核阶段
        progressService.inTaskRewview(sampleID);
        return 1;
    }


    /**
     * 查询任务分派列表展示内容
     */
    @Override
    public List<TaskAssignSearchVO> findTaskAssignList(TaskAssignQueryParamVO taskAssignQueryParamVO) {
        return taskAssignMapper.findTaskAssignList(taskAssignQueryParamVO);
    }

    /**
     * 查询任务重新分派列表展示内容
     */
    @Override
    public List<TaskAssignSearchVO> findTaskReassignList(TaskAssignQueryParamVO taskAssignQueryParamVO) {
        return taskAssignMapper.findTaskReassignList(taskAssignQueryParamVO);
    }

    /**
     * 获取检测农药ID和检测农药名称
     */
    @Override
    public List<PesticideIDNameVO> getDetectionMethodListOption() {
        return taskAssignMapper.getDetectionMethodListOption();
    }

    /**
     * 查找所有业务员的ID和账号，和检测员共用一个VO
     * @return
     */
    @Override
    public List<InspectorIDAndNameVO> findAllServicer() {
        return taskAssignMapper.findAllServicer();
    }

    /**
     * 修改单个检测项目
     * @param taskAssign
     * @return
     */
    @Transactional
    @Override
    public int updateDetectionItem(TaskAssign taskAssign) {
        TaskAssign oldTaskAssign = taskAssignMapper.selectTaskAssignById(taskAssign.getTaskAssignID());
        if(oldTaskAssign.getInspectorID().equals(taskAssign.getInspectorID()) && oldTaskAssign.getDetectionMethod().equals(taskAssign.getDetectionMethod())&&oldTaskAssign.getPesticideID().equals(taskAssign.getPesticideID())){
            return 0;
        }
        taskAssign.setOperationTime(new Date());
        taskAssign.setReviewStatus(0);
        taskAssign.setRemark(null);
        Review review = new Review();
        CommissionSample commissionSample = commissionSampleService.selectCommissionSampleById(taskAssign.getSampleID());
        review.setSampleID(taskAssign.getSampleID());
        review.setPriority(commissionSample.getPriority());
        review.setAssignOrResultID(taskAssign.getTaskAssignID());
        review.setReviewCategory(22);
        reviewService.insertReview(review);
        return taskAssignMapper.updateDetectionItem(taskAssign);
    }

    /**
     * 根据检测项目ID查找到需要检测人员，检测方法
     */
    @Override
    public DetectionItemVO methodsAndInspector(Long id) {
        DetectionItemVO item = new DetectionItemVO();
        item.setMethods(taskAssignMapper.findDetectionMethodsByID(id));
        item.setInspectors(taskAssignMapper.findInspectorsByID(id));
        return item;
    }
}
