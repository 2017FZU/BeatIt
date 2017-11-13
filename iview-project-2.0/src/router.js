import App from './app.vue'
import index from './views/index.vue'
import detail from './template/afterLogin/detail.vue'
import solution from './template/afterLogin/solution.vue'
import header from './template/afterLogin/header.vue'
import course_work from './template/afterLogin/course_work.vue'
import notice from './template/afterLogin/notice.vue'
import general_comment from './template/afterLogin/general_comment.vue'

const routers = [
    {
        // path: '/',
        // meta: {
        //     title: ''
        // },
        // component: (resolve) => require(['./views/index.vue'], resolve)
        path: '/',
        component: index,
        children: [
            {
                path: 'detail',
                name: 'detail',
                component: detail,
            }
        ]
    },
    {
        
        path: '/',
        component: detail,
        children: [
            {
                path: 'solution',
                name: 'solution',
                component: solution,
            },
            {
                path: 'course_work',
                name: 'course_work',
                component: course_work,
            },
            {
                path: 'notice',
                name: 'notice',
                component: notice,
            },
            {
                path: 'general_comment',
                name: 'general_comment',
                component: general_comment,
            }
        ]
    }
];
export default routers;