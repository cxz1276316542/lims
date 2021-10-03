import request from '@/utils/request'

// 查询委托样品列表
export function listCommissionPrototype(query) {
  return request({
    url: '/commission/commissionManage/list',
    method: 'get',
    params: query
  })
}

// 查询委托样品详细
export function getCommissionPrototype(sampleID) {
  return request({
    url: '/commission/commissionManage/' + sampleID,
    method: 'get'
  })
}

// 新增委托样品
export function addCommissionPrototype(data) {
  return request({
    url: '/commission/commissionManage',
    method: 'post',
    data: data
  })
}

// 修改委托样品
export function updateCommissionPrototype(data) {
  return request({
    url: '/commission/commissionManage',
    method: 'put',
    data: data
  })
}

// 删除委托样品
export function delCommissionPrototype(sampleID) {
  return request({
    url: '/commission/commissionManage/' + sampleID,
    method: 'delete'
  })
}

// 导出委托样品
export function exportCommissionPrototype(query) {
  return request({
    url: '/commission/commissionManage/export',
    method: 'get',
    params: query
  })
}
//获取检测标准
export function getStandards() {
  return request({
    url: '/commission/commissionPrototype/standards',
    method: 'get',

  })
}
//获取计量单位
export function getUnits() {
  return request({
    url: '/commission/commissionPrototype/units',
    method: 'get',

  })
}
//查询委托意见
export function getOpinion(sampleID) {
  return request({
    url: '/commission/commissionManage/getOpinion?sampleId=' + sampleID,
    method: 'get'
  })
}

