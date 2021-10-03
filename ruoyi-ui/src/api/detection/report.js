import request from '@/utils/request'
import da from "element-ui/src/locale/lang/da";

// 查询检测报告打印邮寄列表
export function listReport(query) {
  return request({
    url: '/detection/report/list',
    method: 'get',
    params: query
  })
}

// 查询检测报告打印邮寄详细
export function getReport(reportID) {
  return request({
    url: '/detection/report/' + reportID,
    method: 'get'
  })
}

// 新增检测报告打印邮寄
export function addReport(data) {
  return request({
    url: '/detection/report',
    method: 'post',
    data: data
  })
}

// 修改检测报告打印邮寄
export function updateReport(data) {
  return request({
    url: '/detection/report',
    method: 'put',
    data: data
  })
}

// 删除检测报告打印邮寄
export function delReport(reportID) {
  return request({
    url: '/detection/report/' + reportID,
    method: 'delete'
  })
}

// 导出检测报告打印邮寄
export function exportReport(query) {
  return request({
    url: '/detection/report/export',
    method: 'get',
    params: query
  })
}

// 查询复检检测结果列表
export function selectPassList() {
  return request({
    url: '/detection/report/selectPassList',
    method: 'get'
  })
}

// 查询重新撰写结果列表
export function selectNoPassList() {
  return request({
    url: '/detection/report/selectNoPassList',
    method: 'get'
  })
}


// 查询复检检测结果列表
export function listPassDetectionResult(query) {
  return request({
    url: '/detection/report/listPassDetectionResult',
    method: 'get',
    params: query
  })
}





//生成报告
export function createReport(data) {
  return request({
    url: '/detection/report/createReport',
    method: 'put',
    data: data
  })
}
export function reCreateReport(data) {
  return request({
    url: '/detection/report/reCreateReport',
    method: 'put',
    data: data
  })
}



//查询报告
export function selectReport(data) {
  return request({
    url: '/detection/report/selectReport',
    method: 'put',
    data: data
  })
}

//签发通过
export function reportPass(data) {
  return request({
    url: '/detection/report/reportPass',
    method: 'post',
    data: data
  })
}

//打回撰写
export function reportFail(data) {
  return request({
    url: '/detection/report/reportFail',
    method: 'post',
    data: data
  })
}

// 查询该中心模板
export function selectAllTem(){
  return request({
    url: '/detection/report/selectAllTem',
    method: 'get'
  })
}
