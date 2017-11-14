<template>
  <div class="container">
    <img src="/src/images/搜索图标.png" alt="" style="float:left;margin-top:4px;margin-left:400px;margin-right:8px;">
    <Form ref="formValidate" :model="formValidate" :rules="ruleValidate">
	<Form-item prop="search">
		<Input v-model="formValidate.search" placeholder="输入姓名或学号" class="search" @on-enter="handleSubmit('formValidate')"></Input>
	</Form-item>
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
          key: "id"
        },
        {
          title: "姓名",
          key: "name"
        },
        {
          title: "完成情况",
          key: "all"
        },
        {
          title: "得星数",
          key: "star"
        },
        {
          title: "优秀作业",
          key: "nice"
        }
      ],
      data1: [
        {
          id: "031502666",
          name: "John Brown",
          all: "20/20",
          star: "80/100",
          nice: "5/20"
        }
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

