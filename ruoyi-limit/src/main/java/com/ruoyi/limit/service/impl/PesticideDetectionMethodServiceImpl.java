package com.ruoyi.limit.service.impl;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.limit.mapper.PesticideDetectionMethodMapper;
import com.ruoyi.limit.domain.PesticideDetectionMethod;
import com.ruoyi.limit.service.IPesticideDetectionMethodService;

/**
 * 检测项目-检测方法Service业务层处理
 * 
 * @author wlf
 * @date 2021-08-28
 */
@Service
public class PesticideDetectionMethodServiceImpl implements IPesticideDetectionMethodService
{
    @Autowired
    private PesticideDetectionMethodMapper pesticideDetectionMethodMapper;

    /**
     * 查询检测项目-检测方法
     * 
     * @param pesticideDetectionMethodID 检测项目-检测方法ID
     * @return 检测项目-检测方法
     */
    @Override
    public PesticideDetectionMethod selectPesticideDetectionMethodById(Long pesticideDetectionMethodID)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        return pesticideDetectionMethodMapper.selectPesticideDetectionMethodById(pesticideDetectionMethodID, detectionCenterID);
    }

    /**
     * 查询检测项目-检测方法列表
     * 
     * @param pesticideDetectionMethod 检测项目-检测方法
     * @return 检测项目-检测方法
     */
    @Override
    public List<PesticideDetectionMethod> selectPesticideDetectionMethodList(PesticideDetectionMethod pesticideDetectionMethod)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        pesticideDetectionMethod.setDetectionCenterID(detectionCenterID);
        return pesticideDetectionMethodMapper.selectPesticideDetectionMethodList(pesticideDetectionMethod);
    }

    /**
     * 新增检测项目-检测方法
     * 
     * @param pesticideDetectionMethod 检测项目-检测方法
     * @return 结果
     */
    @Override
    public String insertPesticideDetectionMethod(PesticideDetectionMethod pesticideDetectionMethod)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        pesticideDetectionMethod.setDetectionCenterID(detectionCenterID);
        PesticideDetectionMethod isExist = pesticideDetectionMethodMapper.selectPesticideDetectionMethodExist(pesticideDetectionMethod);
        if(StringUtils.isNull(isExist))
        {
            int ret = pesticideDetectionMethodMapper.insertPesticideDetectionMethod(pesticideDetectionMethod);;
            return ret > 0 ? "操作成功":"操作失败";
        }
        return "重复插入";
    }

    /**
     * 修改检测项目-检测方法
     * 
     * @param pesticideDetectionMethod 检测项目-检测方法
     * @return 结果
     */
    @Override
    public int updatePesticideDetectionMethod(PesticideDetectionMethod pesticideDetectionMethod)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        pesticideDetectionMethod.setDetectionCenterID(detectionCenterID);
        return pesticideDetectionMethodMapper.updatePesticideDetectionMethod(pesticideDetectionMethod);
    }

    /**
     * 批量删除检测项目-检测方法
     * 
     * @param pesticideDetectionMethodIDs 需要删除的检测项目-检测方法ID
     * @return 结果
     */
    @Override
    public int deletePesticideDetectionMethodByIds(Long[] pesticideDetectionMethodIDs)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        return pesticideDetectionMethodMapper.deletePesticideDetectionMethodByIds(pesticideDetectionMethodIDs, detectionCenterID);
    }

    /**
     * 删除检测项目-检测方法信息
     * 
     * @param pesticideDetectionMethodID 检测项目-检测方法ID
     * @return 结果
     */
    @Override
    public int deletePesticideDetectionMethodById(Long pesticideDetectionMethodID)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        return pesticideDetectionMethodMapper.deletePesticideDetectionMethodById(pesticideDetectionMethodID, detectionCenterID);
    }
}
