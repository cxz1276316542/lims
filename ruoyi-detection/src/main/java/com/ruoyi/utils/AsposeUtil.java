package com.ruoyi.utils;



import com.aspose.words.Document;
import com.aspose.words.License;
import com.aspose.words.SaveFormat;
import com.ruoyi.detection.constant.ReportTemplateURL;

import java.io.*;

/**
 * @author : LCheng
 * @date : 2020-12-25 13:47
 * description : Aspose工具类
 */
public class AsposeUtil {

    /**
     * doc转pdf
     * @param sourceFileName doc文档的路径 如：C:\Users\weipc\Desktop\html\询问笔录.doc
     * @param newFileName 将要生成的pdf路径 如：C:\Users\weipc\Desktop\html\询问笔录.pdf
     * @throws Exception
     */
    public static void docTurnPdf(String sourceFileName,String newFileName) throws Exception {
        if (!getLicense()) {// 验证License 若不验证则转化出的pdf文档会有水印产生
            return;
        }
        File file = new File(newFileName);  //新建一个空白pdf文档
        FileOutputStream os = new FileOutputStream(file);
        Document doc = new Document(sourceFileName);//Address是将要被转化的word文档
        doc.save(os, SaveFormat.PDF);//全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF, EPUB, XPS, SWF 相互转换
        System.out.println("------------------------------转换pdf成功-------------------------");
        os.close();
        //删除doc文件，用不到就删掉
//        deleteFile(sourceFileName);
    }

    public static void deleteFile(String fileName) throws IOException {
        File file = new File(fileName);
        if (file.isFile() && file.exists()) {
            file.delete();
            System.out.println("删除单个文件" + fileName + "成功！");
        } else {
            System.out.println("删除单个文件" + fileName + "失败！");
        }
    }



    public static boolean getLicense(){
        boolean result = false;
        try {
//            InputStream is = AsposeUtil.class.getClassLoader().getResourceAsStream("license.xml"); //Test要替换成当前类名  license.xml应放在..\WebRoot\WEB-INF\classes路径下

            InputStream is =  new FileInputStream(ReportTemplateURL.LICENSE+"license.xml");
            License aposeLic = new License();
            aposeLic.setLicense(is);
            is.close();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

//    public static void main(String[] args) throws Exception{
//        getLicense();
//        AsposeUtil.docTurnPdf(ReportTemplateURL.WORD_PATH + "JP_2021_000001" + ".doc", ReportTemplateURL.PDF_PATH + "JP_2021_000001" + ".pdf");
//    }

}
