import Vue from 'vue';
import Vuex from 'vuex';
Vue.use(Vuex);

const state = {
    token: '',
    courseName: 'English',
    cid: 1,
};

const mutations = {
    setToken(state, token) {
        state.token = token;
        window.localStorage.setItem('currentUser_token', token);
    },
    setCourseName(state, courseName) {
        state.courseName = courseName;
    },
    setCid(state, cid) {
        state.cid = cid;
    },
    logout(state) {
        state.token = null;
        window.localStorage.clear();
    }
};

const getters = {
    getCourseName: state => {
        return state.courseName;
    },
    getCid: state => {
        return state.cid;
    },
    getToken: state => {
        return state.token;
    },
};

const actions = {
};
export default new Vuex.Store({
    state,
    mutations,
    getters,
    actions,
});