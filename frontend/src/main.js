import { createApp } from "vue";
import "./style.css";
import App from "./App.vue";
import { createPinia } from "pinia";
import { createWebHistory } from "vue-router";
import createRouter from "./pages/routes.js";
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/js/bootstrap.min.js';
import axios from "axios";

const router = createRouter(createWebHistory());
const app = createApp(App);
const store = createPinia();

app.config.globalProperties.axios = axios;
app.use(router).use(Antd).use(store).mount("#app");