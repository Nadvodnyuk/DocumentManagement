<script setup>
</script>

<template>
  <div class="sub_header"></div>

</template>

<script>
import { useCatalog } from "../../store/catalog.js";
import { mapState, mapActions } from "pinia";
import NewVersionDataService from "../../services/NewVersionDataService";

export default {
  name: "NewVersion",
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

<style scoped src="./newVersion.css"></style>