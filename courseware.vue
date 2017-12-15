<template>
  <div class="container">
    <div class="publish">
      <Button type="ghost" id="upload" icon="ios-cloud-upload-outline" @click="uploadModal=true">上传</Button>  
       <!-- <Upload multiple action="http://111.231.190.23/web/UploadCourseFile" data="{tid:1,cid:1}">
        <Button name="file" id="upload" type="ghost" icon="ios-cloud-upload-outline">上传</Button>
      </Upload>        -->
      <Modal v-model="uploadModal" title="上传资料" ok-text="" cancel-text="">
        <form method="post" action="http://111.231.190.23/web/UploadCourseFile" enctype="multipart/form-data">
          <div class="fileList" id="fileList"></div>
          <input type="hidden" name="cid" value="1">
          <input type="hidden" name="tid" value="1">
          <div class="fileButton">
            <input type="file" id="filePlace" multiple="multiple" name="file" @change="addFilesName">
            <input type="submit" value="提交" >
          </div>
        </form>  
      </Modal>  
        <!-- <Upload action="http://111.231.190.23/web/UploadCourseFile">
        <Button type="ghost" icon="ios-cloud-upload-outline">Upload files</Button>
    </Upload> -->
    </div>
    <div class="container-main">
      <div class="row">
        <div class="data" v-for="item in classData" :key="item.cfid">
          <a :href="item.url"><img src="../../images/word.png" alt=""></a>
          <p>{{item.cfname}}</p>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import axios from "axios";

export default {
  data() {
    return {
      classData: " ",
      uploadModal: false
    };
  },
  mounted() {
    axios.post("http://111.231.190.23/web/getCourseList?cid=1").then(res => {
      this.classData = res.data.data.courseList;
      console.log(res.data.data);
    });
  },
  methods: {
    jump() {
      this.$router.go(-1);
    },
    addFilesName(){
      var file = document.getElementById("filePlace")
      var fileList = document.getElementById("fileList")
      fileList.innerHTML = ""
      for(var i = 0; i < file.files.length; i++){
        fileList.innerHTML += file.files[i].name + "<br />"
      }
    }
  }
};
</script>

<style scoped>
.container {
  margin-left: -100px;
  margin-top: -10px;
  padding: 0;
  min-height: 600px;
}

.publish > p > img {
  position: relative;
  margin-right: 5px;
}
.publish #upload {
  display: flex;
  align-items: center;
  justify-content: space-around;
  position: relative;
  left: 1000px;
  height: 42px;
  width: 110px;
  border-radius: 20px;
  background-color: rgb(126, 211, 33);
  color: white;
  font-size: 20px;
}
.fileList {
  min-height: 150px;
  border: 1px solid rgb(190, 190, 190);
  padding: 10px;
}
.fileButton {
  display: flex;
  justify-content: space-around;
  margin-top: 30px;
  padding: 0 15px 0 15px;
}
.fileButton input:nth-child(1) {
  width: 200px;
  color: blue;
}
.fileButton input:nth-child(2) {
  width: 50px;
}
.container-main {
  padding: 15px 150px;
}

.container-main .row {
  display: flex;
  flex-wrap: wrap;
}
.container-main .row img {
  display: block;
  margin: auto;
}
.container-main .row .data {
  width: 20%;
  margin-top: 10px;
  margin-bottom: 10px;
}

.container-main .row .data p {
  text-align: center;
  font-family: PingFangSC-Regular;
  font-size: 25px;
  color: #4a4a4a;
}
</style>