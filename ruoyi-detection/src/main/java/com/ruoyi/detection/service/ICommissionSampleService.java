package com.ruoyi.detection.service;

import com.ruoyi.detection.domain.CommissionSample;
import com.ruoyi.detection.domain.vo.*;

import java.util.List;

/**
 * 委托样品Service接口
 *
 * @author lkun
 * @date 2021-07-21
 */
public interface ICommissionSampleService
{
    /**
     * 查询委托样品
     *
     * @param sampleID 委托样品ID
     * @return 委托样品
     */
    public CommissionSample selectCommissionSampleById(Long sampleID);

    /**
     * 查询委托样品列表
     *
     * @param commissionSample 委托样品
     * @return 委托样品集合
     */
    public List<CommissionSample> selectCommissionSampleList(CommissionSample commissionSample);

    /**
     * 新增委托样品
     *
     * @param commissionSample 委托样品
     * @return 结果
     */
    public int insertCommissionSample(CommissionSample commissionSample);

    /**
     * 批量新增委托样品
     * @param CommissionRegister 委托样品
     * @return 是否成功
     */
    int insertCommissionSampleBatch(CommissionRegister commissionRegister);

    /**
     * 修改委托样品
     *
     * @param commissionSample 委托样品
     * @return 结果
     */
    public int updateCommissionSample(CommissionSample commissionSample);

    /**
     * 批量删除委托样品
     *
     * @param sampleIDs 需要删除的委托样品ID
     * @return 结果
     */
    public int deleteCommissionSampleByIds(Long[] sampleIDs);

    /**
     * 删除委托样品信息
     *
     * @param sampleID 委托样品ID
     * @return 结果
     */
    public int deleteCommissionSampleById(Long sampleID);

    /**
     * 查找所有的检测标准
     * @return List<DetectionStandardVO>
     */
    public List<DetectionStandardVO> findAllStandard();

    /**
     * 查找所有的样品学名
     * @return List<String>
     */
    List<String> findAllSampleAcademicName();

    /**
     * 查找所有的计量单位
     * @return
     */
    List<MeasureUnitVO> findAllMeasureUnit();

    int submitSampleBatch(Long[] ids);

    /**
     * 更新样品状态
     * @return
     */
    int updateStatus(Long[] sampleIDs, int status);

    /**
     * 查询所有用户
     * @return
     */
    List<CustomerVO> searchAllCustomers();

    /**
     * 查询委托样品
     */
    List<CommissionSampleRegisterVO> searchAllCommissionSampleRegister(QueryCommissionSampleVO queryCommissionSampleVO);

    /**
     * 查询委托样品历史
     */
    List<CommissionSampleRegisterVO> searchAllCommissionSampleRegisterHistory(QueryCommissionSampleVO queryCommissionSampleVO);


    /**
     * 查询所有的公司ID和地址
     * @return
     */
    List<CompanyVO> searchAllCompanies(Long id);


    /**
     * 根据客户ID查找到最新的一条记录
     */
    CommissionSample searchLastRecordByCustomerID(Long customerID);


    /**
     * 根据检品ID，查询到所有的检测项目
     */
    List<IDAndNameVO> findItemListBySampleID(String sampleName);

}
