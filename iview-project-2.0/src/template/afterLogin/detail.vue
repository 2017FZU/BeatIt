<template>
    <div class="main">
        <my-head></my-head>
        <div class="layout">
            <div class="layout-content">
                <Row>
                    <Col span="5">
                        <Menu width="200px" >
                            <MenuGroup title="课程管理">
            <MenuItem name="1">
                <Icon type="android-notifications-none"></Icon>
                <a @click="$router.push({name:'notice'})">课程通知</a>
            </MenuItem>
            <MenuItem name="2">
                <Icon type="document"></Icon>
               <a @click="$router.push({name:'assignment'})">课程作业</a>
            </MenuItem>
            <MenuItem name="3">
                <Icon type="ios-folder-outline"></Icon>
               <a @click="$router.push({name:'courseware'})">课程资料</a>
            </MenuItem>
        </MenuGroup>
        <MenuGroup title="学生管理">
            <MenuItem name="4">
                <Icon type="android-open"></Icon>
                <a @click="$router.push({name:'correct'})">作业批改</a>
            </MenuItem>
            <MenuItem name="5">
                <Icon type="help"></Icon>
               <a @click="$router.push({name:'solution'})">作业解答</a>
            </MenuItem>
            <MenuItem name="6">
                <Icon type="ios-pricetag-outline"></Icon>
               <a @click="$router.push({name:'studentList'})">学生总评</a>
            </MenuItem>
        </MenuGroup>
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
    return {};
  },
  components: {
    myHead: require("./courseHeader")
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