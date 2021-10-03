<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="auto">
      <el-form-item label="检测员" prop="inspectorID">
        <el-select placeholder="请选择检测员名字" clearable filterable v-model="queryParams.inspectorID" clearable>
          <el-option
            v-for="item in inspectorNameOptions"
            :key="item.userId"
            :label="item.nickName"
            :value="item.userId"
            @keyup.enter.native="handleQuery"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="检测项目" prop="pesticideName">
        <!--          <el-input v-model="form.pesticideName" placeholder="请输入检测项目名称" v-bind:disabled="isAdd"/>-->
        <el-select placeholder="请选择检测项目名称" clearable filterable v-model="queryParams.pesticideID">
          <el-option
            v-for="item in pesticideList"
            :key="item.pesticideID"
            :label="item.pesticideName"
            :value="item.pesticideID"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="有效性" prop="isValid">
        <el-select placeholder="请选择是否有效" prop="isValid" clearable filterable v-model="queryParams.isValid" >
          <el-option
            v-for="item in isValidOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value"
            @keyup.enter.native="handleQuery"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['limit:inspectorPesticide:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['limit:inspectorPesticide:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['limit:inspectorPesticide:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
		  :loading="exportLoading"
          @click="handleExport"
          v-hasPermi="['limit:inspectorPesticide:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="inspectorPesticideList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" type="index" width="55" align="center">
        <template slot-scope="scope">
          <span>{{(queryParams.pageNum - 1) * queryParams.pageSize + scope.$index + 1}}</span>
        </template>
      </el-table-column>
      <el-table-column label="检测员名字" align="center" prop="inspectorName" />
      <el-table-column label="检测项目名字" align="center" prop="pesticideName" />
      <el-table-column label="是否有效" align="center" prop="isValid">
        <template slot-scope="scope">
          <el-radio-group v-model="scope.row.isValid" size="mini" @change="changeValid(scope.row)">
            <el-radio-button :label="0">无效</el-radio-button>
            <el-radio-button :label="1">有效</el-radio-button>
            <el-radio-button :label="2">默认</el-radio-button>
          </el-radio-group>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['limit:inspectorPesticide:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['limit:inspectorPesticide:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改检测员-检测项目对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="auto">
        <el-form-item label="检测员" prop="inspectorID">
          <el-select placeholder="请选择检测员名字" clearable filterable v-model="form.inspectorID" v-bind:disabled="isAdd">
            <el-option
              v-for="item in inspectorNameOptions"
              :key="item.userId"
              :label="item.nickName"
              :value="item.userId"
              @keyup.enter.native="handleQuery"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="检测项目" prop="pesticideID">
          <el-select placeholder="请选择检测项目名字" clearable filterable v-model="form.pesticideID" v-bind:disabled="isAdd">
            <el-option
              v-for="item in pesticideList"
              :key="item.pesticideID"
              :label="item.pesticideName"
              :value="item.pesticideID"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="有效性" prop="isValid">
          <el-select placeholder="请选择是否有效" clearable filterable v-model="form.isValid" >
            <el-option
              v-for="item in isValidOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"
              @keyup.enter.native="handleQuery"
            >
            </el-option>
          </el-select>
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
import { listInspectorPesticide, getInspectorPesticide, delInspectorPesticide, addInspectorPesticide, updateInspectorPesticide, exportInspectorPesticide, listInspectorName } from "@/api/limit/inspectorPesticide";
import {listPesticide} from "../../../api/limit/pesticide";

export default {
  name: "InspectorPesticide",
  data() {
    return {
      // 遮罩层
      loading: false,
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
      // 检测员-检测项目表格数据
      inspectorPesticideList: [],
      // 检测员名字下拉列表
      inspectorNameOptions: [],
      // 检测项目列表
      pesticideList: [],
      // 是否是新增页面
      isAdd: false,
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 有效性标识
      isValidOptions:[{
        value: 0,
        label: '无效'
      },{
        value: 1,
        label:'有效'
      },{
        value: 2,
        label:'默认'
      }],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        inspectorID: null,
        inspectorName:null,
        pesticideID: null,
        detectionCenterID: null,
        isValid: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        inspectorID: [
          { required: true, message: "检测员名字不能为空", trigger: "blur" }
        ],
        pesticideID: [
          { required: true, message: "检测项目名字不能为空", trigger: "blur" }
        ],
        isValid: [
          { required: true, message: "数据有效性标识不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
    this.getInspectorNameList();
    this.getPesticideList();
  },
  methods: {
    /** 查询检测员-检测项目列表 */
    getList() {
      this.loading = true;
      listInspectorPesticide(this.queryParams).then(response => {
        this.inspectorPesticideList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    //获取检测员名字下拉列表
    getInspectorNameList(){
      listInspectorName().then(response => {
        this.inspectorNameOptions = response.rows;
        console.log("============",this.inspectorNameOptions);
        this.total = response.total;
      })
    },
    // 获取检测项目下拉列表
    getPesticideList(){
      listPesticide().then(response => {
        this.pesticideList = response.rows;
      })
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        inspectorPesticideID: null,
        inspectorID: null,
        pesticideID: null,
        detectionCenterID: null,
        isValid: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      console.log(this.queryParams);
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.inspectorPesticideID)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.isAdd = false;
      this.form.isValid = 1;
      this.title = "添加检测员-检测项目";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const inspectorPesticideID = row.inspectorPesticideID || this.ids
      getInspectorPesticide(inspectorPesticideID).then(response => {
        this.form = response.data;
        this.open = true;
        this.isAdd= true;
        this.title = "修改检测员-检测项目";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.inspectorPesticideID != null) {
            updateInspectorPesticide(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addInspectorPesticide(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const inspectorPesticideIDs = row.inspectorPesticideID || this.ids;
      this.$confirm('是否确认删除检测员-检测项目编号为"' + inspectorPesticideIDs + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delInspectorPesticide(inspectorPesticideIDs);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有检测员-检测项目数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.exportLoading = true;
          return exportInspectorPesticide(queryParams);
        }).then(response => {
          this.download(response.msg);
          this.exportLoading = false;
        }).catch(() => {});
    },
    changeValid(row){
      updateInspectorPesticide(row).then(response => {
        this.msgSuccess("修改成功");
        this.getList();
      });
    }
  }
};
</script>
