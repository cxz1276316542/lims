package com.ruoyi.limit.service.impl;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.limit.mapper.DetectionMethodStandardMapper;
import com.ruoyi.limit.domain.DetectionMethodStandard;
import com.ruoyi.limit.service.IDetectionMethodStandardService;

/**
 * 检测方法-检测标准Service业务层处理
 * 
 * @author wlf
 * @date 2021-08-29
 */
@Service
public class DetectionMethodStandardServiceImpl implements IDetectionMethodStandardService 
{
    @Autowired
    private DetectionMethodStandardMapper detectionMethodStandardMapper;

    /**
     * 查询检测方法-检测标准
     * 
     * @param detectionMethodStandardID 检测方法-检测标准ID
     * @return 检测方法-检测标准
     */
    @Override
    public DetectionMethodStandard selectDetectionMethodStandardById(Long detectionMethodStandardID)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        return detectionMethodStandardMapper.selectDetectionMethodStandardById(detectionMethodStandardID, detectionCenterID);
    }

    /**
     * 查询检测方法-检测标准列表
     * 
     * @param detectionMethodStandard 检测方法-检测标准
     * @return 检测方法-检测标准
     */
    @Override
    public List<DetectionMethodStandard> selectDetectionMethodStandardList(DetectionMethodStandard detectionMethodStandard)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        detectionMethodStandard.setDetectionCenterID(detectionCenterID);
        return detectionMethodStandardMapper.selectDetectionMethodStandardList(detectionMethodStandard);
    }

    /**
     * 新增检测方法-检测标准
     * 
     * @param detectionMethodStandard 检测方法-检测标准
     * @return 结果
     */
    @Override
    public String insertDetectionMethodStandard(DetectionMethodStandard detectionMethodStandard)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        detectionMethodStandard.setDetectionCenterID(detectionCenterID);
        DetectionMethodStandard isExist = detectionMethodStandardMapper.selectDetectionMethodStandardExist(detectionMethodStandard);
        if(StringUtils.isNull(isExist)){
            int ret = detectionMethodStandardMapper.insertDetectionMethodStandard(detectionMethodStandard);
            return ret > 0 ? "操作成功":"操作失败";
        }
        return "重复插入";
    }

    /**
     * 修改检测方法-检测标准
     * 
     * @param detectionMethodStandard 检测方法-检测标准
     * @return 结果
     */
    @Override
    public int updateDetectionMethodStandard(DetectionMethodStandard detectionMethodStandard)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        detectionMethodStandard.setDetectionCenterID(detectionCenterID);
        return detectionMethodStandardMapper.updateDetectionMethodStandard(detectionMethodStandard);
    }

    /**
     * 批量删除检测方法-检测标准
     * 
     * @param detectionMethodStandardIDs 需要删除的检测方法-检测标准ID
     * @return 结果
     */
    @Override
    public int deleteDetectionMethodStandardByIds(Long[] detectionMethodStandardIDs)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        return detectionMethodStandardMapper.deleteDetectionMethodStandardByIds(detectionMethodStandardIDs, detectionCenterID);
    }

    /**
     * 删除检测方法-检测标准信息
     * 
     * @param detectionMethodStandardID 检测方法-检测标准ID
     * @return 结果
     */
    @Override
    public int deleteDetectionMethodStandardById(Long detectionMethodStandardID)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        return detectionMethodStandardMapper.deleteDetectionMethodStandardById(detectionMethodStandardID, detectionCenterID);
    }
}
