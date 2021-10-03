<template>
  <div class="app-container">
    <!-- 样品信息搜索框   -->
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="126px">
      <el-row type="flex" justify="space-around">
        <el-col :span="8">
          <el-form-item label="样品名称" prop="foodNickName">
            <el-select placeholder="请选择样品名称" v-model="queryParams.foodNickName" clearable filterable :loading="allFoodTypeLoading">
              <el-option
                v-for="item in allFoodList"
                :key="item.foodID"
                :label="item.foodNickName"
                :value="item.foodNickName"
                @keyup.enter.native="handleQuery"
              />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="样品学名" prop="foodName">
            <el-select placeholder="请选择样品学名" v-model="queryParams.foodName" clearable filterable :loading="allFoodTypeLoading">
              <el-option
                v-for="item in allFoodList"
                :key="item.foodID"
                :label="item.foodName"
                :value="item.foodName"
                @keyup.enter.native="handleQuery"
              />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="测定部位" prop="detectionPart">
            <el-input
              v-model="queryParams.detectionPart"
              placeholder="请输入测定部位"
              clearable
              size="small"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row >
        <el-col :span="8" >
          <el-form-item label="有效性" prop="isValid">
            <el-select placeholder="请选择标准类型" prop="isValid" v-model="queryParams.isValid" clearable filterable>
              <el-option
                v-for="item in isValidOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
                @keyup.enter.native="handleQuery"
              >
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8" :pull="1">
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
            <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
          </el-form-item>
        </el-col>
    </el-row>
      </el-form>

    <!-- 样品信息新增导出按钮   -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['limit:food:add']"
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
          v-hasPermi="['limit:food:edit']"
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
          v-hasPermi="['limit:food:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="info"
          plain
          icon="el-icon-upload2"
          size="mini"
          @click="handleImport"
          v-hasPermi="['limit:food:import']"
        >导入</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          :loading="exportLoading"
          @click="handleExport"
          v-hasPermi="['limit:food:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>


    <!-- 样品信息主体部分   -->
    <el-table v-loading="loading" :data="foodList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" type="index" align="center" >
        <template slot-scope="scope">
          <span>{{(queryParams.pageNum - 1) * queryParams.pageSize + scope.$index + 1}}</span>
        </template>
      </el-table-column>
