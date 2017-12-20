<template>
    <div class="login">
        <h2>Login</h2>
        <Form ref="formValidate" :model="formValidate" :rules="ruleValidate" :label-width="60">
            <Form-item label="手机号" prop="user">
                <Input type="text" v-model="formValidate.user" placeholder="Phone number">
                <Icon type="ios-person-outline" slot="prepend"></Icon>
                </Input>
            </Form-item>
            <Form-item label="密码" prop="password" class="password">
                <Input type="password" v-model="formValidate.password" placeholder="Password">
                <Icon type="ios-locked-outline" slot="prepend"></Icon>
                </Input>
            </Form-item>
            <Form-item>
                <a class="forget-password">忘记密码</a>
                <!-- <Button type="success" long @click="$router.push({name:'test'})">登录</Button> -->
                <Button type="success" long @click="handleSubmit('formValidate')">登录</Button>
            </Form-item>
        </Form>
    </div>
</template>

<script>
import axios from "axios";
export default {
  name: "signUpPageOne",
  data() {
    return {
      current: 0,
      formValidate: {
        user: "13075835808",
        password: "123456"
      },
      ruleValidate: {
        user: [{ required: true, message: "手机号不能为空", trigger: "blur" }],
        password: [
          { required: true, message: "请填写密码", trigger: "blur" },
          { type: "string", min: 6, message: "密码长度不能小于6位", trigger: "blur" }
        ]
      }
    };
  },
  methods: {
    handleSubmit(name) {
      var that = this;
      this.$refs[name].validate(valid => {
        if (valid) {
          axios
            .post(
              "http://111.231.190.23/web/userLogin?phone=" +
                that.formValidate.user +
                "&psw=" +
                that.formValidate.password
            )
            .then(function(response) {
              console.log(response);
              that.$store.commit("setToken", response.data.data.session);
              that.$store.commit("setTid", response.data.data.tid),
                that.$store.commit("setTName", response.data.data.tname),
                that.$router.push({
                  name: "detail",
                  params: { userId: response.data.data.tid }
                });
                that.$Message.success("登陆成功！");
            })
            .catch(function(error) {
              console.log(error);
            });
        } else {
          that.$Message.error("error");
        }
      });
    }
  }
};
</script>

<style scoped>
.login {
  padding-top: 60px;
  margin: 0px auto;
  height: 686px;
}

h2 {
  font-size: 26px;
  text-align: center;
}

form {
  width: 360px;
  margin: 20px auto;
  padding-right: 50px;
}

.password {
  margin: 0;
  padding: 0;
}

.forget-password {
  float: right;
  margin: 0;
  padding: 0;
}

button {
  font-size: 14px;
}
</style>
