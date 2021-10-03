package com.ruoyi.review.mapper;

import java.util.List;
import com.ruoyi.review.domain.Review;
import com.ruoyi.review.domain.VO.ReviewVO;
import org.apache.ibatis.annotations.Param;

/**
 * 审核Mapper接口
 *
 * @author zjccc
 * @date 2021-07-31
 */
public interface ReviewMapper
{
    /**
     * 查询审核
     * @param reviewID 审核ID
     * @return 审核
     */
    public Review selectReviewById(Long reviewID);

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
     * 删除审核
     *
     * @param reviewID 审核ID
     * @return 结果
     */
    public int deleteReviewById(Long reviewID);

    /**
     * 批量删除审核
     *
     * @param reviewIDs 需要删除的数据ID
     * @return 结果
     */
    public int deleteReviewByIds(Long[] reviewIDs);

    /**
     * 通过类别查询检品审核列表
     * @param review 审核
     * @param categories 类别
     * @return 审核集合
     */
    List<Review> selectSampleReviewListByCategory(@Param("review")Review review, @Param("categories")int[] categories);

    /**
     * 查询审核列表
     * @param reviewVO 审核
     * @return 审核集合
     */
    List<ReviewVO> selectReviewVOList(@Param("reviewVO")ReviewVO reviewVO,@Param("categories")int[] categories);

    /**
     * 查询未审核列表
     * @param reviewVO 审核
     * @return 审核集合
     */
    List<ReviewVO> selectNotReviewVOList(@Param("reviewVO")ReviewVO reviewVO,@Param("categories")int[] categories);

    /**
     * 批量审核通过
     * @param reviewIDs 审核ID列表
     * @param review  审核
     * @return 结果
     */
    int batchReviewPass(@Param("reviewIDs")Long[] reviewIDs,@Param("review")Review review);

    /**
     * 批量查询审核
     * @param reviewIDs 需要查询的数据ID
     * @return 结果
     */
    List<Review> selectReviewByIds(Long[] reviewIDs);

    /**
     * 通过审核ID数组修改审核
     * @param review 审核
     * @return 结果
     */
    int updateReviewByIDs(@Param("reviewIDs")Long[] reviewIds,@Param("review")Review review);

    /**
     * 查询审核工作量
     * @param operatorID 审核员ID
     * @param days 工作量查询天数
     * @param reviewConclusion 审核结论（1-通过，2-不通过）
     * @return 结果
     */
    int countWorkLoad(@Param("operatorID")Long operatorID,@Param("days")int days,@Param("reviewConclusion")int reviewConclusion);
}
