package com.ruoyi.system.service.impl;

import java.util.*;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.vo.NumberVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.NumberMapper;
import com.ruoyi.system.domain.Number;
import com.ruoyi.system.service.INumberService;

/**
 * 编号生成Service业务层处理
 *
 * @author lkun
 * @date 2021-08-22
 */
@Service
public class NumberServiceImpl implements INumberService
{
    @Autowired
    private NumberMapper numberMapper;

    /**
     * 查询编号生成
     *
     * @param numberID 编号生成ID
     * @return 编号生成
     */
    @Override
    public Number selectNumberById(Long numberID)
    {
        return numberMapper.selectNumberById(numberID);
    }

    /**
     * 查询编号生成列表
     *
     * @param number 编号生成
     * @return 编号生成
     */
    @Override
    public List<Number> selectNumberList(Number number)
    {
        return numberMapper.selectNumberList(number);
    }

    /**
     * 新增编号生成
     *
     * @param number 编号生成
     * @return 结果
     */
    @Override
    public int insertNumber(Number number)
    {
        return numberMapper.insertNumber(number);
    }

    /**
     * 修改编号生成
     *
     * @param number 编号生成
     * @return 结果
     */
    @Override
    public int updateNumber(Number number)
    {
        return numberMapper.updateNumber(number);
    }

    /**
     * 批量删除编号生成
     *
     * @param numberIDs 需要删除的编号生成ID
     * @return 结果
     */
    @Override
    public int deleteNumberByIds(Long[] numberIDs)
    {
        return numberMapper.deleteNumberByIds(numberIDs);
    }

    /**
     * 删除编号生成信息
     *
     * @param numberID 编号生成ID
     * @return 结果
     */
    @Override
    public int deleteNumberById(Long numberID)
    {
        return numberMapper.deleteNumberById(numberID);
    }

    @Override
    public List<NumberVO> searchAll() {
        List<Number> numberList = numberMapper.selectNumberList(null);
        List<NumberVO> res = new ArrayList<>();
        NumberVO commissionNumber = new NumberVO(-1l, 0, "委托单编号", null, "", null, new ArrayList<>());
        NumberVO sampleNumber = new NumberVO(-2l, 1, "样品编号", null, "", null, new ArrayList<>());
        NumberVO taskNumber = new NumberVO(-3l, 2, "任务单编号", null, "", null, new ArrayList<>());
        NumberVO reportNumber = new NumberVO(-4l, 3, "检测报告编号", null, "", null, new ArrayList<>());
        res.add(commissionNumber);
        res.add(sampleNumber);
        res.add(taskNumber);
        res.add(reportNumber);
        for(Number number: numberList){
            NumberVO numberVO = new NumberVO();
            BeanUtils.copyProperties(number,numberVO);
            res.get(numberVO.getNumberTypeID()).getChildren().add(numberVO);
        }
        for(NumberVO numberVO : res){
            List<NumberVO> children = numberVO.getChildren();
            Collections.sort(children,(a,b)->(a.getOrder()-b.getOrder()));
        }
        return res;
    }

    @Override
    public List<Number> findByNumberType(Integer type) {
        Number number = new Number();
        number.setNumberTypeID(type);
        List<Number> numberList = numberMapper.selectNumberList(number);
        Collections.sort(numberList,(a,b)->(a.getOrder()-b.getOrder()));
        return numberList;
    }

    private String[] getNumberPattern(int type){
        List<Number> commissionNumberPattern = findByNumberType(type);
        StringBuffer sb = new StringBuffer();
        String serialNumber = "";
        String splitSign = "";
        // 是否一致
        for(Number number : commissionNumberPattern){
            if(("分隔符").equals(number.getDescription())){
                splitSign = number.getContent();
            }
        }
        for(Number number : commissionNumberPattern){
            if(("分隔符").equals(number.getDescription())){
                continue;
            }
            if(!StringUtils.isEmpty(number.getContent())){
                sb.append(number.getContent()).append(splitSign);
            }
            if(number.getDescription() != null && number.getDescription().equals("流水号")){
                serialNumber = String.valueOf(number.getContentLength());
            }
        }
        return new String[]{sb.toString(),serialNumber,splitSign};
    }

