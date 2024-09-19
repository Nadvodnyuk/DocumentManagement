<template>
  <div class="wrapper">
    <h3 class="title">Создать новый документ</h3>
    <form @submit.prevent="submitForm" enctype="multipart/form-data">
      <div class="mb-3">
        <label for="documentData.documentName" class="form-label"
          >Название документа</label
        >
        <input
          type="text"
          v-model="documentData.documentName"
          class="form-control"
          minlength="2"
          required
        />
      </div>

      <div class="mb-3">
        <label for="documentData.author" class="form-label">Автор</label>
        <input
          type="text"
          v-model="documentData.author"
          class="form-control"
          required
          minlength="2"
        />
      </div>

      <div class="mb-3">
        <label for="documentData.documentIntroNumber" class="form-label"
          >Входящий номер документа</label
        >
        <input
          type="text"
          v-model="documentData.documentIntroNumber"
          class="form-control"
          required
        />
      </div>

      <div class="mb-3">
        <label for="formattedDateIntro" class="form-label"
          >Дата поступления (автоматически)</label
        >
        <input
          type="text"
          v-model="formattedDateIntro"
          class="form-control"
          disabled
        />
      </div>

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
import NewDocDataService from "../../services/NewDocDataService";

export default {
  name: "NewDoc",
  data() {
    return {
      documentData: {
        documentName: "",
        author: "",
        documentIntroNumber: "",
        dateIntro: new Date(),
      },
      file: null,
    };
  },
  computed: {
    formattedDateIntro() {
      return this.documentData.dateIntro.toLocaleString("ru-RU", {
        day: "2-digit",
        month: "2-digit",
        year: "numeric",
        hour: "2-digit",
        minute: "2-digit",
      });
    },
  },
  methods: {
    handleFileChange(event) {
      this.file = event.target.files[0];
    },
    async submitForm() {
      try {
        const documentToSubmit = {
          ...this.documentData,
          dateIntro: this.documentData.dateIntro.toISOString(),
        };
        await NewDocDataService.createDocument(this.file, documentToSubmit);
        alert("Документ успешно создан");
        this.$router.push('/');
      } catch (error) {
        console.error("Ошибка при создании документа:", error);
        alert("Ошибка при создании документа");
      }
    },
    updateDateIntro() {
      this.documentData.dateIntro = new Date();
    },
  },
  mounted() {
    this.interval = setInterval(this.updateDateIntro, 1000);
  },
  beforeDestroy() {
    clearInterval(this.interval);
  },
};
</script>

<style scoped src="./newDoc.css"></style>
