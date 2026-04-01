<template>

  <h1>Add new task</h1>

  <div v-for="(task, index) in tasks" :key="index">
    <input v-model="task.title" placeholder="Task name" />
    <input v-model="task.description" placeholder="Description" />
    <button @click="removeTask(index)">Remove</button>
  </div>

  <button @click="addTask">Add another task</button>
  <button @click="handleSave">Save</button>
</template>

<script setup lang="ts">

import {ref} from "vue";
import { useRouter } from "vue-router";
import { Task, createTask } from "@/api/task"

const router = useRouter()

const tasks = ref<Omit<Task, "id">[]>([
  { title: "", description: "", completed: false }
]);

function addTask() {
  tasks.value.push({ title: "", description: "", completed: false })
}

function removeTask(index: number) {
  tasks.value.splice(index, 1);
}

async function handleSave() {
  try {
    for (const task of tasks.value) {
      await createTask(task);
    }
    router.push("/tasklist");
  } catch (err) {
    console.error("Failed to save takss", err);
  }
}
</script>