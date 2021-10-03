package com.ruoyi.review.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import com.ruoyi.commission.service.IProgressService;
import com.ruoyi.commission.service.impl.ProgressService;
import com.ruoyi.common.constant.CommissionStatus;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.IntUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.detection.constant.*;
import com.ruoyi.detection.domain.CommissionSample;
import com.ruoyi.detection.domain.DetectionResult;
import com.ruoyi.detection.domain.Report;
import com.ruoyi.detection.domain.TaskAssign;
import com.ruoyi.detection.mapper.CommissionSampleMapper;
import com.ruoyi.detection.mapper.DetectionResultMapper;
import com.ruoyi.detection.mapper.ReportMapper;
import com.ruoyi.detection.mapper.TaskAssignMapper;
import com.ruoyi.limit.domain.Standard;
import com.ruoyi.limit.mapper.DetectionMethodMapper;
import com.ruoyi.limit.mapper.PesticideMapper;
import com.ruoyi.limit.mapper.StandardMapper;
import com.ruoyi.review.constant.ReviewCategory;
import com.ruoyi.review.constant.ReviewCirculation;
import com.ruoyi.review.constant.ReviewConclusion;
import com.ruoyi.review.constant.ReviewStatus;
import com.ruoyi.review.domain.VO.ReviewVO;
import com.ruoyi.stock.utils.PagingUtils;
import com.ruoyi.system.domain.SysUserRole;
import com.ruoyi.system.mapper.SysRoleMapper;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.mapper.SysUserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.review.mapper.ReviewMapper;
import com.ruoyi.review.domain.Review;
import com.ruoyi.review.service.IReviewService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 审核Service业务层处理
 *
 * @author zjccc
 * @date 2021-07-31
 */
@Service
public class ReviewServiceImpl implements IReviewService
{
    @Autowired
    private ReviewMapper reviewMapper;

    @Autowired
    private CommissionSampleMapper commissionSampleMapper;

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private SysUserRoleMapper userRoleMapper;

    @Autowired
    private StandardMapper standardMapper;

    @Autowired
    private SysRoleMapper roleMapper;

    @Autowired
    private PesticideMapper pesticideMapper;

    @Autowired
    private DetectionMethodMapper detectionMethodMapper;

    @Autowired
    private DetectionResultMapper detectionResultMapper;

    @Autowired
    private TaskAssignMapper taskAssignMapper;

    @Autowired
    private ReportMapper reportMapper;

    @Autowired
    private IProgressService progressService;


    /**
     * 查询审核对应样品信息
     * @param sampleID 样品ID
     * @return 审核
     */
    public Map<String,Object> getReviewCommissionInfo(Long sampleID) {
        Map<String,Object> map = new HashMap<>();
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        CommissionSample commissionSample = commissionSampleMapper.selectCommissionSampleById(sampleID);

        map.put("sampleNumber",commissionSample.getSampleNumber());
        map.put("sampleSenderName",commissionSample.getSampleSenderName());
        //获取检测标准
        Standard standard = standardMapper.selectStandardById(commissionSample.getDetectionStandardID(),detectionCenterID);
        map.put("detectionStandard",standard.getStandardName());

        map.put("sampleName",commissionSample.getSampleName());
        map.put("sampleScientificName",commissionSample.getSampleScientificName());
        if(SampleStatusEnums.codeOf(commissionSample.getSampleStatus()) != null){
            map.put("sampleStatus", SampleStatusEnums.codeOf(commissionSample.getSampleStatus()).getFiled());
        }
        if (SamplePriorityEnums.codeOf(commissionSample.getPriority()) != null){
            map.put("priority", SamplePriorityEnums.codeOf(commissionSample.getPriority()).getFiled());
        }

        map.put("sampleQuantity",commissionSample.getSampleQuantity());
        map.put("measureUnit",commissionSample.getMeasureUnit());
        map.put("sampleDescription",commissionSample.getSampleDescription());

        map.put("samplingMode",commissionSample.getSamplingMode()==1L?"抽检":"送检");
        map.put("samplingBase",commissionSample.getSamplingBase());

        if (DetectionCategoryEnums.codeOf(commissionSample.getDetectionCategory()) != null){
            map.put("detectionCategory", DetectionCategoryEnums.codeOf(commissionSample.getDetectionCategory()).getFiled());
        }

        map.put("productionUnit",commissionSample.getProductionUnit());
        map.put("productionUnitAddress",commissionSample.getProductionUnitAddress());
        if (commissionSample.getSamplingTime() != null){
            map.put("samplingTime",new SimpleDateFormat("yyyy-MM-dd HH:mm").format(commissionSample.getSamplingTime()));
        }
        map.put("sampleImage",commissionSample.getSampleImage());

        map.put("reportReceiveWay",commissionSample.getReportReceiveWay()==0L?"邮寄":"自取");
        map.put("recycle",commissionSample.getRecycle()==0L?"否":"是");

        return map;
    }


