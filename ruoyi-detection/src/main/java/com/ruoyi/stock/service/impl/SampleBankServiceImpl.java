package com.ruoyi.stock.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import com.graphbuilder.curve.NURBSpline;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.detection.constant.DetectionCategoryEnums;
import com.ruoyi.detection.constant.SamplePriorityEnums;
import com.ruoyi.detection.constant.SampleStatusEnums;
import com.ruoyi.detection.domain.CommissionSample;
import com.ruoyi.detection.domain.MeasureUnit;
import com.ruoyi.detection.mapper.MeasureUnitMapper;
import com.ruoyi.limit.domain.Standard;
import com.ruoyi.limit.mapper.StandardMapper;
import com.ruoyi.stock.constant.StockConstants;
import com.ruoyi.stock.domain.EntryAndOut;
import com.ruoyi.stock.domain.Operator;
import com.ruoyi.stock.domain.vo.AbandonVO;
import com.ruoyi.stock.domain.vo.EntryAndOutVO;
import com.ruoyi.stock.domain.vo.SampleBankVO;
import com.ruoyi.detection.mapper.CommissionSampleMapper;
import com.ruoyi.stock.mapper.EntryAndOutMapper;
import com.ruoyi.stock.utils.PagingUtils;
import com.ruoyi.system.domain.SysUserRole;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.mapper.SysUserRoleMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.stock.mapper.SampleBankMapper;
import com.ruoyi.stock.domain.SampleBank;
import com.ruoyi.stock.service.ISampleBankService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 样品库Service业务层处理
 *
 * @author ruoyi
 * @date 2021-07-21
 */
@Service
public class SampleBankServiceImpl implements ISampleBankService
{
    @Autowired
    private SampleBankMapper sampleBankMapper;

    @Autowired
    private CommissionSampleMapper commissionSampleMapper;

    @Autowired
    private SysUserRoleMapper userRoleMapper;

    @Autowired
    private MeasureUnitMapper measureUnitMapper;

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private StandardMapper standardMapper;

    @Autowired
    private EntryAndOutMapper entryAndOutMapper;
    /**
     * 查询样品库
     * @param sampleBankID 样品库ID
     * @return 样品库
     */
    @Override
    public Map<String,Object> selectSampleBankById(Long sampleBankID)
    {
        Map<String,Object> map = new HashMap<>();
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        SampleBankVO sampleBankVO = sampleBankMapper.selectSampleBankVOById(sampleBankID);
//        if (sampleBankVO.getDeliverID() != null){
//            sampleBankVO.setDeliverName(userMapper.selectUserById(sampleBankVO.getDeliverID()).getNickName());
//        }

        CommissionSample commissionSample = commissionSampleMapper.selectCommissionSampleById(sampleBankVO.getSampleID());
        map.put("sampleNumber",commissionSample.getSampleNumber());
        map.put("sampleSenderName",commissionSample.getSampleSenderName());
        //获取检测标准
        Standard standard = standardMapper.selectStandardById(commissionSample.getDetectionStandardID(), detectionCenterID);
        map.put("detectionStandard",standard.getStandardName());

        map.put("sampleName",commissionSample.getSampleName());
        map.put("sampleScientificName",commissionSample.getSampleScientificName());
        if(SampleStatusEnums.codeOf(commissionSample.getSampleStatus()) != null){
            map.put("sampleStatus", SampleStatusEnums.codeOf(commissionSample.getSampleStatus()).getFiled());
        }
        if (SamplePriorityEnums.codeOf(commissionSample.getPriority()) != null){
            map.put("priority", SamplePriorityEnums.codeOf(commissionSample.getPriority()).getFiled());
        }

        map.put("sampleQuantity",commissionSample.getSampleQuantity());
        map.put("measureUnit",commissionSample.getMeasureUnit());
        map.put("sampleDescription",commissionSample.getSampleDescription());

        map.put("samplingMode",commissionSample.getSamplingMode()==1L?"抽检":"送检");
        map.put("samplingBase",commissionSample.getSamplingBase());

        if (DetectionCategoryEnums.codeOf(commissionSample.getDetectionCategory()) != null){
            map.put("detectionCategory", DetectionCategoryEnums.codeOf(commissionSample.getDetectionCategory()).getFiled());
        }

        map.put("productionUnit",commissionSample.getProductionUnit());
        map.put("productionUnitAddress",commissionSample.getProductionUnitAddress());
        if (commissionSample.getSamplingTime() != null){
            map.put("samplingTime",new SimpleDateFormat("yyyy-MM-dd HH:mm").format(commissionSample.getSamplingTime()));
        }
        map.put("sampleImage",commissionSample.getSampleImage());
        map.put("reportReceiveWay",commissionSample.getReportReceiveWay()==0L?"邮寄":"自取");
        map.put("recycle",commissionSample.getRecycle()==0L?"否":"是");

        return map;
    }

