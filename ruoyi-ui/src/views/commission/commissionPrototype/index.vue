<template>
<div class="app-container">
  <el-card>
    <el-form  :model="customerData" :rules="rules" label-width="100px" :inline="true" >
      <div style="border-left: 4px solid #39a9ff;padding-left: 8px;line-height: initial;font-size: initial;">
        <h4>客户基本信息</h4>
      </div>
      <el-form-item label="客户名" prop="username" label-width="120px"  >
        <el-input v-model="customerData.username"  clearable size="small"  :disabled="true" />
      </el-form-item>
      <el-form-item label="单位" prop="companyName" label-width="120px" >
        <el-input v-model="customerData.companyName"   clearable size="small" :disabled="true" />
      </el-form-item>

    </el-form>
  </el-card>
<el-card>
  <el-form ref="addCommonForm" :model="addEntrust" :rules="rules" label-width="100px" :inline="true" >
    <div style="border-left: 4px solid #39a9ff;padding-left: 8px;line-height: initial;font-size: initial;">
      <h4>委托方基本信息</h4>
    </div>
    <el-form-item label="送样人名称" prop="sampleSenderName" label-width="120px"  :rules="rules.sampleSenderName">
      <el-input v-model="addEntrust.sampleSenderName" placeholder="请输入送样人名称"  clearable size="small" />
    </el-form-item>
    <el-form-item label="送样人电话 " prop="sampleSenderPhone" label-width="120px" :rules="rules.sampleSenderPhone">
      <el-input v-model="addEntrust.sampleSenderPhone" placeholder="请输入送样人电话 "  clearable size="small" />
    </el-form-item>
      <el-form-item label="收件地址" prop="receiptAddress" label-width="120px" >
        <el-input v-model="addEntrust.receiptAddress" placeholder="请输入收件地址"  clearable size="small" />
      </el-form-item>
    <el-form-item label="委托单编号" prop="orderID" label-width="120px" >
      <el-input v-model="orderID" placeholder="请输入委托单编号"  clearable size="small" :disabled="true" />
    </el-form-item>


    <el-form-item>
      <el-button icon="el-icon-refresh" size="mini" @click="resetAdd">重置</el-button>
    </el-form-item>
  </el-form>
</el-card>

<!--      样品单独信息-->

  <el-form ref="entrustInformation" :model="addEntrust"  label-width="110px" >

    <el-row :gutter="20" v-for="(item,index) in addEntrust.entrustList" :key="index">
      <el-card>
      <div style="border-left: 4px solid #39a9ff;padding-left: 8px;line-height: initial;font-size: initial;">
        <h4>委托信息</h4>
      </div>
      <el-row :gutter="20">
        <el-col :span="5">
          <el-form-item label="样品学名" :prop="'entrustList.'+index+'.sampleScientificName'"  :rules="rules.sampleScientificName">
            <el-select
              v-model="item.sampleScientificName"
              placeholder="请输入检品学名"
              clearable
              filterable
              @change="getHistoryObject(index)"
            >
              <el-option v-for="item in sampleAcademicOptions"
                         :key="item"
                         :value="item"
                         :label="item"
              />
            </el-select>
  <!--          <el-input v-model="item.sampleScientificName" placeholder="请输入样品学名" @blur="getHistoryObject($event,index)"  clearable size="small" />-->
          </el-form-item>
        </el-col>
        <el-col :span="5">
          <el-form-item label="样品名称" :prop="'entrustList.'+index+'.sampleName'" :rules="rules.sampleName">
            <el-input v-model="item.sampleName" placeholder="请输入样品名称" />
          </el-form-item>
        </el-col>
        <el-col :span="5">
          <el-form-item label="采样优先级 " :prop="'entrustList.'+index+'.priority'" :rules="rules.priority">
            <el-select v-model="item.priority" placeholder="请选择采样优先级">
              <el-option
                v-for="item in priorityOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="5">
          <el-form-item label="检测标准" :prop="'entrustList.'+index+'.detectionStandardID'" >
            <el-select v-model="item.detectionStandardID" placeholder="请选择检测标准">
              <el-option
                v-for="item in standardsItem"
                :key="item.id"
                :label="item.standardCode"
                :value="item.id">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="20">
        <el-col :span="5">
          <el-form-item label="报告领取方式" :prop="'entrustList.'+index+'.reportReceiveWay'" :rules="rules.reportReceiveWay">
            <el-select v-model="item.reportReceiveWay" placeholder="请选择报告领取方式">
              <el-option
                v-for="item in reportReceiveWayOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
  <!--      <el-col :span="5">-->
  <!--        <el-form-item label="采样时间" :prop="'entrustList.'+index+'.samplingTime'" :rules="rules.samplingTime">-->
  <!--          <el-date-picker clearable size="small"-->
  <!--                          v-model="item.samplingTime"-->
  <!--                          type="date"-->
  <!--                          value-format="yyyy-MM-dd"-->
  <!--                          placeholder="选择采样时间">-->
  <!--          </el-date-picker>-->
  <!--        </el-form-item>-->
  <!--      </el-col>-->
        <el-col :span="5">
          <el-form-item label="样品状态" :prop="'entrustList.'+index+'.sampleStatus'" :rules="rules.sampleStatus">
            <el-select v-model="item.sampleStatus" placeholder="请选择样品状态">
              <el-option
                v-for="item in sampleStatusOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>

          </el-form-item>
        </el-col>
        <el-col :span="5">
          <el-form-item label="样品数量" :prop="'entrustList.'+index+'.sampleQuantity'"  :rules="rules.sampleQuantity">
            <el-input v-model="item.sampleQuantity" placeholder="请输入样品数量" />
          </el-form-item>
        </el-col>
        <el-col :span="5" >
          <el-form-item label="样品计量单位"  :prop="'entrustList.'+index+'.measureUnit'" :rules="rules.measureUnit"  >
            <el-select v-model="item.measureUnit" placeholder="请选择样品计量单位" >
              <el-option
                v-for="item in unitsObject"
                :key="item.id"
                :label="item.unit"
                :value="item.id">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="20" >
