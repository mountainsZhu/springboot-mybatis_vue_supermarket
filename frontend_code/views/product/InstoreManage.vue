<template>
    <div id="instore">
        <div style="margin-top: 80px;">
            <el-col :span=7 :push=2 style="height: 500px;box-shadow: 0 2px 12px 0 rgba(1, 0, 0, 1);background-color: lightskyblue">
                <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px">

                    <h3 align="center">必填部分:(务必在右侧查证后填写!)</h3>

                    <el-form-item label="货物名称" prop="inname" style="margin-right: 50px" class="label-style">
                        <el-input v-model="ruleForm.inname" prefix-icon="el-icon-user"></el-input>
                    </el-form-item>

                    <el-form-item label="供应商名" prop="infactory" style="margin-right: 50px" class="label-style">
                        <el-input v-model="ruleForm.infactory" prefix-icon="iconfont el-icon-supermarketnicheng"></el-input>
                    </el-form-item>

                    <el-form-item label="货物数量" prop="inamount" style="margin-right: 50px" class="label-style">
                        <el-input v-model="ruleForm.inamount" prefix-icon="el-icon-lock" oninput = "value=value.replace(/[^\d]/g,'')"></el-input>
                    </el-form-item>

                    <el-form-item label="进货日期" required>
                        <el-col :span="10">
                            <el-form-item prop="intime">
                                <el-date-picker type="date" placeholder="选择日期" v-model="ruleForm.intime" style="width: 100%;"></el-date-picker>
                            </el-form-item>
                        </el-col>
                    </el-form-item>

                    <el-form-item label="进货价格" prop="inprice" style="margin-right: 50px" class="label-style">
                        <el-input v-model="ruleForm.inprice" prefix-icon="el-icon-lock" oninput = "value=value.replace(/[^0-9\.]/g,'')"></el-input>
                    </el-form-item>

                    <el-form-item style="margin-top: 60px">
                        <span style="margin-left: 90px"></span>
                        <el-button type="success" @click="makesure">确认</el-button>
                    </el-form-item>

                </el-form>
            </el-col>
            <el-col :span=7 :push=4 style="height: 500px;box-shadow: 0 2px 12px 0 rgba(1, 0, 0, 1);background-color: lightskyblue">
                <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px">

                    <el-form-item>
                        <h3 style="float: left">已有商品不填：</h3>
                        <el-button style="margin-top: 20px;margin-left: 30px" type="warning" @click="isProductExit">查询商品是否存在</el-button>
                    </el-form-item>

                    <el-form-item label="售价" style="margin-right: 50px" class="label-style">
                        <el-input v-model="ruleForm.promoteprice" prefix-icon="el-icon-user" oninput = "value=value.replace(/[^0-9\.]/g,'')"></el-input>
                    </el-form-item>

                    <el-form-item label="保质期" style="margin-right: 50px" class="label-style">
                        <el-input v-model="ruleForm.guarantee" prefix-icon="el-icon-lock" oninput = "value=value.replace(/[^\d]/g,'')"></el-input>
                    </el-form-item>


                </el-form>
                <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" style="margin-top: 10px">

                    <el-form-item>
                        <h3 style="float: left">已有供货商不填：</h3>
                        <el-button style="margin-top: 20px;" type="danger" @click="isProviderExit">查询供货商是否存在</el-button>
                    </el-form-item>

                    <el-form-item label="厂商地址" style="margin-right: 50px" class="label-style">
                        <el-input v-model="ruleForm.provideraddress" prefix-icon="el-icon-user"></el-input>
                    </el-form-item>

                    <el-form-item label="电话" prop="phone" style="margin-right: 50px" class="label-style">
                        <el-input v-model="ruleForm.phone" prefix-icon="el-icon-lock"></el-input>
                    </el-form-item>

                    <el-form-item label="邮箱" style="margin-right: 50px" class="label-style">
                        <el-input v-model="ruleForm.email" prefix-icon="iconfont el-icon-supermarketnicheng"></el-input>
                    </el-form-item>

                </el-form>
            </el-col>
        </div>
        <
    </div>
</template>

