<template>
    <div class="main">
        <my-head></my-head>
        <div class="layout">
            <div class="layout-content">
                <!-- <Row>
                    <Col span="5">
                        <Menu width="200px" :open-names="['1','2','3']">
                            <Submenu name="1">
                                <template slot="title">
                                    <Icon type="ios-paper"></Icon>
                                    课程作业
                                </template>
                                
                            </Submenu>
                            <Submenu name="2">
                                <template slot="title">
                                    <Icon type="ios-people"></Icon>作业批改
                                </template>
                              
                            </Submenu>
                            <Submenu name="3">
                                <template slot="title">
                                    <Icon type="ios-pricetag-outline"></Icon>
                                    学生总评
                                </template>
                                
                            </Submenu>
                            <Submenu name="4">
                                <template slot="title">
                                    <Icon type="social-wordpress-outline"></Icon>
                                    作业解答
                                </template>
                                <Menu-item name="4-1">
                                    <a href="http://www.west2gold.com/" style="color:#495060">西二在线</a>
                                </Menu-item>
                                <Menu-item name="4-2">意见反馈</Menu-item>
                            </Submenu>
                            <Submenu name="5">
                                <template slot="title">
                                    <Icon type="social-wordpress-outline"></Icon>
                                    课程资料
                                </template>
                                <Menu-item name="5-1">
                                    <a href="http://www.west2gold.com/" style="color:#495060">西二在线</a>
                                </Menu-item>
                                <Menu-item name="5-2">意见反馈</Menu-item>
                            </Submenu>
                            <Submenu name="6">
                                <template slot="title">
                                    <Icon type="social-wordpress-outline"></Icon>
                                    课程通知
                                </template>
                                <Menu-item name="6-1">
                                    <a href="http://www.west2gold.com/" style="color:#495060">西二在线</a>
                                </Menu-item>
                                <Menu-item name="6-2">意见反馈</Menu-item>
                            </Submenu>
                        </Menu>
                    </Col>
                    <Col span="19">
                        <div class="layout-content-main">
                            <router-view></router-view>
                        </div>
                    </Col>
                </Row> -->
                <Row>
                    <div class="layout-content-topbar">

                            <div class="layout-title">作业来啦</div>
                            <div class="layout-inform">
                                <img src="../images/教师头像.png" alt="">
                                <span>Welcome，XX老师</span>
                            </div>
                    </div>
                </Row>
                <Row>
                    <div class="layout-content-main">
                        <img src="../images/首页图片.png" alt="">
                        <span>作业信息化时代来了</span>
                        <span>Homework is coming</span>
                    </div>    
                </Row> 
                <Row>
                    <div class="layout-content-class">
                        <p>我的课程</p>
                        <img id="img" src="../images/我的课程_横线.png" alt="">
                        <p><img src="../images/新建课堂_图标.png" alt="">添加课程</p>
                        <div class="layout-content-class-row">
                            <div class="layout-content-class-row-one">
                                <p>高等数学B</p>
                            </div>
                            <div class="layout-content-class-row-one">
                                <p>高等数学B</p>
                            </div>
                            <div class="layout-content-class-row-one">
                                <p>高等数学B</p>
                            </div>
                            
                        </div>
                    </div>
                </Row>
            </div>
        </div>
    </div>
</template>

<script>
export default {
  data() {
    return {
      
    };
  },
  methods: {
    judgePro() {
      if (this.$store.getters.getLevel >= 3) {
        this.$router.push({ name: "projectList" });
      }
    },
    judgeView() {
      if (this.$store.getters.getLevel >= 5) {
        this.$router.push({ name: "viewData" });
      }
    }
  },
  components: {
    myHead: require("../template/afterLogin/header")
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
.layout-content-topbar{
  display: flex;
  align-items: center;
  padding: 20px 50px;
  background-image: linear-gradient(-90deg, #9FC1FE 0%, #AFD7FD 48%, #BDE9FC 100%);
}

.layout-content-topbar .layout-title{
  width: 220px;
  font-size: 48px;
  color: white;
}

.layout-content-topbar .layout-inform{
  width:300px; 
  margin-left: auto;
  margin-right: 0;
  display: flex;
  align-items: center;

}
.layout-content-topbar .layout-inform img{
  border-radius: 50%;
}
.layout-content-topbar .layout-inform span{
  font-family: PingFangSC-Regular;
  font-size: 24px;
  color: #655E68;
  margin-left: 5px;
}
.layout-content-main{
  position: relative;
  height: 346px;
}
.layout-content-main span:nth-child(2){
  position: absolute;
  top: 70px;
  left: 146px;
  font-family: WeibeiSC-Bold;
  font-size: 48px;
  color: #4A4A4A;
}
.layout-content-main span:nth-child(3){
  position: absolute;
  top: 220px;
  left: 146px;
  font-family: WeibeiSC-Bold;
  font-size: 48px;
  color: #4A4A4A;
}
.layout-content-main img{
  width: 100%;
  height: 100%;
}
.layout-content-class{
  padding: 0 20px;
}
.layout-content-class p:first-child{
  line-height: 50px;
  text-align: center;
  font-family: PingFangSC-Regular;
  font-size: 36px;
  color: #4A4A4A;
}
.layout-content-class p:nth-child(3){
  display: flex;
  align-items: center;
  justify-content:flex-end;
  font-family: PingFangSC-Regular;
  font-size: 24px;
  color: #4A4A4A;
}
.layout-content-class #img{
  width: 146px;
  margin: auto;
  display: inherit;
}
.layout-content-class-row{
  margin-top: 20px;
  display: flex;
  justify-content: space-around; 
}
.layout-content-class-row-one{
  display: flex;
  align-items: center;
  width: 190px;
  height: 186px;
  background-image: url(../images/课程背景框.png);
}
.layout-content-class-row-one p{
  text-align: center;
  width: 100%;
  font-family: PingFangSC-Regular;
  font-size: 30px;
  color: #000000;
  line-height: 42px;
}
</style>