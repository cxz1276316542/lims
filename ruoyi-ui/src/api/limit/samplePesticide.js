import request from '@/utils/request'

// 查询样品-检测项目列表
export function listSamplePesticide(query) {
  return request({
    url: '/limit/samplePesticide/list',
    method: 'get',
    params: query
  })
}

// 查询样品-检测项目详细
export function getSamplePesticide(samplePesticideID) {
  return request({
    url: '/limit/samplePesticide/' + samplePesticideID,
    method: 'get'
  })
}

// 新增样品-检测项目
export function addSamplePesticide(data) {
  return request({
    url: '/limit/samplePesticide',
    method: 'post',
    data: data
  })
}

// 修改样品-检测项目
export function updateSamplePesticide(data) {
  return request({
    url: '/limit/samplePesticide',
    method: 'put',
    data: data
  })
}

// 删除样品-检测项目
export function delSamplePesticide(samplePesticideID) {
  return request({
    url: '/limit/samplePesticide/' + samplePesticideID,
    method: 'delete'
  })
}

// 导出样品-检测项目
export function exportSamplePesticide(query) {
  return request({
    url: '/limit/samplePesticide/export',
    method: 'get',
    params: query
  })
}