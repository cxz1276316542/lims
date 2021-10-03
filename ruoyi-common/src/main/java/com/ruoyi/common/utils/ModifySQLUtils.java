package com.ruoyi.common.utils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zjccc
 * @date 2021/7/20
 * @Email:648629522@qq.com
 */
public class ModifySQLUtils {
    public static void main(String[] args) throws IOException {
//        String filePath = "E:\\lims\\numberMenu.sql";
//        String outPutPath = "E:\\lims\\numberMenu1.sql";
        String filePath = "D:\\Google Chrome Download\\ruoyi (3)\\DetectionMethodStandardMenu.sql";
        String outPutPath = "D:\\Google Chrome Download\\ruoyi (3)\\DetectionMethodStandardMenu1.sql";

        File file = new File(filePath);
        Long fileLength = file.length();
        byte[] fileContext = new byte[fileLength.intValue()];
        FileInputStream in = null;
        PrintWriter out = null;
        in = new FileInputStream(filePath);
        in.read(fileContext);


        Map<String,String> map = new HashMap<>();
        map.put("sys_menu","xt_cd");
        map.put("menu_name","CD_MC");
        map.put("parent_id","CD_FID");
        map.put("order_num","CD_XSSX");
        map.put("path","CD_LYDZ");
        map.put("component","CD_ZJLJ");
        map.put("is_frame","CD_WL");
        map.put("is_cache","CD_HC");
        map.put("menu_type","CD_LX");
        map.put("visible","CD_XS");
        map.put("status","CD_ZT");
        map.put("perms","CD_QXBS");
        map.put("icon","CD_TB");
        map.put("create_by","CD_CJZ");
        map.put("create_time","CD_CJSJ");
        map.put("update_by","CD_GXZ");
        map.put("update_time","CD_GXSJ");
        map.put("remark","CD_BZ");

        String str = new String(fileContext, "utf-8");

        for (Map.Entry<String, String> entry : map.entrySet()){
            // 避免出现中文乱码
            //字节转换成字符
            str = str.replaceAll(entry.getKey(), entry.getValue());
        }
        out = new PrintWriter(outPutPath, "utf-8");//写入文件时的charset
        out.write(str);
        out.flush();
        out.close();
        in.close();
    }
}
