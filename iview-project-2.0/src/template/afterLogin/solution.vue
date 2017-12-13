<template>
  <div class="container">
        <div class="select_work">
            <img src="/src/images/作业图标.png" alt="">
            <Select @on-change="change" v-model="model1"  style="width:200px;margin-left:6px;margin-top:-20px;">
        <Option v-for="item in cityList" :value="item.value" :key="item.value">{{ item.label }}</Option>
    </Select>
        </div>
        <div style="margin-top:10px;">
            <h1>作业标题:</h1>
            <h1>{{cityList[model1].label}}</h1>
        </div>
        <br>
        <br>
        <div>
            <h1>作业详情:</h1>
            <h1>{{cityList[model1].content}}</h1>
            </div>
            <br>
        <br>
            <div>
            <h1>作业解答:</h1>

    <Upload multiple action="http://111.231.190.23/web/upExplainFile" data="{wid:this.cityList[model1].wid}">
        <Button name="file" type="ghost" icon="ios-cloud-upload-outline"  style="float:left;margin-left:8px;">上传</Button>
      </Upload>
            <div style="margin-top:200px;margin-left:100px;">
            <Button @click="ok" type="success"  icon="checkmark-round">
        <span >完成解答</span>
    </Button>   
    </div>         
            </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      cityList: [],
      test: [],
      model1: 0
    };
  },
  methods: {
    change: function(res) {
      this.inx = res;
      this.model1 = res;
    },
    ok() {
      this.$Message.info("解答成功");
    }
  },
  mounted() {
    if (module.hot) {
      module.hot.accept();
    }
    const that = this;
    var passIndex = this.$route.query.index;
    if (passIndex >= 0) {
      that.model1 = passIndex;
    }
    axios
      .post(
        "http://111.231.190.23/web/getHomeWorkList?cid=" +
          that.$store.getters.getCid
      )
      .then(function(res) {
        that.test = res.data.data.homeWorkList;
        for (var i = 0; i < that.test.length; ++i) {
          var tmp = { value: 0, label: "", wid: 0 };
          tmp.value = i;
          tmp.wid = that.test[i].wid;
          tmp.label = that.test[i].title;
          tmp.content = that.test[i].content;
          that.cityList.push(tmp);
        }
      });
  }
};
</script>

<style scoped>
.container h1 {
  line-height: 30px;
  font-size: 20px;
  float: left;
  color: black;
  font-family: Microsoft YaHei;
  font-weight: normal;
  letter-spacing: 0;
}

.container {
  margin-left: 320px;
  height: 600px;
}

.select_work {
  margin-left: 40px;
}
</style>
