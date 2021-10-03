import request from '@/utils/request'

// 查询检测结果列表
export function listDetectionResult(query) {
  return request({
    url: '/detection/detectionResult/list',
    method: 'get',
    params: query
  })
}

// 查询复检检测结果列表
export function listReDetectionResult(query) {
  return request({
    url: '/detection/detectionResult/reList',
    method: 'get',
    params: query
  })
}

// 查询检测结果详细
export function getDetectionResult(detectionResultID) {
  return request({
    url: '/detection/detectionResult/' + detectionResultID,
    method: 'get'
  })
}

// 查询检测结果详细
export function getReDetectionResult(detectionResultID) {
  return request({
    url: '/detection/detectionResult/getReDetectionResult' + detectionResultID,
    method: 'get'
  })
}

// 新增检测结果
export function addDetectionResult(data) {
  return request({
    url: '/detection/detectionResult',
    method: 'post',
    data: data
  })
}

// 修改检测结果
export function updateDetectionResult(data) {
  return request({
    url: '/detection/detectionResult',
    method: 'put',
    data: data
  })
}

// 删除检测结果
export function delDetectionResult(detectionResultID) {
  return request({
    url: '/detection/detectionResult/' + detectionResultID,
    method: 'delete'
  })
}

// 导出检测结果
export function exportDetectionResult(query) {
  return request({
    url: '/detection/detectionResult/export',
    method: 'get',
    params: query
  })
}

// 单行提交初检审核
export function submitReview(data) {
  return request({
    url: '/detection/detectionResult/submitReview',
    method: 'put',
    data: data
  })
}

//批量初检审核
export function submitBatchReview(data) {
  return request({
    url: '/detection/detectionResult/submitBatchReview',
    method: 'put',
    data: data
  })
}


// 单行提交复检审核
export function submitReReview(data) {
  return request({
    url: '/detection/detectionResult/submitReReview',
    method: 'put',
    data: data
  })
}


//批量复检审核
export function submitBatchReReview(data) {
  return request({
    url: '/detection/detectionResult/submitBatchReReview',
    method: 'put',
    data: data
  })
}


