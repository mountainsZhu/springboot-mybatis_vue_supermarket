<template>
    <div id="updownsale">
        <el-breadcrumb separator="/">
            <el-breadcrumb-item><i class="el-icon-date"></i> 库存管理</el-breadcrumb-item>
            <el-breadcrumb-item>积压警告</el-breadcrumb-item>
        </el-breadcrumb>
        <el-drawer
                title="输入积压上限"
                :visible.sync="drawer"
                size="500px">
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                <el-form-item label="商品名" prop="storename">
                    <el-input v-model="ruleForm.storename" readonly></el-input>
                </el-form-item>
                <el-form-item label="积压上限" prop="upperlimit">
                    <el-input v-model="ruleForm.upperlimit" oninput = "value=value.replace(/[^\d]/g,'')"></el-input>
                </el-form-item>
                <el-form-item>
                    <span style="margin-left: 250px"></span>
                    <el-button type="primary" @click="handleUpper">确认</el-button>
                </el-form-item>
            </el-form>
        </el-drawer>
        <div class="cantainer">
            <el-table style="width: 100%;" :data="tableData.slice((currentPage-1)*pageSize,currentPage*pageSize)">
                <el-table-column type="index" width="200">
                </el-table-column>
                <el-table-column label="商品名" prop="storename" width="300">
                </el-table-column>
                <el-table-column label="库存" prop="amount" width="300">
                </el-table-column>
                <el-table-column label="积压上限" prop="upperlimit" width="300">
                </el-table-column>
                <el-table-column label="操作">
                    <template slot-scope="scope">
                        <el-button
                                size="mini"
                                type="success"
                                @click="getDrawer(scope.row)">修改积压上限</el-button>
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
                drawer:false,
                tableData: [],//商品数据
                ruleForm:{
                    storename:'',
                    upperlimit:''
                },
                rules:{
                    upperlimit:[
                        { required: true, message: '请输入积压上限', trigger: 'blur' },
                    ]
                }
            }
        },
        created() {//初始化
            this.handleStoreList();
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
            handleStoreList() {
                var that = this;
                axios.get('http://localhost:9000/isUpper').then(res => {  //这是从本地请求的数据接口，
                    // console.log(res.data);
                    that.tableData = res.data;
                })
            },
            getDrawer(row){
                this.ruleForm.storename = row.storename;
                this.ruleForm.upperlimit = row.upperlimit;
                this.drawer = true;
            },
            handleUpper(formName){
                var that = this;
                const params = new URLSearchParams();
                params.append("storeName",this.ruleForm.storename);
                params.append("upperlimit",this.ruleForm.upperlimit);
                axios.post('http://localhost:9000/updateUpper',params).then(res => {  //这是从本地请求的数据接口，
                    that.drawer = false;
                    window.location.reload();
                })
            }
        }
    }
</script>