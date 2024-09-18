<script setup></script>

<template>
  <div>
    <div class="sub_header"></div>
    {{ id }}
  </div>
</template>

<script>
import { useCatalog } from "../../store/catalog.js";
import { mapState, mapActions } from "pinia";
import InfoDataService from "../../services/InfoDataService";

export default {
  name: "Info",
  props: ["id"],
  data() {
    return {};
  },
  computed: {
    ...mapState(useCatalog, []),
  },
  methods: {
    ...mapActions(useCatalog, []),
  },
};
</script>

<style scoped src="./info.css"></style>
