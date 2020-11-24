<template>
    <div id="login">
        <div>
            <h1 align="center" id="title" >巴蜀超市欢迎您！</h1>
            <el-col :span=7 :push=8 style="height: 300px;box-shadow: 0 2px 12px 0 rgba(1, 0, 0, 1);background-color: lightskyblue">
                <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="50px">

                    <el-form-item label="账号" prop="username" style="margin-right: 50px;margin-left: 40px;margin-top: 50px">
                        <el-input v-model="ruleForm.username" prefix-icon="el-icon-user"></el-input>
                    </el-form-item>

                    <el-form-item label="密码" prop="password" style="margin-right: 50px;margin-left: 40px">
                        <el-input type="password" v-model="ruleForm.password" autocomplete="off" prefix-icon="el-icon-lock"></el-input>
                    </el-form-item>

                    <el-form-item>
                        <span style="margin-left: 70px"></span>
                        <el-button type="success" @click="login">登录</el-button>
                        <span style="margin-left: 100px"></span>
                        <el-button type="warning" @click="regist">注册</el-button>
                    </el-form-item>

                    <el-form-item>
                        <span style="margin-left: 250px;"></span>
                        <el-button type="text" @click="reset" style="color: brown">修改密码</el-button>
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
                    callback(new Error('请输入密码'));
                } else {
                    if (this.ruleForm.password !== '') {
                        this.$refs.ruleForm.validateField('checkPass');
                    }
                    callback();
                }
            };
            return {
                ruleForm: {
                    username: '',
                    password: '',
                },
                rules: {
                    password: [
                        { validator: validatePass, trigger: 'blur' }
                    ]
                }
            };
        },
        created() {
            var that = this;
            document.onkeydown = function() {
                var key = window.event.keyCode
                if (key == 13) {
                    that.login();
                }
            }
        },
        methods: {
            login(){
                const params = new URLSearchParams();
                params.append("username",this.ruleForm.username);
                params.append("password",this.ruleForm.password);
                axios.post("http://localhost:9000/login",params).then(res=>{
                    if(res.data == 1){//登陆成功
                        this.$router.push("/Index");
                    }else {
                        alert("登陆失败！请检查账号密码");
                    }
                })
            },
            regist(){
                this.$router.push("/Regist")
            },
            reset(){
                this.$router.push("/UpdatePassword")
            }
        }
    }
</script>
<style>
    #login {
        background: url("../../assets/chaoshibeijing.jpg") no-repeat;
        background-position: center;
        height: 100%;
        width: 100%;
        background-size: cover;
        position: fixed;
        opacity: 0.5;
    }
    #title{
        text-shadow: 0px 1px 0px #999, 0px 2px 0px #888, 0px 3px 0px #777, 0px 4px 0px #666, 0px 5px 0px #555, 0px 6px 0px #444, 0px 7px 0px #333, 0px 8px 7px #001135;
        font-size: 100px;
        color: darkblue;
    }
</style>