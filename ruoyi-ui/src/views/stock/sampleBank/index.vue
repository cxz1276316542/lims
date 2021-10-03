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

      <el-form-item label="检品学名" prop="sampleScientificName">
        <el-select v-model="queryParams.sampleScientificName" placeholder="请选择" clearable filterable>
          <el-option
            v-for="item in sampleScientificNameOptions"
            :key="item"
            :label="item"
            :value="item">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="检品库地点" prop="sampleAddress">
        <el-input
          v-model="queryParams.sampleAddress"
          placeholder="请输入检品库地点"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="检品位置" prop="sampleLocation">
        <el-input
          v-model="queryParams.sampleLocation"
          placeholder="请输入检品位置"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

<!--      <el-form-item label="进库时间" prop="storageTime">-->
<!--        <el-date-picker clearable size="small"-->
<!--          v-model="queryParams.storageTime"-->
<!--          type="date"-->
<!--          value-format="yyyy-MM-dd"-->
<!--          placeholder="选择进库时间">-->
<!--        </el-date-picker>-->
<!--      </el-form-item>-->

      <el-form-item label="进库时间" prop="stockInTime">
        <el-date-picker clearable size="small"
                        v-model="operationTimeList"
                        type="daterange"
                        value-format="yyyy-MM-dd"
                        range-separator="至"
                        start-placeholder="开始日期"
                        end-placeholder="结束日期">
        </el-date-picker>
      </el-form-item>

      <el-form-item label="保质截止时间" prop="qualityGuaranteePeriod">
        <el-date-picker clearable size="small"
          v-model="queryParams.qualityGuaranteePeriod"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择保质截止时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="接收人" prop="operatorID">
        <el-select v-model="queryParams.operatorID" placeholder="请选择" clearable filterable>
          <el-option
            v-for="(value,key) in receiverList"
            :key="key"
            :label="value"
            :value="key">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="送库人" prop="deliverID">
        <el-select v-model="queryParams.deliverID" placeholder="请选择" clearable filterable>
          <el-option
            v-for="(value,key) in receiverList"
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
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['detection:sampleBank:add']"
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
<!--          v-hasPermi="['detection:sampleBank:edit']"-->
<!--        >修改</el-button>-->
<!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="batchAbandon"
          v-hasPermi="['detection:sampleBank:remove']"
        >销毁</el-button>
      </el-col>
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
<!--      <el-table-column label="检品库ID" align="center" prop="sampleBankID" />-->
      <el-table-column label="检品编号" align="center" prop="sampleNumber" />
      <el-table-column label="检品学名" align="center" prop="sampleScientificName" />
      <el-table-column label="检品库地点" align="center" prop="sampleAddress" />
      <el-table-column label="检品位置" align="center" prop="sampleLocation" />
      <el-table-column label="检品剩余数量" align="center" prop="remainQuantity" />
      <el-table-column label="检品总数量" align="center" prop="totalQuantity" />
      <el-table-column label="检品计量单位" align="center" prop="measureUnit" />
      <el-table-column label="进库时间" align="center" prop="storageTime">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.storageTime, '{y}-{m}-{d} {h}:{i}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="保质截止时间" align="center" prop="qualityGuaranteePeriod">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.qualityGuaranteePeriod, '{y}-{m}-{d}') }}</span>
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
      <el-table-column label="接收人" align="center" prop="operatorName" />
      <el-table-column label="送库人" align="center" prop="deliverName" />
      <el-table-column label="状态" align="center" prop="warning" >
        <template slot-scope="scope">
          <el-tag size="mini" type="success" v-if="scope.row.warning == 0">正常</el-tag>
          <el-tag size="mini" type="warning" v-else-if="scope.row.warning == 1">即将到期</el-tag>
          <el-tag size="mini" type="danger" v-else-if="scope.row.warning == 2">已过期</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleEdit(scope.row)"
            v-hasPermi="['detection:sampleBank:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUse(scope.row)"
            v-hasPermi="['detection:sampleBank:edit']"
          >领用</el-button>

          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleRecycle(scope.row)"
            v-hasPermi="['detection:sampleBank:edit']"
          >归还</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleAbandon(scope.row)"
            v-hasPermi="['detection:sampleBank:remove']"
          >销毁</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleReturn(scope.row)"
            v-hasPermi="['detection:sampleBank:remove']"
          >退样</el-button>

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

    <!-- 修改检品库对话框 -->
    <el-dialog :title="title" :visible.sync="editOpen" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="检品编号" prop="sampleNumber">
          <el-input v-model="form.sampleNumber" placeholder="" disabled/>
        </el-form-item>
        <el-form-item label="检品库地点" prop="sampleAddress">
          <el-input v-model="form.sampleAddress" placeholder="请输入检品库地点" />
        </el-form-item>
        <el-form-item label="检品位置" prop="sampleLocation">
          <el-input v-model="form.sampleLocation" placeholder="请输入检品位置" />
        </el-form-item>
        <el-form-item label="检品剩余数量" prop="remainQuantity">
          <el-input v-model="form.remainQuantity" placeholder="请输入检品剩余数量" disabled />
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
        <el-form-item label="进库时间" prop="storageTime">
          <el-input v-model="form.storageTime" placeholder="" disabled />
