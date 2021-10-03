<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="126px">
      <el-row type="flex" justify="space-around">
        <el-col :span="8">
          <el-form-item label="标准编号" prop="standardNumber">
            <el-input
              v-model="queryParams.standardNumber"
              placeholder="请输入标准编号"
              clearable
              size="small"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="标准类型" prop="standardType">
            <el-select placeholder="请选择标准类型" prop="standardType" v-model="queryParams.standardType">
              <el-option
                v-for="item in standardTypeOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
                @keyup.enter.native="handleQuery"
              >
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="检测标准名称" prop="standardName" >
            <el-input
              v-model="queryParams.standardName"
              placeholder="请输入检测标准名称"
              clearable
              size="small"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" justify="space-around">
        <el-col :span="8">
          <el-form-item label="备注" prop="standardRemark" >
            <el-input
              v-model="queryParams.standardRemark"
              placeholder="请输入备注"
              clearable
              size="small"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="检测标准开始时间" prop="standardStartTime" >
            <el-date-picker clearable size="small"
                            v-model="queryParams.standardStartTime"
                            type="date"
                            value-format="yyyy-MM-dd"
                            placeholder="选择检测标准执行开始时间">
            </el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="检测标准结束时间" prop="standardEndTime" >
            <el-date-picker clearable size="small"
                            v-model="queryParams.standardEndTime"
                            type="date"
                            value-format="yyyy-MM-dd"
                            placeholder="选择检测标准执行结束时间">
            </el-date-picker>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" >
        <el-col :span="8">
          <el-form-item label="有效性" prop="isValid">
            <el-select placeholder="请选择标准类型" prop="isValid" v-model="queryParams.isValid" clearable>
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

    <!-- 检测标准息新增导出按钮   -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['limit:standard:add']"
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
          v-hasPermi="['limit:standard:edit']"
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
          v-hasPermi="['limit:standard:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="info"
          plain
          icon="el-icon-upload2"
          size="mini"
          @click="handleImport"
          v-hasPermi="['limit:standard:import']"
          v-loading.fullscreen.lock="importLimitInfoLoading"
          element-loading-text="正在导入限量库详细信息，请稍后！"
          element-loading-spinner="el-icon-loading"
          element-loading-background="rgba(0, 0, 0, 0.7)"
        >导入限量库明细</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          :loading="exportLoading"
          @click="handleExport"
          v-hasPermi="['limit:standard:export']"
        >导出检测标准信息</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 检测标准信息主体部分   -->

    <el-table
      :data="standardList"
      @selection-change="handleSelectionChange"
      v-loading.fullscreen.lock="exportLimitInfoLoading"
      element-loading-text="正在导出限量库详细信息，请稍后！"
      element-loading-spinner="el-icon-loading"
      element-loading-background="rgba(0, 0, 0, 0.7)"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" type="index" align="center">
        <template slot-scope="scope">
          <span>{{(queryParams.pageNum - 1) * queryParams.pageSize + scope.$index + 1}}</span>
        </template>
      </el-table-column>
      <el-table-column label="标准编号" align="center" sortable prop="standardNumber" />
      <el-table-column label="标准类型" align="center" sortable prop="standardType">
        <template slot-scope="scope">
          <span v-if="scope.row.standardType== 1">国标</span>
          <span v-if="scope.row.standardType== 2">地方标准</span>
          <span v-if="scope.row.standardType== 3">行业标准</span>
          <span v-if="scope.row.standardType== 4">非标</span>
        </template>
      </el-table-column>
      <el-table-column label="检测标准名称" align="center" prop="standardName" />
      <el-table-column label="备注" align="center" prop="standardRemark" />
      <el-table-column label="检测标准执行开始时间" align="center" sortable prop="standardStartTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.standardStartTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="检测标准执行结束时间" align="center" sortable prop="standardEndTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.standardEndTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="是否有效" align="center" prop="isValid">
        <template slot-scope="scope">
          <el-switch
            class="switch"
            v-model= "scope.row.isValid"
            active-text="启用"
            inactive-text="禁用"
            :active-value="1"
            :inactive-value="0"
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
            v-hasPermi="['limit:standard:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['limit:standard:remove']"
          >删除</el-button>
          <el-button
            type="text"
            plain
            icon="el-icon-download"
            size="mini"
            @click="handleLimitInfoExport(scope.row)"
            v-hasPermi="['limit:standard:exportLimitInfo']"
          >导出基础库明细</el-button>
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


    <!-- 添加或修改检测标准对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="auto">
        <el-form-item label="标准编号" prop="standardNumber">
          <el-col :span="16">
            <el-input v-model="form.standardNumber" placeholder="请输入标准编号" />
          </el-col>
        </el-form-item>
        <!--  prop="standardType"      -->
        <el-form-item label="标准类型" prop="standardType">
          <el-col :span="17">
            <el-select placeholder="请选择标准类型" prop="standardType" v-model="form.standardType">
              <el-option
                v-for="item in standardTypeOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
                @keyup.enter.native="handleQuery"
              >
              </el-option>
            </el-select>
          </el-col>
        </el-form-item>
        <el-form-item label="检测标准名称" prop="standardName">
          <el-col :span="16">
            <el-input v-model="form.standardName" placeholder="请输入检测标准名称" />
          </el-col>
        </el-form-item>
        <el-form-item label="有效性" prop="isValid">
          <el-select placeholder="请选择是否有效" prop="isValid" clearable v-model="form.isValid">
            <el-option
              v-for="item in isValidOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="备注" prop="standardRemark">
          <el-col :span="16">
            <el-input v-model="form.standardRemark" placeholder="请输入备注" />
          </el-col>
        </el-form-item>
        <el-form-item label="检测标准开始时间" prop="standardStartTime">
          <el-date-picker clearable size="small"
            v-model="form.standardStartTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择检测标准执行开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="检测标准结束时间" prop="standardEndTime">
          <el-date-picker clearable size="small"
            v-model="form.standardEndTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择检测标准执行结束时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="备注" prop="standardRemark">
          <el-input v-model="form.standardRemark" placeholder="请输入备注"  type="textarea" style="width:63%"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 限量库详细信息导入对话框 -->
    <!--        :action="upload.url + '?updateSupport=' + upload.updateSupport"-->
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
        <div class="el-upload__tip" style="color:#ff0000" slot="tip">提示：仅允许导入“xls”或“xlsx”格式文件！</div>
      </el-upload>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFileForm">确 定</el-button>
        <el-button @click="upload.open = false">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listStandard, getStandard, delStandard, addStandard, updateStandard, exportStandard, exportLimitInfo, importTemplate} from "@/api/limit/standard";
