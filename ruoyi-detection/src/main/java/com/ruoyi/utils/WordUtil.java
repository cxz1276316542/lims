package com.ruoyi.utils;

import com.ruoyi.detection.constant.ReportTemplateURL;
import com.ruoyi.detection.domain.vo.ReportResultVO;
import com.ruoyi.detection.domain.vo.ReportDetailInfoVO;
import com.ruoyi.detection.mapper.ReportTemplateMapper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 自动生成报告工具
 * @author zhangkui
 * @data 2021.5.18
 */

public class WordUtil {

    /**
     * 使用FreeMarker自动生成Word文档
     *
     * @param dataMap  生成Word文档所需要的数据
     * @param fileName 生成Word文档的全路径名称
     */
    public static void generateWord(Map<String, Object> dataMap, String fileName,String reportTemURL) throws Exception {

        String[] split = reportTemURL.split("/");


        String filePath= ReportTemplateURL.WORD_PATH +fileName+".doc";
//        String filePath="/home/ruoyi/uploadPath/upload"+fileName+".doc";
        // 设置FreeMarker的版本和编码格式
        Configuration configuration = new Configuration(new Version("2.3.28"));
        configuration.setDefaultEncoding("UTF-8");

        // 设置FreeMarker生成Word文档所需要的模板的路径
//        configuration.setDirectoryForTemplateLoading(new File(ReportTemplateURL.TEMPLATE));
        configuration.setDirectoryForTemplateLoading(new File(ReportTemplateURL.TEMPLATE+split[0]+"/"+split[1]+"/"));
        // 设置FreeMarker生成Word文档所需要的模板
        Template t = configuration.getTemplate(split[2], "UTF-8");
//        Template t = configuration.getTemplate("reportTemplate.ftl", "UTF-8");
        // 创建一个Word文档的输出流
        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(filePath)), "UTF-8"));
        //FreeMarker使用Word模板和数据生成Word文档
        t.process(dataMap, out);
        out.flush();
        out.close();
        System.out.println(fileName+"报告生成成功");

    }

    /**
     * 获取生成Word文档所需要的数据
     */
    public static Map<String, Object> getWordData(ReportResultVO reportVO) {


        /*
         * 创建一个Map对象，将Word文档需要的数据都保存到该Map对象中
         */
        Map<String, Object> dataMap = new HashMap<>();

        /*
         * 直接在map里保存一个用户的各项信息
         * 该用户信息用于Word文档中FreeMarker普通文本处理
         * 模板文档占位符${name}中的name即指定使用这里的name属性的值"用户1"替换
         */
        dataMap.put("sampleAcademicName",reportVO.getSampleAcademicName());
        dataMap.put("detectionType",reportVO.getDetectionType());
        dataMap.put("sampleNumber",reportVO.getSampleNumber());
        dataMap.put("sampleCount",reportVO.getSampleCount());
        dataMap.put("sendSamplePeopleName",reportVO.getSendSamplePeopleName());
        dataMap.put("detectionDate",reportVO.getDetectionDate().toString());
        dataMap.put("productUnit",reportVO.getProductUnit());
        dataMap.put("productAddress",reportVO.getProductAddress());
        dataMap.put("standard",reportVO.getStandard());
        dataMap.put("result",reportVO.getResult()==0?"不合格":"合格");
        dataMap.put("oldSampleNumber",reportVO.getOldSampleNumber());
        List<ReportDetailInfoVO> reportDetailInfoVOList=reportVO.getReportDetailInfoVOList();
        dataMap.put("detail",reportDetailInfoVOList);
        StringBuilder stringBuffer = new StringBuilder();
        for (String s : reportVO.getDetectionList()) {
            stringBuffer.append(s+"、");
        }
        String itemNames = stringBuffer.toString();
        itemNames.substring(0,stringBuffer.length()-1);
        dataMap.put("itemsName",itemNames);

//        /**
//         * 将用户的各项信息封装成对象，然后将对象保存在map中，
//         * 该用户对象用于Word文档中FreeMarker表格和图片处理
//         * 模板文档占位符${userObj.name}中的userObj即指定使用这里的userObj属性的值(即user2对象)替换
//         */
//        User user2 = new User();
//        user2.setName("用户2");
//        user2.setSex("女");
//        user2.setBirthday("1992-02-02");
//        // 使用FreeMarker在Word文档中生成图片时，需要将图片的内容转换成Base64编码的字符串
//        user2.setPhoto(ImageUtil.getImageBase64String("E:/Word/Images/photo.jpg"));
//        dataMap.put("userObj", user2);

        return dataMap;
    }

}
