<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="126px">
      <el-row type="flex" justify="space-around">
        <el-col :span="8">
          <el-form-item label="项目名称" prop="pesticideName">
            <el-select placeholder="请选择项目名称" v-model="queryParams.pesticideName" filterable clearable :loading="allPesticideLoading">
              <el-option
                v-for="item in allPesticideList"
                :key="item.pesticideID"
                :label="item.pesticideName"
                :value="item.pesticideName"
                @keyup.enter.native="handleQuery"
              />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="项目英文名称" prop="pesticideEnglishName" >
            <el-select placeholder="请选择项目英文名称" v-model="queryParams.pesticideEnglishName" filterable clearable :loading="allPesticideLoading">
              <el-option
                v-for="item in allPesticideList"
                :key="item.pesticideID"
                :label="item.pesticideEnglishName"
                :value="item.pesticideEnglishName"
                @keyup.enter.native="handleQuery"
              />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="豁免" prop="pesticideExempt">
            <el-select placeholder="请选择是否豁免" prop="pesticideExempt" v-model="queryParams.pesticideExempt" filterable clearable>
              <el-option
                v-for="item in pesticideExemptOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
                @keyup.enter.native="handleQuery"
              >
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" justify="space-around">
        <el-col :span="8">
          <el-form-item label="成人每千克摄入量" prop="adi" >
            <el-input
              v-model="queryParams.adi"
              placeholder="请输入成人每千克摄入量"
              clearable
              size="small"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="功能" prop="pesticideFunction">
            <el-input
              v-model="queryParams.pesticideFunction"
              placeholder="请输入功能"
              clearable
              size="small"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="有效性" prop="isValid">
            <el-select placeholder="请选择是否有效" prop="isValid" v-model="queryParams.isValid" filterable clearable>
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
      </el-row>
      <el-row>
      <el-col :span="6" :offset="18">
        <el-form-item>
          <el-button type="primary" icon="el-sicon-search" size="mini" @click="handleQuery">搜索</el-button>
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
          v-hasPermi="['limit:pesticide:add']"
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
          v-hasPermi="['limit:pesticide:edit']"
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
          v-hasPermi="['limit:pesticide:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="info"
          plain
          icon="el-icon-upload2"
          size="mini"
          @click="handleImport"
          v-hasPermi="['limit:pesticide:import']"
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
          v-hasPermi="['limit:pesticide:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="pesticideList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" type="index" align="center">
        <template slot-scope="scope">
          <span>{{(queryParams.pageNum - 1) * queryParams.pageSize + scope.$index + 1}}</span>
        </template>
      </el-table-column>
      <el-table-column label="项目名称" align="center" sortable prop="pesticideName" />
      <el-table-column label="项目英文名称" align="center" prop="pesticideEnglishName" />
      <el-table-column label="豁免" align="center" sortable prop="pesticideExempt">
        <template slot-scope="scope">
          <span v-if="scope.row.pesticideExempt === 0">不豁免</span>
          <span v-if="scope.row.pesticideExempt === 1">豁免</span>
        </template>
      </el-table-column>
      <el-table-column label="成人每千克摄入量" align="center" sortable prop="adi" />
      <el-table-column label="残留物" align="center" prop="pesticideResidues" />
      <el-table-column label="功能" align="center" prop="pesticideFunction" />
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
            v-hasPermi="['limit:pesticide:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['limit:pesticide:remove']"
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

    <!-- 添加或修改检测农药管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="auto">
        <el-form-item label="项目名称" prop="pesticideName">
          <el-col :span="16">
            <el-input v-model="form.pesticideName" placeholder="请输入项目名称" />
          </el-col>
        </el-form-item>
        <el-form-item label="项目英文名称" prop="pesticideEnglishName">
          <el-col :span="16">
            <el-input v-model="form.pesticideEnglishName" placeholder="请输入项目英文名称" />
          </el-col>
        </el-form-item>
        <el-form-item label="豁免" prop="pesticideExempt">
          <el-col :span="16">
            <el-select placeholder="请选择是否豁免" prop="pesticideExempt" v-model="form.pesticideExempt">
              <el-option
                v-for="item in pesticideExemptOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>
          </el-col>
        </el-form-item>
        <el-form-item label="有效性" prop="isValid">
          <el-col :span="16">
            <el-select placeholder="请选择是否有效" prop="isValid" clearable v-model="form.isValid">
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
        <el-form-item label="成人每千克摄入量" prop="adi">
          <el-col :span="16">
            <el-input v-model="form.adi" placeholder="请输入成人每千克摄入量" />
          </el-col>
        </el-form-item>
        <el-form-item label="残留物" prop="pesticideResidues">
          <el-col :span="16">
            <el-input v-model="form.pesticideResidues" type="textarea" placeholder="请输入残留物" />
          </el-col>
        </el-form-item>
        <el-form-item label="功能" prop="pesticideFunction">
          <el-col :span="16">
            <el-input v-model="form.pesticideFunction" placeholder="请输入功能" />
          </el-col>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 检测标准导入对话框 -->
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
import { listPesticide, getPesticide, delPesticide, addPesticide, updatePesticide, exportPesticide, importTemplate } from "@/api/limit/pesticide";
import { getToken } from "@/utils/auth";
export default {
  name: "Pesticide",
  data() {
    return {
      // 遮罩层
      loading: true,
      //所有检测农药信息
      allPesticideLoading:true,
      // 导出遮罩层
      exportLoading: false,
      // 选中数组
      ids: [],
      //选中农药名称数组
      pesticideNames:[],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 检测农药管理表格数据
      pesticideList: [],
      //所有检测农药数据
      allPesticideList:[],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
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
        url: process.env.VUE_APP_BASE_API + "/limit/pesticide/importData"
      },
      //是否豁免 0-不豁免 1-豁免
      pesticideExemptOptions: [{
        value: 0,
        label: '不豁免'
      }, {
        value: 1,
        label: '豁免'
      }],
      //是否有效 1-有效 0-无效
      isValidOptions:[{
        value:1,
        label:"有效"
      },{
        value: 0,
        label: "无效"
      }],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        pesticideName: null,
        pesticideEnglishName: null,
        pesticideExempt: null,
        adi: null,
        pesticideResidues: null,
        pesticideFunction: null,
        isValid:null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        pesticideName: [
          { required: true, message: "项目名称不能为空", trigger: "blur" }
        ],
        pesticideEnglishName: [
          { required: true, message: "项目英文名称不能为空", trigger: "blur" }
        ],
        pesticideExempt: [
          { required: true, message: "豁免不能为空", trigger: "blur" }
        ],
        isValid: [
          { required: true, message: "数据有效性标识不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
    this.getAllPesticideList();
  },
  methods: {
    /** 查询检测农药管理列表 */
    getList() {
      this.loading = true;
      listPesticide(this.queryParams).then(response => {
        this.pesticideList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    getAllPesticideList(){
      this.allPesticideLoading = true;
      listPesticide().then(response =>{
        this.allPesticideList = response.rows;
        this.allPesticideLoading = false;
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
        pesticideID: null,
        pesticideName: null,
        pesticideEnglishName: null,
        pesticideExempt: null,
        adi: null,
        pesticideResidues: null,
        pesticideFunction: null,
        isValid:null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      console.log(this.queryParams);
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
      this.ids = selection.map(item => item.pesticideID)
      this.pesticideNames = selection.map(item => item.pesticideName)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.form.isValid = 1;
      this.open = true;
      this.title = "添加检测农药管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const pesticideID = row.pesticideID || this.ids;
      getPesticide(pesticideID).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改检测农药管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.pesticideID != null) {
            updatePesticide(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
              this.getAllPesticideList();
            });
          } else {
            addPesticide(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
              this.getAllPesticideList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const pesticideIDs = row.pesticideID || this.ids;
      const pesticideNames = row.pesticideName || this.pesticideNames;
      this.$confirm('是否确认删除检测农药名为"' + pesticideNames + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delPesticide(pesticideIDs);
        }).then(() => {
          this.getList();
          this.getAllPesticideList();
          this.msgSuccess("删除成功");
        }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有检测农药管理数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.exportLoading = true;
          return exportPesticide(queryParams);
        }).then(response => {
          this.download(response.msg);
          this.exportLoading = false;
        }).catch(() => {});
    },

    /** 导入按钮点击功能*/
    handleImport(){
      this.upload.title = "检测标准信息导入";
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
      this.getAllPesticideList();
    },
    // 提交上传文件
    submitFileForm() {
      this.$refs.upload.submit();
    },
    changeValid(row){
      // let status = row.valid === true ? '启用' : '禁用';
      let flag = row.isValid; //保存点击之后v-model的值(1，0)
      console.log(flag);
      //保持switch点击前的状态
      row.isValid = row.isValid===1 ? 0 : 1;

      this.$confirm('是否改变'+row.pesticideName+'的状态？', "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() =>{
          flag ? row.isValid = 1 : row.isValid = 0; // 这一步很重要，row.showState会根据flag的值开启或关闭开关
          updatePesticide(row).then(response => {
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

