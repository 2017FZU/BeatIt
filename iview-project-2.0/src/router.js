import App from './app.vue'
import index from './views/index.vue'
import detail from './template/afterLogin/detail.vue'
import solution from './template/afterLogin/solution.vue'
import header from './template/afterLogin/header.vue'
import course_work from './template/afterLogin/course_work.vue'

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
            }
        ]
    }
];
export default routers;