import request from '@/utils/request'

// 查询样品检测状态列表
export function listDetectionStatus(query) {
  return request({
    url: '/detection/detectionStatus/list',
    method: 'get',
    params: query
  })
}

// 查询样品检测状态详细
export function getDetectionStatus(sampleID) {
  return request({
    url: '/detection/detectionStatus/' + sampleID,
    method: 'get'
  })
}

// 新增样品检测状态
export function addDetectionStatus(data) {
  return request({
    url: '/detection/detectionStatus',
    method: 'post',
    data: data
  })
}

// 修改样品检测状态
export function updateDetectionStatus(data) {
  return request({
    url: '/detection/detectionStatus',
    method: 'put',
    data: data
  })
}

// 删除样品检测状态
export function delDetectionStatus(sampleID) {
  return request({
    url: '/detection/detectionStatus/' + sampleID,
    method: 'delete'
  })
}

// 导出样品检测状态
export function exportDetectionStatus(query) {
  return request({
    url: '/detection/detectionStatus/export',
    method: 'get',
    params: query
  })
}
