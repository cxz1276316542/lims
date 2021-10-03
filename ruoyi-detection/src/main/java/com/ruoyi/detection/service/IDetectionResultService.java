package com.ruoyi.detection.service;

import java.util.List;
import com.ruoyi.detection.domain.DetectionResult;
import com.ruoyi.detection.domain.vo.DetectionResultVO;

/**
 * 检测结果Service接口
 * 
 * @author zhangkui
 * @date 2021-08-01
 */
public interface IDetectionResultService 
{
    /**
     * 查询检测结果
     * 
     * @param detectionResultID 检测结果ID
     * @return 检测结果
     */
    public DetectionResult selectDetectionResultById(Long detectionResultID);

    /**
     * 查询检测结果列表
     * 
     * @param detectionResult 检测结果
     * @return 检测结果集合
     */
    public List<DetectionResult> selectDetectionResultList(DetectionResult detectionResult);

    /**
     * 新增检测结果
     * 
     * @param detectionResult 检测结果
     * @return 结果
     */
    public int insertDetectionResult(DetectionResult detectionResult);

    /**
     * 修改检测结果
     * 
     * @param detectionResult 检测结果
     * @return 结果
     */
    public int updateDetectionResult(DetectionResult detectionResult);

    /**
     * 批量删除检测结果
     * 
     * @param detectionResultIDs 需要删除的检测结果ID
     * @return 结果
     */
    public int deleteDetectionResultByIds(Long[] detectionResultIDs);

    /**
     * 删除检测结果信息
     * 
     * @param detectionResultID 检测结果ID
     * @return 结果
     */
    public int deleteDetectionResultById(Long detectionResultID);


    /**
     * 查询初检
     * @param detectionResultVO
     * @return
     */
    List<DetectionResultVO> selectResultVOList(DetectionResultVO detectionResultVO);


    /**
     * 提交审核
     * @param detectionResultVOList
     * @return
     */
    int submitReviewMethod(List<DetectionResultVO> detectionResultVOList);

    /**
     * 查询复检
     * @param detectionResultVO
     * @return
     */
    List<DetectionResultVO> selectReResultVOList(DetectionResultVO detectionResultVO);

    /**
     * 提交复检
     * @param detectionResultVOList
     * @return
     */
    int submitReReviewMethod(List<DetectionResultVO> detectionResultVOList);
}
