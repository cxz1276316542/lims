import request from '@/utils/request'

// 查询检测员-检测项目列表
export function listInspectorPesticide(query) {
  return request({
    url: '/limit/inspectorPesticide/list',
    method: 'get',
    params: query
  })
}

// 查询检测员-检测项目详细
export function getInspectorPesticide(inspectorPesticideID) {
  return request({
    url: '/limit/inspectorPesticide/' + inspectorPesticideID,
    method: 'get'
  })
}

// 查询检测员名字列表
export function listInspectorName() {
  return request({
    url: '/system/user/listInspector',
    method: 'get'
  })
}

// 新增检测员-检测项目
export function addInspectorPesticide(data) {
  return request({
    url: '/limit/inspectorPesticide',
    method: 'post',
    data: data
  })
}

// 修改检测员-检测项目
export function updateInspectorPesticide(data) {
  return request({
    url: '/limit/inspectorPesticide',
    method: 'put',
    data: data
  })
}

// 删除检测员-检测项目
export function delInspectorPesticide(inspectorPesticideID) {
  return request({
    url: '/limit/inspectorPesticide/' + inspectorPesticideID,
    method: 'delete'
  })
}

// 导出检测员-检测项目
export function exportInspectorPesticide(query) {
  return request({
    url: '/limit/inspectorPesticide/export',
    method: 'get',
    params: query
  })
}