    /**
     * 查询审核内容信息
     * @param reviewID 审核ID
     * @return 审核
     */
    @Override
    public Map<String,Object> selectReviewInfoById(Long reviewID)
    {
        Review review = reviewMapper.selectReviewById(reviewID);

        Map<String,Object> map = getReviewCommissionInfo(review.getSampleID());

        //为检品审核
        if (IntUtils.IntArrLookupInt(ReviewCategory.SAMPLE_REVIEW,review.getReviewCategory()) ){

        }else if (IntUtils.IntArrLookupInt(ReviewCategory.ASSIGN_REVIEW,review.getReviewCategory())){
            //任务分派审核
            //获取对应的任务分派
            TaskAssign taskAssign = taskAssignMapper.selectTaskAssignById(review.getAssignOrResultID());

            if (taskAssign != null){
                SysUser user = userMapper.selectUserById(SecurityUtils.getLoginUser().getUser().getUserId());
                Long detectionCenterID = user.getDetectionCenterID();
                map.put("pesticideName",pesticideMapper.selectPesticideById(taskAssign.getPesticideID(),detectionCenterID).getPesticideName());
                map.put("inspectorName",userMapper.selectUserById(taskAssign.getOperatorID()).getNickName());
                map.put("detectionMethodName",detectionMethodMapper.selectDetectionMethodById(taskAssign.getDetectionMethod(),user.getDetectionCenterID()).getDetectionMethodName());
            }

        }else if (IntUtils.IntArrLookupInt(ReviewCategory.RESULT_REVIEW,review.getReviewCategory())){

            DetectionResult detectionResult = detectionResultMapper.selectDetectionResultById(review.getAssignOrResultID());

            if (detectionResult != null){
                map.put("sampleNumber",detectionResult.getSampleNumber());
                SysUser user = userMapper.selectUserById(SecurityUtils.getLoginUser().getUser().getUserId());
                Long detectionCenterID = user.getDetectionCenterID();
                map.put("pesticideName",pesticideMapper.selectPesticideById(detectionResult.getPesticideID(),detectionCenterID).getPesticideName());
                map.put("detectionData",detectionResult.getDetectionData());
                map.put("inspectorName",userMapper.selectUserById(detectionResult.getInspectorID()).getNickName());
                map.put("detectionTime",new SimpleDateFormat("yyyy-MM-dd HH:mm").format(detectionResult.getOperationTime()));
                //参考标准
                map.put("referenceStandard",pesticideMapper.selectPesticideById(detectionResult.getPesticideID(),detectionCenterID).getAdi());
            }

        }else if (IntUtils.IntArrLookupInt(ReviewCategory.REPORT_REVIEW,review.getReviewCategory())){

        }
        return map;
    }

    /**
     * 查询已审核详细信息
     * @param reviewID 审核ID
     * @return 审核
     */
    @Override
    public Map<String,Object> selectReviewedInfoById(Long reviewID) {
        Review review = reviewMapper.selectReviewById(reviewID);
        Map<String,Object> map = selectReviewInfoById(reviewID);
        if (review != null){
            map.put("reviewConclusion",review.getReviewConclusion());
            map.put("reviewOpinion",review.getReviewOpinion());
            map.put("reviewCategory",review.getReviewCategory());
            map.put("operationTime",review.getOperationTime());
            if (review.getOperatorID() !=null){
                map.put("operatorName",userMapper.selectUserById(review.getOperatorID()).getNickName());
            }
            return map;
        }else {
            return null;
        }
    }

    /**
     * 查询已审核信息
     * @param reviewID 审核ID
     * @return 审核
     */
    @Override
    public Map<String,Object> selectReviewUpdateInfoById(Long reviewID) {
        Review review = reviewMapper.selectReviewById(reviewID);
        Map<String,Object> map = new HashMap<>();
        if (review != null){
            map.put("reviewOpinion",review.getReviewOpinion());
            map.put("reviewID",review.getReviewID());
            map.put("sampleID",review.getSampleID());

            return map;
        }else {
            return null;
        }
    }

    /**
     * 查询报告撰写人员信息
     * @return
     */
    @Override
    public Map<Long, String> selectInventoryManager() {

        //查询库存管理员信息
        SysUserRole[] sysUsers = userRoleMapper.selectUserByRoleID(17L);
        Map<Long, String> writerList = new HashMap<>();
        for (SysUserRole sysUserRole :sysUsers ){
            writerList.put(sysUserRole.getUserId(),userMapper.selectUserById(sysUserRole.getUserId()).getNickName());
        }
        return writerList;
    }

    /**
     * 查询审核列表
     *
     * @param review 审核
     * @return 审核
     */
    @Override
    public List<Review> selectReviewList(Review review)
    {
        return reviewMapper.selectReviewList(review);
    }

    /**
     * 新增审核
     *
     * @param review 审核
     * @return 结果
     */
    @Transactional
    @Override
    public int insertReview(Review review)
    {
        return reviewMapper.insertReview(review);
    }

    /**
     * 修改审核
     *
     * @param review 审核
     * @return 结果
     */
    @Override
    public int updateReview(Review review)
    {
        return reviewMapper.updateReview(review);
    }

    /**
     * 批量删除审核
     *
     * @param reviewIDs 需要删除的审核ID
     * @return 结果
     */
    @Override
    public int deleteReviewByIds(Long[] reviewIDs)
    {
        return reviewMapper.deleteReviewByIds(reviewIDs);
    }

    /**
     * 删除审核信息
     *
     * @param reviewID 审核ID
     * @return 结果
     */
    @Override
    public int deleteReviewById(Long reviewID)
    {
        return reviewMapper.deleteReviewById(reviewID);
    }


    /**
     * 查询检品审核列表
     * @param reviewVO 审核
     * @param status 审核状态
     * @return 审核集合
     */
    @Override
    public TableDataInfo selectSampleReviewList(ReviewVO reviewVO,int status) {
        //分页
        PagingUtils.startPage();
        List<ReviewVO> reviewVOList = null;
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        //查询样品审核
        if (status == ReviewStatus.REVIEWED){
            reviewVOList = reviewMapper.selectReviewVOList(reviewVO,ReviewCategory.SAMPLE_REVIEW);
        }else if (status == ReviewStatus.NOT_REVIEW){
            reviewVOList = reviewMapper.selectNotReviewVOList(reviewVO,ReviewCategory.SAMPLE_REVIEW);
        }
        //添加信息
        for (ReviewVO reviewVO1:reviewVOList){
            //样品登记人信息
            if(reviewVO1.getRegistrantID() != null){
                reviewVO1.setRegistrantName(userMapper.selectUserById(reviewVO1.getRegistrantID()).getNickName());
            }

            //获取对应的样品对象
            CommissionSample commissionSample = commissionSampleMapper.selectCommissionSampleById(reviewVO1.getSampleID());

            reviewVO1.setSampleNumber(commissionSample.getSampleNumber());
            Map<String,Object> map = new HashMap<>();
            map.put("sampleNumber",commissionSample.getSampleNumber());
            map.put("priority",commissionSample.getPriority());
            map.put("samplingMode",commissionSample.getSamplingMode());
            map.put("detectionCategory",commissionSample.getDetectionCategory());
            //map.put("sampleStatus",commissionSample.getSampleStatus());
            map.put("sampleDescription",commissionSample.getSampleDescription());
            map.put("sampleScientificName",commissionSample.getSampleScientificName());
            map.put("sampleQuantity",commissionSample.getSampleQuantity());
            map.put("samplingBase",commissionSample.getSamplingBase());
            map.put("productionUnitAddress",commissionSample.getProductionUnitAddress());
            map.put("measureUnit",commissionSample.getMeasureUnit());
            map.put("sampleImage",commissionSample.getSampleImage());
            //获取检测标准
            Standard standard = standardMapper.selectStandardById(commissionSample.getDetectionStandardID(), detectionCenterID);
            if (standard!=null){
                map.put("detectionStandard",standard.getStandardName());
            }

            reviewVO1.setSubject(map);
            //若是已经审核的，传入操作者信息
            if (reviewVO1.getOperatorID() != null){
                reviewVO1.setOperatorName(userMapper.selectUserById(reviewVO1.getOperatorID()).getNickName());
            }

        }
        return PagingUtils.getDataTable(reviewVOList);
    }

