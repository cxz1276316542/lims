import request from '@/utils/request'

// 查询未审核列表
export function noReviewInfo(query) {
  return request({
    url: '/review/resultReview/notReviewList',
    method: 'get',
    params: query
  })
}
//返回复检
export function failReturnToRecheck(data) {
  return request({
    url: '/review/resultReview/returnToRecheck',
    method: 'post',
    data:data
  })
}
//返回重新分派
export function returnToAssign(data) {
  return request({
    url: '/review/resultReview/returnToAssign',
    method: 'post',
    data:data
  })
}
// 查询已审核列表
export function ReviewInfo(query) {
  return request({
    url: '/review/resultReview/reviewedList',
    method: 'get',
    params: query
  })
}
// 查询检查结果信息
export function resultInfo(reviewID) {
  return request({
    url: '/review/resultReview/getInfo/' + reviewID,
    method: 'get'
  })
}


// 查询操作员列表
export function reviewerList(query) {
  return request({
    url: '/review/resultReview/reviewerList',
    method: 'get',
    params: query
  })
}
// 查询审核详细
export function getReviewed(reviewID) {
  return request({
    url: '/review/resultReview/getReviewedInfo/' + reviewID,
    method: 'get'
  })
}

// 新增审核
export function addReview(data) {
  return request({
    url: '/review/resultReview',
    method: 'post',
    data: data
  })
}

// 修改审核
export function updateReview(data) {
  return request({
    url: '/review/resultReview',
    method: 'post',
    data: data
  })
}

// 删除审核
export function delReview(reviewID) {
  return request({
    url: '/review/resultReview/' + reviewID,
    method: 'delete'
  })
}

// 导出审核
export function exportReview(query) {
  return request({
    url: '/review/resultReview/export',
    method: 'get',
    params: query
  })
}

//通过审核
export function passReview(data) {
  return request({
    url: '/review/resultReview/pass',
    method: 'post',
    data:data
  })
}
//批量审核通过
export function batchPass(ids,data) {
  return request({
    url: '/review/resultReview/batchPass/'+ids,
    method: 'post',
    data: data,
  })
}

//批量检验审核不通过,退回复检
export function batchReturnToRecheck(ids,data) {
  return request({
    url: '/review/resultReview/batchReturnToRecheck/'+ids,
    method: 'post',
    data: data,
  })
}

//批量检验审核不通过,重新分派
export function batchReturnToAssign(ids,data) {
  return request({
    url: '/review/resultReview/batchReturnToAssign/'+ids,
    method: 'post',
    data: data,
  })
}

