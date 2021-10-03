package com.ruoyi.stock.service;

import java.util.List;

import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.stock.domain.EntryAndOut;
import com.ruoyi.stock.domain.vo.EntryAndOutVO;

/**
 * 样品出入库Service接口
 *
 * @author ruoyi
 * @date 2021-07-21
 */
public interface IEntryAndOutService
{
    /**
     * 查询样品出入库
     * @param entryAndOutID 样品出入库ID
     * @return 样品出入库
     */
    public EntryAndOut selectEntryAndOutById(Long entryAndOutID);

    /**
     * 查询样品出入库列表
     * @param entryAndOutVO 样品出入库
     * @return 分页信息
     */
    public TableDataInfo selectEntryAndOutList(EntryAndOutVO entryAndOutVO);

    /**
     * 新增样品出入库
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
     * 批量删除样品出入库
     *
     * @param entryAndOutIDs 需要删除的样品出入库ID
     * @return 结果
     */
    public int deleteEntryAndOutByIds(Long[] entryAndOutIDs);

    /**
     * 删除样品出入库信息
     *
     * @param entryAndOutID 样品出入库ID
     * @return 结果
     */
    public int deleteEntryAndOutById(Long entryAndOutID);
}
