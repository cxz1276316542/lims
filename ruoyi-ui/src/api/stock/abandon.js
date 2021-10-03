import request from '@/utils/request'

// 查询废弃样品库列表
export function listSampleBank(query) {
  return request({
    url: '/stock/sampleBank/abandonList',
    method: 'get',
    params: query
  })
}

// 查询废弃样品库详细
export function getSampleBank(sampleBankID) {
  return request({
    url: '/stock/sampleBank/' + sampleBankID,
    method: 'get'
  })
}

// 废弃样品
export function abandonSampleBank(data) {
  return request({
    url: '/stock/sampleBank/abandon',
    method: 'post',
    data: data
  })
}

// 删除样品库
export function delSampleBank(sampleBankID) {
  return request({
    url: '/stock/sampleBank/' + sampleBankID,
    method: 'delete'
  })
}

// 导出样品库
export function exportSampleBank(query) {
  return request({
    url: '/stock/sampleBank/export',
    method: 'get',
    params: query
  })
}
