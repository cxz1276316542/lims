<template>
  <div class="app-container">
    <el-card>
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="100px">
      <el-form-item label="客户账号" prop="customerNumber">
        <el-input
          v-model="queryParams.customerNumber"
          placeholder="请输入客户账号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="样品编号" prop="sampleNumber">
        <el-input
          v-model="queryParams.sampleNumber"
          placeholder="请输入样品编号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="优先级" prop="priority">
        <el-select
          v-model="queryParams.priority"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        >
          <el-option
                  v-for="item in priorities"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="采样方式" prop="sampleMethod">
        <el-select
          v-model="queryParams.sampleMethod"
          placeholder="请输入采样方式"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        >
          <el-option
                  v-for="item in samplingModes"
                  :key="item.value1"
                  :label="item.label"
                  :value="item.value1">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="检测类别 " prop="detectionType">
        <el-select
          v-model="queryParams.detectionType"
          placeholder="请输入检测类别 "
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        >
          <el-option
                  v-for="item in detectionCategories"
                  :key="item.value2"
                  :label="item.label"
                  :value="item.value2">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="检测标准" prop="detectionStandardID">
        <el-select
          v-model="queryParams.detectionStandardID"
          placeholder="请输入检测标准"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        >
          <el-option v-for="item in detectionStandardOptions"
                     :key="item.detectionStandardID"
                     :label="item.detectionNumber"
                     :value="item.detectionStandardID"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="送样人电话" prop="sendSamplePersonPhone">
        <el-input
          v-model="queryParams.sendSamplePersonPhone"
          placeholder="请输入送样人电话 客户填写或业务员代写"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="送样人名称" prop="sendSamplePersonName">
        <el-input
          v-model="queryParams.sendSamplePersonName"
          placeholder="请输入送样人名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="采样时间" prop="samplingTime">
        <el-date-picker clearable size="small"
                v-model="samplingTimeArray"
                type="daterange"
                value-format="yyyy-MM-dd"
                range-separator="至"
                start-placeholder="开始日期"
                end-placeholder="结束日期">
        </el-date-picker>
      </el-form-item>

      <el-form-item label="检品名称" prop="sampleName">
        <el-input
          v-model="queryParams.sampleName"
          placeholder="请输入检品名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="检品学名" prop="sampleAcademicName">
        <el-select
          v-model="queryParams.sampleAcademicName"
          placeholder="请输入检品学名"
          clearable
          filterable
          size="small"
          @keyup.enter.native="handleQuery"
        >
          <el-option v-for="item in sampleAcademicOptions"
                     :key="item"
                     :value="item"
                     :label="item"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="检品生产单位" prop="productionUnit">
        <el-input
          v-model="queryParams.productionUnit"
          placeholder="请输入检品生产单位"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
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
              @click="toCommissionSample"
              round
      >检品受理</el-button>
    </el-row>
