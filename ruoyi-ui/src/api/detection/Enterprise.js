import request from '@/utils/request'

// 查询企业信息列表
export function listEnterprise(query) {
  return request({
    url: '/detection/Enterprise/list',
    method: 'get',
    params: query
  })
}

// 查询企业信息详细
export function getEnterprise(enterpriseID) {
  return request({
    url: '/detection/Enterprise/' + enterpriseID,
    method: 'get'
  })
}

// 新增企业信息
export function addEnterprise(data) {
  return request({
    url: '/detection/Enterprise',
    method: 'post',
    data: data
  })
}

// 修改企业信息
export function updateEnterprise(data) {
  return request({
    url: '/detection/Enterprise',
    method: 'put',
    data: data
  })
}

// 删除企业信息
export function delEnterprise(enterpriseID) {
  return request({
    url: '/detection/Enterprise/' + enterpriseID,
    method: 'delete'
  })
}

// 导出企业信息
export function exportEnterprise(query) {
  return request({
    url: '/detection/Enterprise/export',
    method: 'get',
    params: query
  })
}