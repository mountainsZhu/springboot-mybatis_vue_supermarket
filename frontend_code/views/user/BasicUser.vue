<template>
    <div class="crumbs">
        <el-breadcrumb separator="/">
            <el-breadcrumb-item><i class="el-icon-date"></i> 商品管理</el-breadcrumb-item>
            <el-breadcrumb-item>用户列表</el-breadcrumb-item>
        </el-breadcrumb>
        <el-drawer
                title="修改用户信息"
                :visible.sync="drawer1"
                size="500px">
            <el-form :model="userMsg" :rules="rules" ref="userMsg" label-width="100px" class="demo-ruleForm">
                <el-form-item label="账号" prop="username">
                    <el-input v-model="userMsg.username" readonly></el-input>
                </el-form-item>
                <el-form-item label="昵称" prop="nickname">
                    <el-input v-model="userMsg.nickname"></el-input>
                </el-form-item>
                <el-form-item label="手机号" prop="phone">
                    <el-input v-model="userMsg.phone"></el-input>
                </el-form-item>
                <el-form-item label="性别" prop="sex" style="margin-right: 50px">
                    <el-select v-model="userMsg.sex" placeholder="请选择性别">
                        <el-option label="男" value="男"></el-option>
                        <el-option label="女" value="女"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="邮箱" prop="email">
                    <el-input v-model="userMsg.email"></el-input>
                </el-form-item>
                <el-form-item label="权限" prop="power" style="margin-right: 50px">
                    <el-select v-model="userMsg.power" placeholder="请选择权限">
                        <el-option label="0" value="0"></el-option>
                        <el-option label="1" value="1"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="handleUpdate">确认</el-button>
                    <el-button @click="resetForm('userMsg')">重置</el-button>
                </el-form-item>
            </el-form>
        </el-drawer>
        <div class="cantainer">
            <el-table style="width: 100%;" :data="tableData.slice((currentPage-1)*pageSize,currentPage*pageSize)">
                <el-table-column type="index" width="50">
                </el-table-column>
                <el-table-column label="账号" prop="username" width="180">
                </el-table-column>
                <el-table-column label="昵称" prop="nickname" width="180">
                </el-table-column>
                <el-table-column label="手机号" prop="phone" width="180">
                </el-table-column>
                <el-table-column label="性别" prop="sex" width="180">
                </el-table-column>
                <el-table-column label="邮箱" prop="email" width="180">
                </el-table-column>
                <el-table-column label="权限" prop="power" width="180">
                </el-table-column>
                <el-table-column label="操作">
                    <template slot-scope="scope">
                        <el-button
                                size="mini"
                                @click="setUpdate(scope.row)">编辑</el-button>
                        <el-button
                                size="mini"
                                type="danger"
                                @click="handleDelete(scope.row)">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <el-pagination
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page="currentPage"
                    :page-sizes="[5, 10, 20, 30]"
                    :page-size="pageSize"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="tableData.length">
            </el-pagination>
        </div>
    </div>
</template>

<script>
    export default {
        data() {
            var validatePass = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入电话'));
                }else{
                    var reg=/^1[3456789]\d{9}$/;
                    if(!reg.test(value)){
                        callback(new Error('请输入有效的手机号码'));
                    }
                }
            };
            var validatePass1 = (rule, value, callback) => {
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
                currentPage:1, //初始页
                pageSize:5,    //每页的数据
                tableData: [],//用户数据
                drawer1:false,
                userMsg:{
                    username:'',
                    power:'',
                    nickname:'',
                    phone:'',
                    sex:'',
                    email:''
                },
                rules:{
                    username:[
                        { required: true, message: '不能修改为空', trigger: 'blur' },
                    ],
                    nickname:[
                        { required: true, message: '不能修改为空', trigger: 'blur' },
                    ],
                    phone:[
                        { validator: validatePass,required: true, trigger: 'blur' },
                    ],
                    sex:[
                        { required: true, message: '不能修改为空', trigger: 'blur' },
                    ],
                    email:[
                        { validator: validatePass1,required: true, trigger: 'blur' },
                    ],
                    power:[
                        { required: true, message: '不能修改为空', trigger: 'blur' },
                    ]
                }
            }
        },
        created() {//初始化
            this.handleUserList();
        },
        methods: {
            // 初始页currentPage、初始每页数据数pagesize和数据data
            handleSizeChange: function (size) {
                this.pageSize = size;
                // console.log("每页数据量："+size)  //每页下拉显示数据
            },
            handleCurrentChange: function (currentPage) {
                this.currentPage = currentPage;
                // console.log("当前页"+currentPage)  //点击第几页
            },
            handleUserList() {
                var that = this;
                axios.get('http://localhost:9000/selectAllUsers').then(res => {  //这是从本地请求的数据接口，
                    // console.log(res.data);
                    that.tableData = res.data;
                })
            },
            handleDelete(row){
                const params = new URLSearchParams();
                params.append("username",row.username)
                axios.post('http://localhost:9000/deleteUser',params).then(res =>{
                    if(res.data != 1){
                        alert("删除失败！");
                    }else {
                        window.location.reload();
                    }
                })
            },
            handleUpdate(){//通过弹窗添加
                var that = this;
                const params = new URLSearchParams();//不导入qs包，需定义一个可解析的json格式
                params.append("username",that.userMsg.username);
                params.append("nickname",that.userMsg.nickname);
                params.append("phone",that.userMsg.phone);
                params.append("sex",that.userMsg.sex);
                params.append("email",that.userMsg.email);
                params.append("power",that.userMsg.power);
                axios.post("http://localhost:9000/updateUserInfo",params)
                    .then(function (message) {
                        if(message.data == 0){
                            alert("修改失败！");
                        }else{
                            that.drawer1 = false;
                            window.location.reload();
                        }
                    }).catch(function (err){
                    console.log(err);
                })
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },
            setUpdate(row){
                this.userMsg.username = row.username;
                this.userMsg.nickname = row.nickname;
                this.userMsg.phone = row.phone;
                this.userMsg.sex = row.sex;
                this.userMsg.email = row.email;
                this.userMsg.power = row.power;
                this.drawer1 = true;
            }
        }
    }
</script>