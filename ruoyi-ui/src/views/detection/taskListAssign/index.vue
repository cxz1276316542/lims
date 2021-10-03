<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
<!--      <el-form-item label="负责分配的业务员" prop="salesmanID" label-width="150px">-->
<!--        <el-input-->
<!--          v-model="queryParams.salesmanID"-->
<!--          placeholder="请选择负责分配的业务员"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->

<!--      负责分配的业务员-->
      <el-select v-model="queryParams.salesmanID" style="margin: 0px 60px 0px 60px" label="负责分配的业务员" placeholder="请选择负责分配的业务员">
        <el-option
          v-for="item in salesmanList"
          :key="item.userId"
          :label="item.nickName"
          :value="item.userId">
          <span style="float: left">{{ item.nickName }}</span>
          <span style="float: right; color: #8492a6; font-size: 13px">{{ item.deptName }}</span>
        </el-option>
      </el-select>


<!--      <el-form-item label="操作员" prop="operatorID">-->
<!--        <el-input-->
<!--          v-model="queryParams.operatorID"-->
<!--          placeholder="请输入操作员姓名"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->

      <!--      操作员-->
      <el-select v-model="queryParams.operatorID" style="margin: 0px 60px 0px 60px" size="medium" placeholder="请选择操作员">
        <el-option
          v-for="item in managerList"
          :key="item.userId"
          :label="item.nickName"
          :value="item.userId">
          <span style="float: left">{{ item.nickName }}</span>
          <span style="float: right; color: #8492a6; font-size: 13px">{{ item.roleName }}</span>
        </el-option>
      </el-select>

      <el-form-item label="分配时间" prop="operationTime">
        <el-date-picker clearable size="small"
          v-model="queryParams.operationTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择分配时间">
        </el-date-picker>
      </el-form-item>
<!--      <el-form-item label="创建时间" prop="createTime">-->
<!--        <el-date-picker clearable size="small"-->
<!--          v-model="queryParams.createTime"-->
<!--          type="date"-->
<!--          value-format="yyyy-MM-dd"-->
<!--          placeholder="选择创建时间">-->
<!--        </el-date-picker>-->
<!--      </el-form-item>-->
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
<!--          v-hasPermi="['detection:taskListAssign:add']"-->
<!--        >新增</el-button>-->
<!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['detection:taskListAssign:edit']"
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
          v-hasPermi="['detection:taskListAssign:remove']"
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
          v-hasPermi="['detection:taskListAssign:export']"
        >导出</el-button>
      </el-col>

      <el-col :span="1.5" style="margin-left: 30%">
        <el-radio-group v-model="radio" @change="radioChangeHandler">
          <el-radio label="1">未分配任务</el-radio>
          <el-radio label="2">已分配任务</el-radio>
          <el-radio label="3">全部任务</el-radio>
        </el-radio-group>

      </el-col>

      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>



    <el-table v-loading="loading" :data="taskListAssignList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="委托单编号" align="center" prop="sampleNumber" width="150"/>
      <el-table-column label="负责分配的业务员" align="center" prop="salesmanName" />
      <el-table-column label="操作员" align="center" prop="operatorName"/>
<!--          <template slot-scope="scope">-->
<!--            <el-select v-model="scope.row.proJbruserValue"  @focus="getDatalist(scope.row)" placeholder="请选择" filterable allow-create>-->
<!--              <el-option v-for="item in projectJbrUserlist " :key="item.id" :label="item.username" :value="item.id">-->
<!--              </el-option>-->
<!--            </el-select>-->
<!--          </template>-->



      <el-table-column label="分配时间" align="center" prop="operationTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.operationTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
