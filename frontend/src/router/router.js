import { createRouter, createWebHistory } from "vue-router";
import Home from "../pages/home/Home.vue";
import Info from "../pages/info/Info.vue";
import NewDoc from "../pages/newDoc/NewDoc.vue";
import NewVersion from "../pages/newVersion/NewVersion.vue";
import Deregister from "../pages/deregister/Deregister.vue";

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },

  {
    path: "/info/:id",
    name: "Info",
    component: Info,
    props: route => ({ id: Number(route.params.id) }),
  },

  {
    path: "/newDoc/",
    name: "NewDoc",
    component: NewDoc,
  },

  {
    path: "/newVersion/:id",
    name: "NewVersion",
    component: NewVersion,
    props: true,
  },

  {
    path: "/deregister/:id",
    name: "Deregister",
    component: Deregister,
    props: true,
  },
];

export default function (history) {
  return createRouter({
    history: createWebHistory(),
    routes,
  });
}
