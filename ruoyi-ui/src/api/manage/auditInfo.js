import request from '@/utils/request'

// 查询审核信息列表
export function listAuditInfo(query) {
  return request({
    url: '/manage/auditInfo/list',
    method: 'get',
    params: query
  })
}

// 查询审核信息详细
export function getAuditInfo(auditId) {
  return request({
    url: '/manage/auditInfo/' + auditId,
    method: 'get'
  })
}

// 新增审核信息
export function addAuditInfo(data) {
  return request({
    url: '/manage/auditInfo',
    method: 'post',
    data: data
  })
}

// 修改审核信息
export function updateAuditInfo(data) {
  return request({
    url: '/manage/auditInfo',
    method: 'put',
    data: data
  })
}

// 删除审核信息
export function delAuditInfo(auditId) {
  return request({
    url: '/manage/auditInfo/' + auditId,
    method: 'delete'
  })
}
