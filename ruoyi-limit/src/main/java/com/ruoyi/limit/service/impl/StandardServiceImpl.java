package com.ruoyi.limit.service.impl;

import java.util.List;
import java.util.Map;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.limit.domain.DetectionMethodStandard;
import com.ruoyi.limit.domain.vo.LimitInfoVo;
import com.ruoyi.limit.mapper.DetectionMethodStandardMapper;
import com.ruoyi.limit.mapper.LimitInfoVoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.limit.mapper.StandardMapper;
import com.ruoyi.limit.domain.Standard;
import com.ruoyi.limit.service.IStandardService;

/**
 * 检测标准Service业务层处理
 * 
 * @author wlf
 * @date 2021-07-20
 */
@Service
public class StandardServiceImpl implements IStandardService
{
    @Autowired
    private StandardMapper standardMapper;

    @Autowired
    private LimitInfoVoMapper limitInfoVoMapper;

    @Autowired
    private DetectionMethodStandardMapper detectionMethodStandardMapper;

    /**
     * 查询检测标准
     * 
     * @param standard 检测标准ID
     * @return 检测标准
     */
    @Override
    public Standard selectStandardById(Long standard)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        return standardMapper.selectStandardById(standard, detectionCenterID);
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
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        standard.setDetectionCenterID(detectionCenterID);
        return standardMapper.selectStandardList(standard);
    }

    /**
     * 获取检测标准ID-检测编号map集合
     * @return 检测标准集合
     */
    public List<Map<Long,String>> selectStandardMap(){
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        return standardMapper.selectStandardMap(detectionCenterID);
    }

    /**
     * 新增检测标准
     * @param standard 检测标准
     * @return 结果
     */
    @Override
    public String insertStandard(Standard standard)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        standard.setDetectionCenterID(detectionCenterID);
        Standard isExist = standardMapper.selectStandardExist(standard);
        if(StringUtils.isNull(isExist)){
            int ret = standardMapper.insertStandard(standard);
            return ret > 0 ? "操作成功":"操作失败";
        }
        return "重复插入";
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
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        standard.setDetectionCenterID(detectionCenterID);
        if(standard.getIsValid() == 0){
            DetectionMethodStandard detectionMethodStandard = new DetectionMethodStandard();
            detectionMethodStandard.setDetectionCenterID(detectionCenterID);
            detectionMethodStandard.setStandardID(standard.getStandard());
            detectionMethodStandard.setIsValid(0);
            detectionMethodStandardMapper.updateDetectionMethodStandard(detectionMethodStandard);
        }
        return standardMapper.updateStandard(standard);
    }

    /**
     * 批量删除检测标准
     * @param standards 需要删除的检测标准ID
     * @return 结果
     */
    @Override
    public int deleteStandardByIds(Long[] standards)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        //删除限量库
        limitInfoVoMapper.deleteLimitInfoVoListByStandards(standards,detectionCenterID);
        //删除检测方法-检测标准
        detectionMethodStandardMapper.deleteDetectionMethodStandardByStandardIds(standards,detectionCenterID);
        return standardMapper.deleteStandardByIds(standards,detectionCenterID);
    }

    /**
     * 删除检测标准信息
     * @param standard 检测标准ID
     * @return 结果
     */
    @Override
    public int deleteStandardById(Long standard)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        return standardMapper.deleteStandardById(standard, detectionCenterID);
    }
}
