package com.ruoyi.limit.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.limit.domain.Pesticide;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

/**
 * 检测农药管理Mapper接口
 * 
 * @author wlf
 * @date 2021-07-20
 */
public interface PesticideMapper 
{
    /**
     * 获取农药名称 和 农药ID map
     */
    @MapKey("pesticideName")
    public Map<String, Pesticide> selectPesticideIDNameMap(@Param("detectionCenterID") Long detectionCenterID);

    /**
     * 查询某条农药信息是否存在
     */
    public Pesticide selectPesticideExist(Pesticide pesticide);
    /**
     * 查询豁免清单
     * @param pesticide 检测农药
     * @return 豁免清单数据
     */
    public List<Pesticide> selectExemptionList(Pesticide pesticide);
    /**
     * 查询检测农药管理
     * 
     * @param pesticideID 检测农药管理ID
     * @param detectionCenterID 检测中心ID
     * @return 检测农药管理
     */
    public Pesticide selectPesticideById(@Param("pesticideID") Long pesticideID, @Param("detectionCenterID") Long detectionCenterID);

    /**
     * 查询检测农药信息
     * @param pesticideName
     * @param detectionCenterID 检测中心ID
     */
    public Pesticide selectPesticideByName(@Param("pesticideName") String pesticideName, @Param("detectionCenterID")Long detectionCenterID);
    /**
     * 查询检测农药管理列表
     * 
     * @param pesticide 检测农药管理
     * @return 检测农药管理集合
     */
    public List<Pesticide> selectPesticideList(Pesticide pesticide);

    /**
     * 新增检测农药管理
     * 
     * @param pesticide 检测农药管理
     * @return 结果
     */
    public int insertPesticide(Pesticide pesticide);

    /**
     * 修改检测农药管理
     * 
     * @param pesticide 检测农药管理
     * @return 结果
     */
    public int updatePesticide(Pesticide pesticide);

    /**
     * 删除检测农药管理
     * 
     * @param pesticideID 检测农药管理ID
     * @param detectionCenterID 检测中心ID
     * @return 结果
     */
    public int deletePesticideById(@Param("pesticideID") Long pesticideID, @Param("detectionCenterID")Long detectionCenterID);

    /**
     * 批量删除检测农药管理
     * 
     * @param pesticideIDs 需要删除的数据ID
     * @param detectionCenterID 检测中心ID
     * @return 结果
     */
    public int deletePesticideByIds(@Param("pesticideIDs")Long[] pesticideIDs, @Param("detectionCenterID")Long detectionCenterID);
}