<!--        <el-table-column label="样品ID" align="center" prop="foodID" />-->
      <el-table-column label="样品类别层级" align="center" prop="foodTypeStr" width="330px"/>
      <el-table-column label="样品名称" align="center" prop="foodNickName" />
      <el-table-column label="样品学名" align="center" prop="foodName" />
      <el-table-column label="测定部位" align="center" sortable prop="detectionPart" />
      <el-table-column label="是否有效" align="center" prop="isValid">
        <template slot-scope="scope">
          <el-switch
            class="switch"
            v-model= "scope.row.isValid"
            active-text="启用"
            inactive-text="禁用"
            :active-value="1"
            :inactive-value="0"
            @change="changeValid(scope.row)"
          >
          </el-switch>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['limit:food:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['limit:food:remove']"
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

    <!-- 添加或修改样品管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="auto">
        <el-form-item label="样品类别层级" prop="foodTypeStr" >
          <el-col :span="15">
            <el-input v-model="form.foodTypeStr" placeholder="样品类别层级" :disabled="true"/>
          </el-col>
        </el-form-item>
        <el-form-item label="所属上一层级"  v-show="isAdd">
          <el-col :span="15">
            <el-select placeholder="请选择所属上一层级" filterable v-model="form.foodTypeParentID" size="medium" @change="mergeFoodTypeStr" :loading="allFoodTypeLoading">
              <el-option
                v-for="item in foodTypeList"
                :key="item.foodTypeID"
                :label="item.foodTypeName"
                :value="item.foodTypeID"
              >
              </el-option>
            </el-select>
          </el-col>
        </el-form-item>
        <el-form-item label="样品名称" prop="foodNickName">
          <el-col :span="15">
            <el-input v-model="form.foodNickName" placeholder="请输入样品名称" />
          </el-col>
        </el-form-item>
        <el-form-item label="样品学名" prop="foodName">
          <el-col :span="15">
            <el-input v-model="form.foodName" placeholder="请输入样品学名"/>
          </el-col>
        </el-form-item>
        <el-form-item label="样品名称" prop="foodNickName">
          <el-input v-model="form.foodNickName" placeholder="请输入样品名称" />
        </el-form-item>
        <el-form-item label="测定部位" prop="detectionPart">
          <el-col :span="15">
            <el-input v-model="form.detectionPart" placeholder="请输入测定部位" />
          </el-col>
        </el-form-item>
        <el-form-item label="有效性" prop="isValid">
          <el-select placeholder="请选择是否有效" prop="isValid" clearable v-model="form.isValid">
            <el-option
              v-for="item in isValidOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 样品信息导入对话框 -->
    <el-dialog :title="upload.title" :visible.sync="upload.open" width="400px" append-to-body>
      <el-upload
        ref="upload"
        :limit="1"
        accept=".xlsx, .xls"
        :headers="upload.headers"
        :action="upload.url + '?updateSupport=' + upload.updateSupport"
        :disabled="upload.isUploading"
        :on-progress="handleFileUploadProgress"
        :on-success="handleFileSuccess"
        :auto-upload="false"
        drag
      >
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">
          将文件拖到此处，或
          <em>点击上传</em>
        </div>
        <div class="el-upload__tip" slot="tip">
          <el-checkbox v-model="upload.updateSupport" />是否更新已经存在的用户数据
          <el-link type="info" style="font-size:12px" @click="importTemplate">下载模板</el-link>
        </div>
        <div class="el-upload__tip" style="color:red" slot="tip">提示：仅允许导入“xls”或“xlsx”格式文件！</div>
      </el-upload>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFileForm">确 定</el-button>
        <el-button @click="upload.open = false">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listFood, getFood, delFood, addFood, updateFood, exportFood,importTemplate } from "@/api/limit/food";