<!--          <el-date-picker clearable size="small"-->
<!--            v-model="form.storageTime"-->
<!--            type="date"-->
<!--            value-format="yyyy-MM-dd"-->
<!--            placeholder="选择进库时间">-->
<!--          </el-date-picker>-->
        </el-form-item>
        <el-form-item label="保质截止时间" prop="qualityGuaranteePeriod">
          <el-date-picker clearable size="small"
            v-model="form.qualityGuaranteePeriod"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择保质截止时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="请选择送库人" prop="deliverID">
          <el-select v-model="form.deliverID" placeholder="请选择送库人" clearable filterable size="small">
            <el-option
              v-for="(value,key) in receiverList"
              :key="key"
              :label="value"
              :value="key">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="updateForm">确 定</el-button>
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

    <!-- 销毁检品库对话框 -->
    <el-dialog :title="title" :visible.sync="abandonOpen" width="500px" append-to-body>
      <el-form ref="form" :model="abandonForm" :rules="rules2" label-width="80px">
        <el-form-item label="检品编号" prop="sampleNumber">
          <el-input v-model="abandonForm.sampleNumber" placeholder="检品编号" disabled />
        </el-form-item>
        <el-form-item label="销毁方法" prop="destroyMethod">
          <el-input v-model="abandonForm.destroyMethod" placeholder="请输入销毁方法" />
        </el-form-item>
        <el-form-item label="销毁说明" prop="sampleFlow">
          <el-input v-model="abandonForm.sampleFlow" placeholder="请输入销毁说明" />
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitAbandonForm">确 定</el-button>
        <el-button @click="reset2">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 退样对话框 -->
    <el-dialog :title="title" :visible.sync="returnOpen" width="500px" append-to-body>
      <el-form ref="form" :model="returnForm" :rules="rules2" label-width="80px">
        <el-form-item label="检品编号" prop="sampleNumber">
          <el-input v-model="returnForm.sampleNumber" placeholder="检品编号" disabled />
        </el-form-item>

        <el-form-item label="接收人" prop="receiverID">
          <el-select v-model="returnForm.receiverID" placeholder="请选择" clearable filterable>
            <el-option
              v-for="(value,key) in receiverList"
              :key="key"
              :label="value"
              :value="key">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="退样说明" prop="sampleFlow">
          <el-input v-model="returnForm.sampleFlow" placeholder="请输入销毁说明" />
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitReturnForm">确 定</el-button>
        <el-button @click="reset4">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 回收检品库对话框 -->
    <el-dialog :title="title" :visible.sync="recycleOpen" width="500px" append-to-body>
      <el-form ref="form" :model="recycleForm" :rules="rules3" label-width="80px">
        <el-form-item label="检品编号" prop="sampleNumber">
          <el-input v-model="recycleForm.sampleNumber" placeholder="检品编号" disabled />
        </el-form-item>
        <el-form-item label="计量单位" prop="measureUnit">
          <el-input v-model="recycleForm.measureUnit" disabled/>
        </el-form-item>
        <el-form-item label="回收数量" prop="sampleQuantity">
          <el-input v-model="recycleForm.sampleQuantity" placeholder="请输入回收数量" />
        </el-form-item>
        <el-form-item label="送库人" prop="receiverID">
          <el-select v-model="recycleForm.receiverID" placeholder="请选择送库人" clearable filterable>
            <el-option
              v-for="(value,key) in receiverList"
              :key="key"
              :label="value"
              :value="key">
            </el-option>
          </el-select>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitRecycleForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 回收检品库对话框 -->
    <el-dialog :title="title" :visible.sync="useOpen" width="500px" append-to-body>
      <el-form ref="form" :model="recycleForm" :rules="rules3" label-width="80px">
        <el-form-item label="检品编号" prop="sampleNumber">
          <el-input v-model="recycleForm.sampleNumber" placeholder="检品编号" disabled />
        </el-form-item>
        <el-form-item label="计量单位" prop="measureUnit">
          <el-input v-model="recycleForm.measureUnit" disabled/>
        </el-form-item>
        <el-form-item label="领用数量" prop="sampleQuantity">
          <el-input v-model="recycleForm.sampleQuantity" placeholder="请输入回收数量" />
        </el-form-item>
        <el-form-item label="领用人" prop="receiverID">
          <el-select v-model="recycleForm.receiverID" placeholder="请选择领用人" clearable filterable>
            <el-option
              v-for="(value,key) in receiverList"
              :key="key"
              :label="value"
              :value="key">
            </el-option>
          </el-select>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitUseForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 检品详情对话框 -->
    <el-dialog :title="title"  :visible.sync="detailOpen" width="500px" append-to-body>
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


  </div>
