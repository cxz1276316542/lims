package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Number;
import com.ruoyi.system.domain.vo.NumberVO;
import org.apache.ibatis.annotations.Select;

/**
 * 编号生成Mapper接口
 *
 * @author lkun
 * @date 2021-08-22
 */
public interface NumberMapper
{
    /**
     * 查询编号生成
     *
     * @param numberID 编号生成ID
     * @return 编号生成
     */
    public Number selectNumberById(Long numberID);

    /**
     * 查询编号生成列表
     *
     * @param number 编号生成
     * @return 编号生成集合
     */
    public List<Number> selectNumberList(Number number);

    /**
     * 新增编号生成
     *
     * @param number 编号生成
     * @return 结果
     */
    public int insertNumber(Number number);

    /**
     * 修改编号生成
     *
     * @param number 编号生成
     * @return 结果
     */
    public int updateNumber(Number number);

    /**
     * 删除编号生成
     *
     * @param numberID 编号生成ID
     * @return 结果
     */
    public int deleteNumberById(Long numberID);

    /**
     * 批量删除编号生成
     *
     * @param numberIDs 需要删除的数据ID
     * @return 结果
     */
    public int deleteNumberByIds(Long[] numberIDs);

    /**
     * 查找委托编号的最后一条记录
     * @return
     */
    @Select("select WTYP_WTBH from ypjc_wtyp order by WTYP_ID desc limit 1")
    String findLastCommissionRecord();

    /**
     * 查询到委托样品的最后一条记录编号
     */
    @Select("select YP_BH from ypjc_wtyp order by WTYP_ID DESC limit 1")
    String findLastSampleRecord();

    /**
     * 查找最后一条任务分派的记录的任务分派编号
     */
    @Select("select RWFP_BH from ypjc_jcxm_rwfp order by RWFP_ID desc limit 1")
    String findLastTaskAssignRecord();

    /**
     * 查找检测报告最后一条记录
     */
    @Select("select JCBG_BH from ypjc_jcbg order by JCBG_ID desc limit 1")
    String findLastReportRecord();
}
