import Vue from 'vue'
import App from './App.vue'
import router from './router'

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

import Scene from "@/components/welcome/Scene.vue"
import Login from "@/components/welcome/Login.vue"
import Welcome from "@/components/Welcome.vue"

Vue.config.productionTip = false
Vue.use(ElementUI)

Vue.component("MyScene", Scene)
Vue.component("MyLogin", Login)
Vue.component("MyWelcome", Welcome)

const vue = new Vue({
  router,
  render: h => h(App)
})

vue.$mount('#app')
