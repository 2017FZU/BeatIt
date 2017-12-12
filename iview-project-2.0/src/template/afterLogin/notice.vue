<template>
  <div class="container">
    <div class="publish">
      <Button type="primary" icon="paper-airplane" @click="modal1 = true">发布通知</Button>
      <Modal v-model="modal1" title="发布通知" @on-ok="ok" @on-cancel="cancel">
        <p style="margin-top:4px;margin-bottom:4px;font-size:14px;">通知内容</p>
        <textarea v-model="noticeContent" name="" id="" cols="66" rows="6" style="font-size:14px;"></textarea>
      </Modal>
    </div>
    <div style="margin-left:220px;">
      <Table height:200 border :columns="columns1" :data="noticeList" style="width:800px;"></Table>
    </div>

  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      noticeContent: " ",
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
      noticeList: []
    };
  },
  mounted() {
    const that = this;
    axios
      .post("http://111.231.190.23/web/getNoticeList?cid="+that.$store.getters.getCid)
      .then(function(res) {
        that.noticeList = res.data.data.noticeList;
      });
  },
  methods: {
    ok() {
      const that = this;
      axios
        .post(
          'http://111.231.190.23/web/addNotice?cid='+that.$store.getters.getCid+'&title="oj8k"&content="' +
            this.noticeContent +
            '"'
        )
        .then(function(res) {
          console.log(res);
        })
        .catch(function(err) {
          console.log(err);
        });
      this.$Message.success("通知发布成功!");
      location.reload();
    },
    cancel() {
      this.$Message.info("取消发布");
    },
    remove(index) {
      const that = this;
      axios
        .post(
          "http://111.231.190.23/web/UpdateNotice?nid=" +
            this.noticeList[index].nid +
            "&tid=1&op=0"
        )
        .then(function(res) {
          console.log(res);
        })
        .catch(function(err) {
          console.log(err);
        });
      this.$Message.success("通知删除成功!");
      location.reload();
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