<!--      <el-table-column label="创建时间" align="center" prop="createTime" width="180">-->
<!--        <template slot-scope="scope">-->
<!--          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>-->
<!--        </template>-->
<!--      </el-table-column>-->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['detection:taskListAssign:edit']"
          >分配</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['detection:taskListAssign:remove']"
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

    <!-- 添加或修改任务单分配对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item v-model="form.salesmanID" label=" " prop="submitSalesmanID">
          <el-select style="margin: 0px 60px 0px 60px" label="负责分配的业务员" placeholder="请选择负责分配的业务员">
            <el-option
              v-for="item in salesmanList"
              :key="item.userId"
              :label="item.nickName"
              :value="item.userId">
              <span style="float: left">{{ item.nickName }}</span>
              <span style="float: right; color: #8492a6; font-size: 13px">{{ item.deptName }}</span>
            </el-option>
          </el-select>
        </el-form-item>




<!--        <el-form-item label="操作员ID" prop="operatorID">-->
<!--          <el-input v-model="form.operatorID" placeholder="请输入操作员ID" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="操作时间" prop="operationTime">-->
<!--          <el-date-picker clearable size="small"-->
<!--            v-model="form.operationTime"-->
<!--            type="date"-->
<!--            value-format="yyyy-MM-dd"-->
<!--            placeholder="选择操作时间">-->
<!--          </el-date-picker>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="创建时间" prop="createTime">-->
<!--          <el-date-picker clearable size="small"-->
<!--            v-model="form.createTime"-->
<!--            type="date"-->
<!--            value-format="yyyy-MM-dd"-->
<!--            placeholder="选择创建时间">-->
<!--          </el-date-picker>-->
<!--        </el-form-item>-->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  getTaskListAssign,
  delTaskListAssign,
  addTaskListAssign,
  updateTaskListAssign,
  exportTaskListAssign,
  listSalesmanList, listTaskListAssign
} from "@/api/detection/taskListAssign";

export default {
  name: "TaskListAssign",
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
      // 任务单分配表格数据
      taskListAssignList: [],
      // 业务员列表数据
      salesmanList: [],
      //业务管理员列表数据
      managerList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        salesmanID: null,
        operatorID: null,
        operationTime: null,
        createTime: null,
      },
      // 表单参数
      form: {
        salesmanID: null,
      },
      // 单选框，用于查看已分发和未分发任务单
      radio: '1',
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
    this.getStaffList();
  },
  methods: {
    /** 查询任务单分配列表 */
    getList() {
      this.loading = true;
      this.queryParams.require = this.radio
      // if (this.radio === '1'){
      //   listTaskListAssign1(this.queryParams).then(response => {
      //     this.taskListAssignList = response.rows;
      //     this.total = response.total;
      //     this.loading = false;
      //   });
      // } else if (this.radio === '2') {
      //   listTaskListAssign2(this.queryParams).then(response => {
      //     this.taskListAssignList = response.rows;
      //     this.total = response.total;
      //     this.loading = false;
      //   });
      // }
      listTaskListAssign(this.queryParams).then(response => {
        this.taskListAssignList = response.data.rows;
        this.total = response.data.total;
        this.loading = false;
      });
      console.log(this.taskListAssignList);
    },

    /** 监听单选框变化，更新表格 */
    radioChangeHandler(val){
      this.getList();
    },


    /** 查询业务员列表 */
    getStaffList(){
      this.loading = true;
      listSalesmanList().then(response => {
        this.salesmanList = response.data[0];
        this.managerList = response.data[1];
        //this.total = response.total;
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
        salesmanID: null,
        operatorID: null,
        operationTime: null,
        createTime: null
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
      this.title = "添加任务单分配";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const sampleID = row.sampleID || this.ids
      getTaskListAssign(sampleID).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改任务单分配";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.sampleID != null) {
            updateTaskListAssign(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTaskListAssign(this.form).then(response => {
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
      this.$confirm('是否确认删除任务单分配编号为"' + sampleIDs + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delTaskListAssign(sampleIDs);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有任务单分配数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.exportLoading = true;
          return exportTaskListAssign(queryParams);
        }).then(response => {
          this.download(response.msg);
          this.exportLoading = false;
        }).catch(() => {});
    }
  }
};
</script>
