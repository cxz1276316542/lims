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
      <el-row type="flex" justify="space-around">
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
      <el-row >
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
          <el-form-item label="检测方法名称" prop="detectionMethodName">
          <el-select v-model="queryParams.detectionMethodName" filterable size="medium" clearable placeholder="请输入检测方法名称" >
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
      </el-row>
      <el-row >
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
        <el-col :span="8">
          <el-form-item label="数据有效性" prop="isValid">
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
          v-hasPermi="['limit:instrumentMethod:add']"
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
          v-hasPermi="['limit:instrumentMethod:edit']"
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
          v-hasPermi="['limit:instrumentMethod:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="info"
          plain
          icon="el-icon-upload2"
          size="mini"
          @click="handleImport"
          v-hasPermi="['limit:instrumentMethod:import']"
        >导入检测仪器-检测方法</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          :loading="exportLoading"
          @click="handleExport"
          v-hasPermi="['limit:instrumentMethod:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="instrumentMethodList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" type="index" align="center">
        <template slot-scope="scope">
          <span>{{(queryParams.pageNum - 1) * queryParams.pageSize + scope.$index + 1}}</span>
        </template>
      </el-table-column>
      <!--<el-table-column label="采购员ID" align="center" prop="purchaserID" />-->
      <el-table-column label="采购员名字" align="center" prop="purchaserName" />
      <el-table-column label="检测仪器编号" align="center" sortable prop="instrumentNumber" />
      <el-table-column label="检测仪器名称" align="center" sortable prop="instrumentName" />
      <el-table-column label="检测方法名称" align="center" sortable prop="detectionMethodName" />
      <el-table-column label="生产厂家" align="center" prop="manufacturer" />
      <el-table-column label="启用日期" align="center" sortable prop="launchDate" width="120">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.launchDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="采购日期" align="center" sortable prop="purchaseDate" width="120">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.purchaseDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="目前状态" align="center" sortable prop="status" >
        <template slot-scope="scope">
          <span v-if="scope.row.status === 0">未使用</span>
          <span v-if="scope.row.status === 1">正常</span>
          <span v-if="scope.row.status === 2">损坏</span>
        </template>
      </el-table-column>

      <el-table-column label="操作描述" align="center" prop="detectionMethodInfo" />
      <el-table-column label="检测方法地址" align="center" prop="detectionMethodURL" />
      <el-table-column label="是否有效" width="200" align="center" prop="isValid">
        <template slot-scope="scope">
          <el-radio-group v-model="scope.row.isValid" size="mini" @change="changeValid(scope.row)">
            <el-radio-button :label="0">无效</el-radio-button>
            <el-radio-button :label="1">有效</el-radio-button>
            <el-radio-button :label="2">默认</el-radio-button>
          </el-radio-group>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="100" class-name="small-padding fixed-width" >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['limit:instrumentMethod:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['limit:instrumentMethod:remove']"
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

    <!-- 添加检测方法-检测仪器关系对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="auto">
        <el-form-item label="检测方法名称" prop="detectionMethodID">
          <el-select v-model="form.detectionMethodID" filterable size="medium" clearable placeholder="请输入检测方法名称" v-bind:disabled="isAdd">
            <el-option v-for="item in detectionMethodNameOptions"
                       :key="item.detectionMethodID"
                       :value="item.detectionMethodID"
                       :label="item.detectionMethodName"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="检测仪器编号" prop="instrumentID">
          <el-select v-model="form.instrumentID" filterable size="medium" clearable placeholder="请输入检测仪器编号" @change="selectInstrumentNumber" v-bind:disabled="isAdd">
            <el-option v-for="item in instrumentNumberOptions"
                       :key="item.instrumentID"
                       :value="item.instrumentID"
                       :label="item.instrumentNumber"
            />
          </el-select>
        </el-form-item>
          <el-form-item label="检测仪器名称"  prop="instrumentName" >
            <el-col :span="15.5">
              <el-input
                v-model="form.instrumentName"
                placeholder="请输入检测仪器名称"
                clearable
                size="medium"
                :disabled="true"
              />
            </el-col>
          </el-form-item>
        <el-form-item label="数据有效性标识" prop="isValid">
          <el-select placeholder="请选择是否有效" clearable v-model="form.isValid" >
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

    <!-- 检测仪器-检测方法导入对话框 -->
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
  import { listInstrumentMethod, delInstrumentMethod, addInstrumentMethod, exportInstrumentMethod, listDetectionMethodName,importTemplate,getInstrumentMethod } from "@/api/limit/instrumentMethod";
  import { isExist, updateInstrumentMethod} from "../../../api/limit/instrumentMethod";
  import {listDetectionMethod} from "../../../api/limit/detectionMethod";
  import {listInstrument} from "../../../api/limit/instrument";
  import {getToken} from "../../../utils/auth";
  import {listUser} from "../../../api/system/user";

  export default {
    name: "InstrumentMethod",
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
        // 删除仪器方法名字弹框提示
        instrumentMethodNames: null,
        // 检测方法-检测仪器关系表格数据
        instrumentMethodList: [],
        // 采购员名字下拉选项
        purchaserNameOptions: [],
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
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          purchaserID: null,
          instrumentName: null,
          manufacturer: null,
          launchDate: null,
          purchaseDate: null,
          status: null,
          detectionMethodName: null,
          detectionMethodInfo: null,
          detectionMethodURL: null,
          detectionCenterID: null,
          isValid: null
        },
        // 检测方法下拉选项
        detectionMethodNameOptions: [],
        // 检测仪器下拉选项
        instrumentNameOptions: [],
        // 检测仪器编号下拉选项
        instrumentNumberOptions: [],
        // 表单参数
        form: {
          detectionMethodName:null
        },
        // 新增检测仪器-方法是否存在
        isExist: null,
        //是否是新增页面
        isAdd:false,
        test:'',
        // 检测仪器-检测方法导入参数
        upload: {
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
          url: process.env.VUE_APP_BASE_API + "/limit/instrumentMethod/importData"
        },
        // 表单校验
        rules: {
          instrumentID: [
            { required: true, message: "检测仪器编号不能为空", trigger: "blur" }
          ],
          detectionMethodID: [
            { required: true, message: "检测方法不能为空", trigger: "blur"}
          ],
          isValid: [
            { required: true, message: "数据有效性标识不能为空", trigger: "blur" }
          ]
        }
      };
    },
    watch: {
    },
    created() {
      this.getList();
      this.getDetectionMethodNameList();
      this.getinstrumentNameList();
      this.getinstrumentNumberList();
      this.getUserList();
    },
    methods: {
      /** 查询检测方法-检测仪器关系列表 */
      getList() {
        this.loading = true;
        listInstrumentMethod(this.queryParams).then(response => {
          console.log("==========")
          console.log(response.rows);
          this.instrumentMethodList = response.rows;
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
      getDetectionMethodNameList(){
        listDetectionMethod().then(response =>{
          this.detectionMethodNameOptions = response.rows;
        })
      },
      getinstrumentNameList(){
        listInstrument().then(response =>{
          this.instrumentNameOptions = response.rows;
        })

      },

      getinstrumentNumberList(){
        listInstrument().then(response =>{
          this.instrumentNumberOptions = response.rows;
        })
        /*listDetectionMethodName().then(response =>{
          this.detectionMethodNameOptions = response.data;
        });*/
      },
      selectInstrumentNumber(){
        let result = this.instrumentNumberOptions.find(a=>{
          return a.instrumentID === this.form.instrumentID;
        });
        this.form.instrumentName = result.instrumentName;
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
          instrumentID: null,
          instrumentName: null,
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
        //console.log(selection);
        this.ids = selection.map(item => item.instrumentDetectionMethodID);
        this.instrumentMethodNames = selection.map(item => (item.instrumentName+"-"+item.detectionMethodName));
        console.log(this.instrumentMethodNames)
        this.single = selection.length!==1;
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.form.isValid = 1;
        this.isAdd = false;
        this.title = "添加检测方法-检测仪器关系";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const instrumentDetectionMethodID = row.instrumentDetectionMethodID || this.ids;
        getInstrumentMethod(instrumentDetectionMethodID).then(response => {
          this.form = response.data;

          this.isAdd = true;
          this.open = true;
          this.title = "修改添加检测方法-检测仪器";
        });
      },
      /** 判定检测仪器-检测方法是否已存在*/
      isInstrumentMethodExist() {
        const form = this.form;
        isExist(form).then(response => {
          this.isExist = response.data;
        })
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if(this.form.instrumentDetectionMethodID != null) {
              updateInstrumentMethod(this.form).then(response => {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              })
            }else{
              isExist(this.form).then(response => {
                const isExist = response.data;
                console.log(isExist);
                if(isExist === 0){
                  addInstrumentMethod(this.form).then(response => {
                    this.msgSuccess("新增成功");
                    this.open = false;
                    this.getList();
                  });
                }else {
                  this.msgSuccess("数据已存在");
                  this.open = false;
                }
              })
            }

            // if(this.isExist === 0){
            //   addInstrumentMethod(this.form).then(response => {
            //     this.msgSuccess("新增成功");
            //     this.open = false;
            //     this.getList();
            //   });
            // }else{
            //   this.msgSuccess("数据已存在");
            //   this.open = false;
            // }

          }
        });
      },
      /** 批量删除按钮操作 */
      handleDelete(row) {
        const instrumentDetectionMethodIDs = row.instrumentDetectionMethodID || this.ids ;
        const instrumentMethodNames = row.instrumentName!=null? (row.instrumentName+ "-" + row.detectionMethodName): this.instrumentMethodNames;
        this.$confirm('是否确认删除检测方法-检测仪器名称为"' + instrumentMethodNames + '" 的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delInstrumentMethod(instrumentDetectionMethodIDs);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(() => {});
      },
      /** 导入按钮点击功能*/
      handleImport(){
        this.upload.title = "检测仪器-检测方法信息导入";
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
        //this.getList();
      },
      // 提交上传文件
      submitFileForm() {
        this.$refs.upload.submit();
      },
      /** 导出按钮操作 */
      handleExport() {
        const queryParams = this.queryParams;
        this.$confirm('是否确认导出所有检测方法-检测仪器关系数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.exportLoading = true;
          return exportInstrumentMethod(queryParams);
        }).then(response => {
          this.download(response.msg);
          this.exportLoading = false;
        }).catch(() => {});
      },
      changeValid(row){
        updateInstrumentMethod(row).then(response => {
          this.msgSuccess("修改成功");
          this.getList();
        });
      }
    }
  };
</script>
