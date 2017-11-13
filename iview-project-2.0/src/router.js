import App from './app.vue'
import index from './views/index.vue'
import detail from './template/afterLogin/detail.vue'
import solution from './template/afterLogin/solution.vue'
import header from './template/afterLogin/header.vue'
import courseware from './template/afterLogin/courseware.vue'
import correct from './template/afterLogin/correct.vue'
import assignment from './template/afterLogin/assignment.vue'
import notice from './template/afterLogin/notice.vue'

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
        component: detail
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
        component: solution
      },
      {
        path: 'correct',
        name: 'correct',
        component: correct
      },
      {
        path: 'courseware',
        name: 'courseware',
        component: courseware
      },
      {
        path: 'assignment',
        name: 'assignment',
        component: assignment
      },
      {
        path: 'notice',
        name: 'notice',
        component: notice
      }
    ]
  }
]
export default routers
