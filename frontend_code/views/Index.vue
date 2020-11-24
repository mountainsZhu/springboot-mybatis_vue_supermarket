<template>
    <el-container style="height: 100%; border: 1px solid #eee">
        <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
            <el-menu router :default-active=$route.path :default-openeds="['0']">
                <el-submenu v-for="(item,index) in $router.options.routes" :index="index+''" v-if="item.show">
                    <template slot="title"><i class="el-icon-goods"></i>{{item.name}}</template>
                    <el-menu-item v-for="(item1,index1) in item.children" :index="item1.path">
                        {{item1.name}}
                    </el-menu-item>
                </el-submenu>
            </el-menu>
        </el-aside>

        <el-main>
            <router-view></router-view>
        </el-main>

    </el-container>
</template>

<script>
    export default {
        name: "Index"
    }
</script>

<style scoped>

</style>