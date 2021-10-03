import request from '@/utils/request'

// 查询审核列表
export function listReview(query) {
  return request({
    url: '/review/reportReview/list',
    method: 'get',
    params: query
  })
}

//通过审核
export function passReview(data) {
  return request({
    url: '/review/reportReview/pass',
    method: 'post',
    data:data
  })
}

//不通过审核，退回撰写人
export function failReturnToWriter(data) {
  return request({
    url: '/review/reportReview/returnToWriter',
    method: 'post',
    data:data
  })
}

//不通过审核，重新分派
export function returnToAssign(data) {
  return request({
    url: '/review/reportReview/returnToAssign',
    method: 'post',
    data:data
  })
}

//不通过审核，重新检测
export function returnToRecheck(data) {
  return request({
    url: '/review/reportReview/returnToRecheck',
    method: 'post',
    data:data
  })
}

// 查询未审核列表
export function noReviewInfo(query) {
  return request({
    url: '/review/reportReview/notReviewList',
    method: 'get',
    params: query
  })
}
// 查询已审核列表
export function ReviewInfo(query) {
  return request({
    url: '/review/reportReview/reviewedList',
    method: 'get',
    params: query
  })
}

// 查询审核详细
export function getReview(reviewID) {
  return request({
    url: '/review/reportReview/getInfo/' + reviewID,
    method: 'get'
  })
}

// 查询审已核详细
export function getReviewed(reviewID) {
  return request({
    url: '/review/reportReview/getReviewedInfo/' + reviewID,
    method: 'get'
  })
}

// 新增审核
export function addReview(data) {
  return request({
    url: '/review/reportReview',
    method: 'post',
    data: data
  })
}

// 修改审核
export function updateReview(data) {
  return request({
    url: '/review/reportReview',
    method: 'put',
    data: data
  })
}

// 删除审核
export function delReview(reviewID) {
  return request({
    url: '/review/reportReview/' + reviewID,
    method: 'delete'
  })
}

// 导出审核
export function exportReview(query) {
  return request({
    url: '/review/reportReview/export',
    method: 'get',
    params: query
  })
}

// 获取库存管理用户信息
export function getWriterList() {
  return request({
    url: '/review/reportReview/getReportWriterList',
    method: 'get'
  })
}

//批量报告审核不通过,退回重新分派
export function batchReturnToAssign(ids,data) {
  return request({
    url: '/review/reportReview/batchReturnToAssign/'+ids,
    method: 'post',
    data: data,
  })
}

//批量报告审核不通过,退回报告撰写人
export function batchReturnToWriter(ids,data) {
  return request({
    url: '/review/reportReview/batchReturnToWriter/'+ids,
    method: 'post',
    data: data,
  })
}

//批量报告审核不通过,退回复检
export function batchReturnToRecheck(ids,data) {
  return request({
    url: '/review/reportReview/batchReturnToRecheck/'+ids,
    method: 'post',
    data: data,
  })
}

//批量审核通过
export function batchPass(ids,data) {
  return request({
    url: '/review/reportReview/batchPass/'+ids,
    method: 'post',
    data: data,
  })
}
