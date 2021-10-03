package com.ruoyi.commission.service.impl;

import com.ruoyi.commission.domain.Commission;
import com.ruoyi.commission.domain.dto.CommissionDTO;
import com.ruoyi.commission.domain.dto.EntrustList;
import com.ruoyi.commission.domain.vo.CustomerDataVO;
import com.ruoyi.commission.domain.vo.DpesticideVO;
import com.ruoyi.commission.domain.vo.StandardVO;
import com.ruoyi.commission.domain.vo.UnitVO;
import com.ruoyi.commission.mapper.CommissionMapper;
import com.ruoyi.commission.mapper.ProgressMapper;
import com.ruoyi.commission.service.ICommissionService;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.detection.domain.CommissionSample;
import com.ruoyi.detection.mapper.CommissionSampleMapper;
import com.ruoyi.system.service.INumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 委托样品Service业务层处理
 * 
 * @author lkun
 * @date 2021-07-23
 */
@Service
public class CommissionServiceImpl implements ICommissionService 
{
    @Autowired
    private CommissionMapper commissionMapper;

    @Autowired
    private ProgressMapper progressMapper;

    @Autowired
    private RedisCache redisCache;

//    @Autowired
//    private CommissionSampleMapper commissionSampleMapper;

    @Autowired
    private INumberService numberService;

    /**
     * 查询委托样品
     * 
     * @param sampleID 委托样品ID
     * @return 委托样品
     */
    @Override
    public Commission selectCommissionById(Long sampleID)
    {
        return commissionMapper.selectCommissionById(sampleID);
    }

    /**
     * 查询委托样品列表
     * 
     * @param commission 委托样品
     * @return 委托样品
     */
    @Override
    public List<Commission> selectCommissionList(Commission commission)
    {
        return commissionMapper.selectCommissionList(commission);
    }

    /**
     * 新增委托样品
     * 
     * @param commission 委托样品
     * @return 结果
     */
    @Override
    public int insertCommission(Commission commission)
    {
        return commissionMapper.insertCommission(commission);
    }

    /**
     * 修改委托样品
     * 
     * @param commission 委托样品
     * @return 结果
     */
    @Override
    public int updateCommission(Commission commission)
    {
        return commissionMapper.updateCommission(commission);
    }

    /**
     * 批量删除委托样品
     * 
     * @param sampleIDs 需要删除的委托样品ID
     * @return 结果
     */
    @Override
    public int deleteCommissionByIds(Long[] sampleIDs)
    {
        return commissionMapper.deleteCommissionByIds(sampleIDs);
    }

    /**
     * 删除委托样品信息
     * 
     * @param sampleID 委托样品ID
     * @return 结果
     */
    @Override
    public int deleteCommissionById(Long sampleID)
    {
        return commissionMapper.deleteCommissionById(sampleID);
    }

    @Override
    public List<StandardVO> getDetectionStandard() {
        List<StandardVO> standardVOS = commissionMapper.selectDetectionStandard();
//        StandardVO standardVO = new StandardVO(,"不详");
//        standardVOS.add(standardVO);
        return standardVOS;
    }

    @Override
    public List<UnitVO> getUnits() {
        return commissionMapper.selectUnit();
    }

    @Override
    public boolean submit(CommissionDTO commissionDTO) {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        boolean result = true;
        // notice
        int index = 0;
        List<String> sampleNumbers = numberService.generateSampleNumber(commissionDTO.getEntrustList().size());
        String commissionNumber = numberService.generateCommissionNumber();
        // notice
        for(EntrustList p : commissionDTO.getEntrustList()){
            // 待插入 客户ID，单位ID，样品编号,收获地址，发送者名称，电话号,操作者ID，
            Commission commission = new Commission(p);
            commission.setCustomerID(user.getUserId());
            commission.setCompanyID(commissionMapper.getCompanyID(user.getUserId()));
            // notice
            commission.setSampleNumber(sampleNumbers.get(index++));
            commission.setCommissionNumber(commissionNumber);
            // notice
            commission.setReceiptAddress(commissionDTO.getReceiptAddress());
            commission.setSampleSenderName(commissionDTO.getSampleSenderName());
            commission.setSampleSenderPhone(commissionDTO.getSampleSenderPhone());
            commission.setOperatorID(user.getUserId());
            // 设置状态
            commission.setReviewStatus(1L);
            result &= (commissionMapper.insertCommission(commission) == 1);
            progressMapper.inCommission(commission.getSampleID());
        }

        // 向缓存中存一份
        redisCache.setCacheObject("commissionInput:"+user.getUserName()+":"+user.getUserId(),commissionDTO);
        return result;
    }

