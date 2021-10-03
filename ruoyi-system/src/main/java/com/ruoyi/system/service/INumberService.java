package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Number;
import com.ruoyi.system.domain.vo.NumberVO;

/**
 * 编号生成Service接口
 *
 * @author lkun
 * @date 2021-08-22
 */
public interface INumberService
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
     * 批量删除编号生成
     *
     * @param numberIDs 需要删除的编号生成ID
     * @return 结果
     */
    public int deleteNumberByIds(Long[] numberIDs);

    /**
     * 删除编号生成信息
     *
     * @param numberID 编号生成ID
     * @return 结果
     */
    public int deleteNumberById(Long numberID);

    /**
     * 查询到所有的编号内容
     * @return
     */
    List<NumberVO> searchAll();

    /**
     *
     * @param type
     * @return
     */
    List<Number> findByNumberType(Integer type);

    /**
     * 生成单个委托检品编号
     */
    String generateCommissionNumber();

    /**
     * 生成一批检品编号
     */
    List<String> generateSampleNumber(int size);

    /**
     * 生成一个任务单编号
     */
    String generateTaskNumber();

    /**
     * 生成一批检测报告
     */
    List<String> generateReportNumber(int size);
}