    /**
     * 检品审核通过
     * @param review 审核
     * @return 结果
     */
    @Override
    public int adoptSampleReview(Review review) {
        review.setOperationTime(new Date());
        review.setReviewConclusion(ReviewConclusion.PASS);
        review.setOperatorID(SecurityUtils.getLoginUser().getUser().getUserId());
        review.setCirculation(0);
        //添加当前用户电子签名
        review.setElectronicSignature(userMapper.selectUserById(SecurityUtils.getLoginUser().getUser().getUserId()).getElectronicSignature());

        //更新委托样品状态
        CommissionSample commissionSample = new CommissionSample();
        commissionSample.setSampleID(review.getSampleID());
        commissionSample.setReviewStatus(CommissionStatus.REVIEW_ADOPT);
        commissionSampleMapper.updateCommissionSample(commissionSample);

        //更新委托流程
        progressService.inTaskAssign(review.getSampleID());
        return reviewMapper.updateReview(review);
    }

    /**
     * 检品审核不通过
     * @param review 审核
     * @param circulation 流转位置
     * @return 结果
     */
    @Override
    public int failSampleReview(Review review,int circulation) {
        review.setOperationTime(new Date());
        review.setReviewConclusion(ReviewConclusion.FAIL);
        review.setCirculation(circulation);
        review.setOperatorID(SecurityUtils.getLoginUser().getUser().getUserId());
        //添加当前用户电子签名
        review.setElectronicSignature(userMapper.selectUserById(SecurityUtils.getLoginUser().getUser().getUserId()).getElectronicSignature());

        //更新委托样品状态
        CommissionSample sample = new CommissionSample();
        sample.setSampleID(review.getSampleID());
        //若返回给业务员，样品状态置为退回修改
        if (circulation == ReviewCirculation.SAMPLE_TO_SALESMAN){
            sample.setReviewStatus(CommissionStatus.RETURN_MODIFY);
            //更新委托流程
            progressService.inCommission(review.getSampleID());
        }else if (circulation == ReviewCirculation.SAMPLE_TO_CUSTOMER){
            //若返回给客户重新送样，样品状态置为审核受理不通过
            sample.setReviewStatus(CommissionStatus.REVIEW_NOT_ADOPT);
            //更新委托流程
            progressService.inCommission(review.getSampleID());
        }else if (circulation == ReviewCirculation.SAMPLE_TO_CUSTOMER_MODIFY){
            //若返回给客户修改委托，样品状态置为审核受理不通过
            sample.setReviewStatus(CommissionStatus.COMMISSION_LIST_MODIFY);
            //更新委托流程
            progressService.inCommission(review.getSampleID());
        }
        commissionSampleMapper.updateCommissionSample(sample);
        return reviewMapper.updateReview(review);
    }

    /**
     * 批量检品审核不通过，退回用户重新送样
     * @param review 审核
     * @param circulation 流转标识
     * @return 结果
     */
    @Override
    public int batchFailSampleReview(Review review, Long[] reviewIds, int circulation) {
        review.setOperationTime(new Date());
        review.setReviewConclusion(ReviewConclusion.FAIL);
        review.setCirculation(circulation);
        review.setOperatorID(SecurityUtils.getLoginUser().getUser().getUserId());
        //添加当前用户电子签名
        review.setElectronicSignature(userMapper.selectUserById(SecurityUtils.getLoginUser().getUser().getUserId()).getElectronicSignature());

        //更新委托样品状态
        //获取需要更改的样品id数组
        Map<String, Object> map = new HashMap<>();
        List<Review> reviewList = reviewMapper.selectReviewByIds(reviewIds);
        for (int i =0;i<reviewList.size();i++){
            CommissionSample sample = new CommissionSample();
            sample.setSampleID(reviewList.get(i).getSampleID());
            //若返回给业务员，样品状态置为退回修改
            if (circulation == ReviewCirculation.SAMPLE_TO_SALESMAN){
                sample.setReviewStatus(CommissionStatus.RETURN_MODIFY);
            }else if (circulation == ReviewCirculation.SAMPLE_TO_CUSTOMER){
                //若返回给客户重新送样，样品状态置为审核受理不通过
                sample.setReviewStatus(CommissionStatus.REVIEW_NOT_ADOPT);
            }else if (circulation == ReviewCirculation.SAMPLE_TO_CUSTOMER_MODIFY){
                //若返回给客户修改委托，样品状态置为审核受理不通过
                sample.setReviewStatus(CommissionStatus.COMMISSION_LIST_MODIFY);
            }
            commissionSampleMapper.updateCommissionSample(sample);
            //更新委托流程
            progressService.inCommission(reviewList.get(i).getSampleID());
        }

        return reviewMapper.updateReviewByIDs(reviewIds,review);
    }

