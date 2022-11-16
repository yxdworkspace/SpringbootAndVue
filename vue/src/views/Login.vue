<template>
<div style="width: 100%;height: 100vh;background-color: darkslateblue;overflow: hidden">
  <div style="width: 400px;margin: 150px auto">
    <div style="color: #cccccc;font-size: 30px;text-align: center;padding: 30px 0">欢迎登录</div>
    <el-form
        ref="form"
        :model="form"
        label-width="80px"
        size="large"
        label-position="top"
        :rules="rules"
    >
      <el-form-item prop="username">
        <el-input v-model="form.username">
          <template #prefix>
            <el-icon class="el-input__icon"><user /></el-icon>
          </template>
        </el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input show-password type="password" v-model="form.password">
          <template #prefix>
            <el-icon class="el-input__icon"><lock /></el-icon>
          </template>
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="login" type="primary" style="width: 100%;">登录</el-button>
      </el-form-item>
    </el-form>
  </div>
</div>
</template>

<script>
import {  Search,User,Lock } from '@element-plus/icons-vue'
import request from "@/utils/request";
export default {
  name: "Login",
  data(){
    return {
      form: {},
      rules:{
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
        ],
      }
    }
  },
  components:{
    Search,
    User,
    Lock
  },
  created() {
    sessionStorage.removeItem("user")
  },
  methods:{
    login(){
      this.$refs['form'].validate((valid) => {
        if (valid) {
          request.post("/user/login",this.form).then(res=>{
            console.log(res)
            if(res.code === "0"){
              this.$message({
                type:"success",
                message:"登录成功"
              })
              sessionStorage.setItem("user", JSON.stringify(res.data))
              this.$router.push("/")
            }else {
              this.$message({
                type:"error",
                message:res.msg
              })
            }
          })
        } else {
          console.log('error submit!', fields)
        }
      })
    }
  }
}
</script>

<style scoped>

</style>