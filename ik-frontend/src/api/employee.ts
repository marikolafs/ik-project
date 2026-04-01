import api from "./api";

export interface Employee {
  id: number;
  firstName: string;
  lastName: string;
  position: string;
  email: string;
  admin: boolean;
}

export const getEmployees = async () => {
  return api.get("/auth/employees");
}

export const createEmployee = async (employee: any) => {
  return api.post("/auth/employees", employee);
}

export const updateEmployee = async (employee: Employee) => {
  return api.put(`/auth/employees/${employee.id}`, employee);
}