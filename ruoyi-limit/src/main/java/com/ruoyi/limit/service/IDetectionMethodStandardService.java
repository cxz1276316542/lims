package com.ruoyi.limit.service;

import java.util.List;
import com.ruoyi.limit.domain.DetectionMethodStandard;

/**
 * 检测方法-检测标准Service接口
 * 
 * @author wlf
 * @date 2021-08-29
 */
public interface IDetectionMethodStandardService 
{
    /**
     * 查询检测方法-检测标准
     * 
     * @param detectionMethodStandardID 检测方法-检测标准ID
     * @return 检测方法-检测标准
     */
    public DetectionMethodStandard selectDetectionMethodStandardById(Long detectionMethodStandardID);

    /**
     * 查询检测方法-检测标准列表
     * 
     * @param detectionMethodStandard 检测方法-检测标准
     * @return 检测方法-检测标准集合
     */
    public List<DetectionMethodStandard> selectDetectionMethodStandardList(DetectionMethodStandard detectionMethodStandard);

    /**
     * 新增检测方法-检测标准
     * 
     * @param detectionMethodStandard 检测方法-检测标准
     * @return 结果
     */
    public String insertDetectionMethodStandard(DetectionMethodStandard detectionMethodStandard);

    /**
     * 修改检测方法-检测标准
     * 
     * @param detectionMethodStandard 检测方法-检测标准
     * @return 结果
     */
    public int updateDetectionMethodStandard(DetectionMethodStandard detectionMethodStandard);

    /**
     * 批量删除检测方法-检测标准
     * 
     * @param detectionMethodStandardIDs 需要删除的检测方法-检测标准ID
     * @return 结果
     */
    public int deleteDetectionMethodStandardByIds(Long[] detectionMethodStandardIDs);

    /**
     * 删除检测方法-检测标准信息
     * 
     * @param detectionMethodStandardID 检测方法-检测标准ID
     * @return 结果
     */
    public int deleteDetectionMethodStandardById(Long detectionMethodStandardID);
}
