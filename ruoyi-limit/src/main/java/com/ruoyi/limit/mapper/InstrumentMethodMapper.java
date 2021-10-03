package com.ruoyi.limit.mapper;

import java.util.List;

import com.ruoyi.limit.domain.vo.InstrumentMethodVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 检测方法-检测仪器关系Mapper接口
 *
 * @author dlk
 * @date 2021-08-07
 */
public interface InstrumentMethodMapper
{
    /**
     * 查询检测方法-检测仪器关系
     *
     * @param instrumentDetectionMethodID 检测方法-检测仪器关系ID
     * @param detectionCenterID 检测中心ID
     * @return 检测方法-检测仪器关系
     */
    public InstrumentMethodVo selectInstrumentMethodById(@Param("instrumentDetectionMethodID") Long instrumentDetectionMethodID,@Param("detectionCenterID")Long detectionCenterID);

     /**
     * 查询检测方法-检测仪器关系列表
     *
     * @param instrumentMethodVo 检测方法-检测仪器关系
     * @return 检测方法-检测仪器关系集合
     */
    public List<InstrumentMethodVo> selectInstrumentMethodList(InstrumentMethodVo instrumentMethodVo);


    /**
     * 查询检测仪器-检测方法是否存在
     *
     * @param instrumentMethodVo 检测仪器-检测方法管理
     * @return 检测仪器-检测方法
     */
    public InstrumentMethodVo selectInstrumentDetectionMethod(InstrumentMethodVo instrumentMethodVo);
    /**
     * 新增检测方法-检测仪器关系
     *
     * @param instrumentMethodVo 检测方法-检测仪器关系
     * @return 结果
     */
    public int insertInstrumentMethod(InstrumentMethodVo instrumentMethodVo);

    /**
     * 修改检测方法-检测仪器关系
     *
     * @param instrumentMethodVo 检测方法-检测仪器关系
     * @return 结果
     */
    public int updateInstrumentMethod(InstrumentMethodVo instrumentMethodVo);

    /**
     * 根据仪器IDs批量删除检测方法-检测仪器关系
     *
     * @param instrumentIDs 需要删除的数据ID
     * @param detectionCenterID 检测中心ID
     * @return 结果
     */
    public int deleteInstrumentMethodByInstrumentIds(@Param("instrumentIDs") Long[] instrumentIDs,@Param("detectionCenterID")Long detectionCenterID);

    /**
     * 根据检测方法IDs批量删除检测方法-检测仪器关系
     *
     * @param detectionMethodIDs 需要删除的数据ID
     * @param detectionCenterID 检测中心ID
     * @return 结果
     */
    public int deleteInstrumentMethodByDetectionMethodIds(@Param("detectionMethodIDs")Long[] detectionMethodIDs,@Param("detectionCenterID")Long detectionCenterID);

    /**
     * 删除检测方法-检测仪器关系
     *
     * @param instrumentMethodVo 检测方法-检测仪器关系
     * @return 结果
     *//*
    public int deleteInstrumentMethodById(InstrumentMethodVo instrumentMethodVo);*/

    /**
     * 批量删除检测方法-检测仪器关系
     *
     * @param instrumentDetectionMethodIDs 需要删除的数据ID
     * @param detectionCenterID 检测中心ID
     * @return 结果
     */
    public int deleteInstrumentMethodByIds(@Param("instrumentDetectionMethodIDs") Long[] instrumentDetectionMethodIDs,@Param("detectionCenterID") Long detectionCenterID);

    /**
     * 查找所有的检测方法名称
     */
    @Select("select JCFF_MC from xlk_jcff")
    List<String> findAllDetectionMethodName();
}
