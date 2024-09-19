<template>
  <div class="container">
    <div class="left-pane">
      <h3 class="title">
        {{ regCard?.document?.documentName || "Документ не найден" }}
      </h3>
      <table class="table">
        <thead>
          <tr>
            <th>Версия</th>
            <th>Автор версии</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          <tr
            v-for="(version, index) in documentVersions"
            :key="version.documentVersionId"
          >
            <td>Версия {{ documentVersions.length - index }}</td>
            <td>{{ version.versionAuthor}}</td>
            <td>
              <button @click="downloadVersion(version.documentVersionId, documentVersions.length - index)">
                Скачать
              </button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>

    <div class="right-pane">
      <div v-if="regCard" class="regCard">
        <p>
          <strong>Входящий номер документа:</strong>
          {{ regCard.documentIntroNumber }}
        </p>
        <p v-show="regCard.documentExternNumber">
          <strong>Исходящий номер документа:</strong>
          {{ regCard.documentExternNumber }}
        </p>
        <p>
          <strong>Дата поступления:</strong> {{ formatDate(regCard.dateIntro) }}
        </p>
        <p v-show="regCard.dateExtern">
          <strong>Дата снятия с учета :</strong>
          {{ formatDate(regCard.dateExtern) }}
        </p>
        <div class="mb">
          <router-link
            v-show="!regCard.dateExtern"
            :to="{
              name: 'NewVersion',
              params: { id: regCard.document.documentId },
            }"
            class="btn btn-primary"
          >
            Загрузить новую версию
          </router-link>
        </div>
        <div class="mb">
          <router-link
            v-show="!regCard.dateExtern"
            :to="{ name: 'Deregister', params: { id: regCard.regCardId } }"
            class="btn btn-danger"
          >
            Снять с учёта
          </router-link>
        </div>
      </div>
      <p v-else>Загрузка...</p>
    </div>
  </div>
</template>

<script>
import InfoDataService from "../../services/InfoDataService";

export default {
  name: "Info",
  props: ["id"],
  data() {
    return {
      documentVersions: [],
      regCard: null,
    };
  },
  created() {
    this.fetchDocumentVersions();
    this.fetchRegCard();
  },
  methods: {
    async fetchDocumentVersions() {
      try {
        const response = await InfoDataService.getDocVerByDocId(this.id);
        this.documentVersions = response.data.sort(
          (a, b) => b.documentVersionId - a.documentVersionId
        );
      } catch (error) {
        console.error("Error fetching document versions:", error);
        this.documentVersions = [];
      }
    },
    async fetchRegCard() {
      try {
        const response = await InfoDataService.getRegCard(this.id);
        this.regCard = response.data;
      } catch (error) {
        console.error("Error fetching registration card:", error);
        this.regCard = null;
      }
    },
    formatDate(date) {
      if (!date) return "";
      const options = {
        day: "2-digit",
        month: "2-digit",
        year: "numeric",
        hour: "2-digit",
        minute: "2-digit",
      };
      return new Date(date).toLocaleDateString("ru-RU", options);
    },
    async downloadVersion(versionId, version) {
      try {
        const response = await InfoDataService.downloadDocumentVersion(versionId);

        const blob = new Blob([response.data], { type: response.headers['content-type'] });
        const url = window.URL.createObjectURL(blob);
        const link = document.createElement('a');
        link.href = url;

        const contentDisposition = response.headers['content-disposition'];
        let fileName = this.regCard?.document?.documentName + "_" + version;
        console.log(contentDisposition)
        if (contentDisposition) {
          const fileNameMatch = contentDisposition.match(/filename="(.+)"/);
          if (fileNameMatch && fileNameMatch[1]) {
            fileName = fileNameMatch[1];
          }
        }

        link.setAttribute('download', fileName);
        document.body.appendChild(link);
        link.click();
        link.remove();
        window.URL.revokeObjectURL(url);
      } catch (error) {
        console.error("Ошибка при скачивании версии документа:", error);
      }
    }
  },
};
</script>

<style scoped src="./info.css"></style>
