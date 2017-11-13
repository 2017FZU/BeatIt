<template>
    <div class="main">
        <my-head></my-head>
        <div class="layout">
            <div class="layout-content">
                <Row>
                    <Col span="5">
                        <Menu width="200px" :open-names="['1','2','3']">
                            <Submenu name="1">
                                <template slot="title">
                                    <Icon type="ios-paper"></Icon>
                                    <a @click="$router.push({name:'course_work'})">课程作业</a>
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
                                    <a @click="$router.push({name:'general_comment'})">学生总评</a>
                                </template>
                                
                            </Submenu>
                            <Submenu name="4">
                                <template slot="title">
                                    <Icon type="social-wordpress-outline"></Icon>
                                    <a @click="$router.push({name:'solution'})">作业解答</a>
                                </template>
                            </Submenu>
                            <Submenu name="5">
                                <template slot="title">
                                    <Icon type="social-wordpress-outline"></Icon>
                                    课程资料
                                </template>                               
                            </Submenu>
                            <Submenu name="6">
                                <template slot="title">
                                    <Icon type="social-wordpress-outline"></Icon>
                                    <a @click="$router.push({name:'notice'})">课程通知</a>
                                </template>
                            </Submenu>
                        </Menu>
                    </Col>
                    <Col span="19">
                        <div class="layout-content-main">
                            <router-view></router-view>
                        </div>
                    </Col>
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
  components: {
    myHead: require("./header")
  }
};
</script>

<style scoped>
.main {
    height: 600px;
    width: 100%;
    padding: 0;
    margin: 0;
    background: #fdfdfc;
}

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

.layout-content-main {
  padding: 25px 0;
}

.layout-copy {
  text-align: center;
  padding: 10px 0 20px;
  color: #9ea7b4;
}
</style>