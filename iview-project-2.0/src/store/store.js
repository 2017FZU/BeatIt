import Vue from 'vue';
import Vuex from 'vuex';
Vue.use(Vuex);

const state = {
    courseName: 'English',
    cid: 1,
};

const mutations = {
    setCourseName(state, courseName) {
        state.courseName = courseName;
    },
    setCid(state, cid) {
        state.cid = cid;
    }
};

const getters = {
    getCourseName: state => {
        return state.courseName;
    },
    getCid: state => {
        return state.cid;
    }
};

const actions = {
};
export default new Vuex.Store({
    state,
    mutations,
    getters,
    actions,
});