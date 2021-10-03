package com.ruoyi.detection.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 样品检测状态对象 ypjc_ypjczt
 *
 * @author ruoyi
 * @date 2021-07-21
 */
public class DetectionStatus extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 样品ID */
    private Long sampleID;

    /** 目前状态1-委托 2-任务分发3-检测 4-报告生成5-报告领取/邮寄 6-退检（保质过期情况） */
    @Excel(name = "目前状态1-委托 2-任务分发3-检测 4-报告生成5-报告领取/邮寄 6-退检", readConverterExp = "保=质过期情况")
    private Integer currentStatus;

    public void setSampleID(Long sampleID)
    {
        this.sampleID = sampleID;
    }

    public Long getSampleID()
    {
        return sampleID;
    }
    public void setCurrentStatus(Integer currentStatus)
    {
        this.currentStatus = currentStatus;
    }

    public Integer getCurrentStatus()
    {
        return currentStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("sampleID", getSampleID())
            .append("currentStatus", getCurrentStatus())
            .toString();
    }
}
