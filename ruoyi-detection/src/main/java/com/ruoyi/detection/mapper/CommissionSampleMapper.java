package com.ruoyi.detection.mapper;

import com.ruoyi.detection.domain.CommissionSample;
import com.ruoyi.detection.domain.vo.*;
import com.ruoyi.review.domain.Review;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.security.core.parameters.P;

import java.util.List;
import java.util.Map;

/**
 * 委托样品Mapper接口
 *
 * @author lkun
 * @date 2021-07-21
 */
public interface CommissionSampleMapper
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
     * 修改委托样品
     *
     * @param commissionSample 委托样品
     * @return 结果
     */
    public int updateCommissionSample(CommissionSample commissionSample);

    /**
     * 删除委托样品
     *
     * @param sampleID 委托样品ID
     * @return 结果
     */
    public int deleteCommissionSampleById(Long sampleID);

    /**
     * 批量删除委托样品
     *
     * @param sampleIDs 需要删除的数据ID
     * @return 结果
     */
    public int deleteCommissionSampleByIds(Long[] sampleIDs);

    /**
     * 查找所有的检测标准
     */
    @Select("select JCBZ_ID,JCBZ_BH from xlk_jcbz where SJYXX_BS = 1")
    List<DetectionStandardVO> findAllStandards();

    /**
     * 查找所有的样品学名
     */
    @Select("select distinct YP_XM from xlk_yp where SJYXX_BS = 1")
    List<String> findAllSampleAcademicName();

    /**
     * 查找所有有效的计量单位
     * @return 所有有效的计量单位集合
     */
    @Select("select JLDW_ID measureID,JLDW_MC measureName from xlk_jldw where JLDW_BS = 1")
    List<MeasureUnitVO> findAllValidMeasureUnit();

    /**
     * 通过样品ID更新样品状态
     * @return
     */
    int updateStatusById(Map<String, Object> map);

    /**
     * 查询未分配检测员且已完成登记的样品列表
     * @return
     */
    List<CommissionSample> selectNotAssignList();

    /**
     * 批量提交，提交就是 reviewStatus设置为4
     * @param ids
     * @return
     */
    int submitSampleBatch(@Param("ids") Long[] ids,@Param("operatorID") Long operatorID);


    /**
     * 查询已分配业务员的委托单列表
     * @param
     * @return 结果
     */
    List<CommissionSample> selectAssignedList();

    /**
     * 查询所有登记成功的委托单列表
     * @param
     * @return 结果
     */
    List<CommissionSample> selectAllAssignList();

    /**
     * 计算未分配检测员且已完成登记的样品列表
     * @return
     */
    int countNotAssignList();

    /**
     * 计算已分配业务员的委托单数量
     * @param
     * @return 结果
     */
    int countAssignedList();

    /**
     * 计算所有登记成功的委托单数量
     * @param
     * @return 结果
     */
    int countAllAssignList();

    /**
     * 查询所有用户
     * @return
     */
    @Select("select YH_ID customerID,YH_ZH name from xt_yh where YH_LX = '01' and YH_ZT = 0")
    List<CustomerVO> searchAllCustomers();


    /**
     * 查询委托样品
     */
    List<CommissionSampleRegisterVO> searchAllCommissionSampleRegister(QueryCommissionSampleVO queryCommissionSampleVO);

    /**
     * 查询根据所有企业ID和地址
     * @param id
     * @return
     */
    @Select("select QY_ID companyID, QY_DZ companyAddress from xt_qy where KH_ID = #{id}")
    List<CompanyVO> searchAllCompanies(@Param("id") Long id);

    /**
     * 根据id数组查询委托检品信息
     * @param ids id数组
     * @return
     */
    List<CommissionSample> selectCommissionSampleByIds(Long[] ids);

    /**
     * 根据编号查询委托检品
     * @param sampleNumber 样品编号
     * @return
     */
    List<CommissionSample> selectCommissionSampleBySampleNumber(String sampleNumber);

    /**
     * 根据样品id数组更新委托分发状态
     * @param sampleIDs 样品id
     * @param assignStatus 样品分发状态
     * @return
     */
    int updateCommissionAssignStatusById(@Param("sampleIDs") Long[] sampleIDs, @Param("assignStatus") int assignStatus);

    /**
     * 根据检品ID修改检品是否分发字段
     */
    @Update("update ypjc_wtyp set WTYP_YPFF = #{flag} where WTYP_ID = #{sampleID}")
    int updateAssignFlagBySampleID(@Param("sampleID") Long sampleID, @Param("flag") Integer flag);

    /**
     * 查询到委托样品的最后一条记录编号
     */
    @Select("select YP_BH from ypjc_wtyp order by WTYP_ID DESC limit 1")
    String findLastRecordNumber();


    /**
     * 根据客户ID查找到最新的一条记录
     */
    CommissionSample searchLastRecordByCustomerID(Long customerID);


    /**
     * 根据检品ID，查询到所有的检测项目
     */
    @Select("select distinct a.JCXM_ID ID, a.JCXM_MC name from xlk_jcxm a\n" +
            "where a.JCXM_ID in\n" +
            "      (select distinct b.JCXM_ID from xlk_yp_jcxm b where\n" +
            "             b.YPLB_ID in (select c.YPLB_ID from xlk_yp c where c.YP_XM = #{sampleName})\n" +
            "             and b.JCZX_ID = #{laboratoryID})")
    List<IDAndNameVO> findItemListBySampleID(@Param("sampleName") String sampleName,@Param("laboratoryID") Long laboratoryID);

    /**
     * 根据检测项目ID，找到对应检测项目名称
     */
    List<String> findItemListByIDs(Long[] ids);

    /**
     * 格局检测项目名称，查找到对应的检测项目ID
     */
    List<Long> findItemListByNames(List<String> names);

    /**
     * 根据样品id查找审核记录的个数
     * @param sampleID
     * @return
     */
    @Select("select count(SH_ID) from ypjc_sh where YP_ID = #{sampleID}")
    int findSampleSubmitRecord(@Param("sampleID") Long sampleID);
}
