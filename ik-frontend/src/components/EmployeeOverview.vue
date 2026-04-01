<template>
  <h1>Employees</h1>
  <ul>
    <li v-for="emp in employees" :key="emp.id">
      {{ emp.firstName }} {{ emp.lastName }} - {{ emp.position }}
    </li>
  </ul>
<button @click="goToCreate">Add new employee</button>
</template>

<script setup lang="ts">

import { useRouter } from "vue-router";
import { ref, onMounted } from "vue";
import { Employee, getEmployees, updateEmployee } from "@/api/employee";

const router = useRouter();
const employees = ref<Employee[]>([]);
const error = ref<string | null>(null);

function goToCreate() {
  router.push("/newemployee");
}

async function fetchEmployees() {
  try {
    const res = await getEmployees();
    employees.value = res.data;
  } catch (err: any) {
    console.error("Failed to fetch employees", err);
    error.value = err.message;
  }
}

onMounted(() => {
  fetchEmployees();
});
</script>
