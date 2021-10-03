import request from '@/utils/request'

// 查询未审核列表
export function notReviewList(query) {
  return request({
    url: '/review/assignReview/notReviewList',
    method: 'get',
    params: query
  })
}

// 查询操作员列表
export function reviewerList(query) {
  return request({
    url: '/review/assignReview/reviewerList',
    method: 'get',
    params: query
  })
}

// 查询已审核列表
export function ReviewInfo(query) {
  return request({
    url: '/review/assignReview/reviewedList',
    method: 'get',
    params: query
  })
}

// 查询分派信息
export function assignInfo(reviewID) {
  return request({
    url: '/review/assignReview/getInfo/' + reviewID,
    method: 'get'
  })
}

// 查询已审核详细
export function getReviewed(reviewID) {
  return request({
    url: '/review/sampleReview/getReviewedInfo/' + reviewID,
    method: 'get'
  })
}

//审核通过
export function pass(data) {
  return request({
    url: '/review/assignReview/pass',
    method: 'post',
    data: data
  })
}

//审核不通过
export function fail(data) {
  return request({
    url: '/review/assignReview/fail',
    method: 'post',
    data: data
  })
}

// 新增审核
export function addReview(data) {
  return request({
    url: '/review/assignReview',
    method: 'post',
    data: data
  })
}

// 修改审核
export function updateReview(data) {
  return request({
    url: '/review/assignReview',
    method: 'post',
    data: data
  })
}

// 删除审核
export function delReview(reviewID) {
  return request({
    url: '/review/assignReview/' + reviewID,
    method: 'delete'
  })
}

// 导出审核
export function exportReview(query) {
  return request({
    url: '/review/assignReview/export',
    method: 'get',
    params: query
  })
}

//批量审核通过
export function batchPass(ids,data) {
  return request({
    url: '/review/assignReview/batchPass/'+ids,
    method: 'post',
    data: data,
  })
}

//批量分派审核不通过,重新分派
export function batchFailAssignReview(ids,data) {
  return request({
    url: '/review/assignReview/batchFailAssignReview/'+ids,
    method: 'post',
    data: data,
  })
}
