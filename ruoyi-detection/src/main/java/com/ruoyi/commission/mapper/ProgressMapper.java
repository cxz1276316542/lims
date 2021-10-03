package com.ruoyi.commission.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface ProgressMapper {

    @Insert("insert into ypjc_ypjczt values(#{sampleId}, 10)")
    void inCommission(@Param("sampleId") long sampleId);

    @Update("update ypjc_ypjczt set YPJCZT_MQZT=10 where YP_ID=#{sampleId}")
    void updateCommission(@Param("sampleId") long sampleId);

    @Insert("insert into ypjc_ypjczt values(#{sampleId}, 11)")
    void insertCommissionRewview(@Param("sampleId") long sampleId);

    @Update("update ypjc_ypjczt set YPJCZT_MQZT=11 where YP_ID=#{sampleId}")
    void updateCommissionRewview(@Param("sampleId") long sampleId);

    @Insert("insert into ypjc_ypjczt values(#{sampleId}, 12)")
    void insertCommissionExit(@Param("sampleId") long sampleId);

    @Update("update ypjc_ypjczt set YPJCZT_MQZT=12 where YP_ID=#{sampleId}")
    void updateCommissionExit(@Param("sampleId") long sampleId);

    @Insert("insert into ypjc_ypjczt values(#{sampleId}, 20)")
    void insertTaskAssign(@Param("sampleId") long sampleId);

    @Update("update ypjc_ypjczt set YPJCZT_MQZT=20 where YP_ID=#{sampleId}")
    void updateTaskAssign(@Param("sampleId") long sampleId);

    @Insert("insert into ypjc_ypjczt values(#{sampleId}, 21)")
    void insertTaskRewview(@Param("sampleId") long sampleId);

    @Update("update ypjc_ypjczt set YPJCZT_MQZT=21 where YP_ID=#{sampleId}")
    void updateTaskRewview(@Param("sampleId") long sampleId);

    @Insert("insert into ypjc_ypjczt values(#{sampleId}, 30)")
    void insertDetectionResult(@Param("sampleId") long sampleId);

    @Update("update ypjc_ypjczt set YPJCZT_MQZT=30 where YP_ID=#{sampleId}")
    void updateDetectionResult(@Param("sampleId") long sampleId);

    @Insert("insert into ypjc_ypjczt values(#{sampleId}, 31)")
    void insertDetectionResultReview(@Param("sampleId") long sampleId);

    @Update("update ypjc_ypjczt set YPJCZT_MQZT=31 where YP_ID=#{sampleId}")
    void updateDetectionResultReview(@Param("sampleId") long sampleId);

    @Insert("insert into ypjc_ypjczt values(#{sampleId}, 40)")
    void insertReport(@Param("sampleId") long sampleId);

    @Update("update ypjc_ypjczt set YPJCZT_MQZT=40 where YP_ID=#{sampleId}")
    void updateReport(@Param("sampleId") long sampleId);

    @Insert("insert into ypjc_ypjczt values(#{sampleId}, 41)")
    void insertReportReview(@Param("sampleId") long sampleId);

    @Update("update ypjc_ypjczt set YPJCZT_MQZT=41 where YP_ID=#{sampleId}")
    void updateReportReview(@Param("sampleId") long sampleId);

    @Insert("insert into ypjc_ypjczt values(#{sampleId}, 42)")
    void insertReportSign(@Param("sampleId") long sampleId);

    @Update("update ypjc_ypjczt set YPJCZT_MQZT=42 where YP_ID=#{sampleId}")
    void updateReportSign(@Param("sampleId") long sampleId);

    @Insert("insert into ypjc_ypjczt values(#{sampleId}, 43)")
    void insertReportFinish(@Param("sampleId") long sampleId);

    @Update("update ypjc_ypjczt set YPJCZT_MQZT=43 where YP_ID=#{sampleId}")
    void updateReportFinish(@Param("sampleId") long sampleId);

    @Insert("insert into ypjc_ypjczt values(#{sampleId}, 50)")
    void insertReportExit(@Param("sampleId") long sampleId);

    @Update("update ypjc_ypjczt set YPJCZT_MQZT=50 where YP_ID=#{sampleId}")
    void updateReportExit(@Param("sampleId") long sampleId);

    @Select("select YP_ID from ypjc_ypjczt where YP_ID=#{sampleId}")
    Integer selectId(@Param("sampleId") long sampleId);

}