<!--        <el-col :span="5">-->
<!--          <el-form-item label="抽样基数" :prop="'entrustList.'+index+'.samplingBase'">-->
<!--            <el-input v-model="item.samplingBase" placeholder="请输入抽样基数" />-->
<!--          </el-form-item>-->
<!--        </el-col>-->
        <el-col :span="5">
          <el-form-item label="回收剩余样品" :prop="'entrustList.'+index+'.recycle'">
            <el-select v-model="item.recycle" placeholder="请选择是否回收样品">
              <el-option
                v-for="item in recycleOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="5">
          <el-form-item label="样品生产单位" :prop="'entrustList.'+index+'.productionUnit'" >
            <el-input v-model="item.productionUnit" placeholder="请输入样品生产单位" />
          </el-form-item>
        </el-col>
        <el-col :span="5">
          <el-form-item label="生产单位地址" :prop="'entrustList.'+index+'.productionUnitAddress'" >
            <el-input v-model="item.productionUnitAddress" placeholder="请输入生产单位地址"  />
          </el-form-item>
        </el-col>
        <el-col :span="5">
          <el-form-item label="上传样品图像" :prop="'entrustList.'+index+'.sampleImage'">
            <!--          <el-input v-model="item.sampleImage" placeholder="请上传样品图像" />-->
            <el-upload action="#" :before-upload = "sampleImageUpload">
              <el-button size="medium" type="primary" icon="el-icon-upload" @click="getIndex(index)">点击上传</el-button>
            </el-upload>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="20">


        <el-col :span="5">
<!--          <el-form-item label="样品描述" :prop="'entrustList.'+index+'.sampleDescription'">-->
<!--            <el-input v-model="item.sampleDescription"   type="textarea"  placeholder="请输入样品描述" />-->
<!--          </el-form-item>-->
          <el-form-item label="检品描述" >
            <el-select filterable multiple v-model="sampleDescriptionArray"
                       size="medium"  placeholder="请选择检品描述">
              <el-option v-for="it in detectionDescriptionOption"
                         :key="it.id" :label="it.name" :value="it.id">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>

