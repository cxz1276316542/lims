import request from '@/utils/request'

// 查询样品入库列表
export function listStockIn(query) {
  return request({
    url: '/stock/stockIn/list',
    method: 'get',
    params: query
  })
}

// 查询样品出入库详细
export function getStockIn(entryAndOutID) {
  return request({
    url: '/stock/stockIn/' + entryAndOutID,
    method: 'get'
  })
}

// 新增样品入库
export function addStockIn(data) {
  return request({
    url: '/stock/sampleBank/addSample',
    method: 'post',
    data: data
  })
}

// 修改样品出入库
export function updateStockIn(data) {
  return request({
    url: '/stock/stockIn',
    method: 'put',
    data: data
  })
}

// 删除样品出入库
export function delStockIn(entryAndOutID) {
  return request({
    url: '/stock/stockIn/' + entryAndOutID,
    method: 'delete'
  })
}

// 导出样品出入库
export function exportStockIn(query) {
  return request({
    url: '/stock/stockIn/export',
    method: 'get',
    params: query
  })
}
