<template>
  <div class="container">
    <img src="/src/images/学生图标.png" alt="" style="float:left;margin-right:10px;margin-left:140px;">
    <h1>学生列表</h1>
    <Form ref="formValidate" :model="formValidate">
      <Form-item prop="search">
        <Input v-model="formValidate.search" placeholder="输入姓名或学号" class="search" @on-change="handleSubmit"></Input>
      </Form-item>
    </Form>
    <div style="margin-left:140px;">
      <Table border :columns="columns1" :data="stuLList" :stripe="showIndex" @on-row-click="jump" style="width:800px;"></Table>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  data() {
    return {
      showIndex: true,
      formValidate: {
        search: ""
      },
      columns1: [
        {
          title: "学号",
          key: "sno",
          sortable: true
        },
        {
          title: "姓名",
          key: "sname"
        },
        {
          title: "完成情况",
          key: "scoreShow",
          sortable: true
        },
        {
          title: "得星数",
          key: "allStar",
          sortable: true
        },
        {
          title: "优秀作业",
          key: "count",
          sortable: true
        }
      ],
      stuList: [],
      stuLList: [] 
    }
  },
  methods: {
    handleSubmit: function(){
      const that = this
      if (!that.formValidate.search){
        this.stuLList = this.stuList
        return 
      }
      var tempList =  this.stuList.filter(function(item) {
        // if(item.sname == that.formValidate.search||item.sno == that.formValidate.search){
        //   return true
        // }
        if (item.sname.match(that.formValidate.search,".*")||item.sno.match(that.formValidate.search,".*"))
          return true
      })
      this.stuLList = tempList
    },
    jump: function(res) {
      console.log(res);
      javascript: location.href = 'commont?sno=' + res.sno + '&sname=' + res.sname + '&scoreShow=' + res.scoreShow +
        '&allStar=' + res.allStar + '&count=' + res.count
    }
  },
  mounted() {
    const that = this
    axios.post('http://111.231.190.23/web/getAllStudent?cid='+that.$store.getters.getCid)
      .then(function(res) {
        that.stuList = res.data.data.allStudent
        that.stuLList = that.stuList
      })
    
  }
}
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
  float: left;
  color: black;
  font-family: Microsoft YaHei;
  font-weight: normal;
  letter-spacing: 0;
}

.search {
  float: left;
  margin-top: 8px;
  margin-left: 510px;
  width: 170px;
}
</style>


