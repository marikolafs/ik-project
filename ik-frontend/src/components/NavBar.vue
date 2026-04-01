<template>
  <header class="nav">
    <h1>IK-mat app</h1>
    <nav>
      <router-link to="/tasklist">Task List</router-link>
      <router-link to="/employeeoverview">Employees</router-link>
      <button @click="logout">Log out</button>
    </nav>
  </header>
</template>

<script setup lang="ts">
import { useRouter } from 'vue-router'

const router = useRouter()

function logout() {
  router.push('/')
}
</script>

