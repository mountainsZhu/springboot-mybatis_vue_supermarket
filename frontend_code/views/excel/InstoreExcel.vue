<template>
    <div id="instoreexcel">
        <div style="margin-top: 200px;">
            <el-col :span=7 :push=6 style="height: 400px;box-shadow: 0 2px 12px 0 rgba(1, 0, 0, 1);background-color: lightskyblue">
                <el-form :model="ruleForm" status-icon label-width="100px">

                    <h2 align="center">请务必先确认信息再打印报表！</h2>

                    <el-form-item label="货物名称:" style="margin-right: 50px;margin-top: 50px">
                        <el-input v-model="ruleForm.inname" prefix-icon="el-icon-date"></el-input>
                    </el-form-item>

                    <el-form-item label="进货日期">
                        <el-col :span="10">
                            <el-form-item>
                                <el-date-picker type="date" placeholder="选择日期" v-model="ruleForm.intime" style="width: 100%;"></el-date-picker>
                            </el-form-item>
                        </el-col>
                    </el-form-item>

                    <el-form-item label="进货商:" style="margin-right: 50px">
                        <el-input v-model="ruleForm.infactory" prefix-icon="el-icon-date"></el-input>
                    </el-form-item>

                    <el-form-item style="margin-top: 50px">
                        <el-button type="warning" @click="makesure">确认信息</el-button>
                        <span style="margin-left: 80px"></span>
                        <a :href="excelurl">
                            <el-button type="success" style="margin-left: -10px;margin-top: 0px;">打印进货报表</el-button>
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
            return {
                ruleForm: {
                    inname: '',
                    intime:'',
                    infactory:''
                },
                excelurl:""
            };
        },
        methods:{
            makesure(){
                // if(!this.ruleForm.inname&&!this.ruleForm.intime&&!this.ruleForm.infactory){
                //     this.excelurl = "http://localhost:9000/inSaleExcel?year="+this.ruleForm.year;
                //     alert("打印"+this.ruleForm.year+"年销售报表");
                // }
                this.excelurl = "http://localhost:9000/inSaleExcel?inname="+this.ruleForm.inname+"&intime="+this.ruleForm.intime+"&infactory="+this.ruleForm.infactory;
                alert("打印进货报表");
            }
        }
    }
</script>
<style>
    #instoreexcel {
        background: url("../../assets/chaoshibeijing.jpg") no-repeat;
        background-position: center;
        height: 100%;
        width: 100%;
        background-size: cover;
        position: fixed;
        opacity: 0.5;
    }
</style>