    /**
     * 查询分派审核列表
     * @param reviewVO 审核
     * @param status 审核状态
     * @return 审核集合
     */
    @Override
    public TableDataInfo selectAssignReviewList(ReviewVO reviewVO,int status) {
        //分页
        PagingUtils.startPage();
        List<ReviewVO> reviewVOList = null;
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        //查询样品审核
        if (status == ReviewStatus.REVIEWED){
            reviewVOList = reviewMapper.selectReviewVOList(reviewVO, ReviewCategory.ASSIGN_REVIEW);
        }else if (status == ReviewStatus.NOT_REVIEW){
            reviewVOList = reviewMapper.selectNotReviewVOList(reviewVO, ReviewCategory.ASSIGN_REVIEW);
        }
        //添加信息
        for (ReviewVO reviewVO1:reviewVOList){
            //分派人信息
            if(reviewVO1.getAssignorID() != null){
                reviewVO1.setAssignorName(userMapper.selectUserById(reviewVO1.getAssignorID()).getNickName());
            }

            //获取对应的样品对象
            CommissionSample commissionSample = commissionSampleMapper.selectCommissionSampleById(reviewVO1.getSampleID());
            //获取对应的任务分派
            TaskAssign taskAssign = taskAssignMapper.selectTaskAssignById(reviewVO1.getAssignOrResultID());

            reviewVO1.setSampleNumber(commissionSample.getSampleNumber());
            Map<String,Object> map = new HashMap<>();

            if (taskAssign != null){
                map.put("detectionMethod",taskAssign.getDetectionMethod());
                map.put("pesticideID",taskAssign.getPesticideID());
                map.put("pesticideName",pesticideMapper.selectPesticideById(taskAssign.getPesticideID(),detectionCenterID).getPesticideName());
                map.put("inspectorID",taskAssign.getOperatorID());
                map.put("inspectorName",userMapper.selectUserById(taskAssign.getInspectorID()).getNickName());
                map.put("detectionMethodName",detectionMethodMapper.selectDetectionMethodById(taskAssign.getDetectionMethod(),user.getDetectionCenterID()).getDetectionMethodName());

                //获取检测标准
                Standard standard = standardMapper.selectStandardById(commissionSample.getDetectionStandardID(), detectionCenterID);
                map.put("detectionStandard",standard.getStandardName());
            }

            reviewVO1.setSubject(map);
            //若是已经审核的，传入操作者信息
            if (reviewVO1.getOperatorID() != null){
                reviewVO1.setOperatorName(userMapper.selectUserById(reviewVO1.getOperatorID()).getNickName());
            }
        }

        return PagingUtils.getDataTable(reviewVOList);
    }

    /**
     * 分派审核通过
     * @param review 审核
     * @return 结果
     */
    @Override
    public int adoptAssignReview(Review review) {
        review.setOperationTime(new Date());
        review.setReviewConclusion(ReviewConclusion.PASS);
        review.setOperatorID(SecurityUtils.getLoginUser().getUser().getUserId());
        review.setCirculation(0);
        //添加当前用户电子签名
        review.setElectronicSignature(userMapper.selectUserById(SecurityUtils.getLoginUser().getUser().getUserId()).getElectronicSignature());
        Review review1 = reviewMapper.selectReviewById(review.getReviewID());

        //更新任务分派状态
        TaskAssign taskAssign = new TaskAssign();
        taskAssign.setTaskAssignID(review1.getAssignOrResultID());
        taskAssign.setReviewStatus(TaskAssignStatus.ASSIGN_REVIEW_PASS);
        taskAssignMapper.updateTaskAssign(taskAssign);

        //更新委托流程
        progressService.inDetectionResult(review1.getSampleID());

        return reviewMapper.updateReview(review);
    }

    /**
     * 分派审核不通过
     * @param review 审核
     * @return 结果
     */
    @Override
    public int failAssignReview(Review review) {
        Review review1 = reviewMapper.selectReviewById(review.getReviewID());

        review.setOperationTime(new Date());
        review.setReviewConclusion(ReviewConclusion.FAIL);
        review.setOperatorID(SecurityUtils.getLoginUser().getUser().getUserId());
        //添加当前用户电子签名
        review.setElectronicSignature(userMapper.selectUserById(SecurityUtils.getLoginUser().getUser().getUserId()).getElectronicSignature());

        //更新任务分派状态，状态为不通过
        TaskAssign taskAssign = new TaskAssign();
        taskAssign.setTaskAssignID(review1.getAssignOrResultID());

        taskAssign.setReviewStatus(TaskAssignStatus.ASSIGN_REVIEW_FAIL);
        taskAssign.setOperationTime(new Date());
        taskAssignMapper.updateTaskAssign(taskAssign);

        //更新委托流程
        progressService.inTaskAssign(review1.getSampleID());

        return reviewMapper.updateReview(review);
    }

    /**
     * 批量分派审核不通过
     * @param review 审核
     * @return 结果
     */
    @Override
    public int batchFailAssignReview(Review review, Long[] reviewIDs) {
        review.setOperationTime(new Date());
        review.setReviewConclusion(ReviewConclusion.FAIL);
        review.setOperatorID(SecurityUtils.getLoginUser().getUser().getUserId());
        //添加当前用户电子签名
        review.setElectronicSignature(userMapper.selectUserById(SecurityUtils.getLoginUser().getUser().getUserId()).getElectronicSignature());

        List<Review> reviewList = reviewMapper.selectReviewByIds(reviewIDs);

        //更新任务分派状态为不通过
        for(Review review1:reviewList){
            //更新任务分派状态
            TaskAssign taskAssign = new TaskAssign();
            taskAssign.setTaskAssignID(review1.getAssignOrResultID());

            taskAssign.setReviewStatus(TaskAssignStatus.ASSIGN_REVIEW_FAIL);
            taskAssign.setOperationTime(new Date());

            //更新委托流程
            progressService.inTaskAssign(review1.getSampleID());

            taskAssignMapper.updateTaskAssign(taskAssign);
        }

        return reviewMapper.updateReviewByIDs(reviewIDs,review);
    }

