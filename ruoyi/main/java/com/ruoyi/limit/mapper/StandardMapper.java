package com.ruoyi.limit.mapper;

import java.util.List;
import com.ruoyi.limit.domain.Standard;

/**
 * 检测标准Mapper接口
 * 
 * @author lkun
 * @date 2021-07-20
 */
public interface StandardMapper 
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
     * 删除检测标准
     * 
     * @param standard 检测标准ID
     * @return 结果
     */
    public int deleteStandardById(Long standard);

    /**
     * 批量删除检测标准
     * 
     * @param standards 需要删除的数据ID
     * @return 结果
     */
    public int deleteStandardByIds(Long[] standards);
}
