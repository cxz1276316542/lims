<template>
  <!--部门数据-->

  <div class="app-container" >

    <el-dialog style="height: 700px;width: 60%;margin: 10px auto;"
               :close-on-click-modal="false"
               :visible.sync="dialogVisible"
               :fullscreen="true"
               title="文件预览">
      <div class="agreement_picture" style="height: 600px;width: 100%">
        <div class="pdf" style="height: 100%;width: 100%;position: relative;transform: translateY(-5%)">
          <iframe :src="pdfsrc" frameborder="0" style="width: 100%; height: 100%;">111</iframe>
        </div>
      </div>

    </el-dialog>
    <el-col :span="4" :xs="24">
      <div class="head-container">


          <el-button
            type="primary"
            plain
            icon="el-icon-edit"
            size="mini"
            @click="toFirstAdit"
          >报告撰写</el-button>


          <el-button
            type="info"
            plain
            icon="el-icon-edit"
            size="mini"

            @click="toReAdit"
          >重新撰写</el-button>

      </div>

      <div style="margin-top: 20px">
        <el-tree
          :data="data"
          show-checkbox
          node-key="id"
          default-expand-all
          ref="tree"
          highlight-current
          @check-change="handleCheckChange"
          @node-click="handleNodeClick"
          :props="defaultProps">
        </el-tree>
      </div>
<!--      <div class="head-container">-->
<!--        <el-tree-->
<!--          :props="props"-->
<!--          :load="loadNode"-->
<!--          lazy-->
<!--          show-checkbox-->
<!--          @check-change="handleCheckChange">-->
<!--        </el-tree>-->
<!--&lt;!&ndash;        <el-tree&ndash;&gt;-->
<!--&lt;!&ndash;          :data="deptOptions"&ndash;&gt;-->
<!--&lt;!&ndash;          :props="defaultProps"&ndash;&gt;-->
<!--&lt;!&ndash;          :expand-on-click-node="false"&ndash;&gt;-->
<!--&lt;!&ndash;          :filter-node-method="filterNode"&ndash;&gt;-->
<!--&lt;!&ndash;          ref="tree"&ndash;&gt;-->
<!--&lt;!&ndash;          default-expand-all&ndash;&gt;-->
<!--&lt;!&ndash;          @node-click="handleNodeClick"&ndash;&gt;-->
<!--&lt;!&ndash;        />&ndash;&gt;-->
<!--      </div>-->
    </el-col>
    <el-col :span="20" :xs="24">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="150px" >

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
<!--      <el-form-item label="检测项目名称" prop="pesticideName">-->
<!--        <el-input-->
<!--          v-model="queryParams.pesticideName"-->
<!--          placeholder="请输入检测项目称"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
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
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch"  label-width="150px"   >
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
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="multiple"
          @click="createReport"
          v-hasPermi="['detection:report:edit']"
        >生成报告</el-button>

        <el-select filterable clearable  v-model="form.reportTemID" placeholder="请选择报告模板" style="width: 150px;margin-left: 20px" size="mini">
          <el-option
            v-for="item in laboratoryList"
            :key="item.reportTemID"
            :label="item.reportTemName"
            :value="item.reportTemID">
          </el-option>
        </el-select>
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
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="warning"-->
<!--          plain-->
<!--          icon="el-icon-download"-->
<!--          size="mini"-->
<!--		  :loading="exportLoading"-->
<!--          @click="handleExport"-->
<!--          v-hasPermi="['detection:detectionResult:export']"-->
<!--        >导出</el-button>-->
<!--      </el-col>-->

      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>
<!--      <el-button @click="toggleSelection(detectionResultList)">切换第二、第三行的选中状态</el-button>-->
    <el-table v-loading="loading" :data="detectionResultList" @selection-change="handleSelectionChange" ref="multipleTable">

      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="项目检测数据记录ID" align="center" prop="detectionResultID" />-->
      <el-table-column label="检品编号" align="center" prop="sampleNumber" />
      <el-table-column label="检测任务单编号" align="center" prop="taskNumber" />
      <el-table-column label="样品学名" align="center" prop="sampleAcademicName" />
      <el-table-column label="检测项目名称" align="center" prop="pesticideName" />
      <el-table-column label="检测人员名称" align="center" prop="inspectorName" />
      <el-table-column label="优先级" align="center" prop="priority" >
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
      <el-table-column label="最大含量标准" align="center" prop="maxContent" />
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
          <span v-if="scope.row.auditStatus === 1">初检</span>
          <span v-if="scope.row.auditStatus === 2">复检</span>
          <span v-if="scope.row.auditStatus === 3">其他</span>
        </template>
      </el-table-column>
      <el-table-column label="操作时间" align="center" prop="operationTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.operationTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark" />
    </el-table>

      <el-dialog :title="title" :visible.sync="open" width="1000px"  append-to-body>

      <el-table v-loading="loading" :data="reportList" >
