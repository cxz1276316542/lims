package com.ruoyi.commission.service;

import com.ruoyi.commission.domain.Commission;
import com.ruoyi.commission.domain.dto.CommissionDTO;
import com.ruoyi.commission.domain.vo.CustomerDataVO;
import com.ruoyi.commission.domain.vo.DpesticideVO;
import com.ruoyi.commission.domain.vo.StandardVO;
import com.ruoyi.commission.domain.vo.UnitVO;
import com.ruoyi.common.core.domain.entity.SysUser;

import java.util.List;

/**
 * 委托样品Service接口
 * 
 * @author lkun
 * @date 2021-07-23
 */
public interface ICommissionService 
{
    /**
     * 查询委托样品
     * 
     * @param sampleID 委托样品ID
     * @return 委托样品
     */
    public Commission selectCommissionById(Long sampleID);

    /**
     * 查询委托样品列表
     * 
     * @param commission 委托样品
     * @return 委托样品集合
     */
    public List<Commission> selectCommissionList(Commission commission);

    /**
     * 新增委托样品
     * 
     * @param commission 委托样品
     * @return 结果
     */
    public int insertCommission(Commission commission);

    /**
     * 修改委托样品
     * 
     * @param commission 委托样品
     * @return 结果
     */
    public int updateCommission(Commission commission);

    /**
     * 批量删除委托样品
     * 
     * @param sampleIDs 需要删除的委托样品ID
     * @return 结果
     */
    public int deleteCommissionByIds(Long[] sampleIDs);

    /**
     * 删除委托样品信息
     * 
     * @param sampleID 委托样品ID
     * @return 结果
     */
    public int deleteCommissionById(Long sampleID);

    List<StandardVO> getDetectionStandard();

    List<UnitVO> getUnits();

    boolean submit(CommissionDTO commissionDTO);

    CommissionDTO getHistory(SysUser user, String sampleName);

    CustomerDataVO getCustomerData();

    String getOrderId();

    List<DpesticideVO> getDpesticide(String sampleName);
}
