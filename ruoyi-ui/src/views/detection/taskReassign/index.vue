<template>
  <div class="app-container">
    <el-card>
      <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
        <el-form-item label="样品编号" prop="sampleNumber">
          <el-input
                  v-model="queryParams.sampleNumber"
                  placeholder="请输入样品编号"
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
        <el-form-item label="检测农药" prop="pesticideID">
          <el-select clearable filterable v-model="queryParams.pesticideID">
            <el-option v-for="it in pesticideListOption"
                       :key="it.pesticideID"
                       :label="it.pesticideName"
                       :value="it.pesticideID" @keyup.enter.native="handleQuery"/>
          </el-select>
        </el-form-item>
        <el-form-item label="检测方法" prop="detectionMethodID">
          <el-select clearable filterable v-model="queryParams.detectionMethodID">
            <el-option v-for="it in detectionMethodListOption"
                       :key="it.detectionMethodID"
                       :label="it.detectionMethodName"
                       :value="it.detectionMethodID" @keyup.enter.native="handleQuery"/>
          </el-select>
        </el-form-item>
        <el-form-item label="检测员" prop="inspectorID">
          <el-select clearable filterable v-model="queryParams.inspectorID">
            <el-option v-for="it in inspectorListOption"
                       :key="it.inspectorID"
                       :label="it.inspectorName"
                       :value="it.inspectorID" @keyup.enter.native="handleQuery"/>
          </el-select>
        </el-form-item>
        <el-form-item label="主副标识" prop="flag">
          <el-select clearable filterable v-model="queryParams.flag">
            <el-option v-for="it in flagListOption"
                       :key="it.id"
                       :label="it.status"
                       :value="it.id" @keyup.enter.native="handleQuery"/>
          </el-select>
        </el-form-item>
