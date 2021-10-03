package com.ruoyi.limit.mapper;

import java.util.List;
import com.ruoyi.limit.domain.DetectionMethodStandard;
import org.apache.ibatis.annotations.Param;

/**
 * 检测方法-检测标准Mapper接口
 * 
 * @author wlf
 * @date 2021-08-29
 */
public interface DetectionMethodStandardMapper 
{
    /**
     * 查询检测方法-检测标准
     * 
     * @param detectionMethodStandardID 检测方法-检测标准ID
     * @param detectionCenterID 检测中心ID
     * @return 检测方法-检测标准
     */
    public DetectionMethodStandard selectDetectionMethodStandardById(@Param("detectionMethodStandardID") Long detectionMethodStandardID, @Param("detectionCenterID")Long detectionCenterID);

    /**
     * 查询检测方法-检测标准列表
     * 
     * @param detectionMethodStandard 检测方法-检测标准
     * @return 检测方法-检测标准集合
     */
    public List<DetectionMethodStandard> selectDetectionMethodStandardList(DetectionMethodStandard detectionMethodStandard);

    /**
     * 查询某条检测方法-检测标准信息是否存在
     */
    public DetectionMethodStandard selectDetectionMethodStandardExist(DetectionMethodStandard detectionMethodStandard);

    /**
     * 新增检测方法-检测标准
     * 
     * @param detectionMethodStandard 检测方法-检测标准
     * @return 结果
     */
    public int insertDetectionMethodStandard(DetectionMethodStandard detectionMethodStandard);

    /**
     * 修改检测方法-检测标准
     * 
     * @param detectionMethodStandard 检测方法-检测标准
     * @return 结果
     */
    public int updateDetectionMethodStandard(DetectionMethodStandard detectionMethodStandard);

    /**
     * 删除检测方法-检测标准
     *
     * @param detectionMethodIDs 检测方法ID
     * @param detectionCenterID 检测中心ID
     * @return 结果
     */
    public int deleteDetectionMethodStandardByMethodIds(@Param("detectionMethodIDs") Long[] detectionMethodIDs, @Param("detectionCenterID")Long detectionCenterID);

    /**
     * 删除检测方法-检测标准
     *
     * @param standardIDs 检测标准ID
     * @param detectionCenterID 检测中心ID
     * @return 结果
     */
    public int deleteDetectionMethodStandardByStandardIds(@Param("standardIDs") Long[] standardIDs, @Param("detectionCenterID")Long detectionCenterID);


    /**
     * 删除检测方法-检测标准
     * 
     * @param detectionMethodStandardID 检测方法-检测标准ID
     * @param detectionCenterID 检测中心ID
     * @return 结果
     */
    public int deleteDetectionMethodStandardById(@Param("detectionMethodStandardID") Long detectionMethodStandardID, @Param("detectionCenterID")Long detectionCenterID);

    /**
     * 批量删除检测方法-检测标准
     * 
     * @param detectionMethodStandardIDs 需要删除的数据ID
     * @param detectionCenterID 检测中心ID
     * @return 结果
     */
    public int deleteDetectionMethodStandardByIds(@Param("detectionMethodStandardIDs") Long[] detectionMethodStandardIDs, @Param("detectionCenterID")Long detectionCenterID);
}
