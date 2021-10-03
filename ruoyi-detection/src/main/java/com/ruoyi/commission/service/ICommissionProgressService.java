package com.ruoyi.commission.service;

import java.util.List;
import com.ruoyi.commission.domain.Commission;
import com.ruoyi.commission.domain.CommissionManage;
import com.ruoyi.commission.domain.vo.OrderProgressVO;

/**
 * 委托进度Service接口
 *
 * @author lkun
 * @date 2021-08-03
 */
public interface ICommissionProgressService
{
    /**
     * 查询委托进度
     *
     * @param sampleID 委托进度ID
     * @return 委托进度
     */
    public Commission selectCommissionById(Long sampleID);

    /**
     * 查询委托进度列表
     *
     * @param commission 委托进度
     * @return 委托进度集合
     */
    public List<Commission> selectCommissionList(CommissionManage commission);

    /**
     * 新增委托进度
     *
     * @param commission 委托进度
     * @return 结果
     */
    public int insertCommission(Commission commission);

    /**
     * 修改委托进度
     *
     * @param commission 委托进度
     * @return 结果
     */
    public int updateCommission(Commission commission);

    /**
     * 批量删除委托进度
     *
     * @param sampleIDs 需要删除的委托进度ID
     * @return 结果
     */
    public int deleteCommissionByIds(Long[] sampleIDs);

    /**
     * 删除委托进度信息
     *
     * @param sampleID 委托进度ID
     * @return 结果
     */
    public int deleteCommissionById(Long sampleID);

    long getProgress(long sampleId);

    List<OrderProgressVO> getOrderProgress(long sampleId);
}