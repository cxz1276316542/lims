package com.ruoyi.commission.service;

public interface IProgressService {
    public void inCommission(long sampleId);

    /**
     * 委托审核
     * @param sampleId
     */
    public void inCommissionRewview(long sampleId);

    /**
     * 委托退出
     * @param sampleId
     */
    public void inCommissionExit(long sampleId);

    /**
     * 任务分派
     * @param sampleId
     */
    public void inTaskAssign(long sampleId);

    /**
     * 任务审核
     * @param sampleId
     */
    public void inTaskRewview(long sampleId);

    /**
     * 项目检测
     * @param sampleId
     */
    public void inDetectionResult(long sampleId);

    /**
     * 项目审核
     * @param sampleId
     */
    public void inDetectionResultReview(long sampleId);

    /**
     * 报告生成
     * @param sampleId
     */
    public void inReport(long sampleId);

    /**
     * 报告审核
     * @param sampleId
     */
    public void inReportReview(long sampleId);

    /**
     * 报告签发
     * @param sampleId
     */
    public void inReportSign(long sampleId);

    /**
     * 报告完成
     * @param sampleId
     */
    public void inReportFinish(long sampleId);

    /**
     * 报告退检
     * @param sampleId
     */
    public void inReportExit(long sampleId);

}
