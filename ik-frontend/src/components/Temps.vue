<template>
  <h1>Temperature Logs</h1>

  <input v-model.number="temperature" placeholder="Enter temperature" />
  <button @click="handleLog">Log Temperature</button>

  <ul>
    <li v-for="log in logs" :key="log.id">
      {{ log.date }} -> {{ log.temperature }}°C
    </li>
  </ul>
</template>


<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { useRoute } from "vue-router"
import { logTemperature, getLogs } from "@/api/temperature";

const route = useRoute();
const unitId = Number(route.params.id);

const temperature = ref<number | null>(null);
const logs = ref<any[]>([]);

async function fetchLogs() {
  const res = await getLogs(unitId);
  logs.value = res.data;
}

onMounted(() => {
  fetchLogs();
});

async function handleLog() {
  if (temperature.value === null) return;

  await logTemperature({
    unitId: unitId,
    temperature: temperature.value,
  });
}
</script>