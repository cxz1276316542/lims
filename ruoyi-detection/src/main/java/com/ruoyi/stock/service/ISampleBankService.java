package com.ruoyi.stock.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.stock.domain.SampleBank;
import com.ruoyi.stock.domain.vo.AbandonVO;
import com.ruoyi.stock.domain.vo.EntryAndOutVO;
import com.ruoyi.stock.domain.vo.SampleBankVO;

/**
 * 样品库Service接口
 *
 * @author ruoyi
 * @date 2021-07-21
 */
public interface ISampleBankService
{
    /**
     * 查询样品库
     * @param sampleBankID 样品库ID
     * @return 样品库
     */
    public Map<String,Object> selectSampleBankById(Long sampleBankID);

    /**
     * 查询样品库列表
     *
     * @param sampleBankVO 样品库
     * @return 样品库集合
     */
    public TableDataInfo selectSampleBankList(SampleBankVO sampleBankVO);

    /**
     * 新增样品库
     * @param sampleBankVO 样品库
     * @return 结果
     */
    public int insertSampleBank(SampleBankVO sampleBankVO) throws Exception;

    /**
     * 修改样品库
     *
     * @param sampleBankVO 样品库
     * @return 结果
     */
    public int updateSampleBank(SampleBankVO sampleBankVO);

    /**
     * 批量删除样品库
     *
     * @param sampleBankIDs 需要删除的样品库ID
     * @return 结果
     */
    public int deleteSampleBankByIds(Long[] sampleBankIDs);

    /**
     * 删除样品库信息,退样
     * @param entryAndOutVO
     * @return 结果
     */
    public int returnSampleBank(EntryAndOutVO entryAndOutVO);

    /**
     * 库存管理员信息
     * @return Map 管理员ID，管理员名称
     */
    List<Map<Long, String>> selectInventoryManager();

    /**
     * 批量废弃样品库信息
     * @param sampleBankIDs 样品库IDs
     * @return 结果
     */
    int abandonSampleBankByIds(Long[] sampleBankIDs);

    /**
     * 废弃样品库信息
     * @param entryAndOutVO  出入库VO
     * @return 结果
     */
    int abandonSampleBank(EntryAndOutVO entryAndOutVO);

    /**
     * 回收样品库信息
     * @param entryAndOutVO  出入库VO
     * @return 结果
     */
    int recycleSampleBank(EntryAndOutVO entryAndOutVO);

    /**
     * 查询销毁样品库列表
     * @param abandonVO 样品库
     * @return 样品库集合
    */
    TableDataInfo selectAbandonList(AbandonVO abandonVO);

    /**
     * 获取计量单位
     */
    List<String> getMeasureUnit();

    /**
     * 领用样品库信息
     * @param entryAndOutVO  出入库VO
     * @return 结果
     */
    int useSampleBank(EntryAndOutVO entryAndOutVO) throws Exception;
}
