<template>
    <div id="uplowexcel">
        <div style="margin-top: 200px;">
            <el-col :span=7 :push=6 style="height: 200px;box-shadow: 0 2px 12px 0 rgba(1, 0, 0, 1);background-color: lightskyblue">
                <el-form :model="ruleForm" status-icon label-width="100px">

                    <h2 align="center">请务必先确认信息再打印报表！</h2>

                    <el-form-item style="margin-top: 50px">
                        <el-button type="warning" @click="makesure">确认信息</el-button>
                        <span style="margin-left: 80px"></span>
                        <a :href="excelurl">
                            <el-button type="success" style="margin-left: -10px;margin-top: 0px;">打印退货报表</el-button>
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
                this.excelurl = "http://localhost:9000/upperlowerExcel";
                alert("打印积压/缺货报表");
            }
        }
    }
</script>
<style>
    #uplowexcel {
        background: url("../../assets/chaoshibeijing.jpg") no-repeat;
        background-position: center;
        height: 100%;
        width: 100%;
        background-size: cover;
        position: fixed;
        opacity: 0.5;
    }
</style>