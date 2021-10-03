package com.ruoyi.limit.service;

import java.util.List;

import com.ruoyi.limit.domain.DetectionMethod;
import com.ruoyi.limit.domain.Instrument;
import com.ruoyi.limit.domain.vo.InstrumentMethodVo;

/**
 * 检测仪器管理Service接口
 *
 * @author dlk
 * @date 2021-07-20
 */
public interface IInstrumentService
{
    /**
     * 查询检测仪器管理
     *
     * @param instrumentID 检测仪器管理ID
     * @return 检测仪器管理
     */
    public Instrument selectInstrumentById(Long instrumentID);

    /**
     * 查询检测仪器管理列表
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
     * 批量删除检测仪器管理
     *
     * @param instrumentIDs 需要删除的检测仪器管理ID
     * @return 结果
     */
    public int deleteInstrumentByIds(Long[] instrumentIDs);

    /**
     * 删除检测仪器管理信息
     *
     * @param instrumentID 检测仪器管理ID
     * @return 结果
     *//*
    public int deleteInstrumentById(Long instrumentID);*/

    /**
     * 导入检测方法管理信息
     *
     * @param instrumentList 检测仪器管理集合
     * @param isUpdateSupport 是否支持更新
     * @return 结果
     */
    public String importInstrument(List<Instrument> instrumentList, Boolean isUpdateSupport);

    /**
     * 自定义创建仪器编号
     * @return 仪器编号
     */
    public String createInstrumentNumber();

    /**
     * 根据检测仪器查询检测仪器-检测方法集合
     * @param instrument 检测仪器管理
     * @return 检测仪器-检测方法集合
     */
    public List<InstrumentMethodVo> selectInstrumentDetectionMethodList(Instrument instrument);

}
