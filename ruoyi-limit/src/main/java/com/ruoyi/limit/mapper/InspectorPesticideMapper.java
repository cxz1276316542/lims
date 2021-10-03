package com.ruoyi.limit.mapper;

import java.util.List;
import com.ruoyi.limit.domain.InspectorPesticide;
import org.apache.ibatis.annotations.Param;

/**
 * 检测员-检测项目Mapper接口
 * 
 * @author dlk
 * @date 2021-08-28
 */
public interface InspectorPesticideMapper
{
    /**
     * 查询检测员-检测项目
     * 
     * @param inspectorPesticideID 检测员-检测项目ID
     * @return 检测员-检测项目
     */
    public InspectorPesticide selectinspectorPesticideById(Long inspectorPesticideID);

    /**
     * 查询检测员-检测项目列表
     * 
     * @param inspectorPesticide 检测员-检测项目
     * @return 检测员-检测项目集合
     */
    public List<InspectorPesticide> selectinspectorPesticideList(InspectorPesticide inspectorPesticide);

    /**
     * 查询检测员-检测项目是否存在
     *
     * @param inspectorPesticide 检测员-检测项目
     * @return 检测员-检测项目
     */
    public InspectorPesticide selectInspectorPesticideExist(InspectorPesticide inspectorPesticide);

    /**
     * 新增检测员-检测项目
     * 
     * @param inspectorPesticide 检测员-检测项目
     * @return 结果
     */
    public int insertInspectorPesticide(InspectorPesticide inspectorPesticide);

    /**
     * 修改检测员-检测项目
     * 
     * @param inspectorPesticide 检测员-检测项目
     * @return 结果
     */
    public int updateinspectorPesticide(InspectorPesticide inspectorPesticide);

    /**
     * 删除检测员-检测项目
     * 
     * @param inspectorPesticideID 检测员-检测项目ID
     * @param detectionCenterID 检测中心ID
     * @return 结果
     */
    public int deleteinspectorPesticideById(@Param("inspectorPesticideID") Long inspectorPesticideID,@Param("detectionCenterID") Long detectionCenterID);

    /**
     * 批量删除检测员-检测项目
     * 
     * @param inspectorPesticideIDs 需要删除的数据ID
     * @param detectionCenterID 检测中心ID
     * @return 结果
     */
    public int deleteinspectorPesticideByIds(@Param("inspectorPesticideIDs")Long[] inspectorPesticideIDs,@Param("detectionCenterID") Long detectionCenterID);
}