<script>
    export default {
        data() {
            var validatePass = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入货物名称'));
                }
            };
            var validatePass1 = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入货物数量'));
                }
            };
            var validatePass2 = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请选择进货时间'));
                }
            };
            var validatePass3 = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入进货价格'));
                }
            };
            var validatePass4 = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入进货商名'));
                }
            };
            var validatePass5 = (rule, value, callback) => {
                if(value!==''){
                    var reg=/^1[3456789]\d{9}$/;
                    if(!reg.test(value)){
                        callback(new Error('请输入有效的手机号码'));
                    }
                }
            };
            return {
                ruleForm: {
                    inname:'',
                    inamount:'',
                    intime:'',
                    inprice:'',
                    infactory:'',
                    provideraddress:'',
                    guarantee:'',
                    phone:'',
                    promoteprice:'',
                    email:''
                },
                rules: {
                    inname:[
                        { required: true, validator: validatePass, trigger: 'blur' }
                    ],
                    inamount: [
                        { required: true, validator: validatePass1, trigger: 'blur' }
                    ],
                    intime: [
                        { required: true, type: 'date',validator: validatePass2, trigger: 'change' }
                    ],
                    inprice:[
                        { required: true, validator: validatePass3, trigger: 'blur' }
                    ],
                    infactory:[
                        { required: true, validator: validatePass4, trigger: 'blur' }
                    ],
                    phone:[
                        { validator: validatePass5, trigger: 'blur' }
                    ]
                }
            };
        },
        methods: {
            makesure(){
                if(this.ruleForm.promoteprice&&this.ruleForm.guarantee&&this.ruleForm.provideraddress&&this.ruleForm.phone&&this.ruleForm.email){
                    console.log("1");
                    var params = new URLSearchParams();
                    params.append("inname",this.ruleForm.inname);
                    params.append("inamount",this.ruleForm.inamount);
                    params.append("intime",this.ruleForm.intime);
                    params.append("inprice",this.ruleForm.inprice);
                    params.append("infactory",this.ruleForm.infactory);
                    params.append("promoteprice",this.ruleForm.promoteprice);
                    params.append("guarantee",this.ruleForm.guarantee);
                    params.append("provideraddress",this.ruleForm.provideraddress);
                    params.append("phone",this.ruleForm.phone);
                    params.append("email",this.ruleForm.email);
                    axios.post("http://localhost:9000/inStore",params).then(res=>{
                        if(res.data != 0){
                            alert("入库成功！");
                        }else {
                            alert("入库失败");
                        }
                    }).catch(res1=>{
                        alert("请按照文字说明填写！")
                    })
                }else{
                    console.log("2");
                    var params = new URLSearchParams();
                    params.append("inname",this.ruleForm.inname);
                    params.append("inamount",this.ruleForm.inamount);
                    params.append("intime",this.ruleForm.intime);
                    params.append("inprice",this.ruleForm.inprice);
                    params.append("infactory",this.ruleForm.infactory);
                    axios.post("http://localhost:9000/inStore2",params).then(res=>{
                        if(res.data != 0){
                            alert("入库成功！");
                        }else {
                            alert("入库失败");
                        }
                    }).catch(res1=>{
                        alert("请按照文字说明填写！")
                    })
                }

            },
            isProductExit(){
                var that = this;
                var params = new URLSearchParams();
                params.append("productname",this.ruleForm.inname);
                axios.post("http://localhost:9000/selectProductByName",params).then(res=>{
                    if(res.data[0]){
                        that.ruleForm.inprice = res.data[0].orignalprice;
                        that.ruleForm.infactory = res.data[0].factory;
                        alert("此货物已经存在！\r\n供应商为："+res.data[0].factory+",\r\n"+
                        "上次进货价格："+res.data[0].orignalprice);
                    }else {
                        alert("此货物不存在！请输入货物信息");
                    }
                })
            },
            isProviderExit(){
                var params = new URLSearchParams();
                params.append("providername",this.ruleForm.infactory);
                axios.post("http://localhost:9000/selectProviderByName",params).then(res=>{
                    if(res.data[0]){
                        alert("此供货商已经存在！");
                    }else {
                        alert("此供货商不存在！请输入供货商信息");
                    }
                })
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
    #instore {
        background: url("../../assets/chaoshibeijing.jpg") no-repeat;
        background-position: center;
        height: 100%;
        width: 100%;
        background-size: cover;
        position: fixed;
        opacity: 0.5;
    }
</style>