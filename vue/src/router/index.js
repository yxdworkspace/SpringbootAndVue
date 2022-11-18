import { createRouter, createWebHistory } from 'vue-router'
import layout from "@/layout/layout";

const routes = [
  {
    path: '/',
    name: 'layout',
    component: layout,
    redirect: '/user',
    children: [
      {
        path: 'user',
        name: 'user',
        component: () => import('@/views/User.vue')
      },
      {
        path: 'book',
        name: 'book',
        component: () => import('@/views/Book.vue')
      },
      {
        path: 'person',
        name: 'person',
        component: () => import('@/views/Person.vue')
      },
      {
        path: 'news',
        name: 'news',
        component: () => import('@/views/News.vue')
      }
    ]
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('@/views/Login.vue')
  },
  {
    path: '/register',
    name: 'Register',
    component: () => import('@/views/Register.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
