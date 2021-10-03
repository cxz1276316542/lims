package com.ruoyi.detection.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.security.core.parameters.P;

import java.util.List;

/**
 * @author liukun
 * @date 2021/9/2
 */
@Mapper
public interface InsertData {

    @Select("select JCXM_ID from xlk_jcxm where JCXM_HM = 0")
    List<Long> findByIDS();

    @Select("select distinct a.YH_ID from xt_yh a left join xt_yh_js b on a.YH_ID = b.YH_ID\n" +
            "left join xt_js c on b.JS_ID = c.JS_ID where a.YH_LX = '00' and c.JS_MC = '检测员'")
    List<Long> findInspectors();

    @Insert("insert into xlk_jcy_jcxm (JCY_ID,JCXM_ID,JCZX_ID,SJYXX_BS)values(#{detectionId},#{jcxm_id},#{jczx_id},#{sjyxx_bs})")
    void insertIntoDetectionList(@Param("detectionId") Long detectionId,@Param("jcxm_id") Long jcxm_id,@Param("jczx_id") Long jczx_id,@Param("sjyxx_bs") Integer sjyxx_bs);

    @Select("select JCFF_ID from xlk_jcff where SJYXX_BS = 1")
    List<Long> findMethods();

    @Insert("insert into xlk_jcxm_jcff (JCXM_ID,JCFF_ID,JCZX_ID,SJYXX_BS) values(#{jcxm_id},#{jcff_id},#{jczx_id},#{sjyxx_bs})")
    void insertIntoMethodsAndItemList(@Param("jcxm_id") Long itemID,@Param("jcff_id") Long jcff_id,@Param("jczx_id") Long jczx_id,@Param("sjyxx_bs") Integer sjyxx_bs);
}
