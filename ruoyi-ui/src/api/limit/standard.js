import request from '@/utils/request'
import request2 from '@/utils/request2'

// 查询检测标准列表
export function listStandard(query) {
  return request({
    url: '/limit/standard/list',
    method: 'get',
    params: query
  })
}

// 查询检测标准map
export function mapStandard(query) {
  return request({
    url: '/limit/standard/map',
    method: 'get',
    params: query
  })
}

// 查询检测标准详细
export function getStandard(standard) {
  return request({
    url: '/limit/standard/' + standard,
    method: 'get'
  })
}

// 新增检测标准
export function addStandard(data) {
  return request({
    url: '/limit/standard',
    method: 'post',
    data: data
  })
}

// 修改检测标准
export function updateStandard(data) {
  return request({
    url: '/limit/standard',
    method: 'put',
    data: data
  })
}

// 删除检测标准
export function delStandard(standard) {
  return request({
    url: '/limit/standard/' + standard,
    method: 'delete'
  })
}

//导入检测标准相关数据

// export function importLimitInfo(query) {
//   return request({
//     url:'/limit/standard/importLimitInfo',
//     method: 'get',
//     params:query
//   })
// }

// 导出检测标准
export function exportStandard(query) {
  return request({
    url: '/limit/standard/export',
    method: 'get',
    params: query
  })
}

//下载限量库明细导入模板
export function importTemplate() {
  return request({
    url: '/limit/standard/importTemplate',
    method: 'get'
  })
}

//导出某检测标准的限量库明细
export function exportLimitInfo(standardNum) {
  return request2({
    url:'/limit/standard/exportLimitInfo',
    method:'get',
    params:standardNum
  })
}
