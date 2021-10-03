package com.ruoyi.limit.service.impl;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.limit.domain.DetectionMethod;
import com.ruoyi.limit.domain.vo.InstrumentMethodVo;
import com.ruoyi.limit.mapper.DetectionMethodMapper;
import com.ruoyi.limit.mapper.InstrumentMethodMapper;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.service.ISysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.limit.mapper.InstrumentMapper;
import com.ruoyi.limit.domain.Instrument;
import com.ruoyi.limit.service.IInstrumentService;

/**
 * 检测仪器管理Service业务层处理
 *
 * @author dlk
 * @date 2021-07-20
 */
@Service
public class InstrumentServiceImpl implements IInstrumentService
{
    private static final Logger log = LoggerFactory.getLogger(InstrumentServiceImpl.class);

    @Autowired
    private InstrumentMapper instrumentMapper;

    @Autowired
    private DetectionMethodMapper detectionMethodMapper;

    @Autowired
    private InstrumentMethodMapper instrumentMethodMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 查询检测仪器管理
     *
     * @param instrumentID 检测仪器管理ID
     * @return 检测仪器管理
     */
    @Override
    public Instrument selectInstrumentById(Long instrumentID)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        return instrumentMapper.selectInstrumentById(instrumentID,detectionCenterID);
    }

    /**
     * 查询检测仪器管理列表
     *
     * @param instrument 检测仪器管理
     * @return 检测仪器管理
     */
    @Override
    public List<Instrument> selectInstrumentList(Instrument instrument)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        instrument.setDetectionCenterID(detectionCenterID);
        return instrumentMapper.selectInstrumentList(instrument);
    }

    /**
     * 新增检测仪器管理
     *
     * @param instrument 检测仪器管理
     * @return 结果
     */
    @Override
    public int insertInstrument(Instrument instrument)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        instrument.setDetectionCenterID(detectionCenterID);
        instrument.setInstrumentNumber(createInstrumentNumber());
        return instrumentMapper.insertInstrument(instrument);
    }

    /**
     * 修改检测仪器管理
     *
     * @param instrument 检测仪器管理
     * @return 结果
     */
    @Override
    public int updateInstrument(Instrument instrument)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        instrument.setDetectionCenterID(detectionCenterID);
        // 该检测仪器对应的 检测仪器-检测方法设置为无效
        if(instrument.getIsValid() == 0){
            InstrumentMethodVo instrumentMethodVo = new InstrumentMethodVo();
            instrumentMethodVo.setInstrumentID(instrument.getInstrumentID());
            instrumentMethodVo.setIsValid(0);
            instrumentMethodVo.setDetectionCenterID(detectionCenterID);
            instrumentMethodMapper.updateInstrumentMethod(instrumentMethodVo);
        }
        return instrumentMapper.updateInstrument(instrument);
    }

    /**
     * 批量删除检测仪器管理
     *
     * @param instrumentIDs 需要删除的检测仪器管理ID
     * @return 结果
     */
    @Override
    public int deleteInstrumentByIds(Long[] instrumentIDs)
    {
        // 删除绑定的检测仪器-检测方法数据
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        instrumentMethodMapper.deleteInstrumentMethodByInstrumentIds(instrumentIDs,detectionCenterID);
        return instrumentMapper.deleteInstrumentByIds(instrumentIDs,detectionCenterID);
    }


    /**
     * 根据检测仪器查询检测仪器-检测方法集合
     * @param instrument 检测仪器管理
     * @return 检测仪器-检测方法集合
     */
    public List<InstrumentMethodVo> selectInstrumentDetectionMethodList(Instrument instrument){
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        instrument.setDetectionCenterID(detectionCenterID);
        return instrumentMapper.selectInstrumentDetectionMethodList(instrument);
    }

    /**
     * 导入检测仪器信息
     * @param instrumentList 待导入的检测仪器列表
     * @param isUpdateSupport 是否支持更新  暂时只考虑插入不考虑更新
     * @return 导入返回结果
     */
    @Override
    public String importInstrument(List<Instrument> instrumentList, Boolean isUpdateSupport){
        if(StringUtils.isNull(instrumentList) || instrumentList.size() == 0)
        {
            throw new CustomException("导入检测仪器数据不能为空！");
        }
        int successNum = 0;
        int insertNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        int row = 1;
        SysUser user = SecurityUtils.getLoginUser().getUser();
        //先对数据格式进行检验，检验成功后再进行插入
        for(Instrument instrument : instrumentList)
        {
            try
            {
                row++;
                String instrumentName = instrument.getInstrumentName();
                Integer status = instrument.getStatus();
                String purchaserName = instrument.getPurchaserName();
                Integer isValid = instrument.getIsValid();
                if(StringUtils.isEmpty(purchaserName)){
                    failureNum++;
                    failureMsg.append("<br/>" + "Excel表中第" + row + "行数据采购人名字未填写，请先填写");
                    continue;
                }
                if(StringUtils.isEmpty(instrumentName)){
                    failureNum++;
                    failureMsg.append("<br/>" + "Excel表中第" + row + "行数据检测仪器名称未填写，请先填写");
                    continue;
                }
                if(StringUtils.isNull(status)){
                    failureNum++;
                    failureMsg.append("<br/>" + "Excel表中第" + row + "行数据仪器状态未填写，请先填写");
                    continue;
                }
                if(StringUtils.isNull(isValid)){
                    failureNum++;
                    failureMsg.append("<br/>" + "Excel表中第" + row + "行数据检测方法有效性未填写，请先填写");
                    continue;
                }
                // 判断用户是否存在
                SysUser sysUser = sysUserMapper.selectUserByNickName(purchaserName);
                if(StringUtils.isNull(sysUser)) {
                    failureNum++;
                    failureMsg.append("<br/>" + "Excel表中第" + row + "行数据用户名未找到，请重新填写");
                    continue;
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
            for(Instrument instrument : instrumentList)
            {
                //自动增加编号
                instrument.setInstrumentNumber(createInstrumentNumber());
                instrument.setDetectionCenterID(user.getDetectionCenterID());
                SysUser sysUser = sysUserMapper.selectUserByNickName(instrument.getPurchaserName());
                instrument.setPurchaserID(sysUser.getUserId());
                instrumentMapper.insertInstrument(instrument);
                successNum++;
                insertNum++;
            }
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条"+
                    "<br/>"+"  新增数据 " + insertNum + " 条");
        }
        return successMsg.toString();
    }


    /**
     * 自定义创建仪器编号
     * @return 仪器编号
     */
    public String createInstrumentNumber(){
        //YPDJ2021052400001
        //JCYQ_00001
        String lastInstrumentNumber = instrumentMapper.findLastInstrumentNumber();

        //String lastSampleRegistrationID = sampleRegistrationMapper.findLastSampleRegistrationNumber();
        //String date = DateUtils.getDate().replace("-","");
        String result = "";
        if(lastInstrumentNumber == null){
            result = "JCYQ_00001";
        }else{
            int length = lastInstrumentNumber.length();
            //oldNumber是截取最后的数字编号并进行提取真实数字
            Integer oldNumber = Integer.parseInt(lastInstrumentNumber.substring(length-5,length));
            String newNumber = String.valueOf(oldNumber+1);
            StringBuffer zeros = new StringBuffer();
            for(int i = 0; i < 5 - newNumber.length(); i++){
                zeros.append("0");
            }
            result = "JCYQ_" + zeros.toString() + newNumber;
        }
        return result;
    }
}
