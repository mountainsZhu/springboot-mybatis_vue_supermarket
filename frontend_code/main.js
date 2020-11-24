import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import router from './router'
import store from './store'
import './plugins/element.js'
import './icon/iconfont.js' //引入阿里巴巴图标库js
import './icon/iconfont.css'//引入阿里巴巴图标库css
import qs from 'qs'

Vue.prototype.$qs = qs
Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
