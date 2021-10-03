package com.ruoyi.commission.service;

import java.util.List;
import com.ruoyi.commission.domain.Commission;
import com.ruoyi.commission.domain.CommissionManage;

/**
 * 委托样品Service接口
 *
 * @author lkun
 * @date 2021-08-03
 */
public interface ICommissionManageService
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
    public List<Commission> selectCommissionList(CommissionManage commission);

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

    String getReview(long sampleId);
}