</template>

<script>
import {
  listSampleBank,
  getSampleBank,
  delSampleBank,
  addSampleBank,
  updateSampleBank,
  exportSampleBank,
  getSampleScientificNameList,
  getInventoryManagerList,
  recycleSampleBank,
  getMeasureUnit,
  returnSampleBank,
  batchAbandon, sampleBankInfo, useSampleBank
} from "@/api/stock/sampleBank";
import {abandonSampleBank} from "@/api/stock/abandon";
import {addStockIn} from "@/api/stock/stcokIn";
import {assignInfo} from "@/api/review/assignReview";

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
      // 检品库表格数据
      sampleBankList: [],
      //检品销毁框
      abandonOpen: false,
      //退样框
      returnOpen: false,
      //检品回收框
      recycleOpen: false,
      //领用框
      useOpen:false,
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      //检品详细对话框
      detailOpen:false,
      //计量单位列表
      measureUnitList: null,
      //检品学名选项
      sampleScientificNameOptions: null,
      //入库员列表
      operatorList: null,
      //送库人列表
      receiverList: null,
      //选中样品库信息
      sampleBankInfo: null,
      //修改框
      editOpen: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        sampleID: null,
        sampleAddress: null,
        sampleLocation: null,
        remainQuantity: null,
        totalQuantity: null,
        measureUnit: null,
        storageTime: null,
        qualityGuaranteePeriod: null,
        operatorID: null,
        status: null
      },
      // 表单参数
      form: {},
      //销毁表单参数
      abandonForm: {},
      // 操作时间list
      operationTimeList: '',
      //退样表单
      returnForm:{},
      //回收表单参数
      recycleForm: {},
      //销毁表单规则
      rules2:{
        destroyMethod:[
          { required: true, message: "销毁方法不能为空", trigger: "blur" }
        ],
        sampleFlow:[
          { required: true, message: "销毁说明不能为空", trigger: "blur"}
        ]
      },
      //回收表单规则
      rules3:{
        sampleQuantity:[
          { required: true, message: "回收数量不能为空", trigger: "blur" },
          { validator: this.validateNumber,}
        ],
        receiverID:[
          { required: true, message: "请选择送库人", trigger: "blur" },
        ]

      },

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
    //判断是否为数字
    validateNumber(rule, value, callback) {
      if (value === '') {
        return callback();
      }  //这是用来判断如果不是必须输入的，则直接跳出
      const r = /^\d+$|^\d+[.]?\d+$/; // 正整数
      // 如果判断不符合正则，则不是正整数不能提交
      if (!r.test(value)) {
        return callback(new Error('请输入数字'));
      } else {
        return callback();
      }
    },

    /** 查询检品库列表 */
    getList() {
      this.loading = true;
      if(this.operationTimeList != null && this.operationTimeList.length > 0){
        this.queryParams.storageStartTime = this.operationTimeList[0]
        this.queryParams.storageEndTime = this.operationTimeList[1]
      }else {
        this.queryParams.storageStartTime = null
        this.queryParams.storageEndTime = null
      }
      listSampleBank(this.queryParams).then(response => {
        this.sampleBankList = response.rows;
        // for(let i in this.sampleBankList){
        //   if(this.sampleBankList[i].warning === 0){
        //     this.sampleBankList[i].warning = '正常'
        //   }else if(this.sampleBankList[i].warning === 1){
        //     this.sampleBankList[i].warning = '即将到期'
        //   }else if(this.sampleBankList[i].warning === 2){
        //     this.sampleBankList[i].warning = '已过期'
        //   }
        // }
        this.total = response.total;
        this.loading = false;
        this.reset();
      });
      console.log(this.sampleBankList);
      console.log(this.total);
    },

    /** 检品详情按钮操作 */
    handleSearch(row) {
      this.reset();
      const sampleBankID = row.sampleBankID
      sampleBankInfo(sampleBankID).then(response => {
        this.form = response.data;
        // this.form.sampleID = sampleID
        this.detailOpen = true;
        this.title = "检品详情";
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.editOpen = false;
      this.recycleOpen = false;
      this.detailOpen = false;
      this.useOpen = false;

      this.reset();
      this.reset2();
      this.reset3();
    },
    // 表单重置
    reset() {
      this.form = {
        sampleBankID: null,
        sampleBankNumber: null,
        sampleID: null,
        sampleAddress: null,
        sampleLocation: null,
        remainQuantity: null,
        totalQuantity: null,
        measureUnit: null,
        storageTime: null,
        qualityGuaranteePeriod: null,
        operatorID: null,
        status: null
      };
      this.resetForm("form");
    },

    // 废弃表单重置
    reset2() {
      this.abandonOpen = false;
      this.abandonForm = {
        sampleBankID: null,
        sampleID: null,
        sampleNumber:null,
      };
      this.resetForm("abandonForm");
    },
    // 回收表单重置
    reset3(){
      this.recycleForm = {
        sampleBankID: null,
        sampleID: null,
        sampleNumber:null,
        measureUnit:null,
        sampleQuantity:null,
      };
    },

    // 退样表单重置
    reset4(){
      this.returnOpen = false;
      this.resetForm("returnForm");
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
      this.ids = selection.map(item => item.sampleBankID)
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
          // if (this.form.sampleBankID != null) {
          //   updateSampleBank(this.form).then(response => {
          //     this.msgSuccess("修改成功");
          //     this.open = false;
          //     this.getList();
          //   });
          // } else {
          //   addSampleBank(this.form).then(response => {
          //     this.msgSuccess("新增成功");
          //     this.open = false;
          //     this.getList();
          //   });
          // }
          addStockIn(this.form).then(response => {
            this.msgSuccess("操作成功");
            this.open = false;
            this.getList();
          });
        }
      });
    },
    /** 修改提交按钮 */
    updateForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          updateSampleBank(this.form).then(response => {
            this.msgSuccess("操作成功");
            this.editOpen = false;
            this.getList();
          });
        }
      });
    },

    /** 销毁提交按钮 */
    submitAbandonForm() {
        abandonSampleBank(this.abandonForm).then(response => {
          if (response.code === 200){
            this.msgSuccess("操作成功");
          }else {
            this.msgError(response.msg);
          }
          this.abandonOpen = false;
          this.getList();
        })
    },
    /** 退样提交按钮 */
    submitReturnForm() {
      returnSampleBank(this.returnForm).then(response => {
        if (response.code === 200){
          this.msgSuccess("退样操作成功");
        }else {
          this.msgError(response.msg);
        }
        this.returnOpen = false;
        this.getList();
      })
    },

    /** 回收提交按钮 */
    submitRecycleForm() {
      recycleSampleBank(this.recycleForm).then(response => {
        if (response.code === 200){
          this.msgSuccess("操作成功");
        }else {
          this.msgError(response.msg);
        }
        this.recycleOpen = false;
        this.getList();
      })
    },

    /** 领用提交按钮 */
    submitUseForm() {
      useSampleBank(this.recycleForm).then(response => {
        if (response.code === 200){
          this.msgSuccess("操作成功");
        }else {
          this.msgError(response.msg);
        }
        this.useOpen = false;
        this.getList();
      })
    },

    /** 销毁按钮操作 */
    batchAbandon() {
      const sampleBankIDs =  this.ids;
      this.$confirm('是否确认销毁检品库这些库存?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return batchAbandon(sampleBankIDs);
        }).then(() => {
          this.getList();
          this.msgSuccess("销毁成功");
        }).catch(() => {});
    },

    /** 销毁按钮操作 */
    handleAbandon(row) {
      this.reset();
      this.abandonForm.sampleID = row.sampleID;
      this.abandonForm.sampleNumber = row.sampleNumber;
      this.abandonForm.sampleBankID = row.sampleBankID;
      this.abandonOpen = true;
      this.title = "销毁申请";
    },

    /** 退样按钮操作 */
    handleReturn(row) {
      this.reset();
      this.returnForm.sampleID = row.sampleID;
      this.returnForm.sampleNumber = row.sampleNumber;
      this.returnForm.sampleBankID = row.sampleBankID;
      this.returnOpen = true;
      this.title = "退样申请";
    },

    /** 获取选中样品库的信息 */
    handleEdit(row) {
      sampleBankInfo(row.sampleBankID).then(res=>{
          this.form = res.data
        })
        this.editOpen = true;
    },

    /** 回收按钮操作 */
    handleRecycle(row) {
      this.reset();
      this.recycleForm.sampleID = row.sampleID;
      this.recycleForm.sampleNumber = row.sampleNumber;
      this.recycleForm.sampleBankID = row.sampleBankID;
      this.recycleForm.measureUnit = row.measureUnit;
      this.recycleOpen = true;
      this.title = "回收检品";
    },

    /** 领用按钮操作 */
    handleUse(row) {
      this.reset();
      this.recycleForm.sampleID = row.sampleID;
      this.recycleForm.sampleNumber = row.sampleNumber;
      this.recycleForm.sampleBankID = row.sampleBankID;
      this.recycleForm.measureUnit = row.measureUnit;
      this.useOpen = true;
      this.title = "领用检品";
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
