<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">

      <el-form-item label="报告编号" prop="reportNumber">
        <el-input
          v-model="queryParams.reportNumber"
          placeholder="请输入报告编号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="检品编号" prop="sampleNumber">
        <el-input
          v-model="queryParams.sampleNumber"
          placeholder="请输入检品编号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="样品学名" prop="sampleAcademicName">
        <el-input
          v-model="queryParams.sampleAcademicName"
          placeholder="请输入样品学名"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="优先级" prop="priority"  >

        <el-select v-model="queryParams.priority" placeholder="请选择优先级" clearable size="medium"   @keyup.enter.native="handleQuery">
          <el-option
            v-for="item in option"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>

<!--      <el-form-item label="检测报告判定结果:1-判定合格，2-判定不合格，3-不做判定" prop="reportResult">-->
<!--        <el-input-->
<!--          v-model="queryParams.reportResult"-->
<!--          placeholder="请输入检测报告判定结果:1-判定合格，2-判定不合格，3-不做判定"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="判定结果" prop="reportResult" >
        <el-select v-model="queryParams.reportResult" placeholder="请选择判定结果" clearable size="medium"   @keyup.enter.native="handleQuery">
          <el-option
            v-for="item in option2"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="状态" prop="reportStatus" >
        <el-select v-model="queryParams.reportStatus" placeholder="请选择状态" clearable size="medium"   @keyup.enter.native="handleQuery">
          <el-option
            v-for="item in option1"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="打印邮寄时间" prop="printMailTime">
        <el-date-picker clearable size="small"
          v-model="queryParams.issueTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择签发时间">
        </el-date-picker>
      </el-form-item>



      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="primary"-->
<!--          plain-->
<!--          icon="el-icon-plus"-->
<!--          size="mini"-->
<!--          @click="handleAdd"-->
<!--          v-hasPermi="['detection:report:add']"-->
<!--        >批量签发</el-button>-->
<!--      </el-col>-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="success"-->
<!--          plain-->
<!--          icon="el-icon-edit"-->
<!--          size="mini"-->
<!--          :disabled="single"-->
<!--          @click="handleUpdate"-->
<!--          v-hasPermi="['detection:report:edit']"-->
<!--        >批量打回撰写</el-button>-->
<!--      </el-col>-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="danger"-->
<!--          plain-->
<!--          icon="el-icon-delete"-->
<!--          size="mini"-->
<!--          :disabled="multiple"-->
<!--          @click="handleDelete"-->
<!--          v-hasPermi="['detection:report:remove']"-->
<!--        >删除</el-button>-->
<!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
		  :loading="exportLoading"
          @click="handleExport"
          v-hasPermi="['detection:report:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="reportList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="检测报告ID" align="center" prop="reportID" />-->
      <el-table-column sortable label="报告编号" align="center" prop="reportNumber" />
      <el-table-column sortable label="检品编号" align="center" prop="sampleNumber" />
      <el-table-column label="样品学名" align="center" prop="sampleAcademicName" />
      <el-table-column sortable label="优先级" align="center" prop="priority" >
        <template slot-scope="scope">
          <span v-if="scope.row.priority === 1">正常</span>
          <span v-if="scope.row.priority === 2">急</span>
          <span v-if="scope.row.priority === 3">加急</span>
          <span v-if="scope.row.priority === 4">特急</span>
        </template>
      </el-table-column>
      <el-table-column label="检测标准" align="center" prop="standard" />
      <el-table-column label="检测报告判定结果" align="center" prop="reportResult" />


      <el-table-column sortable label="检测报告判定结果" align="center" prop="reportResult" width="150px" >
        <template slot-scope="scope">

          <span v-if="scope.row.reportResult === 1">判定合格</span>
          <span v-if="scope.row.reportResult === 2">判定不合格</span>
          <span v-if="scope.row.reportResult === 3">不做判定</span>
        </template>
      </el-table-column>
      <el-table-column sortable label="状态" align="center" prop="reportStatus" >
        <template slot-scope="scope">
          <span v-if="scope.row.reportStatus === 0">默认</span>
          <span v-if="scope.row.reportStatus === 1">拟稿</span>
          <span v-if="scope.row.reportStatus === 2">审核</span>
          <span v-if="scope.row.reportStatus === 3">签发</span>
          <span v-if="scope.row.reportStatus === 4">打印、邮寄</span>
        </template>
      </el-table-column>

