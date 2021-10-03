<template>
  <div class="app-container">
    <el-card>
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="检品ID" prop="sampleID">
        <el-input
          v-model="queryParams.sampleID"
          placeholder="请输入检品ID"
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
      <el-form-item label="检品学名" prop="sampleAcademicName">
        <el-input
          v-model="queryParams.sampleAcademicName"
          placeholder="检品学名"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="检品状态" prop="sampleStatus">
        <el-input
          v-model="queryParams.sampleStatus"
          placeholder="检品状态"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="检测标准" prop="detectionStandardID">
        <el-input
          v-model="queryParams.detectionStandardID"
          placeholder="请输入检测人ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="操作时间">
        <el-date-picker clearable size="small"
                        v-model="operationTimeList"
                        type="daterange"
                        value-format="yyyy-MM-dd"
                        range-separator="至"
                        start-placeholder="开始日期"
                        end-placeholder="结束日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    </el-card>
    <el-row :gutter="20" style="margin-bottom: 20px;margin-top: 20px">
      <el-button
              type="success"
              v-hasPermi="['detection:taskAssign:query']"
              @click="toTaskAssign"
              round
      >任务首次分派</el-button>
      <el-button
              type="primary"
              v-hasPermi="['detection:taskAssign:query']"
              @click="toTaskReassign"
              round
      >任务再次分派</el-button>
      <el-button
              type="warning"
              v-hasPermi="['detection:taskAssign:query']"
              @click="toTaskAssignSearch"
              round
      >任务查询</el-button>
    </el-row>


    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['detection:taskAssign:edit']"
        >分派</el-button>
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
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="taskAssignAddList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="检品ID" align="center" prop="sampleID" />
      <el-table-column label="检品编号" align="center" prop="sampleNumber" />
      <el-table-column label="检品学名" align="center" prop="sampleAcademicName" />
      <el-table-column label="优先级" align="center" prop="priority" >
        <template slot-scope="scope">
          <span v-if="scope.row.priority == 1">正常</span>
          <span v-else-if="scope.row.priority == 2">急</span>
          <span v-else-if="scope.row.priority == 3">加急</span>
          <span v-else-if="scope.row.priority == 4">特急</span>
        </template>
      </el-table-column>
      <el-table-column label="检品状态" align="center" prop="sampleStatus" >
        <template slot-scope="scope">
          <span v-if="scope.row.sampleStatus == 1">固体</span>
          <span v-if="scope.row.sampleStatus == 2">液体</span>
          <span v-if="scope.row.sampleStatus == 3">胶体</span>
          <span v-if="scope.row.sampleStatus == 4">气体</span>
          <span v-if="scope.row.sampleStatus == 5">其他</span>
        </template>
      </el-table-column>
      <el-table-column label="检测标准名称" align="center" prop="detectionStandardName" />
      <el-table-column label="检品登记时间" align="center" prop="sampleRegisterTime" >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.sampleRegisterTime, '{y}-{m}-{d}') }}</span>
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
            v-hasPermi="['detection:taskAssign:edit']"
          >分派</el-button>
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

    <!-- 任务分派对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1500px" append-to-body>
      <el-card>
      <h2>检品基本信息</h2>
      <el-form ref="form" :model="form" :rules="rules">
        <el-row :gutter="20">
          <el-col :span="7">
            <el-form-item label="检品ID" prop="sampleID" label-width="120px">
              <el-input v-model="form.sampleID" :disabled="true"  />
            </el-form-item>
          </el-col>
          <el-col :span="7">
            <el-form-item label="检品编号" prop="sampleNumber" label-width="120px">
              <el-input v-model="form.sampleNumber" :disabled="true" />
            </el-form-item>
          </el-col>
          <el-col :span="7">
            <el-form-item label="检品学名" prop="sampleAcademicName" label-width="120px">
              <el-input v-model="form.sampleAcademicName" :disabled="true" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="7">
            <el-form-item label="优先级" prop="priority" label-width="120px">
              <el-select v-model="form.priority" :disabled="true" >
                <el-option
                        v-for="it in priorities"
                        :key="it.value"
                        :label="it.label"
                        :value="it.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="7">
            <el-form-item label="检品状态" prop="sampleStatus" label-width="120px">
              <el-select v-model="form.sampleStatus" :disabled="true">
                <el-option
                        v-for="it in sampleStatusList"
                        :key="it.value"
                        :label="it.label"
                        :value="it.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="7">
            <el-form-item label="检测标准" prop="detectionStandardID" label-width="120px" :rules="rules.detectionStandardID">
              <el-select v-model="form.detectionStandardID" placeholder="请输入检测标准">
                <el-option v-for="it in detectionStandardOptions"
                           :key="it.detectionStandardID"
                           :label="it.detectionNumber"
                           :value="it.detectionStandardID"
                />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="7">
            <el-form-item label="检品登记时间" prop="sampleRegisterTime" label-width="120px">
              <el-input v-model="form.sampleRegisterTime" :disabled="true" />
            </el-form-item>
          </el-col>
          <el-col :span="7">
            <el-form-item label="备注" prop="remark" label-width="120px">
              <el-input v-model="form.remark" :disabled="true" />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      </el-card>
      <el-card>
      <h2>检测项目</h2>
      <el-form ref="itemForm" :model="form" :rules="rules" label-width="100px" size="medium">
        <el-row :gutter="20" v-for="(item,index) in detectionTask.detectionItemList" :key="index">
          <el-card>
          <el-row :gutter="20" style="margin-top: 15px;height: 30px">
            <el-col :span="6">
              <!---->
              <el-form-item :label="'检测项目 '+(index+1)" :rules="rules.pesticideID">
                <el-select v-model="item.pesticideID" placeholder="请选择检测项目" clearable @change="searchMethodsAndInspectors(index)">
                  <el-option v-for="it in detectionItemOptions"
                             :key="it.pesticideID"
                             :label="it.pesticideName"
                             :value="it.pesticideID"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="5">
              <!---->
              <el-form-item label="检测方法" :rules="rules.detectionMethodID">
                <el-select v-model="item.detectionMethodID" placeholder="请选择检测方法" clearable>
                  <el-option v-for="it in item.methods"
                             :key="it.id"
                             :label="it.name"
                             :value="it.id"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="5">
              <!---->
              <el-form-item label="主检员"  :rules="rules.mainInspectorID">
                <el-select v-model="item.mainInspectorID" placeholder="请选择主检员" clearable>
                  <el-option v-for="it in item.inspectors"
                             :key="it.id"
                             :label="it.name"
                             :value="it.id"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="5">
              <!---->
              <el-form-item label="副检员" >
                <el-select v-model="item.deputyInspectorID" placeholder="请选择副检员" clearable>
                  <el-option v-for="it in item.inspectors"
                             :key="it.id"
                             :label="it.name"
                             :value="it.id"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="3">
              <el-form-item>
                <el-button type="danger" size="small" plain
                           @click="removeRow(index)">删除</el-button>
              </el-form-item>
            </el-col>
        </el-row>
          </el-card>
        </el-row>
        <el-row :gutter="20">
            <el-col :span="20" :push="18">
              <el-form-item>
                <el-button type="primary" @click="addItemRow()">增加检测项目</el-button>
                <el-button type="success" @click="clearItemRow()">清空项目</el-button>
              </el-form-item>
            </el-col>
        </el-row>
      </el-form>
      </el-card>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitTask">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listTaskUnassigned, getTaskAssign, delTaskAssign, addTaskAssign,
  updateTaskAssign, exportTaskAssign, getUnsignedTaskByID, getDetectionStandards,
  getAllDetectionItemList,getDetectionItemIDAndName,getAllDetectionMethodList,
  getAllInspectorList,addTask,findMethodsAndInspectors} from "@/api/detection/taskAssignAdd";

