<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="150px">

      <el-form-item label="报告模板名称" prop="reportTemName">
        <el-input
          v-model="queryParams.reportTemName"
          placeholder="请输入报告模板名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="检测中心名称" prop="laboratoryAddress">
        <el-input
          v-model="queryParams.laboratoryAddress"
          placeholder="请输入检测中心名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="操作时间" prop="operatorTime">
        <el-date-picker clearable size="small"
          v-model="queryParams.operatorTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择操作时间">
        </el-date-picker>
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
          v-hasPermi="['detection:reportTemplate:add']"
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
          v-hasPermi="['detection:reportTemplate:edit']"
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
          v-hasPermi="['detection:reportTemplate:remove']"
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
          v-hasPermi="['detection:reportTemplate:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="reportTemplateList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="报告模板名称" align="center" prop="reportTemName" />
      <el-table-column label="检测中心名称" align="center" prop="laboratoryName" />
      <el-table-column label="检测中心地址" align="center" prop="laboratoryAddress" />s
      <el-table-column label="操作员名称" align="center" prop="operatorName" />
      <el-table-column label="操作时间" align="center" prop="operatorTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.operatorTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['detection:reportTemplate:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['detection:reportTemplate:remove']"
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

    <!-- 添加或修改报告模板对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="110px" style="width: 400px">
        <el-form-item label="报告模板名称" prop="reportTemName">
          <el-input v-model="form.reportTemName" placeholder="请输入报告模板名称" />
        </el-form-item>
<!--        <el-form-item label="报告模板地址" prop="reportTemURL">-->
<!--          <el-input v-model="form.reportTemURL" placeholder="请输入报告模板地址" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="检测中心ID" prop="LaboratoryID">-->
<!--          <el-input v-model="form.LaboratoryID" placeholder="请输入检测中心ID" />-->
<!--        </el-form-item>-->

<!--        <el-form-item label="操作员ID" prop="operatorID">-->
<!--          <el-input v-model="form.operatorID" placeholder="请输入操作员ID" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="操作时间" prop="operatorTime">-->
<!--          <el-date-picker clearable size="small"-->
<!--            v-model="form.operatorTime"-->
<!--            type="date"-->
<!--            value-format="yyyy-MM-dd"-->
<!--            placeholder="选择操作时间">-->
<!--          </el-date-picker>-->
<!--        </el-form-item>-->
        <el-form-item label="检测中心名称" prop="laboratoryID"  >
          <el-select filterable clearable  v-model="form.laboratoryID" placeholder="请选择" style="width: 290px">
            <el-option
              v-for="item in laboratoryList"
              :key="item.laboratoryID"
              :label="item.laboratoryName"
              :value="item.laboratoryID">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>

<!--        <el-form-item label="上传模板" prop="reportTemplate" >-->
      <el-upload
        ref="upload"
        :message="upload.message"
        :limit="1"
        accept=" .ftl"
        :headers="upload.headers"
        :action="upload.url + '?updateSupport=' + upload.updateSupport+'&reportTemName='+upload.reportTemName"
        :disabled="upload.isUploading"
        :on-progress="handleFileUploadProgress"
        :on-success="handleFileSuccess"
        :auto-upload="false"
        :on-change="handleFileChange"
        drag
        style="margin-left: 50px"

      >
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">
          将文件拖到此处，或
          <em>点击上传</em>
        </div>
        <div class="el-upload__tip" style="color:red" slot="tip">提示：仅允许导入“ftl”格式文件！</div>
      </el-upload>
<!--        </el-form-item>-->
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>


    </el-dialog>


    <!-- 检测方法导入对话框 -->
<!--    <el-dialog :title="upload.title" :visible.sync="upload.open" width="400px" append-to-body>-->
<!--      <el-upload-->
<!--        ref="upload"-->
<!--        :limit="1"-->
<!--        accept=".xlsx, .xls"-->
<!--        :headers="upload.headers"-->
<!--        :action="upload.url + '?updateSupport=' + upload.updateSupport"-->
<!--        :disabled="upload.isUploading"-->
<!--        :on-progress="handleFileUploadProgress"-->
<!--        :on-success="handleFileSuccess"-->
<!--        :auto-upload="false"-->
<!--        drag-->
<!--      >-->
<!--        <i class="el-icon-upload"></i>-->
<!--        <div class="el-upload__text">-->
<!--          将文件拖到此处，或-->
<!--          <em>点击上传</em>-->
<!--        </div>-->
<!--        <div class="el-upload__tip" slot="tip">-->
<!--          <el-checkbox v-model="upload.updateSupport" />是否更新已经存在的用户数据-->
<!--          <el-link type="info" style="font-size:12px" @click="importTemplate">下载模板</el-link>-->
<!--        </div>-->
<!--        <div class="el-upload__tip" style="color:red" slot="tip">提示：仅允许导入“xls”或“xlsx”格式文件！</div>-->
<!--      </el-upload>-->
<!--      <div slot="footer" class="dialog-footer">-->
<!--        <el-button type="primary" @click="submitFileForm">确 定</el-button>-->
<!--        <el-button @click="upload.open = false">取 消</el-button>-->
<!--      </div>-->
<!--    </el-dialog>-->
  </div>

