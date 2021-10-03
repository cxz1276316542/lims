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

      <el-form-item label="操作员" prop="operatorID">
        <el-select v-model="queryParams.operatorID" placeholder="请选择操作员" clearable filterable size="small">
          <el-option
            v-for="(value,key) in operatorList"
            :key="key"
            :label="value"
            :value="key">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="检品学名" prop="sampleScientificName">
        <el-select v-model="queryParams.sampleScientificName" placeholder="请选择检品学名" clearable filterable size="small">
          <el-option
            v-for="item in sampleScientificNameOptions"
            :key="item"
            :label="item"
            :value="item">
          </el-option>
        </el-select>
      </el-form-item>

<!--      <el-form-item label="入库时间" prop="operationTime">-->
<!--        <el-date-picker clearable size="small"-->
<!--          v-model="queryParams.operationTime"-->
<!--          type="date"-->
<!--          value-format="yyyy-MM-dd"-->
<!--          placeholder="选择入库时间">-->
<!--        </el-date-picker>-->
<!--      </el-form-item>-->

      <el-form-item label="送库人" prop="receiverID">
        <el-select v-model="queryParams.receiverID" placeholder="请选择送库人" clearable filterable size="small">
          <el-option
            v-for="(value,key) in receiverList"
            :key="key"
            :label="value"
            :value="key">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="入库类型" prop="entryAndOutType">
        <el-select v-model="queryParams.entryAndOutType" placeholder="请选出库类型" clearable filterable size="small">
          <el-option
            v-for="item in entryAndOutTypeOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="入库时间" prop="stockInTime">
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

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['detection:stockIn:add']"
        >入库</el-button>
      </el-col>
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="success"-->
<!--          plain-->
<!--          icon="el-icon-edit"-->
<!--          size="mini"-->
<!--          :disabled="single"-->
<!--          @click="handleUpdate"-->
<!--          v-hasPermi="['detection:stockIn:edit']"-->
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
<!--          v-hasPermi="['detection:stockIn:remove']"-->
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
          v-hasPermi="['detection:stockIn:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="stockInList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="检品编号" align="center" prop="sampleNumber" />
      <el-table-column label="检品学名" align="center" prop="sampleScientificName" />
      <el-table-column label="入库类型" align="center" prop="entryAndOutType" >
        <template slot-scope="scope">
          <el-tag size="mini" type="success" v-if="scope.row.entryAndOutType == 1">登记入库</el-tag>
          <el-tag size="mini" type="warning" v-else-if="scope.row.entryAndOutType == 2">回收</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="送库人" align="center" prop="receiverName" />
      <el-table-column label="入库检品数量" align="center" prop="sampleQuantity" />
      <el-table-column label="操作员" align="center" prop="operatorName" />
      <el-table-column label="入库时间" align="center" prop="operationTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.operationTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>

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
<!--            v-hasPermi="['detection:stockIn:edit']"-->
<!--          >修改</el-button>-->
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-delete"-->
<!--            @click="handleDelete(scope.row)"-->
<!--            v-hasPermi="['detection:stockIn:remove']"-->
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

    <!-- 添加或修改检品出入库对话框 -->
<!--    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>-->
<!--      <el-form ref="form" :model="form" :rules="rules" label-width="80px">-->
<!--        <el-form-item label="检品编号" prop="sampleNumber">-->
<!--          <el-input v-model="form.sampleID" placeholder="请输入检品编号" />-->
<!--        </el-form-item>-->

<!--        <el-form-item label="入库类型" prop="stockInCategory">-->
<!--          <el-input v-model="form.stockInCategory" placeholder="请输入出入库类别" />-->
<!--        </el-form-item>-->

<!--        <el-form-item label="入检品数量" prop="sampleQuantity">-->
<!--          <el-input v-model="form.sampleQuantity" placeholder="请输入检品数量" />-->
<!--        </el-form-item>-->