<!--        <el-col :span="5">-->
<!--          <el-form-item label="检测描述" :prop="'entrustList.'+index+'.detectionDescription'">-->
<!--            <el-input v-model="item.detectionDescription"   type="textarea"  placeholder="请输入检测描述" />-->
<!--          </el-form-item>-->
<!--        </el-col>-->
        <el-col :span="5">
        <el-form-item>
          <el-button type="danger" v-if="addEntrust.entrustList.length >1" size="small" plain
                     @click="removeRow(index)">
            删除</el-button>
        </el-form-item>
        </el-col>
      </el-row>
      </el-card>
    </el-row>

    <el-row :gutter="20">
      <el-col :span="6" :push="18">
        <el-form-item>
          <el-button icon="el-icon-plus" size="small" @click="addEntrustInformation">新增委托基本信息</el-button>
          <el-button icon="el-icon-refresh" size="small" @click="resetSample">重置所有委托信息</el-button>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row :gutter="20" style="margin-top:50px;">
      <el-col :span="6" :push="18">
        <el-form-item label-width="0">
          <el-button type="success" @click="submitForm" style="width:100px;">提交</el-button>

          <el-button type="primary" @click="resetTwoForm" style="width:100px;">重置信息</el-button>
        </el-form-item>
      </el-col>
    </el-row>
  </el-form>
  <el-dialog
    title="提示"
    :visible.sync="dialogVisible"
    width="30%"
  >
    <span>你确定要提交吗？</span>
    <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitAllForm">确 定</el-button>
      </span>
  </el-dialog>
</div>

</template>

<script>
import {  getStandards,getUnits,addEntrustFrom,getLastInput,getHistoryFrom,uploadSampleImage,getSampleAcademicNameList,getCustomerData,getOrderID ,findIDAndName} from "@/api/commission/commissionPrototype";


