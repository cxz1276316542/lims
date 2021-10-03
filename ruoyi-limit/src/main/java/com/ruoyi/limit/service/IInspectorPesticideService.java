package com.ruoyi.limit.service;

import java.util.List;
import com.ruoyi.limit.domain.InspectorPesticide;

/**
 * 检测员-检测项目Service接口
 * 
 * @author dlk
 * @date 2021-08-28
 */
public interface IInspectorPesticideService
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
     * 新增检测员-检测项目
     * 
     * @param inspectorPesticide 检测员-检测项目
     * @return 结果
     */
    public String insertInspectorPesticide(InspectorPesticide inspectorPesticide);

    /**
     * 修改检测员-检测项目
     * 
     * @param inspectorPesticide 检测员-检测项目
     * @return 结果
     */
    public int updateinspectorPesticide(InspectorPesticide inspectorPesticide);

    /**
     * 批量删除检测员-检测项目
     * 
     * @param inspectorPesticideIDs 需要删除的检测员-检测项目ID
     * @return 结果
     */
    public int deleteinspectorPesticideByIds(Long[] inspectorPesticideIDs);

    /**
     * 删除检测员-检测项目信息
     * 
     * @param inspectorPesticideID 检测员-检测项目ID
     * @return 结果
     */
    public int deleteinspectorPesticideById(Long inspectorPesticideID);
}
