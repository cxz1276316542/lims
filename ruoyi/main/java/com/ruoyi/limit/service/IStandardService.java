package com.ruoyi.limit.service;

import java.util.List;
import com.ruoyi.limit.domain.Standard;

/**
 * 检测标准Service接口
 * 
 * @author lkun
 * @date 2021-07-20
 */
public interface IStandardService 
{
    /**
     * 查询检测标准
     * 
     * @param standard 检测标准ID
     * @return 检测标准
     */
    public Standard selectStandardById(Long standard);

    /**
     * 查询检测标准列表
     * 
     * @param standard 检测标准
     * @return 检测标准集合
     */
    public List<Standard> selectStandardList(Standard standard);

    /**
     * 新增检测标准
     * 
     * @param standard 检测标准
     * @return 结果
     */
    public int insertStandard(Standard standard);

    /**
     * 修改检测标准
     * 
     * @param standard 检测标准
     * @return 结果
     */
    public int updateStandard(Standard standard);

    /**
     * 批量删除检测标准
     * 
     * @param standards 需要删除的检测标准ID
     * @return 结果
     */
    public int deleteStandardByIds(Long[] standards);

    /**
     * 删除检测标准信息
     * 
     * @param standard 检测标准ID
     * @return 结果
     */
    public int deleteStandardById(Long standard);
}
