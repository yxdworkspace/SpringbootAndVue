<template>
<div>
  <el-row class="tac">
    <el-col>
      <el-menu
          default-active="user"
          class="el-menu-vertical-demo"
          @open="handleOpen"
          @close="handleClose"
          style="width: 200px;min-height: calc(100vh - 50px);"
          :default-openeds="[1]"
      >
        <el-sub-menu index="1">
          <template #title>
            <span>系统管理</span>
          </template>
          <el-menu-item-group>
            <el-menu-item index="user" :route="{path:'/'}">用户管理</el-menu-item>
          </el-menu-item-group>
        </el-sub-menu>
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
  }
}
</script>

<style scoped>

</style>