<!--        <el-form-item label="认领标识" prop="status">
          <el-select clearable filterable v-model="queryParams.status">
            <el-option v-for="it in statusListOption"
                       :key="it.id"
                       :label="it.status"
                       :value="it.id" @keyup.enter.native="handleQuery"/>
          </el-select>
        </el-form-item>-->
        <!--      <el-form-item label="审核状态" prop="reviewStatus">
                <el-input
                  v-model="queryParams.reviewStatus"
                  placeholder="请输入审核状态"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery"
                />
              </el-form-item>-->
        <el-form-item label="操作员ID" prop="operatorID">
          <el-select clearable filterable v-model="queryParams.operatorID">
            <el-option v-for="it in servicerListOption"
                       :key="it.inspectorID"
                       :label="it.inspectorName"
                       :value="it.inspectorID"/>
          </el-select>
        </el-form-item>
        <el-form-item label="操作时间" prop="operationTime">
          <el-date-picker clearable size="small"
                          v-model="operationTimeArray"
                          type="daterange"
                          value-format="yyyy-MM-dd"
                          range-separator="至"
                          start-placeholder="开始日期"
                          end-placeholder="结束日期">
          </el-date-picker>
        </el-form-item>
        <!--      <el-form-item label="备注" prop="remark">
                <el-input
                  v-model="queryParams.remark"
                  placeholder="请输入备注"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery"
                />
              </el-form-item>-->
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
      <!--      <el-col :span="1.5">
              <el-button
                type="primary"
                plain
                icon="el-icon-plus"
                size="mini"
                @click="handleAdd"
                v-hasPermi="['detection:taskAssign:add']"
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
                v-hasPermi="['detection:taskAssign:edit']"
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
                v-hasPermi="['detection:taskAssign:remove']"
              >删除</el-button>
            </el-col>-->
      <el-col :span="1.5">
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
        </el-col>        <el-button
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

    <el-table v-loading="loading" :data="taskAssignList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="任务分派ID" align="center" prop="taskAssignID" />
      <el-table-column label="委托样品ID" align="center" prop="sampleID" />
      <el-table-column label="任务分派编号" align="center" prop="taskAssignNumber" />
      <el-table-column label="样品编号" align="center" prop="sampleNumber" />
      <el-table-column label="检测项目" align="center" prop="pesticideName" />
      <el-table-column label="检测方法" align="center" prop="detectionMethod" />
      <el-table-column label="检测员" align="center" prop="inspectorName" />
      <el-table-column label="标识" align="center" prop="flag">
        <template slot-scope="scope">
          <span v-if="scope.row.flag == 1">主检</span>
          <span v-else-if="scope.row.flag == 2">副检</span>
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          <span v-if="scope.row.status == 0">未认领</span>
          <span v-if="scope.row.status == 1">认领成功</span>
          <span v-if="scope.row.status == 2">拒绝认领</span>
        </template>
      </el-table-column>
      <el-table-column label="操作员" align="center" prop="operatorName" />
      <el-table-column label="操作时间" align="center" prop="operationTime" >
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

    <!-- 添加或修改任务分派对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="700px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="样品编号" prop="sampleNumber">
          <el-input v-model="form.sampleNumber" placeholder="请输入样品编号" :disabled="true"  />
        </el-form-item>
        <el-form-item label="任务分派编号" prop="taskAssignNumber">
          <el-input v-model="form.taskAssignNumber" placeholder="请输入任务分派编号" :disabled="true" />
        </el-form-item>
        <el-form-item label="检测农药" prop="pesticideID">
          <el-select clearable filterable v-model="form.pesticideID" :disabled="true">
            <el-option v-for="it in pesticideListOption"
                       :key="it.pesticideID"
                       :label="it.pesticideName"
                       :value="it.pesticideID" @keyup.enter.native="handleQuery"/>
          </el-select>
        </el-form-item>
        <el-form-item label="检测方法" prop="detectionMethod" :rules="rules.detectionMethod">
          <el-select clearable filterable v-model="form.detectionMethod">
            <el-option v-for="it in detectionMethodListOption"
                       :key="it.detectionMethodID"
                       :label="it.detectionMethodName"
                       :value="it.detectionMethodID"/>
          </el-select>
        </el-form-item>
        <el-form-item label="检测员" prop="inspectorID" :rules="rules.inspectorID">
          <el-select clearable filterable v-model="form.inspectorID">
            <el-option v-for="it in inspectorListOption"
                       :key="it.inspectorID"
                       :label="it.inspectorName"
                       :value="it.inspectorID"/>
          </el-select>
        </el-form-item>
        <el-form-item label="重新分派理由" prop="remark">
          <el-input v-model="form.remark"  :disabled="true" placeholder="" />
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
  import { listTaskAssign, getTaskAssign, delTaskAssign, addTaskAssign, updateTaskAssign, exportTaskAssign,
    getTaskAssignListVO,getPesticideIDAndName,getDetectionMethodIDAndName,getInspectorIDAndName,
    getServicerIDAndName,getTaskAssignInfo,updateTaskAssignInfo} from "@/api/detection/taskReassign";

  export default {
    name: "TaskAssign",
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
        // 任务分派表格数据
        taskAssignList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        operationTimeArray:[],
        pesticideListOption:[],
        detectionMethodListOption:[],
        inspectorListOption:[],
        servicerListOption:[],
        statusListOption:[{id: 0, status:'未认领'},{id:1,status:'认领成功'},{id:2,status:'认领失败'}],
        flagListOption:[{id: 1, status:'主检员'},{id:2,status:'副检员'}],
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          sampleNumber: null,
          taskAssignNumber: null,
          pesticideID: null,
          detectionMethodID: null,
          inspectorID: null,
          flag: null,
          status: null,
          operatorID: null,
          operationStartTime: null,
          operationEndTime: null,
          remark: null
        },
        // 表单参数
        form: {},
        // 以前数据
        oldData:{
          inspectorID: null,
          detectionMethod: null,
        },
        // 表单校验
        rules: {
          detectionMethod: [
            { required: true, message: "检测方法不能为空", trigger: "change" }
          ],
          inspectorID: [
            { required: true, message: "检测员不能为空", trigger: "change" }
          ],
        }
      };
    },
    created() {
      this.findDetectionMethodIDAndName();
      this.findInspectorIDAndName();
      this.findPesticideIDAndName();
      this.findServicerIDAndName();
      this.getList();
    },
    methods: {
      // 匹配时间
      matchOperationTime(){
        if(this.operationTimeArray == null || this.operationTimeArray.length > 0){
          this.queryParams.operationStartTime = this.operationTimeArray[0]
          this.queryParams.operationEndTime = this.operationTimeArray[1]
        }
      },
      /** 查询任务分派列表 */
      getList() {
        this.loading = true;
        this.matchOperationTime()
        getTaskAssignListVO(this.queryParams).then(response => {
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
        this.ids = selection.map(item => item.taskAssignID)
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
        this.reset();
        const taskAssignID = row.taskAssignID || this.ids
        getTaskAssignInfo(taskAssignID).then(response => {
          this.form = response.data;
          this.oldData.inspectorID = this.form.inspectorID;
          this.oldData.detectionMethod = this.form.detectionMethod;
          this.open = true;
          this.title = "任务重新分派";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if(this.validateDataIsSame()){
              this.msgError("请重新选择检测方法或检测员")
              return;
            }
            this.$confirm('是否确认提交?', "提示", {
              confirmButtonText: "确定",
              cancelButtonText: "取消",
              type: "info"
            }).then(()=>{
              updateTaskAssignInfo(this.form).then(res=>{
                this.msgSuccess("重新分派成功");
                this.open = false;
                this.getList();
              })
            })
          }
        });
      },
      /**提交之前判断是否数据发生改变*/
      validateDataIsSame(){
        return this.oldData.inspectorID === this.form.inspectorID && this.oldData.detectionMethod === this.form.detectionMethod;
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        const taskAssignIDs = row.taskAssignID || this.ids;
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
      // 获取所有的检测农药ID和名称
      findPesticideIDAndName(){
        getPesticideIDAndName().then(res=>{
          this.pesticideListOption = res.data
        })
      },
      // 获取所有检测方法ID和名称
      findDetectionMethodIDAndName(){
        getDetectionMethodIDAndName().then(res=>{
          this.detectionMethodListOption = res.data
        })
      },
      // 获取所有的检测员的ID和名称
      findInspectorIDAndName(){
        getInspectorIDAndName().then(res=>{
          this.inspectorListOption = res.data
        })
      },
      findServicerIDAndName(){
        getServicerIDAndName().then(res=>{
          this.servicerListOption = res.data
        })
      },
    }
  };
</script>
