import App from './app.vue'
import index from './views/index.vue'
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
                path: 'assignment',
                name: 'assignment',
                component: assignment,
            },
            {
                path: 'notice',
                name: 'notice',
                component: notice,
            },
            {
                path: 'studentList',
                name: 'studentList',
                component: studentList,
            },
            ,
            {
                path: 'commont',
                name: 'commont',
                component: commont,
            },
            {
                path: 'correct',
                name: 'correct',
                component: correct,
            },
            {
                path: 'courseware',
                name: 'courseware',
                component: courseware,
            }
        ]
    }
];
export default routers;