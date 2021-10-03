package com.ruoyi.limit.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.limit.domain.DetectionMethod;
import com.ruoyi.limit.domain.vo.InstrumentMethodVo;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

/**
 * 检测方法管理Mapper接口
 * 
 * @author dlk
 * @date 2021-07-20
 */
public interface DetectionMethodMapper 
{
    /**
     * 获取检测方法名称 和 对应 方法实体 map
     * 检测方法 - 检测方法ID
     */
    @MapKey("detectionMethodName")
    public Map<String, DetectionMethod> selectDetectionMethodNameMap();

    /**
     * 获取检测方法ID 和 对应 方法实体 map
     */
    @MapKey("detectionMethodID")
    public Map<Long, DetectionMethod> selectDetectionMethodIDMap();

    /**
     * 查询检测方法管理
     *
     * @param detectionMethodID 检测方法管理ID
     * @param detectionCenterID  检测中心ID
     * @return 检测方法管理
     */
    public DetectionMethod selectDetectionMethodById(@Param("detectionMethodID")Long detectionMethodID,@Param("detectionCenterID")Long detectionCenterID);

    /**
     * 根据检测方法名称查询检测方法
     * @param detectionMethodName 检测方法管理名称
     * @param detectionCenterID 检测中心ID
     */
    public DetectionMethod selectDetectionMethodByName(@Param("detectionMethodName")String detectionMethodName,@Param("detectionCenterID")Long detectionCenterID);
//    public DetectionMethod selectDetectionMethodByName(@Param("detectionMethodName")String detectionMethodName);

    /**
     * 模糊查询检测方法管理列表
     * 
     * @param detectionMethod 检测方法管理
     * @return 检测方法管理集合
     */
    public List<DetectionMethod> selectDetectionMethodList(DetectionMethod detectionMethod);

    /**
     * 新增检测方法管理
     * 
     * @param detectionMethod 检测方法管理
     * @return 结果
     */
    public int insertDetectionMethod(DetectionMethod detectionMethod);

    /**
     * 修改检测方法管理
     * 
     * @param detectionMethod 检测方法管理
     * @return 结果
     */
    public int updateDetectionMethod(DetectionMethod detectionMethod);

    /**
     * 删除检测方法管理
     * 
     * @param detectionMethodID 检测方法管理ID
     * @param detectionCenterID 检测中心ID
     * @return 结果
     */
    public int deleteDetectionMethodById(@Param("detectionMethodID") Long detectionMethodID,@Param("detectionCenterID") Long detectionCenterID);

    /**
     * 批量删除检测方法管理
     * 
     * @param detectionMethodIDs 需要删除的数据ID
     * @param detectionCenterID 检测中心ID
     * @return 结果
     */
    public int deleteDetectionMethodByIds(@Param("detectionMethodIDs") Long[] detectionMethodIDs,@Param("detectionCenterID") Long detectionCenterID );

    /**
     * 数据库是否存在该数据 0代表不存在 1代表存在
     * @param detectionMethod 检测方法管理
     * @return 数据库是否存在该数据
     */
    public int isExistDetectionMethod(DetectionMethod detectionMethod);
}
