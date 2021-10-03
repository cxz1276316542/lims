package com.ruoyi.limit.service.impl;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.limit.domain.*;
import com.ruoyi.limit.domain.vo.InstrumentMethodVo;
import com.ruoyi.limit.mapper.DetectionMethodStandardMapper;
import com.ruoyi.limit.mapper.InstrumentMethodMapper;
import com.ruoyi.limit.mapper.PesticideDetectionMethodMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.limit.mapper.DetectionMethodMapper;
import com.ruoyi.limit.service.IDetectionMethodService;

/**
 * 检测方法管理Service业务层处理
 *
 * @author dlk
 * @date 2021-07-20
 */
@Service
public class DetectionMethodServiceImpl implements IDetectionMethodService
{

    private static final Logger log = LoggerFactory.getLogger(DetectionMethodServiceImpl.class);

    @Autowired
    private DetectionMethodMapper detectionMethodMapper;

    @Autowired
    private InstrumentMethodMapper instrumentMethodMapper;

    @Autowired
    private DetectionMethodStandardMapper detectionMethodStandardMapper;

    @Autowired
    private PesticideDetectionMethodMapper pesticideDetectionMethodMapper;


    /**
     *
     * 查询检测方法管理
     *
     * @param detectionMethodID 检测方法管理ID
     * @return 检测方法管理
     */
    @Override
    public DetectionMethod selectDetectionMethodById(Long detectionMethodID)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        return detectionMethodMapper.selectDetectionMethodById(detectionMethodID,detectionCenterID);
    }

    /**
     * 查询检测方法管理列表
     *
     * @param detectionMethod 检测方法管理
     * @return 检测方法管理
     */
    @Override
    public List<DetectionMethod> selectDetectionMethodList(DetectionMethod detectionMethod)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        detectionMethod.setDetectionCenterID(user.getDetectionCenterID());
        return detectionMethodMapper.selectDetectionMethodList(detectionMethod);
    }

    /**
     * 数据库是否存在该数据
     * @param detectionMethod 检测方法管理
     * @return 数据库是否存在该数据
     *//*
    public int isExistDetectionMethod(DetectionMethod detectionMethod){
        SysUser user = SecurityUtils.getLoginUser().getUser();
        detectionMethod.setDetectionCenterID(user.getDetectionCenterID());
        System.out.println(detectionMethodMapper.isExistDetectionMethod(detectionMethod));
        return detectionMethodMapper.isExistDetectionMethod(detectionMethod);
    }*/

    /**
     * 新增检测方法管理
     *
     * @param detectionMethod 检测方法管理
     * @return 结果
     */
    @Override
    public String insertDetectionMethod(DetectionMethod detectionMethod)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        detectionMethod.setDetectionCenterID(user.getDetectionCenterID());
        int i = detectionMethodMapper.isExistDetectionMethod(detectionMethod);
        if(i==0){
            int ret = detectionMethodMapper.insertDetectionMethod(detectionMethod);
            return ret > 0 ? "操作成功":"操作失败";
        }
        return "请勿重复插入";
    }

    /**
     * 修改检测方法管理
     *
     * @param detectionMethod 检测方法管理
     * @return 结果
     */
    @Override
    public int updateDetectionMethod(DetectionMethod detectionMethod)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        detectionMethod.setDetectionCenterID(user.getDetectionCenterID());
        // 该检测方法对应的 检测仪器-检测方法设置为无效 检测方法-检测标准 检测项目-检测方法
        if(detectionMethod.getIsValid() == 0) {
            InstrumentMethodVo instrumentMethodVo = new InstrumentMethodVo();
            instrumentMethodVo.setDetectionMethodID(detectionMethod.getDetectionMethodID());
            instrumentMethodVo.setDetectionCenterID(user.getDetectionCenterID());
            instrumentMethodVo.setIsValid(0);
            instrumentMethodMapper.updateInstrumentMethod(instrumentMethodVo);

            DetectionMethodStandard detectionMethodStandard = new DetectionMethodStandard();
            detectionMethodStandard.setIsValid(0);
            detectionMethodStandard.setDetectionCenterID(user.getDetectionCenterID());
            detectionMethodStandard.setDetectionMethodID(detectionMethod.getDetectionMethodID());
            detectionMethodStandardMapper.updateDetectionMethodStandard(detectionMethodStandard);

            PesticideDetectionMethod pesticideDetectionMethod = new PesticideDetectionMethod();
            pesticideDetectionMethod.setIsValid(0);
            pesticideDetectionMethod.setDetectionCenterID(user.getDetectionCenterID());
            pesticideDetectionMethod.setDetectionMethodID(detectionMethod.getDetectionMethodID());
            pesticideDetectionMethodMapper.updatePesticideDetectionMethod(pesticideDetectionMethod);
        }
        return detectionMethodMapper.updateDetectionMethod(detectionMethod);
    }

    /**
     * 批量删除检测方法管理
     *
     * @param detectionMethodIDs 需要删除的检测方法管理ID
     * @return 结果
     */
    @Override
    public int deleteDetectionMethodByIds(Long[] detectionMethodIDs)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        //删除仪器-检测方法
        instrumentMethodMapper.deleteInstrumentMethodByDetectionMethodIds(detectionMethodIDs,detectionCenterID);
        //删除检测项目-检测方法
        pesticideDetectionMethodMapper.deletePesticideDetectionMethodByMethodIds(detectionMethodIDs,detectionCenterID);
        //删除检测方法-检测标准
        detectionMethodStandardMapper.deleteDetectionMethodStandardByMethodIds(detectionMethodIDs,detectionCenterID);
        return detectionMethodMapper.deleteDetectionMethodByIds(detectionMethodIDs,detectionCenterID);
    }

    /**
     * 删除检测方法管理信息
     *
     * @param detectionMethodID 检测方法管理ID
     * @return 结果
     */
    @Override
    public int deleteDetectionMethodById(Long detectionMethodID)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        return detectionMethodMapper.deleteDetectionMethodById(detectionMethodID,detectionCenterID);
    }

    /**
     * 导入检测方法信息
     * @param detectionMethodList 待导入的检测方法列表
     * @param isUpdateSupport 是否支持更新
     * @return 导入返回结果
     */
    @Override
    public String importDetectionMethod(List<DetectionMethod> detectionMethodList, Boolean isUpdateSupport){
        if(StringUtils.isNull(detectionMethodList) || detectionMethodList.size() == 0)
        {
            throw new CustomException("导入检测方法数据不能为空！");
        }
        int successNum = 0;
        int insertNum = 0;
        int updateNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();

        int row = 1;
        SysUser user = SecurityUtils.getLoginUser().getUser();
        for(DetectionMethod detectionMethod : detectionMethodList)
        {
            try
            {
                row++;
                //如果检测方法名称没有填写，给出提示
                if(StringUtils.isEmpty(detectionMethod.getDetectionMethodName())){
                    failureNum++;
                    failureMsg.append("<br/>" + "Excel表中第" + row + "行数据检测方法名称未填写，请先填写");
                    continue;
                }
                if(StringUtils.isNull(detectionMethod.getIsValid())){
                    failureNum++;
                    failureMsg.append("<br/>" + "Excel表中第" + row + "行数据检测方法有效性未填写，请先填写");
                    continue;
                }
                //验证检测方法信息是否存在数据库中
                DetectionMethod IsExist = detectionMethodMapper.selectDetectionMethodByName(detectionMethod.getDetectionMethodName(),user.getDetectionCenterID());

                    if (StringUtils.isNull(IsExist))
                {
                    successNum++;
                    insertNum++;
                    detectionMethod.setDetectionCenterID(user.getDetectionCenterID());
                    detectionMethodMapper.insertDetectionMethod(detectionMethod);
                }
                // 是否支持更新
                else if (isUpdateSupport)
                {
                    IsExist.setDetectionMethodInfo(detectionMethod.getDetectionMethodInfo());
                    IsExist.setDetectionMethodURL(detectionMethod.getDetectionMethodURL());
                    IsExist.setDetectionCenterID(user.getDetectionCenterID());
                    IsExist.setIsValid(detectionMethod.getIsValid());
                    detectionMethodMapper.updateDetectionMethod(IsExist);
                    updateNum++;
                    successNum++;
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + "Excel表中第" + row + "行数据已存在");
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
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条"+
                    "<br/>"+"  新增数据 " + insertNum + " 条"+
                    "<br/>"+ "  更新数据 "+ updateNum + " 条");
        }
        return successMsg.toString();
    }
}
