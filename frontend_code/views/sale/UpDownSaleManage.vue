<template>
    <div id="updownsale">
        <el-breadcrumb separator="/">
            <el-breadcrumb-item><i class="el-icon-date"></i> 销售管理</el-breadcrumb-item>
            <el-breadcrumb-item>商品上&下架</el-breadcrumb-item>
        </el-breadcrumb>
        <div class="cantainer">
            <el-table style="width: 100%;" :data="tableData.slice((currentPage-1)*pageSize,currentPage*pageSize)">
                <el-table-column type="index" width="200">
                </el-table-column>
                <el-table-column label="商品名" prop="storename" width="400">
                </el-table-column>
                <el-table-column label="上架情况" prop="isup" width="400">
                </el-table-column>
                <el-table-column label="操作">
                    <template slot-scope="scope">
                        <el-button
                                size="mini"
                                type="success"
                                @click="handleUpDown(scope.row)">上/下架</el-button>
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
                tableData: []//商品数据
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
                axios.get('http://localhost:9000/selectAllStore2').then(res => {  //这是从本地请求的数据接口，
                    // console.log(res.data);
                    that.tableData = res.data;
                })
            },
            handleUpDown(row){
                if(row.isup==0){
                    const params = new URLSearchParams();
                    params.append("storeName",row.storename)
                    axios.post('http://localhost:9000/upProduct',params).then(res => {  //这是从本地请求的数据接口，
                        window.location.reload();
                    })
                }else{
                    const params = new URLSearchParams();
                    params.append("storeName",row.storename)
                    axios.post('http://localhost:9000/downProduct',params).then(res => {  //这是从本地请求的数据接口，
                        window.location.reload();
                    })
                }
            }
        }
    }
</script>