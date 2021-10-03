<template>
  <div class="app-container" style="position: relative;height: 688px; ">
<!--    background-color: #7a6df0-->
    <!-- 头部区域   -->
    <el-row>
      <el-col :span="4">
        <el-select v-model="selectStandardID" filterable placeholder="请选择检测标准" @change="selectStandard">
          <el-option
            v-for="item in standardMap"
            :key="item.JCBZ_ID"
            :label="item.JCBZ_BH"
            :value="item.JCBZ_ID">
          </el-option>
        </el-select>

      </el-col>

      <el-col :span="4" :offset="10">
        <el-select v-model="selectOptionValue" placeholder="请选择">
          <el-option
              v-for="item in selectOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value">
          </el-option>
        </el-select>
      </el-col>
      <el-col :span="4" >
        <el-input v-model="selectInputContent" clearable @keyup.enter.native="handleSelectQuery"/>
      </el-col>
      <el-col :span="1" style="margin-left: 5px;margin-top: 4px">
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleSelectQuery">搜索</el-button>
      </el-col>
    </el-row>

    <el-row>
      <el-col :span="24" style="font-size: 20px; margin-top: 16px">
        <span>{{selectStandardInfo.standardName}}</span>
      </el-col>
    </el-row>

    <!--   查询按钮   -->
    <el-row style="margin-top: 10px; margin-bottom: 10px">
      <el-button @click="changeTable(0)">首页</el-button>
      <el-button @click="getFoodList">按样品类别/名称查询</el-button>
      <el-button @click="getPesticideList">按检测项目查询</el-button>
      <el-button @click="getExemptionList">豁免农药清单</el-button>
    </el-row>

    <!--  主体区域  -->
    <el-row>
      <!--   左侧区域   -->
      <el-col :span="3" :gutter="60">
        <!--   样品类别列表     -->
        <el-tree
          :data="foodTypeOptions"
          :props="defaultProps"
          ref="tree"
          @node-click="handleNodeClick"
        />

      </el-col>
      <!-- 右侧区域 -->
      <el-col :span="21">

        <!--  检测农药列表 v-show="pesticideShow"   -->
        <div id="pesticideTable">
          <el-table  :data="pesticideList" v-loading="pesticideLoading" >
