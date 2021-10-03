package com.ruoyi.detection.mapper;

import com.ruoyi.detection.domain.DetectionResult;
import com.ruoyi.detection.domain.vo.DetectionResultVO;

import java.util.List;

/**
 * 检测结果Mapper接口
 * 
 * @author zhangkui
 * @date 2021-08-01
 */
public interface DetectionResultMapper 
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
     * 查询检测结果列表
     *
     * @param detectionResultVO 检测结果
     * @return 检测结果VO
     */
    List<DetectionResultVO> selectResultVOList(DetectionResultVO detectionResultVO);

    /**
     * 查询复检结果
     * @param detectionResultVO
     * @return
     */
    List<DetectionResultVO> selectReResultVOList(DetectionResultVO detectionResultVO);



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
     * 删除检测结果
     * 
     * @param detectionResultID 检测结果ID
     * @return 结果
     */
    public int deleteDetectionResultById(Long detectionResultID);

    /**
     * 批量删除检测结果
     * 
     * @param detectionResultIDs 需要删除的数据ID
     * @return 结果
     */
    public int deleteDetectionResultByIds(Long[] detectionResultIDs);




}
