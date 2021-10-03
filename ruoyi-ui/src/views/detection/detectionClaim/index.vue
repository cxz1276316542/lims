<template>
  <div class="app-container">
<!--    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">-->
<!--      <el-form-item label="检品编号" prop="sampleNumber">-->
<!--        <el-input-->
<!--          v-model="queryParams.sampleNumber"-->
<!--          placeholder="请输入检品编号"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->

<!--      <el-form-item label="检测农药ID" prop="pesticideID">-->
<!--        <el-input-->
<!--          v-model="queryParams.pesticideID"-->
<!--          placeholder="请输入检测农药ID"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="检测方法ID" prop="detectionMethod">-->
<!--        <el-input-->
<!--          v-model="queryParams.detectionMethod"-->
<!--          placeholder="请输入检测方法ID"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="检测人ID" prop="inspectorID">-->
<!--        <el-input-->
<!--          v-model="queryParams.inspectorID"-->
<!--          placeholder="请输入检测人ID"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="标识 是否为初检" prop="flag">-->
<!--        <el-input-->
<!--          v-model="queryParams.flag"-->
<!--          placeholder="请输入标识 是否为初检"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="审核状态(0-提交审核，审核中，1-审核通过，2-审核不通过，3-保存，4-认领失败，5-认领" prop="reviewStatus">-->
<!--        <el-input-->
<!--          v-model="queryParams.reviewStatus"-->
<!--          placeholder="请输入审核状态(0-提交审核，审核中，1-审核通过，2-审核不通过，3-保存，4-认领失败，5-认领)"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="操作员ID" prop="operatorID">-->
<!--        <el-input-->
<!--          v-model="queryParams.operatorID"-->
<!--          placeholder="请输入操作员ID"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="操作时间" prop="operationTime">-->
<!--        <el-date-picker clearable size="small"-->
<!--          v-model="queryParams.operationTime"-->
<!--          type="date"-->
<!--          value-format="yyyy-MM-dd"-->
<!--          placeholder="选择操作时间">-->
<!--        </el-date-picker>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="备注" prop="remark">-->
<!--        <el-input-->
<!--          v-model="queryParams.remark"-->
<!--          placeholder="请输入备注"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item>-->
<!--        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>-->
<!--        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>-->
<!--      </el-form-item>-->
<!--    </el-form>-->
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="140px">

      <el-form-item label="检品编号" prop="sampleNumber">
        <el-input
          v-model="queryParams.sampleNumber"
          placeholder="请输入检品编号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="任务分派编号" prop="taskAssignNumber">
        <el-input
          v-model="queryParams.taskAssignNumber"
          placeholder="请输入任务分派编号"
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
      <el-form-item label="检测方法" prop="inspectionMethodName">
        <el-input
          v-model="queryParams.inspectionMethodName"
          placeholder="请输入检测方法"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>


    </el-form>
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch"  label-width="140px" >
      <el-form-item label="检测标准" prop="standard">
        <el-input
          v-model="queryParams.standard"
          placeholder="请输入检测标准"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="检测项目名称" prop="pesticideName">
        <el-input
          v-model="queryParams.pesticideName"
          placeholder="请输入检测项目称"
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
      <el-form-item label="审核类别" prop="auditStatus">
        <el-select v-model="queryParams.auditStatus" placeholder="请选择审核类别" clearable size="medium"   @keyup.enter.native="handleQuery">
          <el-option
            v-for="item in option1"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item >
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
          @click="submitBatchClaim"
          v-hasPermi="['detection:detectionResult:edit']"
          :disabled="multiple"
        >批量认领</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          @click="submitBatchNoClaim"
          v-hasPermi="['detection:detectionResult:edit']"
          :disabled="multiple"
        >批量拒领</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
		  :loading="exportLoading"
          @click="handleExport"
          v-hasPermi="['detection:taskAssign:export']"
        >导出</el-button>
      </el-col>

      <div style="margin-left: 1200px">
        <el-col :span="1.5" >
          <el-button
            type="primary"
            plain
            icon="el-icon-edit"
            size="mini"
            round
            @click="toClaim"
            v-hasPermi="['detection:detectionResult:edit']"
          >项目认领</el-button>
        </el-col>

        <el-col :span="1.5">
          <el-button
            type="success"
            plain
            icon="el-icon-edit"
            size="mini"
            round
            @click="toDetection"
            v-hasPermi="['detection:detectionResult:edit']"
          >项目初检</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="info"
            plain
            icon="el-icon-edit"
            size="mini"
            round
            @click="toReDetection"
            v-hasPermi="['detection:detectionResult:edit']"
          >项目复检</el-button>
        </el-col>
      </div>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="taskAssignList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column sortable label="检品编号" align="center" prop="sampleNumber" />
      <el-table-column sortable label="任务单编号" align="center" prop="taskNumber" width="120px"/>
      <el-table-column label="样品学名" align="center" prop="sampleAcademicName" />
      <el-table-column label="检测项目名称" align="center" prop="pesticideName" />
      <el-table-column sortable label="优先级" align="center" prop="priority" >
        <template slot-scope="scope">
          <span v-if="scope.row.priority === 1">正常</span>
          <span v-if="scope.row.priority === 2">急</span>
          <span v-if="scope.row.priority === 3">加急</span>
          <span v-if="scope.row.priority === 4">特急</span>
        </template>
      </el-table-column>
      <el-table-column label="检测室" align="center" prop="inspectionRoom" />
      <el-table-column label="检测标准" align="center" prop="standard" />
      <el-table-column label="检测方法" align="center" prop="inspectionMethodName" />
      <el-table-column sortable label="最大含量标准" align="center" prop="maxContent" width="130px"/>
      <el-table-column label="成人每公斤摄入量(mg/kg)" align="center" prop="adi" />
      <el-table-column sortable label="审核状态" align="center" prop="auditStatus" >
        <template slot-scope="scope">
          <span v-if="scope.row.auditStatus === 1">认领成功</span>
          <span v-if="scope.row.auditStatus === 2">拒绝领取</span>
          <span v-if="scope.row.auditStatus === 3">待领取</span>
        </template>
      </el-table-column>
      <el-table-column label="操作时间" align="center" prop="operationTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.operationTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="submitClaim(scope.row)"
            v-hasPermi="['detection:detectionResult:edit']"
          >认领</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="submitNoClaim(scope.row)"

            v-hasPermi="['detection:detectionResult:edit']"
          >拒领</el-button>
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

    <!-- 添加或修改检测结果对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">

        <el-form-item label="检品编号" prop="sampleNumber">
          <el-input v-model="form.sampleNumber" placeholder="请输入检品编号" :disabled="true" />
        </el-form-item>
        <el-form-item label="样品学名" prop="sampleAcademicName">
          <el-input v-model="form.sampleAcademicName" placeholder="请输入样品学名" :disabled="true"/>
        </el-form-item>
        <el-form-item label="检测方法" prop="inspectionMethodName">
          <el-input v-model="form.inspectionMethodName" placeholder="请输入检测方法" :disabled="true"/>
        </el-form-item>
        <el-form-item label="检测名称" prop="pesticideName">
          <el-input v-model="form.pesticideName" placeholder="请输入检测名称" :disabled="true"/>
        </el-form-item>
        <el-form-item label="拒领意见" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入拒绝领取意见" />
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
import { submitNoClaim,submitBatchNoClaim,submitBatchClaim,submitClaim,listTaskAssignVO, getTaskAssign, delTaskAssign, addTaskAssign, updateTaskAssign, exportTaskAssign } from "@/api/detection/taskAssign";

