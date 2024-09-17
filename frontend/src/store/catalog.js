import { defineStore } from "pinia";

export const useCatalog = defineStore("catalog-store", {
  id: "documentManagement",
  state: () => {
    return {

    };
  },

  actions: {
 
    scrollToTop() {
      window.scrollTo(0, 0);
    },
  },
});