import { defineStore } from "pinia";
import api from "@/api/api";

export const useAuthStore = defineStore('auth', {
  state: () => ({
    user: null,
    token: null,
  }),
  actions: {
    async login(email: string, password: string) {
      const res = await api.post('/auth/login', {email, password});
      console.log("LOGIN RESPONSE:", res.data);
      this.token = res.data.token;
      this.user = res.data.user;
      sessionStorage.setItem('token', this.token);
      sessionStorage.setItem('user', JSON.stringify(this.user));
    },
    logout() {
      this.user = null;
      this.token = null;
      sessionStorage.clear();
    },
    async register(orgName: string, orgNr: string, email: string, password: string) {
      const res = await api.post('/auth/register', {orgName, orgNr, email, password});
      this.token = res.data.token;
      this.user = res.data.user;
      sessionStorage.setItem('token', this.token);
      sessionStorage.setItem('user', JSON.stringify(this.user));
    }
  }
});