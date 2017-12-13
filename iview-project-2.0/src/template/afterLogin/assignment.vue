<template>
  <div class="container">
    <div class="publish">
      <Button type="primary" icon="paper-airplane" @click="modal1 = true">发布作业</Button>
      <Modal v-model="modal1" title="发布作业" @on-ok="ok" @on-cancel="cancel">
        <label for="">作业标题</label>
        <Input v-model="newTitle"></Input>
        <label for="">作业内容</label>
        <Input type="textarea" :autosize="true" v-model="newContent"></Input>
        <RadioGroup v-model="type" style="margin:10px 0">
        <Radio label="on">
            <span>线上提交</span>
        </Radio>
        <Radio label="down">
            <span>线下提交</span>
        </Radio>
    </RadioGroup>
        <p style="margin-top:4px;margin-bottom:4px;font-size:14px;">截止日期</p>
        <Row>
          <Col span="18">
            <DatePicker v-model="newDeadline" type="datetime" placeholder="Select date" style="width: 200px" ></DatePicker>
          </Col>
        </Row>
      </Modal>
    </div>
    <div class="correct">
      <img src="/src/images/待批改作业.png" alt="">
      <h1>待批改作业</h1>
    </div>
    <div style="margin-left:220px;">
      <Table height="200" :columns="columns1" :data="data1" @on-row-click="jump" style="width:800px;"></Table>
      <Modal v-model="modal2" title="作业详情">
        <p class="detailCss">作业标题</p>
        <p>{{detailTitle}}</p>
        <p class="detailCss">作业内容</p>
        <p>{{detailContent}}</p>
        <p class="detailCss">截止日期</p>
        <p>{{detailDeadline}}</p>
      </Modal>
    </div>
    <div class="completed">
      <img src="/src/images/已完成作业.png" alt="">
      <h1>已批改作业</h1>
    </div>
    <div style="margin-left:220px;">
      <Table height="200" :columns="columns2" :data="data2" style="width:800px;"></Table>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      type: 'on',
      newDeadline: {},
      newTitle: "",
      newContent: "",
      modal1: false,
      modal2: false,
      detailTitle: 1,
      detailContent: 2,
      detailDeadline: 3,
      columns1: [
        {
          title: "作业名",
          key: "content",
          render: (h, params) => {
            return h("div", [h("strong", params.row.title)]);
          }
        },
        {
          title: "发布时间",
          key: "deadline",
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
                      // this.show(params.index);
                      javascript: location.href =
                        "correct?index=" +
                        params.index +
                        "&wid=" +
                        this.data1[params.index].wid;
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
                      // this.remove(params.index);
                      javascript: location.href =
                        "solution?index=" + params.index;
                    }
                  }
                },
                "解答"
              )
            ]);
          }
        }
      ],
      data1: [],
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
      data2: []
    };
  },

  mounted() {
    const that = this;
    axios
      .post(
        "http://111.231.190.23/web/getHomeWorkList?cid=" +
          that.$store.getters.getCid
      )
      .then(function(res) {
        console.log(res.data);
        that.data1 = res.data.data.homeWorkList;
      });
  },
  methods: {
    jump: function(res) {
      this.detailTitle = res.title;
      this.detailContent = res.content;
      this.detailDeadline = res.deadline;
      this.modal2 = true;
    },
    toFormat(num) {
      if (num < 10) {
        return "0" + num.toString();
      } else {
        return num.toString();
      }
    },
    ok() {
      var mes =
        "http://111.231.190.23/web/addHomeWork?cid=" +
        this.$store.getters.getCid +
        '&title="' +
        this.newTitle +
        '"&content="' +
        this.newContent +
        '"&online=1&deadline="';
      mes +=
        this.toFormat(this.newDeadline.getFullYear()) +
        this.toFormat(this.newDeadline.getMonth() + 1) +
        this.toFormat(this.newDeadline.getDate()) +
        this.toFormat(this.newDeadline.getHours()) +
        this.toFormat(this.newDeadline.getMinutes()) +
        this.toFormat(this.newDeadline.getSeconds());
      mes += '"';

      axios.post(mes).then(function(res) {
        console.log(res);
      });
      location.reload();
      this.$Message.info("发布成功");
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
    //       ])
    //     }
    //   })
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

.detailCss {
  font-size: 16px;
  font-weight: bold;
  margin-bottom: 4px;
}

.publish {
  margin-left: 1160px;
}
</style>

