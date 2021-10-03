package com.ruoyi.detection.service.impl;

import java.util.List;

import com.ruoyi.detection.domain.Laboratory;
import com.ruoyi.detection.mapper.LaboratoryMapper;
import com.ruoyi.detection.service.ILaboratoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 检测中心Service业务层处理
 * 
 * @author zhangkui
 * @date 2021-09-02
 */
@Service
public class LaboratoryServiceImpl implements ILaboratoryService
{
    @Autowired
    private LaboratoryMapper laboratoryMapper;

    /**
     * 查询检测中心
     * 
     * @param laboratoryID 检测中心ID
     * @return 检测中心
     */
    @Override
    public Laboratory selectLaboratoryById(Long laboratoryID)
    {
        return laboratoryMapper.selectLaboratoryById(laboratoryID);
    }

    /**
     * 查询检测中心列表
     * 
     * @param laboratory 检测中心
     * @return 检测中心
     */
    @Override
    public List<Laboratory> selectLaboratoryList(Laboratory laboratory)
    {
        return laboratoryMapper.selectLaboratoryList(laboratory);
    }

    /**
     * 新增检测中心
     * 
     * @param laboratory 检测中心
     * @return 结果
     */
    @Override
    public int insertLaboratory(Laboratory laboratory)
    {
        return laboratoryMapper.insertLaboratory(laboratory);
    }

    /**
     * 修改检测中心
     * 
     * @param laboratory 检测中心
     * @return 结果
     */
    @Override
    public int updateLaboratory(Laboratory laboratory)
    {
        return laboratoryMapper.updateLaboratory(laboratory);
    }

    /**
     * 批量删除检测中心
     * 
     * @param laboratoryIDs 需要删除的检测中心ID
     * @return 结果
     */
    @Override
    public int deleteLaboratoryByIds(Long[] laboratoryIDs)
    {
        return laboratoryMapper.deleteLaboratoryByIds(laboratoryIDs);
    }

    /**
     * 删除检测中心信息
     * 
     * @param laboratoryID 检测中心ID
     * @return 结果
     */
    @Override
    public int deleteLaboratoryById(Long laboratoryID)
    {
        return laboratoryMapper.deleteLaboratoryById(laboratoryID);
    }
}