export default {
  name: "TaskAssignAdd",
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
      // 任务分派增加表格数据
      taskAssignAddList: [
        {
          sampleID: null,
          sampleNumber: null,
          sampleAcademicName: null,
          priority: null,
          sampleStatus: null,
          detectionStandardName: null,
          sampleRegisterTime: null,
          remark:null
        }
      ],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        sampleID:'',
        sampleNumber: '',
        sampleAcademicName: '',
        priority: '',
        sampleStatus: '',
        detectionStandardID: '',
        sampleRegisterStartTime: '',
        sampleRegisterEndTime: ''
      },
      // 操作时间list
      operationTimeList: '',
      // 表单参数
      form: {},
      //检品状态下拉选择项
      sampleStatusList:[{
        value: 1,
        label: '固态'
      }, {
        value: 2,
        label: '液态'
      },{
        value: 3,
        label: '胶体'
      }, {
        value: 4,
        label: '气体'
      }, {
        value: 5,
        label: '其他'
      }],
      //优先级下拉选择选项
      priorities: [{
        value: 1,
        label: '正常'
      }, {
        value: 2,
        label: '急'
      }, {
        value: 3,
        label: '加急'
      }, {
        value: 4,
        label: '特急'
      }],
      // 检测标准列表
      detectionStandardOptions: [
        {
          detectionNumber: "GB 2763-2019",
          detectionStandardID: 1,
        }
      ],
      // 检测项目List
      detectionTask:{
        detectionItemList:[]
      },
      // 检测项目选项
      detectionItemOptions: [],
      // 检测方法选项
      detectionMethodOptions: [],
      // 检测人员列表
      inspectorOptions:[],
      // 表单校验
      rules: {
        detectionStandardID: [
          { required: true, message: "检测标准不能为空", trigger: "change" }
        ],
        pesticideID: [
          { required: true, message: "检测项目不能为空", trigger: "change" }
        ],
        detectionMethodID: [
          { required: true, message: "检测方法不能为空", trigger: "change" }
        ],
        mainInspectorID: [
          { required: true, message: "主检员不能为空", trigger: "change" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询任务分派列表 */
    getList() {
      this.loading = true;
      if(this.operationTimeList != null || this.operationTimeList.length > 0){
        this.queryParams.sampleRegisterStartTime = this.operationTimeList[0]
        this.queryParams.sampleRegisterEndTime = this.operationTimeList[1]
      }
      listTaskUnassigned(this.queryParams).then(response => {
        this.taskAssignAddList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.detectionTask.detectionItemList = [];
      this.detectionItemOptions = [];
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
    /** 分派按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.findAllStandardInfo();
      const sampleID = row.sampleID || this.ids
      getUnsignedTaskByID(sampleID).then(response => {
        this.form = response.data;
        if(response.data.detectionItemList == null || response.data.detectionItemList.length == 0){
          return;
        }
        this.detectionTask.detectionItemList = [];
        this.detectionItemOption = [];
        for(let i = 0; i < response.data.detectionItemList.length; ++i){
          // 检测项目选项填充
            this.detectionItemOptions.push(response.data.detectionItemList[i]);
          // 检测方法和检测员填充
            this.detectionTask.detectionItemList.push(response.data.detectionItemList[i]);
        }
        // 展示所有的检测项目，然后把所有检测项目 --> 多个检测方法
        //                          检测项目 --> 多个检测员，默认一个主检员
        this.open = true;
        this.title = "任务分派";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.sampleID != null) {
            updateTaskAssign(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTaskAssign(this.form).then(response => {
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
    },
    // 跳转到任务首次分派页面
    toTaskAssign(){
      this.$router.push({ path: "/detection/taskAssign"});
    },
    // 跳转到任务查询页面
    toTaskAssignSearch(){
      this.$router.push({ path: "/detection/taskAssignSearch"});
    },
    // 跳转到任务再次分派页面
    toTaskReassign(){
      this.$router.push({ path: "/detection/taskReassign"});
    },
    // 获取所有检测标准ID和名称
    findAllStandardInfo(){
      getDetectionStandards().then(res=>{
        if(res.data != null){
          this.detectionStandardOptions = res.data
        }
      })
    },
    // 根据获取检测项目列表
    findAllDetectionItemList(){
      getAllDetectionItemList(this.form.sampleAcademicName,this.form.detectionStandardID).then(res =>{
        for(let i = 0; i < res.data.length; i++){
          res.data[i].mainInspectorID = 103
          res.data[i].deputyInspectorID = 104
          if(res.data[i].detectionMethodID == null){
            res.data[i].detectionMethodID = 1
          }
          this.detectionTask.detectionItemList.push(res.data[i])
        }
      });
    },
    // 删除检测项目
    removeRow(index) {
      if (index >= 0) {
        this.detectionTask.detectionItemList.splice(index, 1);
      }
    },
    //清空检测项目
    clearItemRow(){
      const len = this.detectionTask.detectionItemList.length
      for(let i = 0; i < len; i++){
        this.detectionTask.detectionItemList.pop();
      }
    },
    // 增加检测项目
    addItemRow(){
      this.detectionTask.detectionItemList.push({});
    },
    // 根据学名和检测标准获取到检测项目ID和名称
    findDetectionItemIDAndName(){
      getDetectionItemIDAndName(this.form.sampleAcademicName,this.form.detectionStandardID).then(res=>{
        this.detectionItemOptions = res.data
      });
    },
    // 获取到所有的检测方法ID和名称
    findAllDetectionMethodList(){
      getAllDetectionMethodList().then(res=>{
        this.detectionMethodOptions = res.data
      })
    },
    // 获得到所有的检测人员列表
    findAllInspectorList(){
      getAllInspectorList().then(res=>{
        this.inspectorOptions = res.data
      })
    },
    // 新增检测项目
    submitTask(){
      this.$refs["form"].validate(valid1=>{
        if(valid1){
          this.$refs["itemForm"].validate(valid2=>{
            if(valid2){
              if(!this.validData()){
                return;
              }
              this.$confirm('是否确认提交?', "提示", {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "info"
              }).then(()=>{
                this.matchSampleIDAndStandardID()
                addTask(this.detectionTask.detectionItemList).then(res =>{
                  this.getList();
                  this.open = false
                  this.msgSuccess("新增成功");
                  this.detectionTask.detectionItemList = [];
                  this.detectionItemOptions = [];
                })
              })
            }
          })
        }
      })
    },
    // 为每个检测项目匹配检品ID和检测标准ID
    matchSampleIDAndStandardID(){
      for(let i = 0; i < this.detectionTask.detectionItemList.length; i++){
        this.detectionTask.detectionItemList[i].detectionStandardID = this.form.detectionStandardID
        this.detectionTask.detectionItemList[i].sampleID = this.form.sampleID
      }
    },
    // 各种校验
    validData(){
      if(this.detectionTask.detectionItemList == null || this.detectionTask.detectionItemList == 'undefine'){
        this.msgError("检测项目不能为空")
        return false;
      }
      // 检测项目不能重复
      const s = new Set()
      const len = this.detectionTask.detectionItemList.length
      for(let i = 0; i < len; i++){
        s.add(this.detectionTask.detectionItemList[i].pesticideID)
        //主检人和复检人不能相等
        if(this.detectionTask.detectionItemList[i].mainInspectorID == this.detectionTask.detectionItemList[i].deputyInspectorID){
          this.msgError("同一个检测项目的主检员和副检员不能为同一个")
          return false;
        }
      }
      if(s.size != len){
        this.msgError("检测项目不能重复")
        return false;
      }
      return true;
    },
    // 查找检测方法和检测员
    searchMethodsAndInspectors(index){
      findMethodsAndInspectors(this.detectionTask.detectionItemList[index].pesticideID).then(res=>{
        if(res.data == null || res.data.inspectors == null || res.data.methods == null){
          this.msgInfo("检测方法或检测员没有数据")
        }
        this.detectionTask.detectionItemList[index].inspectors = res.data.inspectors;
        this.detectionTask.detectionItemList[index].methods = res.data.methods;
      })
    }
  }
};
</script>
