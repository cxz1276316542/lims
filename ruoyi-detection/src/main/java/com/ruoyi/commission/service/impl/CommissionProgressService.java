package com.ruoyi.commission.service.impl;

import java.util.List;

import com.ruoyi.commission.domain.CommissionManage;
import com.ruoyi.commission.domain.vo.OrderProgressVO;
import com.ruoyi.commission.mapper.CommissionProgressMapper;
import com.ruoyi.commission.service.ICommissionProgressService;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.commission.mapper.CommissionMapper;
import com.ruoyi.commission.domain.Commission;
import com.ruoyi.commission.service.ICommissionService;

/**
 * 委托进度Service业务层处理
 *
 * @author lkun
 * @date 2021-08-03
 */
@Service
public class CommissionProgressService implements ICommissionProgressService
{
    @Autowired
    private CommissionProgressMapper commissionMapper;

    /**
     * 查询委托进度
     *
     * @param sampleID 委托进度ID
     * @return 委托进度
     */
    @Override
    public Commission selectCommissionById(Long sampleID)
    {
        return commissionMapper.selectCommissionById(sampleID);
    }

    /**
     * 查询委托进度列表
     *
     * @param commission 委托进度
     * @return 委托进度
     */
    @Override
    public List<Commission> selectCommissionList(CommissionManage commission)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        commission.setCustomerID(user.getUserId());
        return commissionMapper.selectCommissionList(commission);
    }

    /**
     * 新增委托进度
     *
     * @param commission 委托进度
     * @return 结果
     */
    @Override
    public int insertCommission(Commission commission)
    {
        return commissionMapper.insertCommission(commission);
    }

    /**
     * 修改委托进度
     *
     * @param commission 委托进度
     * @return 结果
     */
    @Override
    public int updateCommission(Commission commission)
    {
        return commissionMapper.updateCommission(commission);
    }

    /**
     * 批量删除委托进度
     *
     * @param sampleIDs 需要删除的委托进度ID
     * @return 结果
     */
    @Override
    public int deleteCommissionByIds(Long[] sampleIDs)
    {
        return commissionMapper.deleteCommissionByIds(sampleIDs);
    }

    /**
     * 删除委托进度信息
     *
     * @param sampleID 委托进度ID
     * @return 结果
     */
    @Override
    public int deleteCommissionById(Long sampleID)
    {
        return commissionMapper.deleteCommissionById(sampleID);
    }

    @Override
    public long getProgress(long sampleId) {
        return commissionMapper.selectCommissionProgressById(sampleId);
    }

    @Override
    public List<OrderProgressVO> getOrderProgress(long sampleId) {
        List<OrderProgressVO> res = commissionMapper.selectOrderProgressById(sampleId);
        for(OrderProgressVO t : res){
            modifyProgreeNum(t);
        }
        return res;
    }

    private void modifyProgreeNum(OrderProgressVO orderProgressVO){
        int progress = orderProgressVO.getProgress();
        switch (progress){
            case 10 : progress = 1;break;
            case 11 : progress = 2;break;
            case 20 : progress = 3;break;
            case 21 : progress = 4;break;
            case 30 : progress = 5;break;
            case 31 : progress = 6;break;
            case 40 : progress = 7;break;
            case 41 : progress = 8;break;
            case 42 : progress = 9;break;
            case 43 : progress = 10;break;
        }
        orderProgressVO.setProgress(progress);
    }
}
