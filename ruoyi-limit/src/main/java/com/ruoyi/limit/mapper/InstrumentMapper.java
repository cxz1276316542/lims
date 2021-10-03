package com.ruoyi.limit.mapper;

import java.util.List;
import com.ruoyi.limit.domain.Instrument;
import com.ruoyi.limit.domain.vo.InstrumentMethodVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.security.core.parameters.P;

/**
 * 检测仪器管理Mapper接口
 *
 * @author dlk
 * @date 2021-07-20
 */
public interface InstrumentMapper
{
    /**
     * 查询检测仪器管理
     *
     * @param instrumentName 检测仪器管理名称
     * @param detectionCenterID 检测中心ID
     * @return 检测仪器管理
     */
    public Instrument selectInstrumentByName(@Param("instrumentName") String instrumentName, @Param("detectionCenterID")Long detectionCenterID);

    /**
     * 根据仪器编号查询检测仪器管理
     *
     * @param instrumentNumber 检测仪器管理编号
     * @param detectionCenterID 检测中心ID
     * @return 检测仪器管理
     */
    public Instrument selectInstrumentByInstrumentNumber(@Param("instrumentNumber")String instrumentNumber,@Param("detectionCenterID")Long detectionCenterID);

    /**
     * 查询检测仪器管理
     *
     * @param instrumentID 检测仪器管理ID
     * @param detectionCenterID 检测中心ID
     * @return 检测仪器管理
     */
    public Instrument selectInstrumentById(@Param("instrumentID") Long instrumentID,@Param("detectionCenterID")Long detectionCenterID);

    /**
     * 模糊查询检测仪器管理列表
     *
     * @param instrument 检测仪器管理
     * @return 检测仪器管理集合
     */
    public List<Instrument> selectInstrumentList(Instrument instrument);

    /**
     * 新增检测仪器管理
     *
     * @param instrument 检测仪器管理
     * @return 结果
     */
    public int insertInstrument(Instrument instrument);

    /**
     * 修改检测仪器管理
     *
     * @param instrument 检测仪器管理
     * @return 结果
     */
    public int updateInstrument(Instrument instrument);

    /**
     * 删除检测仪器管理
     *
     * @param instrumentID 检测仪器管理ID
     * @param detectionCenterID 检测中心ID
     * @return 结果
     */
    public int deleteInstrumentById(@Param("instrumentID") Long instrumentID,@Param("detectionCenterID")Long detectionCenterID);

    /**
     * 批量删除检测仪器管理
     *
     * @param instrumentIDs 需要删除的数据ID
     * @param detectionCenterID 检测中心ID
     * @return 结果
     */
    public int deleteInstrumentByIds(@Param("instrumentIDs") Long[] instrumentIDs,@Param("detectionCenterID")Long detectionCenterID);

    /**
     * 根据检测仪器查询检测仪器-检测方法管理列表
     *
     * @param instrument 检测仪器管理
     * @return 检测仪器-检测方法管理集合
     */
    public List<InstrumentMethodVo> selectInstrumentDetectionMethodList(Instrument instrument);

    /**
     * 删除检测仪器-检测方法管理
     *
     * @param instrumentID 检测仪器管理ID
     * @param detectionCenterID 检测中心ID
     * @return 结果
     */
    public int deleteInstrumentDetectionMethodById(@Param("instrumentID") Long instrumentID,@Param("detectionCenterID")Long detectionCenterID);

    /**
     * 批量删除检测仪器管理
     *
     * @param instrumentIDs 需要删除的数据ID
     * @param detectionCenterID 检测中心ID
     * @return 结果
     */
    public int deleteInstrumentDetectionMethodByIds(@Param("instrumentIDs") Long[] instrumentIDs,@Param("detectionCenterID")Long detectionCenterID);


    /**
     * 查找最大的仪器编号
     *
     * @return 仪器编号
     */
    @Select("select JCYQ_BH from xlk_jcyq order by JCYQ_BH desc limit 1")
    public String findLastInstrumentNumber();
}
