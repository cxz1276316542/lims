package com.ruoyi.detection.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.detection.mapper.EnterpriseMapper;
import com.ruoyi.detection.domain.Enterprise;
import com.ruoyi.detection.service.IEnterpriseService;

/**
 * 企业信息Service业务层处理
 * 
 * @author zhangkui
 * @date 2021-07-22
 */
@Service
public class EnterpriseServiceImpl implements IEnterpriseService 
{
    @Autowired
    private EnterpriseMapper enterpriseMapper;

    /**
     * 查询企业信息
     * 
     * @param enterpriseID 企业信息ID
     * @return 企业信息
     */
    @Override
    public Enterprise selectEnterpriseById(Long enterpriseID)
    {
        return enterpriseMapper.selectEnterpriseById(enterpriseID);
    }

    /**
     * 查询企业信息列表
     * 
     * @param enterprise 企业信息
     * @return 企业信息
     */
    @Override
    public List<Enterprise> selectEnterpriseList(Enterprise enterprise)
    {
        return enterpriseMapper.selectEnterpriseList(enterprise);
    }

    /**
     * 新增企业信息
     * 
     * @param enterprise 企业信息
     * @return 结果
     */
    @Override
    public int insertEnterprise(Enterprise enterprise)
    {
        enterprise.setCreateTime(DateUtils.getNowDate());
        return enterpriseMapper.insertEnterprise(enterprise);
    }

    /**
     * 修改企业信息
     * 
     * @param enterprise 企业信息
     * @return 结果
     */
    @Override
    public int updateEnterprise(Enterprise enterprise)
    {
        enterprise.setUpdateTime(DateUtils.getNowDate());
        return enterpriseMapper.updateEnterprise(enterprise);
    }

    /**
     * 批量删除企业信息
     * 
     * @param enterpriseIDs 需要删除的企业信息ID
     * @return 结果
     */
    @Override
    public int deleteEnterpriseByIds(Long[] enterpriseIDs)
    {
        return enterpriseMapper.deleteEnterpriseByIds(enterpriseIDs);
    }

    /**
     * 删除企业信息信息
     * 
     * @param enterpriseID 企业信息ID
     * @return 结果
     */
    @Override
    public int deleteEnterpriseById(Long enterpriseID)
    {
        return enterpriseMapper.deleteEnterpriseById(enterpriseID);
    }
}
