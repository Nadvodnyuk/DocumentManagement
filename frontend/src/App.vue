<script setup>
import NavBar from "./components/NavBar.vue";
</script>

<template>
  <div class="wrapper">
    <NavBar />

    <div class="body">
      <router-view />
    </div>
  </div>
</template>

<style scoped>
.wrapper {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
}

.body {
  flex: 1;
  padding: 1em;
}
.logo {
  height: 6em;
  padding: 1.5em;
  will-change: filter;
  transition: filter 300ms;
}
.logo:hover {
  filter: drop-shadow(0 0 2em #646cffaa);
}
.logo.vue:hover {
  filter: drop-shadow(0 0 2em #42b883aa);
}
</style>
