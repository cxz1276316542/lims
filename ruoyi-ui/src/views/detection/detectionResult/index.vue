<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="150px">

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
      <el-form-item label="检测项目名称" prop="pesticideName">
        <el-input
          v-model="queryParams.pesticideName"
          placeholder="请输入检测项目称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="检测标准" prop="standard">
        <el-input
          v-model="queryParams.standard"
          placeholder="请输入检测标准"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
    </el-form>
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch"  label-width="150px" >
      <el-form-item label="检测方法" prop="inspectionMethodName">
        <el-input
          v-model="queryParams.inspectionMethodName"
          placeholder="请输入检测方法"
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
      <el-form-item label="审核类别" prop="auditStatus" style="margin-left: -20px">
        <el-select v-model="queryParams.auditStatus" placeholder="请选择审核类别" clearable size="medium"   @keyup.enter.native="handleQuery">
          <el-option
            v-for="item in option1"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item style="margin-left: 100px">
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
<!--    <el-row>-->
<!--      <el-col :span="18"><div class="grid-content bg-purple">-->
<!--        <el-col :span="1.5">-->
<!--          <el-button-->
<!--            type="success"-->
<!--            plain-->
<!--            icon="el-icon-edit"-->
<!--            size="mini"-->

<!--            @click="submitBatchReview"-->
<!--            v-hasPermi="['detection:detectionResult:edit']"-->
<!--          >批量审核</el-button>-->
<!--        </el-col>-->
<!--        <el-col :span="1.5" style="margin-left: 20px">-->
<!--          <el-button-->
<!--            type="warning"-->
<!--            plain-->
<!--            icon="el-icon-download"-->
<!--            size="mini"-->
<!--            :loading="exportLoading"-->
<!--            @click="handleExport"-->
<!--            v-hasPermi="['detection:detectionResult:export']"-->
<!--          >导出</el-button>-->
<!--        </el-col>-->
<!--      </div></el-col>-->
<!--      <el-col :span="6"><div class="grid-content bg-purple-light">-->
<!--        <el-col :span="1.5" style="border: 1px solid red">-->
<!--          <el-button-->
<!--            type="primary"-->
<!--            plain-->
<!--            icon="el-icon-edit"-->
<!--            size="mini"-->
<!--            round-->
<!--            @click="toClaim"-->
<!--            v-hasPermi="['detection:detectionResult:edit']"-->
<!--          >项目认领</el-button>-->
<!--        </el-col>-->