export default {
  name: "TaskAssign",
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
        value: 3,
        label: '待领取'
      },{
        value: 1,
        label: '认领成功'
      },{
        value: 2,
        label: '认领失败'
      }],
      //选中数据
      items:[],
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
      // 任务分派表格数据
      taskAssignList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        sampleNumber: null,
        taskAssignNumber: null,
        pesticideID: null,
        detectionMethod: null,
        inspectorID: null,
        flag: null,
        reviewStatus: null,
        operatorID: null,
        operationTime: null,
        remark: null,
        taskNumber: null,
        sampleAcademicName: null,
        pesticideName: null,
        priority: null,
        inspectionRoom: null,
        standard: null,
        inspectionMethodName: null,
        maxContent: null,
        adi: null,
        auditStatus: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        sampleNumber: [
          { required: true, message: "检品编号不能为空", trigger: "blur" }
        ],
        taskAssignNumber: [
          { required: true, message: "任务分派编号不能为空", trigger: "blur" }
        ],
        pesticideID: [
          { required: true, message: "检测农药ID不能为空", trigger: "blur" }
        ],
        detectionMethod: [
          { required: true, message: "检测方法ID不能为空", trigger: "blur" }
        ],
        inspectorID: [
          { required: true, message: "检测人ID不能为空", trigger: "blur" }
        ],
        flag: [
          { required: true, message: "标识 是否为初检不能为空", trigger: "blur" }
        ],
        reviewStatus: [
          { required: true, message: "审核状态(0-提交审核，审核中，1-审核通过，2-审核不通过，3-保存，4-认领失败，5-认领)不能为空", trigger: "blur" }
        ],
        operatorID: [
          { required: true, message: "操作员ID不能为空", trigger: "blur" }
        ],
        operationTime: [
          { required: true, message: "操作时间不能为空", trigger: "blur" }
        ],
        remark: [
          { required: true, message: "拒领理由不能为空！", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    toClaim() {
      this.$router.push({ path: "/detection/detectionClaim"});
    },
    toDetection(){
      this.$router.push({ path: "/detection/detectionResult"});
    },
    toReDetection(){
      this.$router.push({ path: "/detection/reDetectionResult"});
    },
    /** 批量认领 */
    submitBatchClaim() {
      console.log(items)
      let flag=0;
      let items=this.items;

      for (let i = 0; i <items.length ; i++) {
        if(this.items[i].auditStatus!==3) flag=1;
      }
      if(flag===0&&items){
        console.log(items)
        this.$confirm('是否认领所选检测项目?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(
          function() {
            debugger
            console.log(items)
            return submitBatchClaim(items);
          }).then(() => {
          this.getList();
          this.msgSuccess("提交成功");
        }).catch(() => {});
      }else{
        this.msgError("请勿重复提交审核");
        this.getList();
      }
    },

    /** 单行认领 */
    submitClaim(row){
      console.log(row)

      if(row.auditStatus===3){
        this.$confirm('是否认领该检测项目?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return submitClaim(row);
        }).then(() => {
          this.getList();
          this.msgSuccess("提交成功");
        }).catch(() => {});
      }else{
        this.msgError("请勿重复认领");
        this.getList();
      }
    },

    /** 批量拒领 */
    submitBatchNoClaim() {

      let flag=0;
      let items=this.items;
      for (let i = 0; i <items.length ; i++) {
        if(this.items[i].auditStatus!==3) flag=1;
      }
      if(flag===0&&items){
        console.log(items)
        this.$confirm('是否拒领所选检测项目?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(
          function() {
            return submitBatchNoClaim(items);
          }).then(() => {
          this.getList();
          this.msgSuccess("提交成功");
        }).catch(() => {});
      }else{
        this.msgError("请勿重复拒领");
        this.getList();
      }
    },

    /** 单行拒领 */
    submitNoClaim(row){
      if(row.auditStatus===3){
        console.log(row)
        this.$confirm('是否拒领该检测项目?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          debugger
          return submitNoClaim(row);
        }).then(() => {
          this.getList();
          this.msgSuccess("提交成功");
        }).catch(() => {});
      }else{
        this.msgError("请勿重复提交审核");
        this.getList();
      }
    },
    /** 查询任务分派列表 */
    getList() {
      this.loading = true;
      listTaskAssignVO(this.queryParams).then(response => {
        this.taskAssignList = response.rows;
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
        sampleID: null,
        taskAssignID: null,
        sampleNumber: null,
        taskAssignNumber: null,
        pesticideID: null,
        detectionMethod: null,
        inspectorID: null,
        flag: null,
        reviewStatus: null,
        operatorID: null,
        operationTime: null,
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
      this.items = selection
      this.ids = selection.map(item => item.sampleID)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加任务分派";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      if(row.auditStatus===3){
        this.reset();
        const taskAssignID = row.taskAssignID || this.ids
        getTaskAssign(taskAssignID).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "拒领项目详情";
        });
      }else{
        this.msgError("请勿重复拒领");
        this.getList();
      }

    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
            if(this.form.remark===null){
              this.msgError("拒领理由不能为空!");
            }else{
              updateTaskAssign(this.form).then(response => {
                this.msgSuccess("拒领成功");
                this.open = false;
                this.getList();
              });
            }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const sampleIDs = row.sampleID || this.ids;
      this.$confirm('是否确认删除任务分派编号为"' + sampleIDs + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delTaskAssign(sampleIDs);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有任务分派数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.exportLoading = true;
          return exportTaskAssign(queryParams);
        }).then(response => {
          this.download(response.msg);
          this.exportLoading = false;
        }).catch(() => {});
    }
  }
};
</script>
