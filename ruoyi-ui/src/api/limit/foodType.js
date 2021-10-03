import request from '@/utils/request'

// 查询食品类别管理列表
export function listFoodType(query) {
  return request({
    url: '/limit/foodType/list',
    method: 'get',
    params: query
  })
}

// 查询食品类别管理列表 不分页
export function listAllFoodType(query) {
  return request({
    url: '/limit/foodType/listAll',
    method: 'get',
    params: query
  })
}

// 查询食品类别管理详细
export function getFoodType(foodTypeID) {
  return request({
    url: '/limit/foodType/' + foodTypeID,
    method: 'get'
  })
}

// 新增食品类别管理
export function addFoodType(data) {
  return request({
    url: '/limit/foodType',
    method: 'post',
    data: data
  })
}

// 修改食品类别管理
export function updateFoodType(data) {
  return request({
    url: '/limit/foodType',
    method: 'put',
    data: data
  })
}

// 删除食品类别管理
export function delFoodType(foodTypeID) {
  return request({
    url: '/limit/foodType/' + foodTypeID,
    method: 'delete'
  })
}

// 导出食品类别管理
export function exportFoodType(query) {
  return request({
    url: '/limit/foodType/export',
    method: 'get',
    params: query
  })
}



