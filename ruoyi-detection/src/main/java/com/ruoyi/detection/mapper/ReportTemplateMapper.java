package com.ruoyi.detection.mapper;

import java.util.List;

import com.ruoyi.detection.domain.Laboratory;
import com.ruoyi.detection.domain.ReportTemplate;
import com.ruoyi.detection.domain.vo.LaboratoryVO;
import com.ruoyi.detection.domain.vo.ReportTemplateVO;
import org.apache.ibatis.annotations.Select;

/**
 * 报告模板Mapper接口
 * 
 * @author zhangkui
 * @date 2021-08-25
 */
public interface ReportTemplateMapper 
{
    /**
     * 查询报告模板
     * 
     * @param reportTemID 报告模板ID
     * @return 报告模板
     */
    public ReportTemplate selectReportTemplateById(Long reportTemID);

    /**
     * 查询报告模板列表
     * 
     * @param reportTemplate 报告模板
     * @return 报告模板集合
     */
    public List<ReportTemplate> selectReportTemplateList(ReportTemplate reportTemplate);

    /**
     * 新增报告模板
     * 
     * @param reportTemplate 报告模板
     * @return 结果
     */
    public int insertReportTemplate(ReportTemplate reportTemplate);

    /**
     * 修改报告模板
     * 
     * @param reportTemplate 报告模板
     * @return 结果
     */
    public int updateReportTemplate(ReportTemplate reportTemplate);

    /**
     * 删除报告模板
     * 
     * @param reportTemID 报告模板ID
     * @return 结果
     */
    public int deleteReportTemplateById(Long reportTemID);

    /**
     * 批量删除报告模板
     * 
     * @param reportTemIDs 需要删除的数据ID
     * @return 结果
     */
    public int deleteReportTemplateByIds(Long[] reportTemIDs);

//    @Select("select BGMB_MC as reportTemName,JCZX_MC as LaboratoryName,JCZX_DZ as LaboratoryAddress,YH_NC as operatorName,BGMB_CZSJ as operatorTime from xt_bgmb left join xt_jczx on xt_bgmb.BGMB_JCZXID=xt_jczx.JCZX_ID left join xt_yh on xt_bgmb.BGMB_CZYID=xt_yh.YH_ID <where>" +
//            "      <if test=\"reportTemName != null  reportTemName != ''\">  BGMB_MC = #{reportTemName}</if> " +
//            " <if test=\"LaboratoryName != null  and LaboratoryName != ''\"> and JCZX_MC = #{LaboratoryName}</if> </where>" )

    List<ReportTemplateVO>  selectReportTemplateVOList(ReportTemplateVO reportTemplateVO);

    @Select("select JCZX_ID as laboratoryID,JCZX_MC as laboratoryName from xt_jczx")
    List<LaboratoryVO> searchLaboratoryies();

}