    /**
     * 查询检测结果审核列表
     * @param reviewVO 审核
     * @param status 审核状态
     * @return 审核集合
     */
    @Override
    public TableDataInfo selectResultReviewList(ReviewVO reviewVO,int status) {
        //分页
        PagingUtils.startPage();
        List<ReviewVO> reviewVOList = null;
        //查询检测结果审核
        if (status == ReviewStatus.REVIEWED){
            reviewVOList = reviewMapper.selectReviewVOList(reviewVO,ReviewCategory.RESULT_REVIEW);
        }else if (status == ReviewStatus.NOT_REVIEW){
            reviewVOList = reviewMapper.selectNotReviewVOList(reviewVO,ReviewCategory.RESULT_REVIEW);
        }
        //添加信息
        for (ReviewVO reviewVO1:reviewVOList){
            //检测人信息
            if(reviewVO1.getInspectorID() != null){
                reviewVO1.setInspectorName(userMapper.selectUserById(reviewVO1.getInspectorID()).getNickName());
            }

            //获取对应的样品对象
            CommissionSample commissionSample = commissionSampleMapper.selectCommissionSampleById(reviewVO1.getSampleID());
            //获取对应检测结果
            DetectionResult detectionResult = detectionResultMapper.selectDetectionResultById(reviewVO1.getAssignOrResultID());
            //ProjectAssignment projectAssignment = projectAssignmentMapper.selectProjectAssignmentById(review1.getAssignOrResultID());

            reviewVO1.setSampleNumber(commissionSample.getSampleNumber());
            reviewVO1.setPriority(commissionSample.getPriority());
            Map<String,Object> map = new HashMap<>();
            if (detectionResult != null){
                map.put("sampleNumber",detectionResult.getSampleNumber());
                SysUser user = userMapper.selectUserById(SecurityUtils.getLoginUser().getUser().getUserId());
                Long detectionCenterID = user.getDetectionCenterID();
                map.put("pesticideName",pesticideMapper.selectPesticideById(detectionResult.getPesticideID(),detectionCenterID).getPesticideName());
                map.put("detectionData",detectionResult.getDetectionData());
                map.put("inspectorName",userMapper.selectUserById(detectionResult.getInspectorID()).getNickName());
                //参考标准
                map.put("referenceStandard",pesticideMapper.selectPesticideById(detectionResult.getPesticideID(),detectionCenterID).getAdi());
            }

            reviewVO1.setSubject(map);
            //若是已经审核的，传入操作者信息
            if (reviewVO1.getOperatorID() != null){
                reviewVO1.setOperatorName(userMapper.selectUserById(reviewVO1.getOperatorID()).getNickName());
            }
        }
        return PagingUtils.getDataTable(reviewVOList);
    }

    /**
     * 检测结果审核通过
     * @param review 审核
     * @return 结果
     */
    @Override
    public int adoptResultReview(Review review) {
        //基本信息
        review.setOperationTime(new Date());
        review.setReviewConclusion(ReviewConclusion.PASS);
        review.setOperatorID(SecurityUtils.getLoginUser().getUser().getUserId());
        review.setCirculation(0);
        //添加当前用户电子签名
        review.setElectronicSignature(userMapper.selectUserById(SecurityUtils.getLoginUser().getUser().getUserId()).getElectronicSignature());

        //更新检测结果状态审核通过
        DetectionResult detectionResult = new DetectionResult();
        Review review1 = reviewMapper.selectReviewById(review.getReviewID());
        detectionResult.setDetectionResultID(review1.getAssignOrResultID());
        detectionResult.setAuditStatus(DetectionResultStatus.DETECTION_REVIEW_PASS);
        detectionResultMapper.updateDetectionResult(detectionResult);

        //更新委托流程
        progressService.inReport(review1.getSampleID());

        return reviewMapper.updateReview(review);
    }

    /**
     * 检测结果审核不通过，复检/重新分派
     * @param review 审核
     * @return 结果
     */
    @Override
    public int failResultReview(Review review, int circulation) {
        review.setOperationTime(new Date());
        review.setReviewConclusion(ReviewConclusion.FAIL);
        review.setCirculation(circulation);
        review.setOperatorID(SecurityUtils.getLoginUser().getUser().getUserId());
        //添加当前用户电子签名
        review.setElectronicSignature(userMapper.selectUserById(SecurityUtils.getLoginUser().getUser().getUserId()).getElectronicSignature());

        Review review1 = reviewMapper.selectReviewById(review.getReviewID());
        //退回复检
        if (circulation == ReviewCirculation.RESULT_TO_INSPECTOR){
            //更新检测结果状态为复检，审核不通过
            DetectionResult detectionResult = new DetectionResult();
            detectionResult.setDetectionResultID(review1.getAssignOrResultID());
            //审核状态不通过
            detectionResult.setAuditStatus(DetectionResultStatus.DETECTION_REVIEW_FAIL);
            //置空
            detectionResult.setDetectionData(new BigDecimal(0));
            //更新委托流程
            progressService.inDetectionResult(review1.getSampleID());
            detectionResultMapper.updateDetectionResult(detectionResult);

        }else if (circulation == ReviewCirculation.RESULT_TO_ASSIGN){
            //获取当前检测结果记录
            DetectionResult detectionResult = detectionResultMapper.selectDetectionResultById(review1.getAssignOrResultID());

            //更新任务分派状态
            TaskAssign taskAssign = new TaskAssign();
            //获取结果对应的任务分派id
            taskAssign.setTaskAssignID(detectionResult.getTaskAssignID());

            taskAssign.setReviewStatus(TaskAssignStatus.RESULT_REVIEW_FAIL);
            taskAssign.setOperationTime(new Date());
            taskAssignMapper.updateTaskAssign(taskAssign);
            //更新委托流程
            progressService.inTaskAssign(review1.getSampleID());
            //删除检测结果记录
            detectionResultMapper.deleteDetectionResultById(detectionResult.getDetectionResultID());
        }
        return reviewMapper.updateReview(review);
    }

