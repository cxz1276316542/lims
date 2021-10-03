package com.ruoyi.review.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.review.domain.Review;
import com.ruoyi.review.domain.VO.ReviewVO;

/**
 * 审核Service接口
 *
 * @author zjccc
 * @date 2021-07-31
 */
public interface IReviewService
{
    /**
     * 查询审核
     * @param reviewID 审核ID
     * @return 审核
     */
    public Map<String,Object>  selectReviewInfoById(Long reviewID);

    /**
     * 查询审核列表
     *
     * @param review 审核
     * @return 审核集合
     */
    public List<Review> selectReviewList(Review review);

    /**
     * 新增审核
     *
     * @param review 审核
     * @return 结果
     */
    public int insertReview(Review review);

    /**
     * 修改审核
     *
     * @param review 审核
     * @return 结果
     */
    public int updateReview(Review review);

    /**
     * 批量删除审核
     *
     * @param reviewIDs 需要删除的审核ID
     * @return 结果
     */
    public int deleteReviewByIds(Long[] reviewIDs);

    /**
     * 删除审核信息
     *
     * @param reviewID 审核ID
     * @return 结果
     */
    public int deleteReviewById(Long reviewID);


    /**
     * 查询检品审核列表
     * @param reviewVO 审核
     * @param status 审核状态
     * @return 审核集合
     */
    TableDataInfo selectSampleReviewList(ReviewVO reviewVO,int status);

    /**
     * 检品审核通过
     * @param review 审核
     * @return 审核集合
     */
    int adoptSampleReview(Review review);

    /**
     * 检品审核不通过
     * @param review 审核
     * @param circulation 流转标识
     * @return 结果
     */
    int failSampleReview(Review review,int circulation);

    /**
     * 批量检品审核不通过
     * @param review 审核
     * @param circulation 流转标识
     * @return 结果
     */
    int batchFailSampleReview(Review review,Long[] reviewIds ,int circulation);

    /**
     * 查询分配审核列表
     * @param reviewVO 审核
     * @param status 审核状态
     * @return 审核集合
     */
    TableDataInfo selectAssignReviewList(ReviewVO reviewVO,int status);

    /**
     * 分派审核通过
     * @param review 审核
     * @return 结果
     */
    public int adoptAssignReview(Review review);

    /**
     * 分派审核不通过
     * @param review 审核
     * @return 结果
     */
    public int failAssignReview(Review review);

    /**
     * 批量分派审核不通过
     * @param review 审核
     * @return 结果
     */
    int batchFailAssignReview(Review review, Long[] reviewIDs);

    /**
     * 查询检测结果审核列表
     * @param reviewVO 审核
     * @param status 审核状态
     * @return 审核集合
     */
    TableDataInfo selectResultReviewList(ReviewVO reviewVO,int status);

    /**
     * 检测结果审核通过
     * @param review 审核
     * @return 结果
     */
    int adoptResultReview(Review review);

    /**
     * 检测结果审核不通过，复检/重新分派
     * @param review 审核
     * @param circulation 流转标识
     * @return 结果
     */
    int failResultReview(Review review, int circulation);

    /**
     * 批量检测结果审核不通过，复检/重新分派
     * @param review 审核
     * @param circulation 流转标识
     * @return 结果
     */
    int batchFailResultReview(Review review, Long[] reviewIDs, int circulation);

    /**
     * 报告审核列表
     * @param reviewVO 审核
     * @param status 审核状态
     * @return 审核集合
     */
    TableDataInfo selectReportReviewList(ReviewVO reviewVO,int status);

    /**
     * 报告审核通过
     * @param review 审核
     * @return 结果
     */
    int adoptReportReview(Review review);

    /**
     * 报告审核不通过，退回报告撰写人/检测员/任务分派
     * @param review 审核
     * @param circulation 流转标识
     * @return 结果
     */
    int failReportReview(Review review, int circulation);

    /**
     * 批量报告审核不通过，退回报告撰写人/检测员/任务分派
     * @param review 审核
     * @param circulation 流转标识
     * @return 结果
     */
    int batchFailReportReview(Review review, Long[] reviewIDs, int circulation);


    /**
     * 获取审核员列表
     * @param roleID
     * @return  map 审核员 ID、姓名
     */
    Map<Long,String> selectReviewerList(Long roleID);

    /**
     * 批量审核通过
     * @param reviewIDs 审核ID列表
     * @param review 审核
     * @return 结果
     */
    int batchReviewPass(Long[] reviewIDs,int category,Review review);

    /**
     * 查询已审核信息
     * @param reviewID 审核ID
     * @return 审核
     */
    Map<String,Object> selectReviewedInfoById(Long reviewID);

    /**
     * 查询报告撰写人员信息
     * @return 审核
     */
    Map<Long, String> selectInventoryManager();

    /**
     * 查询审核工作量
     * @param operatorID 审核员ID
     * @param days 工作量查询天数
     * @param reviewConclusion 审核结论（1-通过，2-不通过）
     * @return 结果
     */
    int countWorkLoad(Long operatorID,int days, int reviewConclusion);

    /**
     * 查询已审核基本信息
     * @param reviewID 审核ID
     * @return 审核
     */
    Map<String,Object> selectReviewUpdateInfoById(Long reviewID);
}
