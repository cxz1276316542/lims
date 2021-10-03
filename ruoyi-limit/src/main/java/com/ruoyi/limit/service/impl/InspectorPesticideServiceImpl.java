package com.ruoyi.limit.service.impl;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.limit.domain.Pesticide;
import com.ruoyi.limit.mapper.PesticideMapper;
import com.ruoyi.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.limit.mapper.InspectorPesticideMapper;
import com.ruoyi.limit.domain.InspectorPesticide;
import com.ruoyi.limit.service.IInspectorPesticideService;

/**
 * 检测员-检测项目Service业务层处理
 * 
 * @author dlk
 * @date 2021-08-28
 */
@Service
public class InspectorPesticideServiceImpl implements IInspectorPesticideService
{
    @Autowired
    private InspectorPesticideMapper inspectorPesticideMapper;

    @Autowired
    private PesticideMapper pesticideMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 查询检测员-检测项目
     * 
     * @param inspectorPesticideID 检测员-检测项目ID
     * @return 检测员-检测项目
     */
    @Override
    public InspectorPesticide selectinspectorPesticideById(Long inspectorPesticideID)
    {
        InspectorPesticide ins = inspectorPesticideMapper.selectinspectorPesticideById(inspectorPesticideID);
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        Pesticide pesticide = pesticideMapper.selectPesticideById(ins.getPesticideID(),detectionCenterID);
        ins.setPesticideName(pesticide.getPesticideName());
        SysUser sysUser = sysUserMapper.selectUserById(ins.getInspectorID());
        ins.setInspectorName(sysUser.getNickName());
        return ins;
    }

    /**
     * 查询检测员-检测项目列表
     * 
     * @param inspectorPesticide 检测员-检测项目
     * @return 检测员-检测项目
     */
    @Override
    public List<InspectorPesticide> selectinspectorPesticideList(InspectorPesticide inspectorPesticide)
    {
        List<InspectorPesticide> list = inspectorPesticideMapper.selectinspectorPesticideList(inspectorPesticide);
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        for (InspectorPesticide ins:list) {
            Pesticide pesticide = pesticideMapper.selectPesticideById(ins.getPesticideID(),detectionCenterID);
            if(pesticide != null)
            {
                ins.setPesticideName(pesticide.getPesticideName());
            }
            SysUser sysUser = sysUserMapper.selectUserById(ins.getInspectorID());
            ins.setInspectorName(sysUser.getNickName());
        }
        return list;
    }

    /**
     * 新增检测员-检测项目
     * 
     * @param inspectorPesticide 检测员-检测项目
     * @return 结果
     */
    @Override
    public String insertInspectorPesticide(InspectorPesticide inspectorPesticide)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        inspectorPesticide.setDetectionCenterID(detectionCenterID);
        InspectorPesticide isExist = inspectorPesticideMapper.selectInspectorPesticideExist(inspectorPesticide);
        if(StringUtils.isNull(isExist)){
            int ret = inspectorPesticideMapper.insertInspectorPesticide(inspectorPesticide);
            return ret > 0 ? "操作成功" : "操作失败";
        }
        return "请勿重复插入";
    }

    /**
     * 修改检测员-检测项目
     * 
     * @param inspectorPesticide 检测员-检测项目
     * @return 结果
     */
    @Override
    public int updateinspectorPesticide(InspectorPesticide inspectorPesticide)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        inspectorPesticide.setDetectionCenterID(detectionCenterID);
        return inspectorPesticideMapper.updateinspectorPesticide(inspectorPesticide);
    }

    /**
     * 批量删除检测员-检测项目
     * 
     * @param inspectorPesticideIDs 需要删除的检测员-检测项目ID
     * @return 结果
     */
    @Override
    public int deleteinspectorPesticideByIds(Long[] inspectorPesticideIDs)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        return inspectorPesticideMapper.deleteinspectorPesticideByIds(inspectorPesticideIDs,detectionCenterID);
    }

    /**
     * 删除检测员-检测项目信息
     * 
     * @param inspectorPesticideID 检测员-检测项目ID
     * @return 结果
     */
    @Override
    public int deleteinspectorPesticideById(Long inspectorPesticideID)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        return inspectorPesticideMapper.deleteinspectorPesticideById(inspectorPesticideID,detectionCenterID);
    }
}
