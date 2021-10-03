package com.ruoyi.detection.mapper;

import java.util.List;
import com.ruoyi.detection.domain.DetectionStatus;

/**
 * 样品检测状态Mapper接口
 *
 * @author ruoyi
 * @date 2021-07-21
 */
public interface DetectionStatusMapper
{
    /**
     * 查询样品检测状态
     *
     * @param sampleID 样品检测状态ID
     * @return 样品检测状态
     */
    public DetectionStatus selectDetectionStatusById(Long sampleID);

    /**
     * 查询样品检测状态列表
     *
     * @param detectionStatus 样品检测状态
     * @return 样品检测状态集合
     */
    public List<DetectionStatus> selectDetectionStatusList(DetectionStatus detectionStatus);

    /**
     * 新增样品检测状态
     *
     * @param detectionStatus 样品检测状态
     * @return 结果
     */
    public int insertDetectionStatus(DetectionStatus detectionStatus);

    /**
     * 修改样品检测状态
     *
     * @param detectionStatus 样品检测状态
     * @return 结果
     */
    public int updateDetectionStatus(DetectionStatus detectionStatus);

    /**
     * 删除样品检测状态
     *
     * @param sampleID 样品检测状态ID
     * @return 结果
     */
    public int deleteDetectionStatusById(Long sampleID);

    /**
     * 批量删除样品检测状态
     *
     * @param sampleIDs 需要删除的数据ID
     * @return 结果
     */
    public int deleteDetectionStatusByIds(Long[] sampleIDs);
}
