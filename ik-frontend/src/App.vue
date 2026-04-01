<script setup>
import NavBar from './components/NavBar.vue'
</script>

<template>
  <div id="app">
    <NavBar />
    <main class="page">
      <router-view />
    </main>
  </div>
</template>