    /**
     * 查询样品库列表
     * @param sampleBankVO 样品库
     * @return 分页信息
     */
    @Override
    public TableDataInfo selectSampleBankList(SampleBankVO sampleBankVO)
    {

        PagingUtils.startPage();
        List<SampleBankVO> sampleBankVOS =  sampleBankMapper.selectSampleBankVOList(sampleBankVO);

        for (SampleBankVO sampleBankVO1:sampleBankVOS){

            Operator operator = entryAndOutMapper.selectDeliverByBankID(sampleBankVO1.getSampleBankID());
            if (operator != null){
                sampleBankVO1.setDeliverID(operator.getOperatorID());
                sampleBankVO1.setDeliverName(operator.getOperatorName());
            }

            //样品相关信息
            CommissionSample commissionSample = commissionSampleMapper.selectCommissionSampleById(sampleBankVO1.getSampleID());
            sampleBankVO1.setSampleNumber(commissionSample.getSampleNumber());
            sampleBankVO1.setSampleScientificName(commissionSample.getSampleScientificName());

            Map<String, Object> map = new HashMap<>();
            map.put("sampleNumber",commissionSample.getSampleNumber());
            map.put("priority",commissionSample.getPriority());
            map.put("samplingMode",commissionSample.getSamplingMode());
            map.put("detectionCategory",commissionSample.getDetectionCategory());
            map.put("sampleDescription",commissionSample.getSampleDescription());
            map.put("sampleScientificName",commissionSample.getSampleScientificName());
            map.put("sampleQuantity",commissionSample.getSampleQuantity());
            map.put("samplingBase",commissionSample.getSamplingBase());
            map.put("productionUnitAddress",commissionSample.getProductionUnitAddress());
            map.put("measureUnit",commissionSample.getMeasureUnit());
            map.put("sampleImage",commissionSample.getSampleImage());

            sampleBankVO1.setDetail(map);

            int warning;
            if ((sampleBankVO1.getQualityGuaranteePeriod().getTime()-new Date().getTime())/(1000*3600*24) >= StockConstants.WARNING_DAY){
                //保质期内
                warning = 0;
            }else if((sampleBankVO1.getQualityGuaranteePeriod().getTime()-new Date().getTime())/(1000*3600*24) <=0){
                //已过期
                warning = 2;
            }else {
                //三天内预警
                warning = 1;
            }
            sampleBankVO1.setWarning(warning);
        }

        return PagingUtils.getDataTable(sampleBankVOS);
    }

    /**
     * 新增样品库
     * @param sampleBankVO 样品库
     * @return 结果
     */
    @Override
    @Transactional
    public int insertSampleBank(SampleBankVO sampleBankVO) throws Exception {
        SampleBank sampleBank = new SampleBank();
        BeanUtils.copyProperties(sampleBankVO,sampleBank);

        //通过输入的编号查询样品ID
        List<CommissionSample> commissionSampleList = commissionSampleMapper.selectCommissionSampleBySampleNumber(sampleBankVO.getSampleNumber());
        if (commissionSampleList.size() == 0){
            throw (new Exception("编号有误"));
        }
        sampleBank.setSampleID(commissionSampleList.get(0).getSampleID());
        sampleBank.setStorageTime(new Date());
        sampleBank.setRemainQuantity(sampleBankVO.getTotalQuantity());
        sampleBank.setOperatorID(SecurityUtils.getLoginUser().getUser().getUserId());
        sampleBank.setStatus(StockConstants.BANK_STATUS_VALID);
        sampleBankMapper.insertSampleBank(sampleBank);
        Long sampleBankID = 0L;
     //   Thread.sleep(500);
        //获取刚入库的样品库ID
        Long[] ids = new Long[]{sampleBank.getSampleID()};
        List<SampleBank> sampleBanks = sampleBankMapper.selectSampleBankBySampleIds(ids);
        if (sampleBanks.size() > 0){
            sampleBankID = sampleBanks.get(0).getSampleBankID();
        }
        //插入入库信息
        EntryAndOut entryAndOut = new EntryAndOut();
        entryAndOut.setEntryAndOutCategory(StockConstants.CATEGORY_STOCK_IN);
        entryAndOut.setSampleID(sampleBank.getSampleID());
        entryAndOut.setSampleBankID(sampleBankID);
        entryAndOut.setSampleQuantity(sampleBank.getTotalQuantity());
        entryAndOut.setSampleQuantity(sampleBank.getTotalQuantity());
        entryAndOut.setEntryAndOutType(StockConstants.TYPE_REGISTER);
        entryAndOut.setReceiverID(sampleBankVO.getDeliverID());
        entryAndOut.setOperatorID(SecurityUtils.getLoginUser().getUser().getUserId());

        return entryAndOutMapper.insertEntryAndOut(entryAndOut);
    }

