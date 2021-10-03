import request from '@/utils/request'

// 查询未审核列表
export function noReviewInfo(query) {
  return request({
    url: '/review/sampleReview/notReviewList',
    method: 'get',
    params: query
  })
}
// 查询已审核列表
export function ReviewInfo(query) {
  return request({
    url: '/review/sampleReview/reviewedList',
    method: 'get',
    params: query
  })
}
// 查询审核详细
export function getReviewed(reviewID) {
  return request({
    url: '/review/sampleReview/getReviewedInfo/' + reviewID,
    method: 'get'
  })
}

// 查询审核详细
export function getReviewInfo(reviewID) {
  return request({
    url: '/review/getReviewInfo/' + reviewID,
    method: 'get'
  })
}

// 新增审核
export function addReview(data) {
  return request({
    url: '/review/sampleReview',
    method: 'post',
    data: data
  })
}

// 修改审核
export function updateReview(data) {
  return request({
    url: '/review/sampleReview',
    method: 'post',
    data: data
  })
}

// 删除审核
export function delReview(reviewID) {
  return request({
    url: '/review/sampleReview/' + reviewID,
    method: 'delete'
  })
}

// 导出审核
export function exportReview(query) {
  return request({
    url: '/review/sampleReview/export',
    method: 'get',
    params: query
  })
}

// 查询操作员列表
export function reviewerList(query) {
  return request({
    url: '/review/sampleReview/reviewerList',
    method: 'get',
    params: query
  })
}

// 查询用户列表
export function userList(query) {
  return request({
    url: '/review/userList',
    method: 'get',
    params: query
  })
}

// 查询检品信息
export function sampleInfo(reviewID) {
  return request({
    url: '/review/sampleReview/getInfo/' + reviewID,
    method: 'get'
  })
}
//通过审核
export function passReview(data) {
  return request({
    url: '/review/sampleReview/pass',
    method: 'post',
    data:data
  })
}

//不通过审核
export function failReview(data) {
  return request({
    url: '/review/sampleReview/fail',
    method: 'post',
    data:data
  })
}

//不通过审核，退回业务员修改
export function failReturnToModify(data) {
  return request({
    url: '/review/sampleReview/returnToModify',
    method: 'post',
    data:data
  })
}

//不通过审核，退回委托修改
export function failReturnToCommissionModify(data) {
  return request({
    url: '/review/sampleReview/returnToCommissionModify',
    method: 'post',
    data:data
  })
}

//批量审核通过
export function batchPass(ids,data) {
  return request({
    url: '/review/sampleReview/batchPass/'+ids,
    method: 'post',
    data: data,
  })
}

//批量审核不通过，退回重新送样
export function batchFailReview(ids,data) {
  return request({
    url: '/review/sampleReview/batchFailSampleReview/'+ids,
    method: 'post',
    data: data,
  })
}

//批量检品审核不通过,退回业务员修改
export function batchReturnToModify(ids,data) {
  return request({
    url: '/review/sampleReview/batchReturnToModify/'+ids,
    method: 'post',
    data: data,
  })
}

//批量检品审核退回委托修改,返回客户修改
export function batchReturnToCommissionModify(ids,data) {
  return request({
    url: '/review/sampleReview/batchReturnToCommissionModify/'+ids,
    method: 'post',
    data: data,
  })
}

// 获取电子签名
export function getUserElectronicSignature() {
  return request({
    url: '/review/getUserElectronicSignature',
    method: 'get',
  })
}

//上传电子签名
export function uploadSignature(data) {
  return request({
    url: '/review/uploadSignature',
    method: 'post',
    data: data,
  })
}
