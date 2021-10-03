package com.ruoyi.limit.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.limit.mapper.SamplePesticideMapper;
import com.ruoyi.limit.domain.SamplePesticide;
import com.ruoyi.limit.service.ISamplePesticideService;

import javax.annotation.Resource;

/**
 * 样品-检测项目Service业务层处理
 * 
 * @author wlf
 * @date 2021-08-28
 */
@Service
public class SamplePesticideServiceImpl implements ISamplePesticideService
{
    @Autowired
    private SamplePesticideMapper samplePesticideMapper;

    @Resource
    private FoodServiceImpl foodService;


    /**
     * 查询样品-检测项目
     * 
     * @param samplePesticideID 样品-检测项目ID
     * @return 样品-检测项目
     */
    @Override
    public SamplePesticide selectSamplePesticideById(Long samplePesticideID)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        SamplePesticide samplePesticide = samplePesticideMapper.selectSamplePesticideById(samplePesticideID, detectionCenterID);
        String str = foodService.generateFoodTypeStr(samplePesticide.getSampleID());
        samplePesticide.setSampleTypeStr(str);
        return samplePesticide;
    }

    /**
     * 查询样品-检测项目列表
     * 
     * @param samplePesticide 样品-检测项目
     * @return 样品-检测项目
     */
    @Override
    public List<SamplePesticide> selectSamplePesticideList(SamplePesticide samplePesticide)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        samplePesticide.setDetectionCenterID(detectionCenterID);
        List<SamplePesticide> list = samplePesticideMapper.selectSamplePesticideList(samplePesticide);
        //食品类别ID和食品类别层级Map
        Map<Long,String> foodTypeIDStrMap = new HashMap<Long, String>() ;
        for(SamplePesticide samplePesticideVo: list){
            if(!foodTypeIDStrMap.containsKey(samplePesticideVo.getSampleID()))
            {
                String str = foodService.generateFoodTypeStr(samplePesticideVo.getSampleID());
                samplePesticideVo.setSampleTypeStr(str);
                foodTypeIDStrMap.put(samplePesticideVo.getSampleID(),str);
            }
            else
            {
                samplePesticideVo.setSampleTypeStr(foodTypeIDStrMap.get(samplePesticideVo.getSampleID()));
            }
        }
        return list;
    }

    /**
     * 新增样品-检测项目
     * 
     * @param samplePesticide 样品-检测项目
     * @return 结果
     */
    @Override
    public String insertSamplePesticide(SamplePesticide samplePesticide)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        samplePesticide.setDetectionCenterID(detectionCenterID);
        SamplePesticide isExist = samplePesticideMapper.selectSamplePesticideExist(samplePesticide);
        if(StringUtils.isNull(isExist))
        {
            int ret = samplePesticideMapper.insertSamplePesticide(samplePesticide);
            return ret > 0 ? "操作成功":"操作失败";
        }
        return "重复插入";
    }

    /**
     * 修改样品-检测项目
     * 
     * @param samplePesticide 样品-检测项目
     * @return 结果
     */
    @Override
    public int updateSamplePesticide(SamplePesticide samplePesticide)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        samplePesticide.setDetectionCenterID(detectionCenterID);
        return samplePesticideMapper.updateSamplePesticide(samplePesticide);
    }

    /**
     * 批量删除样品-检测项目
     * 
     * @param samplePesticideIDs 需要删除的样品-检测项目ID
     * @return 结果
     */
    @Override
    public int deleteSamplePesticideByIds(Long[] samplePesticideIDs)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        return samplePesticideMapper.deleteSamplePesticideByIds(samplePesticideIDs, detectionCenterID);
    }

    /**
     * 删除样品-检测项目信息
     * 
     * @param samplePesticideID 样品-检测项目ID
     * @return 结果
     */
    @Override
    public int deleteSamplePesticideById(Long samplePesticideID)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        return samplePesticideMapper.deleteSamplePesticideById(samplePesticideID, detectionCenterID);
    }
}
