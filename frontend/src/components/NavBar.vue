<template>
  <header>
    <nav
      class="navbar navbar-expand-md navbar-dark"
      style="background-color: #2c3e50"
    >
      <div class="container" style="margin-left: 42px">
        <router-link class="navbar-brand" to="/"
          >DocumentManagement
        </router-link>
        <button
          class="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarCollapse"
          aria-controls="navbarCollapse"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span class="navbar-toggler-icon"> </span>
        </button>
        <div
          class="collapse navbar-collapse"
          id="navbarCollapse"
          style="flex-grow: 0"
        >
          <ul class="navbar-nav me-auto mb-2 mb-md-0">
            <li class="nav-item">
              <router-link class="nav-link" to="/newDoc"
                >Загрузить новый документ
              </router-link>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  </header>
</template>

<script>
import { defineComponent } from "vue";

export default defineComponent({
  name: "NavBar",
  computed: {},
  methods: {},
});
</script>

<style scoped>
a {
  cursor: pointer;
}
</style>
