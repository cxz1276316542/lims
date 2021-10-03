import request from '@/utils/request'

// 查询未分配任务单分配列表
export function listTaskListAssign(query) {
  return request({
    url: '/detection/taskListAssign/taskAssignList',
    method: 'get',
    params: query
  })
}

// // 查询已分配任务单分配列表
// export function listTaskListAssign2(query) {
//   return request({
//     url: '/detection/taskListAssign/assignedList',
//     method: 'get',
//     params: query
//   })
// }

// 查询业务员列表
export function listSalesmanList(query) {
  return request({
    url: '/detection/taskListAssign/staffList',
    method: 'get',
    params: query
  })
}

// 查询任务单分配详细
export function getTaskListAssign(sampleID) {
  return request({
    url: '/detection/taskListAssign/' + sampleID,
    method: 'get'
  })
}

// 新增任务单分配
export function addTaskListAssign(data) {
  return request({
    url: '/detection/taskListAssign',
    method: 'post',
    data: data
  })
}

// 修改任务单分配
export function updateTaskListAssign(data) {
  return request({
    url: '/detection/taskListAssign',
    method: 'put',
    data: data
  })
}

// 删除任务单分配
export function delTaskListAssign(sampleID) {
  return request({
    url: '/detection/taskListAssign/' + sampleID,
    method: 'delete'
  })
}

// 导出任务单分配
export function exportTaskListAssign(query) {
  return request({
    url: '/detection/taskListAssign/export',
    method: 'get',
    params: query
  })
}
