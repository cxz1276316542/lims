package com.ruoyi.detection.service;

import com.ruoyi.detection.domain.Laboratory;

import java.util.List;


/**
 * 检测中心Service接口
 * 
 * @author zhangkui
 * @date 2021-09-02
 */
public interface ILaboratoryService 
{
    /**
     * 查询检测中心
     * 
     * @param laboratoryID 检测中心ID
     * @return 检测中心
     */
    public Laboratory selectLaboratoryById(Long laboratoryID);

    /**
     * 查询检测中心列表
     * 
     * @param laboratory 检测中心
     * @return 检测中心集合
     */
    public List<Laboratory> selectLaboratoryList(Laboratory laboratory);

    /**
     * 新增检测中心
     * 
     * @param laboratory 检测中心
     * @return 结果
     */
    public int insertLaboratory(Laboratory laboratory);

    /**
     * 修改检测中心
     * 
     * @param laboratory 检测中心
     * @return 结果
     */
    public int updateLaboratory(Laboratory laboratory);

    /**
     * 批量删除检测中心
     * 
     * @param laboratoryIDs 需要删除的检测中心ID
     * @return 结果
     */
    public int deleteLaboratoryByIds(Long[] laboratoryIDs);

    /**
     * 删除检测中心信息
     * 
     * @param laboratoryID 检测中心ID
     * @return 结果
     */
    public int deleteLaboratoryById(Long laboratoryID);
}
