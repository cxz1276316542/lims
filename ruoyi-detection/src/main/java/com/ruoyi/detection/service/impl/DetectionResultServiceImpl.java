package com.ruoyi.detection.service.impl;

import com.ruoyi.detection.constant.DetectionResultStatus;
import com.ruoyi.detection.domain.DetectionResult;
import com.ruoyi.detection.domain.vo.DetectionResultVO;
import com.ruoyi.detection.mapper.DetectionResultMapper;
import com.ruoyi.detection.service.IDetectionResultService;
import com.ruoyi.review.domain.Review;
import com.ruoyi.review.mapper.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 检测结果Service业务层处理
 * 
 * @author zhangkui
 * @date 2021-08-01
 */
@Service
public class DetectionResultServiceImpl implements IDetectionResultService
{
    @Autowired
    private DetectionResultMapper detectionResultMapper;

    @Autowired
    private ReviewMapper reviewMapper;
    /**
     * 查询检测结果
     * 
     * @param detectionResultID 检测结果ID
     * @return 检测结果
     */
    @Override
    public DetectionResult selectDetectionResultById(Long detectionResultID)
    {
        return detectionResultMapper.selectDetectionResultById(detectionResultID);
    }

    /**
     * 查询检测结果列表
     * 
     * @param detectionResult 检测结果
     * @return 检测结果
     */
    @Override
    public List<DetectionResult> selectDetectionResultList(DetectionResult detectionResult)
    {
        return detectionResultMapper.selectDetectionResultList(detectionResult);
    }

    /**
     * 查询检测结果VO列表
     *
     * @param detectionResultVO 检测结果
     * @return 检测结果VO
     */
    @Transactional
    @Override
    public List<DetectionResultVO> selectResultVOList(DetectionResultVO detectionResultVO) {
//        //查出初检不通过的审核记录
//        Review review =new Review();
//        review.setReviewConclusion(ReviewConclusion.FAIL);
//        review.setCirculation(ReviewCirculation.RESULT_TO_INSPECTOR);
//        List<Review> reviews = reviewMapper.selectReviewList(review);
//        Set<Long> set=new HashSet<>();
//        for (Review re : reviews) {
//            set.add(re.getAssignOrResultID());
//        }
//        List<DetectionResultVO> detectionResultVOList = detectionResultMapper.selectResultVOList(detectionResultVO);
//        List<DetectionResultVO> ansList=new ArrayList<>();
//        if (detectionResultVOList.size()!=0){
//            for (DetectionResultVO resultVO : detectionResultVOList) {
//                //不展示审核不通过的
//                if(!set.contains(resultVO.getDetectionResultID())){
//                    if (resultVO.getAuditStatus()==0||resultVO.getAuditStatus()==1){
//                        ansList.add(resultVO);
//                    }
//                }
//            }
//        }
//        return ansList;

        List<DetectionResultVO> detectionResultVOList = detectionResultMapper.selectResultVOList(detectionResultVO);
        System.out.println(detectionResultVOList.size());
        return  detectionResultVOList;

    }


    /**
     * 查询需要复检的项目
     * @param detectionResultVO
     * @return
     */

    @Transactional
    @Override
    public List<DetectionResultVO> selectReResultVOList(DetectionResultVO detectionResultVO) {

//        Review review =new Review();
//        //查出初检不同过的审核记录
//        review.setReviewConclusion(ReviewConclusion.FAIL);
//        review.setCirculation(ReviewCirculation.RESULT_TO_INSPECTOR);
//        List<Review> reviews = reviewMapper.selectReviewList(review);
//        List<DetectionResultVO> detectionResultVOList=new ArrayList<>();
//        Set<Long> set=new HashSet<>();
//        for (Review re : reviews) {
//            if(set.add(re.getAssignOrResultID())) {
//                detectionResultVO.setDetectionResultID(re.getAssignOrResultID());
//                List<DetectionResultVO> detectionResultVOList1 = detectionResultMapper.selectResultVOList(detectionResultVO);
//                if (detectionResultVOList1.size() == 0) break;
//                detectionResultVOList.add(detectionResultVOList1.get(0));
//            }
//        }
//        return detectionResultVOList;
        return detectionResultMapper.selectReResultVOList(detectionResultVO);

    }

    @Transactional
    @Override
    public int submitReviewMethod(List<DetectionResultVO> detectionResultVOList) {
        for (DetectionResultVO detectionResultVO : detectionResultVOList) {
            DetectionResult detectionResult = detectionResultMapper.selectDetectionResultById(detectionResultVO.getDetectionResultID());
            //将检测结果状态更新为3，提交审核
            detectionResult.setAuditStatus(DetectionResultStatus.DETECTION_SUBMIT_ONE);
            detectionResultMapper.updateDetectionResult(detectionResult);
            //往审核表里插入初检数据
            Review review = new Review(detectionResult.getSampleID(),detectionResultVO.getPriority(),detectionResult.getDetectionResultID(),31,new Date());
            reviewMapper.insertReview(review);
        }
        return 1;
    }

    @Transactional
    @Override
    public int submitReReviewMethod(List<DetectionResultVO> detectionResultVOList) {

        for (DetectionResultVO detectionResultVO : detectionResultVOList) {
            DetectionResult detectionResult = detectionResultMapper.selectDetectionResultById(detectionResultVO.getDetectionResultID());
            //将检测结果状态更新为二次提交；
            detectionResult.setAuditStatus(DetectionResultStatus.DETECTION_SUBMIT_TWO);
            detectionResultMapper.updateDetectionResult(detectionResult);
            //往审核表里插入复检数据
            Review review = new Review(detectionResult.getSampleID(),detectionResultVO.getPriority(),detectionResult.getDetectionResultID(),32,new Date());
            reviewMapper.insertReview(review);
        }
        return 1;

    }

    /**
     * 新增检测结果
     * 
     * @param detectionResult 检测结果
     * @return 结果
     */
    @Transactional
    @Override
    public int insertDetectionResult(DetectionResult detectionResult)
    {
        return detectionResultMapper.insertDetectionResult(detectionResult);
    }

    /**
     * 修改检测结果
     * 
     * @param detectionResult 检测结果
     * @return 结果
     */

    @Override
    public int updateDetectionResult(DetectionResult detectionResult)
    {
        return detectionResultMapper.updateDetectionResult(detectionResult);
    }

    /**
     * 批量删除检测结果
     * 
     * @param detectionResultIDs 需要删除的检测结果ID
     * @return 结果
     */
    @Override
    public int deleteDetectionResultByIds(Long[] detectionResultIDs)
    {
        return detectionResultMapper.deleteDetectionResultByIds(detectionResultIDs);
    }

    /**
     * 删除检测结果信息
     * 
     * @param detectionResultID 检测结果ID
     * @return 结果
     */
    @Override
    public int deleteDetectionResultById(Long detectionResultID)
    {
        return detectionResultMapper.deleteDetectionResultById(detectionResultID);
    }


}