import { getToken } from "@/utils/auth";
export default {
  name: "Standard",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 导出遮罩层
      exportLoading: false,
      //导入限量库明细遮罩层
      importLimitInfoLoading:false,
      // 导出限量库明细遮罩层
      exportLimitInfoLoading:false,
      // 选中数组
      ids: [],
      //选中的检测编号数组
      standardNumbers:[],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 用户导入参数
      upload: {
        // 是否显示弹出层（用户导入）
        open: false,
        // 弹出层标题（用户导入）
        title: "",
        // 是否禁用上传
        isUploading: false,
        // 是否更新已经存在的用户数据
        updateSupport: 0,
        // 设置上传的请求头部
        headers: { Authorization: "Bearer " + getToken() },
        // 上传的地址
        url: process.env.VUE_APP_BASE_API + "/limit/standard/importLimitInfo"
      },
      //标准类型:1-国标 2-地方标准3-行业标准 4-非标
      standardTypeOptions: [{
        value: 1,
        label: '国标'
      }, {
        value: 2,
        label: '地方标准'
      }, {
        value: 3,
        label: '行业标准'
      }, {
        value: 4,
        label: '非标'
      }],
      //是否有效 1-有效 0-无效
      isValidOptions:[{
        value:1,
        label:"有效"
      },{
        value: 0,
        label: "无效"
      }],
      // 检测标准表格数据
      standardList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        standardNumber: null,
        standardType: null,
        standardName: null,
        standardRemark: null,
        standardStartTime: null,
        standardEndTime: null,
        isValid:null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        standardNumber: [
          { required: true, message: "标准编号不能为空", trigger: "blur" }
        ],
        standardType: [
          { required: true, message: "标准类型:1-国标 2-地方标准3-行业标准 4-非标不能为空", trigger: "blur" }
        ],
        standardName: [
          { required: true, message: "检测标准名称不能为空", trigger: "blur" }
        ],
        isValid: [
          { required: true, message: "数据有效性标识不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询检测标准列表 */
    getList() {
      this.loading = true;
      listStandard(this.queryParams).then(response => {
        this.standardList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        standard: null,
        standardNumber: null,
        standardType: null,
        standardName: null,
        standardRemark: null,
        standardStartTime: null,
        standardEndTime: null,
        isValid:null
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
      this.queryParams.standardType = "";
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.standard);
      this.standardNumbers = selection.map(item => item.standardNumber);
      this.single = selection.length!==1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.form.isValid = 1;
      this.open = true;
      this.title = "添加检测标准";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const standard = row.standard || this.ids
      getStandard(standard).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改检测标准";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.standard != null) {
            updateStandard(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addStandard(this.form).then(response => {
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
      const standards = row.standard || this.ids;
      const standardNumber = row.standardNumber || this.standardNumbers;
      this.$confirm('是否确认删除检测标准编号为"' + standardNumber + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delStandard(standards);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有检测标准数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.exportLoading = true;
          return exportStandard(queryParams);
        }).then(response => {
          this.download(response.msg);
          this.exportLoading = false;
        }).catch(() => {});
    },
    /** 导出限量库明细操作*/
    handleLimitInfoExport(row){
      console.log(row);
      const standardNumber ={standardNumber : row.standardNumber};

      this.$confirm('是否确认导出所有检测标准为"'+ row.standardNumber+'"的基础库明细', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        this.exportLimitInfoLoading = true;
        return exportLimitInfo(row);
      }).then(response => {
        this.download(response.msg);
        this.exportLimitInfoLoading = false;
      }).catch(() => {
        this.msgInfo("基础库明细导入失败！");
        this.exportLimitInfoLoading = false;
      });
    },
    /** 导入按钮操作*/
    handleImport(){
      this.upload.title = "限量库详细信息导入";
      this.upload.open = true;
    },
    /**提交上传文件*/
    submitFileForm(){
      this.importLimitInfoLoading = true;
      this.$refs.upload.submit();
    },
    /**下载模板*/
    importTemplate(){
      importTemplate().then(response =>{
        this.download(response.msg);
      })
    },
    //文件上传中处理
    handleFileUploadProgress(event, file, fileList){
      this.upload.isUploading = true;
    },
    //文件上传成功处理
    handleFileSuccess(response, file, fileList) {
      this.upload.open = false;
      this.upload.isUploading = false;
      this.$refs.upload.clearFiles();
      this.importLimitInfoLoading = false;
      this.$alert(response.msg, "导入结果", { dangerouslyUseHTMLString: true });
      // this.getList();
    },
    changeValid(row){
      // let status = row.valid === true ? '启用' : '禁用';
      let flag = row.isValid; //保存点击之后v-model的值(1，0)
      console.log(flag);
      //保持switch点击前的状态
      row.isValid = row.isValid===1 ? 0 : 1;

      this.$confirm('是否改变'+row.standardNumber+'的状态？', "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() =>{
        flag ? row.isValid = 1 : row.isValid = 0; // 这一步很重要，row.showState会根据flag的值开启或关闭开关
        updateStandard(row).then(response => {
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


