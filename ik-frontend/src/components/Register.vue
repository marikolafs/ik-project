<template>
  <h1>Register</h1>

  <input v-model="orgName" placeholder="Organization name" />
  <input v-model="orgNr" placeholder="Organization number" />
  <input v-model="email" placeholder="Your email" />
  <input v-model="password" type="password" placeholder="Password" />

  <button @click="handleRegister">Register</button>

  <p v-if="message">{{ message }}</p>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { useRouter } from "vue-router"
import {useAuthStore} from "@/stores/authStore";

const orgName = ref("")
const orgNr = ref("")
const email = ref("")
const password = ref("")
const message = ref("")

const router = useRouter()
const auth = useAuthStore()

async function handleRegister() {
  try {
    await auth.register(orgName.value, orgNr.value, email.value, password.value);
    message.value = "Registration successful!";
    router.push('/tasklist')
  } catch (e) {
    message.value = "Error: " + (e.response?.data || "Failed");
  }
};
</script>