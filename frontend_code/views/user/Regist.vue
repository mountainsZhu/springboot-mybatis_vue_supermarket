<template>
    <div id="regist">
        <div style="margin-top: 50px;">
            <el-col :span=7 :push=8 style="height: 600px;box-shadow: 0 2px 12px 0 rgba(1, 0, 0, 1);background-color: lightskyblue">
                <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="50px">

                    <el-form-item label="账号" prop="username" style="margin-right: 50px;margin-left: 40px;margin-top: 50px" class="label-style">
                        <el-input v-model="ruleForm.username" prefix-icon="el-icon-user"></el-input>
                    </el-form-item>

                    <el-form-item label="密码" prop="password" style="margin-right: 50px;margin-left: 40px" class="label-style">
                        <el-input type="password" v-model="ruleForm.password" autocomplete="off" prefix-icon="el-icon-lock"></el-input>
                    </el-form-item>

                    <el-form-item label="重复密码" label-width="90px" prop="repassword" style="margin-right: 50px" class="label-style">
                        <el-input type="password" v-model="ruleForm.repassword" autocomplete="off" prefix-icon="el-icon-lock"></el-input>
                    </el-form-item>

                    <el-form-item label="昵称" prop="nickname" style="margin-right: 50px;margin-left: 40px" class="label-style">
                        <el-input v-model="ruleForm.nickname" prefix-icon="iconfont el-icon-supermarketnicheng"></el-input>
                    </el-form-item>

                    <el-form-item label="性别" prop="sex" style="margin-right: 50px;margin-left: 40px" class="label-style">
                        <el-select v-model="ruleForm.sex" placeholder="请选择性别">
                            <el-option label="男" value="男"></el-option>
                            <el-option label="女" value="女"></el-option>
                        </el-select>
                    </el-form-item>

                    <el-form-item label="手机号" label-width="60px" prop="phone" style="margin-right: 50px;margin-left: 30px" class="label-style">
                        <el-input v-model="ruleForm.phone" prefix-icon="iconfont el-icon-supermarketshoujihao"></el-input>
                    </el-form-item>

                    <el-form-item label="邮箱" prop="email" style="margin-right: 50px;margin-left: 40px" class="label-style">
                        <el-input v-model="ruleForm.email" prefix-icon="iconfont el-icon-supermarketyouxiang"></el-input>
                    </el-form-item>

                    <el-form-item>
                        <span style="margin-left: 150px"></span>
                        <el-button type="success" @click="regist">确认</el-button>
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
                    if (this.ruleForm.repassword !== '') {
                        this.$refs.ruleForm.validateField('repassword');
                    }
                    callback();
                }
            };
            var validatePass2 = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请再次输入密码'));
                } else if (value !== this.ruleForm.password) {
                    callback(new Error('两次输入密码不一致!'));
                } else {
                    callback();
                }
            };
            var validatePass3 = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入昵称'));
                }
            };
            var validatePass4 = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入性别'));
                }
            };
            var validatePass5 = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入电话'));
                }else{
                    var reg=/^1[3456789]\d{9}$/;
                    if(!reg.test(value)){
                        callback(new Error('请输入有效的手机号码'));
                    }
                }
            };
            var validatePass6 = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入邮箱'));
                }else{
                    var reg=/^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
                    if(!reg.test(value)){
                        callback(new Error('请输入有效的邮箱'));
                    }

                }
            };
            return {
                ruleForm: {
                    username: '',
                    password: '',
                    repassword:'',
                    nickname:'',
                    sex:'',
                    phone:'',
                    email:''
                },
                rules: {
                    username:[
                        { validator: validatePass, trigger: 'blur' }
                    ],
                    password: [
                        { validator: validatePass1, trigger: 'blur' }
                    ],
                    repassword: [
                        { validator: validatePass2, trigger: 'blur' }
                    ],
                    nickname:[
                        { validator: validatePass3, trigger: 'blur' }
                    ],
                    sex:[
                        { validator: validatePass4, trigger: 'blur' }
                    ],
                    phone:[
                        { validator: validatePass5, trigger: 'blur' }
                    ],
                    email:[
                        { validator: validatePass6, trigger: 'blur' }
                    ],
                }
            };
        },
        created() {
            var that = this;
            document.onkeydown = function() {
                var key = window.event.keyCode
                if (key == 13) {
                    that.regist();
                }
            }
        },
        methods: {
            checkEmail(){

            },
            regist(){
                var params = new URLSearchParams();
                params.append("username",this.ruleForm.username);
                params.append("password",this.ruleForm.password);
                params.append("nickname",this.ruleForm.nickname);
                params.append("phone",this.ruleForm.phone);
                params.append("sex",this.ruleForm.sex);
                params.append("email",this.ruleForm.email);
                if(this.ruleForm.username&&this.ruleForm.password&&this.ruleForm.nickname&&this.ruleForm.phone&&this.ruleForm.sex&&this.ruleForm.email&&this.ruleForm.repassword){
                    axios.post("http://localhost:9000/userRegist",params).then(res=>{
                        if(res.data == 1){
                            alert("恭喜你，注册成功！");
                            this.$router.push("/");
                        }else {
                            alert("注册失败！账号已存在");
                        }
                    })
                }else {
                    alert("有信息为空！");
                }
            }
        }
    }
</script>
<style>
    .label-style .el-form-item__label{
        color: #996666
    }
    .iconfont {
        width: 1em; height: 1em;
        vertical-align: -0.15em;
        fill: currentColor;
    }
     #regist {
         background: url("../../assets/chaoshibeijing.jpg") no-repeat;
         background-position: center;
         height: 100%;
         width: 100%;
         background-size: cover;
         position: fixed;
         opacity: 0.5;
     }
</style>