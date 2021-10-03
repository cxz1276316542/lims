import request from '@/utils/request'

// 查询检测标准列表
export function listStandard(query) {
  return request({
    url: '/limit/standard/list',
    method: 'get',
    params: query
  })
}

// 查询检测标准详细
export function getStandard(standard) {
  return request({
    url: '/limit/standard/' + standard,
    method: 'get'
  })
}

// 新增检测标准
export function addStandard(data) {
  return request({
    url: '/limit/standard',
    method: 'post',
    data: data
  })
}

// 修改检测标准
export function updateStandard(data) {
  return request({
    url: '/limit/standard',
    method: 'put',
    data: data
  })
}

// 删除检测标准
export function delStandard(standard) {
  return request({
    url: '/limit/standard/' + standard,
    method: 'delete'
  })
}

// 导出检测标准
export function exportStandard(query) {
  return request({
    url: '/limit/standard/export',
    method: 'get',
    params: query
  })
}