<template>
  <div class="table-container">
    <table>
      <thead>
        <tr>
          <th>Название документа</th>
          <th>Входящий номер документа</th>
          <th>Дата поступления</th>
          <th>Информация</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="regCard in regCards" :key="regCard.regCardId">
          <td>{{ regCard.document.documentName }}</td>
          <td>{{ regCard.documentIntroNumber }}</td>
          <td>{{ regCard.dateIntro }}</td>
          <td>
            <router-link
              :to="{ name: 'Info', params: { id: regCard.regCardId } }"
            >
              Посмотреть информацию
            </router-link>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import HomeDataService from "../../services/HomeDataService";

export default {
  name: "Home",
  data() {
    return {
      regCards: [],
    };
  },
  mounted() {
    this.fetchRegCards();
  },
  methods: {
    async fetchRegCards() {
      try {
        const response = await HomeDataService.getAll();
        console.log(response.data);
        this.regCards = response.data.sort((a, b) => b.regCardId - a.regCardId);
      } catch (error) {
        console.error("Ошибка при получении данных:", error);
      }
    },
  },
};
</script>

<style scoped>
.table-container {
  display: flex;
  justify-content: center;
  padding: 1em;
}

table {
  width: 80%;
  border-collapse: collapse;
}

th,
td {
  border: 1px solid #ddd;
  padding: 8px;
}

th {
  background-color: #f2f2f2;
  text-align: left;
}
</style>
