package com.ruoyi.detection.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.detection.mapper.DetectionStatusMapper;
import com.ruoyi.detection.domain.DetectionStatus;
import com.ruoyi.detection.service.IDetectionStatusService;

/**
 * 样品检测状态Service业务层处理
 *
 * @author ruoyi
 * @date 2021-07-21
 */
@Service
public class DetectionStatusServiceImpl implements IDetectionStatusService
{
    @Autowired
    private DetectionStatusMapper detectionStatusMapper;

    /**
     * 查询样品检测状态
     *
     * @param sampleID 样品检测状态ID
     * @return 样品检测状态
     */
    @Override
    public DetectionStatus selectDetectionStatusById(Long sampleID)
    {
        return detectionStatusMapper.selectDetectionStatusById(sampleID);
    }

    /**
     * 查询样品检测状态列表
     *
     * @param detectionStatus 样品检测状态
     * @return 样品检测状态
     */
    @Override
    public List<DetectionStatus> selectDetectionStatusList(DetectionStatus detectionStatus)
    {
        return detectionStatusMapper.selectDetectionStatusList(detectionStatus);
    }

    /**
     * 新增样品检测状态
     *
     * @param detectionStatus 样品检测状态
     * @return 结果
     */
    @Override
    public int insertDetectionStatus(DetectionStatus detectionStatus)
    {
        return detectionStatusMapper.insertDetectionStatus(detectionStatus);
    }

    /**
     * 修改样品检测状态
     *
     * @param detectionStatus 样品检测状态
     * @return 结果
     */
    @Override
    public int updateDetectionStatus(DetectionStatus detectionStatus)
    {
        return detectionStatusMapper.updateDetectionStatus(detectionStatus);
    }

    /**
     * 批量删除样品检测状态
     *
     * @param sampleIDs 需要删除的样品检测状态ID
     * @return 结果
     */
    @Override
    public int deleteDetectionStatusByIds(Long[] sampleIDs)
    {
        return detectionStatusMapper.deleteDetectionStatusByIds(sampleIDs);
    }

    /**
     * 删除样品检测状态信息
     *
     * @param sampleID 样品检测状态ID
     * @return 结果
     */
    @Override
    public int deleteDetectionStatusById(Long sampleID)
    {
        return detectionStatusMapper.deleteDetectionStatusById(sampleID);
    }
}
