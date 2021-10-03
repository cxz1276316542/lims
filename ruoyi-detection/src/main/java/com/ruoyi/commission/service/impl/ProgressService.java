package com.ruoyi.commission.service.impl;

import com.ruoyi.commission.mapper.ProgressMapper;
import com.ruoyi.commission.service.IProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProgressService implements IProgressService {
    @Autowired
    private ProgressMapper progressMapper;

    @Override
    public void inCommission(long sampleId){
        if(isExist(sampleId)){
            progressMapper.updateCommission(sampleId);
        }else{
            progressMapper.inCommission(sampleId);
        }
    }

    /**
     * 委托审核
     * @param sampleId
     */
    @Override
    public void inCommissionRewview(long sampleId){
        if(isExist(sampleId)){
            progressMapper.updateCommissionRewview(sampleId);
        }else{
            progressMapper.insertCommissionRewview(sampleId);
        }
    }

    /**
     * 委托退出
     * @param sampleId
     */
    @Override
    public void inCommissionExit(long sampleId){
        if(isExist(sampleId)){
            progressMapper.updateCommissionExit(sampleId);
        }else{
            progressMapper.insertCommissionExit(sampleId);
        }
    }

    /**
     * 任务分派
     * @param sampleId
     */
    @Override
    public void inTaskAssign(long sampleId){
        if(isExist(sampleId)){
            progressMapper.updateTaskAssign(sampleId);
        }else{
            progressMapper.insertTaskAssign(sampleId);
        }
    }

    /**
     * 任务审核
     * @param sampleId
     */
    @Override
    public void inTaskRewview(long sampleId){
        if(isExist(sampleId)){
            progressMapper.updateTaskRewview(sampleId);
        }else{
            progressMapper.insertTaskRewview(sampleId);
        }
    }

    /**
     * 项目检测
     * @param sampleId
     */
    @Override
    public void inDetectionResult(long sampleId){
        if(isExist(sampleId)){
            progressMapper.updateDetectionResult(sampleId);
        }else{
            progressMapper.insertDetectionResult(sampleId);
        }
    }

    /**
     * 项目审核
     * @param sampleId
     */
    @Override
    public void inDetectionResultReview(long sampleId){
        if(isExist(sampleId)){
            progressMapper.updateDetectionResultReview(sampleId);
        }else{
            progressMapper.insertDetectionResultReview(sampleId);
        }
    }

    /**
     * 报告生成
     * @param sampleId
     */
    @Override
    public void inReport(long sampleId){
        if(isExist(sampleId)){
            progressMapper.updateReport(sampleId);
        }else{
            progressMapper.insertReport(sampleId);
        }
    }

    /**
     * 报告审核
     * @param sampleId
     */
    @Override
    public void inReportReview(long sampleId){
        if(isExist(sampleId)){
            progressMapper.updateReportReview(sampleId);
        }else{
            progressMapper.insertReportReview(sampleId);
        }
    }

    /**
     * 报告签发
     * @param sampleId
     */
    @Override
    public void inReportSign(long sampleId){
        if(isExist(sampleId)){
            progressMapper.updateReportSign(sampleId);
        }else{
            progressMapper.insertReportSign(sampleId);
        }
    }

    /**
     * 报告完成
     * @param sampleId
     */
    @Override
    public void inReportFinish(long sampleId){
        if(isExist(sampleId)){
            progressMapper.updateReportFinish(sampleId);
        }else{
            progressMapper.insertReportFinish(sampleId);
        }
    }

    /**
     * 报告退检
     * @param sampleId
     */
    @Override
    public void inReportExit(long sampleId){
        if(isExist(sampleId)){
            progressMapper.updateReportExit(sampleId);
        }else{
            progressMapper.insertReportExit(sampleId);
        }
    }

    private boolean isExist(long sampleId){
        Integer i = progressMapper.selectId(sampleId);
        if(i == null){
            return false;
        }
        return sampleId == i;
    }

}
