package com.ruoyi.detection.service.impl;

import java.io.IOException;
import java.util.List;

import com.ruoyi.common.exception.file.InvalidExtensionException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.MimeTypeUtils;
import com.ruoyi.detection.constant.ReportTemplateURL;
import com.ruoyi.detection.domain.Laboratory;
import com.ruoyi.detection.domain.Report;
import com.ruoyi.detection.domain.vo.LaboratoryVO;
import com.ruoyi.detection.domain.vo.ReportTemplateVO;
import com.ruoyi.detection.mapper.LaboratoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.detection.mapper.ReportTemplateMapper;
import com.ruoyi.detection.domain.ReportTemplate;
import com.ruoyi.detection.service.IReportTemplateService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * 报告模板Service业务层处理
 * 
 * @author zhangkui
 * @date 2021-08-25
 */
@Service
public class ReportTemplateServiceImpl implements IReportTemplateService 
{
    @Autowired
    private ReportTemplateMapper reportTemplateMapper;

    @Autowired
    private LaboratoryMapper laboratoryMapper;

    /**
     * 查询报告模板
     * 
     * @param reportTemID 报告模板ID
     * @return 报告模板
     */
    @Override
    public ReportTemplate selectReportTemplateById(Long reportTemID)
    {
        return reportTemplateMapper.selectReportTemplateById(reportTemID);
    }

    /**
     * 查询报告模板列表
     * 
     * @param reportTemplate 报告模板
     * @return 报告模板
     */
    @Override
    public List<ReportTemplate> selectReportTemplateList(ReportTemplate reportTemplate)
    {
        return reportTemplateMapper.selectReportTemplateList(reportTemplate);
    }

    /**
     * 新增报告模板
     * 
     * @param reportTemplate 报告模板
     * @return 结果
     */
    @Override
    @Transactional
    public int insertReportTemplate(ReportTemplate reportTemplate)
    {
//        reportTemplate.setReportTemURL(ReportTemplateURL.TEMPLATE+reportTemplate.getReportTemName());
        reportTemplate.setOperatorID(SecurityUtils.getLoginUser().getUser().getUserId());
        reportTemplate.setOperatorTime(DateUtils.getNowDate());
        return reportTemplateMapper.insertReportTemplate(reportTemplate);
    }

    /**
     * 修改报告模板
     * 
     * @param reportTemplate 报告模板
     * @return 结果
     */
    @Override
    @Transactional
    public int updateReportTemplate(ReportTemplate reportTemplate)
    {
        reportTemplate.setReportTemURL(ReportTemplateURL.TEMPLATE+reportTemplate.getReportTemName());
        reportTemplate.setOperatorID(SecurityUtils.getLoginUser().getUser().getUserId());
        reportTemplate.setOperatorTime(DateUtils.getNowDate());
        return reportTemplateMapper.updateReportTemplate(reportTemplate);
    }

    /**
     * 批量删除报告模板
     * 
     * @param reportTemIDs 需要删除的报告模板ID
     * @return 结果
     */
    @Override
    public int deleteReportTemplateByIds(Long[] reportTemIDs)
    {
        return reportTemplateMapper.deleteReportTemplateByIds(reportTemIDs);
    }

    /**
     * 删除报告模板信息
     * 
     * @param reportTemID 报告模板ID
     * @return 结果
     */
    @Override
    public int deleteReportTemplateById(Long reportTemID)
    {
        return reportTemplateMapper.deleteReportTemplateById(reportTemID);
    }

    @Override
    public List<ReportTemplateVO> selectReportTemplateVOList(ReportTemplateVO reportTemplateVO) {
        return reportTemplateMapper.selectReportTemplateVOList(reportTemplateVO);
    }

    @Override
    public List<LaboratoryVO> searchLaboratoryies() {
        return  reportTemplateMapper.searchLaboratoryies();
    }

    /**
     * 上穿报告模板
     * @param file
     * @param updateSupport
     * @param reportTemName
     * @return
     * @throws IOException
     * @throws InvalidExtensionException
     * @throws InterruptedException
     */
    @Override
    @Transactional
    public int importReportTem(MultipartFile file, String updateSupport, String reportTemName) throws IOException, InvalidExtensionException, InterruptedException {
        if (file != null) {

            String baseDir = ReportTemplateURL.TEMPLATE+updateSupport+"/"+reportTemName+"/";
            // 上传并返回新文件名称
            String fileURL = FileUploadUtils.myUpload(baseDir,updateSupport,reportTemName, file, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION);
            Thread.sleep(1000);
            //更新url
            ReportTemplate reportTemplate = new ReportTemplate();
            reportTemplate.setReportTemName(reportTemName);
            List<ReportTemplate> reportTemplates = reportTemplateMapper.selectReportTemplateList(reportTemplate);
            reportTemplates.get(0).setReportTemURL(fileURL);
            reportTemplateMapper.updateReportTemplate(reportTemplates.get(0));
        }
        return 1;
    }
}
