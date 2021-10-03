import request from '@/utils/request'

// 查询检测方法-检测标准列表
export function listDetectionMethodStandard(query) {
  return request({
    url: '/limit/DetectionMethodStandard/list',
    method: 'get',
    params: query
  })
}

// 查询检测方法-检测标准详细
export function getDetectionMethodStandard(detectionMethodStandardID) {
  return request({
    url: '/limit/DetectionMethodStandard/' + detectionMethodStandardID,
    method: 'get'
  })
}

// 新增检测方法-检测标准
export function addDetectionMethodStandard(data) {
  return request({
    url: '/limit/DetectionMethodStandard',
    method: 'post',
    data: data
  })
}

// 修改检测方法-检测标准
export function updateDetectionMethodStandard(data) {
  return request({
    url: '/limit/DetectionMethodStandard',
    method: 'put',
    data: data
  })
}

// 删除检测方法-检测标准
export function delDetectionMethodStandard(detectionMethodStandardID) {
  return request({
    url: '/limit/DetectionMethodStandard/' + detectionMethodStandardID,
    method: 'delete'
  })
}

// 导出检测方法-检测标准
export function exportDetectionMethodStandard(query) {
  return request({
    url: '/limit/DetectionMethodStandard/export',
    method: 'get',
    params: query
  })
}