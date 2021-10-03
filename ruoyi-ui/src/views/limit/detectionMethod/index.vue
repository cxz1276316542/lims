<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="126px">
      <el-row type="flex" justify="space-around">
        <el-col :span="8">
          <el-form-item label="检测方法名称" prop="detectionMethodName">
            <el-select v-model="queryParams.detectionMethodName" filterable size="small" clearable placeholder="请输入检测方法名称" >
              <el-option v-for="item in detectionMethodNameOptions"
                         :key="item.detectionMethodName"
                         :value="item.detectionMethodName"
                         :label="item.detectionMethodName"
              />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="操作描述" prop="detectionMethodInfo">
            <el-input
              v-model="queryParams.detectionMethodInfo"
              placeholder="请输入操作描述"
              clearable
              size="small"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="检测方法地址" prop="detectionMethodURL">
            <el-input
              v-model="queryParams.detectionMethodURL"
              placeholder="请输入检测方法地址"
              clearable
              size="small"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="有效性" prop="isValid" >
            <el-select placeholder="请选择是否有效" prop="isValid" size="small" clearable v-model="queryParams.isValid" >
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
        </el-col>
        <el-col :span="8" :pull="1">
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
            <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['limit:detectionMethod:add']"
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
          v-hasPermi="['limit:detectionMethod:edit']"
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
          v-hasPermi="['limit:detectionMethod:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="info"
          plain
          icon="el-icon-upload2"
          size="mini"
          @click="handleImport"
          v-hasPermi="['limit:detectionMethod:import']"
        >导入</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          :loading="exportLoading"
          @click="handleExport"
          v-hasPermi="['limit:detectionMethod:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="detectionMethodList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" type="index" align="center">
        <template slot-scope="scope">
          <span>{{(queryParams.pageNum - 1) * queryParams.pageSize + scope.$index + 1}}</span>
        </template>
      </el-table-column>
      <el-table-column label="检测方法名称" align="center" sortable prop="detectionMethodName" />
      <el-table-column label="操作描述" align="center" prop="detectionMethodInfo" />
      <el-table-column label="检测方法地址" align="center" prop="detectionMethodURL" />
      <el-table-column label="是否有效" align="center" prop="isValid" sortable>
        <template slot-scope="scope">
          <el-switch
            class="switch"
            v-model= "scope.row.isValid"
            active-text="启用"
            inactive-text="禁用"
            :active-value=1
            :inactive-value=0
            @change="changeValid(scope.row)"
          >
          </el-switch>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['limit:detectionMethod:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['limit:detectionMethod:remove']"
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

    <!-- 添加或修改检测方法管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="auto">
        <el-form-item label="检测方法名称" prop="detectionMethodName">
          <el-col :span="15.5">
            <el-input v-model="form.detectionMethodName" placeholder="请输入检测方法名称" />
          </el-col>
        </el-form-item>
        <el-form-item label="操作描述" >
          <el-col :span="15.5">
            <el-input v-model="form.detectionMethodInfo" placeholder="请输入操作描述" />
          </el-col>
        </el-form-item>
        <el-form-item label="检测方法地址" >
          <el-col :span="15.5">
            <el-input v-model="form.detectionMethodURL" placeholder="请输入检测方法地址" />
          </el-col>
        </el-form-item>
        <el-form-item label="有效性" prop="isValid">
          <el-col :span="14">
            <el-select placeholder="请选择是否有效" prop="isValid" size="medium"  clearable v-model="form.isValid">
              <el-option
                v-for="item in isValidOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>
          </el-col>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 检测方法导入对话框 -->
    <el-dialog :title="upload.title" :visible.sync="upload.open" width="400px" append-to-body>
      <el-upload
        ref="upload"
        :limit="1"
        accept=".xlsx, .xls"
        :headers="upload.headers"
        :action="upload.url + '?updateSupport=' + upload.updateSupport"
        :disabled="upload.isUploading"
        :on-progress="handleFileUploadProgress"
        :on-success="handleFileSuccess"
        :auto-upload="false"
        drag
      >
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">
          将文件拖到此处，或
          <em>点击上传</em>
        </div>
        <div class="el-upload__tip" slot="tip">
          <el-checkbox v-model="upload.updateSupport" />是否更新已经存在的用户数据
          <el-link type="info" style="font-size:12px" @click="importTemplate">下载模板</el-link>
        </div>
        <div class="el-upload__tip" style="color:red" slot="tip">提示：仅允许导入“xls”或“xlsx”格式文件！</div>
      </el-upload>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFileForm">确 定</el-button>
        <el-button @click="upload.open = false">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import { listDetectionMethod, getDetectionMethod, delDetectionMethod, addDetectionMethod, updateDetectionMethod, exportDetectionMethod, importTemplate } from "@/api/limit/detectionMethod";
  import {getToken} from "../../../utils/auth";
  import {isExistDetectionMethod} from "../../../api/limit/detectionMethod";

  export default {
    name: "DetectionMethod",
    data() {
      return {
        // 遮罩层
        loading: true,
        // 导出遮罩层
        exportLoading: false,
        // 选中数组
        ids: [],
        // 选中检查方法名称数组
        detectionMethodNames: [],
        // 非单个禁用
        single: true,
        // 非多个禁用
        multiple: true,
        // 显示搜索条件
        showSearch: true,
        // 总条数
        total: 0,
        // 检测方法管理表格数据
        detectionMethodList: [],
        // 检测方法下拉选项
        detectionMethodNameOptions: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          detectionMethodName: null,
          detectionMethodInfo: null,
          detectionMethodURL: null,
          isValid:null,
          detectionCenterID:null
        },
        //是否有效 true-有效 false-无效
        isValidOptions:[{
          value: 1,
          label:'有效'
        },{
          value: 0,
          label: '无效'
        }],
        // 表单参数
        form: {},
        // 检测标准导入参数
        upload: {
          // 是否显示弹出层（检测标准导入）
          open: false,
          // 弹出层标题（监察人标准导入）
          title: "",
          // 是否禁用上传
          isUploading: false,
          // 是否更新已经存在的检测标准数据
          updateSupport: 0,
          // 设置上传的请求头部
          headers: { Authorization: "Bearer " + getToken() },
          // 上传的地址
          url: process.env.VUE_APP_BASE_API + "/limit/detectionMethod/importData"
        },
        // 表单校验
        rules: {
          detectionMethodName: [
            { required: true, message: "检测方法名称不能为空", trigger: "blur" }
          ],
          isValid: [
            { required: true, message: "数据有效性标识 0-无效 1-有效", trigger: "blur" }
          ]
        }
      };
    },
    created() {
      this.getList();
      this.getDetectionMethodNameList();
      // this.isExist();
    },
    methods: {
      /** 查询检测方法管理列表 */
      getList() {
        this.loading = true;
        listDetectionMethod(this.queryParams).then(response => {
          this.detectionMethodList = response.rows;
          this.total = response.total;
          this.loading = false;
        });
      },
      // 获取检测方法名称下拉选项
      getDetectionMethodNameList(){
        listDetectionMethod().then(response =>{
          this.detectionMethodNameOptions = response.rows;
        })
      },
      isExist() {
        isExistDetectionMethod(this.form).then(response =>{
          console.log(response);
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
          detectionMethodID: null,
          detectionMethodName: null,
          detectionMethodInfo: null,
          detectionMethodURL: null,
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
        this.ids = selection.map(item => item.detectionMethodID);
        this.detectionMethodNames = selection.map(item => item.detectionMethodName);
        this.single = selection.length!==1;
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.form.isValid = 1;
        this.title = "添加检测方法管理";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const detectionMethodID = row.detectionMethodID || this.ids
        getDetectionMethod(detectionMethodID).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改检测方法管理";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(isValid => {
          if (isValid) {
            if (this.form.detectionMethodID != null) {
              updateDetectionMethod(this.form).then(response => {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
                this.getDetectionMethodNameList();
              });
            } else {
              addDetectionMethod(this.form).then(response => {
                this.msgSuccess("新增成功");
                this.open = false;
                this.getList();
                this.getDetectionMethodNameList();
              });
            }
          }
        });
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        const detectionMethodIDs = row.detectionMethodID || this.ids;
        const detectionMethodNames = row.detectionMethodName || this.detectionMethodNames;
        this.$confirm('是否确认删除检测方法名称为"' + detectionMethodNames + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delDetectionMethod(detectionMethodIDs);
        }).then(() => {
          this.getList();
          this.getDetectionMethodNameList();
          this.msgSuccess("删除成功");
        }).catch(() => {});
      },
      /** 导入按钮点击功能*/
      handleImport(){
        this.upload.title = "检测方法信息导入";
        this.upload.open = true;
      },
      /** 下载模板操作 */
      importTemplate() {
        importTemplate().then(response => {
          this.download(response.msg);
        });
      },
      // 文件上传中处理
      handleFileUploadProgress(event, file, fileList) {
        this.upload.isUploading = true;
      },
      // 文件上传成功处理
      handleFileSuccess(response, file, fileList) {
        this.upload.open = false;
        this.upload.isUploading = false;
        this.$refs.upload.clearFiles();
        this.$alert(response.msg, "导入结果", { dangerouslyUseHTMLString: true });
        this.getList();
        this.getDetectionMethodNameList();
      },
      // 提交上传文件
      submitFileForm() {
        this.$refs.upload.submit();
      },
      /** 导出按钮操作 */
      handleExport() {
        const queryParams = this.queryParams;
        this.$confirm('是否确认导出所有检测方法管理数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.exportLoading = true;
          return exportDetectionMethod(queryParams);
        }).then(response => {
          this.download(response.msg);
          this.exportLoading = false;
        }).catch(() => {});
      },
      changeValid(row){
        let flag = row.isValid; //保存点击之后v-modeld的值(true，false)
        row.isValid = row.isValid === 1 ? 0:1; //保持switch点击前的状态
        // let status = row.isValid === true ? '启用' : '禁用';
        this.$confirm('是否改变'+row.detectionMethodName+'的状态？', "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() =>{
          flag ? row.isValid = 1 : row.isValid = 0; // 这一步很重要，row.showState会根据flag的值开启或关闭开关
          updateDetectionMethod(row).then(response => {
            this.msgSuccess("修改成功");
            this.getList();
          });
        }).catch(() => {
          //这里处理取消操作时候的效果
          this.row.isValid =  !this.row.isValid;
        });
      }
    }

  };
</script>

<style>
  .switch .el-switch__label {
    position: absolute;
    display: none;
    color: #fff !important;

  }
  /*打开时文字位置设置*/
  .switch .el-switch__label--right {
    z-index: 1;
  }
  /* 调整打开时文字的显示位子 */
  .switch .el-switch__label--right span{
    margin-right: 9px;
  }
  /*关闭时文字位置设置*/
  .switch .el-switch__label--left {
    z-index: 1;
  }
  /* 调整关闭时文字的显示位子 */
  .switch .el-switch__label--left span{
    margin-left: 9px;
  }
  /*显示文字*/
  .switch .el-switch__label.is-active {
    display: block;
  }
  /* 调整按钮的宽度 */
  .switch.el-switch .el-switch__core,
  .el-switch .el-switch__label {
    width: 60px !important;
    margin: 0;
  }
</style>
