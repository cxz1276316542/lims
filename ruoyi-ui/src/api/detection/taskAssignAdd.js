import request from '@/utils/request'

// 查询任务分派列表
export function listTaskUnassigned(params) {
  return request({
    url: '/detection/taskAssign/unAssignedTaskList',
    method: 'get',
    params: params
  })
}

export function listTaskAssignVO(query) {
  return request({
    url: '/detection/taskAssign/listTaskAssignVO',
    method: 'get',
    params: query
  })
}

//单行认领
export function submitClaim(data) {
  return request({
    url: '/detection/taskAssign/submitClaim',
    method: 'put',
    data: data
  })
}

//批量拒领
export function submitBatchNoClaim(data) {
  return request({
    url: '/detection/taskAssign/submitBatchNoClaim',
    method: 'put',
    data: data
  })
}

//批量认领
export function submitBatchClaim(data) {
  return request({
    url: '/detection/taskAssign/submitBatchClaim',
    method: 'put',
    data: data
  })
}
//单行拒领
export function submitNoClaim(data) {
  return request({
    url: '/detection/taskAssign/submitNoClaim',
    method: 'put',
    data: data
  })
}


// 查询任务分派详细
export function getTaskAssign(sampleID) {
  return request({
    url: '/detection/taskAssign/' + sampleID,
    method: 'get'
  })
}

// 新增任务分派
export function addTaskAssign(data) {
  return request({
    url: '/detection/taskAssign',
    method: 'post',
    data: data
  })
}

// 修改任务分派
export function updateTaskAssign(data) {
  return request({
    url: '/detection/taskAssign',
    method: 'put',
    data: data
  })
}

// 删除任务分派
export function delTaskAssign(sampleID) {
  return request({
    url: '/detection/taskAssign/' + sampleID,
    method: 'delete'
  })
}

// 导出任务分派
export function exportTaskAssign(query) {
  return request({
    url: '/detection/taskAssign/export',
    method: 'get',
    params: query
  })
}

// 根据检品ID查询到为分派的任务
export function getUnsignedTaskByID(sampleID) {
  return request({
    url: '/detection/taskAssign/unsignedTask/'+sampleID,
    method: 'get'
  })
}

// 获取所有检测标准
export function getDetectionStandards() {
  return request({
    url: '/detection/commissionSample/detectionStandards',
    method: 'get'
  })
}

// 根据检品学名和检测ID获取检测项目
export function getAllDetectionItemList(sampleAcademicName,standardID) {
  return request({
    url: '/detection/taskAssign/detectionItemList?sampleAcademicName='+sampleAcademicName+'&standardID='+standardID,
    method: 'get'
  })
}

// 根据检品学名和检测标准ID确定检测项目列表
export function getDetectionItemIDAndName(academicName,standardID) {
  return request({
    url:'/detection/taskAssign/pesticideList?academicName='+academicName+'&standardID='+standardID,
    method: 'get'
  })
}

// 获取到所有的检测方法
export function getAllDetectionMethodList() {
  return request({
    url: '/detection/taskAssign/detectionMethodList',
    method: 'get'
  })
}

// 获取到所有的检测人员和检测方法
export function getAllInspectorList() {
  return request({
    url: '/detection/taskAssign/inspectorList',
    method: 'get'
  })
}

// 新增检测项目
export function addTask(data) {
  return request({
    url: '/detection/taskAssign/addTask',
    method: 'post',
    data: data
  })
}

// 查找检测方法和检测员
export function findMethodsAndInspectors(param) {
  return request({
    url: '/detection/taskAssign/methodsAndInspectors/'+param,
    method: 'get'
  })
}
