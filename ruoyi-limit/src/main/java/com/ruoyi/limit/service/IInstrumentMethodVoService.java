package com.ruoyi.limit.service;

import java.util.List;

import com.ruoyi.limit.domain.vo.InstrumentMethodVo;

/**
 * 检测方法-检测仪器关系Service接口
 *
 * @author dlk
 * @date 2021-08-07
 */
public interface IInstrumentMethodVoService
{
    /**
     * 查询检测方法-检测仪器关系
     *
     * @param instrumentDetectionMethodID 检测方法-检测仪器关系ID
     * @return 检测方法-检测仪器关系
     */
    public InstrumentMethodVo selectInstrumentMethodById(Long instrumentDetectionMethodID);

    /**
     * 查询检测方法-检测仪器关系列表
     *
     * @param instrumentMethodVo 检测方法-检测仪器关系
     * @return 检测方法-检测仪器关系集合
     */
    public List<InstrumentMethodVo> selectInstrumentMethodList(InstrumentMethodVo instrumentMethodVo);

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
     * 批量删除检测方法-检测仪器关系
     *
     * @param instrumentDetectionMethodIDs 需要删除的检测方法-检测仪器关系ID
     * @return 结果
     */
    public int deleteInstrumentMethodByIds(Long[] instrumentDetectionMethodIDs);

    /**
     * 删除检测方法-检测仪器关系信息
     *
     * @param instrumentMethodVo 检测方法-检测仪器关系ID
     * @return 结果
     *//*
    public int deleteInstrumentMethodById(InstrumentMethodVo instrumentMethodVo);*/

    /**
     * 查找所有的检测方法名称
     * @return List<String>
     */
    List<String> findAllDetectionMethodName();

    /**
     * 导入检测仪器-检测方法管理信息
     *
     * @param instrumentMethodVoList 检测仪器-检测方法管理集合
     * @param isUpdateSupport 是否支持更新
     * @return 结果
     */
    public String importInstrumentMethod(List<InstrumentMethodVo> instrumentMethodVoList, Boolean isUpdateSupport);

    /**
     * 数据库是否存在该数据
     * @param instrumentMethodVo 检测仪器-检测方法管理
     * @return 数据库是否存在该数据
     */
    public int isExist(InstrumentMethodVo instrumentMethodVo);

}
