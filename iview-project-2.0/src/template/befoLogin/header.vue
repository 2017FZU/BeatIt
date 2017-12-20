<template>
  <header>
    <div class="container">
      <h1>作业来了</h1>
      <nav>
        <a @click="$router.push({name:'signIn'}), SignIn()" :class="{choosen:ifSin}">Sign in</a>
        <span>|</span>
        <a @click="$router.push({name:'signUp'}), SignUp()" :class="{choosen:ifSup}">Sign up</a>
      </nav>
      <!-- <Form ref="formValidate" :model="formValidate" :rules="ruleValidate">
        <Form-item prop="search">
          <Input v-model="formValidate.search" placeholder="search here" class="search" @on-enter="handleSubmit('formValidate')"></Input>
        </Form-item>
      </Form> -->
    </div>
  </header>
</template>

<script>
export default {
  data() {
    return {
      formValidate: {
        search: ''
      },
      ruleValidate: {
        search: [
          { required: true, message: ' ', trigger: 'on-enter' }
        ]
      },
      ifSup:'' ,
      ifSin:''
    }
  },
  methods: {
    handleSubmit(name) {
      this.$refs[name].validate((valid) => {
        if (!valid) {
          this.$Message.error('搜索不能为空');
        }
      })
    },
    SignUp() {
      this.ifSin = false;
      this.ifSup =true;
    },
    SignIn() {
      this.ifSin = true;
      this.ifSup = false;
    }
  }
}
</script>

<style scoped>
header {
  height: 48px;
  background: #9fc1fe;
  
}

header .container {
  width: 100%;
  height: 34px;
  margin: 0 auto;
  float: left;
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


header .search {
  float: right;
  margin: 8px 6px;
  right: 20px;
  width: 170px;
}

header nav {
  float: right;
  font-size: 17px;
  line-height: 48px;
  margin-right: 40px;
}


header nav a {
  color: white;
}

.choosen {
  color: #FF7F00;
}

header nav span {
  color: white;
  padding: 0 5px;
}
</style>
