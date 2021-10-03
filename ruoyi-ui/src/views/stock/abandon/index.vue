<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="检品编号" prop="sampleNumber">
        <el-input
          v-model="queryParams.sampleNumber"
          placeholder="请输入检品编号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="检品库地点" prop="sampleAddress">-->
<!--        <el-input-->
<!--          v-model="queryParams.sampleAddress"-->
<!--          placeholder="请输入检品库地点"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="检品位置" prop="sampleLocation">-->
<!--        <el-input-->
<!--          v-model="queryParams.sampleLocation"-->
<!--          placeholder="请输入检品位置"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->

<!--      <el-form-item label="进库时间" prop="storageTime">-->
<!--        <el-date-picker clearable size="small"-->
<!--                        v-model="queryParams.storageTime"-->
<!--                        type="date"-->
<!--                        value-format="yyyy-MM-dd"-->
<!--                        placeholder="选择进库时间">-->
<!--        </el-date-picker>-->
<!--      </el-form-item>-->

      <el-form-item label="检品学名" prop="sampleScientificName">
        <el-select v-model="queryParams.sampleScientificName" placeholder="请输入检品学名" clearable filterable size="small">
          <el-option
            v-for="item in sampleScientificNameOptions"
            :key="item"
            :label="item"
            :value="item">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="销毁时间" prop="stockInTime">
        <el-date-picker clearable size="small"
                        v-model="operationTimeList"
                        type="daterange"
                        value-format="yyyy-MM-dd"
                        range-separator="至"
                        start-placeholder="开始日期"
                        end-placeholder="结束日期">
        </el-date-picker>
      </el-form-item>

      <el-form-item label="销毁人" prop="operatorID">
        <el-select v-model="queryParams.operatorID" placeholder="请选择" clearable filterable>
          <el-option
            v-for="(value,key) in operatorList"
            :key="key"
            :label="value"
            :value="key">
          </el-option>
        </el-select>
      </el-form-item>
      <!--      <el-form-item label="状态" prop="status">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.status"-->
      <!--          placeholder="请输入状态"-->
      <!--          clearable-->
      <!--          size="small"-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
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
<!--          v-hasPermi="['detection:sampleBank:add']"-->
<!--        >新增</el-button>-->
<!--      </el-col>-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="success"-->
<!--          plain-->
<!--          icon="el-icon-edit"-->
<!--          size="mini"-->
<!--          :disabled="single"-->
<!--          @click="handleUpdate"-->
<!--          v-hasPermi="['detection:sampleBank:edit']"-->
<!--        >修改</el-button>-->
<!--      </el-col>-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="danger"-->
<!--          plain-->
<!--          icon="el-icon-delete"-->
<!--          size="mini"-->
<!--          :disabled="multiple"-->
<!--          @click="handleDelete"-->
<!--          v-hasPermi="['detection:sampleBank:remove']"-->
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
          v-hasPermi="['detection:sampleBank:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="sampleBankList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="检品编号" align="center" prop="sampleNumber" />
      <el-table-column label="检品学名" align="center" prop="sampleScientificName" />
<!--      <el-table-column label="检品库地点" align="center" prop="sampleAddress" />-->
<!--      <el-table-column label="检品位置" align="center" prop="sampleLocation" />-->
<!--      <el-table-column label="检品剩余数量" align="center" prop="remainQuantity" />-->
<!--      <el-table-column label="检品总数量" align="center" prop="totalQuantity" />-->
<!--      <el-table-column label="检品计量单位ID" align="center" prop="measureUnitID" />-->
      <el-table-column label="销毁时间" align="center" prop="operationTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.operationTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
<!--      <el-table-column label="保质截止时间" align="center" prop="qualityGuaranteePeriod" width="180">-->
<!--        <template slot-scope="scope">-->
<!--          <span>{{ parseTime(scope.row.qualityGuaranteePeriod, '{y}-{m}-{d}') }}</span>-->
<!--        </template>-->
<!--      </el-table-column>-->
      <el-table-column label="销毁人" align="center" prop="operatorName" />
<!--      <el-table-column label="状态" align="center" prop="status" />-->
      <el-table-column label="销毁方法" align="center" prop="destroyMethod" />
      <el-table-column label="销毁说明" align="center" prop="sampleFlow" />
      <el-table-column label="详细信息" align="center" class-name="small-padding fixed-width" >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-view"
            @click="handleSearch(scope.row)"
          >查看</el-button>
        </template>
      </el-table-column>
