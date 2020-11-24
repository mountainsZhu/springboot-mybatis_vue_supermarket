<template>
    <div id="monthexcel">
        <div style="margin-top: 200px;">
            <el-col :span=7 :push=6 style="height: 300px;box-shadow: 0 2px 12px 0 rgba(1, 0, 0, 1);background-color: lightskyblue">
                <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px">

                    <h2 align="center">请务必先确认信息再打印报表！</h2>

                    <el-form-item label="输入年份:" prop="year" style="margin-right: 50px;margin-top: 50px">
                        <el-input v-model="ruleForm.year" prefix-icon="el-icon-date" oninput = "value=value.replace(/[^\d]/g,'')"></el-input>
                    </el-form-item>

                    <el-form-item label="输入月份:" prop="month" style="margin-right: 50px">
                        <el-input v-model="ruleForm.month" prefix-icon="el-icon-date" oninput = "value=value.replace(/[^\d]/g,'')"></el-input>
                    </el-form-item>

                    <el-form-item >
                        <el-button type="warning" @click="makesure">确认信息</el-button>
                        <span style="margin-left: 80px"></span>
                        <a :href="excelurl">
                            <el-button type="success" style="margin-left: -10px;margin-top: 0px;">打印月报表</el-button>
                        </a>
                    </el-form-item>

                </el-form>
            </el-col>
        </div>
    </div>
</template>

<script>
    export default {
        data() {
            var validatePass = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入年份'));
                }else{
                    callback();
                }
            };
            var validatePass1 = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入月份'));
                }else{
                    callback();
                }
            };
            return {
                ruleForm: {
                    year: '',
                    month:''
                },
                rules: {
                    year: [
                        { required:true, validator: validatePass, trigger: 'blur' }
                    ],
                    month: [
                        { required:true, validator: validatePass1, trigger: 'blur' }
                    ]
                },
                excelurl:""
            };
        },
        methods:{
            makesure(){
                this.excelurl = "http://localhost:9000/monthSaleExcel?year="+this.ruleForm.year+"&month="+this.ruleForm.month;
                alert("打印"+this.ruleForm.year+"年"+this.ruleForm.month+"月销售报表");
            }
        }
    }
</script>
<style>
    #monthexcel {
        background: url("../../assets/chaoshibeijing.jpg") no-repeat;
        background-position: center;
        height: 100%;
        width: 100%;
        background-size: cover;
        position: fixed;
        opacity: 0.5;
    }
</style>