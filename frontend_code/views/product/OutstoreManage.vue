<template>
    <div id="instore">
        <div style="margin-top: 80px;">
            <el-col :span=7 :push=6 style="height: 500px;box-shadow: 0 2px 12px 0 rgba(1, 0, 0, 1);background-color: lightskyblue">
                <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px">

                    <el-form-item style="margin-top: 20px">
                        <el-button type="success" @click="makesure" style="float: left">确认</el-button>
                        <el-button type="warning" style="margin-left: 20px" @click="searchinfo">自动填入货物进价和供货商</el-button>
                    </el-form-item>

                    <el-form-item label="货物名称" prop="outname" style="margin-right: 50px" class="label-style">
                        <el-input v-model="ruleForm.outname" prefix-icon="el-icon-user"></el-input>
                    </el-form-item>

                    <el-form-item label="货物数量" prop="outamount" style="margin-right: 50px" class="label-style">
                        <el-input v-model="ruleForm.outamount" prefix-icon="iconfont el-icon-supermarketnicheng" oninput = "value=value.replace(/[^\d]/g,'')"></el-input>
                    </el-form-item>

                    <el-form-item label="退货日期" required>
                        <el-col :span="10">
                            <el-form-item prop="outtime">
                                <el-date-picker type="date" placeholder="选择日期" v-model="ruleForm.outtime" style="width: 100%;"></el-date-picker>
                            </el-form-item>
                        </el-col>
                    </el-form-item>

                    <el-form-item label="退货价格" prop="outprice" style="margin-right: 50px" class="label-style">
                        <el-input v-model="ruleForm.outprice" prefix-icon="el-icon-lock" oninput = "value=value.replace(/[^0-9\.]/g,'')"></el-input>
                    </el-form-item>

                    <el-form-item label="供货商名" prop="outprice" style="margin-right: 50px" class="label-style">
                        <el-input v-model="ruleForm.outfactory" prefix-icon="el-icon-lock"></el-input>
                    </el-form-item>

                    <el-form-item label="退货原因" prop="outreason" style="margin-right: 50px" class="label-style">
                        <textarea v-model="ruleForm.outreason"></textarea>
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
                    callback(new Error('请选择退货日期'));
                }
            };
            var validatePass3 = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入退货价格'));
                }
            };
            var validatePass4 = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入供货商名'));
                }
            };
            var validatePass5 = (rule, value, callback) => {
                if(value ===''){
                    callback(new Error('请输入退货原因'));
                }
            };
            return {
                ruleForm: {
                    outname:'',
                    outamount:'',
                    outtime:'',
                    outprice:'',
                    outreason:'',
                    outfactory:''
                },
                rules: {
                    outname:[
                        { required: true, validator: validatePass, trigger: 'blur' }
                    ],
                    outamount: [
                        { required: true, validator: validatePass1, trigger: 'blur' }
                    ],
                    outtime: [
                        { required: true, type: 'date',validator: validatePass2, trigger: 'change' }
                    ],
                    outprice:[
                        { required: true, validator: validatePass3, trigger: 'blur' }
                    ],
                    outfactory:[
                        { required: true, validator: validatePass4, trigger: 'blur' }
                    ],
                    outreason:[
                        { required: true, validator: validatePass5, trigger: 'blur' }
                    ]
                }
            };
        },
        methods: {
            makesure(){
                var params = new URLSearchParams();
                params.append("outname",this.ruleForm.outname);
                params.append("outamount",this.ruleForm.outamount);
                params.append("outtime",this.ruleForm.outtime);
                params.append("outprice",this.ruleForm.outprice);
                params.append("outfactory",this.ruleForm.outfactory);
                params.append("outreason",this.ruleForm.outreason);
                axios.post("http://localhost:9000/outStore",params).then(res=>{
                    if(res.data != 0){
                        alert("退货成功！");
                    }else {
                        alert("退货失败");
                    }
                })
            },
            searchinfo(){
                var that = this;
                var params = new URLSearchParams();
                params.append("productname",this.ruleForm.outname);
                axios.post("http://localhost:9000/selectProductByName",params).then(res=>{
                    that.ruleForm.outprice = res.data[0].orignalprice;
                    that.ruleForm.outfactory = res.data[0].factory;
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