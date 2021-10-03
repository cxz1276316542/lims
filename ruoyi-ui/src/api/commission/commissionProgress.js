import request from '@/utils/request'

// 查询委托进度列表
export function listCommissionProgress(query) {
  return request({
    url: '/commission/commissionProgress/list',
    method: 'get',
    params: query
  })
}

// 查询委托进度_查看详情
export function getCommissionProgress(sampleID) {
  return request({
    url: '/commission/commissionProgress/' + sampleID,
    method: 'get'
  })
}
// 查询委托进度——进度条
export function getProgress(sampleID) {
  return request({
    url: '/commission/commissionProgress/getProgress?sampleId=' + sampleID,
    method: 'get'
  })
}

// 新增委托进度
export function addCommissionProgress(data) {
  return request({
    url: '/commission/commissionProgress',
    method: 'post',
    data: data
  })
}

// 修改委托进度
export function updateCommissionProgress(data) {
  return request({
    url: '/commission/commissionProgress',
    method: 'put',
    data: data
  })
}

// 删除委托进度
export function delCommissionProgress(sampleID) {
  return request({
    url: '/commission/commissionProgress/' + sampleID,
    method: 'delete'
  })
}

// 导出委托进度
export function exportCommissionProgress(query) {
  return request({
    url: '/commission/commissionProgress/export',
    method: 'get',
    params: query
  })
}

export function getOrderProgress(sampleID) {
  return request({
    url: '/commission/commissionProgress/getOrderProgress?sampleId=' + sampleID,
    method: 'get'
  })
}
