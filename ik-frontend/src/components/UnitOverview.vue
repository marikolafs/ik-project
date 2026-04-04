<template>
  <h1>Storage units</h1>

  <div v-if="auth.user?.admin">

    <h3>Add new unit</h3>

    <input v-model="name" placeholder="Name" />
    <select v-model="type" @change="applyDefaults">
      <option value="FRIDGE">Fridge</option>
      <option value="FREEZER">Freezer</option>
      <option value="HEATER">Heater</option>
    </select>

    <input v-model.number="minTemp" placeholder="Min temp" />
    <input v-model.number="maxTemp" placeholder="Max temp" />

    <button @click="handleCreate">Create</button>
  </div>

  <ul>
    <li v-for="unit in units" :key="unit.id">
      {{ unit.name }} ({{ unit.type }}) -> {{ unit.minTemp }}°C - {{ unit.maxTemp }}°C
      <button @click="goToLogs(unit.id)">Log</button>
    </li>
  </ul>
</template>

<script setup lang="ts">

import { useRouter } from "vue-router";
import { ref, onMounted } from "vue";
import { Unit, getUnits, createUnit } from "@/api/unit";
import {useAuthStore} from "@/stores/authStore";

const router = useRouter();
const error = ref<string | null>(null);
const auth = useAuthStore();

const units = ref<Unit[]>([]);

const name = ref("");
const type = ref("FRIDGE");
const minTemp = ref<number | null>(null);
const maxTemp = ref<number | null>(null);

function applyDefaults() {
  if (type.value === "FRIDGE") {
    minTemp.value = 0;
    maxTemp.value = 4;
  } else if (type.value === "FREEZER") {
    minTemp.value = -30;
    maxTemp.value = -18;
  } else if (type.value === "HEATER") {
    minTemp.value = 60;
    maxTemp.value = 100;
  }
}

async function handleCreate() {
  await createUnit({
    name: name.value,
    type: type.value,
    minTemp: minTemp.value,
    maxTemp: maxTemp.value,
  });

  name.value = "";
  applyDefaults();

  await fetchUnits();
}

function goToLogs(id: number) {
  router.push(`/temperature/${id}`);
}

async function fetchUnits() {
  const res = await getUnits();
  units.value = res.data;
}

onMounted(() => {
  applyDefaults();
  fetchUnits();
});
</script>
