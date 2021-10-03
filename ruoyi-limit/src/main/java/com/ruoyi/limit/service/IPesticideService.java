package com.ruoyi.limit.service;

import java.util.List;
import com.ruoyi.limit.domain.Pesticide;

/**
 * 检测农药管理Service接口
 * 
 * @author wlf
 * @date 2021-07-20
 */
public interface IPesticideService 
{
    /**
     * 导入检测农药信息
     */
    public String importPesticide(List<Pesticide> pesticideList, Boolean isUpdateSupport);
    /**
     *查询豁免清单
     *
     * @param pesticide 检测农药信息
     * @return 豁免清单数据
     */
    public List<Pesticide> selectExemptionList(Pesticide pesticide);
    /**
     * 查询检测农药管理
     * 
     * @param pesticideID 检测农药管理ID
     * @return 检测农药管理
     */
    public Pesticide selectPesticideById(Long pesticideID);

    /**
     * 查询检测农药管理列表
     * 
     * @param pesticide 检测农药管理
     * @return 检测农药管理集合
     */
    public List<Pesticide> selectPesticideList(Pesticide pesticide);

    /**
     * 新增检测农药管理
     * 
     * @param pesticide 检测农药管理
     * @return 结果
     */
    public String insertPesticide(Pesticide pesticide);

    /**
     * 修改检测农药管理
     * 
     * @param pesticide 检测农药管理
     * @return 结果
     */
    public int updatePesticide(Pesticide pesticide);

    /**
     * 批量删除检测农药管理
     * 
     * @param pesticideIDs 需要删除的检测农药管理ID
     * @return 结果
     */
    public int deletePesticideByIds(Long[] pesticideIDs);

    /**
     * 删除检测农药管理信息
     * 
     * @param pesticideID 检测农药管理ID
     * @return 结果
     */
    public int deletePesticideById(Long pesticideID);
}
