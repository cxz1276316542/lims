import request from '@/utils/request'

// 查询任务分派列表
export function listTaskAssign(query) {
  return request({
    url: '/detection/taskAssign/list',
    method: 'get',
    params: query
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
