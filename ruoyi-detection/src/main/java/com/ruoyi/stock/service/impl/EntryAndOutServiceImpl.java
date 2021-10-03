package com.ruoyi.stock.service.impl;

import java.text.SimpleDateFormat;
import java.util.*;

import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.detection.domain.CommissionSample;
import com.ruoyi.detection.mapper.CommissionSampleMapper;
import com.ruoyi.stock.constant.StockConstants;
import com.ruoyi.stock.domain.vo.EntryAndOutVO;
import com.ruoyi.stock.utils.PagingUtils;
import com.ruoyi.system.mapper.SysUserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.stock.mapper.EntryAndOutMapper;
import com.ruoyi.stock.domain.EntryAndOut;
import com.ruoyi.stock.service.IEntryAndOutService;

/**
 * 样品出入库Service业务层处理
 *
 * @author ruoyi
 * @date 2021-07-21
 */
@Service
public class EntryAndOutServiceImpl implements IEntryAndOutService
{
    @Autowired
    private EntryAndOutMapper entryAndOutMapper;

    @Autowired
    private CommissionSampleMapper commissionSampleMapper;

    @Autowired
    private SysUserMapper userMapper;

    /**
     * 查询样品出入库
     *
     * @param entryAndOutID 样品出入库ID
     * @return 样品出入库
     */
    @Override
    public EntryAndOut selectEntryAndOutById(Long entryAndOutID)
    {
        return entryAndOutMapper.selectEntryAndOutById(entryAndOutID);
    }

    /**
     * 查询样品出入库列表
     * @param entryAndOutVO 样品出入库
     * @return 分页信息
     */
    @Override
    public TableDataInfo selectEntryAndOutList(EntryAndOutVO entryAndOutVO)
    {
        PagingUtils.startPage();
        //用于返回的VO列表
        List<EntryAndOutVO> entryAndOutVOList = entryAndOutMapper.selectEntryAndOutVOList(entryAndOutVO);

        for (EntryAndOutVO entryAndOutVO1 : entryAndOutVOList){
            //获取领用/送库人名称
            entryAndOutVO1.setReceiverName(userMapper.selectUserById(entryAndOutVO1.getReceiverID()).getNickName());

            //当前样品
            CommissionSample commissionSample = commissionSampleMapper.selectCommissionSampleById(entryAndOutVO1.getSampleID());
            //样品详细信息
            Map<String, Object> map = new HashMap<>();
            map.put("sampleNumber",commissionSample.getSampleNumber());
            map.put("priority",commissionSample.getPriority());
            map.put("samplingMode",commissionSample.getSamplingMode());
            map.put("detectionCategory",commissionSample.getDetectionCategory());
            map.put("sampleDescription",commissionSample.getSampleDescription());
            map.put("sampleScientificName",commissionSample.getSampleScientificName());
            map.put("sampleQuantity",commissionSample.getSampleQuantity());
            map.put("samplingBase",commissionSample.getSamplingBase());
            map.put("productionUnitAddress",commissionSample.getProductionUnitAddress());
            map.put("measureUnit",commissionSample.getMeasureUnit());
            map.put("sampleImage",commissionSample.getSampleImage());

            entryAndOutVO1.setDetail(map);
        }
        return PagingUtils.getDataTable(entryAndOutVOList);
    }


    /**
     * 新增样品出入库
     *
     * @param entryAndOut 样品出入库
     * @return 结果
     */
    @Override
    public int insertEntryAndOut(EntryAndOut entryAndOut)
    {
        return entryAndOutMapper.insertEntryAndOut(entryAndOut);
    }

    /**
     * 修改样品出入库
     *
     * @param entryAndOut 样品出入库
     * @return 结果
     */
    @Override
    public int updateEntryAndOut(EntryAndOut entryAndOut)
    {
        return entryAndOutMapper.updateEntryAndOut(entryAndOut);
    }

    /**
     * 批量删除样品出入库
     *
     * @param entryAndOutIDs 需要删除的样品出入库ID
     * @return 结果
     */
    @Override
    public int deleteEntryAndOutByIds(Long[] entryAndOutIDs)
    {
        return entryAndOutMapper.deleteEntryAndOutByIds(entryAndOutIDs);
    }

    /**
     * 删除样品出入库信息
     *
     * @param entryAndOutID 样品出入库ID
     * @return 结果
     */
    @Override
    public int deleteEntryAndOutById(Long entryAndOutID)
    {
        return entryAndOutMapper.deleteEntryAndOutById(entryAndOutID);
    }


}
