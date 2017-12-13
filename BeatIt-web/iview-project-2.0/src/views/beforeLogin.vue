<template>
    <div class="main">
        <my-head></my-head>
        <router-view></router-view>
    </div>
</template>

<script>
export default {
    data() {
        return {
            current: 0
        }
    },
     mounted: function() {
         this.$store.commit('logout');
     },
    methods: {

    },
    components: {
        'myHead': require('../template/befoLogin/header.vue'),

    }
}
</script>

<style scoped>
.main {
    height: 600px;
    width: 100%;
    padding: 0;
    margin: 0;
    background: #fdfdfc;
}
</style>