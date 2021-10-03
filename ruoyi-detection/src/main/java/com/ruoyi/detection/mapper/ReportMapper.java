package com.ruoyi.detection.mapper;

import com.ruoyi.detection.domain.Report;
import com.ruoyi.detection.domain.vo.ReportTemplateVO;
import com.ruoyi.detection.domain.vo.ReportVO;
import com.ruoyi.detection.domain.vo.TemplateVO;
import org.apache.ibatis.annotations.Select;
import org.omg.PortableInterceptor.INACTIVE;

import java.security.acl.LastOwnerException;
import java.util.List;

/**
 * 检测报告打印邮寄Mapper接口
 * 
 * @author zhangkui
 * @date 2021-08-08
 */
public interface ReportMapper 
{
    /**
     * 查询检测报告打印邮寄
     * 
     * @param reportID 检测报告打印邮寄ID
     * @return 检测报告打印邮寄
     */
    public Report selectReportById(Long reportID);

    /**
     * 查询检测报告打印邮寄列表
     * 
     * @param report 检测报告打印邮寄
     * @return 检测报告打印邮寄集合
     */
    public List<Report> selectReportList(Report report);

    /**
     * 新增检测报告打印邮寄
     * 
     * @param report 检测报告打印邮寄
     * @return 结果
     */
    public int insertReport(Report report);

    /**
     * 修改检测报告打印邮寄
     * 
     * @param report 检测报告打印邮寄
     * @return 结果
     */
    public int updateReport(Report report);

    /**
     * 删除检测报告打印邮寄
     * 
     * @param reportID 检测报告打印邮寄ID
     * @return 结果
     */
    public int deleteReportById(Long reportID);

    /**
     * 批量删除检测报告打印邮寄
     * 
     * @param reportIDs 需要删除的数据ID
     * @return 结果
     */
    public int deleteReportByIds(Long[] reportIDs);

    List<ReportVO> selectReportVOList(ReportVO reportVO);


    /**
     * 审核通过的报告
     */
//    @Select("SELECT\n" +
//            "    A.YP_ID\n" +
//            "FROM\n" +
//            "    (\n" +
//            "        SELECT\n" +
//            "            YP_ID,\n" +
//            "            count(YP_ID) ac\n" +
//            "        FROM\n" +
//            "            ypjc_jcxm_rwfp\n" +
//            "        GROUP BY\n" +
//            "            YP_ID\n" +
//            "    ) AS A,\n" +
//            "    (\n" +
//            "        SELECT\n" +
//            "            YP_ID,\n" +
//            "            count(YP_ID) bc\n" +
//            "        FROM\n" +
//            "            ypjc_jcxm_rwfp\n" +
//            "        WHERE\n" +
//            "                RWFP_ZT = 1\n" +
//            "        GROUP BY\n" +
//            "            YP_ID\n" +
//            "    ) AS B,\n" +
//            "    (\n" +
//            "        SELECT\n" +
//            "            YP_ID,\n" +
//            "            count(YP_ID) cc\n" +
//            "        FROM\n" +
//            "            ypjc_jcjg\n" +
//            "        WHERE\n" +
//            "                JCJG_ZT = 1 and JCJG_SFXZ=0\n" +
//            "        GROUP BY\n" +
//            "            YP_ID\n" +
//            "    ) AS C,\n" +
//            "    (\n" +
//            "        SELECT\n" +
//            "            YP_ID,\n" +
//            "            count(YP_ID) dc\n" +
//            "        FROM\n" +
//            "            ypjc_sh\n" +
//            "        WHERE\n" +
//            "                SH_SHJL = 1\n" +
//            "          AND SH_SHLB IN (31, 32)\n" +
//            "        GROUP BY\n" +
//            "            YP_ID\n" +
//            "    ) AS D\n" +
//            "WHERE\n" +
//            "        A.YP_ID = B.YP_ID\n" +
//            "  AND B.YP_ID = C.YP_ID\n" +
//            "  AND C.YP_ID = D.YP_ID\n" +
//            "  AND ac = bc\n" +
//            "  AND bc = cc\n" +
//            "  AND dc = cc")
    @Select("SELECT\n" +
            "    A.YP_ID\n" +
            "FROM\n" +
            "    (\n" +
            "        SELECT\n" +
            "            YP_ID,\n" +
            "            count(YP_ID) ac\n" +
            "        FROM\n" +
            "            ypjc_jcxm_rwfp\n" +
            "        GROUP BY\n" +
            "            YP_ID\n" +
            "    ) AS A,\n" +
            "    (\n" +
            "        SELECT\n" +
            "            YP_ID,\n" +
            "            count(YP_ID) bc\n" +
            "        FROM\n" +
            "            ypjc_jcxm_rwfp\n" +
            "        WHERE\n" +
            "                RWFP_ZT = 1\n" +
            "        GROUP BY\n" +
            "            YP_ID\n" +
            "    ) AS B,\n" +
            "    (\n" +
            "        SELECT\n" +
            "            YP_ID,\n" +
            "            count(YP_ID) cc\n" +
            "        FROM\n" +
            "            ypjc_jcjg\n" +
            "        WHERE\n" +
            "                JCJG_ZT = 1 and JCJG_SFXZ=0\n" +
            "        GROUP BY\n" +
            "            YP_ID\n" +
            "    ) AS C,\n" +
            "    (\n" +
            "        SELECT\n" +
            "            YP_ID,\n" +
            "            count(YP_ID) dc\n" +
            "        FROM\n" +
            "            ypjc_sh\n" +
            "        WHERE\n" +
            "                SH_SHJL = 1\n" +
            "           AND     SH_LZBZ = 0\n" +
            "          AND SH_SHLB IN (31, 32)\n" +
            "        GROUP BY\n" +
            "            YP_ID\n" +
            "    ) AS D\n" +
            "WHERE\n" +
            "        A.YP_ID = B.YP_ID\n" +
            "  AND B.YP_ID = C.YP_ID\n" +
            "  AND C.YP_ID = D.YP_ID\n" +
            "  AND ac = bc\n" +
            "  AND bc = cc\n" +
            "  AND dc = cc\n" +
            "  AND A.YP_ID NOT IN (select distinct YP_ID from ypjc_jcbg where JCBG_ZT=4 union\n" +
            "                      select distinct YP_ID from ypjc_sh where SH_SHLB=33 and SH_SHJL=2 and SH_LZBZ=1)")
    List<Long> selectPassList();


    @Select("select distinct YP_ID from (select distinct YP_ID\n" +
            "                            from ypjc_jcbg\n" +
            "                            where JCBG_ZT = 4\n" +
            "                            union\n" +
            "                            select distinct YP_ID\n" +
            "                            from ypjc_sh\n" +
            "                            where SH_SHLB = 33\n" +
            "                              and SH_SHJL = 2\n" +
            "                              and SH_LZBZ = 1\n" +
            "                           ) A where A.YP_ID  in\n" +
            "(     SELECT\n" +
            "          YP_ID\n" +
            "      FROM\n" +
            "          ypjc_jcjg\n" +
            "      WHERE\n" +
            "              JCJG_ZT = 1 and JCJG_SFXZ=0\n" +
            "      GROUP BY\n" +
            "          YP_ID)")
    List<Long> selectNoPassList();

    @Select("select BGMB_ID as reportTemID,BGMB_MC as reportTemName from xt_bgmb where BGMB_JCZXID=#{inspectorID} ")
    List<TemplateVO> selectAllTem(Long inspectorID);
}
