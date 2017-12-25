<template>
    <div class="main">
        <my-head></my-head>
        <div class="layout">
            <div class="layout-content">
                <Row>
                    <div class="layout-content-main">
                        <img src="/src/images/首页图片.png" alt="">
                        <span>作业信息化时代来了</span>
                        <span>Homework is coming</span>
                    </div>    
                </Row> 
                <Row>
                    <div class="layout-content-class">
                        <p>我的课程</p>
                        <img id="img" src="/src/images/我的课程_横线.png" alt="">
                        <p><Button type="primary" size="large" @click="newClassButton = true">新建课堂</Button></p>
                        <Modal v-model="newClassButton" title="新建课堂" @on-ok="createClass">
                          <p class="newClass">
                            <label for="">课程名</label>
                            <Input type="text" v-model="courseName"></Input>
                          </p>
                        </Modal>
                        <div class="layout-content-class-row">
                            <div class="layout-content-class-row-one" v-for="item in classList" :key="item.cid">
                                <a @click="click($event,item.cid)">{{item.cname}}</a>
                            </div>                            
                        </div>
                    </div>
                </Row>
            </div>
        </div>
    </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      newClassButton: false,
      classList: " ",
      courseName: " "
    };
  },
  mounted() {
    const that = this;
    axios
      .post("http://111.231.190.23/web/getClassList?tid="+that.$store.getters.getTid)
      // .then(response => {
      //   this.classList = response.data.data.classList
      //   })
      .then(function(response) {
        that.classList = response.data.data.classList;
        console.log(response.data.data.classList);
      });
  },
  methods: {
    click(e, cid) {
      this.$store.commit("setCid", cid);
      var cn = e.currentTarget.innerHTML;
      this.$store.commit("setCourseName", cn);
      this.$router.push({ name: "assignment" });
    },
    createClass() {
      axios
        .post(
          'http://111.231.190.23/web/CreateClass?tid='+that.$store.getters.getTid+'&cname="' +
            this.courseName +
            '"'
        )
        .then(function(response) {
          console.log(response);
        })
        .catch(function(error) {
          console.log(error);
        });
      this.$Message.info("创建课堂成功！");
      location.reload();
    }
  },
  components: {
    myHead: require("../template/afterLogin/indexHeader")
  }
};
</script>

<style scoped>
.layout {
  border: 1px solid #d7dde4;
  background: #f2f2f3;
  height: 686px;
}

.layout-content {
  min-height: 200px;
  margin: 15px;
  overflow: hidden;
  background: #fff;
  border-radius: 4px;
}

.layout-copy {
  text-align: center;
  padding: 10px 0 20px;
  color: #9ea7b4;
}

/*LFH*/
.layout-content-topbar {
  display: flex;
  align-items: center;
  padding: 20px 50px;
  background-image: linear-gradient(
    -90deg,
    #9fc1fe 0%,
    #afd7fd 48%,
    #bde9fc 100%
  );
}
.layout-content-topbar .layout-title {
  width: 220px;
  font-size: 48px;
  color: white;
}
.layout-content-topbar .layout-inform {
  width: 300px;
  margin-left: auto;
  margin-right: 0;
  display: flex;
  align-items: center;
}
.layout-content-topbar .layout-inform img {
  border-radius: 50%;
}
.layout-content-topbar .layout-inform span {
  font-family: PingFangSC-Regular;
  font-size: 24px;
  color: #655e68;
  margin-left: 5px;
}
.layout-content-main {
  position: relative;
  height: 500px;
}
.layout-content-main span:nth-child(2) {
  position: absolute;
  top: 70px;
  left: 146px;
  font-family: WeibeiSC-Bold;
  font-size: 48px;
  color: #4a4a4a;
}
.layout-content-main span:nth-child(3) {
  position: absolute;
  top: 220px;
  left: 146px;
  font-family: WeibeiSC-Bold;
  font-size: 48px;
  color: #4a4a4a;
}
.layout-content-main img {
  width: 100%;
  height: 100%;
}
.layout-content-class {
  padding: 0 20px;
  margin-top: 20px;
}
.newClass {
  display: flex;
}
.newClass title {
  font-size: 30px;
}
.newClass label {
  width: 64px;
  font-family: PingFangSC-Regular;
  font-size: 16px;
  color: #4a4a4a;
}
.layout-content-class p:first-child {
  line-height: 50px;
  text-align: center;
  font-family: PingFangSC-Regular;
  font-size: 36px;
  color: #4a4a4a;
}
.layout-content-class p:nth-child(3) {
  display: flex;
  align-items: center;
  margin-left: 1200px;
  font-family: PingFangSC-Regular;
  font-size: 24px;
  color: #4a4a4a;
}
.layout-content-class #img {
  width: 146px;
  margin: auto;
  display: inherit;
}
.layout-content-class-row {
  margin-top: 20px;
  padding: 4px 68px 4px 68px;
  display: flex;
  justify-content: space-around;
  flex-wrap: wrap;
}
.layout-content-class-row-one {
  display: flex;
  align-items: center;
  width: 20%;
  height: 186px;
  background-image: url(../images/课程背景框.png);
  background-repeat: no-repeat;
  background-position: center;
}
.layout-content-class-row-one a {
  text-align: center;
  width: 100%;
  font-family: PingFangSC-Regular;
  font-size: 30px;
  color: #000000;
  line-height: 42px;
}
</style>