<!--            <el-table-column label="检测农药ID" align="center" prop="pesticideID" />-->
            <el-table-column label="项目名称" align="center" sortable prop="pesticideName">
              <template slot-scope="scope">
                <a @click="handlePesticideNameClick(scope.row.pesticideName)"
                   style="color:green;cursor:pointer;font-size: 16px;">
                  {{scope.row.pesticideName}}
                </a>
              </template>
            </el-table-column>
            <el-table-column label="项目英文名称" align="center" prop="pesticideEnglishName" />
            <el-table-column label="ADI(mg/kg bw)" align="center" sortable prop="adi" />
            <el-table-column label="残留物" align="center" prop="pesticideResidues" />
            <el-table-column label="功能" align="center" prop="pesticideFunction" />
          </el-table>
          <pagination
            :total="pesticideTotal"
            :page.sync="pesticideQueryParams.pageNum"
            :limit.sync="pesticideQueryParams.pageSize"
            @pagination="getPesticideList"
          />
        </div>

        <!-- 样品列表 v-show="foodShow"-->
        <div id="foodTable">
          <el-table  :data="foodList" v-loading="foodLoading" >
            <el-table-column label="样品类别层级" align="center" prop="foodTypeStr"/>
            <el-table-column label="样品学名" align="center" prop="foodName">
              <template slot-scope="scope">
                <a @click="handleFoodTypeClick(scope.row)"
                   style="color:green;cursor:pointer;font-size: 16px;">
                  {{scope.row.foodName}}
                </a>
              </template>
            </el-table-column>
            <el-table-column label="样品名称" align="center" prop="foodNickName"/>
          </el-table>
          <pagination
            :total="foodTotal"
            :page.sync="foodQueryParams.pageNum"
            :limit.sync="foodQueryParams.pageSize"
            @pagination="getFoodList"
          />
        </div>


        <!-- 豁免清单列表  v-show="exemptionShow"   -->
        <div id="exemptionTable">
          <el-table :data="exemptionList" v-loading="exemptionLoading">
            <el-table-column label="检测项目名称" align="center" prop="pesticideName"/>
            <el-table-column label="检测项目英文名称" align="center" prop="pesticideEnglishName"/>
          </el-table>
          <pagination
            :total="exemptionTotal"
            :page.sync="pesticideQueryParams.pageNum"
            :limit.sync="pesticideQueryParams.pageSize"
            @pagination="getExemptionList"
          />
        </div>


        <!-- 限量库信息展示列表   v-show="limitInfoShow"-->
        <div id="limitInfoTable">
          <el-table  v-loading="loading" :data="limitInfoList" >
            <el-table-column label="样品类别/名称" align="center" prop="foodName"/>
            <el-table-column label="测定部位" align="center" prop="detectionPart"/>
            <el-table-column label="项目名称" align="center" prop="pesticideName"/>
            <el-table-column label="项目英文名称" align="center" prop="pesticideEnglishName"/>
            <el-table-column label="功能" align="center" prop="pesticideFunction"/>
            <el-table-column label="最大残留量/(mg/kg)" align="center" prop="maxResidualAmount"/>
            <el-table-column label="ADI(mg/kgbw)" align="center" prop="adi"/>
            <el-table-column label="残留物" align="center" prop="pesticideResidues"/>
            <el-table-column label="检测方法" align="center" prop="detectionNames"/>
            <el-table-column label="备注" align="center" prop="remark"/>
          </el-table>
          <pagination
            :total="limitTotal"
            :page.sync="queryParams.pageNum"
            :limit.sync="queryParams.pageSize"
            @pagination="getList"
          />
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import { listLimitInfo} from "@/api/limit/limitInfo";
  import { treeselect } from "@/api/limit/limitInfo";
  import { listPesticide, listExemptionList } from "@/api/limit/pesticide";
  import {listFood} from "@/api/limit/food";
  import {mapStandard, getStandard} from "@/api/limit/standard";
  export default {
    name: "LimitInfo",
    data() {
      return {
        // 遮罩层
        loading: true,
        // 样品加载中
        foodLoading: true,
        // 农药加载中
        pesticideLoading: true,
        //豁免加载中
        exemptionLoading:true,
        // 导出遮罩层
        exportLoading: true,
        // 选中数组
        ids: [],
        // 非单个禁用
        single: true,
        // 非多个禁用
        multiple: true,
        // 显示搜索条件
        showSearch: true,
        //样品总条数
        foodTotal:0,
        //检测农药总条数
        pesticideTotal:0,
        //豁免总条数
        exemptionTotal:0,
        // 限量库信息总条数
        limitTotal: 0,
        //样品类别树选项
        foodTypeOptions: undefined,
        //检测标准信息列表 id-编号map
        standardMap:[],
        //样品类别列表
        foodTypeList:[],
        //检测农药列表
        pesticideList:[],
        //样品列表
        foodList:[],
        //豁免清单数据
        exemptionList:[],
        // 限量库详细信息表格数据
        limitInfoList: [],
        //选择检测标准
        selectStandardID: '',
        //选择的检测标准详细信息
        selectStandardInfo:{},
        //选择的查询方式
        selectOptionValue:1,
        //选择查询方式的内容
        selectInputContent:"",
        //下拉选择查询类别 根据农药、样品查询
        selectOptions:[{
          value: 1,
          label:"按样品类别查询"
        },{
          value: 2,
          label: "按农药查询"
        }],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          //检测标准
          standardNumber: null,
          standardName: null,
          //检测农药表
          pesticideName: null,
          pesticideEnglishName: null,
          pesticideExempt: null,
          Adi: null,
          pesticideResidues: null,
          pesticideFunction: null,
          //样品信息
          foodTypeID: null,
          foodNickName: null,
          foodName: null,
          detectionPart: null,
          //总表参数
          detectionStandardID: null,
          foodID: null,
          pesticideID: null,
          detectionFunctions: null,
          maxResidualAmount: null,
          remark: null
        },
        //样品查询参数
        foodQueryParams:{
          pageNum: 1,
          pageSize: 10,
          //样品信息
          foodTypeID: null,
          foodNickName: null,
          foodName: null,
          detectionPart: null
        },
        //农药查询参数
        pesticideQueryParams:{
          //检测农药表
          pageNum: 1,
          pageSize: 10,
          pesticideID: null,
          pesticideName: null,
          pesticideEnglishName: null,
          pesticideExempt: null,
          Adi: null,
          pesticideResidues: null,
          pesticideFunction: null,
        },
        // 表单参数
        form: {},
        defaultProps: {
          children: "children",
          label: "label"
        }
      };
    },
    created() {
      this.getStandard();
      this.getFoodTypeTree();
      // this.getPesticideList();
      // this.getList();
      this.$nextTick(function () {
        this.getFoodList();
      });

    },
    methods: {
      /**表格显示*/
      changeTable(index){
        //首页
        if(index === 0){
          document.getElementById('foodTable').style.display = "none";
          document.getElementById('pesticideTable').style.display = "none";
          document.getElementById('exemptionTable').style.display = "none";
          document.getElementById('limitInfoTable').style.display = "none";
        }
        //按样品类别/名称查询
        else if(index === 1) {
          document.getElementById('foodTable').style.display = "block";
          document.getElementById('pesticideTable').style.display = "none";
          document.getElementById('exemptionTable').style.display = "none";
          document.getElementById('limitInfoTable').style.display = "none";
        }
        //按农药查询
        else if(index === 2) {
          document.getElementById('foodTable').style.display = "none";
          document.getElementById('pesticideTable').style.display = "block";
          document.getElementById('exemptionTable').style.display = "none";
          document.getElementById('limitInfoTable').style.display = "none";
        }
        //豁免清单查询
        else if(index === 3){
          document.getElementById('foodTable').style.display = "none";
          document.getElementById('pesticideTable').style.display = "none";
          document.getElementById('exemptionTable').style.display = "block";
          document.getElementById('limitInfoTable').style.display = "none";
        }
        //限量库表显示
        else{
          document.getElementById('foodTable').style.display = "none";
          document.getElementById('pesticideTable').style.display = "none";
          document.getElementById('exemptionTable').style.display = "none";
          document.getElementById('limitInfoTable').style.display = "block";
        }
      },
      /** 查询限量库详细信息列表 */
      getList() {
        this.loading = true;
        listLimitInfo(this.queryParams).then(response => {
          this.limitInfoList = response.rows;
          this.limitTotal = response.total;
          this.changeTable(4);
          this.loading = false;
        });

      },
      /** 查询检测标准信息*/
      getStandard(){
        mapStandard(this.queryParams).then(response =>{
          this.standardMap = response.data;
        })
      },
      selectStandard(){
        getStandard(this.selectStandardID).then(response =>{
          this.selectStandardInfo = response.data;
          // this.getFoodList();
          this.changeTable(1);
        })
      },
      /** 查询检测农药信息*/
      getPesticideList(){
        this.$nextTick(function() {
          // DOM 更新了
          this.pesticideLoading = true;
        });
        this.changeTable(2);

        if(this.selectOptionValue === 2){
          this.pesticideQueryParams.pesticideName = this.selectInputContent;
        }else{
          this.pesticideQueryParams.pesticideName = "";
        }
        listPesticide(this.pesticideQueryParams).then(response =>{
          this.pesticideList = response.rows;
          this.pesticideTotal = response.total;
          this.pesticideLoading = false;
        })
      },
      /**exemptionList*/
      getExemptionList(){
        this.exemptionLoading = true;
        this.changeTable(3);
        listExemptionList(this.pesticideQueryParams).then(response =>{
          this.exemptionList = response.rows;
          console.log(this.exemptionList);
          this.exemptionTotal = response.total;
          this.exemptionLoading = false;
        })
      },
      /** 查询样品信息*/
      getFoodList(){
        this.foodLoading = true;
        this.changeTable(1);
        if(this.selectOptionValue === 1){
          this.foodQueryParams.foodName = this.selectInputContent;
        }else{
          this.foodQueryParams.foodName = "";
        }
        listFood(this.foodQueryParams).then(response =>{
          this.foodList = response.rows;
          this.foodTotal = response.total;
          this.foodLoading = false;
        })
      },
      /** 查询样品类别下拉树结构*/
      getFoodTypeTree() {
        treeselect().then(response => {
          this.foodTypeOptions = response.data;
        })
      },
      handleNodeClick(data){
        if(data.children === undefined)
        {
          if(this.selectStandardID === "")
          {
            alert("请选择检测标准！！！");
            return;
          }
          this.resetQueryParams();
          this.queryParams.foodTypeID = data.id;
          this.queryParams.foodName = data.label;
          this.queryParams.pageNum = 1;
          this.queryParams.pageSize = 10;
          this.queryParams.standard = this.selectStandardID;

          // this.loading = true;
          this.$nextTick(function() {
            // DOM 更新了
            this.loading = true;
          });
          this.changeTable(4);
          listLimitInfo(this.queryParams).then(response => {
            this.limitInfoList = response.rows;
            this.limitTotal = response.total;
            this.loading = false;
          });
        }

      },
      handlePesticideNameClick(nameStr){
        if(this.selectStandardID === "")
        {
          alert("请选择检测标准！！！");
          return;
        }
        this.resetQueryParams();
        this.queryParams.pesticideName = nameStr;
        this.queryParams.standard = this.selectStandardID;
        this.queryParams.pageNum = 1;
        this.queryParams.pageSize = 10;
        this.queryParams.standard = this.selectStandardID;
        // this.loading = true;
        this.$nextTick(function() {
          // DOM 更新了
          this.loading = true;
        });
        this.changeTable(4);
        listLimitInfo(this.queryParams).then(response => {
          console.log(response.rows);
          this.limitInfoList = response.rows;
          this.limitTotal = response.total;
          this.loading = false;
          // this.changeTable(4);
        });
      },
      //点击样品 显示限量库详细信息
      handleFoodTypeClick(row){
        if(this.selectStandardID === "")
        {
          alert("请选择检测标准！！！");
          return;
        }
        this.resetQueryParams();
        this.queryParams = row;
        this.queryParams.pageNum = 1;
        this.queryParams.pageSize = 10;
        this.queryParams.standard = this.selectStandardID;
        this.$nextTick(function() {
          // DOM 更新了
          this.loading = true;
        });
        this.changeTable(4);
        // this.loading = true;
        listLimitInfo(this.queryParams).then(response => {
          this.limitInfoList = response.rows;
          this.limitTotal = response.total;
          this.loading = false;
        });
      },
      //下拉选取按样品查询、按农药查询显示的内容
      handleSelectQuery(){
        // ==1 按样品查询
        if(this.selectOptionValue === 1)
        {
            this.foodQueryParams.foodName = this.selectInputContent;
            this.getFoodList();
        }
        // == 2 按农药查询
        if(this.selectOptionValue ===  2)
        {
          this.pesticideQueryParams.pesticideName = this.selectInputContent;
          this.getPesticideList();
        }
        this.selectInputContent = "";
      },
      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      resetQueryParams(){

        this.queryParams = {
          pageNum: 1,
          pageSize: 10,
          //检测标准
          standardNumber: null,
          standardName: null,
          //检测农药表
          pesticideName: null,
          pesticideEnglishName: null,
          pesticideExempt: null,
          Adi: null,
          pesticideResidues: null,
          pesticideFunction: null,
          //样品信息
          foodTypeID: null,
          foodNickName: null,
          foodName: null,
          detectionPart: null,
          //总表参数
          detectionStandardID: null,
          foodID: null,
          pesticideID: null,
          detectionFunctions: null,
          maxResidualAmount: null,
          remark: null
        }
      },
      // 表单重置
      reset() {
        this.form = {
          detectionStandardID: null,
          foodID: null,
          pesticideID: null,
          detectionFunctions: null,
          maxResidualAmount: null,
          remark: null
        };
        this.resetForm("form");
      },
      // /** 搜索按钮操作 */
      // handleQuery() {
      //   this.queryParams.pageNum = 1;
      //   this.getList();
      // },
      // /** 重置按钮操作 */
      // resetQuery() {
      //   this.resetForm("queryForm");
      //   this.handleQuery();
      // }
    }
  };
</script>
