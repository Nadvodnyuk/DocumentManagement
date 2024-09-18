import { createRouter, createWebHistory } from "vue-router";
import Home from "../pages/home/Home.vue";
import Info from "../pages/info/Info.vue";
import NewDoc from "../pages/newDoc/NewDoc.vue";
import NewVersion from "../pages/newVersion/NewVersion.vue";

const routes = [
  {
    path: "/",
    name: "home",
    component: Home,
  },

  {
    path: "/info/:id",
    name: "info",
    component: Info,
    props: true,
  },

  {
    path: "/newDoc/",
    name: "newDoc",
    component: NewDoc,
  },

  {
    path: "/newVersion/:id",
    name: "newVersion",
    component: NewVersion,
    props: true,
  },
];

export default function (history) {
  return createRouter({
    history: createWebHistory(),
    routes,
  });
}
