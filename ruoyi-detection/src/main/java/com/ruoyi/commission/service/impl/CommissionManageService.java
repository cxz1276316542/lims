package com.ruoyi.commission.service.impl;

import java.security.Security;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.ruoyi.commission.domain.CommissionManage;
import com.ruoyi.commission.mapper.CommissionManageMapper;
import com.ruoyi.commission.service.ICommissionManageService;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.detection.domain.vo.IDAndNameVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.commission.mapper.CommissionMapper;
import com.ruoyi.commission.domain.Commission;
import com.ruoyi.commission.service.ICommissionService;

/**
 * 委托样品Service业务层处理
 *
 * @author lkun
 * @date 2021-08-03
 */
@Service
public class CommissionManageService implements ICommissionManageService
{
    @Autowired
    private CommissionManageMapper commissionMapper;

    /**
     * 查询委托样品
     *
     * @param sampleID 委托样品ID
     * @return 委托样品
     */
    @Override
    public Commission selectCommissionById(Long sampleID)
    {
        Commission commission = commissionMapper.selectCommissionById(sampleID);
        String detectionDescription = commission.getSampleDescription();
        if(StringUtils.isEmpty(detectionDescription)){
            return commission;
        }
        String[] split = detectionDescription.split(",");
        List<Long> ids = Arrays.stream(split).mapToLong(t->Long.valueOf(t)).boxed().collect(Collectors.toList());
        List<IDAndNameVO> pesticides = commissionMapper.selectPesticideById(ids);
        String pes = listToString(pesticides);
        commission.setDetectionDescriptionInfo(pes);
        return commission;
    }

    /**
     * 查询委托样品列表
     *
     * @param commission 委托样品
     * @return 委托样品
     */
    @Override
    public List<Commission> selectCommissionList(CommissionManage commission)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        commission.setCustomerID(user.getUserId());
        List<Commission> commissions = commissionMapper.selectCommissionList(commission);
        for(Commission c : commissions){
            String detectionDescription = c.getSampleDescription();
            if(StringUtils.isEmpty(detectionDescription)){
                continue;
            }
            String[] split = detectionDescription.split(",");
            List<Long> ids = Arrays.stream(split).mapToLong(t->Long.valueOf(t)).boxed().collect(Collectors.toList());
            List<IDAndNameVO> pesticides = commissionMapper.selectPesticideById(ids);
            String pes = listToString(pesticides);
            c.setDetectionDescriptionInfo(pes);
        }
        return commissions;
    }

    private String listToString(List<IDAndNameVO> pesticides) {
        StringBuilder builder = new StringBuilder();
        for(IDAndNameVO s : pesticides){
            builder.append(s.getName());
            builder.append(",");
        }
        return builder.toString();
    }

    /**
     * 新增委托样品
     *
     * @param commission 委托样品
     * @return 结果
     */
    @Override
    public int insertCommission(Commission commission)
    {
        return commissionMapper.insertCommission(commission);
    }

    /**
     * 修改委托样品
     *
     * @param commission 委托样品
     * @return 结果
     */
    @Override
    public int updateCommission(Commission commission)
    {
        return commissionMapper.updateCommission(commission);
    }

    /**
     * 批量删除委托样品
     *
     * @param sampleIDs 需要删除的委托样品ID
     * @return 结果
     */
    @Override
    public int deleteCommissionByIds(Long[] sampleIDs)
    {
        return commissionMapper.deleteCommissionByIds(sampleIDs);
    }

    /**
     * 删除委托样品信息
     *
     * @param sampleID 委托样品ID
     * @return 结果
     */
    @Override
    public int deleteCommissionById(Long sampleID)
    {
        return commissionMapper.deleteCommissionById(sampleID);
    }

    @Override
    public String getReview(long sampleId) {
        return commissionMapper.selectReview(sampleId);
    }
}
