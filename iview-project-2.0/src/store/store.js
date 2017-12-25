import Vue from 'vue';
import Vuex from 'vuex';
Vue.use(Vuex);

const state = {
    tid:-1,
    token: '',
    courseName: 'defaultCourseName',
    cid: -1,
    teacherName:'defaultTeacherName',
    url:'defaultUrl',
    basedata:'defaultBasedata',
    qcode:'defaultQcode'

};

const mutations = {
    setToken(state, token) {
        state.token = token;
        window.localStorage.setItem('currentUser_token', token);
    },
    setUrl(state, url) {
        state.url = url;
        window.localStorage.setItem('currentUser_url', url);
    },
    setBasedata(state, basedata) {
        state.basedata = basedata;
        window.localStorage.setItem('currentUser_basedata', basedata);
    },
    setCourseName(state, courseName) {
        state.courseName = courseName;
        window.localStorage.setItem('currentCourse_cname', courseName);
    },
    setCid(state, cid) {
        state.cid = cid;
        window.localStorage.setItem('currentCourse_cid', cid);
    },
    setQcode(state, qcode) {
        state.qcode = qcode;
        window.localStorage.setItem('currentCourse_qcode', qcode);
    },
    setTid(state, tid) {
        state.tid = tid;
        window.localStorage.setItem('currentUser_tid', tid);
    },
    setTName(state, teacherName) {
        state.teacherName = teacherName;
        window.localStorage.setItem('currentUser_tName', teacherName);
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
    getUrl: state => {
        return state.url;
    },
    getBasedata: state => {
        return state.basedata;
    },
    getCid: state => {
        return state.cid;
    },
    getQcode: state => {
        return state.qcode;
    },
    getToken: state => {
        return state.token;
    },
    getTid: state => {
        return state.tid;
    },
    getTname: state => {
        return state.teacherName;
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