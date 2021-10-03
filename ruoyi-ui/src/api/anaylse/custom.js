import request from '@/utils/request'

// 获取一周委托数统计
export function listWeekData() {
  return request({
    url: '/analyse/custom/weekday',
    method: 'get',
  })
}
