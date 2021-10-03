import request from '@/utils/request'

// 查询检测仪器管理列表
export function listInstrument(query) {
  return request({
    url: '/limit/instrument/list',
    method: 'get',
    params: query
  })
}

// 查询检测仪器管理详细
export function getInstrument(instrumentID) {
  return request({
    url: '/limit/instrument/' + instrumentID,
    method: 'get'
  })
}

// 新增检测仪器管理
export function addInstrument(data) {
  return request({
    url: '/limit/instrument',
    method: 'post',
    data: data
  })
}

// 修改检测仪器管理
export function updateInstrument(data) {
  return request({
    url: '/limit/instrument',
    method: 'put',
    data: data
  })
}

// 删除检测仪器管理
export function delInstrument(instrumentID) {
  return request({
    url: '/limit/instrument/' + instrumentID,
    method: 'delete'
  })
}

// 导出检测仪器管理
export function exportInstrument(query) {
  return request({
    url: '/limit/instrument/export',
    method: 'get',
    params: query
  })
}

// 下载检测仪器导入模板
export function importTemplate() {
  return request({
    url: '/limit/instrument/importTemplate',
    method: 'get'
  })
}



