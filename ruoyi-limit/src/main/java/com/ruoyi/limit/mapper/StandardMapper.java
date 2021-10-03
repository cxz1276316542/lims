package com.ruoyi.limit.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.limit.domain.Standard;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

/**
 * 检测标准Mapper接口
 * 
 * @author wlf
 * @date 2021-07-20
 */
public interface StandardMapper 
{
    /**
     * 获取检测标准名称 和 对应检测标准实体 Map
     * 检测标准名称 - 检测标准ID Map
     */
    @MapKey("standardNumber")
    public Map<String, Standard> selectStandardIDNameMap(@Param("detectionCenterID") Long detectionCenterID);

    /**
     * 获取检测标准ID-检测编号map集合
     * @return 检测标准集合
     */
    @MapKey("standardNumber")
    public List<Map<Long,String>> selectStandardMap(@Param("detectionCenterID") Long detectionCenterID);

    /**
     * 查询检测标准
     * 
     * @param standard 检测标准ID
     * @param detectionCenterID 检测中心ID
     * @return 检测标准
     */
    public Standard selectStandardById(@Param("standard") Long standard, @Param("detectionCenterID") Long detectionCenterID);

    /**
     * 查询检测标准列表
     * 
     * @param standard 检测标准
     * @return 检测标准集合
     */
    public List<Standard> selectStandardList(Standard standard);

    /**
     * 查询某条检测标准是否存在
     */
    public Standard selectStandardExist(Standard standard);


    /**
     * 新增检测标准
     * 
     * @param standard 检测标准
     * @return 结果
     */
    public int insertStandard(Standard standard);

    /**
     * 修改检测标准
     * 
     * @param standard 检测标准
     * @return 结果
     */
    public int updateStandard(Standard standard);

    /**
     * 删除检测标准
     * 
     * @param standard 检测标准ID
     * @param detectionCenterID 检测中心ID
     * @return 结果
     */
    public int deleteStandardById(@Param("standard")Long standard, @Param("detectionCenterID") Long detectionCenterID);

    /**
     * 批量删除检测标准
     * 
     * @param standards 需要删除的数据ID
     * @param detectionCenterID 检测中心ID
     * @return 结果
     */
    public int deleteStandardByIds(@Param("standards")Long[] standards, @Param("detectionCenterID") Long detectionCenterID);
}
