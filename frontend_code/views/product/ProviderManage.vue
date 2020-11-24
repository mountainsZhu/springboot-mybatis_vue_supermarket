<template>
    <div class="crumbs">
        <el-breadcrumb separator="/">
            <el-breadcrumb-item><i class="el-icon-date"></i> 供应商管理</el-breadcrumb-item>
            <el-breadcrumb-item>供应商列表</el-breadcrumb-item>
        </el-breadcrumb>
        <el-button style="margin-left: 1200px"
                   size="middle"
                   type="warning"
                   @click="drawer1 = true">添加</el-button>
        <el-drawer
                title="输入添加的供应商信息"
                :visible.sync="drawer1"
                size="500px">
            <el-form :model="providerMsg" :rules="rules" ref="providerMsg" label-width="100px" class="demo-ruleForm">
                <el-form-item label="供应商名" prop="providername">
                    <el-input v-model="providerMsg.providername"></el-input>
                </el-form-item>
                <el-form-item label="电话" prop="phone">
                    <el-input v-model="providerMsg.phone"></el-input>
                </el-form-item>
                <el-form-item label="地址" prop="address">
                    <el-input v-model="providerMsg.address"></el-input>
                </el-form-item>
                <el-form-item label="邮箱" prop="email">
                    <el-input v-model="providerMsg.email"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="handleInsert">确认</el-button>
                    <el-button @click="resetForm('providerMsg')">重置</el-button>
                </el-form-item>
            </el-form>
        </el-drawer>
        <div class="cantainer">
            <el-table style="width: 100%;" :data="tableData.slice((currentPage-1)*pageSize,currentPage*pageSize)">
                <el-table-column type="index" width="50">
                </el-table-column>
                <el-table-column label="供应商名" prop="providername" width="250">
                </el-table-column>
                <el-table-column label="电话" prop="phone" width="250">
                </el-table-column>
                <el-table-column label="地址" prop="address" width="300">
                </el-table-column>
                <el-table-column label="邮箱" prop="email" width="250">
                </el-table-column>
                <el-table-column label="操作">
                    <template slot-scope="scope">
                        <el-button
                                size="mini"
                                @click="handleUpdate(scope.row)">编辑</el-button>
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
                tableData: [],//商品数据
                drawer1: false,//添加弹窗
                providerMsg:{
                    providername:'',
                    phone:'',
                    address:'',
                    email:''
                },
                rules:{
                    providername:[
                        { required: true, message: '请输入供应商名称', trigger: 'blur' },
                    ],
                    phone:[
                        { required: true,validator:validatePass, trigger: 'blur' },
                    ],
                    address:[
                        { required: true, message: '请输入地址', trigger: 'blur' },
                    ],
                    email:[
                        { required: true,validator:validatePass1, trigger: 'blur' },
                    ]
                }
            }
        },
        created() {//初始化
            this.handleProviderList();
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
            handleProviderList() {
                var that = this;
                axios.get('http://localhost:9000/selectAllProvider').then(res => {  //这是从本地请求的数据接口，
                    // console.log(res.data);
                    that.tableData = res.data;
                })
            },
            handleUpdate(row){//通过新窗体修改
                this.$router.push({
                    path: "/UpdateProvider",
                    query: {
                        providername: row.providername
                    }
                })
            },
            handleDelete(row){
                const params = new URLSearchParams();
                params.append("providername",row.providername)
                axios.post('http://localhost:9000/deleteProvider',params).then(res =>{
                    console.log(res.data);
                    if(res.data != 1){
                        alert("删除失败！");
                    }else {
                        window.location.reload();
                    }
                })
            },
            handleInsert(){//通过弹窗添加
                var that = this;
                const params = new URLSearchParams();//不导入qs包，需定义一个可解析的json格式
                params.append("providername",that.providerMsg.providername);
                params.append("phone",that.providerMsg.phone);
                params.append("address",that.providerMsg.address);
                params.append("email",that.providerMsg.email);
                axios.post("http://localhost:9000/insertProvider",params)
                    .then(function (message) {
                        if(message.data == 0){
                            alert("添加失败！");
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
            }
        }
    }
</script>