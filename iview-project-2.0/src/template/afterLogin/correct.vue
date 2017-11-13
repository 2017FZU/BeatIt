<template>
  <div class="container">
    <div class="select">
      <img src="../../images/选择作业图标.png" alt="">
      <Select id="homework" v-model="homework" style="width:200px">
        <Option v-for="item in homeworkList" :value="item.value" :key="item.value">{{ item.label }}</Option>
      </Select>
      <img src="../../images/学生图标.png" alt="">
      <Select v-model="student" style="width:200px">
        <Option v-for="item in studentList" :value="item.value" :key="item.value">{{ item.label }}</Option>
      </Select>
      <Button type="primary" @click="modal1 = true">Display dialog box</Button>
      <Modal v-model="modal1" title="Common Modal dialog box title" @on-ok="ok" @on-cancel="cancel">
        <p>Content of dialog</p>
        <p>Content of dialog</p>
        <p>Content of dialog</p>
      </Modal>
    </div>
    <div class="container-main">
      <Table height="500" width="950" size="large" stripe border align="center" :columns="columns1" :data="data1"></Table>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      modal1:false,
      homeworkList: [
        {
          value: '作业一',
          label: '作业一'
        },
        {
          value: '作业二',
          label: '作业二'
        },
        {
          value: '作业三',
          label: '作业三'
        }
      ],
      studentList: [
        {
          value: '学生一',
          label: '学生一'
        },
        {
          value: '学生二',
          label: '学生二'
        },
        {
          value: '学生三',
          label: '学生三'
        }
      ],
      columns1: [
        {
          title: '作业',
          key: 'homework',
          align: 'center',
          width: '200'
        },
        {
          title: '姓名',
          key: 'name',
          align: 'center',
          width: '250'
        },
        {
          title: '学号',
          key: 'number',
          align: 'center',
          width: '250'
        },
        {
          title: '批改情况',
          key: 'case',
          align: 'center',
          render: (h, params) => {
            return h('Button', {
              props: {
                type: 'primary'
              },
              on: {
                click: () => {
                  modal1 = "true"
                }
              }
            }, "未批改")
          }
        }
      ],
      data1: [
        {
          homework: '作业一',
          name: 'John Brown',
          number: '001',
          case: '未批改'
        },
        {
          homework: '作业一',
          name: 'Jim Green',
          number: '002',
          case: '未批改'
        },
        {
          homework: '作业一',
          name: 'Joe Black',
          number: '003',
          case: '未批改'
        },
        {
          homework: '作业一',
          name: 'Joe Black',
          number: '003',
          case: '未批改'
        },
        {
          homework: '作业一',
          name: 'Joe Black',
          number: '003'
        },
        {
          homework: '作业一',
          name: 'Joe Black',
          number: '003'
        },
        {
          homework: '作业一',
          name: 'Joe Black',
          number: '003'
        },
        {
          homework: '作业一',
          name: 'Joe Black',
          number: '003'
        },
        {
          homework: '作业一',
          name: 'Joe Black',
          number: '003'
        },
        {
          homework: '作业一',
          name: 'Joe Black',
          number: '003'
        },
        {
          homework: '作业一',
          name: 'Joe Black',
          number: '003'
        }
      ]
    }
  },
  methods: {
    show() {
      modal1 = true
    }
  }
}
</script>

<style scoped>
.container {
  margin-left: -100px;
  margin-top: -10px;
  padding: 0;
  height: 600px;
}

.select {
  padding: 15px 40px;
  display: flex;
  align-items: center;
}

.select img {
  margin-left: 15px;
  margin-right: 15px;
}

select#homework {
  width: 200px;
  border: 2px solid #979797;
  border-radius: 25px;
}

.container-main {
  padding: 15px 40px;
}
</style>