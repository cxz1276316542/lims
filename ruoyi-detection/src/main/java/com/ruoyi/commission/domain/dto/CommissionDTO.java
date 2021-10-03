package com.ruoyi.commission.domain.dto;
import com.ruoyi.commission.domain.Commission;

import java.util.*;

public class CommissionDTO {

    private String sampleSenderName;
    private String sampleSenderPhone;
    private String receiptAddress;
    private List<EntrustList> entrustList;

    public CommissionDTO(){}

    public CommissionDTO(Commission commission){
        sampleSenderName = commission.getSampleSenderName();
        sampleSenderPhone = commission.getSampleSenderPhone();
        receiptAddress = commission.getReceiptAddress();
        entrustList = new ArrayList<>();
        EntrustList el = new EntrustList();
        el.setPriority(((Number)commission.getPriority()).intValue());
        el.setDetectionCategory(Math.toIntExact(commission.getDetectionCategory()));
        el.setDetectionStandardID(Math.toIntExact(commission.getDetectionStandardID()));
        el.setMeasureUnit(Math.toIntExact(commission.getMeasureUnit()));
        el.setProductionUnit(commission.getProductionUnit());
        el.setProductionUnitAddress(commission.getProductionUnitAddress());
        el.setRecycle(Math.toIntExact(commission.getRecycle()));
        el.setReportReceiveWay(Math.toIntExact(commission.getReportReceiveWay()));
        el.setSampleDescription(commission.getSampleDescription());
        el.setSampleName(commission.getSampleName());
        el.setSamplingBase(commission.getSamplingBase());
        el.setSampleScientificName(commission.getSampleScientificName());
        entrustList.add(el);
    }

    public void setSampleSenderName(String sampleSenderName) {
        this.sampleSenderName = sampleSenderName;
    }
    public String getSampleSenderName() {
        return sampleSenderName;
    }

    public void setSampleSenderPhone(String sampleSenderPhone) {
        this.sampleSenderPhone = sampleSenderPhone;
    }
    public String getSampleSenderPhone() {
        return sampleSenderPhone;
    }

    public void setReceiptAddress(String receiptAddress) {
        this.receiptAddress = receiptAddress;
    }
    public String getReceiptAddress() {
        return receiptAddress;
    }

    public void setEntrustList(List<EntrustList> entrustList) {
        this.entrustList = entrustList;
    }
    public List<EntrustList> getEntrustList() {
        return entrustList;
    }
}