    /**
     * 批量检测结果审核不通过，复检/重新分派
     * @param review 审核
     * @param circulation 流转标识
     * @return 结果
     */
    @Override
    public int batchFailResultReview(Review review, Long[] reviewIDs, int circulation) {
        review.setOperationTime(new Date());
        review.setReviewConclusion(ReviewConclusion.FAIL);
        review.setCirculation(circulation);
        review.setOperatorID(SecurityUtils.getLoginUser().getUser().getUserId());
        //添加当前用户电子签名
        review.setElectronicSignature(userMapper.selectUserById(SecurityUtils.getLoginUser().getUser().getUserId()).getElectronicSignature());

        List<Review> reviewList = reviewMapper.selectReviewByIds(reviewIDs);

        //退回复检
        if (circulation == ReviewCirculation.RESULT_TO_INSPECTOR){
            for (Review review1:reviewList){
                //更新检测结果状态为复检，审核不通过
                DetectionResult detectionResult = new DetectionResult();
                detectionResult.setDetectionResultID(review1.getAssignOrResultID());
                //审核状态不通过
                detectionResult.setAuditStatus(DetectionResultStatus.DETECTION_REVIEW_FAIL);
                //置空
                detectionResult.setDetectionData(new BigDecimal(0));
                //更新委托流程
                progressService.inDetectionResult(review1.getSampleID());
                detectionResultMapper.updateDetectionResult(detectionResult);
            }
        }else if (circulation == ReviewCirculation.RESULT_TO_ASSIGN){
            for (Review review1:reviewList){
                //获取当前检测结果记录
                DetectionResult detectionResult = detectionResultMapper.selectDetectionResultById(review1.getAssignOrResultID());

                //更新任务分派状态
                TaskAssign taskAssign = new TaskAssign();
                //获取结果对应的任务分派id
                taskAssign.setTaskAssignID(detectionResult.getTaskAssignID());

                taskAssign.setReviewStatus(TaskAssignStatus.RESULT_REVIEW_FAIL);
                taskAssign.setOperationTime(new Date());
                taskAssignMapper.updateTaskAssign(taskAssign);
                //更新委托流程
                progressService.inTaskAssign(review1.getSampleID());
                //删除检测结果记录
                detectionResultMapper.deleteDetectionResultById(detectionResult.getDetectionResultID());
            }

        }
        return reviewMapper.updateReviewByIDs(reviewIDs,review);
    }

    /**
     * 报告审核列表
     * @param reviewVO 审核
     * @param status 审核状态
     * @return 审核集合
     */
    @Override
    public TableDataInfo selectReportReviewList(ReviewVO reviewVO,int status) {
        //分页
        PagingUtils.startPage();
        List<ReviewVO> reviewVOList = null;
        //查询报告审核
        if (status == ReviewStatus.REVIEWED){
            reviewVOList = reviewMapper.selectReviewVOList(reviewVO,ReviewCategory.REPORT_REVIEW);
        }else if (status == ReviewStatus.NOT_REVIEW){
            reviewVOList = reviewMapper.selectNotReviewVOList(reviewVO,ReviewCategory.REPORT_REVIEW);
        }
        //添加信息
        for (ReviewVO reviewVO1:reviewVOList){
            //撰写人人信息
            if(reviewVO1.getReportWriterID() != null){
                reviewVO1.setReportWriterName(userMapper.selectUserById(reviewVO1.getReportWriterID()).getNickName());
            }
            //获取对应的样品对象
            CommissionSample commissionSample = commissionSampleMapper.selectCommissionSampleById(reviewVO1.getSampleID());

            //检测报告数据
            reviewVO1.setSampleNumber(commissionSample.getSampleNumber());
            reviewVO1.setPriority(commissionSample.getPriority());
            Map<String,Object> map = new HashMap<>();

            //获取检测标准
            if (commissionSample.getDetectionStandardID() != null){
                SysUser user = SecurityUtils.getLoginUser().getUser();
                Long detectionCenterID = user.getDetectionCenterID();
                Standard standard = standardMapper.selectStandardById(commissionSample.getDetectionStandardID(),detectionCenterID);
                map.put("detectionStandard", standard.getStandardName());
            }
            reviewVO1.setSubject(map);
            //若是已经审核的，传入操作者信息
            if (reviewVO1.getOperatorID() != null){
                reviewVO1.setOperatorName(userMapper.selectUserById(reviewVO1.getOperatorID()).getNickName());
            }
        }

        return PagingUtils.getDataTable(reviewVOList);
    }

    /**
     * 报告审核通过
     * @param review 审核
     * @return 结果
     */
    @Override
    public int adoptReportReview(Review review) {
        //基本信息
        review.setOperationTime(new Date());
        review.setReviewConclusion(ReviewConclusion.PASS);
        review.setOperatorID(SecurityUtils.getLoginUser().getUser().getUserId());
        review.setCirculation(0);
        //添加当前用户电子签名
        review.setElectronicSignature(userMapper.selectUserById(SecurityUtils.getLoginUser().getUser().getUserId()).getElectronicSignature());

        //更新报告信息
        Report report = new Report();
        Review review1 = reviewMapper.selectReviewById(review.getReviewID());
        report.setReportID(review1.getAssignOrResultID());
        report.setReportStatus(ReportStatus.REPORT_PASS);
        report.setReviewerID(SecurityUtils.getLoginUser().getUser().getUserId());
        report.setReviewerTime(new Date());
        //判定结论
        if (review.getRemarks() != null){
            report.setReportResult(Integer.valueOf(review.getRemarks()));
        }

        //更新委托流程
        progressService.inReportSign(review1.getSampleID());

        reportMapper.updateReport(report);

        return reviewMapper.updateReview(review);
    }

