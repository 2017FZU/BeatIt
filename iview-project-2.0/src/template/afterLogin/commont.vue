<template>
  <div class="container">
    <Form ref="formValidate" :model="formValidate" :rules="ruleValidate">
</Form>
<div style="margin-left:140px;">
  <img class="pic" src="/src/images/作业总评.png" alt=""><h1>作业总评</h1>
  <Table  border :columns="columns1" :data="data1" style="width:800px;"></Table>
</div>

<div style="margin-left:140px;">
  <img class="pic" src="/src/images/单次作业.png" alt=""><h1>单次作业</h1>
  <Table height="200" :columns="columns2" :data="data2" style="width:800px;"></Table>
</div>
  </div>
</template>



<script>
export default {
  data() {
    return {
      formValidate: {
        search: ""
      },
      ruleValidate: {
        search: [{ required: true, message: " ", trigger: "on-enter" }]
      },
      columns1: [
        {
          title: "学号",
          key: "sno"
        },
        {
          title: "姓名",
          key: "sname"
        },
        {
          title: "完成情况",
          key: "scoreShow"
        },
        {
          title: "得星数",
          key: "allStar"
        },
        {
          title: "优秀作业",
          key: "count"
        }
      ],
      data1: [
      ],
      columns2: [
        {
          title: "作业名",
          key: "name"
        },
        {
          title: "得星数/5",
          key: "score"
        },
        {
          title: "是否优秀作业",
          key: "whether"
        }
      ],
      data2: [
        {
          name: "第一章第一次作业",
          score: "4",
          whether: "是"
        },
        {
          name: "第一章第一次作业",
          score: "4",
          whether: "是"
        },
        {
          name: "第一章第一次作业",
          score: "4",
          whether: "是"
        },
        {
          name: "第一章第一次作业",
          score: "4",
          whether: "是"
        },
        {
          name: "第一章第一次作业",
          score: "4",
          whether: "是"
        },
        {
          name: "第一章第一次作业",
          score: "4",
          whether: "是"
        },
        {
          name: "第一章第一次作业",
          score: "4",
          whether: "是"
        },
        {
          name: "第一章第一次作业",
          score: "4",
          whether: "是"
        },
        {
          name: "第一章第一次作业",
          score: "4",
          whether: "是"
        }
      ]
    };
  },
  // sno: "031502666",
  //         sname: "John Brown",
  //         count: "20/20",
  //         scoreShow: "80/100",
  //         allStar: "5/20"
  mounted () {
    if (module.hot) {
      module.hot.accept();
    }
    var that = this;
    that.data1 = [];
    var tmp= {sno: 0, sname: "",count: 0,scoreShow: "",allStar : 0};
    tmp.sno = this.$route.query.sno;
    tmp.sname = this.$route.query.sname;
    tmp.count = this.$route.query.count;
    tmp.scoreShow = this.$route.query.scoreShow;
    tmp.allStar = this.$route.query.allStar;
    that.data1.push(tmp);
  },
  methods: {
    handleSubmit(name) {
      this.$refs[name].validate(valid => {
        if (!valid) {
          this.$Message.error("搜索不能为空");
        }
      });
    }
  }
};
</script>


<style scoped>
.container {
  height: 600px;
  /* border: 5px solid red; */
}

.container h1 {
  /* margin-left: 20px; */
  margin-bottom: 10px;
  margin-top: 10px;
  font-size: 20px;
  /* float: left; */
  color: black;
  font-family: Microsoft YaHei;
  font-weight: normal;
  letter-spacing: 0;
}

.pic {
  float: left;
  height: 30px;
  width: 30px;
  margin-right: 10px;
}

.search {
  /* float: right; */
  /* margin: 8px 6px; */
  /* right: 20px; */
  width: 170px;
}
</style>

