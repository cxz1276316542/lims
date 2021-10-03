package com.ruoyi.detection.service;

import java.io.IOException;
import java.util.List;

import com.ruoyi.common.exception.file.InvalidExtensionException;
import com.ruoyi.detection.domain.ReportTemplate;
import com.ruoyi.detection.domain.vo.LaboratoryVO;
import com.ruoyi.detection.domain.vo.ReportTemplateVO;
import org.springframework.web.multipart.MultipartFile;

/**
 * 报告模板Service接口
 * 
 * @author zhangkui
 * @date 2021-08-25
 */
public interface IReportTemplateService 
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
     * 批量删除报告模板
     * 
     * @param reportTemIDs 需要删除的报告模板ID
     * @return 结果
     */
    public int deleteReportTemplateByIds(Long[] reportTemIDs);

    /**
     * 删除报告模板信息
     * 
     * @param reportTemID 报告模板ID
     * @return 结果
     */
    public int deleteReportTemplateById(Long reportTemID);

    List<ReportTemplateVO> selectReportTemplateVOList(ReportTemplateVO reportTemplateVO);

    List<LaboratoryVO> searchLaboratoryies();

    int importReportTem(MultipartFile file, String updateSupport, String reportTemName) throws IOException, InvalidExtensionException, InterruptedException;
}
