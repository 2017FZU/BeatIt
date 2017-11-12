<template>
  <div class="container">
    <div class="publish">
      <Button type="primary" icon="paper-airplane" @click="modal1 = true">发布通知</Button>
      <Modal v-model="modal1" title="发布通知" @on-ok="ok" @on-cancel="cancel">
        <p style="margin-top:4px;margin-bottom:4px;font-size:14px;">通知内容</p>
        <textarea name="" id="" cols="66" rows="6" style="font-size:14px;"></textarea>
      </Modal>
    </div>
    <div style="margin-left:220px;">
      <Table height:200 border :columns="columns1" :data="data1" style="width:800px;"></Table>
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
          title: "通知时间",
          key: "time",
          sortable: true
        },
        {
          title: "通知内容",
          key: "content",
          render: (h, params) => {
            return h("div", [h("strong", params.row.content)]);
          }

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
                "修改"
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
                "删除"
              )
            ]);
          }
        }
      ],
      data1: [
        {
          time: 20171002,
          content: "明晚七点东三100补课"
        },
        {
          time: 20171002,
          content: "明晚七点东三100补课"
        },
        {
          time: 20171002,
          content: "明晚七点东三100补课"
        },
        {
          time: 20171002,
          content: "明晚七点东三100补课"
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

.publish {
  margin-left: 1160px;
}
</style>