<!--        <el-form-item label="请选择送库人" prop="receiverID">-->
<!--          <el-select v-model="queryParams.receiverID" placeholder="请选择送库人" clearable filterable size="small">-->
<!--            <el-option-->
<!--              v-for="(value,key) in receiverList"-->
<!--              :key="key"-->
<!--              :label="value"-->
<!--              :value="key">-->
<!--            </el-option>-->
<!--          </el-select>-->
<!--        </el-form-item>-->
<!--      </el-form>-->
<!--      <div slot="footer" class="dialog-footer">-->
<!--        <el-button type="primary" @click="submitForm">确 定</el-button>-->
<!--        <el-button @click="cancel">取 消</el-button>-->
<!--      </div>-->
<!--    </el-dialog>-->

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

    <!-- 添加入库对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="550px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="检品编号" prop="sampleNumber">
          <el-input v-model="form.sampleNumber" placeholder="请输入检品编号" />
        </el-form-item>

        <el-form-item label="检品库地点" prop="sampleAddress">
          <el-input v-model="form.sampleAddress" placeholder="请输入检品库地点" />
        </el-form-item>
        <el-form-item label="检品位置" prop="sampleLocation">
          <el-input v-model="form.sampleLocation" placeholder="请输入检品位置" />
        </el-form-item>

        <el-form-item label="检品总数量" prop="totalQuantity">
          <el-input v-model="form.totalQuantity" placeholder="请输入检品总数量" />
        </el-form-item>

        <el-form-item label="检品计量单位" prop="measureUnit">
          <el-select v-model="form.measureUnit" placeholder="请选择检品计量单位" clearable filterable>
            <el-option
              v-for="item in measureUnitList"
              :key="item"
              :label="item"
              :value="item">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="请选择送库人" prop="receiverID">
          <el-select v-model="form.deliverID" placeholder="请选择送库人" clearable filterable size="small">
            <el-option
              v-for="(value,key) in receiverList"
              :key="key"
              :label="value"
              :value="key">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="保质截止时间" prop="qualityGuaranteePeriod">
          <el-date-picker clearable size="small"
                          v-model="form.qualityGuaranteePeriod"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择保质截止时间">
          </el-date-picker>
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
import { listStockIn, getStockIn, delStockIn, addStockIn, updateStockIn, exportStockIn} from "@/api/stock/stcokIn";
import {
  getInventoryManagerList,
  getMeasureUnit,
  getSampleScientificNameList,
  sampleBankInfo
} from "@/api/stock/sampleBank"
export default {
  name: "StockIn",
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
      // 检品出入库表格数据
      stockInList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 操作时间list
      operationTimeList: '',
      //检品学名选项
      sampleScientificNameOptions: null,
      //入库员列表
      operatorList: null,
      //计量单位列表
      measureUnitList: null,
      //送库人列表
      receiverList: null,
      //详细信息
      detail: {},
      //详细信息展示框显示
      detailOpen: false,
      //出入库类型
      entryAndOutTypeOptions: [
        { value: 1,
          label: "登记入库"
        },
        { value: 2,
          label: "回收"
        }
      ],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        receiverID: null,
        sampleNumber: null,
        sampleID: null,
        sampleBankID: null,
        stockInCategory: null,
        sampleQuantity: null,
        operatorID: null,
        operationTime: null,
        sampleScientificName: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        sampleNumber: [
          { required: true, message: "检品编号不能为空", trigger: "blur" }
        ],
        deliverID: [
          { required: true, message: "送库人不能为空", trigger: "blur" }
        ],
        sampleAddress: [
          { required: true, message: "检品库地点不能为空", trigger: "blur" }
        ],
        sampleLocation: [
          { required: true, message: "检品位置不能为空", trigger: "blur" }
        ],
        totalQuantity: [
          { required: true, message: "检品总数量不能为空", trigger: "blur" }
        ],
        measureUnit: [
          { required: true, message: "检品计量单位不能为空", trigger: "blur" }
        ],
        qualityGuaranteePeriod: [
          { required: true, message: "保质截止时间不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.sampleScientificNameList();
    this.inventoryManagerList();
    this.getMeasureUnitList();
  },
  methods: {
    /** 查询检品出入库列表 */
    getList() {
      this.loading = true;
      if(this.operationTimeList != null && this.operationTimeList.length > 0){
        this.queryParams.entryAndOutStartTime = this.operationTimeList[0]
        this.queryParams.entryAndOutEndTime = this.operationTimeList[1]
      }else {
        this.queryParams.entryAndOutStartTime = null
        this.queryParams.entryAndOutEndTime = null
      }
      listStockIn(this.queryParams).then(response => {
        this.stockInList = response.rows;
        // for(let i in this.stockInList){
        //   if(this.stockInList[i].entryAndOutType === 1){
        //     this.stockInList[i].entryAndOutType = '登记入库'
        //   }else if(this.stockInList[i].entryAndOutType === 2){
        //     this.stockInList[i].entryAndOutType = '回收'
        //   }
        // }
        this.total = response.total;
        this.loading = false;
        console.log(this.stockInList)
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
        stockInID: null,
        sampleID: null,
        sampleBankID: null,
        stockInCategory: null,
        sampleQuantity: null,
        operatorID: null,
        operationTime: null
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
      this.operationTimeList = "";
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.stockInID)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加检品入库";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const stockInID = row.stockInID || this.ids
      getStockIn(stockInID).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改检品出入库";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          // if (this.form.stockInID != null) {
          //   updateStockIn(this.form).then(response => {
          //     this.msgSuccess("入库成功");
          //     this.open = false;
          //     this.getList();
          //   });
          // }
            addStockIn(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const stockInIDs = row.stockInID || this.ids;
      this.$confirm('是否确认删除检品出入库编号为"' + stockInIDs + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delStockIn(stockInIDs);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有检品出入库数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.exportLoading = true;
          return exportStockIn(queryParams);
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

    /** 获取人员信息（库管员、领用/送样人） */
    inventoryManagerList(){
      getInventoryManagerList().then(res=>{
        this.operatorList = res.data[0];
        this.receiverList = res.data[1];
      })
    },

    /** 获取计量单位 */
    getMeasureUnitList(){
      getMeasureUnit().then(res=>{
        this.measureUnitList = res.data;
      })
    },
  }
};
</script>
