import { createRouter, createWebHistory } from 'vue-router'

import LogIn from '@/components/LogIn.vue'
import TaskList from "@/components/TaskList.vue"
import Register from "@/components/Register.vue";
import NewTask from "@/components/NewTask.vue";
import EmployeeOverview from "@/components/EmployeeOverview.vue";
import NewEmployee from "@/components/NewEmployee.vue";
import {useAuthStore} from "@/stores/authStore";
import UnitOverview from "@/components/UnitOverview.vue";
import Temps from '@/components/Temps.vue';

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
  },
  {
    path: '/employeeoverview',
    name: 'employeeoverview',
    component: EmployeeOverview,
    meta: { requiresAdmin: true }
  },
  {
    path: '/newemployee',
    name: 'newemployee',
    component: NewEmployee,
  },
  {
    path: '/unit',
    name: 'unit',
    component: UnitOverview,
  },
  {
    path: '/temperature/:id',
    name: 'temperature',
    component: Temps,
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

router.beforeEach((to, from) => {
  const auth = useAuthStore();

  if (to.meta.requiresAdmin && !auth.user?.admin) {
    return '/tasklist';
  }
  if (to.meta.requiresAuth && !auth.user) {
    return '/login';
  }
});

export default router