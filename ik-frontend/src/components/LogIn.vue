<template>
  <h1>Log in</h1>
  <input v-model="email" placeholder="Email" />
  <input v-model="password" type="password" placeholder="password" />

  <button @click="handleLogin">Log in</button>

  <button @click="router.push('/register')">Sign up</button>

  <p v-if="error">{{ error }}</p>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { login } from "@/api/auth"
import { useRouter } from "vue-router"
import { useAuthStore } from "@/stores/authStore";

const email = ref('')
const password = ref('')
const error = ref("")

const router = useRouter()
const auth = useAuthStore();

async function handleLogin() {
  try {
    await auth.login(email.value, password.value);

    router.push('/tasklist')
  } catch (e) {
    error.value = "Invalid credentials"
  }
}
</script>