<template>
  <div class="container">
    <div class="select_work">
      <img src="/src/images/作业图标.png" alt="">
      <Select v-model="model1" style="width:200px;margin-left:20px;margin-top:-20px;">
        <Option v-for="item in cityList" :value="item.value" :key="item.value">{{ item.label }}</Option>
      </Select>
    </div>
    <div style="margin-top:10px;">
      <h1>作业标题</h1> <input type="text" name="" id="" style="font-size:14px;margin-left:20px;margin-top:8px;width:48%;height:28px;">
    </div>
    <div>
      <h1>作业详情</h1>
      <textarea name="textarea1" id="1" cols="67" rows="10" style="font-size:14px;margin-left:20px;margin-top:10px;"></textarea>
    </div>
    <div>
      <h1>作业解答</h1>
      <Upload action="//jsonplaceholder.typicode.com/posts/">
        <Button type="ghost" icon="ios-cloud-upload-outline" style="float:left;margin-left:20px;">Upload files</Button>
      </Upload>
      <textarea name="textarea1" id="1" cols="67" rows="10" style="font-size:14px;margin-left:100px;"></textarea>
      <div style="margin-left:290px;">
        <Button type="success" icon="checkmark-round">
          <span>完成解答</span>
        </Button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      cityList: [
        {
          value: "第一章习题一",
          label: "第一章习题一"
        },
        {
          value: "第一章习题二",
          label: "第一章习题二"
        },
        {
          value: "第一章习题三",
          label: "第一章习题三"
        },
        {
          value: "第二章习题一",
          label: "第二章习题一"
        },
        {
          value: "第二章习题二",
          label: "第二章习题二"
        },
        {
          value: "第二章习题三",
          label: "第二章习题三"
        }
      ],
      model1: ""
    };
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
  margin-left: 160px;
  height: 600px;
}

.select_work {
  margin-left: 38px;
}
</style>