export default {
  name: "CommissionPrototype",
  data() {
    return {
      // 遮罩层
      loading: true,
      dialogVisible: false,
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
      // 委托样品表格数据
      commissionPrototypeList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      standardsItem:[],//检测标准
      unitsObject:[],//计量单位
      sampleName:'',
      listData:[],
      customerData:{
        username:null,
        companyName:null,
      },
      //采样优先级
      // 检品学名选项
      sampleAcademicOptions : [],
      priorityOptions:[{
        value:1,
        label:'正常'
      },{
        value:2,
        label:'急'
      },{
        value:3,
        label:'加急'
      },{
        value:4,
        label:'特急'
      }],
      //报告领取方式
      reportReceiveWayOptions:[{
        value:0,
        label:'邮寄'
      },{
        value:1,
        label:'自取'
      }],
      //样品状态
      sampleStatusOptions:[{
        value:1,
        label:'固体'
      },{
        value:2,
        label:'液体'
      },{
        value:3,
        label:'胶体'
      },{
        value:4,
        label:'气体'
      },{
        value:5,
        label:'其他'
      }],
      //回收剩余样品
      recycleOptions:[{
        value:0,
        label:'否'
      },{
        value:1,
        label:'是'
      }],
      orderID:null,
      //新增参数
      addEntrust:{
        sampleSenderName:null,
        sampleSenderPhone:null,
        receiptAddress:null,
        entrustList:[{
          priority:null,
          detectionStandardID:null,
          reportReceiveWay:null,
          productionUnit:null,
          productionUnitAddress:null,
          samplingTime:null,
          sampleName:null,
          sampleScientificName:null,
          sampleStatus:null,
          sampleQuantity:null,
          measureUnit:null,
          samplingBase:null,
          sampleDescription:null,
          recycle:null,
          sampleImage:null,
          detectionDescription:null,
          // sampleDescriptionArray:[],
          // detectionDescriptionOption:[]
        }]
      },
      imageIndex:'',
      sampleDescriptionArray:[],
      detectionDescriptionOption:[],
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        priority: [
          { required: true, message: "请选择采样优先级", trigger: "blur" }
        ],
        sampleSenderName: [
          { required: true, message: "送样人名称不能为空", trigger: "blur" ,},
          {
            validator: function(rule, value, callback) {
              //校验中文的正则：/^[\u4e00-\u9fa5]{0,}$/
              if (/^[\u4e00-\u9fa5]+$/.test(value) == false) {
                callback(new Error("请输入中文"));
              } else {
                //校验通过
                callback();
              }
            },
            trigger: "blur"
          }

        ],

        reportReceiveWay: [
          { required: true, message: "请选择报告领取方式 ", trigger: "change" }
        ],
        samplingTime: [
          { required: true, message: "采样时间不能为空", trigger: "blur" }
        ],
        sampleName: [
          { required: true, message: "样品名称不能为空", trigger: "blur" },
          {
            validator: function(rule, value, callback) {
              //校验中文的正则：/^[\u4e00-\u9fa5]{0,}$/
              if (/^[\u4e00-\u9fa5]+$/.test(value) == false) {
                callback(new Error("请输入中文"));
              } else {
                //校验通过
                callback();
              }
            },
            trigger: "blur"
          }
        ],
        sampleScientificName: [
          { required: true, message: "样品学名不能为空", trigger: "blur" }
        ],
        sampleQuantity: [
          { required: true, message: "样品数量不能为空", trigger: "blur" , },
          {type: 'number', transform: (value) => Number(value),message: "样品数量只能输入数字",}
        ],
        measureUnit: [
          { required: true, message: "样品计量单位不能为空", trigger: "blur" }
        ],
        sampleSenderPhone: [
          { required: true, pattern: /^1[34578]\d{9}$/,message: "请输入正确的手机号码", trigger: "blur" }
        ],
        detectionStandardID: [
          { required: true, message: "请选择检测标准", trigger: "blur" }
        ],
        sampleStatus: [
          { required: true, message: "请选择样品状态", trigger: "blur" }
        ],

      }
    };
  },
  created() {
    // this.getList();
    this.getStandardsItem();
    this.getUnitsObject();
    this.getLastInputObject();
    this.sampleAcademicNameList();
    this.getCustomerFrom();
    this.getOrder();
    // this.init();
  },
  mounted() {
    // this.init();
  },
  methods: {
    inite(){
      debugger;
      const that = this;
      if(that.addEntrust.entrustList == null){
        return;
      }
      for(var i=0; i < that.addEntrust.entrustList.length; i++){
        let name = that.addEntrust.entrustList[i].sampleScientificName;
        console.log(name)
        findIDAndName(name).then(res=>{
          that.detectionDescriptionOption = res.data;
          // console.log(this.addEntrust.entrustList[i].detectionDescriptionOption);
        })
        var s = that.addEntrust.entrustList[i].sampleDescription;
        var str  = s.split(",")
        for(let i=0; i < str.length; i++){
          that.sampleDescriptionArray[i] = Number(str[i]);
        }
      }
    },
    //获取客户信息
    getCustomerFrom(){
      getCustomerData().then(res=>{
        console.log(res)
        this.customerData= res.data
      })
    },
    // 匹配样品描述数组拼接成字符串
    // changeArrayToString(){
    //
    //   for(let i = 0; i < this.addEntrust.entrustList.length; ++i){
    //     if(this.addEntrust.entrustList[i].sampleDescriptionArray == null){
    //       return;
    //     }
    //     let str = '';
    //     for(let j = 0; j < this.addEntrust.entrustList[i].sampleDescriptionArray.length; ++j){
    //       str += this.addEntrust.entrustList[i].sampleDescriptionArray[j];
    //       if(j < this.addEntrust.entrustList[i].sampleDescriptionArray.length - 1)
    //         str += ',';
    //     }
    //     this.addEntrust.entrustList[i].sampleDescription= str
    //   }
    // },
    arrayToStr(arr){
      var res = '';
      for(let i=0; i < arr.length; i++){
        res += arr[i];
      }
      return res;
    },
    //获取委托单编号
    getOrder(){
      getOrderID().then(res=>{
        console.log(res)
        this.orderID= res.data
      })
    },
    getIndex(index){
      this.imageIndex=index;
      console.log(this.imageIndex)
    },
    // 上传图片
    sampleImageUpload(img){
      // console.log(index)
      let formData = new FormData();
      if(null == img){
        return;
      }
      formData.append("sampleImage", img);
      uploadSampleImage(formData).then(res=>{
        console.log(res)
        this.addEntrust.entrustList[this.imageIndex].sampleImage = res.data
        this.msgSuccess("图片上传成功")
        console.log(this.addEntrust.entrustList)
      })
    },
    //新增委托基本信息
    addEntrustInformation() {
      const that = this;
      that.addEntrust.entrustList.push({});
    },
    //删除委托基本信息
    removeRow(index) {
      if (index >= 0) {
        this.addEntrust.entrustList.splice(index, 1);
      }
    },
    //重置委托基本信息
    resetSample(){
      console.log("111")
      this.resetForm("entrustInformation");
    },
    resetAdd(){
      this.resetForm("addCommonForm")
    },
    resetTwoForm(){
      this.resetAdd();
      this.resetSample();
    },
    checkBlur(e) {
      console.log(e.target.value)
    },

    //获取学名
    sampleAcademicNameList(){
      getSampleAcademicNameList().then(res=>{
        console.log(res)
        this.sampleAcademicOptions = res.data
      })
    },
    //获取最后一次委托数据
    getLastInputObject(){
      debugger
      console.log("23345")
      const that = this;
      getLastInput().then(response => {
        console.log(response)
        that.addEntrust=response.data
        for(let i=0; i < that.addEntrust.entrustList.length; i++){
          that.addEntrust.entrustList[i].detectionDescriptionOption = [];
        }
        // that.standardsItem=response.data;
        return that.inite();
      });
    },
    //根据学名查询数据（农药）
    getHistoryObject(index){
      console.log(index)
      // console.log(e)
      // console.log(e.target.value)
      this.sampleName=this.addEntrust.entrustList[index].sampleScientificName

      console.log(this.sampleName)
      const that = this;

      getHistoryFrom(this.sampleName).then(response => {

        if (response.data.entrustList == null) {
          // that.listData=response.data.entrustList
          // that.addEntrust.entrustList[index] = response.data.entrustList[0]
          // console.log(that.addEntrust.entrustList[index])
          // console.log(that.listData)
          findIDAndName(this.sampleName).then(res=>{
            // debugger;
            that.addEntrust.entrustList[index].detectionDescriptionOption = res.data.slice(0,20);
            console.log(this.addEntrust.entrustList[index].detectionDescriptionOption)
          });
        }else{
          // that.addEntrust=response.data
          // that.standardsItem=response.data;

          findIDAndName(this.sampleName).then(res=>{
            that.addEntrust.entrustList.splice(index,1, response.data.entrustList[0])
            that.addEntrust.entrustList[index].detectionDescriptionOption = [];
            for(let i = 0; i < res.data.length; ++i){
              response.data.entrustList[0].detectionDescriptionOption.push(res.data[i])
            }
          });
        }
      });

    },
    //获取检测标准
    getStandardsItem(){
      const that = this;
      getStandards().then(response => {
        // console.log(response)
        that.standardsItem=response.data;
        that.standardsItem.push(
          {id:null,standardCode:"不详"}
        )
      });
    },
    //获取计量单位
    getUnitsObject(){
      const that = this;
      getUnits().then(response => {
        // console.log(response)
        that.unitsObject=response.data;
      });
    },
    /** 提交按钮 */
    // submitForm() {
    //   console.log("222")
    //   console.log(this.addEntrust)
    //   addEntrustFrom(this.addEntrust).then(response => {
    //     this.msgSuccess("提交成功");
    //     // console.log(response)
    //     // window.close()
    //     // open(location, '_self').close();
    //     window.returnValue=1;
    //   });

      submitForm() {
        //const that = this;
      // this.changeArrayToString()
        this.arrayToStr(this.sampleDescriptionArray)
        const validate1 = this.$refs["addCommonForm"].validate();
        const validate2 = this.$refs["entrustInformation"].validate();

        Promise.all([validate1,validate2])
          .then(res=>{
            this.dialogVisible = true;
          })
          .catch(res=>{
            console.log("提交失败")
          })

      },
    submitAllForm(){
      this.dialogVisible = false;

      addEntrustFrom(this.addEntrust).then(response => {
              this.msgSuccess("提交成功");
              // console.log(response)
        location.reload();
            });
    },
    /** 查询委托样品列表 */
    getList() {
      this.loading = true;
      listCommissionPrototype(this.queryParams).then(response => {
        this.commissionPrototypeList = response.rows;
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
        customerID: null,
        companyID: null,
        sampleNumber: null,
        priority: null,
        samplingMode: null,
        detectionCategory: null,
        detectionStandardID: null,
        reportReceiveWay: null,
        receiptAddress: null,
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
        sampleStage: null,
        sampleDistribute: null
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
      this.title = "添加委托样品";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const sampleID = row.sampleID || this.ids
      getCommissionPrototype(sampleID).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改委托样品";
      });
    },



    /** 删除按钮操作 */
    handleDelete(row) {
      const sampleIDs = row.sampleID || this.ids;
      this.$confirm('是否确认删除委托样品编号为"' + sampleIDs + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function() {
        return delCommissionPrototype(sampleIDs);
      }).then(() => {
        this.getList();
        this.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有委托样品数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        this.exportLoading = true;
        return exportCommissionPrototype(queryParams);
      }).then(response => {
        this.download(response.msg);
        this.exportLoading = false;
      }).catch(() => {});
    }
  }
};
</script>