<!--    <el-row :gutter="10" style="height: 60px; margin-top: 30px">
      <el-radio-group v-model="searchSource" @change="getList">
        <el-radio :label="1">委托检品登记</el-radio>
        <el-radio :label="2">检品登记</el-radio>
        <el-radio :label="3">退回修改检品登记</el-radio>
      </el-radio-group>
    </el-row>-->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
		  :loading="exportLoading"
          @click="handleExport"
          v-hasPermi="['detection:commissionSample:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="commissionSampleList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="委托检品ID" align="center" sortable prop="sampleID" v-if="false"/>
      <el-table-column label="客户账号" align="center" sortable prop="customerNumber" />
      <el-table-column label="客户ID" align="center" sortable prop="customerID" v-if="false"/>
      <el-table-column label="单位地址" align="center" prop="companyID" v-if="false"/>
      <el-table-column label="检品编号" align="center" sortable prop="sampleNumber" />
      <el-table-column label="优先级" align="center" sortable prop="priority" >
        <template slot-scope="scope">
          <span v-if="scope.row.priority == 1">正常</span>
          <span v-else-if="scope.row.priority == 2">急</span>
          <span v-else-if="scope.row.priority == 3">加急</span>
          <span v-else-if="scope.row.priority == 4">特急</span>
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" prop="status" >
        <template slot-scope="scope">
          <span v-if="scope.row.status == 1">委托保存</span>
          <span v-if="scope.row.status == 2">委托修改</span>
          <span v-if="scope.row.status == 11">业务保存</span>
          <span v-if="scope.row.status == 12">业务提交</span>
          <span v-if="scope.row.status == 10">退回修改</span>
          <span v-if="scope.row.status == 21">受理审核通过</span>
          <span v-if="scope.row.status == 20">受理审核不通过</span>
        </template>
      </el-table-column>
      <el-table-column label="采样方式" align="center" prop="samplingMode">
        <template slot-scope="scope">
          <span v-if="scope.row.samplingMode == 1">抽检</span>
          <span v-else-if="scope.row.samplingMode == 2">送检</span>
        </template>
      </el-table-column>
      <el-table-column label="检测类别 " align="center" prop="detectionCategory" >
        <template slot-scope="scope">
          <span v-if="scope.row.detectionCategory == 1">委托</span>
          <span v-else-if="scope.row.detectionCategory == 2">仲裁</span>
          <span v-else-if="scope.row.detectionCategory == 3">其他</span>
        </template>
      </el-table-column>
      <el-table-column label="检测标准" align="center" prop="detectionStandardName">
      </el-table-column>
      <el-table-column label="报告领取方式" align="center" prop="reportReceiveWay" >
        <template slot-scope="scope">
          <span v-if="scope.row.reportReceiveWay == 0">邮寄</span>
          <span v-if="scope.row.reportReceiveWay == 1">自取</span>
        </template>
      </el-table-column>
      <el-table-column label="报告邮寄地址" align="center" prop="receiptAddress" />
      <el-table-column label="送样人电话" sortable align="center" prop="sampleSenderPhone" />
      <el-table-column label="送样人名称" sortable align="center" prop="sampleSenderName" />
      <el-table-column label="采样时间" sortable align="center" prop="samplingTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.samplingTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="检品名称" sortable align="center" prop="sampleName" />
      <el-table-column label="检品学名" sortable align="center" prop="sampleScientificName" />
      <el-table-column label="检品状态" sortable align="center" prop="sampleStatus" >
        <template slot-scope="scope">
          <span v-if="scope.row.sampleStatus == 1">固体</span>
          <span v-if="scope.row.sampleStatus == 2">液体</span>
          <span v-if="scope.row.sampleStatus == 3">胶体</span>
          <span v-if="scope.row.sampleStatus == 4">气体</span>
          <span v-if="scope.row.sampleStatus == 5">其他</span>
        </template>
      </el-table-column>
      <el-table-column label="检品数量" sortable align="center" prop="sampleQuantity" />
      <el-table-column label="抽样基数" sortable align="center" prop="samplingBase" />
      <el-table-column label="检品生产单位" align="center" prop="productionUnit" />
      <el-table-column label="生产单位地址" align="center" prop="productionUnitAddress" />
      <el-table-column label="检品描述" align="center" prop="sampleDescriptionArray" />
      <el-table-column label="是否回收剩余检品" align="center" prop="recycle" >
        <template slot-scope="scope">
          <span v-if="scope.row.recycle == '0'">否</span>
          <span v-else-if="scope.row.recycle == '1'">是</span>
        </template>
      </el-table-column>
      <el-table-column label="检品图像地址" align="center" prop="sampleImage" >
        　　<template slot-scope="scope">
        　　　　<el-image :src= "scope.row.sampleImage" width="80" height="80"
                       :preview-src-list="scope.row.sampleImageList"></el-image>
        　　</template>
      </el-table-column>
      <el-table-column label="二维码地址" align="center" prop="QRcodeAddress" />
<!--      <el-table-column label="电子签名地址" align="center" prop="electronicSignature" />-->

<!--      <el-table-column label="标志" align="center" prop="sign" >
        <template slot-scope="scope">
          <span v-if="scope.row.sign == 1">正常</span>
          <span v-else-if="scope.row.sign == 2">删除</span>
        </template>
      </el-table-column>-->
      <el-table-column label="操作者" align="center" prop="operator" />
      <el-table-column label="操作时间" align="center" prop="operationTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.operationTime, '{y}-{m}-{d}') }}</span>
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
  </div>
</template>

<script>
import { listCommissionSample, getCommissionSample, delCommissionSample, addCommissionSample,
  updateCommissionSample, exportCommissionSample, uploadSampleImage,getDetectionStandards
,getSampleAcademicNameList, submitSampleBatch,findValidMeasureUnit,
  searchAllCustomers,searchAllCompanies,searchLastRecord,listCommissionSampleHistory} from "@/api/detection/commissionSample";
import SampleBank from "../../stock/sampleBank/index";
import {trigger} from "../../../utils/generator/config";

