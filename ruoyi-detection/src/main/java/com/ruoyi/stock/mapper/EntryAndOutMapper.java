package com.ruoyi.stock.mapper;

import java.util.List;
import com.ruoyi.stock.domain.EntryAndOut;
import com.ruoyi.stock.domain.Operator;
import com.ruoyi.stock.domain.vo.EntryAndOutVO;
import org.apache.ibatis.annotations.Param;

/**
 * 样品出入库Mapper接口
 *
 * @author ruoyi
 * @date 2021-07-21
 */
public interface EntryAndOutMapper
{
    /**
     * 查询样品出入库
     *
     * @param entryAndOutID 样品出入库ID
     * @return 样品出入库
     */
    public EntryAndOut selectEntryAndOutById(Long entryAndOutID);

    /**
     * 查询样品出入库列表
     * @param entryAndOut 样品出入库
     * @return 样品出入库集合
     */
    public List<EntryAndOut> selectEntryAndOutList(EntryAndOut entryAndOut);

    /**
     * 新增样品出入库
     *
     * @param entryAndOut 样品出入库
     * @return 结果
     */
    public int insertEntryAndOut(EntryAndOut entryAndOut);

    /**
     * 修改样品出入库
     *
     * @param entryAndOut 样品出入库
     * @return 结果
     */
    public int updateEntryAndOut(EntryAndOut entryAndOut);

    /**
     * 删除样品出入库
     *
     * @param entryAndOutID 样品出入库ID
     * @return 结果
     */
    public int deleteEntryAndOutById(Long entryAndOutID);

    /**
     * 批量删除样品出入库
     *
     * @param entryAndOutIDs 需要删除的数据ID
     * @return 结果
     */
    public int deleteEntryAndOutByIds(Long[] entryAndOutIDs);

    /**
     * 通过样品ID列表查询样品出入库列表
     * @param entryAndOut 样品出入库
     * @param sampleIDs   样品ID列表
     * @return 样品出入库集合
     */
    List<EntryAndOut> selectEntryAndOutListBySampleIDs(@Param("entryAndOut")EntryAndOut entryAndOut,@Param("sampleIDs")List<Long> sampleIDs);

    /**
     * 查询样品出入库列表
     * @param entryAndOutVO 样品出入库
     * @return 样品出入库集合
     */
    List<EntryAndOutVO> selectEntryAndOutVOList(EntryAndOutVO entryAndOutVO);

    /**
     * 通过样品库ID查询领用人
     * @param sampleBankID
     * @return Operator
     */
    Operator selectDeliverByBankID(@Param("sampleBankID")Long sampleBankID);

    /**
     * 通过样品库ID更新领用人
     * @param sampleBankID
     * @return Operator
     */
    int updateEntryAndOutByBankID(@Param("sampleBankID")Long sampleBankID,@Param("deliverID") Long deliverID);
}
