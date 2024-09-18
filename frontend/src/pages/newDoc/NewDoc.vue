<script setup></script>

<template>
  <div>
    <div class="sub_header"></div>
    <div>
      <p>Создать новый документ</p>
    </div>
  </div>
</template>

<script>
import { useCatalog } from "../../store/catalog.js";
import { mapState, mapActions } from "pinia";
import NewDocDataService from "../../services/NewDocDataService";

export default {
  name: "NewDoc",
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

<style scoped src="./newDoc.css"></style>
