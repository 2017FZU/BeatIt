<template>
    <div class="sign-up">
        <h2>Register</h2>
        <Form ref="formValidate" :model="formValidate" :rules="ruleValidate" :label-width="100">
            <Form-item label="用户名" prop="nickname">
                <Input type="text" v-model="formValidate.nickname" placeholder="user name">
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
            <Form-item>
                <Button html-type="submit" type="success" long @click="handleSubmit('formValidate')">注册</Button>
            </Form-item>
        </Form>
    </div>
</template>

<script>
export default {

    name: 'signUpPageOne',
    data() {
        return {
            formValidate: {
                nickname: '',
                email: '',
                password: '',
            },

            ruleValidate: {
                nickname: [
                    { required: true, message: '用户名不能为空', trigger: 'blur' }
                ],
                email: [
                    { required: true, message: '邮箱不能为空', trigger: 'blur' },
                    { type: 'email', message: '邮箱格式不正确', trigger: 'blur' }
                ],
                password: [
                    { required: true, message: '请填写密码', trigger: 'blur' },
                    { type: 'string', min: 6, message: '密码长度不能小于6位', trigger: 'blur' }
                ]
            }
        }
    },
    methods: {
        handleSubmit(name) {
            this.$refs[name].validate((valid) => {
                if (valid) {
                    var that=this;
                    this.axios.post('/index.php/User/name/1',{                      
                        password: this.formValidate.password,
                        sn: this.formValidate.studentId,
                        email: this.formValidate.email,
                    })
                        .then(function(response) {
                            console.log(response);
                            that.$Message.success('success');
                            that.current += 1;
                            that.$router.push({ name: 'signIn' })
                        })
                        .catch(function(error) {
                            console.log(error);
                        });

                } else {
                    this.$Message.error('error');
                }
            })
        }
    }
}
</script>

<style scoped>
.sign-up{
    padding-top:60px; 
    margin: 0px auto;
    height: 686px;
}

Form {
    width: 460px;
    margin: 20px auto;
    padding-right: 100px;
}

h2{
    font-size: 26px;
    text-align: center;
}

button {
    font-size: 14px;
}
</style>
