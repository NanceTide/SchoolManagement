import Vue from 'vue'
import VueRouter from 'vue-router'
import {API_URL} from "@/utils";

import Welcome from "@/components/Welcome.vue"
import Student from "@/components/Student.vue"
import Admin from "@/components/Admin.vue"
import Inter from "@/components/Inter.vue"
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
  },
  {
    path: '/admin',
    component: Admin
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

router.beforeEach(async (to, from, next) => {
  if (to.path === '/')
    next()
  else if(to.path === '/inter' && from.path === '/') {
    const res = await axios.post(API_URL + '/check', "", {
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded',
        'Token': localStorage.getItem("Token")
      },
    })
    const data = res.data;

    if(!data.status)
      return false
    else if(data.data === 1)
      next('/student')
    else if(data.data === 0)
      next('/admin')
    else
      return false

  } else if(from.path === '/inter' && (to.path === '/student' || to.path === '/admin'))
    next()
  else
    next()
})

export default router