import {listFoodType, addFoodType, delFoodType} from "@/api/limit/foodType";
import { getToken } from "@/utils/auth";
export default {
  name: "Food",
  data() {
    return {
      // 遮罩层
      loading: true,
      //所有食品
      allFoodLoading:true,
      //所有食品类别层级
      allFoodTypeLoading:true,
      // 导出遮罩层
      exportLoading: false,
      // 选中数组
      ids: [],
      //选中的数组(样品类别ID spLB_ID)
      idLBs:[],
      //选中的数组(样品名称)
      foodNames:[],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 样品管理表格数据
      foodList: [],
      //所有样品集合
      allFoodList:[],
      //所有样品类别集合
      foodTypeList:[],
      // 弹出层标题
      title: "",
      //是否是新增页面
      isAdd:true,
      //是否有效 1-有效 0-无效
      isValidOptions:[{
        value:1,
        label:"有效"
      },{
        value: 0,
        label: "无效"
      }],
      // 是否显示弹出层
      open: false,
      //是否显示 所属上一层级(新增显示，修改不显示)
      selectFoodType:false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        foodTypeID: null,
        foodParentID:null,
        foodNickName: null,
        foodName: null,
        detectionPart: null,
        isValid:null
      },
      //样品信息导入参数
      upload: {
        // 是否显示弹出层（检测标准导入）
        open: false,
        // 弹出层标题（监察人标准导入）
        title: "",
        // 是否禁用上传
        isUploading: false,
        // 是否更新已经存在的检测标准数据
        updateSupport: 0,
        // 设置上传的请求头部
        headers: { Authorization: "Bearer " + getToken() },
        // 上传的地址
        url: process.env.VUE_APP_BASE_API + "/limit/food/importData"
      },
      //样品类别表单参数
      foodTypeForm:{
        foodTypeID : null,
        foodTypeParentID : null,
        foodTypeName : null,
        isValid:null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        foodTypeStr: [
          { required: true, message: "所属上一层级不能为空", trigger: "blur" }
        ],
        foodNickName: [
          { required: true, message: "样品名称不能为空", trigger: "blur" }
        ],
        foodName: [
          { required: true, message: "样品学名不能为空", trigger: "blur" }
        ],
        isValid: [
          { required: true, message: "数据有效性标识不能为空", trigger: "blur" }
        ]
      }
    };
  },
  watch:{
    'form.foodName':{
      handler(){
        if(!this.isAdd)
        {
          if(this.form.foodTypeStr != null)
          {
            let typeList = this.form.foodTypeStr.split("/");
            let typeListLen = typeList.length;
            typeList[typeListLen - 1] = this.form.foodName;
            let str = "";
            for(let i = 0; i < typeListLen; i++){
              str += typeList[i] + "/";
            }
            if(typeListLen > 0){
              this.form.foodTypeStr = str.substr(0, str.length - 1);
            }
          }
        }
        else
        {
          if(this.form.foodTypeParentID !== undefined)
          {
            this.form.foodTypeStr = this.mergeFoodTypeStr();
          }
          else
          {
            this.form.foodTypeStr = '';
            if(this.form.foodName !== '')
            {
              this.form.foodTypeStr = this.form.foodName;
            }
          }
        }
      }//handler
    }
  },
  created() {
    this.getList();
    this.getFoodTypeList();
    this.getAllFoodList();
  },
  methods: {
    /** 查询样品管理列表 */
    getList() {
      this.loading = true;
      listFood(this.queryParams).then(response => {
        this.foodList = response.rows;
        console.log(this.foodList);
        this.total = response.total;
        this.loading = false;
      });
    },
    getAllFoodList(){
      this.allFoodLoading = true;
      listFood().then(response => {
        this.allFoodList = response.rows;
        this.allFoodLoading = false;
      });
    },
    /** 查询样品类别表*/
    getFoodTypeList(){
      this.allFoodTypeLoading = true;
      listFoodType().then(response =>{
        this.foodTypeList = response.rows;
        this.allFoodTypeLoading = false;
      });
    },
    /** 拼接新增样品 显示的样品类别信息*/
    mergeFoodTypeStr(){
      let foodTypeStrList = [];
      //获取样品类别层级用于在界面显示
      let result;
      let foodParentID = this.form.foodTypeParentID;
      while (foodParentID !== 0){
        result = this.foodTypeList.find(a=> {
          return a.foodTypeID === foodParentID;
        });
        foodParentID = result.foodTypeParentID;
        foodTypeStrList.push(result.foodTypeName);
      }

      foodTypeStrList = foodTypeStrList.reverse();
      let str = '';
      for(let i = 0; i < foodTypeStrList.length; i++){
        str += foodTypeStrList[i] + "/";
      }
      if(this.form.foodName){
        str += this.form.foodName + "/";
      }
      if(str.length > 0){
        this.form.foodTypeStr = str.substr(0, str.length - 1);
        return str.substr(0, str.length - 1);
      }
      return '';
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        foodID: null,
        foodTypeID: null,
        foodTypeStr:null,
        foodNickName: null,
        foodName: null,
        detectionPart: null,
        isValid:null
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
      this.ids = selection.map(item => item.foodID)
      this.idLBs = selection.map(item => item.foodTypeID)
      this.foodNames = selection.map(item => item.foodName);
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.form.isValid = 1;
      this.open = true;
      this.title = "添加样品管理";
      this.isAdd = true;
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const foodID = row.foodID || this.ids
      getFood(foodID).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改样品管理";
        this.isAdd = false;
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.foodID != null) {
            updateFood(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
              this.getAllFoodList();
            });
          } else {

            this.foodTypeForm.foodTypeParentID = this.form.foodTypeParentID;
            this.foodTypeForm.foodTypeName = this.form.foodName;
            this.foodTypeForm.isValid = this.form.isValid;
            //插入样品类别
            addFoodType(this.foodTypeForm).then(response =>{
              //查询插入的样品类别ID
              listFoodType(this.foodTypeForm).then(response =>{
                let result = response.rows;
                if(result.length > 0){
                    this.form.foodTypeID = result[0].foodTypeID;
                    addFood(this.form).then(response =>{
                      this.msgSuccess("新增成功");
                      this.open = false;
                      this.getList();
                      this.getFoodTypeList();
                      this.getAllFoodList();
                      console.log("==============",response);
                    });
                  }
                else{
                  console.log(result.length);
                }
              });
            });

          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      // const foodIDs = row.foodID || this.ids;
      const foodTypeIDs = row.foodTypeID || this.idLBs;
      const foodNames = row.foodName || this.foodNames;
      this.$confirm('是否确认删除样品"' + foodNames + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delFood(foodTypeIDs);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
          this.getFoodTypeList();
          this.getAllFoodList();
        }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有样品管理数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.exportLoading = true;
          return exportFood(queryParams);
        }).then(response => {
          this.download(response.msg);
          this.exportLoading = false;
        }).catch(() => {});
    },
    /** 导入按钮操作*/
    handleImport(){
      this.upload.title = "样品信息导入";
      this.upload.open = true;
    },
    /** 下载模板操作 */
    importTemplate() {
      importTemplate().then(response => {
        this.download(response.msg);
      });
    },
    // 文件上传中处理
    handleFileUploadProgress(event, file, fileList) {
      this.upload.isUploading = true;
    },
    // 文件上传成功处理
    handleFileSuccess(response, file, fileList) {
      this.upload.open = false;
      this.upload.isUploading = false;
      this.$refs.upload.clearFiles();
      this.$alert(response.msg, "导入结果", { dangerouslyUseHTMLString: true });
      this.getList();
      this.getFoodTypeList();
      this.getAllFoodList();
    },
    // 提交上传文件
    submitFileForm() {
      this.$refs.upload.submit();
    },
    changeValid(row){
      // let status = row.valid === true ? '启用' : '禁用';
      let flag = row.isValid; //保存点击之后v-model的值(1，0)
      console.log(flag);
      //保持switch点击前的状态
      row.isValid = row.isValid===1 ? 0 : 1;

      this.$confirm('是否改变'+row.foodName+'的状态？', "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() =>{
        flag ? row.isValid = 1 : row.isValid = 0; // 这一步很重要，row.showState会根据flag的值开启或关闭开关
        updateFood(row).then(response => {
          this.msgSuccess("修改成功");
          this.getList();
        });
      }).catch(() => {
        //这里处理取消操作时候的效果
        this.row.isValid =  !this.row.isValid;
      });

    }
  }
};
</script>

<style>
  .switch .el-switch__label {
    position: absolute;
    display: none;
    color: #fff !important;

  }
  /*打开时文字位置设置*/
  .switch .el-switch__label--right {
    z-index: 1;
  }
  /* 调整打开时文字的显示位子 */
  .switch .el-switch__label--right span{
    margin-right: 9px;
  }
  /*关闭时文字位置设置*/
  .switch .el-switch__label--left {
    z-index: 1;
  }
  /* 调整关闭时文字的显示位子 */
  .switch .el-switch__label--left span{
    margin-left: 9px;
  }
  /*显示文字*/
  .switch .el-switch__label.is-active {
    display: block;
  }
  /* 调整按钮的宽度 */
  .switch.el-switch .el-switch__core,
  .el-switch .el-switch__label {
    width: 60px !important;
    margin: 0;
  }
</style>
