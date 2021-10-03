import request from '@/utils/request'

// 查询任务分派列表
export function listTaskAssign(query) {
  return request({
    url: '/detection/taskAssign/list',
    method: 'get',
    params: query
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

// 查找任务分派内容VO
export function getTaskAssignListVO(query) {
  return request({
    url: '/detection/taskAssign/listVO',
    method: 'get',
    params: query
  })
}

// 查找检测农药ID和检测农药名称
export function getPesticideIDAndName() {
  return request({
    url: '/detection/taskAssign/pesticideListOption',
    method: 'get',
  })
}

// 查找检测方法ID和检测方法名称
export function getDetectionMethodIDAndName() {
  return request({
    url: '/detection/taskAssign/detectionMethodList',
    method: 'get'
  })
}

// 查找所有的检测员ID和名称
export function getInspectorIDAndName() {
  return request({
    url: '/detection/taskAssign/inspectorList',
    method:'get'
  })
}

// 查找所有的业务员员ID和名称
export function getServicerIDAndName() {
  return request({
    url: '/detection/taskAssign/servicerList',
    method:'get'
  })
}
