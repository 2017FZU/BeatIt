<template>
    <header>
        <div class="container">
            <h1>作业来了</h1>
            <nav>
                <img src=".../img/教师头像.png"></img>
                <h1 style="margin-right:20px;">Welcome,张老师</h1>
                
                <Dropdown trigger="click" placement="bottom-end">
                    <Tooltip content="账户设置" placement="bottom-end" :disabled="disabled">
                        <a @click="disabled = true">
                            <Icon type="navicon-round"></Icon>
                        </a>
                    </Tooltip>
                    <Dropdown-menu slot="list" class="Dropdown-menu">
                        <Dropdown-item style="padding:7px 0">
                            <a @click="$router.push({name:'userInfoChg'})">个人资料</a>
                        </Dropdown-item>
                        <Dropdown-item style="padding:7px 0">
                            <a @click="$router.push({name:'passwdChg'})">修改密码</a>
                        </Dropdown-item>
                        <Dropdown-item style="padding:7px 0">
                            <a @click="handleLogout()">注销</a>
                        </Dropdown-item>
                    </Dropdown-menu>
                </Dropdown>
            </nav>
        </div>
    </header>
</template>
<script>
export default {
  data() {
    return {
      disabled: false
    };
  },
  methods: {
    handleLogout() {
      this.$store.commit("logout"), this.$router.push({ name: "signIn" });
    }
  }
};
</script>

<style scoped>
header {
  height: 48px;
  background: #9fc1fe;
  padding-left: 24px;
}

header .container {
  height: 34px;
  margin: 0 auto;
}

header h1 {
  line-height: 48px;
  font-size: 20px;
  float: left;
  color: white;
  font-family: Microsoft YaHei;
  font-weight: normal;
  letter-spacing: 0;
}

header img {
  width: 34px;
  height: 34px;
  border-radius: 100px;
  margin: 7px;
  float: left;
}

header nav {
  float: right;
  font-size: 26px;
  line-height: 48px;
  margin-right: 30px;
}

header nav a {
  margin: 2px;
  color: white;
}

header nav a:hover {
  opacity: 0.9;
}

.Dropdown-menu a {
  color: #666;
  height: 100%;
  width: 100%;
  padding: 8px 26px;
}
</style>
