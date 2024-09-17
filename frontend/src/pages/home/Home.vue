<script setup>
</script>

<template>
  <div class="sub_header"></div>

</template>

<script>
import { useCatalog } from "../../store/catalog.js";
import { mapState, mapActions } from "pinia";
import HomeDataService from "../../services/HomeDataService";

export default {
  name: "App",
  data() {
    return {

    };
  },
  computed: {
    ...mapState(useCatalog, []),

  },
  methods: {
    ...mapActions(useCatalog, []),

  }
};
</script>

<style scoped src="./home.css"></style>