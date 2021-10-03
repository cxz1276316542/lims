package com.ruoyi.detection.mapper;

import java.util.List;

import com.ruoyi.detection.domain.Laboratory;


/**
 * 检测中心Mapper接口
 * 
 * @author zhangkui
 * @date 2021-09-02
 */
public interface LaboratoryMapper 
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
     * 删除检测中心
     * 
     * @param laboratoryID 检测中心ID
     * @return 结果
     */
    public int deleteLaboratoryById(Long laboratoryID);

    /**
     * 批量删除检测中心
     * 
     * @param laboratoryIDs 需要删除的数据ID
     * @return 结果
     */
    public int deleteLaboratoryByIds(Long[] laboratoryIDs);
}