    /**
     * 修改样品库
     *
     * @param sampleBankVO 样品库
     * @return 结果
     */
    @Override
    public int updateSampleBank(SampleBankVO sampleBankVO)
    {
        SampleBank sampleBank = new SampleBank();
        BeanUtils.copyProperties(sampleBankVO,sampleBank);
        entryAndOutMapper.updateEntryAndOutByBankID(sampleBankVO.getSampleBankID(),sampleBankVO.getDeliverID());

        return sampleBankMapper.updateSampleBank(sampleBank);
    }

    /**
     * 批量删除样品库
     *
     * @param sampleBankIDs 需要删除的样品库ID
     * @return 结果
     */
    @Override
    public int deleteSampleBankByIds(Long[] sampleBankIDs)
    {
        return sampleBankMapper.deleteSampleBankByIds(sampleBankIDs);
    }

    /**
     * 删除样品库信息，退样
     * @param entryAndOutVO 样品库ID
     * @return 结果
     */
    @Override
    public int returnSampleBank(EntryAndOutVO entryAndOutVO)
    {
        EntryAndOut entryAndOut = new EntryAndOut();
        BeanUtils.copyProperties(entryAndOutVO,entryAndOut);
        SampleBank sampleBank = sampleBankMapper.selectSampleBankById(entryAndOutVO.getSampleBankID());

        entryAndOut.setEntryAndOutType(StockConstants.TYPE_RETURN);
        entryAndOut.setSampleID(sampleBank.getSampleID());
//        entryAndOut.setOperationTime(new Date());
        entryAndOut.setEntryAndOutCategory(StockConstants.TYPE_RETURN);
        entryAndOut.setSampleQuantity(sampleBank.getRemainQuantity());
        entryAndOut.setOperatorID(SecurityUtils.getLoginUser().getUser().getUserId());
        entryAndOut.setReceiverID(entryAndOutVO.getReceiverID());

        entryAndOutMapper.insertEntryAndOut(entryAndOut);
        return sampleBankMapper.deleteSampleBankById(entryAndOutVO.getSampleBankID());
    }

    /**
     * 库存管理员信息、可能送库/领用的人员信息
     * @return Map 管理员ID，管理员名称
     */
    @Override
    public List<Map<Long, String>> selectInventoryManager() {
        List<Map<Long, String>> list = new ArrayList<>();

        //查询库存管理员信息
        SysUserRole[] sysUsers = userRoleMapper.selectUserByRoleID(16L);
        Map<Long, String> managerList = new HashMap<>();
        for (SysUserRole sysUserRole :sysUsers ){
            managerList.put(sysUserRole.getUserId(),userMapper.selectUserById(sysUserRole.getUserId()).getNickName());
        }
        list.add(managerList);

        Map<Long, String> receiverList = new HashMap<>();

        //可能送库/领用的人员信息
        SysUser cus = new SysUser();
        //cus.set  用户类型为00
        List<SysUser> sysUsers1 = userMapper.selectUserList(cus);
        for (SysUser user:sysUsers1){
            receiverList.put(user.getUserId(),user.getNickName());
        }

        list.add(receiverList);
        return list;
    }

    /**
     * 批量废弃样品库信息
     * @param sampleBankIDs 样品库IDs
     * @return 结果
     */
    @Override
    public int abandonSampleBankByIds(Long[] sampleBankIDs) {
        for (Long sampleBankID:sampleBankIDs){
            SampleBank sampleBank = sampleBankMapper.selectSampleBankById(sampleBankID);
            //出库记录
            EntryAndOut entryAndOut = new EntryAndOut();
            entryAndOut.setSampleBankID(sampleBankID);
            entryAndOut.setEntryAndOutType(StockConstants.TYPE_DESTROY);
            entryAndOut.setSampleID(sampleBank.getSampleID());
            entryAndOut.setOperationTime(new Date());
            entryAndOut.setEntryAndOutCategory(StockConstants.CATEGORY_DELIVERY);
            entryAndOut.setSampleQuantity(sampleBank.getRemainQuantity());
            entryAndOut.setOperatorID(SecurityUtils.getLoginUser().getUser().getUserId());
            entryAndOut.setReceiverID(SecurityUtils.getLoginUser().getUser().getUserId());

            entryAndOutMapper.insertEntryAndOut(entryAndOut);
        }
        //样品库废弃
        return sampleBankMapper.updateSampleBankAbandonByIDs(sampleBankIDs);
    }

