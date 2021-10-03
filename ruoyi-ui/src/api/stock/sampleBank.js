import request from '@/utils/request'

// 查询样品库列表
export function listSampleBank(query) {
  return request({
    url: '/stock/sampleBank/list',
    method: 'get',
    params: query
  })
}

// 查询检品信息
export function sampleBankInfo(sampleBankID) {
  return request({
    url: '/stock/sampleBank/getInfo/' + sampleBankID,
    method: 'get'
  })
}

// 获取所有的样品学名
export function getSampleScientificNameList() {
  return request({
    url: '/detection/commissionSample/sampleAcademicName',
    method: 'get'
  })
}

//批量销毁
export function batchAbandon(ids) {
  return request({
    url: '/stock/sampleBank/batchAbandon/'+ids,
    method: 'post',
  })
}

// 获取计量单位
export function getMeasureUnit() {
  return request({
    url: '/stock/sampleBank/getMeasureUnit',
    method: 'get'
  })
}

// 获取库存管理用户信息
export function getInventoryManagerList() {
  return request({
    url: '/stock/sampleBank/getInventoryManagerList',
    method: 'get'
  })
}

// 查询样品库详细
export function getSampleBank(sampleBankID) {
  return request({
    url: '/stock/sampleBank/info/' + sampleBankID,
    method: 'get'
  })
}

// 新增样品库
export function addSampleBank(data) {
  return request({
    url: '/stock/sampleBank',
    method: 'post',
    data: data
  })
}

// 修改样品库
export function updateSampleBank(data) {
  return request({
    url: '/stock/sampleBank',
    method: 'put',
    data: data
  })
}

// 删除样品库，退样
export function returnSampleBank(data) {
  return request({
    url: '/stock/sampleBank/returnTO',
    method: 'post',
    data: data
  })
}


// 回收样品库
export function recycleSampleBank(data) {
  return request({
    url: '/stock/sampleBank/recycle',
    method: 'post',
    data: data
  })
}

// 领用样品库
export function useSampleBank(data) {
  return request({
    url: '/stock/sampleBank/use',
    method: 'post',
    data: data
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
