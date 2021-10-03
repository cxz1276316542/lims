package com.ruoyi.detection.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.detection.mapper.MeasureUnitMapper;
import com.ruoyi.detection.domain.MeasureUnit;
import com.ruoyi.detection.service.IMeasureUnitService;

/**
 * 计量单位（检测项目单位、样品单位）Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-07-27
 */
@Service
public class MeasureUnitServiceImpl implements IMeasureUnitService 
{
    @Autowired
    private MeasureUnitMapper measureUnitMapper;

    /**
     * 查询计量单位（检测项目单位、样品单位）
     * 
     * @param measureUnitID 计量单位（检测项目单位、样品单位）ID
     * @return 计量单位（检测项目单位、样品单位）
     */
    @Override
    public MeasureUnit selectMeasureUnitById(Long measureUnitID)
    {
        return measureUnitMapper.selectMeasureUnitById(measureUnitID);
    }

    /**
     * 查询计量单位（检测项目单位、样品单位）列表
     * 
     * @param measureUnit 计量单位（检测项目单位、样品单位）
     * @return 计量单位（检测项目单位、样品单位）
     */
    @Override
    public List<MeasureUnit> selectMeasureUnitList(MeasureUnit measureUnit)
    {
        return measureUnitMapper.selectMeasureUnitList(measureUnit);
    }

    /**
     * 新增计量单位（检测项目单位、样品单位）
     * 
     * @param measureUnit 计量单位（检测项目单位、样品单位）
     * @return 结果
     */
    @Override
    public int insertMeasureUnit(MeasureUnit measureUnit)
    {
        return measureUnitMapper.insertMeasureUnit(measureUnit);
    }

    /**
     * 修改计量单位（检测项目单位、样品单位）
     * 
     * @param measureUnit 计量单位（检测项目单位、样品单位）
     * @return 结果
     */
    @Override
    public int updateMeasureUnit(MeasureUnit measureUnit)
    {
        return measureUnitMapper.updateMeasureUnit(measureUnit);
    }

    /**
     * 批量删除计量单位（检测项目单位、样品单位）
     * 
     * @param measureUnitIDs 需要删除的计量单位（检测项目单位、样品单位）ID
     * @return 结果
     */
    @Override
    public int deleteMeasureUnitByIds(Long[] measureUnitIDs)
    {
        return measureUnitMapper.deleteMeasureUnitByIds(measureUnitIDs);
    }

    /**
     * 删除计量单位（检测项目单位、样品单位）信息
     * 
     * @param measureUnitID 计量单位（检测项目单位、样品单位）ID
     * @return 结果
     */
    @Override
    public int deleteMeasureUnitById(Long measureUnitID)
    {
        return measureUnitMapper.deleteMeasureUnitById(measureUnitID);
    }
}