<!--      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">-->
<!--        <template slot-scope="scope">-->
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-edit"-->
<!--            @click="handleUpdate(scope.row)"-->
<!--            v-hasPermi="['detection:sampleBank:edit']"-->
<!--          >修改</el-button>-->
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-delete"-->
<!--            @click="handleDelete(scope.row)"-->
<!--            v-hasPermi="['detection:sampleBank:remove']"-->
<!--          >删除</el-button>-->
<!--        </template>-->
<!--      </el-table-column>-->
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 详细信息展示对话框 -->
    <el-dialog :title="title" :visible.sync="detailOpen" width="500px" append-to-body>
      <el-form ref="form" :model="form"  label-width="120px" class="inputDeep">

        <el-form-item label="检品编号：" prop="sampleNumber">
          <el-input v-model="form.sampleNumber"  size="medium" />
        </el-form-item>

        <el-form-item label="送样人：" prop="sampleSenderName">
          <el-input v-model="form.sampleSenderName"  size="medium" />
        </el-form-item>

        <el-form-item label="检测标准：" prop="detectionStandard">
          <el-input v-model="form.detectionStandard"  size="medium" />
        </el-form-item>

        <el-form-item label="检品名称：" prop="sampleName">
          <el-input v-model="form.sampleName"  size="medium" />
        </el-form-item>

        <el-form-item label="检品学名：" prop="sampleScientificName">
          <el-input v-model="form.sampleScientificName"  size="medium" />
        </el-form-item>

        <el-form-item label="检品状态：" prop="sampleStatus">
          <el-input v-model="form.sampleStatus"  size="medium" />
        </el-form-item>

        <el-form-item label="优先级：" prop="priority">
          <el-input v-model="form.priority"  size="medium" />
        </el-form-item>

        <el-form-item label="检测类别：" prop="detectionCategory">
          <el-input v-model="form.detectionCategory"  size="medium" />
        </el-form-item>

        <el-form-item label="检品数量：" prop="sampleQuantity">
          <el-input v-model="form.sampleQuantity"  size="medium" />
        </el-form-item>

        <el-form-item label="计量单位：" prop="measureUnit">
          <el-input v-model="form.measureUnit"  size="medium" />
        </el-form-item>

        <el-form-item label="检品描述：" prop="sampleDescription">
          <el-input v-model="form.sampleDescription"  size="medium" />
        </el-form-item>

        <el-form-item label="采样方式：" prop="samplingMode">
          <el-input v-model="form.samplingMode"  size="medium" />
        </el-form-item>

        <el-form-item label="抽样基数：" prop="samplingBase">
          <el-input v-model="form.samplingBase"  size="medium" />
        </el-form-item>

        <el-form-item label="生产单位：" prop="productionUnit">
          <el-input v-model="form.productionUnit"  size="medium" />
        </el-form-item>

        <el-form-item label="生产单位地址：" prop="productionUnitAddress">
          <el-input v-model="form.productionUnitAddress"  size="medium" />
        </el-form-item>

        <el-form-item label="采样时间：" prop="samplingTime">
          <el-input v-model="form.samplingTime"  size="medium" />
        </el-form-item>

        <el-form-item label="报告领取方式：" prop="reportReceiveWay">
          <el-input v-model="form.reportReceiveWay"  size="medium" />
        </el-form-item>

        <el-form-item label="回收剩余检品：" prop="recycle">
          <el-input v-model="form.recycle"  size="medium" />
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel">取 消</el-button>
      </div>

    </el-dialog>

    <!-- 添加或修改检品库对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="检品ID" prop="sampleID">
          <el-input v-model="form.sampleID" placeholder="请输入检品ID" />
        </el-form-item>
        <el-form-item label="检品库地点" prop="sampleAddress">
          <el-input v-model="form.sampleAddress" placeholder="请输入检品库地点" />
        </el-form-item>
        <el-form-item label="检品位置" prop="sampleLocation">
          <el-input v-model="form.sampleLocation" placeholder="请输入检品位置" />
        </el-form-item>
        <el-form-item label="检品剩余数量" prop="remainQuantity">
          <el-input v-model="form.remainQuantity" placeholder="请输入检品剩余数量" />
        </el-form-item>
        <el-form-item label="检品总数量" prop="totalQuantity">
          <el-input v-model="form.totalQuantity" placeholder="请输入检品总数量" />
        </el-form-item>
        <el-form-item label="检品计量单位ID" prop="measureUnitID">
          <el-input v-model="form.measureUnitID" placeholder="请输入检品计量单位ID" />
        </el-form-item>
        <el-form-item label="进库时间" prop="storageTime">
          <el-date-picker clearable size="small"
            v-model="form.storageTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择进库时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="保质截止时间" prop="qualityGuaranteePeriod">
          <el-date-picker clearable size="small"
            v-model="form.qualityGuaranteePeriod"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择保质截止时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="接收人ID" prop="receiverID">
          <el-input v-model="form.receiverID" placeholder="请输入接收人ID" />
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-input v-model="form.status" placeholder="请输入状态" />
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
import { listSampleBank, getSampleBank, delSampleBank, exportSampleBank } from "@/api/stock/abandon";
import {getInventoryManagerList, getSampleScientificNameList, sampleBankInfo} from "@/api/stock/sampleBank";

