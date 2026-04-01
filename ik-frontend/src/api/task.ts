import api from "./api";

export interface Task {
  id: number;
  title: string;
  description: string;
  completed: boolean;
}

export const getTasks = async () => {
  return api.get("/tasks");
}

export const createTask = async (task: any) => {
  return api.post("/tasks", task);
}

export const updateTask = async (task: Task) => {
  return api.put(`/tasks/${task.id}`, task);
}