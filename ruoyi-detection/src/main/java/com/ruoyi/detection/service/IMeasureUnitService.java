package com.ruoyi.detection.service;

import java.util.List;
import com.ruoyi.detection.domain.MeasureUnit;

/**
 * 计量单位（检测项目单位、样品单位）Service接口
 * 
 * @author ruoyi
 * @date 2021-07-27
 */
public interface IMeasureUnitService 
{
    /**
     * 查询计量单位（检测项目单位、样品单位）
     * 
     * @param measureUnitID 计量单位（检测项目单位、样品单位）ID
     * @return 计量单位（检测项目单位、样品单位）
     */
    public MeasureUnit selectMeasureUnitById(Long measureUnitID);

    /**
     * 查询计量单位（检测项目单位、样品单位）列表
     * 
     * @param measureUnit 计量单位（检测项目单位、样品单位）
     * @return 计量单位（检测项目单位、样品单位）集合
     */
    public List<MeasureUnit> selectMeasureUnitList(MeasureUnit measureUnit);

    /**
     * 新增计量单位（检测项目单位、样品单位）
     * 
     * @param measureUnit 计量单位（检测项目单位、样品单位）
     * @return 结果
     */
    public int insertMeasureUnit(MeasureUnit measureUnit);

    /**
     * 修改计量单位（检测项目单位、样品单位）
     * 
     * @param measureUnit 计量单位（检测项目单位、样品单位）
     * @return 结果
     */
    public int updateMeasureUnit(MeasureUnit measureUnit);

    /**
     * 批量删除计量单位（检测项目单位、样品单位）
     * 
     * @param measureUnitIDs 需要删除的计量单位（检测项目单位、样品单位）ID
     * @return 结果
     */
    public int deleteMeasureUnitByIds(Long[] measureUnitIDs);

    /**
     * 删除计量单位（检测项目单位、样品单位）信息
     * 
     * @param measureUnitID 计量单位（检测项目单位、样品单位）ID
     * @return 结果
     */
    public int deleteMeasureUnitById(Long measureUnitID);
}