<!--        <el-table-column type="selection" width="55" align="center" />-->
        <!--      <el-table-column label="检测报告ID" align="center" prop="sreportID" />-->
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
        <el-table-column label="备注" align="center" prop="remark" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">


          <template slot-scope="scope">
            <el-button
              size="mini"
              type="text"
              icon="el-icon-view"
              @click="previewReport(scope.row)"
              v-hasPermi="['detection:report:remove']"
            >预览报告</el-button>

          </template>
        </el-table-column>
      </el-table>


      </el-dialog>
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
    </el-col>
  </div>
</template>

<script>
import { detectionTreeselect,listDetectionResult, getDetectionResult, delDetectionResult, addDetectionResult, updateDetectionResult, exportDetectionResult ,submitReview,submitBatchReview} from "@/api/detection/detectionResult";
import {
  selectPassList,
  listPassDetectionResult,
  createReport,
  selectReport,
  selectAllTem,
  reCreateReport
} from "@/api/detection/report";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
export default {
  name: "DetectionResult",
  data() {
    return {
      dialogVisible:false,
      pdfsrc:"",
      reportList: [],
      map:{},
      list:[],
      laboratoryList:[],

      // data: [{
      //   id:1,
      //   label: 'WT001',
      //   children: [
      //     {
      //       id: 3,
      //       label: 'ZK2021081201',
      //     },
      //     {
      //       id: 4,
      //       label: 'ZK2021081202',
      //     }
      //   ]
      // }, {
      //   id:2,
      //   label: 'WT002',
      //   children: [
      //     {
      //       id: 6,
      //       label: 'YP202108100002',
      //     }
      //   ]
      // }],
      data: [],
      // 树选项
      deptOptions: undefined,
      defaultProps: {
        children: "children",
        label: "label"
      },
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
      //tree
      tmp:[],
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
        value: 1,
        label: '初检'
      },{
        value: 2,
        label: '复检'
      },{
        value: 3,
        label: '其他'
      }],

      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 50,
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
        priority:null,
        reportTemID:null,
        reportTemName:null

      },
      // 表单参数
      form: {
        reportTemID:'',
        reportTemName:''
      },
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
    this.getTreeselect();
    this.getList();
    this.findAllCustomers();

  },
  methods: {
    // 查询所有检测中心
    findAllCustomers(){

      selectAllTem().then(res=>{
        debugger
        console.log(res)
        this.laboratoryList = res.data
      })
    },
    getPreview() {
      this.loading = true;
      let items=this.items;
      console.log(items);
      selectReport(items).then(response => {
        this.reportList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    previewReport(row) {

      const sampleNumber = row.sampleNumber || this.ids;
      //http://47.106.113.181:8080;http://localhost:8080
      this.pdfsrc=this.$store.state.baseUrl+sampleNumber+'.pdf';


      this.dialogVisible=true;

    },
    toFirstAdit() {
      this.$router.push({ path: "/report/toFirstAdit"});
    },
    toReAdit(){
      this.$router.push({ path: "/report/toReAdit"});
    },
    toggleSelection(rows) {

      for(let i in rows){
        if(this.list.indexOf(rows[i].sampleNumber)!=-1){
          this.$refs.multipleTable.toggleRowSelection(rows[i],true);
        }else{
          this.$refs.multipleTable.toggleRowSelection(rows[i],false);
        }
      }
    },


    //复选框的值改变
    handleCheckChange(data, checked, indeterminate) {
      console.log('data开始了')
      console.log(data)

      this.list=this.$refs.tree.getCheckedNodes().map(item=>{
        if(!item.children){
          return item.label
        }
      })

      // this.handleNodeClick(data);

      this.toggleSelection(this.detectionResultList);


    },
    // 节点单击事件
    handleNodeClick(data){

      this.queryParams.sampleNumber = data.label;
      this.getList();
    },
    /** 查询部门下拉树结构 */
    getTreeselect() {
      selectPassList().then(response => {
        console.log(response.data);
        this.data = response.data;
      });
    },
    // 筛选节点
    filterNode(value, data) {
      if (!value) return true;
      return data.label.indexOf(value) !== -1;
    },

    /** 查询检测结果列表 */
    getList() {
      console.log("查询结果")
      this.loading = true;
      listPassDetectionResult(this.queryParams).then(response => {
        this.detectionResultList = response.rows;
        this.tmp=response.rows;
        // this.tmp=response.rows;
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
          adi: null


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

      console.log('11111111111111111111111')
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

  /** 提交检测按钮操作 */
  createReport() {

    let items=this.items;
    let form=this.form;
    console.log(items);
    debugger
    if (form.reportTemID!=null){
      this.$confirm('是否生成报告?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(
        function() {
          return createReport({detectionResultVOList:items,inspectorId:form.reportTemID});
        }).then(() => {

        this.open=true;
        this.getPreview();
        this.getTreeselect();
        this.queryParams.sampleNumber=null;
        this.getList();
        this.msgSuccess("提交成功");
      }).catch(() => {});
    }else{
      this.getPreview();
      this.getTreeselect();
      this.queryParams.sampleNumber=null;
      this.getList();
      this.msgError("请选择报告模板");
    }

    }
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


};
</script>
