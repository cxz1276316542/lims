import request from '@/utils/request'

// 查询编号生成列表
export function listNumber(query) {
  return request({
    url: '/system/number/list',
    method: 'get',
    params: query
  })
}

// 查询编号生成详细
export function getNumber(numberID) {
  return request({
    url: '/system/number/' + numberID,
    method: 'get'
  })
}

// 新增编号生成
export function addNumber(data) {
  return request({
    url: '/system/number',
    method: 'post',
    data: data
  })
}

// 修改编号生成
export function updateNumber(data) {
  return request({
    url: '/system/number',
    method: 'put',
    data: data
  })
}

// 删除编号生成
export function delNumber(numberID) {
  return request({
    url: '/system/number/' + numberID,
    method: 'delete'
  })
}

// 导出编号生成
export function exportNumber(query) {
  return request({
    url: '/system/number/export',
    method: 'get',
    params: query
  })
}

// 编号生成
export function findAllContent() {
  return request({
    url: '/system/number/searchAll',
    method: 'get'
  })
}

// 根据编号类型查找所有的编号字段
export function findContentListByNumberType(param) {
  return request({
    url: '/system/number/numberType/'+param,
    method: 'get'
  })
}
