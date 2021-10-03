package com.ruoyi.detection.mapper;

import com.ruoyi.detection.domain.TaskAssign;
import com.ruoyi.detection.domain.vo.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 任务分派Mapper接口
 *
 * @author lkun
 * @date 2021-08-05
 */
public interface TaskAssignMapper {
    /**
     * 查询任务分派
     *
     * @param sampleID 任务分派ID
     * @return 任务分派
     */
    public TaskAssign selectTaskAssignById(Long taskAssignID);

    /**
     * 查询任务分派列表
     *
     * @param taskAssign 任务分派
     * @return 任务分派集合
     */
    public List<TaskAssign> selectTaskAssignList(TaskAssign taskAssign);

    /**
     * 新增任务分派
     *
     * @param taskAssign 任务分派
     * @return 结果
     */
    public int insertTaskAssign(TaskAssign taskAssign);

    /**
     * 修改任务分派
     *
     * @param taskAssign 任务分派
     * @return 结果
     */
    public int updateTaskAssign(TaskAssign taskAssign);

    /**
     * 删除任务分派
     *
     * @param sampleID 任务分派ID
     * @return 结果
     */
    public int deleteTaskAssignById(Long sampleID);

    /**
     * 批量删除任务分派
     *
     * @param sampleIDs 需要删除的数据ID
     * @return 结果
     */
    public int deleteTaskAssignByIds(Long[] sampleIDs);


    List<TaskAssginVO> selectTaskAssignVOList(TaskAssginVO taskAssginVO);

    /**
     * 查询未分派的任务列表
     * @return
     */
    List<TaskAssignAddVO> unAssignedTaskList(TaskAssignAddSearchVO taskAssignAddSearchVO);

    /**
     * 根据ID查询到未分派任务
     * @param sampleID 检品ID
     * @return
     */
    TaskAssignAddVO getUnsignedTaskByID(Long sampleID);

    /**
     * 根据检品学名和检测标准查找到需要的所有检测项目
     */
    List<DetectionItemVO> getDetectionItemList(@Param("sampleAcademicName") String sampleAcademicName, @Param("standardID") Long standardID);

    /**
     *
     * @param academicName
     * @param standardID
     * @return
     */
    @Select("select a.JCNY_ID pesticideID, a.JCNY_MC pesticideName from xlk_jcny a\n" +
            "where a.JCNY_ID in\n" +
            "      (select JCNY_ID from xlk_jcbz_sp_ny b where b.SPLB_ID in\n" +
            "               (select c.SPLB_ID from xlk_sp c where c.SP_XM = #{academicName}) and b.JCBZ_ID = #{standardID})\n" +
            "      and a.JCNY_HM = 0;")
    List<PesticideIDNameVO> getPesticideListByAcademicNameAndStandardID(@Param("academicName") String academicName, @Param("standardID") Long standardID);

    /**
     * 查询到检测方法ID和检测方法名称列表
     * @return
     */
    @Select("select JCFF_ID detectionMethodID,JCFF_MC detectionMethodName from xlk_jcff")
    List<DetectionMethodIDAndNameVO> getDetectionMethodList();


    /**
     * 找到所有的检测人员ID和检测人员名称
     * @return
     */
    @Select("select a.YH_ID inspectorID, a.YH_ZH inspectorName from xt_yh a\n" +
            "    left join xt_yh_js b on a.YH_ID = b.YH_ID\n" +
            "    left join xt_js c on b.JS_ID = c.JS_ID\n" +
            "where a.YH_LX = '00' and c.JS_MC = '检测员' and a.YH_ZT = '0'")
    List<InspectorIDAndNameVO> getInspectorList();

    /**
     * 新增单个检测任务
     * @param detectionItemVO
     * @return
     */
    @Insert("insert into ypjc_jcxm_rwfp\n" +
            "    (RWFP_BH,YP_ID,YP_BH,JCNY_ID,JCFF_ID,RWFP_JCR_ID,RWFP_BS,RWFP_CZYID,RWFP_CZSJ) values\n" +
            "   ('RWD202108090001',#{sampleID},#{sampleNumber},#{pesticideID},#{detectionMethodID}," +
            "   #{mainInspectorID},0,#{operatorID},sysdate())")
    int addMainTask(DetectionItemVO detectionItemVO);

