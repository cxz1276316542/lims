package com.ruoyi.commission.mapper;

import com.ruoyi.commission.domain.Commission;
import com.ruoyi.commission.domain.vo.DpesticideVO;
import com.ruoyi.commission.domain.vo.StandardVO;
import com.ruoyi.commission.domain.vo.UnitVO;
import com.ruoyi.common.core.domain.entity.SysUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 委托样品Mapper接口
 * 
 * @author lkun
 * @date 2021-07-23
 */
public interface CommissionMapper 
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
     * 删除委托样品
     * 
     * @param sampleID 委托样品ID
     * @return 结果
     */
    public int deleteCommissionById(Long sampleID);

    /**
     * 批量删除委托样品
     * 
     * @param sampleIDs 需要删除的数据ID
     * @return 结果
     */
    public int deleteCommissionByIds(Long[] sampleIDs);

    @Select("select JCBZ_ID id ,JCBZ_BH standardCode from xlk_jcbz;")
    List<StandardVO> selectDetectionStandard();

    @Select("select JLDW_ID id ,JLDW_MC unit from xlk_jldw where JLDW_BS = 1")
    List<UnitVO> selectUnit();

    @Select("select QY_ID from xt_qy where KH_ID=#{userId}")
    Long getCompanyID(@Param("userId") Long userId);

    List<Commission> selectCommissionBySample(@Param("userId") long userId,@Param("sampleName") String sampleName);

    @Select("select WTYP_WTBH from ypjc_wtyp order by WTYP_ID desc limit 1")
    String findLastRecord();

    @Select("select QY_MC from xt_qy where KH_ID = #{userId}")
    String selectCompanyByUser(@Param("userId") Long userId);


    List<DpesticideVO> selectDpesticide(Long userId, String sampleName);
}