export default {
  inheritAttrs: false,
  components: {SampleBank},
  props: [],
  name: "CommissionSample",
  data() {
    return {
      // 控制搜索来源,1委托检品登记，2检品登记，3退回检品登记
      searchSource: 2,
      // 遮罩层
      loading: true,
      // 导出遮罩层
      exportLoading: false,
      // 选中数组
      ids: [],
      // 选中顾客id
      customerIDs: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 委托检品表格数据
      commissionSampleList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      addOpen: false,
      editOpen: false,
      // 状态列表
      statusList: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        customerNumber: '',
        sampleNumber: '',
        priority: '',
        sampleMethod: '',
        detectionType: '',
        detectionStandardID: '',
        sendSamplePersonPhone: '',
        sendSamplePersonName: '',
        samplingStartTime: '',
        samplingEndTime: '',
        sampleName: '',
        sampleAcademicName: '',
        productionUnit: '',
        searchSource:'',
      },
      // 图片当前索引
      sampleImageIndex: '',
      samplingTimeArray: '',
      // 表单参数
      form: {
        customerID: null,
        companyID: null,
        sampleNumber: null,
        priority: null,
        samplingMode: null,
        detectionCategory: null,
        detectionStandardID: null,
        reportReceiveWay: null,
        sampleSenderPhone: null,
        sampleSenderName: null,
        samplingTime: null,
        sampleName: null,
        sampleScientificName: null,
        sampleStatus: null,
        sampleQuantity: null,
        measureUnit: null,
        samplingBase: null,
        productionUnit: null,
        productionUnitAddress: null,
        sampleDescription: null,
        recycle: null,
        sampleImage: null,
        QRcodeAddress: null,
        electronicSignature: null,
        reviewStatus: null,
        sign: null,
        operatorID: null,
        operationTime: null,
        receiptAddress: null
      },
      addForm:{
        customerID: '',
        companyID: '',
        receiptAddress: '',
        sampleList: [
          {
            sampleSenderName: '',
            sampleSenderPhone: '',
            detectionStandardID:'',
            sampleName:'',
            sampleAcademicName:'',
            sampleStatus:'',
            sampleQuantity:'',
            measureUnit:'',
            sampleDescription:'',
            samplingBase:'',
            productionUnit:'',
            productionUnitAddress:'',
            samplingTime:'',
            priority:'',
            samplingMode:'',
            detectionCategory:'',
            reportReceiveWay:'',
            recycle:'',
            sampleImage:'',
            reviewStatus: '',
          }
        ]
      }
      ,
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
      value: 1,
      //采样方式下拉选择选项
      samplingModes: [{
        value1: 2,
        label: '抽检'
      }, {
        value1: 1,
        label: '送检'
      }],
      value1: 1,
      //检测类别下拉选择选项
      detectionCategories: [{
        value2: 1,
        label: '委托'
      }, {
        value2: 2,
        label: '仲裁'
      }, {
        value2: 3,
        label: '其他'
      }],
      value2: 1,
      //采样方式下拉选择选项
      reportReceiveWays: [{
        value3: 0,
        label: '邮寄'
      }, {
        value3: 1,
        label: '自取'
      }],
      value3: null,
      //回收检品下拉选择项
      recycles: [{
        value4: 0,
        label: '否'
      }, {
        value4: 1,
        label: '是'
      }],
      value4: null,
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
      // 检测标准列表
      detectionStandardOptions: [
        {
          detectionNumber: "GB 2763-2019",
          detectionStandardID: 1,
        }
      ],
      // 检品学名选项
      sampleAcademicOptions : [],
      // 提交列表
      submitList : [],
      //
      measureUnitList: [],
      // 客户选项列表
      customerList:[],
      // 单位地址选项
      companyList: [],
      // 表单校验
      rules: {
        customerID:[{required:true,message: "客户账号不能为空",trigger:"blur"}],
        companyID:[{required:true,message: "单位地址不能为空",trigger:"blur"}],
      },

      sampleInformationRules: {
        sampleSenderName: [
          { required: true, message: "送样人名称不能为空", trigger: "blur" }
        ],
        sampleSenderPhone:[
          { required: true, message: "送样人电话不能为空", trigger: "blur" }
        ],
        detectionStandardID:[
          { required: true, message: "检测标准不能为空", trigger: "change" }
        ],
        sampleName:[
          { required: true, message: "检品名称不能为空", trigger: "blur" }
        ],
        sampleScientificName:[
          { required: true, message: "检品学名不能为空", trigger: "blur" }
        ],
        sampleStatus:[
          { required: true, message: "检品状态不能为空", trigger: "blur" }
        ],
        sampleQuantity:[
          { required: true, message: "检品数量不能为空", trigger: "blur" }
        ],
        measureUnit:[
          { required: true, message: "计量单位不能为空", trigger: "change" }
        ],
        sampleDescription:[
          { required: true, message: "检品描述不能为空", trigger: "change" }
        ],
        samplingBase:[
          { required: true, message: "抽样基数不能为空", trigger: "change" }
        ],
        productionUnit:[
          { required: true, message: "检品生产单位不能为空", trigger: "change" }
        ],
        productionUnitAddress:[
          { required: true, message: "生产单位地址不能为空", trigger: "change" }
        ],
    }
    }
  },
  created() {
    this.detectionStandardList();
    this.sampleAcademicNameList();
    this.validMeasureUnit();
    this.getList();
  },
  methods: {
    /** 查询委托检品列表 */
    getList() {
      this.loading = true;
      this.queryParams.searchSource = 4
      listCommissionSampleHistory(this.queryParams).then(response => {
        this.commissionSampleList = response.rows;
        this.total = response.total;
        this.loading = false;
        for(let i = 0; i < this.commissionSampleList.length; i++){
          this.commissionSampleList[i].sampleImage = process.env.VUE_APP_BASE_API + this.commissionSampleList[i].sampleImage;
          this.commissionSampleList[i].sampleImageList = [];
          this.commissionSampleList[i].sampleImageList.push(this.commissionSampleList[i].sampleImage)
        }
      });
    },
    // 新增取消按钮
    addCancel() {
      this.addOpen = false;
      this.companyList = null;
      this.reset();
    },
    // 编辑取消按钮
    editCancel(){
      this.editOpen =false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        sampleID: null,
        customerID: null,
        companyID: null,
        sampleNumber: null,
        priority: null,
        samplingMode: null,
        detectionCategory: null,
        detectionStandardID: null,
        reportReceiveWay: null,
        sampleSenderPhone: null,
        sampleSenderName: null,
        samplingTime: null,
        sampleName: null,
        sampleScientificName: null,
        sampleStatus: null,
        sampleQuantity: null,
        measureUnit: null,
        samplingBase: null,
        productionUnit: null,
        productionUnitAddress: null,
        sampleDescription: null,
        recycle: null,
        sampleImage: null,
        QRcodeAddress: null,
        electronicSignature: null,
        reviewStatus: null,
        sign: null,
        operatorID: null,
        operationTime: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.matchSamplingTime();
      console.log(this.queryParams)
      this.getList();
    },
    // 采样是时间匹配
    matchSamplingTime(){
      if(null != this.samplingTimeArray || this.samplingTimeArray.length != 0){
        this.queryParams.samplingStartTime = this.samplingTimeArray[0];
        this.queryParams.samplingEndTime = this.samplingTimeArray[1];
      }
    }
    ,
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.queryParams.reviewStatus = null;
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.sampleID)
      this.customerIDs = selection.map(item => item.customerID)
      this.submitList = selection.map(item => item)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    getSubmitList(flag){
      this.queryParams.reviewStatus = flag
      this.getList()
    }
    ,
    onOpen() {},
    onClose() {
      this.$refs['elForm'].resetFields()
    },
    close() {
      this.$emit('update:visible', false)
    },
    handelConfirm() {
      this.$refs['elForm'].validate(valid => {
        if (!valid) return
        this.close()
      })
    },
    detectionStandardList(){
      getDetectionStandards().then(res=>{
        this.detectionStandardOptions = res.data
      })
    },
    sampleAcademicNameList(){
      getSampleAcademicNameList().then(res=>{
        this.sampleAcademicOptions = res.data
      })
    },
    // 查找有效的单位
    validMeasureUnit(){
      findValidMeasureUnit().then(res=>{
        this.measureUnitList = res.data
      })
    },
    // 查询所有客户
    findAllCustomers(){
      searchAllCustomers().then(res=>{
        this.customerList = res.data
      })
    },
    // 跳转到任务再次分派页面
    toCommissionSampleSearch(){
      this.$router.push({ path: "/detection/commissionSampleSearch"});
    },
    // 跳转到任务再次分派页面
    toCommissionSample(){
      this.$router.push({ path: "/detection/commissionSample"});
    },
  }
};
</script>
