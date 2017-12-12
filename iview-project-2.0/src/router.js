import App from './app.vue'

import beforeLogin from './views/beforeLogin.vue'
import afterLogin from './views/afterLogin.vue'

import signUp from './template/befoLogin/signUp.vue'
import signIn from './template/befoLogin/signIn.vue'

import header from './template/befoLogin/header.vue'

import detail from './template/afterLogin/detail.vue'
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
                component:signIn
            },
            {
                path: 'signUp',
                name: 'signUp',
                component: signUp,
            }
        ]
    },
    {
        path: '/:userId',
        component: afterLogin,
        meta: {
            requireAuth: true, // 添加该字段，表示进入这个路由是需要登录的
        },
        children: [
            {
                path: 'detail',
                name: 'detail',
                component: detail,
                meta: {
                    requireAuth: true, // 添加该字段，表示进入这个路由是需要登录的
                },
            }
        ]
    },
    {
        path: '/:userId',
        component: detail, 
        meta: {
            requireAuth: true, // 添加该字段，表示进入这个路由是需要登录的
        },
        children: [
            {
                path: 'solution',
                name: 'solution',
                component: solution,
                meta: {
                    requireAuth: true, // 添加该字段，表示进入这个路由是需要登录的
                },
            },
            {
                path: 'assignment',
                name: 'assignment',
                component: assignment,
                meta: {
                    requireAuth: true, // 添加该字段，表示进入这个路由是需要登录的
                },
            },
            {
                path: 'notice',
                name: 'notice',
                component: notice,
                meta: {
                    requireAuth: true, // 添加该字段，表示进入这个路由是需要登录的
                },
            },
            {
                path: 'studentList',
                name: 'studentList',
                component: studentList,
                meta: {
                    requireAuth: true, // 添加该字段，表示进入这个路由是需要登录的
                },
            },
            ,
            {
                path: 'commont',
                name: 'commont',
                component: commont,
                meta: {
                    requireAuth: true, // 添加该字段，表示进入这个路由是需要登录的
                },
            },
            {
                path: 'correct',
                name: 'correct',
                component: correct,
                meta: {
                    requireAuth: true, // 添加该字段，表示进入这个路由是需要登录的
                },
            },
            {
                path: 'courseware',
                name: 'courseware',
                component: courseware,
                meta: {
                    requireAuth: true, // 添加该字段，表示进入这个路由是需要登录的
                },
            }
        ]
    }
    
]

export default routers;