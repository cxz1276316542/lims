<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="检测项目名称" prop="pesticideID" label-width="100px">
        <el-select placeholder="请选择检测项目名称" filterable v-model="queryParams.pesticideID" clearable :loading="pesticideLoading">
          <el-option
            v-for="item in pesticideList"
            :key="item.pesticideID"
            :label="item.pesticideName"
            :value="item.pesticideID"
            @keyup.enter.native="handleQuery"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="检测方法名称" prop="detectionMethodID" label-width="100px">
        <el-select placeholder="请选择检测方法名称" filterable v-model="queryParams.detectionMethodID" clearable :loading="detectionMethodLoading">
          <el-option
            v-for="item in detectionMethodList"
            :key="item.detectionMethodID"
            :label="item.detectionMethodName"
            :value="item.detectionMethodID"
            @keyup.enter.native="handleQuery"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="有效性" prop="isValid">
        <el-select placeholder="请选择是否有效" prop="isValid" v-model="queryParams.isValid" clearable>
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
          v-hasPermi="['limit:pesticideDetectionMethod:add']"
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
          v-hasPermi="['limit:pesticideDetectionMethod:edit']"
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
          v-hasPermi="['limit:pesticideDetectionMethod:remove']"
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
          v-hasPermi="['limit:pesticideDetectionMethod:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="pesticideDetectionMethodList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" type="index" width="70" align="center">
        <template slot-scope="scope">
          <span>{{(queryParams.pageNum - 1) * queryParams.pageSize + scope.$index + 1}}</span>
        </template>
      </el-table-column>
      <el-table-column label="检测项目名称" align="center" prop="pesticideName" />
      <el-table-column label="检测方法名称" align="center" prop="detectionMethodName" />
      <el-table-column label="是否有效" align="center" prop="isValid" >
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
            v-hasPermi="['limit:pesticideDetectionMethod:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['limit:pesticideDetectionMethod:remove']"
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

    <!-- 添加或修改检测项目-检测方法对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="106px">
        <el-form-item label="检测项目名称" prop="pesticideID">
          <el-select placeholder="请选择检测项目名称" filterable v-model="form.pesticideID" clearable v-bind:disabled="isAdd" :loading="pesticideLoading">
            <el-option
              v-for="item in pesticideList"
              :key="item.pesticideID"
              :label="item.pesticideName"
              :value="item.pesticideID"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="检测方法名称" prop="detectionMethodID">
          <el-select placeholder="请选择检测方法名称" filterable v-model="form.detectionMethodID" clearable v-bind:disabled="isAdd" :loading="detectionMethodLoading">
            <el-option
              v-for="item in detectionMethodValidList"
              :key="item.detectionMethodID"
              :label="item.detectionMethodName"
              :value="item.detectionMethodID"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="有效性" prop="isValid">
          <el-select placeholder="请选择是否有效" prop="isValid" clearable v-model="form.isValid" >
            <el-option
              v-for="item in isValidOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"
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
import { listPesticideDetectionMethod, getPesticideDetectionMethod, delPesticideDetectionMethod, addPesticideDetectionMethod, updatePesticideDetectionMethod, exportPesticideDetectionMethod } from "@/api/limit/pesticideDetectionMethod";
import {listPesticide} from "@/api/limit/pesticide";
import {listDetectionMethod} from "@/api/limit/detectionMethod";
export default {
  name: "PesticideDetectionMethod",
  data() {
    return {
      pesticideLoading:true,
      detectionMethodLoading:true,
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
      // 检测项目-检测方法表格数据
      pesticideDetectionMethodList: [],
      //检测项目列表
      pesticideList:[],
      //检测方法
      detectionMethodList:[],
      //有效的检测方法
      detectionMethodValidList:[],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      //是否是新增页面
      isAdd:false,
      //是否有效 1-有效 0-无效
      isValidOptions:[{
        value: 0,
        label: "无效"
      },{
        value:1,
        label:"有效"
      },{
        value: 2,
        label: "默认"
      }],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        pesticideID: null,
        pesticideName: null,
        detectionMethodID: null,
        detectionMethodName: null,
        detectionCenterID: null,
        isValid: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        pesticideID: [
          { required: true, message: "检测项目名称不能为空", trigger: "blur" }
        ],
        detectionMethodID: [
          { required: true, message: "检测方法名称不能为空", trigger: "blur" }
        ],
        detectionCenterID: [
          { required: true, message: "检测中心ID不能为空", trigger: "blur" }
        ],
        isValid: [
          { required: true, message: "数据有效性标识不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
    this.getPesticideList();
    this.getDetectionMethodList();
  },
  methods: {
    /** 查询检测项目-检测方法列表 */
    getList() {
      this.loading = true;
      listPesticideDetectionMethod(this.queryParams).then(response => {
        this.pesticideDetectionMethodList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    getPesticideList(){
      this.pesticideLoading = true;
      listPesticide().then(response => {
        this.pesticideList = response.rows;
        this.pesticideLoading = false;
      });
    },
    getDetectionMethodList(){
      this.detectionMethodLoading = true;
      listDetectionMethod().then(response =>{
        this.detectionMethodList = response.rows;
        this.getDetectionMethodValidList();
        this.detectionMethodLoading = false;
      });
    },
    //筛选出有效的检测方法
    getDetectionMethodValidList(){
      this.detectionMethodValidList = this.detectionMethodList.filter(x=>x.isValid===1);
      console.log(this.detectionMethodValidList);
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        pesticideDetectionMethodID: null,
        pesticideID: null,
        pesticideName: null,
        detectionMethodID: null,
        detectionMethodName: null,
        detectionCenterID: null,
        isValid: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.pesticideDetectionMethodID);
      this.single = selection.length!==1;
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.isAdd = false;
      this.form.isValid = 1;
      this.open = true;
      this.title = "添加检测项目-检测方法";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const pesticideDetectionMethodID = row.pesticideDetectionMethodID || this.ids;
      getPesticideDetectionMethod(pesticideDetectionMethodID).then(response => {
        this.form = response.data;
        this.isAdd = true;
        this.open = true;
        this.title = "修改检测项目-检测方法";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.pesticideDetectionMethodID != null) {
            updatePesticideDetectionMethod(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPesticideDetectionMethod(this.form).then(response => {
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
      const pesticideDetectionMethodIDs = row.pesticideDetectionMethodID || this.ids;
      let length = this.ids.length===0 ? 1:this.ids.length;
      this.$confirm('是否确认删除'+ length +'条数据?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delPesticideDetectionMethod(pesticideDetectionMethodIDs);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有检测项目-检测方法数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.exportLoading = true;
          return exportPesticideDetectionMethod(queryParams);
        }).then(response => {
          this.download(response.msg);
          this.exportLoading = false;
        }).catch(() => {});
    },
    changeValid(row){
      updatePesticideDetectionMethod(row).then(response => {
        this.msgSuccess("修改成功");
        this.getList();
      });
    }
  }
};
</script>
