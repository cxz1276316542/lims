package com.ruoyi.limit.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.limit.mapper.StandardMapper;
import com.ruoyi.limit.domain.Standard;
import com.ruoyi.limit.service.IStandardService;

/**
 * 检测标准Service业务层处理
 * 
 * @author lkun
 * @date 2021-07-20
 */
@Service
public class StandardServiceImpl implements IStandardService 
{
    @Autowired
    private StandardMapper standardMapper;

    /**
     * 查询检测标准
     * 
     * @param standard 检测标准ID
     * @return 检测标准
     */
    @Override
    public Standard selectStandardById(Long standard)
    {
        return standardMapper.selectStandardById(standard);
    }

    /**
     * 查询检测标准列表
     * 
     * @param standard 检测标准
     * @return 检测标准
     */
    @Override
    public List<Standard> selectStandardList(Standard standard)
    {
        return standardMapper.selectStandardList(standard);
    }

    /**
     * 新增检测标准
     * 
     * @param standard 检测标准
     * @return 结果
     */
    @Override
    public int insertStandard(Standard standard)
    {
        return standardMapper.insertStandard(standard);
    }

    /**
     * 修改检测标准
     * 
     * @param standard 检测标准
     * @return 结果
     */
    @Override
    public int updateStandard(Standard standard)
    {
        return standardMapper.updateStandard(standard);
    }

    /**
     * 批量删除检测标准
     * 
     * @param standards 需要删除的检测标准ID
     * @return 结果
     */
    @Override
    public int deleteStandardByIds(Long[] standards)
    {
        return standardMapper.deleteStandardByIds(standards);
    }

    /**
     * 删除检测标准信息
     * 
     * @param standard 检测标准ID
     * @return 结果
     */
    @Override
    public int deleteStandardById(Long standard)
    {
        return standardMapper.deleteStandardById(standard);
    }
}