<!--        <el-col :span="1.5">-->
<!--          <el-button-->
<!--            type="success"-->
<!--            plain-->
<!--            icon="el-icon-edit"-->
<!--            size="mini"-->
<!--            round-->
<!--            @click="toDetection"-->
<!--            v-hasPermi="['detection:detectionResult:edit']"-->
<!--          >项目初检</el-button>-->
<!--        </el-col>-->
<!--        <el-col :span="1.5">-->
<!--          <el-button-->
<!--            type="info"-->
<!--            plain-->
<!--            icon="el-icon-edit"-->
<!--            size="mini"-->
<!--            round-->
<!--            @click="toReDetection"-->
<!--            v-hasPermi="['detection:detectionResult:edit']"-->
<!--          >项目复检</el-button>-->
<!--        </el-col>-->
<!--      </div></el-col>-->
<!--    </el-row>-->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="multiple"
          @click="submitBatchReview"
          v-hasPermi="['detection:detectionResult:edit']"
        >批量审核</el-button>
      </el-col>
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="danger"-->
<!--          plain-->
<!--          icon="el-icon-delete"-->
<!--          size="mini"-->
<!--          :disabled="multiple"-->
<!--          @click="handleDelete"-->
<!--          v-hasPermi="['detection:detectionResult:remove']"-->
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
          v-hasPermi="['detection:detectionResult:export']"
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

    <el-table v-loading="loading" :data="detectionResultList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="项目检测数据记录ID" align="center" prop="detectionResultID" />-->
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
      <el-table-column sortable label="检测数据" align="center" prop="detectionData" style="font-size: larger" >
        <template slot-scope="scope">
          <div :style="{'color':scope.row.detectionData>scope.row.maxContent? 'red':'green'}">
            {{scope.row.detectionData}}
          </div>
        </template>
      </el-table-column>
      <el-table-column label="审核状态" align="center" prop="auditStatus" >
        <template slot-scope="scope">
          <span v-if="scope.row.auditStatus === 0">待检</span>
          <span v-if="scope.row.auditStatus === 3">初检</span>
        </template>
      </el-table-column>
      <el-table-column sortable label="操作时间" align="center" prop="operationTime" width="180">
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
            @click="handleUpdate(scope.row)"
            v-hasPermi="['detection:detectionResult:edit']"
          >输入检验数据</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="submitReview(scope.row)"
            v-hasPermi="['detection:detectionResult:edit']"
          >提交审核</el-button>
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-delete"-->
<!--            @click="handleDelete(scope.row)"-->
<!--            v-hasPermi="['detection:detectionResult:remove']"-->
<!--          >删除</el-button>-->
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
          <el-input v-model="form.inspectionMethodName" placeholder="请输入检测名称" :disabled="true"/>
        </el-form-item>
        <el-form-item label="检测名称" prop="pesticideName">
          <el-input v-model="form.pesticideName" placeholder="请输入检测名称" :disabled="true"/>
        </el-form-item>

        <el-form-item label="最大含量标准" prop="maxContent">
          <el-input v-model="form.maxContent" placeholder="请输入最大含量标准" :disabled="true"/>
        </el-form-item>


        <el-form-item label="检测数据" prop="detectionData">

          <el-input v-model="form.detectionData" placeholder="请输入检测数据" style="width: 250px;margin-right: 10px "/>
          <el-input v-model="form.sampleUnit" placeholder="检测单位" style="width: 100px " :disabled="true"/>

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
import { listDetectionResult, getDetectionResult, delDetectionResult, addDetectionResult, updateDetectionResult, exportDetectionResult ,submitReview,submitBatchReview} from "@/api/detection/detectionResult";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
import {selectAllTem} from "@/api/detection/report";
export default {
  name: "DetectionResult",
  data() {
    return {

      radio1: '1',
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
      // 检测结果表格数据
      detectionResultList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
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
        label: '待检'
      },{
        value: 3,
        label: '初检'
      }],

      // 查询参数
      queryParams: {

        pageNum: 1,
        pageSize: 10,
        detectionResultID: null,
        sampleID: null,
        sampleNumber: null,
        pesticideID: null,
        inspectorID: null,
        detectionData: null,
        auditStatus: null,
        operationTime: null,
        remark: null,
        sampleAcademicName: null,
        pesticideName: null,
        standard: null,
        inspectionMethodName: null,
        priority:null

      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        // sampleNumber: [
        //   { required: true, message: "检品编号不能为空", trigger: "blur" }
        // ],
        // pesticideID: [
        //   { required: true, message: "检测农药ID不能为空", trigger: "blur" }
        // ],
        // inspectorID: [
        //   { required: true, message: "检测员ID不能为空", trigger: "blur" }
        // ],
        // detectionData: [
        //   { required: true, message: "检测数据不能为空", trigger: "blur" }
        // ],
        // auditStatus: [
        //   { required: true, message: "审核状态(0-提交审核，审核中，1-审核通过，2-审核不通过，3-保存)不能为空", trigger: "blur" }
        // ],
        // operationTime: [
        //   { required: true, message: "操作时间不能为空", trigger: "blur" }
        // ],
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

    /** 查询检测结果列表 */
    getList() {
      this.loading = true;
      listDetectionResult(this.queryParams).then(response => {
        this.detectionResultList = response.rows;
        console.log(this.detectionResultList)

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
        detectionResultID: null,
        // sampleID: null,
        sampleNumber: null,
        taskNumber: null,
        // pesticideID: null,
        // inspectorID: null,
        detectionData: null,
        auditStatus: null,
        operationTime: null,
        remark: null,
        inspectionMethodName: null,
        sampleAcademicName: null,
          pesticideName: null,
          inspectorName: null,
          inspectionRoom: null,
          standard: null,
          maxContent: null,
          adi: null,
        sampleUnit: null


          // sampleNumber: null,
          // sampleAcademicName: null,
          // inspectionMethodName: null,
          // pesticideName: null,
          // maxContent: null,
          // detectionData: null

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
      this.ids = selection.map(item => item.detectionResultID)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加检测结果";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      if (row.auditStatus === 0) {
        this.reset();
        const detectionResultID = row.detectionResultID || this.ids

        // this.queryParams.detectionResultID=row.detectionResultID || this.ids
        getDetectionResult(detectionResultID).then(response => {

          this.form = response.data;
          this.form.sampleUnit="(mg/kg)"
          this.open = true;
          this.title = "输入检测数据";
        });
      }else{
        this.msgError("已提交审核");
        this.getList();
      }
    },

    /** 提交检测按钮操作 */
    submitBatchReview() {

      let flag=0;
      let items=this.items;
      for (let i = 0; i <items.length ; i++) {
        if(this.items[i].auditStatus!==0) flag=1;
      }
      if(flag===0&&items){
        console.log(items)
        this.$confirm('是否确认批量提交选中的检测结果?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(
          function() {
            return submitBatchReview(items);
          }).then(() => {
          this.getList();
          this.msgSuccess("提交成功");
        }).catch(() => {});
      }else{
        this.msgError("请勿重复提交审核");
        this.getList();
      }
    },

    submitReview(row){
      if(row.auditStatus===0){
        console.log(row)
        this.$confirm('检测数据为:'+row.detectionData+'(mg/kg)，确认提交审核？', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return submitReview(row);
        }).then(() => {
          this.getList();
          this.msgSuccess("提交成功");
        }).catch(() => {});
      }else{
        this.msgError("请勿重复提交审核");
        this.getList();
      }
    },

    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {

          const form = this.form;
          if(this.form.detectionData>=this.form.maxContent){
            this.$confirm('输入检验数据大于最大含量标准!', "警告", {
              confirmButtonText: "确定",
              cancelButtonText: "取消",
              type: "warning"
            }).then(
              function() {
                return updateDetectionResult(form);
              }).then(() => {
              this.getList();
              this.open = false;
              this.msgSuccess("提交成功");
            }).catch(() => {});
          }else{
            this.$confirm('确定输入检验数据?', "警告", {
              confirmButtonText: "确定",
              cancelButtonText: "取消",
              type: "warning"
            }).then(
              function() {
                return updateDetectionResult(form);
              }).then(() => {
              this.getList();
              this.open = false;
              this.msgSuccess("提交成功");
            }).catch(() => {});
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const detectionResultIDs = row.detectionResultID || this.ids;
      this.$confirm('是否确认删除检测结果编号为"' + detectionResultIDs + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delDetectionResult(detectionResultIDs);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有检测结果数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.exportLoading = true;
          return exportDetectionResult(queryParams);
        }).then(response => {
          this.download(response.msg);
          this.exportLoading = false;
        }).catch(() => {});
    }
  }
};
</script>
