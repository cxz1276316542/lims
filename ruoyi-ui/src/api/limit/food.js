import request from '@/utils/request'

// 查询食品管理列表
export function listFood(query) {
  return request({
    url: '/limit/food/list',
    method: 'get',
    params: query
  })
}

// 查询食品管理详细
export function getFood(foodID) {
  return request({
    url: '/limit/food/' + foodID,
    method: 'get'
  })
}

// 新增食品管理
export function addFood(data) {
  return request({
    url: '/limit/food',
    method: 'post',
    data: data
  })
}

// 修改食品管理
export function updateFood(data) {
  return request({
    url: '/limit/food',
    method: 'put',
    data: data
  })
}

// 删除食品管理
export function delFood(foodTypeID) {
  return request({
    url: '/limit/food/' + foodTypeID,
    method: 'delete'
  })
}

// 导出食品管理
export function exportFood(query) {
  return request({
    url: '/limit/food/export',
    method: 'get',
    params: query
  })
}

// 下载用户导入模板
export function importTemplate() {
  return request({
    url: '/limit/food/importTemplate',
    method: 'get'
  })
}