    /**
     * 根据学名获取用户最近输入的历史数据
     * 目前只获取了已经提交的，后面会修改成获取审核通过过的数据
     * @param user
     * @param sampleName
     * @return
     */
    @Override
    public CommissionDTO getHistory(SysUser user, String sampleName) {
        List<Commission> commissions = commissionMapper.selectCommissionBySample(user.getUserId(),sampleName);
        CommissionDTO dto = new CommissionDTO();
        if(commissions.size() != 0){
            dto = new CommissionDTO(commissions.get(0));
        }
        return dto;
    }

    @Override
    public CustomerDataVO getCustomerData() {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        CustomerDataVO customerDataVO = new CustomerDataVO();
        customerDataVO.setUsername(loginUser.getUsername());
        customerDataVO.setCompanyName(commissionMapper.selectCompanyByUser(loginUser.getUser().getUserId()));
        return customerDataVO;
    }

    @Override
    public String getOrderId() {
        return numberService.generateCommissionNumber();
    }

    @Override
    public List<DpesticideVO> getDpesticide(String sampleName) {
        Long userId = SecurityUtils.getLoginUser().getUser().getUserId();
        List<DpesticideVO> res = commissionMapper.selectDpesticide(userId, sampleName);
        return res;
    }



//    /**
//     * WTYP2021080100001
//     * @return
//     */
//    private String createCommissionNumber(){
//        String lastRecordNumber = commissionMapper.findLastRecord();
//        String curDate = DateUtils.getDate().replace("-","");
//        int index = 1;
//        if(null == lastRecordNumber || !curDate.equals(lastRecordNumber.substring(4,4+curDate.length()))){
//            index = 1;
//        }else{
//            // 取出最后5位数
//            Integer lastNumber = Integer.valueOf(lastRecordNumber.substring(lastRecordNumber.length() - 5, lastRecordNumber.length()));
//            index = lastNumber + 1;
//        }
//        StringBuffer number = new StringBuffer();
//        String newNumber = String.valueOf(index);
//        StringBuffer zeros = new StringBuffer();
//        for(int j = 0; j < 5 - newNumber.length(); j++){
//            zeros.append("0");
//        }
//        number.append("WTYP" + curDate + zeros.toString() + newNumber);
//        return number.toString();
//    }
//
//    /**
//     * 生成检品编号
//     * JP2021080100001
//     */
//    private List<String> createSampleNumber(int size){
//        String lastRecordNumber = commissionSampleMapper.findLastRecordNumber();
//        String curDate = DateUtils.getDate().replace("-","");
//        int index = 1;
//        if(null == lastRecordNumber || !curDate.equals(lastRecordNumber.substring(2,2+curDate.length()))){
//            index = 1;
//        }else{
//            // 取出最后5位数
//            Integer lastNumber = Integer.valueOf(lastRecordNumber.substring(lastRecordNumber.length() - 5, lastRecordNumber.length()));
//            index = lastNumber + 1;
//        }
//        List<String> res = new ArrayList<>();
//        for(int i = 0; i < size; i++){
//            StringBuffer number = new StringBuffer();
//            String newNumber = String.valueOf(index++);
//            StringBuffer zeros = new StringBuffer();
//            for(int j = 0; j < 5 - newNumber.length(); j++){
//                zeros.append("0");
//            }
//            number.append("JP" + curDate + zeros.toString() + newNumber);
//            res.add(number.toString());
//        }
//        return res;
//    }

}
