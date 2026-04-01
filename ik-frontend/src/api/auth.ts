import api from "./api";

export const login = (email: string, password: string) =>
    api.post("/auth/login", { email, password });

export const register = (orgName: string, orgNr: string, email: string, password: string) =>
    api.post("auth/register", { orgName, orgNr, email, password });