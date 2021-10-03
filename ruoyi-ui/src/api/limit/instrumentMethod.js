import request from '@/utils/request'

// 查询检测方法-检测仪器关系列表
export function listInstrumentMethod(query) {
  return request({
    url: '/limit/instrumentMethod/list',
    method: 'get',
    params: query
  })
}

// 查询检测方法-检测仪器关系详细
export function getInstrumentMethod(instrumentDetectionMethodID) {
  return request({
    url: '/limit/instrumentMethod/' + instrumentDetectionMethodID,
    method: 'get'
  })
}

// 新增检测方法-检测仪器关系
export function addInstrumentMethod(data) {
  return request({
    url: '/limit/instrumentMethod',
    method: 'post',
    data: data
  })
}


// 是否存在检测方法-检测仪器关系
export function isExist(data) {
  return request({
    url: '/limit/instrumentMethod/isExist',
    method: 'post',
    data: data
  })
}


// 修改检测方法-检测仪器关系
export function updateInstrumentMethod(data) {
  return request({
    url: '/limit/instrumentMethod',
    method: 'put',
    data: data
  })
}

// 删除检测方法-检测仪器关系
export function delSingleInstrumentMethod(detectionMethodIDs) {
  return request({
    url: '/limit/instrumentMethod/single',
    method: 'delete',
    data: detectionMethodIDs
  })
}

// 删除检测方法-检测仪器关系
export function delInstrumentMethod(instrumentDetectionMethodIDs) {
  return request({
    url: '/limit/instrumentMethod/'+instrumentDetectionMethodIDs,
    method: 'delete'
  })
}

// 下载检测仪器-检测方法导入模板
export function importTemplate() {
  return request({
    url: '/limit/instrumentMethod/importTemplate',
    method: 'get'
  })
}

// 导出检测方法-检测仪器关系
export function exportInstrumentMethod(query) {
  return request({
    url: '/limit/instrumentMethod/export',
    method: 'get',
    params: query
  })
}

// 得到检测方法名
export function listDetectionMethodName() {
  return request({
    url: '/limit/instrumentMethod/detectionMethodName',
    method: 'get',
  })
}

