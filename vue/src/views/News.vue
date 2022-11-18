<template>
  <div class="home" style="padding: 10px;">
    <!--    功能区域-->
    <div style="margin: 10px 0">
      <el-button type="primary" @click="add">新增</el-button>
    </div>
    <div style="margin: 10px 0">
      <el-input v-model="search" placeholder="请输入关键字" style="width: 20%;" clearable />
      <el-button @click="load" style="margin-left: 5px" type="primary">查询</el-button>
    </div>
    <el-table stripe :data="tableData" border style="width: 100%">
      <el-table-column sortable prop="id" label="ID" />
      <el-table-column prop="title" label="标题" />
      <el-table-column prop="author" label="作者"  />
      <el-table-column prop="time" label="时间" />
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
          width="50%">
        <el-form label-width="120px">
          <el-form-item label="标题">
            <el-input v-model="form.title" style="width: 50%;" />
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
  name: 'News',
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
      tableData: [],
      message:'',
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
      request.delete("/news/"+id).then(res=>{
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
        request.put('/news',this.form).then(res=>{
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
        request.post('/news',this.form).then(res=>{
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
      request.get("/news",{
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
    },
    fileUploadSuccess(res){
      console.log(res)
      this.form.cover = res.data
    }
  }
}
</script>
