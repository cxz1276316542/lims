package com.ruoyi.limit.mapper;

import java.util.List;

import com.ruoyi.limit.domain.PesticideDetectionMethod;
import org.apache.ibatis.annotations.Param;

/**
 * 检测项目-检测方法Mapper接口
 * 
 * @author wlf
 * @date 2021-08-28
 */
public interface PesticideDetectionMethodMapper
{
    /**
     * 查询检测项目-检测方法
     * 
     * @param pesticideDetectionMethodID 检测项目-检测方法ID
     * @param detectionCenterID 检测中心ID
     * @return 检测项目-检测方法
     */
    public PesticideDetectionMethod selectPesticideDetectionMethodById(@Param("pesticideDetectionMethodID") Long pesticideDetectionMethodID, @Param("detectionCenterID")Long detectionCenterID);

    /**
     * 查询检测项目-检测方法列表
     * 
     * @param pesticideDetectionMethod 检测项目-检测方法
     * @return 检测项目-检测方法集合
     */
    public List<PesticideDetectionMethod> selectPesticideDetectionMethodList(PesticideDetectionMethod pesticideDetectionMethod);

    /**
     * 查询某条检测项目-检测方法信息是否存在
     */
    public PesticideDetectionMethod selectPesticideDetectionMethodExist(PesticideDetectionMethod pesticideDetectionMethod);

    /**
     * 新增检测项目-检测方法
     * 
     * @param pesticideDetectionMethod 检测项目-检测方法
     * @return 结果
     */
    public int insertPesticideDetectionMethod(PesticideDetectionMethod pesticideDetectionMethod);

    /**
     * 修改检测项目-检测方法
     * 
     * @param pesticideDetectionMethod 检测项目-检测方法
     * @return 结果
     */
    public int updatePesticideDetectionMethod(PesticideDetectionMethod pesticideDetectionMethod);

    /**
     * 删除检测项目-检测方法
     * @param pesticideID 检测项目ID
     * @param detectionCenterID 检测中心ID
     */
    public int deletePesticideDetectionMethodByPesticideIds(@Param("pesticideIDs") Long[] pesticideID, @Param("detectionCenterID")Long detectionCenterID);

    /**
     * 删除检测项目-检测方法
     * @param detectionMethodID 检测方法ID
     * @param detectionCenterID 检测中心ID
     */
    public int deletePesticideDetectionMethodByMethodIds(@Param("detectionMethodIDs") Long[] detectionMethodID,@Param("detectionCenterID") Long detectionCenterID);

    /**
     * 删除检测项目-检测方法
     * 
     * @param pesticideDetectionMethodID 检测项目-检测方法ID
     * @param detectionCenterID 检测中心ID
     * @return 结果
     */
    public int deletePesticideDetectionMethodById(@Param("pesticideDetectionMethodID") Long pesticideDetectionMethodID, @Param("detectionCenterID")Long detectionCenterID);

    /**
     * 批量删除检测项目-检测方法
     * 
     * @param pesticideDetectionMethodIDs 需要删除的数据ID
     * @param detectionCenterID 检测中心ID
     * @return 结果
     */
    public int deletePesticideDetectionMethodByIds(@Param("pesticideDetectionMethodIDs") Long[] pesticideDetectionMethodIDs, @Param("detectionCenterID")Long detectionCenterID);
}
