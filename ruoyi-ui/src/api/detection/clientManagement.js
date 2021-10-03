import request from '@/utils/request'

// 查询客户信息列表
export function listClientManagement(query) {
  return request({
    url: '/detection/clientManagement/list',
    method: 'get',
    params: query
  })
}

// 查询客户信息详细
export function getClientManagement(userId) {
  return request({
    url: '/detection/clientManagement/' + userId,
    method: 'get'
  })
}

// 新增客户信息
export function addClientManagement(data) {
  return request({
    url: '/detection/clientManagement',
    method: 'post',
    data: data
  })
}

// 修改客户信息
export function updateClientManagement(data) {
  return request({
    url: '/detection/clientManagement',
    method: 'put',
    data: data
  })
}

// 删除客户信息
export function delClientManagement(userId) {
  return request({
    url: '/detection/clientManagement/' + userId,
    method: 'delete'
  })
}

// 导出客户信息
export function exportClientManagement(query) {
  return request({
    url: '/detection/clientManagement/export',
    method: 'get',
    params: query
  })
}