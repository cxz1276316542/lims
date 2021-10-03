import request from '@/utils/request'

// 查询报告模板列表
export function listReportTemplate(query) {
  return request({
    url: '/detection/reportTemplate/list',
    method: 'get',
    params: query
  })
}

// 查询报告模板详细
export function getReportTemplate(reportTemID) {
  return request({
    url: '/detection/reportTemplate/' + reportTemID,
    method: 'get'
  })
}

// 新增报告模板
export function addReportTemplate(data) {
  return request({
    url: '/detection/reportTemplate',
    method: 'post',
    data: data
  })
}

// 修改报告模板
export function updateReportTemplate(data) {
  return request({
    url: '/detection/reportTemplate',
    method: 'put',
    data: data
  })
}

// 删除报告模板
export function delReportTemplate(reportTemID) {
  return request({
    url: '/detection/reportTemplate/' + reportTemID,
    method: 'delete'
  })
}

// 导出报告模板
export function exportReportTemplate(query) {
  return request({
    url: '/detection/reportTemplate/export',
    method: 'get',
    params: query
  })
}

// 查询所有检验中心
export function laboratoryies(){
  return request({
    url: '/detection/reportTemplate/laboratoryies',
    method: 'get'
  })
}

// 上传样品图片
export function uploadSampleImage(data){
  return request({
    url: '/detection/commissionSample/sample',
    method: 'post',
    data: data
  })
}
