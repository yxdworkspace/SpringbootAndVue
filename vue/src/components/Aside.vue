<template>
<div>
  <el-row class="tac">
    <el-col>
      <el-menu
          :default-active="path"
          class="el-menu-vertical-demo"
          @open="handleOpen"
          @close="handleClose"
          style="width: 200px;min-height: calc(100vh - 50px);"
          :default-openeds="[1]"
          router
      >
        <el-sub-menu index="1" v-if="user.role === 1">
          <template #title>
            <span>系统管理</span>
          </template>
          <el-menu-item-group>
            <el-menu-item index="/user">用户管理</el-menu-item>
          </el-menu-item-group>
        </el-sub-menu>
        <el-menu-item index="/book">
          <span>书籍管理</span>
        </el-menu-item>
        <el-menu-item index="/news">
          <span>新闻管理</span>
        </el-menu-item>
      </el-menu>
    </el-col>
  </el-row>
</div>
</template>

<script>
import {
  Document,
  Menu as IconMenu,
  Location,
  Setting,
} from '@element-plus/icons-vue'
import request from "@/utils/request";
export default {
  name: "Aside",
  components: {
    Document,
    IconMenu,
    Location,
    Setting,
  },
  methods:{
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    }
  },
  created() {
    let userStr = sessionStorage.getItem("user") || "{}"
    this.user = JSON.parse(userStr)

    request.get("/user/" + this.user.id).then(res =>{
      if(res.code === '0'){
        this.user = res.data
      }
    }
    )
  },
  data(){
    return{
      path:this.$route.path,
      user:{}
    }
  }
}
</script>

<style scoped>

</style>