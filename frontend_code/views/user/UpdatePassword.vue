<template>
    <div id="updatepassword">
        <div style="margin-top: 200px">
            <el-col :span=7 :push=8 style="height: 380px;box-shadow: 0 2px 12px 0 rgba(1, 0, 0, 1);background-color: lightskyblue">
                <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="50px" style="margin-top: 50px">

                    <el-form-item label="账号" prop="username" style="margin-right: 50px;margin-left: 40px;margin-top: 50px">
                        <el-input v-model="ruleForm.username" prefix-icon="el-icon-user"></el-input>
                    </el-form-item>

                    <el-form-item label="密码" prop="pass" style="margin-right: 50px;margin-left: 40px">
                        <el-input type="password" v-model="ruleForm.pass" autocomplete="off" prefix-icon="el-icon-lock"></el-input>
                    </el-form-item>
                    <el-form-item label="确认密码" prop="checkPass" label-width="90px" style="margin-right: 50px">
                        <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off" prefix-icon="el-icon-lock"></el-input>
                    </el-form-item>
                    <el-form-item label="验证码" prop="testcode" label-width="60px" style="margin-right: 50px;margin-left: 30px">
                        <el-col :span="12">
                            <el-input v-model="ruleForm.testcode" prefix-icon="iconfont el-icon-supermarketverify-fill"></el-input>
                        </el-col>
                        <el-button type="success" style="margin-left: 40px" @click="sendEmail">获取验证码</el-button>
                    </el-form-item>
                    <el-form-item style="margin-left: 140px">
                        <el-button type="danger" @click="updatePassword">确认修改</el-button>
                    </el-form-item>
                </el-form>
            </el-col>
        </div>
    </div>
</template>

<script>
    export default {
        data() {
            var validatePass = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入账号'));
                }
            };
            var validatePass1 = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入密码'));
                } else {
                    if (this.ruleForm.checkPass !== '') {
                        this.$refs.ruleForm.validateField('checkPass');
                    }
                    callback();
                }
            };
            var validatePass2 = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请再次输入密码'));
                } else if (value !== this.ruleForm.pass) {
                    callback(new Error('两次输入密码不一致!'));
                } else {
                    callback();
                }
            };
            var validatePass3 = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入验证码'));
                }
            };
            return {
                ruleForm: {
                    username:'',
                    pass: '',
                    checkPass: '',
                    testcode: '',
                    email:''
                },
                rules: {
                    username: [
                        { validator: validatePass, trigger: 'blur' }
                    ],
                    pass: [
                        { validator: validatePass1, trigger: 'blur' }
                    ],
                    checkPass: [
                        { validator: validatePass2, trigger: 'blur' }
                    ],
                    testcode: [
                        { validator: validatePass3, trigger: 'blur' }
                    ]
                }
            };
        },
        created() {
            var that = this;
            document.onkeydown = function() {
                var key = window.event.keyCode
                if (key == 13) {
                    that.updatePassword();
                }
            }
        },
        methods: {
            updatePassword(){
                var params = new URLSearchParams();
                params.append("username",this.ruleForm.username);
                params.append("password",this.ruleForm.pass);
                params.append("testcode",this.ruleForm.testcode);
                axios.post("http://localhost:9000/updatePassword",params).then(res=>{
                    if(res.data == 1){
                        alert("修改成功！");
                        this.$router.push("/");
                    }else{
                        alert("验证码错误！");
                    }
                })
            },
            sendEmail(){
                var params = new URLSearchParams();
                params.append("username",this.ruleForm.username);
                axios.post("http://localhost:9000/sendMail",params).then(res=>{
                    if(res.data == 0){
                        alert("发送失败！请检查账号");
                    }else{
                        alert("邮件发送成功！");
                    }
                })
            }
        }
    }
</script>
<style>
    #updatepassword {
        background: url("../../assets/chaoshibeijing.jpg") no-repeat;
        background-position: center;
        height: 100%;
        width: 100%;
        background-size: cover;
        position: fixed;
        opacity: 0.5;
    }
</style>