import App from './app.vue'

import beforeLogin from './views/beforeLogin.vue'
import afterLogin from './views/afterLogin.vue'

import signUp from './template/befoLogin/signUp.vue'
import signIn from './template/befoLogin/signIn.vue'

import header from './template/befoLogin/header.vue'

import test from './template/afterLogin/test.vue'
import detail from './template/afterLogin/detail.vue'
import index from './template/afterLogin/index.vue'
import solution from './template/afterLogin/solution.vue'
import courseHeader from './template/afterLogin/courseHeader.vue'
import indexHeader from './template/afterLogin/indexHeader.vue'
import assignment from './template/afterLogin/assignment.vue'
import notice from './template/afterLogin/notice.vue'
import commont from './template/afterLogin/commont.vue'
import studentList from './template/afterLogin/studentList.vue'
import correct from './template/afterLogin/correct.vue'
import courseware from './template/afterLogin/courseware.vue'


const routers = [
    {
        path: '/',
        component: beforeLogin,
        children: [
            {
                path:'',
                name:'signIn',
                component:signIn,
                children:[
                    {
                        path: 'index',
                        name: 'index',
                        component: index,
                        meta: {
                            requireAuth: true, // 添加该字段，表示进入这个路由是需要登录的
                            keepAlive: true
                        },
                    }
                ]
            },
            {
                path: 'signUp',
                name: 'signUp',
                component: signUp,
            }
        ]
    },
    {
        path: '/userId',
        component: index,
        children: [
            {
                path: 'detail',
                name: 'detail',
                component: detail,
                meta: {
                    requireAuth: true, // 添加该字段，表示进入这个路由是需要登录的
                    keepAlive: true
                },
            }
        ]
    },
    {   
        path: '/userId',
        component: detail, 
        children: [
            {
                path: 'solution',
                name: 'solution',
                component: solution,
                meta: {
                    requireAuth: true, // 添加该字段，表示进入这个路由是需要登录的
                    keepAlive: true
                },
            },
            {
                path: 'assignment',
                name: 'assignment',
                component: assignment,
                meta: {
                    requireAuth: true, // 添加该字段，表示进入这个路由是需要登录的
                    keepAlive: true
                },
            },
            {
                path: 'notice',
                name: 'notice',
                component: notice,
                meta: {
                    requireAuth: true, // 添加该字段，表示进入这个路由是需要登录的
                    keepAlive: true
                },
            },
            {
                path: 'studentList',
                name: 'studentList',
                component: studentList,
                meta: {
                    requireAuth: true, // 添加该字段，表示进入这个路由是需要登录的
                    keepAlive: true
                },
            },
            ,
            {
                path: 'commont',
                name: 'commont',
                component: commont,
                meta: {
                    requireAuth: true, // 添加该字段，表示进入这个路由是需要登录的
                    keepAlive: true
                },
            },
            {
                path: 'correct',
                name: 'correct',
                component: correct,
                meta: {
                    requireAuth: true, // 添加该字段，表示进入这个路由是需要登录的
                    keepAlive: true
                },
            },
            {
                path: 'courseware',
                name: 'courseware',
                component: courseware,
                meta: {
                    requireAuth: true, // 添加该字段，表示进入这个路由是需要登录的
                    keepAlive: true
                },
            },
            {
                path: 'test',
                name: 'test',
                component: test,
                meta: {
                    requireAuth: true, // 添加该字段，表示进入这个路由是需要登录的
                    keepAlive: true
                },
            }
        ]
    }
    
]

export default routers;