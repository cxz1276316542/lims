package com.ruoyi.limit.service;

import java.util.List;
import com.ruoyi.limit.domain.SamplePesticide;

/**
 * 样品-检测项目Service接口
 * 
 * @author wlf
 * @date 2021-08-28
 */
public interface ISamplePesticideService
{
    /**
     * 查询样品-检测项目
     * 
     * @param samplePesticideID 样品-检测项目ID
     * @return 样品-检测项目
     */
    public SamplePesticide selectSamplePesticideById(Long samplePesticideID);

    /**
     * 查询样品-检测项目列表
     * 
     * @param samplePesticide 样品-检测项目
     * @return 样品-检测项目集合
     */
    public List<SamplePesticide> selectSamplePesticideList(SamplePesticide samplePesticide);

    /**
     * 新增样品-检测项目
     * 
     * @param samplePesticide 样品-检测项目
     * @return 结果
     */
    public String insertSamplePesticide(SamplePesticide samplePesticide);

    /**
     * 修改样品-检测项目
     * 
     * @param samplePesticide 样品-检测项目
     * @return 结果
     */
    public int updateSamplePesticide(SamplePesticide samplePesticide);

    /**
     * 批量删除样品-检测项目
     * 
     * @param samplePesticideIDs 需要删除的样品-检测项目ID
     * @return 结果
     */
    public int deleteSamplePesticideByIds(Long[] samplePesticideIDs);

    /**
     * 删除样品-检测项目信息
     * 
     * @param samplePesticideID 样品-检测项目ID
     * @return 结果
     */
    public int deleteSamplePesticideById(Long samplePesticideID);
}