export default {
  name: "SampleBank",
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
      // 操作时间list
      operationTimeList: '',
      // 检品库表格数据
      sampleBankList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      //销毁员列表
      operatorList: [],
      //检品学名选项
      sampleScientificNameOptions: null,
      //详细信息展示框显示
      detailOpen: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        sampleID: null,
        sampleAddress: null,
        sampleLocation: null,
        remainQuantity: null,
        totalQuantity: null,
        measureUnitID: null,
        storageTime: null,
        qualityGuaranteePeriod: null,
        receiverID: null,
        status: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        sampleID: [
          { required: true, message: "检品ID不能为空", trigger: "blur" }
        ],
        sampleAddress: [
          { required: true, message: "检品库地点不能为空", trigger: "blur" }
        ],
        sampleLocation: [
          { required: true, message: "检品位置不能为空", trigger: "blur" }
        ],
        remainQuantity: [
          { required: true, message: "检品剩余数量不能为空", trigger: "blur" }
        ],
        measureUnitID: [
          { required: true, message: "检品计量单位ID不能为空", trigger: "blur" }
        ],
        storageTime: [
          { required: true, message: "进库时间不能为空", trigger: "blur" }
        ],
        qualityGuaranteePeriod: [
          { required: true, message: "保质截止时间不能为空", trigger: "blur" }
        ],
        receiverID: [
          { required: true, message: "接收人ID不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "状态不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
    this.sampleScientificNameList();
    this.inventoryManagerList();
  },
  methods: {
    /** 查询检品库列表 */
    getList() {
      this.loading = true;
      if(this.operationTimeList != null && this.operationTimeList.length > 0){
        this.queryParams.operationStartTime = this.operationTimeList[0]
        this.queryParams.operationEndTime = this.operationTimeList[1]
      }else {
        this.queryParams.operationStartTime = null
        this.queryParams.operationEndTime = null
      }
      listSampleBank(this.queryParams).then(response => {
        this.sampleBankList = response.rows;
        this.total = response.total;
        this.loading = false;
        console.log(this.sampleBankList);
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.detailOpen = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        sampleBankID: null,
        sampleID: null,
        sampleAddress: null,
        sampleLocation: null,
        remainQuantity: null,
        totalQuantity: null,
        measureUnitID: null,
        storageTime: null,
        qualityGuaranteePeriod: null,
        receiverID: null,
        status: null
      };
      this.resetForm("form");
    },
    /** 详情按钮操作 */
    handleSearch(row) {
      this.reset();
      const sampleBankID = row.sampleBankID
      sampleBankInfo(sampleBankID).then(response => {
        this.form = response.data;
        this.detailOpen = true;
        this.title = "检品详情";
      });
    },
    /** 搜索按钮操作 */
    handleQuery() {
      // this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.operationTimeList = "";
      this.queryParams.pageNum = 1;
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.sampleBankID)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加检品库";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const sampleBankID = row.sampleBankID || this.ids
      getSampleBank(sampleBankID).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改检品库";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.sampleBankID != null) {
            updateSampleBank(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addSampleBank(this.form).then(response => {
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
      const sampleBankIDs = row.sampleBankID || this.ids;
      this.$confirm('是否确认删除检品库编号为"' + sampleBankIDs + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delSampleBank(sampleBankIDs);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有检品库数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.exportLoading = true;
          return exportSampleBank(queryParams);
        }).then(response => {
          this.download(response.msg);
          this.exportLoading = false;
        }).catch(() => {});
    },

    /** 获取检品学名选项 */
    sampleScientificNameList(){
      getSampleScientificNameList().then(res=>{
        this.sampleScientificNameOptions = res.data
      })
    },

    /** 获取人员信息（库管员、领用/送样人） */
    inventoryManagerList(){
      getInventoryManagerList().then(res=>{
        this.operatorList = res.data[0];
        this.receiverList = res.data[1];
        console.log(this.receiverList);
      })
    },
  }
};
</script>