<!--      <el-table-column label="拟稿人" align="center" prop="editName" />-->
<!--      <el-table-column label="拟稿时间" align="center" prop="editTime" width="180">-->
<!--        <template slot-scope="scope">-->
<!--          <span>{{ parseTime(scope.row.editTime, '{y}-{m}-{d}') }}</span>-->
<!--        </template>-->
<!--      </el-table-column>-->

<!--      <el-table-column label="签发人" align="center" prop="issueName" />-->
<!--      <el-table-column sortable label="签发时间" align="center" prop="issueTime" width="180">-->
<!--        <template slot-scope="scope">-->
<!--          <span>{{ parseTime(scope.row.issueTime, '{y}-{m}-{d}') }}</span>-->
<!--        </template>-->
<!--      </el-table-column>-->
      <el-table-column label="打印邮寄人" align="center" prop="printMailName" />
      <el-table-column label="打印邮寄时间" align="center" prop="printMailTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.printMailTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
<!--      <el-table-column label="审核人" align="center" prop="reviewerName" />-->
<!--      <el-table-column label="审核时间" align="center" prop="reviewerTime" width="180">-->
<!--        <template slot-scope="scope">-->
<!--          <span>{{ parseTime(scope.row.reviewerTime, '{y}-{m}-{d}') }}</span>-->
<!--        </template>-->
<!--      </el-table-column>-->
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleDownload(scope.row)"
            v-hasPermi="['detection:report:edit']"
          >下载报告</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['detection:report:remove']"
          >打印邮寄</el-button>
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

    <!-- 添加或修改检测报告打印邮寄对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="报告编号" prop="reportNumber">
          <el-input v-model="form.reportNumber" placeholder="请输入报告编号" />
        </el-form-item>
        <el-form-item label="委托样品ID" prop="sampleID">
          <el-input v-model="form.sampleID" placeholder="请输入委托样品ID" />
        </el-form-item>
        <el-form-item label="检品编号" prop="sampleNumber">
          <el-input v-model="form.sampleNumber" placeholder="请输入检品编号" />
        </el-form-item>
        <el-form-item label="检测报告判定结果:1-判定合格，2-判定不合格，3-不做判定" prop="reportResult">
          <el-input v-model="form.reportResult" placeholder="请输入检测报告判定结果:1-判定合格，2-判定不合格，3-不做判定" />
        </el-form-item>
        <el-form-item label="检测报告地址" prop="reportURL">
          <el-input v-model="form.reportURL" placeholder="请输入检测报告地址" />
        </el-form-item>
        <el-form-item label="状态 0-默认1-拟稿2-审核3-签发4-打印、邮寄" prop="reportStatus">
          <el-input v-model="form.reportStatus" placeholder="请输入状态 0-默认1-拟稿2-审核3-签发4-打印、邮寄" />
        </el-form-item>
        <el-form-item label="审核人签字" prop="reviewerSign">
          <el-input v-model="form.reviewerSign" placeholder="请输入审核人签字" />
        </el-form-item>
        <el-form-item label="领导签字" prop="leaderSign">
          <el-input v-model="form.leaderSign" placeholder="请输入领导签字" />
        </el-form-item>
        <el-form-item label="拟稿人ID" prop="editID">
          <el-input v-model="form.editID" placeholder="请输入拟稿人ID" />
        </el-form-item>
        <el-form-item label="拟稿时间" prop="editTime">
          <el-date-picker clearable size="small"
            v-model="form.editTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择拟稿时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="签发人ID" prop="issueID">
          <el-input v-model="form.issueID" placeholder="请输入签发人ID" />
        </el-form-item>
        <el-form-item label="签发时间" prop="issueTime">
          <el-date-picker clearable size="small"
            v-model="form.issueTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择签发时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="打印邮寄人ID" prop="printMailID">
          <el-input v-model="form.printMailID" placeholder="请输入打印邮寄人ID" />
        </el-form-item>
        <el-form-item label="打印邮寄时间" prop="printMailTime">
          <el-date-picker clearable size="small"
            v-model="form.printMailTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择打印邮寄时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="审核人ID" prop="reviewerID">
          <el-input v-model="form.reviewerID" placeholder="请输入审核人ID" />
        </el-form-item>
        <el-form-item label="审核时间" prop="reviewerTime">
          <el-date-picker clearable size="small"
            v-model="form.reviewerTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择审核时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
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
import { listReport, getReport, delReport, addReport, updateReport, exportReport } from "@/api/detection/report";

