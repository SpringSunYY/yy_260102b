import request from '@/utils/request'

export function passedStatistics(query){
    return request({
        url: '/manage/statistics/pass',
        method: 'get',
        params: query
    })
}
