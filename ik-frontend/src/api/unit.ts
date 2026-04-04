import api from "./api";

export interface Unit {
  id: number;
  name: string;
  type: string;
  minTemp: number;
  maxTemp: number;
}

export const getUnits = async () => {
  return api.get("/unit");
}

export const createUnit = async (unit: any) => {
  return api.post("/unit", unit);
}

export const updateUnit = async (unit: Unit) => {
  return api.put(`/unit/${unit.id}`, unit);
}