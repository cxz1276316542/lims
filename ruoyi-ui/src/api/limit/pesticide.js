import request from '@/utils/request'

// 查询检测农药管理列表
export function listPesticide(query) {
  return request({
    url: '/limit/pesticide/list',
    method: 'get',
    params: query
  })
}

//查询农药豁免清单
export  function listExemptionList(query) {
  return request({
    url:'/limit/pesticide/exemption',
    method:'get',
    params:query
  })
}

// 查询检测农药管理详细
export function getPesticide(pesticideID) {
  return request({
    url: '/limit/pesticide/' + pesticideID,
    method: 'get'
  })
}

// 新增检测农药管理
export function addPesticide(data) {
  return request({
    url: '/limit/pesticide',
    method: 'post',
    data: data
  })
}

// 修改检测农药管理
export function updatePesticide(data) {
  return request({
    url: '/limit/pesticide',
    method: 'put',
    data: data
  })
}

// 删除检测农药管理
export function delPesticide(pesticideID) {
  return request({
    url: '/limit/pesticide/' + pesticideID,
    method: 'delete'
  })
}

// 导出检测农药管理
export function exportPesticide(query) {
  return request({
    url: '/limit/pesticide/export',
    method: 'get',
    params: query
  })
}

// 下载用户导入模板
export function importTemplate() {
  return request({
    url: '/limit/pesticide/importTemplate',
    method: 'get'
  })
}
