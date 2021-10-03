import request from '@/utils/request'

// 查询检测方法管理列表
export function listDetectionMethod(query) {
  return request({
    url: '/limit/detectionMethod/list',
    method: 'get',
    params: query
  })
}

// 查询检测方法管理详细
export function getDetectionMethod(detectionMethodID) {
  return request({
    url: '/limit/detectionMethod/' + detectionMethodID,
    method: 'get'
  })
}

// 是否存在检测方法
export function isExistDetectionMethod(data) {
  return request({
    url: '/limit/detectionMethod/isExistDetectionMethod',
    method: 'post',
    data: data
  })
}

// 新增检测方法管理
export function addDetectionMethod(data) {
  return request({
    url: '/limit/detectionMethod',
    method: 'post',
    data: data
  })
}

// 修改检测方法管理
export function updateDetectionMethod(data) {
  return request({
    url: '/limit/detectionMethod',
    method: 'put',
    data: data
  })
}

// 删除检测方法管理
export function delDetectionMethod(detectionMethodID) {
  return request({
    url: '/limit/detectionMethod/' + detectionMethodID,
    method: 'delete'
  })
}

// 导出检测方法管理
export function exportDetectionMethod(query) {
  return request({
    url: '/limit/detectionMethod/export',
    method: 'get',
    params: query
  })
}

// 下载用户导入模板
export function importTemplate() {
  return request({
    url: '/limit/detectionMethod/importTemplate',
    method: 'get'
  })
}

