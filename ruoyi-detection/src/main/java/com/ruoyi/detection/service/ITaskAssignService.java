package com.ruoyi.detection.service;

import com.ruoyi.detection.domain.TaskAssign;
import com.ruoyi.detection.domain.vo.*;

import java.util.List;

/**
 * 任务分派Service接口
 *
 * @author lkun
 * @date 2021-08-05
 */
public interface ITaskAssignService
{
    /**
     * 查询任务分派
     *
     * @param sampleID 任务分派ID
     * @return 任务分派
     */
    public TaskAssign selectTaskAssignById(Long sampleID);

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
     * 批量删除任务分派
     *
     * @param sampleIDs 需要删除的任务分派ID
     * @return 结果
     */
    public int deleteTaskAssignByIds(Long[] sampleIDs);

    /**
     * 删除任务分派信息
     *
     * @param sampleID 任务分派ID
     * @return 结果
     */
    public int deleteTaskAssignById(Long sampleID);

    /**
     * 认领Vo
     * @param taskAssginVO
     * @return
     */
    List<TaskAssginVO> selectTaskAssignVOList(TaskAssginVO taskAssginVO);

    int submitNoClaim(List<TaskAssginVO> taskAssginVOList);

    int submitClaim(List<TaskAssginVO> taskAssginVOList);

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
    List<DetectionItemVO> getDetectionItemList(String sampleAcademicName, Long standardID);

    /**
     * 根据检品学名和检测标准查找农药信息
     * @param academicName 学名
     * @param standardID 检测标准ID
     * @return
     */
    List<PesticideIDNameVO> getPesticideListByAcademicNameAndStandardID(String academicName, Long standardID);

    /**
     * 查询到检测方法ID和检测方法名称列表
     * @return
     */
    List<DetectionMethodIDAndNameVO> getDetectionMethodList();

    /**
     * 找到所有的检测人员ID和检测人员名称
     * @return
     */
    List<InspectorIDAndNameVO> getInspectorList();

    /**
     * 新增单个检测任务
     * @param detectionItemVOs
     * @return
     */
    int addTask(List<DetectionItemVO> detectionItemVOs);

    /**
     * 查询任务分派列表展示内容
     */
    List<TaskAssignSearchVO> findTaskAssignList(TaskAssignQueryParamVO taskAssignQueryParamVO);

    /**
     * 查询任务重新分派列表展示内容
     */
    List<TaskAssignSearchVO> findTaskReassignList(TaskAssignQueryParamVO taskAssignQueryParamVO);

    /**
     * 获取检测农药ID和检测农药名称
     */
    List<PesticideIDNameVO> getDetectionMethodListOption();

    /**
     * 查找所有业务员的ID和账号，和检测员共用一个VO
     * @return
     */
    List<InspectorIDAndNameVO> findAllServicer();

    /**
     * 修改单个检测项目
     * @param taskAssign
     * @return
     */
    int updateDetectionItem(TaskAssign taskAssign);

    /**
     * 根据检测项目ID查找到需要检测人员，检测方法
     */
    DetectionItemVO methodsAndInspector(Long id);
}
