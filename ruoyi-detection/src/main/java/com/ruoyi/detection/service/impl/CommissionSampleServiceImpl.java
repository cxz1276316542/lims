package com.ruoyi.detection.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.commission.service.IProgressService;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.detection.constant.CommissionSampleStatus;
import com.ruoyi.detection.domain.MeasureUnit;
import com.ruoyi.detection.domain.vo.*;
import com.ruoyi.review.domain.Review;
import com.ruoyi.review.service.IReviewService;
import com.ruoyi.system.service.INumberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.detection.mapper.CommissionSampleMapper;
import com.ruoyi.detection.domain.CommissionSample;
import com.ruoyi.detection.service.ICommissionSampleService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 委托样品Service业务层处理
 *
 * @author lkun
 * @date 2021-07-21
 */
@Service
public class CommissionSampleServiceImpl implements ICommissionSampleService
{
    @Resource
    private CommissionSampleMapper commissionSampleMapper;

    @Autowired
    private IReviewService reviewService;

    @Autowired
    private INumberService numberService;

    @Autowired
    private IProgressService progressService;

    /**
     * 查询委托样品
     *
     * @param sampleID 委托样品ID
     * @return 委托样品
     */
    @Override
    public CommissionSample selectCommissionSampleById(Long sampleID)
    {
        CommissionSample commissionSample = commissionSampleMapper.selectCommissionSampleById(sampleID);
        commissionSample.setSampleDescriptionArray(commissionSampleMapper.findItemListByIDs(stringToLongArray(commissionSample.getSampleDescription())));
        return commissionSample;
    }
    private Long[] stringToLongArray(String str){
        if(StringUtils.isEmpty(str)){
            return null;
        }
        String[] ids = str.split(",");
        Long[] longIds = new Long[ids.length];
        for(int i = 0; i < ids.length; ++i){
            longIds[i] = Long.valueOf(ids[i]);
        }
        return longIds;
    }

    /**
     * 查询委托样品列表
     *
     * @param commissionSample 委托样品
     * @return 委托样品
     */
    @Override
    public List<CommissionSample> selectCommissionSampleList(CommissionSample commissionSample)
    {
        return commissionSampleMapper.selectCommissionSampleList(commissionSample);
    }

    /**
     * 新增委托样品
     *
     * @param commissionSample 委托样品
     * @return 结果
     */
    @Transactional
    @Override
    public int insertCommissionSample(CommissionSample commissionSample)
    {
        commissionSample.setSampleStage(1);
        // 处理检品描述，把检测描述名称转成id字符串
        List<Long> itemListByNames = commissionSampleMapper.findItemListByNames(commissionSample.getSampleDescriptionArray());
        commissionSample.setSampleDescription(listToString(itemListByNames));
        return commissionSampleMapper.insertCommissionSample(commissionSample);
    }

    private String listToString(List<Long> ids){
        StringBuffer sb = new StringBuffer();
        int size = ids.size();
        for(int i = 0; i < size; ++i){
            sb.append(ids.get(i));
            if(i < size - 1){
                sb.append(',');
            }
        }
        return sb.toString();
    }

