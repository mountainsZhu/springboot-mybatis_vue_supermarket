<template>
    <div class="crumbs">
        <el-breadcrumb separator="/">
            <el-breadcrumb-item><i class="el-icon-date"></i> 商品管理</el-breadcrumb-item>
            <el-breadcrumb-item>商品列表</el-breadcrumb-item>
        </el-breadcrumb>
        <el-button style="margin-left: 1200px"
                   size="middle"
                   type="warning"
                   @click="drawer1 = true">添加</el-button>
        <el-drawer
                title="输入添加的商品信息"
                :visible.sync="drawer1"
                size="500px">
            <el-form :model="productMsg" :rules="rules" ref="productMsg" label-width="100px" class="demo-ruleForm">
                <el-form-item label="商品名" prop="productname">
                    <el-input v-model="productMsg.productname"></el-input>
                </el-form-item>
                <el-form-item label="进价" prop="orignalprice">
                    <el-input v-model="productMsg.orignalprice"></el-input>
                </el-form-item>
                <el-form-item label="卖价" prop="promoteprice">
                    <el-input v-model="productMsg.promoteprice"></el-input>
                </el-form-item>
                <el-form-item label="供应商" prop="factory">
                    <el-input v-model="productMsg.factory"></el-input>
                </el-form-item>
                <el-form-item label="生产日期" required>
                    <el-col :span="10">
                        <el-form-item prop="createdate">
                            <el-date-picker type="date" placeholder="选择日期" v-model="productMsg.createdate" style="width: 100%;"></el-date-picker>
                        </el-form-item>
                    </el-col>
                </el-form-item>
                <el-form-item label="保质期/天" prop="guarantee">
                    <el-input v-model="productMsg.guarantee"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="handleInsert('productMsg')">确认</el-button>
                    <el-button @click="resetForm('productMsg')">重置</el-button>
                </el-form-item>
            </el-form>
        </el-drawer>
        <div class="cantainer">
            <el-table style="width: 100%;" :data="tableData.slice((currentPage-1)*pageSize,currentPage*pageSize)">
                <el-table-column type="index" width="50">
                </el-table-column>
                <el-table-column label="商品名" prop="productname" width="180">
                </el-table-column>
                <el-table-column label="进价" prop="orignalprice" width="180">
                </el-table-column>
                <el-table-column label="卖价" prop="promoteprice" width="180">
                </el-table-column>
                <el-table-column label="供应商" prop="factory" width="180">
                </el-table-column>
                <el-table-column label="生产日期" prop="createdate" width="180">
                </el-table-column>
                <el-table-column label="保质期/天" prop="guarantee" width="180">
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
            return {
                currentPage:1, //初始页
                pageSize:5,    //每页的数据
                tableData: [],//商品数据
                drawer1: false,//添加弹窗
                productMsg:{
                    productname:"",
                    orignalprice:"",
                    promoteprice:"",
                    factory:"",
                    createdate:"",
                    guarantee:""
                },
                rules:{
                    productname:[
                        { required: true, message: '请输入商品名称', trigger: 'blur' },
                    ],
                    orignalprice:[
                        { required: true, message: '请输入原价', trigger: 'blur' },
                    ],
                    promoteprice:[
                        { required: true, message: '请输入卖价', trigger: 'blur' },
                    ],
                    factory:[
                        { required: true, message: '请输入厂商', trigger: 'blur' },
                    ],
                    createdate:[
                        { type: 'date', required: true, message: '请选择日期', trigger: 'change' }
                    ],
                    guarantee:[
                        { required: true, message: '请输入保质期', trigger: 'blur' },
                    ]
                }
            }
        },
        created() {//初始化
            this.handleProductList();
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
            handleProductList() {
                var that = this;
                axios.get('http://localhost:9000/selectAllProduct').then(res => {  //这是从本地请求的数据接口，
                    // console.log(res.data);
                    that.tableData = res.data;
                })
            },
            handleUpdate(row){//通过新窗体修改
                this.$router.push({
                    path: "/UpdateProduct",
                    query: {
                        productname: row.productname
                    }
                })
            },
            handleDelete(row){
                const params = new URLSearchParams();
                params.append("productname",row.productname)
                axios.post('http://localhost:9000/deleteProduct',params).then(res =>{
                    if(res.data != 1){
                        alert("删除失败！");
                    }else {
                        window.location.reload();
                    }
                })
            },
            handleInsert(formName){//通过弹窗添加
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        var that = this;
                        const params = new URLSearchParams();//不导入qs包，需定义一个可解析的json格式
                        params.append("productname",that.productMsg.productname);
                        params.append("orignalprice",that.productMsg.orignalprice);
                        params.append("promoteprice",that.productMsg.promoteprice);
                        params.append("factory",that.productMsg.factory);
                        params.append("createdate",that.productMsg.createdate);
                        params.append("guarantee",that.productMsg.guarantee);
                        axios.post("http://localhost:9000/insertProduct",params)
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
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            }
        }
    }
</script>