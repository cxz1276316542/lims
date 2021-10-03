import request from '@/utils/request'

// 查询检测项目-检测方法列表
export function listPesticideDetectionMethod(query) {
  return request({
    url: '/limit/pesticideDetectionMethod/list',
    method: 'get',
    params: query
  })
}

// 查询检测项目-检测方法详细
export function getPesticideDetectionMethod(pesticideDetectionMethodID) {
  return request({
    url: '/limit/pesticideDetectionMethod/' + pesticideDetectionMethodID,
    method: 'get'
  })
}

// 新增检测项目-检测方法
export function addPesticideDetectionMethod(data) {
  return request({
    url: '/limit/pesticideDetectionMethod',
    method: 'post',
    data: data
  })
}

// 修改检测项目-检测方法
export function updatePesticideDetectionMethod(data) {
  return request({
    url: '/limit/pesticideDetectionMethod',
    method: 'put',
    data: data
  })
}

// 删除检测项目-检测方法
export function delPesticideDetectionMethod(pesticideDetectionMethodID) {
  return request({
    url: '/limit/pesticideDetectionMethod/' + pesticideDetectionMethodID,
    method: 'delete'
  })
}

// 导出检测项目-检测方法
export function exportPesticideDetectionMethod(query) {
  return request({
    url: '/limit/pesticideDetectionMethod/export',
    method: 'get',
    params: query
  })
}