    /**
     * 废弃样品库信息
     * @param entryAndOutVO
     * @return 结果
     */
    @Override
    public int abandonSampleBank(EntryAndOutVO entryAndOutVO) {
        EntryAndOut entryAndOut = new EntryAndOut();
        BeanUtils.copyProperties(entryAndOutVO,entryAndOut);

        SampleBank sampleBank = sampleBankMapper.selectSampleBankById(entryAndOut.getSampleBankID());

        entryAndOut.setEntryAndOutType(StockConstants.TYPE_DESTROY);
        entryAndOut.setSampleID(sampleBank.getSampleID());
//        entryAndOut.setOperationTime(new Date());
        entryAndOut.setEntryAndOutCategory(StockConstants.CATEGORY_DELIVERY);
        entryAndOut.setSampleQuantity(sampleBank.getRemainQuantity());
        entryAndOut.setOperatorID(SecurityUtils.getLoginUser().getUser().getUserId());
        entryAndOut.setReceiverID(SecurityUtils.getLoginUser().getUser().getUserId());

        entryAndOutMapper.insertEntryAndOut(entryAndOut);

        //样品库废弃
        Long[] sampleIDs = new Long[1];
        sampleIDs[0] = entryAndOut.getSampleBankID();
        return sampleBankMapper.updateSampleBankAbandonByIDs(sampleIDs);
    }

    /**
     * 回收样品库信息
     * @param entryAndOutVO  出入库VO
     * @return 结果
     */
    @Override
    public int recycleSampleBank(EntryAndOutVO entryAndOutVO) {
        EntryAndOut entryAndOut = new EntryAndOut();
        BeanUtils.copyProperties(entryAndOutVO,entryAndOut);
        entryAndOut.setOperatorID(SecurityUtils.getLoginUser().getUser().getUserId());

        entryAndOut.setEntryAndOutType(StockConstants.TYPE_RECYCLE);
        entryAndOut.setEntryAndOutCategory(StockConstants.CATEGORY_STOCK_IN);
        entryAndOutMapper.insertEntryAndOut(entryAndOut);

        SampleBank sampleBank = sampleBankMapper.selectSampleBankById(entryAndOut.getSampleBankID());
        //新的剩余质量
        BigDecimal newRemainQuantity = sampleBank.getRemainQuantity().add(entryAndOutVO.getSampleQuantity());
        sampleBank.setRemainQuantity(newRemainQuantity);
        //更新
        return sampleBankMapper.updateSampleBank(sampleBank);
    }


    /**
     * 查询销毁样品库列表
     * @param abandonVO 样品库
     * @return 样品库集合
     */
    @Override
    public TableDataInfo selectAbandonList(AbandonVO abandonVO) {
        PagingUtils.startPage();
        List<AbandonVO> abandonVOList = sampleBankMapper.selectAbandonVOList(abandonVO);
        for (AbandonVO abandonVO1:abandonVOList){
            if (abandonVO1.getOperatorID() != null){
                abandonVO1.setOperatorName(userMapper.selectUserById(abandonVO1.getOperatorID()).getNickName());
            }
        }
        return PagingUtils.getDataTable(abandonVOList);
    }

    /**
     * 获取计量单位
     */
    @Override
    public List<String> getMeasureUnit() {
        List<String> res = new ArrayList<>();
        List<MeasureUnit> measureUnits = measureUnitMapper.selectMeasureUnitList(new MeasureUnit());
        for (MeasureUnit measureUnit:measureUnits){
            res.add(measureUnit.getMeasureUnitName());
        }
        return res;
    }

    /**
     * 领用样品库信息
     * @param entryAndOutVO  出入库VO
     * @return 结果
     */
    @Override
    public int useSampleBank(EntryAndOutVO entryAndOutVO) throws Exception {
        EntryAndOut entryAndOut = new EntryAndOut();
        BeanUtils.copyProperties(entryAndOutVO,entryAndOut);
        entryAndOut.setOperatorID(SecurityUtils.getLoginUser().getUser().getUserId());

        entryAndOut.setEntryAndOutType(StockConstants.TYPE_CLAIM);
        entryAndOut.setEntryAndOutCategory(StockConstants.CATEGORY_DELIVERY);

        SampleBank sampleBank = sampleBankMapper.selectSampleBankById(entryAndOut.getSampleBankID());
        //新的剩余质量
        BigDecimal newRemainQuantity = sampleBank.getRemainQuantity().subtract(entryAndOutVO.getSampleQuantity());
        if (newRemainQuantity.compareTo(new BigDecimal(0)) < 0){
            throw new Exception("剩余检品不足！");
        }
        sampleBank.setRemainQuantity(newRemainQuantity);

        entryAndOutMapper.insertEntryAndOut(entryAndOut);
        //更新
        return sampleBankMapper.updateSampleBank(sampleBank);
    }
}
