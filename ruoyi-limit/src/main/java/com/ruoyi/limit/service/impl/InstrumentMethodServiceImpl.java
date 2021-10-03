package com.ruoyi.limit.service.impl;

import java.util.List;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.limit.domain.DetectionMethod;
import com.ruoyi.limit.domain.Instrument;
import com.ruoyi.limit.mapper.DetectionMethodMapper;
import com.ruoyi.limit.mapper.InstrumentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.limit.mapper.InstrumentMethodMapper;
import com.ruoyi.limit.domain.vo.InstrumentMethodVo;
import com.ruoyi.limit.service.IInstrumentMethodVoService;

/**
 * 检测方法-检测仪器关系Service业务层处理
 *
 * @author dlk
 * @date 2021-08-07
 */
@Service
public class InstrumentMethodServiceImpl implements IInstrumentMethodVoService
{
    private static final Logger log = LoggerFactory.getLogger(InstrumentMethodServiceImpl.class);
    @Autowired
    private InstrumentMethodMapper instrumentMethodMapper;

    @Autowired
    private InstrumentMapper instrumentMapper;

    @Autowired
    private DetectionMethodMapper detectionMethodMapper;

    /**
     * 查询检测方法-检测仪器关系
     *
     * @param instrumentDetectionMethodID 检测方法-检测仪器ID
     * @return 检测方法-检测仪器关系
     */
    @Override
    public InstrumentMethodVo selectInstrumentMethodById(Long instrumentDetectionMethodID)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        return instrumentMethodMapper.selectInstrumentMethodById(instrumentDetectionMethodID,detectionCenterID);
    }

    /**
     * 查询检测方法-检测仪器关系列表
     *
     * @param instrumentMethodVo 检测方法-检测仪器关系
     * @return 检测方法-检测仪器关系
     */
    @Override
    public List<InstrumentMethodVo> selectInstrumentMethodList(InstrumentMethodVo instrumentMethodVo)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        instrumentMethodVo.setDetectionCenterID(detectionCenterID);
        return instrumentMethodMapper.selectInstrumentMethodList(instrumentMethodVo);
    }

    /**
     * 新增检测方法-检测仪器关系
     *
     * @param instrumentMethodVo 检测方法-检测仪器关系
     * @return 结果
     */
    @Override
    public int insertInstrumentMethod(InstrumentMethodVo instrumentMethodVo)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        instrumentMethodVo.setDetectionCenterID(detectionCenterID);
        return instrumentMethodMapper.insertInstrumentMethod(instrumentMethodVo);
    }

    /**
     * 修改检测方法-检测仪器关系
     *
     * @param instrumentMethodVo 检测方法-检测仪器关系
     * @return 结果
     */
    @Override
    public int updateInstrumentMethod(InstrumentMethodVo instrumentMethodVo)
    {
        return instrumentMethodMapper.updateInstrumentMethod(instrumentMethodVo);
    }

    /**
     * 批量删除检测方法-检测仪器关系
     *
     * @param instrumentDetectionMethodIDs 需要删除的检测方法-检测仪器关系ID
     * @return 结果
     */
    @Override
    public int deleteInstrumentMethodByIds(Long[] instrumentDetectionMethodIDs)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        return instrumentMethodMapper.deleteInstrumentMethodByIds(instrumentDetectionMethodIDs,detectionCenterID);
    }

    /**
     * 删除检测方法-检测仪器关系信息
     *
     * @param instrumentMethodVo 检测方法-检测仪器关系ID
     * @return 结果
     *//*
    @Override
    public int deleteInstrumentMethodById(InstrumentMethodVo instrumentMethodVo)
    {
        return instrumentMethodMapper.deleteInstrumentMethodById(instrumentMethodVo);
    }*/

    /**
     * 查找所有的检测方法名称
     * @return List<String>
     */
    public List<String> findAllDetectionMethodName(){
        return instrumentMethodMapper.findAllDetectionMethodName();
    }

    /**
     * 判断数据库中是否已存在该数据
     * @param instrumentMethodVo 待导入的检测仪器-检测方法列表
     * @return 是否存在
     */
    @Override
    public int isExist(InstrumentMethodVo instrumentMethodVo){
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        instrumentMethodVo.setDetectionCenterID(detectionCenterID);
        InstrumentMethodVo method = instrumentMethodMapper.selectInstrumentDetectionMethod(instrumentMethodVo);
        if(StringUtils.isNull(method)){
            return 0;
        }
        return 1;
    }

    /**
     * 导入检测仪器-检测方法信息
     * @param instrumentMethodVoList 待导入的检测仪器-检测方法列表
     * @param isUpdateSupport 是否支持更新
     * @return 导入返回结果
     */
    @Override
    public String importInstrumentMethod(List<InstrumentMethodVo> instrumentMethodVoList, Boolean isUpdateSupport){
        if(StringUtils.isNull(instrumentMethodVoList) || instrumentMethodVoList.size() == 0)
        {
            throw new CustomException("导入检测仪器-检测方法数据不能为空！");
        }
        int successNum = 0;
        int insertNum = 0;
        int updateNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();

        int row = 1;

        SysUser user = SecurityUtils.getLoginUser().getUser();
        for(InstrumentMethodVo instrumentMethodVo : instrumentMethodVoList)
        {
            try
            {
                row++;
                //验证Excel表格中是否已填写好完整信息
                String instrumentNumber = instrumentMethodVo.getInstrumentNumber();
                String name = instrumentMethodVo.getDetectionMethodName();
                if(StringUtils.isEmpty(name)){
                    failureNum++;
                    failureMsg.append("<br/>" + "Excel表中第" + row + "行数据检测方法未填写，请先填写");
                    continue;
                }
                if(StringUtils.isEmpty(instrumentNumber)){
                    failureNum++;
                    failureMsg.append("<br/>" + "Excel表中第" + row + "行数据检测仪器编号未填写，请先填写");
                    continue;
                }
                //验证数据库中检测仪器检测方法信息是否存在
                Instrument isExist = instrumentMapper.selectInstrumentByInstrumentNumber(instrumentMethodVo.getInstrumentNumber(),user.getDetectionCenterID());
                DetectionMethod isExistMethod = detectionMethodMapper.selectDetectionMethodByName(instrumentMethodVo.getDetectionMethodName(),user.getDetectionCenterID());
                if (StringUtils.isNull(isExist))
                {
                    failureNum++;
                    failureMsg.append("<br/>" + "Excel表中第" + row + "行数据检测仪器不存在，请先添加");
                }
                else if (StringUtils.isNull(isExistMethod))
                {
                    failureNum++;
                    failureMsg.append("<br/>" + "Excel表中第" + row + "行数据检测方法不存在，请先添加");
                }
                else
                {
                    //判断数据库中是否已存在该数据，存在就跳过此次循环，不存在就插入
                    instrumentMethodVo.setDetectionMethodID(isExistMethod.getDetectionMethodID());
                    instrumentMethodVo.setInstrumentID(isExist.getInstrumentID());
                    int exist = isExist(instrumentMethodVo);
                    if(exist>0){
                        updateNum++;
                        successNum++;
                        //successMsg.append("<br/>" + "Excel表中第" + row + "行数据已存在");
                        continue;
                    }
                    instrumentMethodMapper.insertInstrumentMethod(instrumentMethodVo);
                    insertNum++;
                    successNum++;
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
                    "<br/>"+ "  已存在数据 "+ updateNum + " 条"
            );
        }
        return successMsg.toString();
    }

}
