package com.ruoyi.stock.mapper;

import java.util.List;
import com.ruoyi.stock.domain.SampleBank;
import com.ruoyi.stock.domain.vo.AbandonVO;
import com.ruoyi.stock.domain.vo.SampleBankVO;

/**
 * 样品库Mapper接口
 *
 * @author ruoyi
 * @date 2021-07-21
 */
public interface SampleBankMapper
{
    /**
     * 查询样品库
     * @param sampleBankID 样品库ID
     * @return 样品库
     */
    public SampleBank selectSampleBankById(Long sampleBankID);

    /**
     * 查询样品库VO
     * @param sampleBankID 样品库ID
     * @return 样品库
     */
    public SampleBankVO selectSampleBankVOById(Long sampleBankID);

    /**
     * 查询样品库列表
     *
     * @param sampleBank 样品库
     * @return 样品库集合
     */
    public List<SampleBank> selectSampleBankList(SampleBank sampleBank);

    /**
     * 新增样品库
     *
     * @param sampleBank 样品库
     * @return 结果
     */
    public int insertSampleBank(SampleBank sampleBank);

    /**
     * 修改样品库
     *
     * @param sampleBank 样品库
     * @return 结果
     */
    public int updateSampleBank(SampleBank sampleBank);

    /**
     * 删除样品库
     *
     * @param sampleBankID 样品库ID
     * @return 结果
     */
    public int deleteSampleBankById(Long sampleBankID);

    /**
     * 批量删除样品库
     *
     * @param sampleBankIDs 需要删除的数据ID
     * @return 结果
     */
    public int deleteSampleBankByIds(Long[] sampleBankIDs);

    /**
     * 通过样品id查询样品库列表
     * @param ids 样品id
     * @return 样品库集合
     */
    List<SampleBank> selectSampleBankBySampleIds(Long[] ids);

    /**
     * 通过样品id列表更新状态
     * @param sampleBankIDs 样品库ids
     * @return 样品库集合
     */
    int updateSampleBankAbandonByIDs(Long[] sampleBankIDs);

    /**
     * 查询样品库列表
     * @param sampleBankVO 样品库
     * @return 样品库集合
     */
    List<SampleBankVO> selectSampleBankVOList(SampleBankVO sampleBankVO);

    /**
     * 查询销毁样品库列表
     * @param abandonVO 样品库
     * @return 样品库集合
     */
    List<AbandonVO> selectAbandonVOList(AbandonVO abandonVO);
}
