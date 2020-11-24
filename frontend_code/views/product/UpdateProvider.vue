<template>
    <div>
        <div style="margin: 20px;">修改商品信息</div>
        <el-form label-position="right" label-width="80px" :model="updateForm">
            <el-col :span="10" :push="5">
                <el-form-item label="供应商名">
                    <el-input v-model="updateForm.providername"></el-input>
                </el-form-item>
                <el-form-item label="电话">
                    <el-input v-model="updateForm.phone"></el-input>
                </el-form-item>
                <el-form-item label="地址">
                    <el-input v-model="updateForm.address"></el-input>
                </el-form-item>
                <el-form-item label="邮箱">
                    <el-input v-model="updateForm.email"></el-input>
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
                    providername:'',
                    phone:'',
                    address:'',
                    email:''
                }
            };
        },
        created() {
            var that = this;
            const params = new URLSearchParams();
            params.append("providername",this.$route.query.providername);
            axios.post('http://localhost:9000/selectProviderByName',params).then(res =>{
                 console.log(res.data[0]);
                that.updateForm.providername = res.data[0].providername;
                that.updateForm.phone = res.data[0].phone;
                that.updateForm.address = res.data[0].address;
                that.updateForm.email = res.data[0].email;
            })
        },
        methods:{
            update(){
                var that = this;
                const params = new URLSearchParams();
                params.append("providername",that.updateForm.providername);
                params.append("phone",that.updateForm.phone);
                params.append("address",that.updateForm.address);
                params.append("email",that.updateForm.email);
                axios.post('http://localhost:9000/updateProvider',params).then(res =>{
                    console.log(res.data);
                    if(res.data != 1){
                        alert("修改失败！");
                    }else{
                        this.$router.push("/ProviderManage")
                    }
                })
            }
        }
    }
</script>