</template>

<script>
import { listReportTemplate, getReportTemplate, delReportTemplate, addReportTemplate, updateReportTemplate, exportReportTemplate ,laboratoryies,uploadSampleImage} from "@/api/detection/reportTemplate";
import { getToken } from "@/utils/auth";
export default {
  name: "ReportTemplate",
  data() {
    return {

      upload: {
        reportTemName:'',
        message:'',
        // 是否显示弹出层（检测标准导入）
        open: false,
        // 弹出层标题（监察人标准导入）
        title: "",
        // 是否禁用上传
        isUploading: false,
        // 是否更新已经存在的检测标准数据
        updateSupport: '',
        // 设置上传的请求头部
        headers: { Authorization: "Bearer " + getToken() },
        // 上传的地址
        url: process.env.VUE_APP_BASE_API + "/detection/reportTemplate/importReportTem"
      },
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
      // 报告模板表格数据
      reportTemplateList: [],



      laboratoryList:[],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        reportTemID: null,
        reportTemName: null,
        reportTemURL: null,
        laboratoryID: null,
        operatorID: null,
        operatorTime: null,
        remark: null,
        laboratoryAddress: null

      },
      // 表单参数
      form: {
        laboratoryID: '',
        laboratoryName: ''
      },
      // 表单校验
      rules: {
        reportTemName: [
          { required: true, message: "报告模板名称不能为空", trigger: "blur" }
        ],
        reportTemURL: [
          { required: true, message: "报告模板地址不能为空", trigger: "blur" }
        ],
        laboratoryID: [
          { required: true, message: "检测中心ID不能为空", trigger: "blur" }
        ],
        reportTemplate: [
          { required: true, message: "报告模板不能为空", trigger: "blur" }
        ],

      }
    };
  },
  created() {
    this.getList();
  },
  methods: {

    clearFiles () {
      this.$refs['upload'].clearFiles();
    },
    // handleFileChange(file, fileList){
    //   debugger
    //   this.isupload=true;
    // },


    // 文件上传中处理
    handleFileUploadProgress(event, file, fileList) {
      console.log('文件上传处理')
      this.upload.isUploading = true;
    },
    // 文件上传成功处理
    handleFileSuccess(response, file, fileList) {
      console.log('文件上传成功处理')

      this.upload.open = false;
      this.upload.isUploading = false;
      this.$refs.upload.clearFiles();
      this.$alert(response.msg, "导入结果", { dangerouslyUseHTMLString: true });
      this.getList();

    },
    // 提交上传文件
    submitFileForm(res) {
      console.log('提交上传文件')
      console.log(res)
      console.log(this.$refs.upload)
      this.upload.message=res
      this.$refs.upload.submit(res);
      this.getList();
    },
    handleImport(){
      console.log('报告模板导入')
      this.upload.title = "报告模板导入";
      this.upload.open = true;
    },
    // 查询所有检测中心
    findAllCustomers(){
      // debugger
      laboratoryies().then(res=>{
        this.laboratoryList = res.data
      })
    },
    /** 查询报告模板列表 */
    getList() {
      // this.$refs.upload.clearFiles();
      this.loading = true;
      this.isupload=false;
      listReportTemplate(this.queryParams).then(response => {
        this.reportTemplateList = response.rows;
        console.log(this.reportTemplateList)
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
        reportTemID: null,
        reportTemName: null,
        reportTemURL: null,
        laboratoryID: null,
        operatorID: null,
        operatorTime: null,
        remark: null
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
      this.ids = selection.map(item => item.reportTemID)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加报告模板";
      this.findAllCustomers();
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.findAllCustomers();
      const reportTemID = row.reportTemID || this.ids
      getReportTemplate(reportTemID).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改报告模板";
      });
    },



    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {


          if (valid) {
            const form = this.form;
            const upload = this.upload;
            upload.reportTemName = form.reportTemName;
            upload.updateSupport = form.laboratoryID;
            if (this.form.reportTemID != null) {

              this.$confirm('是否修改报告模板', "警告", {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "warning"
              }).then(
                function () {
                  return updateReportTemplate(form);
                }).then(() => {
                this.submitFileForm();
                this.getList();
                this.open = false;
                this.msgSuccess("修改成功");
                this.clearFiles();
              }).catch(() => {
              });
            } else {
              this.$confirm('是否新增报告模板', "警告", {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "warning"
              }).then(
                function () {
                  addReportTemplate(form);
                }).then(() => {
                this.submitFileForm();
                this.getList();
                this.open = false;
                this.msgSuccess("新增成功");
              }).catch(() => {
              });
            }

        }else{
          this.msgError("请导入报告模板！");
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const reportTemIDs = row.reportTemID || this.ids;
      this.$confirm('是否确认删除报告模板编号为"' + reportTemIDs + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delReportTemplate(reportTemIDs);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有报告模板数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.exportLoading = true;
          return exportReportTemplate(queryParams);
        }).then(response => {
          this.download(response.msg);
          this.exportLoading = false;
        }).catch(() => {});
    }
  }
};
</script>
