import request from '@/utils/request'

// 查询食品类别下拉树结构
export function treeselect() {
  return request({
    url: '/limit/limitInfo/foodtypetree',
    method: 'get'
  })
}


// 查询限量库详细信息列表
export function listLimitInfo(query) {
  return request({
    url: '/limit/limitInfo/list',
    method: 'get',
    params: query
  })
}

// 查询限量库详细信息详细
export function getLimitInfo(detectionStandardID) {
  return request({
    url: '/limit/limitInfo/' + detectionStandardID,
    method: 'get'
  })
}



// 导出限量库详细信息
export function exportLimitInfo(query) {
  return request({
    url: '/limit/limitInfo/export',
    method: 'get',
    params: query
  })
}

//根据食品信息查询限量库信息
export function listLimitInfoByFood(query) {
  return request({
    url:'/limit/limitInfo/listByFood',
    method:'get',
    params:query
  })
}

//根据食品信息查询限量库信息
export function listLimitInfoByPesticide(query) {
  return request({
    url:'/limit/limitInfo/listByPesticide',
    method:'get',
    params:query
  })
}