    private boolean isNumberChange(String lastRecord, String numberPattern, int totalLength){
        if(lastRecord == null || lastRecord.length() != totalLength || !lastRecord.contains(numberPattern)){
            return true;
        }
        return false;
    }

    // 流水号补零操作
    private String fillZero(int size,int number){
        StringBuffer zeros = new StringBuffer();
        String numberStr = String.valueOf(number);
        for(int j = 0; j < size - numberStr.length(); j++){
            zeros.append("0");
        }
        return zeros.append(numberStr).toString();
    }

    /**
     * 生成单个委托检品编号
     */
    public String generateCommissionNumber(){
        String[] numberPattern = getNumberPattern(0);
        String year = DateUtils.getDate().substring(0,4);
        int serialLength = Integer.valueOf(numberPattern[1]);
        int totalLength = numberPattern[0].length() + 4 + numberPattern[2].length() + serialLength;
        // 判断是否编号发生变化
        String lastRecord = numberMapper.findLastCommissionRecord();
        int idx = 1;
        if(isNumberChange(lastRecord,numberPattern[0]+year,totalLength)){
            idx = 1;
        }else{
            idx = Integer.valueOf(lastRecord.substring(totalLength - serialLength,totalLength)) + 1;
        }
        String serialNumber = fillZero(serialLength,idx);
        return numberPattern[0]+year+numberPattern[2]+serialNumber;
    }

    /**
     * 生成一批检品编号
     */
    public List<String> generateSampleNumber(int size){
        String[] numberPattern = getNumberPattern(1);
        String year = DateUtils.getDate().substring(0,4);
        int serialLength = Integer.valueOf(numberPattern[1]);
        int totalLength = numberPattern[0].length() + 4 + numberPattern[2].length() + serialLength;
        // 判断是否编号发生变化
        String lastRecord = numberMapper.findLastSampleRecord();
        List<String> res = new ArrayList<>();
        int idx = 1;
        if(isNumberChange(lastRecord,numberPattern[0]+year,totalLength)){
            idx = 1;
        }else{
            idx = Integer.valueOf(lastRecord.substring(totalLength - serialLength,totalLength)) + 1;
        }
        for(int i = 0; i < size; i++){
            String serialNumber = fillZero(serialLength,idx++);
            res.add(numberPattern[0]+year+numberPattern[2]+serialNumber);
        }
        return res;
    }

    /**
     * 生成一个任务单编号
     */
    public String generateTaskNumber(){
        String[] numberPattern = getNumberPattern(2);
        String year = DateUtils.getDate().substring(0,4);
        int serialLength = Integer.valueOf(numberPattern[1]);
        int totalLength = numberPattern[0].length() + 4 + numberPattern[2].length() + serialLength;
        // 判断是否编号发生变化
        String lastRecord = numberMapper.findLastTaskAssignRecord();
        int idx = 1;
        if(isNumberChange(lastRecord,numberPattern[0]+year,totalLength)){
            idx = 1;
        }else{
            idx = Integer.valueOf(lastRecord.substring(totalLength - serialLength,totalLength)) + 1;
        }
        String serialNumber = fillZero(serialLength,idx);
        return numberPattern[0]+year+numberPattern[2]+serialNumber;
    }

    /**
     * 生成一批检测报告
     */
    public List<String> generateReportNumber(int size){
        String[] numberPattern = getNumberPattern(3);
        String year = DateUtils.getDate().substring(0,4);
        int serialLength = Integer.valueOf(numberPattern[1]);
        int totalLength = numberPattern[0].length() + 4 + numberPattern[2].length() + serialLength;
        // 判断是否编号发生变化
        String lastRecord = numberMapper.findLastReportRecord();
        List<String> res = new ArrayList<>();
        int idx = 1;
        if(isNumberChange(lastRecord,numberPattern[0]+year,totalLength)){
            idx = 1;
        }else{
            idx = Integer.valueOf(lastRecord.substring(totalLength - serialLength,totalLength)) + 1;
        }
        for(int i = 0; i < size; i++){
            String serialNumber = fillZero(serialLength,idx++);
            res.add(numberPattern[0]+year+numberPattern[2]+serialNumber);
        }
        return res;
    }

}