    @Transactional
    @Override
    public int insertCommissionSampleBatch(CommissionRegister commissionRegister) {
        int row = 0;
        try {
            LoginUser loginUser = SecurityUtils.getLoginUser();
            // 自动生成委托单编号
            String commissionNumber = numberService.generateCommissionNumber();
            // 自动生成检品编号
            List<String> sampleNumberList = numberService.generateSampleNumber(commissionRegister.getSampleList().size());
            int idx = 0;
            for(CommissionSample com : commissionRegister.getSampleList()){
                com.setPrincipalNumber(commissionNumber);
                com.setSampleNumber(sampleNumberList.get(idx++));
                com.setCompanyID(commissionRegister.getCompanyID());
                com.setCustomerID(commissionRegister.getCustomerID());
                com.setReceiptAddress(commissionRegister.getReceiptAddress());
                com.setOperatorID(loginUser.getUser().getUserId());
                com.setOperationTime(new Date());
                this.insertCommissionSample(com);
                progressService.inCommissionRewview(com.getSampleID());
                // 查询是否存在审核记录
                Review review = new Review();
                review.setSampleID(com.getSampleID());
                // 新增一定是初次送审
                review.setReviewCategory(11);
                review.setPriority(com.getPriority());
                reviewService.insertReview(review);
                row++;
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return row;
    }

    /**
     * 修改委托样品
     *
     * @param commissionSample 委托样品
     * @return 结果
     */
    @Transactional
    @Override
    public int updateCommissionSample(CommissionSample commissionSample)
    {
        if(CommissionSampleStatus.BUSINESS_SUBMIT == commissionSample.getReviewStatus()){
            Review review = new Review();
            int count = commissionSampleMapper.findSampleSubmitRecord(commissionSample.getSampleID());
            if(count > 0){
                review.setReviewCategory(12);
            }else{
                review.setReviewCategory(11);
            }
            review.setSampleID(commissionSample.getSampleID());
            review.setPriority(commissionSample.getPriority());
            reviewService.insertReview(review);
            progressService.inCommissionRewview(commissionSample.getSampleID());
        }
        commissionSample.setSampleDescription(listToString(commissionSampleMapper.findItemListByNames(commissionSample.getSampleDescriptionArray())));
        return commissionSampleMapper.updateCommissionSample(commissionSample);
    }

    /**
     * 批量删除委托样品
     *
     * @param sampleIDs 需要删除的委托样品ID
     * @return 结果
     */
    @Override
    public int deleteCommissionSampleByIds(Long[] sampleIDs)
    {
        return commissionSampleMapper.deleteCommissionSampleByIds(sampleIDs);
    }

    /**
     * 删除委托样品信息
     *
     * @param sampleID 委托样品ID
     * @return 结果
     */
    @Override
    public int deleteCommissionSampleById(Long sampleID)
    {
        return commissionSampleMapper.deleteCommissionSampleById(sampleID);
    }

    @Override
    public List<DetectionStandardVO> findAllStandard() {
        return commissionSampleMapper.findAllStandards();
    }

    @Override
    public List<String> findAllSampleAcademicName() {
        return commissionSampleMapper.findAllSampleAcademicName();
    }

    public List<MeasureUnitVO> findAllMeasureUnit(){
        return commissionSampleMapper.findAllValidMeasureUnit();
    }

    @Transactional
    @Override
    public int submitSampleBatch(Long[] ids) {
        if(null == ids || ids.length == 0){
            return 0;
        }
        List<CommissionSample> commissionSamples = commissionSampleMapper.selectCommissionSampleByIds(ids);
        if(commissionSamples != null && commissionSamples.size() != 0){
            for (CommissionSample sample : commissionSamples) {
                Review review = new Review();
                review.setSampleID(sample.getSampleID());
                if(CommissionSampleStatus.COMMISSION_SAVE == sample.getReviewStatus() || CommissionSampleStatus.BUSINESS_SUBMIT == sample.getReviewStatus()){
                    review.setReviewCategory(11);
                }else if(CommissionSampleStatus.COMMISSION_EDIT == sample.getReviewStatus() || CommissionSampleStatus.BUSINESS_EDIT == sample.getReviewStatus()){
                    review.setReviewCategory(12);
                }else if(CommissionSampleStatus.BUSINESS_SAVE == sample.getReviewStatus()){
                    // 查找审核表记录是否有值，如果有，那么说明不是首次提交，否则，是首次提交
                    int count = commissionSampleMapper.findSampleSubmitRecord(sample.getSampleID());
                    if(count > 0){
                        review.setReviewCategory(12);
                    }else{
                        review.setReviewCategory(11);
                    }
                }
                review.setPriority(sample.getPriority());
                reviewService.insertReview(review);
            }
        }
        Long userId = SecurityUtils.getLoginUser().getUser().getUserId();
        for(Long id: ids){
           progressService.inCommissionRewview(id);
        }
        return commissionSampleMapper.submitSampleBatch(ids,userId);
    }

    @Override
    public int updateStatus(Long[] sampleIDs,int status) {
        Map<String, Object> map = new HashMap<>();
        map.put("list", sampleIDs);
        map.put("status", status);
        return commissionSampleMapper.updateStatusById(map);
    }

    @Override
    public List<CustomerVO> searchAllCustomers() {
        return commissionSampleMapper.searchAllCustomers();
    }

    /**
     * 查询委托样品
     */
    @Override
    public List<CommissionSampleRegisterVO> searchAllCommissionSampleRegister(QueryCommissionSampleVO queryCommissionSampleVO) {
        List<CommissionSampleRegisterVO> commissionSampleRegisterVOS = commissionSampleMapper.searchAllCommissionSampleRegister(queryCommissionSampleVO);
        matchSampleDescriptionArray(commissionSampleRegisterVOS);
        return commissionSampleRegisterVOS;
    }

    /**
     * 查询委托样品
     */
    @Override
    public List<CommissionSampleRegisterVO> searchAllCommissionSampleRegisterHistory(QueryCommissionSampleVO queryCommissionSampleVO) {
        Long userId = SecurityUtils.getLoginUser().getUser().getUserId();
        queryCommissionSampleVO.setOperatorID(userId);
        List<CommissionSampleRegisterVO> commissionSampleRegisterVOS = commissionSampleMapper.searchAllCommissionSampleRegister(queryCommissionSampleVO);
        matchSampleDescriptionArray(commissionSampleRegisterVOS);
        return commissionSampleRegisterVOS;
    }

    /**
     * 把检品描述ids字符串转成检品描述名称数组
     * @param commissionSampleRegisterVOS
     */
    private void matchSampleDescriptionArray(List<CommissionSampleRegisterVO> commissionSampleRegisterVOS){
        for(CommissionSampleRegisterVO com : commissionSampleRegisterVOS){
            matchSampleDescriptionArray(com);
        }
    }

    /**
     * 把检品描述ids字符串转成检品描述名称数组
     * @param commissionSampleRegisterVO
     */
    private void matchSampleDescriptionArray(CommissionSampleRegisterVO commissionSampleRegisterVO){
        if(StringUtils.isEmpty(commissionSampleRegisterVO.getSampleDescription())){
            return;
        }
        Long[] longs = stringToLongArray(commissionSampleRegisterVO.getSampleDescription());
        commissionSampleRegisterVO.setSampleDescriptionArray(commissionSampleMapper.findItemListByIDs(longs));
    }

    /**
     * 查询所有的公司ID和地址
     * @param id 客户ID
     * @return
     */
    @Override
    public List<CompanyVO> searchAllCompanies(Long id) {
        return commissionSampleMapper.searchAllCompanies(id);
    }

    /**
     * 根据客户ID查找到最新的一条记录
     */
    @Override
    public CommissionSample searchLastRecordByCustomerID(Long customerID) {
        CommissionSample commissionSample = commissionSampleMapper.searchLastRecordByCustomerID(customerID);
        if(null == commissionSample){
            return null;
        }
        commissionSample.setSampleDescriptionArray(commissionSampleMapper.findItemListByIDs(stringToLongArray(commissionSample.getSampleDescription())));
        return commissionSample;
    }

    /**
     * 根据检品ID，查询到所有的检测项目
     */
    @Override
    public List<IDAndNameVO> findItemListBySampleID(String sampleName) {
        return commissionSampleMapper.findItemListBySampleID(sampleName,1l);
    }
}
