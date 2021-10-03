package com.ruoyi.detection.service;

import java.util.List;
import com.ruoyi.detection.domain.Enterprise;

/**
 * 企业信息Service接口
 * 
 * @author zhangkui
 * @date 2021-07-22
 */
public interface IEnterpriseService 
{
    /**
     * 查询企业信息
     * 
     * @param enterpriseID 企业信息ID
     * @return 企业信息
     */
    public Enterprise selectEnterpriseById(Long enterpriseID);

    /**
     * 查询企业信息列表
     * 
     * @param enterprise 企业信息
     * @return 企业信息集合
     */
    public List<Enterprise> selectEnterpriseList(Enterprise enterprise);

    /**
     * 新增企业信息
     * 
     * @param enterprise 企业信息
     * @return 结果
     */
    public int insertEnterprise(Enterprise enterprise);

    /**
     * 修改企业信息
     * 
     * @param enterprise 企业信息
     * @return 结果
     */
    public int updateEnterprise(Enterprise enterprise);

    /**
     * 批量删除企业信息
     * 
     * @param enterpriseIDs 需要删除的企业信息ID
     * @return 结果
     */
    public int deleteEnterpriseByIds(Long[] enterpriseIDs);

    /**
     * 删除企业信息信息
     * 
     * @param enterpriseID 企业信息ID
     * @return 结果
     */
    public int deleteEnterpriseById(Long enterpriseID);
}