    /**
     * 报告审核不通过，退回报告撰写人/检测员/任务分派
     * @param review 审核
     * @return 结果
     */
    @Override
    public int failReportReview(Review review, int circulation) {
        review.setOperationTime(new Date());
        review.setReviewConclusion(ReviewConclusion.FAIL);
        review.setCirculation(circulation);
        review.setOperatorID(SecurityUtils.getLoginUser().getUser().getUserId());
        //添加当前用户电子签名
        review.setElectronicSignature(userMapper.selectUserById(SecurityUtils.getLoginUser().getUser().getUserId()).getElectronicSignature());

        Report report = new Report();
        Review review1 = reviewMapper.selectReviewById(review.getReviewID());
        report.setReportID(review1.getAssignOrResultID());
        report.setReportStatus(ReportStatus.REPORT_FAIL);
        report.setReviewerID(SecurityUtils.getLoginUser().getUser().getUserId());
        report.setReviewerTime(new Date());

        DetectionResult detectionResult = new DetectionResult();
        detectionResult.setIsChosen(DetectionResultStatus.DETECTION_ISCHOSEN);
        detectionResult.setSampleID(review1.getSampleID());
        //查询被选中用于生成报告的数据记录
        List<DetectionResult> detectionResultList = detectionResultMapper.selectDetectionResultList(detectionResult);
        detectionResult.setIsChosen(DetectionResultStatus.DETECTION_NOCHOSEN);

        //退回报告撰写人
        if (circulation == ReviewCirculation.REPORT_TO_WRITER){
            //更新检测结果表
            for (DetectionResult result : detectionResultList) {
                detectionResult.setDetectionResultID(result.getDetectionResultID());
                detectionResultMapper.updateDetectionResult(detectionResult);
            }
            //更新委托流程
            progressService.inReport(review1.getSampleID());
        }else if(circulation == ReviewCirculation.REPORT_TO_INSPECTOR){
            //退回复检
            //检测结果审核状态不通过
            detectionResult.setAuditStatus(DetectionResultStatus.DETECTION_REVIEW_FAIL);
            //置空
            detectionResult.setDetectionData(new BigDecimal(0));
            //更新检测结果表
            for (DetectionResult result : detectionResultList) {
                detectionResult.setDetectionResultID(result.getDetectionResultID());
                detectionResultMapper.updateDetectionResult(detectionResult);
            }
            //更新委托流程
            progressService.inDetectionResult(review1.getSampleID());
        }else if (circulation == ReviewCirculation.REPORT_TO_ASSIGN){
            //退回任务分派
            Long[] resultIDs = new Long[detectionResultList.size()];

            for (int i = 0;i<detectionResultList.size();i++){
                resultIDs[i] = detectionResultList.get(i).getDetectionResultID();
                //更新任务分派状态
                TaskAssign taskAssign = new TaskAssign();
                //获取结果对应的任务分派id
                taskAssign.setTaskAssignID(detectionResultList.get(i).getTaskAssignID());
                taskAssign.setReviewStatus(TaskAssignStatus.RESULT_REVIEW_FAIL);
                taskAssign.setOperationTime(new Date());
                taskAssignMapper.updateTaskAssign(taskAssign);
            }
            //更新委托流程
            progressService.inTaskAssign(review1.getSampleID());
            //删除检测结果记录
            detectionResultMapper.deleteDetectionResultByIds(resultIDs);
        }
        reportMapper.updateReport(report);

        return reviewMapper.updateReview(review);
    }

    /**
     * 批量报告审核不通过，退回报告撰写人/检测员/任务分派
     * @param review 审核
     * @param circulation 流转标识
     * @return 结果
     */
    @Override
    public int batchFailReportReview(Review review, Long[] reviewIDs, int circulation) {
        review.setOperationTime(new Date());
        review.setReviewConclusion(ReviewConclusion.FAIL);
        review.setCirculation(circulation);
        review.setOperatorID(SecurityUtils.getLoginUser().getUser().getUserId());
        //添加当前用户电子签名
        review.setElectronicSignature(userMapper.selectUserById(SecurityUtils.getLoginUser().getUser().getUserId()).getElectronicSignature());

        List<DetectionResult> detectionResultList = new ArrayList<>();

        List<Review> reviewList = reviewMapper.selectReviewByIds(reviewIDs);
        for (Review review1:reviewList){
            Report report = new Report();
            report.setReportID(review1.getAssignOrResultID());
            report.setReportStatus(ReportStatus.REPORT_FAIL);
            report.setReviewerID(SecurityUtils.getLoginUser().getUser().getUserId());
            report.setReviewerTime(new Date());

            //获取所有对应的检测结果
            DetectionResult detectionResult = new DetectionResult();
            detectionResult.setSampleID(review1.getSampleID());
            detectionResult.setIsChosen(1);
            detectionResultList.addAll(detectionResultMapper.selectDetectionResultList(detectionResult));

            reportMapper.updateReport(report);
        }

        //退回报告撰写人
        if (circulation == ReviewCirculation.REPORT_TO_WRITER){
            //更新检测结果表
            for (DetectionResult result : detectionResultList) {
                DetectionResult detectionResult = new DetectionResult();
                detectionResult.setDetectionResultID(result.getDetectionResultID());
                detectionResult.setIsChosen(0);
                detectionResultMapper.updateDetectionResult(detectionResult);
            }
            for (Review review1:reviewList){
                //更新委托流程
                progressService.inReport(review1.getSampleID());
            }
        }else if(circulation == ReviewCirculation.REPORT_TO_INSPECTOR){
            //退回复检
            //检测结果审核状态不通过
            DetectionResult detectionResult = new DetectionResult();
            detectionResult.setAuditStatus(DetectionResultStatus.DETECTION_REVIEW_FAIL);
            //置空
            detectionResult.setDetectionData(new BigDecimal(0));
            //更新检测结果表
            for (DetectionResult result : detectionResultList) {
                detectionResult.setDetectionResultID(result.getDetectionResultID());
                detectionResult.setIsChosen(0);
                detectionResultMapper.updateDetectionResult(detectionResult);
            }
            for (Review review1:reviewList){
                //更新委托流程
                progressService.inDetectionResult(review1.getSampleID());
            }
        }else if (circulation == ReviewCirculation.REPORT_TO_ASSIGN){
            //退回任务分派
            Long[] resultIDs = new Long[detectionResultList.size()];

            for (int i = 0;i<detectionResultList.size();i++){
                resultIDs[i] = detectionResultList.get(i).getDetectionResultID();
                //更新任务分派状态
                TaskAssign taskAssign = new TaskAssign();
                //获取结果对应的任务分派id
                taskAssign.setTaskAssignID(detectionResultList.get(i).getTaskAssignID());
                taskAssign.setReviewStatus(TaskAssignStatus.RESULT_REVIEW_FAIL);
                taskAssign.setOperationTime(new Date());
                taskAssignMapper.updateTaskAssign(taskAssign);
            }
            for (Review review1:reviewList){
                //更新委托流程
                progressService.inTaskAssign(review1.getSampleID());
            }
            //删除检测结果记录
            detectionResultMapper.deleteDetectionResultByIds(resultIDs);
        }
        return reviewMapper.updateReviewByIDs(reviewIDs,review);
    }

