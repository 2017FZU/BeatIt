<template>
    <div class="main">
        <my-head></my-head>
        <keep-alive>
    <router-view v-if="$route.meta.keepAlive"></router-view>
</keep-alive>
<router-view v-if="!$route.meta.keepAlive"></router-view>
    </div>
</template>

<script>
export default {
  data() {
    return {
      current: 0
    };
  },
  mounted: function() {
    this.$store.commit("logout");
  },
  methods: {},
  components: {
    myHead: require("../template/befoLogin/header.vue")
  }
};
</script>

<style scoped>
.main {
  height: 770px;
  width: 100%;
  padding: 0;
  margin: 0;
  background: #fdfdfc;
  /* background-image: url('../images/bd.jpg'); */
  background-repeat: no-repeat;
  background-attachment: fixed;
}
</style>