package com.ruoyi.commission.mapper;

import java.util.List;
import com.ruoyi.commission.domain.Commission;
import com.ruoyi.commission.domain.CommissionManage;
import com.ruoyi.commission.domain.vo.OrderProgressVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.security.core.parameters.P;

/**
 * 委托进度Mapper接口
 *
 * @author lkun
 * @date 2021-08-03
 */
public interface CommissionProgressMapper
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
     * 删除委托进度
     *
     * @param sampleID 委托进度ID
     * @return 结果
     */
    public int deleteCommissionById(Long sampleID);

    /**
     * 批量删除委托进度
     *
     * @param sampleIDs 需要删除的数据ID
     * @return 结果
     */
    public int deleteCommissionByIds(Long[] sampleIDs);

    @Select("select YPJCZT_MQZT progree from ypjc_ypjczt where YP_ID=#{sampleId}")
    long selectCommissionProgressById(@Param("sampleId") long sampleId);

    @Select("select b.WTYP_XM sampleName,b.YP_BH sampleNumber, b.WTYP_WTBH orderNumber, a.YPJCZT_MQZT progress from ypjc_ypjczt a\n" +
            "inner  join ypjc_wtyp b\n" +
            "on a.YP_ID = b.WTYP_ID\n" +
            "where a.YP_ID in( select WTYP_ID from ypjc_wtyp where WTYP_WTBH = (select WTYP_WTBH from ypjc_wtyp where WTYP_ID = #{sampleId}))")
    List<OrderProgressVO> selectOrderProgressById(@Param("sampleId") long sampleId);
}