import Vue from 'vue'
import VueRouter from 'vue-router'

import Register from "@/components/welcome/Register.vue"
import Login from "@/components/welcome/Login.vue"
import Welcome from "@/components/Welcome.vue"
import Inter from "@/components/Inter.vue"

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    component: Welcome
  },
  {
    path: '/inter',
    component: Inter
  }

// import HomeView from '../views/HomeView.vue'
  // {
  //   path: '/about',
  //   name: 'about',
  //   // route level code-splitting
  //   // this generates a separate chunk (about.[hash].js) for this route
  //   // which is lazy-loaded when the route is visited.
  //   component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  // }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
