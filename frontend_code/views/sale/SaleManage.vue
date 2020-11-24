<template>
    <div id="singlesale">
        <el-col :span=15 :push=3 style="margin-top: 20px;height: 650px;box-shadow: 0 2px 12px 0 rgba(1, 0, 0, 1);background-color: lightskyblue">
            <el-row class="demo-autocomplete" style="margin-left: 40px;margin-top: 20px;">
                <el-col :span="2">
                    <label id="l1">商品名:</label>
                </el-col>
                <el-col :span="5" :push="1">
                    <el-autocomplete
                            v-model = "productname"
                            :fetch-suggestions="querySearch"
                            placeholder="请输入商品名"
                    ></el-autocomplete>
                </el-col>
                <el-col :span="2" :push="2">
                    <label id="l2">数量:</label>
                </el-col>
                <el-col :span="5" :push="2">
                    <el-input
                            v-model = "amount"
                            placeholder="请输入数量"
                            oninput = "value=value.replace(/[^\d]/g,'')"
                    ></el-input>
                </el-col>
                <el-col :span="6" :push="3" align="center">
                    <el-button id="addInfo" style="margin-left: 10px" type="danger" @click="addItem">添加</el-button>
                </el-col>
            </el-row>
            <el-row class="cantainer">
                <el-col :span=22 :push=1 style="margin-top: 20px;height: 450px;box-shadow: 0 2px 12px 0 rgba(1, 0, 0, 1);background-color: #FFFFFF">
                    <el-table style="width: 100%" :data="items.slice((currentPage-1)*pageSize,currentPage*pageSize)">
                        <el-table-column label="商品名" prop="salename" width="220px">
                        </el-table-column>
                        <el-table-column label="数量" prop="saleamount" width="220px">
                        </el-table-column>
                        <el-table-column label="单价" prop="saleprice" width="220px">
                        </el-table-column>
                        <el-table-column label="小计" prop="salecost" width="220px">
                        </el-table-column>
                    </el-table>
                    <el-pagination
                            @size-change="handleSizeChange"
                            @current-change="handleCurrentChange"
                            :current-page="currentPage"
                            :page-sizes="[3, 5, 10]"
                            :page-size="pageSize"
                            layout="total, sizes, prev, pager, next, jumper"
                            :total="items.length">
                    </el-pagination>
                </el-col>
            </el-row>
            <el-row style="margin-top: 20px">
                <el-col :span="6" :push="4" align="center">
                    <h2>总计:   {{sumcost}}</h2>
                </el-col>
                <el-col :span="12" :push="6" align="center">
                    <el-button id="makesure" style="margin-top: 10px" type="success" @click="deal">确认</el-button>
                </el-col>
            </el-row>
        </el-col>


    </div>
</template>

<script>
    export default {
        data() {
            var validatePass = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入商品名称'));
                }else{
                    callback();
                }
            }
            var validatePass1 = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入商品数量'));
                }else{
                    callback();
                }
            };
            return {
                currentPage:1, //初始页
                pageSize:5,    //每页的数据
                products: [],
                items:[],
                productname: '',
                amount:'',
                sumcost:0,
                rules:{
                    productname:[
                        { required: true,validator:validatePass, trigger: 'blur,change' },
                    ],
                    amount:[
                        { required: true,validator:validatePass1, trigger: 'blur' },
                    ]
                }
            }
        },
        // created() {
        //   this.loadAll();
        // },
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
            querySearch(queryString, cb) {
                var products = this.products;
                var results = queryString ? products.filter(this.createFilter(queryString)) : products;
                // 调用 callback 返回建议列表的数据
                cb(results);
            },
            createFilter(queryString,) {
                return (product) => {
                    return (product.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
                };
            },
            addItem() {//添加项目
                if (this.productname == '' || this.amount == '') {
                    alert("商品名称和数量不能为空，请输入");
                } else if (this.amount > 50) {
                    alert("零售数目不能大于50");
                } else {
                    var that = this;
                    const params = new URLSearchParams();
                    params.append("productname", this.productname);
                    axios.post("http://localhost:9000/selectPriceByProductName", params).then(res => {
                        var item = {
                            salename: '',
                            saleamount: '',
                            saleprice: '',
                            salecost: ''
                        };
                        item.salename = that.productname;
                        item.saleamount = that.amount;
                        item.saleprice = res.data;
                        item.salecost = item.saleamount * item.saleprice;
                        that.items.push(item);
                        that.sumcost += item.salecost;
                    })
                }
            },
                deal(){//确认交易
                    var that = this;
                    var param = {
                        sale:this.items,
                        cost:this.sumcost
                    }
                    axios.post("http://localhost:9000/insertSale",param,{headers:{'content-type':'application/json'}}).then(res=>{
                        if(res.data>0){
                            alert("交易成功！")
                        }
                    })
                    this.items = [];
                    this.sumcost = 0;
                }
            },
        mounted() {
            var that = this;
            axios.get("http://localhost:9000/selectAllProduct").then(res=>{
                for(var i=0;i<res.data.length;i++){
                    res.data[i].value = res.data[i].productname;
                }
                that.products = res.data;
            })
        }
    }
</script>
<style>
    #singlesale {
        background: url("../../assets/chaoshibeijing.jpg") no-repeat;
        background-position: center;
        height: 100%;
        width: 100%;
        background-size: cover;
        position: fixed;
        opacity: 0.5;
    }
    #makesure{
        height: 50px;
        width: 300px;
    }
    #addInfo{
        height: 50px;
        width: 200px;
    }
    #l1{
        font-size: 22px;
        font-weight: bold;
    }
    #l2{
        font-size: 22px;
        font-weight: bold;
    }
</style>