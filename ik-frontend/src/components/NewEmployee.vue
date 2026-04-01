<template>
  <h1>Add new Employee</h1>

  <input v-model="firstName" placeholder="First name" />
  <input v-model="lastName" placeholder="Last name" />
  <input v-model="email" placeholder="Email address" />
  <input v-model="position" placeholder="Job position" />
  <input type="checkbox" v-model="isAdmin" />

  <button @click="handleRegister">Register</button>
</template>


<script setup lang="ts">
import { ref } from 'vue'
import { useRouter } from "vue-router"
import {useAuthStore} from "@/stores/authStore";
import { createEmployee } from "@/api/employee";

const firstName = ref("")
const lastName = ref("")
const email = ref("")
const position = ref("")

const router = useRouter()
const auth = useAuthStore()
const isAdmin = ref(false)

async function handleRegister() {
  try {
    await createEmployee({
      firstName: firstName.value,
      lastName: lastName.value,
      email: email.value,
      position: position.value,
      admin: isAdmin.value
    });
    router.push('/employeeoverview')
  } catch (err) {
    console.error("Failed to create employee", err)
  }
}
</script>