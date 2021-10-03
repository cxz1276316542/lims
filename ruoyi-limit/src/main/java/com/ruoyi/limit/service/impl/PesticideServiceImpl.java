package com.ruoyi.limit.service.impl;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.limit.domain.PesticideDetectionMethod;
import com.ruoyi.limit.domain.SamplePesticide;
import com.ruoyi.limit.mapper.PesticideDetectionMethodMapper;
import com.ruoyi.limit.mapper.SamplePesticideMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.limit.mapper.PesticideMapper;
import com.ruoyi.limit.domain.Pesticide;
import com.ruoyi.limit.service.IPesticideService;

/**
 * 检测农药管理Service业务层处理
 * 
 * @author wlf
 * @date 2021-07-20
 */
@Service
public class PesticideServiceImpl implements IPesticideService
{
    private static final Logger log = LoggerFactory.getLogger(PesticideServiceImpl.class);
    @Autowired
    private PesticideMapper pesticideMapper;

    @Autowired
    SamplePesticideMapper samplePesticideMapper;

    @Autowired
    PesticideDetectionMethodMapper pesticideDetectionMethodMapper;


    /**
     *查询豁免清单
     *
     * @param pesticide 检测农药信息
     * @return 豁免清单数据
     */
    @Override
    public List<Pesticide> selectExemptionList(Pesticide pesticide) {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        pesticide.setDetectionCenterID(detectionCenterID);
        return pesticideMapper.selectExemptionList(pesticide);
    }

    /**
     * 查询检测农药管理
     * 
     * @param pesticideID 检测农药管理ID
     * @return 检测农药管理
     */
    @Override
    public Pesticide selectPesticideById(Long pesticideID)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        return pesticideMapper.selectPesticideById(pesticideID,detectionCenterID);
    }

    /**
     * 查询检测农药管理列表
     * 
     * @param pesticide 检测农药管理
     * @return 检测农药管理
     */
    @Override
    public List<Pesticide> selectPesticideList(Pesticide pesticide)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        pesticide.setDetectionCenterID(detectionCenterID);
        return pesticideMapper.selectPesticideList(pesticide);
    }

    /**
     * 新增检测农药管理
     * 
     * @param pesticide 检测农药管理
     * @return 结果
     */
    @Override
    public String insertPesticide(Pesticide pesticide)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        pesticide.setDetectionCenterID(detectionCenterID);
        Pesticide isExist = pesticideMapper.selectPesticideExist(pesticide);
        if(StringUtils.isNull(isExist))
        {
            int ret = pesticideMapper.insertPesticide(pesticide);
            return ret > 0 ? "操作成功":"操作失败";
        }
        return "重复插入";
    }

    /**
     * 修改检测农药管理
     * 
     * @param pesticide 检测农药管理
     * @return 结果
     */
    @Override
    public int updatePesticide(Pesticide pesticide)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        pesticide.setDetectionCenterID(detectionCenterID);
        if(pesticide.getIsValid() == 0)
        {
            //该检测项目对应的 样品-检测项目、 检测项目-检测方法字段无效
            SamplePesticide samplePesticide = new SamplePesticide();
            samplePesticide.setPesticideID(pesticide.getPesticideID());
            samplePesticide.setIsValid(0);
            samplePesticide.setDetectionCenterID(detectionCenterID);
            samplePesticideMapper.updateSamplePesticide(samplePesticide);

            PesticideDetectionMethod pesticideDetectionMethod = new PesticideDetectionMethod();
            pesticideDetectionMethod.setPesticideID(pesticide.getPesticideID());
            pesticideDetectionMethod.setIsValid(0);
            pesticideDetectionMethod.setDetectionCenterID(detectionCenterID);
            pesticideDetectionMethodMapper.updatePesticideDetectionMethod(pesticideDetectionMethod);
        }
        return pesticideMapper.updatePesticide(pesticide);
    }

    /**
     * 批量删除检测农药管理
     * 
     * @param pesticideIDs 需要删除的检测农药管理ID
     * @return 结果
     */
    @Override
    public int deletePesticideByIds(Long[] pesticideIDs)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        //删除样品-检测项目
        samplePesticideMapper.deleteSamplePesticideByPesticideIds(pesticideIDs, detectionCenterID);
        //删除项目-检测方法
        pesticideDetectionMethodMapper.deletePesticideDetectionMethodByPesticideIds(pesticideIDs, detectionCenterID);
        //删除检测项目
        return pesticideMapper.deletePesticideByIds(pesticideIDs,detectionCenterID);
    }

    /**
     * 删除检测农药管理信息
     * 
     * @param pesticideID 检测农药管理ID
     * @return 结果
     */
    @Override
    public int deletePesticideById(Long pesticideID)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        return pesticideMapper.deletePesticideById(pesticideID,detectionCenterID);
    }

    /**
     * 导入检测农药信息
     * @param pesticideList 待导入的农药列表
     * @param isUpdateSupport 是否支持更新
     * @return 导入返回结果
     */
    public String importPesticide(List<Pesticide> pesticideList, Boolean isUpdateSupport){
        if(StringUtils.isNull(pesticideList) || pesticideList.size() == 0)
        {
            throw new CustomException("导入检测农药数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();

        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        int row = 1;
        for(Pesticide pesticide : pesticideList)
        {
            try
            {
                row++;
                //验证检测农药信息是否存在
                Pesticide IsExist = pesticideMapper.selectPesticideByName(pesticide.getPesticideName(),detectionCenterID);
                if (StringUtils.isNull(IsExist))
                {
                    successNum++;
                    pesticide.setIsValid(1);
                    pesticide.setDetectionCenterID(detectionCenterID);
                    pesticideMapper.insertPesticide(pesticide);
                }
                else if (isUpdateSupport)
                {
                    pesticide.setDetectionCenterID(detectionCenterID);
                    pesticideMapper.updatePesticide(pesticide);
                    successNum++;
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + "Excel表中第" + row + "行数据已存在。如需更新，请点击更新按钮！");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + "Excel表中第" + row + "行数据出错";
                failureMsg.append(msg + e.getMessage());
                log.error(msg,e);
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new CustomException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条");
        }
        return successMsg.toString();
    }

}
