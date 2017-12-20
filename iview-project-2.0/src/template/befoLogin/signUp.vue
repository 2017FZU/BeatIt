<template>
    <div class="sign-up">
        <h2>Register</h2>
        <Form ref="formValidate" :model="formValidate" :rules="ruleValidate" :label-width="100">
            <Form-item label="用户名" prop="nickname">
                <Input type="text" v-model="formValidate.nickname" placeholder="user name">
                <Icon type="ios-person-outline" slot="prepend"></Icon>
                </Input>
            </Form-item>
            <Form-item label="手机号" prop="phone">
                <Input type="text" v-model="formValidate.phone" placeholder="Phone">
                <Icon type="ios-person-outline" slot="prepend"></Icon>
                </Input>
            </Form-item>
            <Form-item label="邮箱" prop="email">
                <Input type="text" v-model="formValidate.email" placeholder="Email">
                <Icon type="ios-email-outline" slot="prepend"></Icon>
                </Input>
            </Form-item>
            <Form-item label="密码" prop="password">
                <Input type="password" v-model="formValidate.password" placeholder="Password">
                <Icon type="ios-locked-outline" slot="prepend"></Icon>
                </Input>
            </Form-item>
            <Form-item label="验证码" prop="vcode">
                <Input type="text" v-model="formValidate.vcode" placeholder="vcode">
                <Icon type="ios-locked-outline" slot="prepend"></Icon>
                </Input>   
                </Form-item>
            <Form-item>
                <Button  v-show="show" type="success" long @click="getCode" >获取手机验证码</Button>
                <Button  v-show="!show"  long type="success">{{count}} s</Button>
            </Form-item>
            <Form-item>
                <Button html-type="submit" type="success" long @click="handleSubmit('formValidate')">注册</Button>
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
      show: true,
      count: 0,
      timer: null,
      formValidate: {
        nickname: "",
        phone: "",
        vcode: "",
        email: "",
        password: ""
      },
      ruleValidate: {
        nickname: [{ required: true, message: "用户名不能为空", trigger: "blur" }],
        email: [
          { required: true, message: "邮箱不能为空", trigger: "blur" },
          { type: "string", message: "邮箱格式不正确", trigger: "blur" }
        ],
        phone: [
          { required: true, message: "手机号不能为空", trigger: "blur" },
          {
            type: "string",
            min: 11,
            message: "手机号长度不能小于11位",
            trigger: "blur"
          }
        ],
        password: [
          { required: true, message: "请填写密码", trigger: "blur" },
          {
            type: "string",
            min: 6,
            message: "密码长度不能小于6位",
            trigger: "blur"
          }
        ],
        vcode: [
          { required: true, message: "验证码不能为空", trigger: "blur" },
          {
            type: "string",
            min: 6,
            message: "验证码长度不能小于6位",
            trigger: "blur"
          }
        ]
      }
    };
  },
  methods: {
    handleSubmit(name) {
    
      var that = this;
      console.log("aaaa =>",that.formValidate.email);
      this.$refs[name].validate(valid => {
        if (valid) {
          axios
            .post(
              "http://111.231.190.23/web/register?tname=" +
                that.formValidate.nickname +
                "&phone=" +
                that.formValidate.phone +
                "&email=" +
                that.formValidate.email +
                "&psw=" +
                that.formValidate.password +
                "&vcode=" +
                that.formValidate.vcode
            )
            .then(function(response) {
              var judge = response.data.data.success;
              if(judge == true)
              {
                that.current += 1;
                javascript: location.href ='./'
              }
              else{
                alert(response.data.data.error);
            
              }
              console.log(response);
            })
            .catch(function(error) {
              console.log(error);
              alert("false");
            });
        } else {
          that.$Message.error("error");
        }
      });
    },
    getCode() {
      var that = this;
      const TIME_COUNT = 60;
      if (that.formValidate.phone.length != 11) {
        this.$Message.error("手机号码格式错误!");
        return;
      }
      if (that.count != 0) {
        return;
      } else {
        axios
          .post(
            "http://111.231.190.23/web/getVcode?phone=" +
              that.formValidate.phone
          )
          .then(function(response) {
            var res = response.data.data;
            if (res.success == true) {
              that.$Message.success("获取成功!");
              that.count = TIME_COUNT;
              that.show = false;
              that.timer = setInterval(() => {
                if (that.count > 0 && that.count <= TIME_COUNT) {
                  that.count--;
                } else {
                  that.show = true;
                  clearInterval(that.timer);
                  that.timer = null;
                }
              }, 1000);
            } else {
              that.$Message.error(res.error);
            }
          })
          .catch(function(error) {
            console.log(error);
          });
      }
    }
  }
};
</script>

<style scoped>
.sign-up {
  padding-top: 60px;
  margin: 0px auto;
  height: 686px;
}

form {
  width: 460px;
  margin: 20px auto;
  padding-right: 100px;
}

h2 {
  font-size: 26px;
  text-align: center;
}

button {
  font-size: 14px;
}

</style>
