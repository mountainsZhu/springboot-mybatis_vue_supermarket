<template>
    <div>
        <div style="margin: 20px;">修改商品信息</div>
        <el-form label-position="right" label-width="80px" :model="updateForm">
            <el-col :span="10" :push="5">
                <el-form-item label="商品名">
                    <el-input v-model="updateForm.productname" readonly></el-input>
                </el-form-item>
                <el-form-item label="进价">
                    <el-input v-model="updateForm.orignalprice"></el-input>
                </el-form-item>
                <el-form-item label="卖价">
                    <el-input v-model="updateForm.promoteprice"></el-input>
                </el-form-item>
                <el-form-item label="供应商">
                    <el-input v-model="updateForm.factory"></el-input>
                </el-form-item>
                <el-form-item label="生产日期">
                    <el-form-item>
                        <el-date-picker type="date" placeholder="选择日期" v-model="updateForm.createdate" style="width: 100%;"></el-date-picker>
                    </el-form-item>
                </el-form-item>
                <el-form-item label="保质期">
                    <el-input v-model="updateForm.guarantee"></el-input>
                </el-form-item>

                <el-col :push="8">
                    <el-form-item>
                        <el-button type="primary" @click="update()">确认</el-button>
                    </el-form-item>
                </el-col>
            </el-col>
        </el-form>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                updateForm: {
                    productname:"",
                    orignalprice:"",
                    promoteprice:"",
                    factory:"",
                    createdate:"",
                    guarantee:""
                }
            };
        },
        created() {
            var that = this;
            const params = new URLSearchParams();
            params.append("productname",this.$route.query.productname)
            axios.post('http://localhost:9000/selectProductByName',params).then(res =>{
                // console.log(res.data[0]);
                that.updateForm.productname = res.data[0].productname;
                that.updateForm.orignalprice = res.data[0].orignalprice;
                that.updateForm.promoteprice = res.data[0].promoteprice;
                that.updateForm.factory = res.data[0].factory;
                that.updateForm.createdate = res.data[0].createdate;
                that.updateForm.guarantee = res.data[0].guarantee;
            })
        },
        methods:{
            update(){
                var that = this;
                const params = new URLSearchParams();
                params.append("productname",that.updateForm.productname);
                params.append("orignalprice",that.updateForm.orignalprice);
                params.append("promoteprice",that.updateForm.promoteprice);
                params.append("factory",that.updateForm.factory);
                params.append("createdate",that.updateForm.createdate);
                params.append("guarantee",that.updateForm.guarantee);
                axios.post('http://localhost:9000/updateProduct',params).then(res =>{
                    console.log(res.data);
                  if(res.data != 1){
                      alert("修改失败！");
                  }else{
                      this.$router.push("/ProductManage")
                  }
                })
            }
        }
    }
</script>