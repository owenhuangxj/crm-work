/**
 * 登录相关接口
 */
import request from '@/utils/request'

export default {

  loginAction(username, password) {
    const data = {
      username,
      password
    };
    return request({
      url: '/auth/login',
      method: 'post',
      data
    })
  },

  logout() {
    return request({
      url: '/auth/logout',
      method: 'post'
    })
  },

  getUserInfo(token) {
    return request({
      url: '/auth/info',
      method: 'get',
      params: { token }
    })
  },

}