export default {
  name: "Report",
  data() {
    return {
      //优先级下拉选择
      option: [{
        value: 1,
        label: '正常'
      }, {
        value: 2,
        label: '急'
      },{
        value: 3,
        label: '加急'
      }, {
        value: 4,
        label: '特急'
      }],
      //审核类别下拉选择

      option1: [ {
        value: 0,
        label: '默认'
      },{
        value: 1,
        label: '拟稿'
      },{
        value: 2,
        label: '审核'
      },{
        value: 3,
        label: '签发'
      },{
        value: 4,
        label: '打印、邮寄'
      }],
      option2: [{
        value: 1,
        label: '判定合格'
      },{
        value: 2,
        label: '判定不合格'
      },{
        value: 3,
        label: '不做判定'
      }],
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
      // 检测报告打印邮寄表格数据
      reportList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        reportID: null,
        reportNumber: null,
        sampleID: null,
        sampleNumber: null,
        reportResult: null,
        reportURL: null,
        reportStatus: null,
        reviewerSign: null,
        leaderSign: null,
        editID: null,
        editTime: null,
        issueID: null,
        issueTime: null,
        printMailID: null,
        printMailTime: null,
        reviewerID: null,
        reviewerTime: null,
        remark: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        reportNumber: [
          { required: true, message: "报告编号不能为空", trigger: "blur" }
        ],
        sampleID: [
          { required: true, message: "委托样品ID不能为空", trigger: "blur" }
        ],
        sampleNumber: [
          { required: true, message: "检品编号不能为空", trigger: "blur" }
        ],
        reportResult: [
          { required: true, message: "检测报告判定结果:1-判定合格，2-判定不合格，3-不做判定不能为空", trigger: "blur" }
        ],
        reportURL: [
          { required: true, message: "检测报告地址不能为空", trigger: "blur" }
        ],
        reportStatus: [
          { required: true, message: "状态 0-默认1-拟稿2-审核3-签发4-打印、邮寄不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询检测报告打印邮寄列表 */
    getList() {
      this.loading = true;
      listReport(this.queryParams).then(response => {
        this.reportList = response.rows;
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
        reportID: null,
        reportNumber: null,
        sampleID: null,
        sampleNumber: null,
        reportResult: null,
        reportURL: null,
        reportStatus: null,
        reviewerSign: null,
        leaderSign: null,
        editID: null,
        editTime: null,
        issueID: null,
        issueTime: null,
        printMailID: null,
        printMailTime: null,
        reviewerID: null,
        reviewerTime: null,
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
      this.ids = selection.map(item => item.reportID)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加检测报告打印邮寄";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const reportID = row.reportID || this.ids
      getReport(reportID).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改检测报告打印邮寄";
      });
    },
    // 文件下载处理
    handleDownload(row) {
      // var name = row.fileName;
      // var url = row.filePath;
      var name="report";
      var url="http://222.204.6.13:8087/prod-api/profile/upload/report.doc";
      // var url="http://localhost:8080/profile/upload/report.doc";
      var suffix = url.substring(url.lastIndexOf("."), url.length);
      const a = document.createElement('a')
      a.setAttribute('download', name + suffix)
      a.setAttribute('target', '_blank')
      a.setAttribute('href', url)
      a.click()
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.reportID != null) {
            updateReport(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addReport(this.form).then(response => {
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
      const reportIDs = row.reportID || this.ids;
      this.$confirm('是否确认删除检测报告打印邮寄编号为"' + reportIDs + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delReport(reportIDs);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有检测报告打印邮寄数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.exportLoading = true;
          return exportReport(queryParams);
        }).then(response => {
          this.download(response.msg);
          this.exportLoading = false;
        }).catch(() => {});
    }
  }
};
</script>
