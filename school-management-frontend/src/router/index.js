import Vue from 'vue'
import VueRouter from 'vue-router'
import {API_URL} from "@/utils";

import Welcome from "@/components/Welcome.vue"
import Student from "@/components/Student.vue";
import axios from "axios";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    component: Welcome
  },
  {
    path: '/student',
    component: Student
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

router.beforeEach(async (to, from, next) => {
  if (to.path === '/') {
    next()
    return
  }

  const token = localStorage.getItem("Token")
  let tokenFlag = token;

  await axios.post(API_URL + '/check', "", {
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded',
      'Token': token
    },
  }).then(({data}) => {
    tokenFlag = data.status
    if(!tokenFlag)
      // 未登录
      if(to.path === '/')
        next()
      else
        next('/')
    else
      next()
  }).catch(() => {
    tokenFlag = false
  })


})

export default router
