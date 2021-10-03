<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="编号种类ID" prop="numberTypeID">
        <el-input
          v-model="queryParams.numberTypeID"
          placeholder="请输入编号种类ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="编号字段内容" prop="content">
        <el-input
          v-model="queryParams.content"
          placeholder="请输入编号字段内容"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="编号字段长度" prop="contentLength">
        <el-input
          v-model="queryParams.contentLength"
          placeholder="请输入编号字段长度"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="编号字段描述" prop="description">
        <el-input
          v-model="queryParams.description"
          placeholder="请输入编号字段描述"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="顺序" prop="order">
        <el-input
          v-model="queryParams.order"
          placeholder="请输入顺序"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <right-toolbar :showSearch.sync="showSearch" @queryTable="searchAll"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="tableData"  row-key="numberID" :tree-props="{children: 'children', hasChildren: 'hasChildren'}">
      <el-table-column label="编号字段ID" align="center" prop="numberID" v-if="false"/>
      <el-table-column label="编号种类ID" align="center" prop="numberTypeID" v-if="false"/>
      <el-table-column label="编号字段内容" align="center" prop="content" />
      <el-table-column label="编号字段长度" align="center" prop="contentLength" />
      <el-table-column label="编号字段描述" align="center" prop="description" />
      <el-table-column label="顺序" align="center" prop="order" />
      <el-table-column label="备注" align="center" prop="remark" v-if="false" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <span v-if="scope.row.numberID < 0">
            <el-button
                  size="mini"
                  type="text"
                  icon="el-icon-edit"
                  @click="handleAdd(scope.row)"
                  v-hasPermi="['system:number:edit']"
          >新增</el-button>
          </span>
          <span v-if="scope.row.numberID > 0">
            <el-button
                  size="mini"
                  type="text"
                  icon="el-icon-edit"
                  @click="handleUpdate(scope.row)"
                  v-hasPermi="['system:number:edit']"
          >修改</el-button>
                <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-edit"
                        @click="handleDelete(scope.row)"
                        v-hasPermi="['system:number:edit']"
                >删除</el-button>
          </span>
        </template>
      </el-table-column>
    </el-table>


    <!-- 新增修改编号生成对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="编号种类" prop="numberTypeID" :disabled="true">
          <span v-if="form.numberTypeID == 0">委托单编号</span>
          <span v-else-if="form.numberTypeID == 1">检品编号</span>
          <span v-else-if="form.numberTypeID == 2">任务单编号</span>
          <span v-else-if="form.numberTypeID == 3">检测编号</span>
        </el-form-item>
        <el-form-item label="编号字段内容" prop="content">
          <el-input v-model="form.content" placeholder="请输入编号字段内容" />
        </el-form-item>
        <el-form-item label="编号字段长度" prop="contentLength">
          <el-input v-model="form.contentLength" placeholder="请输入编号字段长度" />
        </el-form-item>
        <el-form-item label="编号字段描述" prop="description">
          <el-input v-model="form.description" placeholder="请输入编号字段描述" />
        </el-form-item>
        <el-form-item label="顺序" prop="order">
          <el-input v-model="form.order" placeholder="请输入顺序" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listNumber, getNumber, delNumber, addNumber, updateNumber, exportNumber,findAllContent,
  findContentListByNumberType } from "@/api/system/number";

export default {
  name: "Number",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 导出遮罩层
      exportLoading: false,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 编号生成表格数据
      numberList: [],
      // 表格数据
      tableData: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        numberTypeID: null,
        content: null,
        contentLength: null,
        description: null,
        order: null,
        remark: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.searchAll();
  },
  methods: {

    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        numberID: null,
        numberTypeID: null,
        content: null,
        contentLength: null,
        description: null,
        order: null,
        remark: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.searchAll();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },

    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      getNumber(row.numberID).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改编号生成";
      });
    },
    /**新增按钮*/
    handleAdd(row){
      this.reset();
      this.form.numberTypeID = row.numberTypeID
      this.title = '新增字段'
      this.open = true;
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.numberID != null) {
            updateNumber(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.searchAll();
            });
          } else {
            addNumber(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.searchAll();
            });
          }
        }
      });
    },
    // 查询所有编号
    searchAll(){
      this.loading = true;
      findAllContent().then(res =>{
        this.tableData = res.data
        this.loading = false
      })
    },
    // 删除编号
    handleDelete(row){
      delNumber(row.numberID).then(res=>{
        this.msgSuccess("删除成功");
        this.searchAll();
      })
    }
  },
};
</script>
