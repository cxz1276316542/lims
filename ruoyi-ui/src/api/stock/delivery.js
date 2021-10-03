import request from '@/utils/request'

// 查询样品出入库列表
export function listEntryAndOut(query) {
  return request({
    url: '/stock/delivery/list',
    method: 'get',
    params: query
  })
}

// 查询样品出入库详细
export function getEntryAndOut(entryAndOutID) {
  return request({
    url: '/stock/delivery/' + entryAndOutID,
    method: 'get'
  })
}

// 新增样品出入库
export function addEntryAndOut(data) {
  return request({
    url: '/stock/delivery',
    method: 'post',
    data: data
  })
}

// 修改样品出入库
export function updateEntryAndOut(data) {
  return request({
    url: '/stock/delivery',
    method: 'post',
    data: data
  })
}

// 删除样品出入库
export function delEntryAndOut(entryAndOutID) {
  return request({
    url: '/stock/delivery/' + entryAndOutID,
    method: 'delete'
  })
}

// 导出样品出入库
export function exportEntryAndOut(query) {
  return request({
    url: '/stock/delivery/export',
    method: 'get',
    params: query
  })
}
