package com.ruoyi.limit.mapper;

import com.ruoyi.limit.domain.DetectionStandardFoodPesticide;

/**
 * 限量库详细信息Mapper接口
 * 
 * @author wlf
 * @date 2021-07-21
 */
public interface DetectionStandardFoodPesticideMapper
{
    /**
     * 根据检测标准-农药-食品-检测方法 ID查找 数据库中是否包含该数据
     */
    public DetectionStandardFoodPesticide selectLimitInfoVoListByIDs(DetectionStandardFoodPesticide detectionStandardFoodPesticide);

    /**
     * 更新 检测标准-农药-食品-检测方法 表
     */
    public int updateLimitInfoVo(DetectionStandardFoodPesticide detectionStandardFoodPesticide);

    /**
     * 插入 检测标准-农药-食品-检测方法 表
     */
    public int insertLimitInfoVo(DetectionStandardFoodPesticide detectionStandardFoodPesticide);

}
