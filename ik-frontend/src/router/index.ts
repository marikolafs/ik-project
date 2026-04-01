import { createRouter, createWebHistory } from 'vue-router'

import LogIn from '@/components/LogIn.vue'
import TaskList from "@/components/TaskList.vue"
import Register from "@/components/Register.vue";
import NewTask from "@/components/NewTask.vue";

const routes = [
  {
    path: '/',
    name: 'login',
    component: LogIn,
  },
  {
    path: '/tasklist',
    name: 'tasklist',
    component: TaskList,
  },
  {
    path: '/register',
    name: 'register',
    component: Register,
  },
  {
    path: '/taskcreation',
    name: 'taskcreation',
    component: NewTask,
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

export default router