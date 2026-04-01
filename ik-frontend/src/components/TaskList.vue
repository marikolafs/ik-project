<template>
  <h1>TO DO:</h1>
  <ul>
    <li v-for="task in tasks" :key="task.id">
      <label>
        <input type="checkbox" v-model="task.completed" @change="toggleTask(task)" />
        {{ task.title }} - {{ task.description }}
      </label>
    </li>
  </ul>
  <button @click="goToTaskCreation">New Task</button>
</template>

<script setup lang="ts">

import { useRouter } from "vue-router";
import { ref, onMounted } from "vue";
import { Task, getTasks, updateTask } from "@/api/task";

const router = useRouter();
const tasks = ref<Task[]>([]);
const error = ref<string | null>(null);

function goToTaskCreation() {
  router.push("/taskcreation");
}

async function fetchTasks() {
  try {
    const res = await getTasks();
    tasks.value = res.data;
  } catch (err: any) {
    console.error("Failed to fetch tasks", err);
    error.value = err.message;
  }
}

async function toggleTask(task: Task) {
  try {
    await updateTask(task);
  } catch (err) {
    console.error("Failed to update task", err);
  }
}

onMounted(() => {
  fetchTasks();
});

</script>