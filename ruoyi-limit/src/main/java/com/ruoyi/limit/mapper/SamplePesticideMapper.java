package com.ruoyi.limit.mapper;

import java.util.List;
import com.ruoyi.limit.domain.SamplePesticide;
import org.apache.ibatis.annotations.Param;

/**
 * 样品-检测项目Mapper接口
 * 
 * @author wlf
 * @date 2021-08-28
 */
public interface SamplePesticideMapper
{
    /**
     * 查询样品-检测项目
     * 
     * @param samplePesticideID 样品-检测项目ID
     * @param detectionCenterID 检测中心ID
     * @return 样品-检测项目
     */
    public SamplePesticide selectSamplePesticideById(@Param("samplePesticideID") Long samplePesticideID, @Param("detectionCenterID") Long detectionCenterID);

    /**
     * 查询样品-检测项目列表
     * 
     * @param samplePesticide 样品-检测项目
     * @return 样品-检测项目集合
     */
    public List<SamplePesticide> selectSamplePesticideList(SamplePesticide samplePesticide);

    /**
     * 查询某条样品-检测项目信息是否存在
     */
    public SamplePesticide selectSamplePesticideExist(SamplePesticide samplePesticide);

    /**
     * 新增样品-检测项目
     * 
     * @param samplePesticide 样品-检测项目
     * @return 结果
     */
    public int insertSamplePesticide(SamplePesticide samplePesticide);

    /**
     * 修改样品-检测项目
     * 
     * @param samplePesticide 样品-检测项目
     * @return 结果
     */
    public int updateSamplePesticide(SamplePesticide samplePesticide);

    /**
     * 删除样品-检测项目
     * @param foodTypeIDs 食品管理ID
     * @param detectionCenterID 检测中心ID
     */
    public int deleteSamplePesticideByFoodIds(@Param("foodTypeIDs") Long[] foodTypeIDs, @Param("detectionCenterID")Long detectionCenterID);

    /**
     * 删除样品-检测项目
     * @param pesticideIDs 检测项目ID
     * @param detectionCenterID 检测中心ID
     */
    public int deleteSamplePesticideByPesticideIds(@Param("pesticideIDs")Long[] pesticideIDs, @Param("detectionCenterID")Long detectionCenterID);

    /**
     * 删除样品-检测项目
     * 
     * @param samplePesticideID 样品-检测项目ID
     * @param detectionCenterID 检测中心ID
     * @return 结果
     */
    public int deleteSamplePesticideById(@Param("samplePesticideID") Long samplePesticideID, @Param("detectionCenterID")Long detectionCenterID);

    /**
     * 批量删除样品-检测项目
     * 
     * @param samplePesticideIDs 需要删除的数据ID
     * @param detectionCenterID 检测中心ID
     * @return 结果
     */
    public int deleteSamplePesticideByIds(@Param("samplePesticideIDs") Long[] samplePesticideIDs, @Param("detectionCenterID")Long detectionCenterID);
}
