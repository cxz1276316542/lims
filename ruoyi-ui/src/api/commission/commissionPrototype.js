import request from '@/utils/request'

// 查询委托样品列表
export function listCommissionPrototype(query) {
  return request({
    url: '/commission/commissionPrototype/list',
    method: 'get',
    params: query
  })
}

// 查询委托样品详细
export function getCommissionPrototype(sampleID) {
  return request({
    url: '/commission/commissionPrototype/' + sampleID,
    method: 'get'
  })
}

// 新增委托样品
export function addCommissionPrototype(data) {
  return request({
    url: '/commission/commissionPrototype',
    method: 'post',
    data: data
  })
}

// 修改委托样品
export function updateCommissionPrototype(data) {
  return request({
    url: '/commission/commissionPrototype',
    method: 'put',
    data: data
  })
}

// 删除委托样品
export function delCommissionPrototype(sampleID) {
  return request({
    url: '/commission/commissionPrototype/' + sampleID,
    method: 'delete'
  })
}

// 导出委托样品
export function exportCommissionPrototype(query) {
  return request({
    url: '/commission/commissionPrototype/export',
    method: 'get',
    params: query
  })
}
//获取检测标准
export function getStandards() {
  return request({
    url: '/commission/commissionPrototype/standards',
    method: 'get',

  })
}
//获取计量单位
export function getUnits() {
  return request({
    url: '/commission/commissionPrototype/units',
    method: 'get',

  })
}
//提交委托
export function addEntrustFrom(data) {
  return request({
    url: '/commission/commissionPrototype/submit',
    method: 'post',
    data: data
  })
}
//获取上一次登记数据
export function getLastInput() {
  return request({
    url: '/commission/commissionPrototype/getLastInput',
    method: 'get',
  })
}
//根据学名进行搜索
export function getHistoryFrom(data) {
  return request({
    url: '/commission/commissionPrototype/getHistory?sampleName='+data,
    method: 'get',
    // data: data
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
// 获取所有的样品学名
export function getSampleAcademicNameList() {
  return request({
    url: '/detection/commissionSample/sampleAcademicName',
    method: 'get'
  })
}
// 获取客户信息
export function getCustomerData() {
  return request({
    url: '/commission/commissionPrototype/customerData',
    method: 'get'
  })
}
// 获取委托单编号
export function getOrderID() {
  return request({
    url: '/commission/commissionPrototype/orderID',
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
