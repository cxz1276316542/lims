import request from '@/utils/request'

// 查询委托样品列表
export function listCommissionSample(query) {
  return request({
    url: '/detection/commissionSample/list',
    method: 'get',
    params: query
  })
}

// 查询委托样品列表
export function listCommissionSampleHistory(query) {
  return request({
    url: '/detection/commissionSample/history/list',
    method: 'get',
    params: query
  })
}

// 查询委托样品详细
export function getCommissionSample(sampleID) {
  return request({
    url: '/detection/commissionSample/' + sampleID,
    method: 'get'
  })
}

// 新增委托样品
export function addCommissionSample(data) {
  return request({
    url: '/detection/commissionSample',
    method: 'post',
    data: data
  })
}

// 修改委托样品
export function updateCommissionSample(data) {
  return request({
    url: '/detection/commissionSample',
    method: 'put',
    data: data
  })
}

// 删除委托样品
export function delCommissionSample(sampleID) {
  return request({
    url: '/detection/commissionSample/' + sampleID,
    method: 'delete'
  })
}

// 导出委托样品
export function exportCommissionSample(query) {
  return request({
    url: '/detection/commissionSample/export',
    method: 'get',
    params: query
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

// 获取所有检测标准
export function getDetectionStandards() {
  return request({
    url: '/detection/commissionSample/detectionStandards',
    method: 'get'
  })
}

// 获取所有的样品学名
export function getSampleAcademicNameList() {
  return request({
    url: '/detection/commissionSample/sampleAcademicName',
    method: 'get'
  })
}

// 批量提交
export function submitSampleBatch(data) {
  return request({
    url: '/detection/commissionSample/submit',
    method: 'post',
    data: data
  })
}

// 查找有效计量单位
export function findValidMeasureUnit() {
  return request({
    url: '/detection/commissionSample/measureUnits',
    method: 'get',
  })
}

// 查询所有客户
export function searchAllCustomers(){
  return request({
    url: '/detection/commissionSample/customers',
    method: 'get'
  })
}

// 根据客户ID查询到所有企业
export function searchAllCompanies(param) {
  return request({
    url: '/detection/commissionSample/companies/'+param,
    method: 'get'
  })
}

// 根据客户ID查询到最后一条记录
export function searchLastRecord(param) {
  return request({
    url: '/detection/commissionSample/lastRecord/'+param,
    method: 'get'
  })
}

// 通过检品名称查找相对应的检测项目ID和名称
export function findIDAndName(param) {
  return request({
    url: '/detection/commissionSample/findItemList?sampleScientificName='+param,
    method: 'get',
  })
}
