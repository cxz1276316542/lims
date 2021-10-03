<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="126px">
      <el-row type="flex" justify="space-around">
        <el-col :span="8">
          <el-form-item label="采购员名字" prop="purchaserID">
            <el-select placeholder="请选择采购员名字" prop="purchaserID" filterable v-model="queryParams.purchaserID" clearable>
              <el-option
                v-for="item in purchaserNameOptions"
                :key="item.userId"
                :label="item.nickName"
                :value="item.userId"
                @keyup.enter.native="handleQuery"
              >
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="检测仪器编号" prop="instrumentNumber">
            <el-input
              v-model="queryParams.instrumentNumber"
              placeholder="请输入检测仪器编号"
              clearable
              size="small"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="检测仪器名称" prop="instrumentName">
            <el-input
              v-model="queryParams.instrumentName"
              placeholder="请输入检测仪器名称"
              clearable
              size="small"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="生产厂家" prop="manufacturer">
            <el-input
              v-model="queryParams.manufacturer"
              placeholder="请输入生产厂家"
              clearable
              size="small"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="启用日期" prop="launchDate">
            <el-date-picker clearable size="small"
                            v-model="queryParams.launchDate"
                            type="date"
                            value-format="yyyy-MM-dd"
                            placeholder="选择启用日期">
            </el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="采购日期" prop="purchaseDate">
            <el-date-picker clearable size="small"
                            v-model="queryParams.purchaseDate"
                            type="date"
                            value-format="yyyy-MM-dd"
                            placeholder="选择采购日期">
            </el-date-picker>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="仪器状态" prop="status">
            <el-select placeholder="请选择仪器状态" prop="status" v-model="queryParams.status" clearable>
              <el-option
                v-for="item in instrumentStatusOptions"
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
          <el-form-item label="有效性" prop="isValid" >
            <el-select placeholder="请选择是否有效" prop="isValid" clearable v-model="queryParams.isValid" >
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
          v-hasPermi="['limit:instrument:add']"
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
          v-hasPermi="['limit:instrument:edit']"
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
          v-hasPermi="['limit:instrument:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="info"
          plain
          icon="el-icon-upload2"
          size="mini"
          @click="handleImport"
          v-hasPermi="['limit:instrument:import']"
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
          v-hasPermi="['limit:instrument:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="instrumentList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" type="index" align="center">
        <template slot-scope="scope">
          <span>{{(queryParams.pageNum - 1) * queryParams.pageSize + scope.$index + 1}}</span>
        </template>
      </el-table-column>
      <el-table-column label="采购员名字" align="center" prop="purchaserName" />
      <el-table-column label="检测仪器编号" align="center" prop="instrumentNumber" />
      <el-table-column label="检测仪器名称" align="center" prop="instrumentName" />
      <el-table-column label="生产厂家" align="center" prop="manufacturer" />
      <el-table-column label="启用日期" align="center"  prop="launchDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.launchDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="采购日期" align="center" sortable prop="purchaseDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.purchaseDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="仪器状态" align="center" sortable prop="status" >
        <template slot-scope="scope">
          <span v-if="scope.row.status === 0">未使用</span>
          <span v-if="scope.row.status === 1">正常</span>
          <span v-if="scope.row.status === 2">损坏</span>
        </template>
      </el-table-column>
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
            v-hasPermi="['limit:instrument:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['limit:instrument:remove']"
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

    <!-- 添加或修改检测仪器管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="106px">
        <el-form-item label="采购员名字" prop="purchaserID">
          <el-col :span="15">
            <el-select placeholder="请选择采购员名字" prop="purchaserID" v-model="form.purchaserID" clearable>
              <el-option
                v-for="item in purchaserNameOptions"
                :key="item.userId"
                :label="item.nickName"
                :value="item.userId"
                @keyup.enter.native="handleQuery"
              >
              </el-option>
            </el-select>
          </el-col>
        </el-form-item>
        <el-form-item label="检测仪器名称" prop="instrumentName">
          <el-col :span="15">
            <el-input v-model="form.instrumentName" placeholder="请输入检测仪器名称" />
          </el-col>
        </el-form-item>
        <el-form-item label="生产厂家" prop="manufacturer">
          <el-col :span="15">
            <el-input v-model="form.manufacturer" placeholder="请输入生产厂家" />
          </el-col>
        </el-form-item>
        <el-form-item label="启用日期" prop="launchDate">
          <el-col :span="14">
            <el-date-picker clearable size="medium"
                            v-model="form.launchDate"
                            type="date"
                            value-format="yyyy-MM-dd"
                            placeholder="选择启用日期">
            </el-date-picker>
          </el-col>
        </el-form-item>
        <el-form-item label="采购日期" prop="purchaseDate">
          <el-date-picker clearable size="medium"
                          v-model="form.purchaseDate"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择采购日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="仪器状态" prop="status">
          <el-select placeholder="请选择仪器状态" prop="status" v-model="form.status" clearable>
            <el-option
              v-for="item in instrumentStatusOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"
              @keyup.enter.native="handleQuery"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="有效性" prop="isValid" >
          <el-select placeholder="请选择是否有效" prop="isValid" clearable v-model="form.isValid" >
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

    <!-- 检测仪器导入对话框 -->
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
  import { listInstrument, getInstrument, delInstrument, addInstrument, updateInstrument, exportInstrument, importTemplate } from "@/api/limit/instrument";
  import {getToken} from "../../../utils/auth";
  import {listUser} from "../../../api/system/user";

  export default {
    name: "Instrument",
    data() {
      return {
        // 遮罩层
        loading: true,
        // 导出遮罩层
        exportLoading: false,
        // 导出检测方法-检测仪器遮罩层
        exportInstrumentMethodLoading: false,
        // 选中数组
        ids: [],
        // 选中检测仪器名称数组
        instrumentNames: [],
        // 非单个禁用
        single: true,
        // 非多个禁用
        multiple: true,
        // 显示搜索条件
        showSearch: true,
        // 总条数
        total: 0,
        // 检测仪器管理表格数据
        instrumentList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        //仪器状态 0-未使用 1-正常 2-损坏
        instrumentStatusOptions: [{
          value: 0,
          label: '未使用'
        }, {
          value: 1,
          label: '正常'
        }, {
          value: 2,
          label: '损坏'
        }],
        // 采购员名字下拉选项
        purchaserNameOptions: [],
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          purchaserID: null,
          // purchaserName: null,
          instrumentNumber: null,
          instrumentName: null,
          manufacturer: null,
          launchDate: null,
          purchaseDate: null,
          status: null,
          isValid:null,
          detectionCenterID:null
        },
        // 检测仪器导入参数
        upload: {
          // 是否显示弹出层（检测标准导入）
          open: false,
          // 弹出层标题（监察人标准导入）
          title: "",
          // 是否禁用上传
          isUploading: false,
          // 是否更新已经存在的检测仪器数据
          updateSupport: 0,
          // 设置上传的请求头部
          headers: { Authorization: "Bearer " + getToken() },
          // 上传的地址
          url: process.env.VUE_APP_BASE_API + "/limit/instrument/importData"
        },
        /*// 检测标准导入参数
        instrumentMethodUpload: {
          // 是否显示弹出层（检测仪器-方法导入）
          open: false,
          // 弹出层标题（监察人标准导入）
          title: "",
          // 是否禁用上传
          isUploading: false,
          // 是否更新已经存在的检测仪器方法数据
          updateSupport: 0,
          // 设置上传的请求头部
          headers: { Authorization: "Bearer " + getToken() },
          // 上传的地址
          url: process.env.VUE_APP_BASE_API + "/limit/instrument/importInstrumentMethodData"
        },*/
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
        // 表单校验
        rules: {
          purchaserID: [
            { required: true, message: "采购人名称不能为空", trigger: "blur" }
          ],
          instrumentName: [
            { required: true, message: "检测仪器名称不能为空", trigger: "blur" }
          ],
          /*launchDate: [
            { required: true, message: "启用日期不能为空", trigger: "blur" }
          ],
          purchaseDate: [
            { required: true, message: "采购日期不能为空", trigger: "blur" }
          ],*/
          status: [
            { required: true, message: "目前状态:0-未使用，1-正常，3-损坏", trigger: "blur" }
          ],
          isValid: [
            { required: true, message: "数据有效性标识 0-无效 1-有效", trigger: "blur" }
          ]
        }
      };
    },
    created() {
      this.getList();
      this.getUserList();
    },
    methods: {
      /** 查询检测仪器管理列表 */
      getList() {
        this.loading = true;
        listInstrument(this.queryParams).then(response => {
          this.instrumentList = response.rows;
          this.total = response.total;
          this.loading = false;
        });
      },
      // 获取用户选项
      getUserList() {
        listUser().then(response =>{
          this.purchaserNameOptions = response.rows;
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
          instrumentID: null,
          purchaserID: null,
          instrumentName: null,
          manufacturer: null,
          launchDate: null,
          purchaseDate: null,
          status: null,
          isValid:null,
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
        this.ids = selection.map(item => item.instrumentID);
        this.instrumentNames = selection.map(item => item.instrumentName);
        this.single = selection.length!==1;
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.form.isValid = 1;
        this.title = "添加检测仪器管理";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const instrumentID = row.instrumentID || this.ids
        getInstrument(instrumentID).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改检测仪器管理";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(isValid => {
          if (isValid) {
            if (this.form.instrumentID != null) {
              updateInstrument(this.form).then(response => {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addInstrument(this.form).then(response => {
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
        const instrumentIDs = row.instrumentID || this.ids;
        const instrumentNames = row.instrumentName || this.instrumentNames;
        this.$confirm('是否确认删除检测仪器名称为"' + instrumentNames + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delInstrument(instrumentIDs);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(() => {});
      },

      /** 导入按钮点击功能*/
      handleImport(){
        this.upload.title = "检测仪器信息导入";
        this.upload.open = true;
        console.log("----------")
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
      },
      // 提交上传文件
      submitFileForm() {
        this.$refs.upload.submit();
      },
      /** 导出按钮操作 */
      handleExport() {
        const queryParams = this.queryParams;
        this.$confirm('是否确认导出所有检测仪器关系数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.exportLoading = true;
          return exportInstrument(queryParams);
        }).then(response => {
          this.download(response.msg);
          this.exportLoading = false;
        }).catch(() => {});
      },
      changeValid(row){
        console.log(row);
        let flag = row.isValid; //保存点击之后v-modeld的值(true，false)
        row.isValid = row.isValid === 1 ? 0:1; //保持switch点击前的状态
        // let status = row.valid === true ? '启用' : '禁用';
        this.$confirm('是否改变'+row.instrumentName+'的状态？', "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() =>{
          flag ? row.isValid = 1 : row.isValid = 0; // 这一步很重要，row.showState会根据flag的值开启或关闭开关
          updateInstrument(row).then(response => {
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