    /**
     * 新增单个检测任务
     * @param detectionItemVO
     * @return
     */
    @Insert("insert into ypjc_jcxm_rwfp\n" +
            "    (RWFP_BH,YP_ID,YP_BH,JCNY_ID,JCFF_ID,RWFP_JCR_ID,RWFP_BS,RWFP_CZYID,RWFP_CZSJ) values\n" +
            "   ('RWD202108090001',#{sampleID},#{sampleNumber},#{pesticideID},#{detectionMethodID}," +
            "   #{deputyInspectorID},1,#{operatorID},sysdate())")
    int addDeputyTask(DetectionItemVO detectionItemVO);


    /**
     * 查询任务分派VO
     */
    List<TaskAssignSearchVO> findTaskAssignList(TaskAssignQueryParamVO taskAssignQueryParamVO);

    /**
     * 获取检测农药ID和检测农药名称
     */
    @Select("select JCXM_ID pesticideID,JCXM_MC pesticideName from xlk_jcxm")
    List<PesticideIDNameVO> getDetectionMethodListOption();

    /**
     * 查找所有业务员的ID和账号，和检测员共用一个VO
     * @return
     */
    @Select("select a.YH_ID inspectorID, a.YH_ZH inspectorName from xt_yh a\n" +
            "    left join xt_yh_js b on a.YH_ID = b.YH_ID\n" +
            "    left join xt_js c on b.JS_ID = c.JS_ID\n" +
            "where a.YH_LX = '00' and c.JS_MC = '业务员' and a.YH_ZT = '0'")
    List<InspectorIDAndNameVO> findAllServicer();

    /**
     * 修改单个检测项目
     * @param taskAssign
     * @return
     */
    int updateDetectionItem(TaskAssign taskAssign);

    /**
     * 查询任务重新分派列表展示内容
     */
    List<TaskAssignSearchVO> findTaskReassignList(TaskAssignQueryParamVO taskAssignQueryParamVO);

    /**
     * 查找最后一条任务分派的记录的任务分派编号
     */
    @Select("select RWFP_BH from ypjc_jcxm_rwfp order by RWFP_ID desc limit 1")
    String findLastTaskAssignRecord();

    /**
     * 根据检测项目ID查找到默认的检测方法ID
     */
    @Select("select distinct JCFF_ID from xlk_jcxm_jcff where JCXM_ID = #{id} and SJYXX_BS = 2 and JCZX_ID = 1 limit 1")
    Long findDefaultDetectionMethodID(@Param("id") Long id);

    /**
     * 根据检测项目ID查找默认的检测人员ID
     */
    @Select("select distinct JCY_ID from xlk_jcy_jcxm where JCXM_ID = #{id} and SJYXX_BS = 2 and JCZX_ID = 1 limit 1")
    Long findDefaultDetectionInspector(@Param("id") Long id);

    /**
     * 根据检测项目ID查找对应的检测方法list
     */
    @Select("select distinct a.JCFF_ID ID, a.JCFF_MC name from xlk_jcff a left join xlk_jcxm_jcff b on a.JCFF_ID = b.JCFF_ID\n" +
            "where b.JCXM_ID = #{id} and b.SJYXX_BS != 0")
    List<IDAndNameVO> findDetectionMethodsByID(@Param("id")Long id);

    /**
     * 根据检测项目ID查找对应的检测员list
     */
    @Select("select distinct a.YH_ID ID, a.YH_ZH name from xt_yh a left join xlk_jcy_jcxm b on a.YH_ID = b.JCY_ID\n" +
            "where b.JCXM_ID = #{id} and b.SJYXX_BS != 0")
    List<IDAndNameVO> findInspectorsByID(@Param("id")Long id);


    /**
     * 通过ID找到对应的检测项目名称
     */
    @Select("select JCXM_MC from xlk_jcxm where JCXM_ID = #{id}")
    String findDetectionItemNameByID(@Param("id")Long id);
}
