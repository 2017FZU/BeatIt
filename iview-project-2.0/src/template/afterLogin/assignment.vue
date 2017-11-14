<template>
  <div class="container">
      <div class="publish">
          <Button type="primary" icon="paper-airplane" @click="modal1 = true">发布作业</Button>
          <Modal
        v-model="modal1"
        title="发布作业"
        @on-ok="ok"
        @on-cancel="cancel">
        <p style="margin-top:4px;margin-bottom:4px;font-size:14px;">作业标题</p><input type="text" style="font-size:14px;">
        <p style="margin-top:4px;margin-bottom:4px;font-size:14px;">作业内容</p><textarea name="" id="" cols="66" rows="6" style="font-size:14px;"></textarea>
        <p style="margin-top:4px;margin-bottom:4px;font-size:14px;">截止日期</p>
        <Row>
        <Col span="12">
            <DatePicker type="date" placeholder="Select date" style="width: 200px"></DatePicker>
        </Col>
        <Row>
        <Col span="12">
            <TimePicker format="HH:mm" placeholder="Select time" style="width: 112px"></TimePicker>
        </Col>
    </Row>
    </Row>
    </Modal>
          </div>
      <div class="correct">
          <img src="/src/images/待批改作业.png" alt="">
          <h1>待批改作业</h1>
      </div>
      <div style="margin-left:220px;">
      <Table height="200" :columns="columns1" :data="data1" style="width:800px;"></Table>
      </div>
      <div class="completed">
          <img src="/src/images/已完成作业.png" alt="">
          <h1>已完成作业</h1>
      </div>
      <div style="margin-left:220px;">
      <Table height="200" :columns="columns2" :data="data2" style="width:800px;"></Table>
      </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      modal1: false,
      columns1: [
        {
          title: "作业名",
          key: "name",
          render: (h, params) => {
            return h("div", [h("strong", params.row.name)]);
          }
        },
        {
          title: "发布时间",
          key: "when",
          sortable: true
        },
        {
          title: "操作",
          key: "action",
          width: 170,
          align: "center",
          render: (h, params) => {
            return h("div", [
              h(
                "Button",
                {
                  props: {
                    type: "primary",
                    size: "small"
                  },
                  style: {
                    marginRight: "5px"
                  },
                  on: {
                    click: () => {
                      this.show(params.index);
                    }
                  }
                },
                "批改"
              ),
              h(
                "Button",
                {
                  props: {
                    type: "success",
                    size: "small"
                  },
                  on: {
                    click: () => {
                      this.remove(params.index);
                    }
                  }
                },
                "解答"
              )
            ]);
          }
        }
      ],
      data1: [
        {
          name: "第一章第一次作业",
          when: 20171002
        },
        {
          name: "第一章第一次作业",
          when: 20171002
        },
        {
          name: "第一章第一次作业",
          when: 20171002
        },
        {
          name: "第一章第一次作业",
          when: 20171003
        }
      ],
      columns2: [
        {
          title: "作业名",
          key: "name",
          render: (h, params) => {
            return h("div", [h("strong", params.row.name)]);
          }
        },
        {
          title: "发布时间",
          key: "when",
          sortable: true
        },
        {
          title: "操作",
          key: "action",
          width: 170,
          align: "center",
          render: (h, params) => {
            return h("div", [
              h(
                "Button",
                {
                  props: {
                    type: "primary",
                    size: "small"
                  },
                  style: {
                    marginRight: "5px"
                  },
                  on: {
                    click: () => {
                      this.show(params.index);
                    }
                  }
                },
                "完成情况"
              ),
              h(
                "Button",
                {
                  props: {
                    type: "error",
                    size: "small"
                  },
                  on: {
                    click: () => {
                      this.remove(params.index);
                    }
                  }
                },
                "未解答"
              )
            ]);
          }
        }
      ],
      data2: [
        {
          name: "第一章第一次作业",
          when: 20171002
        },
        {
          name: "第一章第一次作业",
          when: 20171002
        },
        {
          name: "第一章第一次作业",
          when: 20171002
        },
        {
          name: "第一章第一次作业",
          when: 20171003
        }
      ]
    };
  },
  methods: {
    ok() {
      this.$Message.info("作业发布成功");
    },
    cancel() {
      this.$Message.info("取消发布");
    }

    // render() {
    //   this.$Modal.confirm({
    //     render: h => {
    //       return h("div", [
    //         h("Input", {
    //           props: {
    //             value: this.value,
    //             autofocus: true,
    //             placeholder: "这里输入作业标题"
    //           }
    //         }),
    //         h("content", {
    //             props: {
    //             value: this.value,
    //             autofocus: true,
    //             placeholder: "这里输入作业内容"
    //           }
    //         })
    //       ]);
    //     }
    //   });
    // }
  }
};
</script>

<style scoped>
.container {
  margin-left: -100px;
  margin-top: -10px;
  padding: 0;
  height: 600px;
  /* border:5px solid red; */
}

.container h1 {
  margin-left: 10px;
  margin-bottom: 10px;
  margin-top: 10px;
  font-size: 20px;
  /* float: left; */
  color: black;
  font-family: Microsoft YaHei;
  font-weight: normal;
  letter-spacing: 0;
}

.container img {
  float: left;
  height: 30px;
  width: 30px;
  margin-left: 540px;
}

.publish {
  margin-left: 1160px;
}
</style>