    /**
     * 获取审核员列表
     * @param roleID 角色ID
     * @return  map 审核员 ID、姓名
     */
    @Override
    public Map<Long, String> selectReviewerList(Long roleID) {
        SysUserRole[] sysUserRoles = userRoleMapper.selectUserByRoleID(roleID);
        Map<Long, String> map = new HashMap<>();
        for (SysUserRole sysUserRole:sysUserRoles){
            map.put(sysUserRole.getUserId(),userMapper.selectUserById(sysUserRole.getUserId()).getNickName());
        }
        return map;
    }

    /**
     * 批量审核通过
     * @param reviewIDs 审核ID列表
     * @param category 审核类别
     * @return 结果
     */
    @Override
    public int batchReviewPass(Long[] reviewIDs,int category,Review review) {
        review.setOperatorID(SecurityUtils.getLoginUser().getUser().getUserId());
        //添加当前用户电子签名
        review.setElectronicSignature(userMapper.selectUserById(SecurityUtils.getLoginUser().getUser().getUserId()).getElectronicSignature());
        int res = reviewMapper.batchReviewPass(reviewIDs,review);

        //检品审核通过
        if (IntUtils.IntArrLookupInt(ReviewCategory.SAMPLE_REVIEW,category)){
            List<Review> reviewList = reviewMapper.selectReviewByIds(reviewIDs);
            //去重
            Set<Long> sampleIDs = new HashSet<>();
            for (Review review1 : reviewList) {
                sampleIDs.add(review1.getSampleID());
            }
            for (Long sampleID:sampleIDs){
                //更新委托流程
                progressService.inTaskAssign(sampleID);
                CommissionSample commissionSample = new CommissionSample();
                commissionSample.setSampleID(sampleID);
                commissionSample.setReviewStatus(CommissionStatus.REVIEW_ADOPT);
                //更新委托样品状态
                commissionSampleMapper.updateCommissionSample(commissionSample);
            }
        }else if (IntUtils.IntArrLookupInt(ReviewCategory.ASSIGN_REVIEW,category)){
            //更新分派审核通过
            List<Review> reviewList = reviewMapper.selectReviewByIds(reviewIDs);
            //更新任务分派状态
            for (Review review1:reviewList){
                TaskAssign taskAssign = new TaskAssign();
                taskAssign.setTaskAssignID(review1.getAssignOrResultID());
                taskAssign.setReviewStatus(TaskAssignStatus.ASSIGN_REVIEW_PASS);
                //更新委托流程
                progressService.inDetectionResult(review1.getSampleID());
                taskAssignMapper.updateTaskAssign(taskAssign);
            }
        }else if (IntUtils.IntArrLookupInt(ReviewCategory.RESULT_REVIEW,category)){
            //检测结果审核通过
            List<Review> reviewList = reviewMapper.selectReviewByIds(reviewIDs);
            for (Review review1:reviewList){
                //更新检测结果状态审核通过
                DetectionResult detectionResult = new DetectionResult();
                detectionResult.setDetectionResultID(review1.getAssignOrResultID());
                detectionResult.setAuditStatus(DetectionResultStatus.DETECTION_REVIEW_PASS);
                //更新委托流程
                progressService.inReport(review1.getSampleID());
                detectionResultMapper.updateDetectionResult(detectionResult);
            }
        }else if (IntUtils.IntArrLookupInt(ReviewCategory.REPORT_REVIEW,category)){
            //更新报告审核通过
            List<Review> reviewList = reviewMapper.selectReviewByIds(reviewIDs);
            for (Review review1:reviewList){
                Report report = new Report();
                report.setReportID(reviewMapper.selectReviewById(review1.getReviewID()).getAssignOrResultID());
                report.setReportStatus(ReportStatus.REPORT_PASS);
                //判定结论
                if (review.getRemarks() != null){
                    report.setReportResult(Integer.valueOf(review.getRemarks()));
                }
                report.setReviewerID(SecurityUtils.getLoginUser().getUser().getUserId());
                report.setReviewerTime(new Date());
                //判定结论
                if (review1.getRemarks() != null){
                    report.setReportResult(Integer.valueOf(review1.getRemarks()));
                }

                //更新委托流程
                progressService.inReportSign(review1.getSampleID());

                reportMapper.updateReport(report);
            }
        }
        return res;
    }

    /**
     * 查询审核工作量
     * @param operatorID 审核员ID
     * @param days 工作量查询天数
     * @param reviewConclusion 审核结论（1-通过，2-不通过）
     * @return 结果
     */
    @Override
    public int countWorkLoad(Long operatorID, int days, int reviewConclusion){
        return reviewMapper.countWorkLoad(SecurityUtils.getLoginUser().getUser().getUserId(),days,reviewConclusion);
    }
}
