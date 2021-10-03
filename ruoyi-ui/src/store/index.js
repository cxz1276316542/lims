import Vue from 'vue'
import Vuex from 'vuex'
import app from './modules/app'
import user from './modules/user'
import tagsView from './modules/tagsView'
import permission from './modules/permission'
import settings from './modules/settings'
import getters from './getters'

Vue.use(Vuex)

const store = new Vuex.Store({
  modules: {
    app,
    user,
    tagsView,
    permission,
    settings
  },
  state:{
    //服务器：http://47.106.113.181:8080;本地：http://localhost:8080
    //baseUrl:'http://localhost:8080/profile/upload/pdf/'
    // baseUrl:'http://47.106.113.181:8080/profile/upload/pdf/'
    baseUrl:'https://lims.anluyun.com/pdf/'
  },
  getters
})

export default store
