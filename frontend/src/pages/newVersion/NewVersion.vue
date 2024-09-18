<script setup></script>

<template>
  <div class="wrapper">
    <h3 class="title">Загрузить новую версию документа</h3>
    <form @submit.prevent="submitForm" enctype="multipart/form-data">
      <div class="mb-3">
        <label for="file" class="form-label">Загрузить файл</label>
        <input
          type="file"
          @change="handleFileChange"
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
import NewVersionDataService from "../../services/NewVersionDataService";

export default {
  name: "NewVersion",
  props: ["id"],
  data() {
    return {
      file: null,
    };
  },
  computed: {
    ...mapState(useCatalog, []),
  },
  methods: {
    ...mapActions(useCatalog, []),
    handleFileChange(event) {
      this.file = event.target.files[0];
    },
    async submitForm() {
      try {
        const documentToSubmit = {
          documentId: this.id,
        };
        await NewVersionDataService.createDocumentVersion(
          this.file,
          documentToSubmit
        );
        alert("Новая версия документа успешно загружена");
        this.$router.push({ name: "Info", params: { id: this.id } });
      } catch (error) {
        console.error("Ошибка при загрузке версии документа:", error);
        alert("Ошибка при загрузке версии документа");
      }
    },
  },
};
</script>

<style scoped src="./newVersion.css"></style>
