<script setup></script>

<template>
  <div class="wrapper">
    <h3 class="title">Снять документ с учёта</h3>
    <form @submit.prevent="submitForm" enctype="multipart/form-data">
      <div class="mb-3">
        <label for="documentData.documentExternNumber" class="form-label"
          >Исходящий номер документа</label
        >
        <input
          type="text"
          v-model="documentData.documentExternNumber"
          class="form-control"
          required
        />
      </div>

      <button type="submit" class="btn btn-primary">Отправить</button>
    </form>
  </div>
</template>

<script>
import { useCatalog } from "../../store/catalog.js";
import { mapState, mapActions } from "pinia";
import DeregisterDataService from "../../services/DeregisterDataService";

export default {
  name: "Deregister",
  props: ["id"],
  data() {
    return {
      documentData: {
        documentExternNumber: "",
      },
    };
  },

  methods: {
    async submitForm() {
      try {
        await DeregisterDataService.updateRegCard(this.id, this.documentData);
        alert("Документ успешно снят с учёта");
        this.$router.push({ name: "Info", params: { id: this.id } });
      } catch (error) {
        console.error("Ошибка при снятии с учёта:", error);
        alert("Ошибка при снятии с учета");
      }
    },
  },
};
</script>

<style scoped></style>
