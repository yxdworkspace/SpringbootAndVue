<template>
  <div class="home" style="padding: 10px;">
<!--    功能区域-->
    <div style="margin: 10px 0">
      <el-button type="primary" @click="add">新增</el-button>
      <el-button type="primary">导入</el-button>
      <el-button type="primary">导出</el-button>
    </div>
    <div style="margin: 10px 0">
      <el-input v-model="search" placeholder="请输入关键字" style="width: 20%;" clearable />
      <el-button @click="load" style="margin-left: 5px" type="primary">查询</el-button>
    </div>
    <el-table stripe :data="tableData" border style="width: 100%">
      <el-table-column sortable prop="id" label="ID" />
      <el-table-column prop="username" label="用户名"  />
      <el-table-column prop="nickname" label="昵称" />
      <el-table-column prop="age" label="年龄" />
      <el-table-column prop="gender" label="性别" />
      <el-table-column prop="address" label="地址" />
      <el-table-column fixed="right" label="操作">
        <template #default="scope">
          <el-button  type="default" size="small" @click="handleEdit(scope.row)">编辑</el-button>
          <el-popconfirm width="200px" title="你确定要删除吗？" @confirm="handleDelete(scope.row.id)">
            <template #reference>
              <el-button  type="danger" size="small">删除</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <div style="margin: 10px 0">
      <el-pagination
          v-model:currentPage="currentPage"
          v-model:page-size="pageSize"
          :page-sizes="[5, 10, 20, 30]"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
      />
      <el-dialog
          v-model="dialogVisible"
          title="Tips"
          width="30%">
        <el-form label-width="120px">
          <el-form-item label="用户名">
            <el-input v-model="form.username" style="width: 80%;" />
          </el-form-item>
          <el-form-item label="昵称">
            <el-input v-model="form.nickname" style="width: 80%;" />
          </el-form-item>
          <el-form-item label="年龄">
            <el-input v-model="form.age" style="width: 80%;" />
          </el-form-item>
          <el-form-item label="性别">
            <el-radio v-model="form.gender" label="男">男</el-radio>
            <el-radio v-model="form.gender" label="女">女</el-radio>
            <el-radio v-model="form.gender" label="未知">未知</el-radio>

          </el-form-item>
          <el-form-item label="地址">
            <el-input type="textarea" v-model="form.address" style="width: 80%;"/>
          </el-form-item>
        </el-form>
        <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="save">
          确定
        </el-button>
      </span>
        </template>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: 'HomeView',
  components: {
  },
  data() {
    return {
      form:{},
      dialogVisible: false,
      search:'',
      currentPage:1,
      total:10,
      pageSize:10,
      tableData: []
    }
  },
  created() {
    this.load();
  },
  methods:{
    handleEdit(row){
      this.form = JSON.parse(JSON.stringify(row));
      this.dialogVisible = true;
    },
    handleDelete(id){
      console.log(id)
      request.delete("/user/"+id).then(res=>{
        this.$message.success("删除成功");
        this.load();
      })
    },
    handleSizeChange(pageSize){
      this.pageSize = pageSize;
      this.load();
    },
    handleCurrentChange(pageNum){
        this.currentPage =pageNum;
        this.load();
    },
    add(){
      this.dialogVisible = true;
      this.form={};
    },
    save(){
      if(this.form.id){
        request.put('/user',this.form).then(res=>{
          console.log(res)
          if(res.code === "0"){
            this.$message({
              message: '更新成功',
              type: 'success'
            });
          }
          else {
            this.$message({
              type:"error",
              message:res.msg
            })
          }
          this.load();
          this.dialogVisible = false;
        })
      }else {
        request.post('/user',this.form).then(res=>{
        console.log(res)
        if(res.code === "0"){
          this.$message({
            message: '新增成功',
            type: 'success'
          });
        }
        else {
          this.$message({
            type:"error",
            message:res.message
          })
        }
          this.load();
          this.dialogVisible = false;
      })
      }
    },
    load(){
      request.get("/user",{
        params:{
          pageNum:this.currentPage,
          pageSize:this.pageSize,
          search:this.search
        }
      }).then(res=>{
        console.log(res)
        this.tableData = res.data.records;
        this.total = res.data.total;
      })
    }
  }
}
</script>
