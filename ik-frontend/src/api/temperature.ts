import api from "./api";

export const logTemperature = (data: any) => {
  return api.post("/temperature-logs", data);
}

export const getLogs = (unitId: number) => {
  return api.get(`/temperature-logs/${unitId}`);
}