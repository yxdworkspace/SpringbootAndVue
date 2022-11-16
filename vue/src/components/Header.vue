<template>
  <div style="height: 50px;align-items: center;border-bottom: 1px solid #ccc;display: flex">
    <div style="width: 200px;padding-left: 30px;font-weight: bold;color: dodgerblue">后台管理</div>
    <div style="flex: 1"></div>
    <div style="width: 100px;">
      <el-dropdown>
    <span class="el-dropdown-link">

      <el-icon class="el-icon--right">
        <arrow-down/>
      </el-icon>
    </span>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item @click="$router.push('/person')">个人信息</el-dropdown-item>
            <el-dropdown-item @click="this.$router.push('/login')">退出系统</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
  </div>
</template>

<script>
import { ArrowDown } from '@element-plus/icons-vue'
import request from "@/utils/request";
export default {
  name: "Header",
  components:{
    ArrowDown
  },
  props:['user']
  // created() {
  //   this.load()
  // },
  // methods:{
  //   load(){
  //     request.post("/user").then(res =>{
  //       console.log(res)
  //     })
  //   }
  // }
}
</script>

<style lang="scss" scoped>

</style>