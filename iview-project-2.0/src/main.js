import Vue from 'vue';
import iView from 'iview';
import VueRouter from 'vue-router';
import Routers from './router';
import Util from './libs/util';
import App from './app.vue';
import 'iview/dist/styles/iview.css';
import axios from 'axios'
import './config/config'
import store from './store/store';

import Vuex from 'vuex';

Vue.use(Vuex);
Vue.prototype.$http = axios;
Vue.use(VueRouter);
Vue.use(iView);
//Vue.use(VueResource);

//请求配置
axios.defaults.timeout = 5000;
axios.defaults.headers.post['Content-Type'] = 'application/json;charset=UTF-8';
axios.defaults.baseURL = 'http://111.231.190.23';// 服务器IP地址



// 路由配置
const RouterConfig = {
    mode: 'history',
    routes: Routers
};
const router = new VueRouter(RouterConfig);

router.beforeEach((to, from, next) => {
    iView.LoadingBar.start();
    Util.title(to.meta.title);
    next();
});

router.beforeEach((to, from, next) => {
    if (to.meta.requireAuth) {  // 判断该路由是否需要登录权限
        if (!localStorage.currenUser_token) {
            store.commit('setToken', localStorage.getItem('currentUser_token'));
        }   
        if (store.state.token) {  // 通过vuex state获取当前的token是否存在
            next();
        }
        
        else {
            next({
                path: '',
                query: { redirect: to.fullPath }  // 将跳转的路由path作为参数，登录成功后跳转到该路由
            });
        }
    }
    else {
        next();
    }
    if(store.state.tid==-1)
    {
        store.commit('setTid', localStorage.getItem('currentUser_tid'));
    }
    if(store.state.cid==-1)
    {
        store.commit('setCid', localStorage.getItem('currentCourse_cid'));
    }
    if(store.state.teacherName=='defaultTeacherName')
    {
        store.commit('setTName', localStorage.getItem('currentUser_tName'));
    }
    if(store.state.courseName=='defaultCourseName')
    {
        store.commit('setCourseName', localStorage.getItem('currentCourse_cname'));
    }
});

// http response 拦截器
axios.interceptors.response.use(
    response => {
        return response;
    },
    error => {
        if (error.response) {
            switch (error.response.status) {
                case 401:
                    // 返回 401 清除token信息并跳转到登录页面
                    store.commit('logout');
                    router.replace({
                        path: '/',
                        query: { redirect: router.currentRoute.fullPath }
                    });
            }
        }
        return Promise.reject(error.response.data);   // 返回接口返回的错误信息
    });

router.afterEach((to, from, next) => {
    iView.LoadingBar.finish();
    window.scrollTo(0, 0);
});

new Vue({
    el: '#app',
    router: router,
    store: store,
    render: h => h(App)
});
