<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="126px">
      <el-row type="flex" justify="space-around">
        <el-col :span="8">
          <el-form-item label="样品类别名称" prop="foodTypeID">
            <el-select placeholder="请选择样品类别" prop="foodTypeID" v-model="queryParams.foodTypeID" filterable clearable :loading="foodTypeLoading">
              <el-option
                v-for="item in foodTypeOptions"
                :key="item.foodTypeID"
                :label="item.foodTypeName"
                :value="item.foodTypeID"
                @keyup.enter.native="handleQuery"
              >
              </el-option>
            </el-select>
            <!--<el-input
              v-model="queryParams.foodTypeName"
              placeholder="请输入样品类别名称"
              clearable
              size="small"
              @keyup.enter.native="handleQuery"
            />-->
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="有效性" prop="isValid">
            <el-select placeholder="请选择标准类型" prop="isValid" v-model="queryParams.isValid" clearable>
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

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['limit:foodType:add']"
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
          v-hasPermi="['limit:foodType:edit']"
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
          v-hasPermi="['limit:foodType:remove']"
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
          v-hasPermi="['limit:foodType:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="foodTypeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" type="index" align="center">
        <template slot-scope="scope">
          <span>{{(queryParams.pageNum - 1) * queryParams.pageSize + scope.$index + 1}}</span>
        </template>
      </el-table-column>
      <el-table-column label="样品类别父名称" align="center" prop="foodTypeStr" />
      <el-table-column label="样品类别名称" align="center" prop="foodTypeName" />
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
            v-hasPermi="['limit:foodType:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['limit:foodType:remove']"
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

    <!-- 添加或修改样品类别管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="auto">
        <el-form-item label="样品类别层级" prop="foodTypeStr" >
          <el-col :span="15">
            <el-input v-model="form.foodTypeStr" placeholder="样品类别层级" :disabled="true"/>
          </el-col>
        </el-form-item>
        <el-form-item label="所属上一层级" prop="foodTypeSelect"  v-show="isAdd">
          <el-select placeholder="请选择所属上一层级" clearable filterable v-model="form.foodTypeParentID" size="medium" @change="mergeFoodTypeStr">
            <el-option
              v-for="item in foodTypeOptions"
              :key="item.foodTypeID"
              :label="item.foodTypeName"
              :value="item.foodTypeID"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="样品类别名称" prop="foodTypeName">
          <el-col :span="15">
            <el-input v-model="form.foodTypeName" placeholder="请输入样品类别名称" />
          </el-col>
        </el-form-item>
        <el-form-item label="有效性" prop="isValid">
          <el-select placeholder="请选择是否有效" prop="isValid" clearable size="medium" v-model="form.isValid">
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
  </div>
</template>

<script>
import { listFoodType, getFoodType, delFoodType, addFoodType, updateFoodType, exportFoodType } from "@/api/limit/foodType";
import {listAllFoodType} from "@/api/limit/foodType";
import {fail} from "../../../api/review/assignReview";
export default {
  name: "FoodType",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 导出遮罩层
      exportLoading: false,
      // 食品类别下拉加载
      foodTypeLoading: false,
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
      // 样品类别管理表格数据
      foodTypeList: [],
      //全部的样品类别信息
      foodTypeAllList:[],
      // 食品类别选项
      foodTypeOptions: [],
      //是否有效 1-有效 0-无效
      isValidOptions:[{
        value:1,
        label:"有效"
      },{
        value: 0,
        label: "无效"
      }],
      // 弹出层标题
      title: "",
      //是否是新增页面
      isAdd:true,
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        foodTypeParentID: null,
        foodTypeID: null,
        foodTypeName: null,
        detectionCenterID: null,
        isValid:null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        foodTypeParentID: [
          { required: true, message: "样品类别父ID不能为空", trigger: "blur" }
        ],
        foodTypeName: [
          { required: true, message: "样品类别名称不能为空", trigger: "blur" }
        ],
        isValid: [
          { required: true, message: "数据有效性标识 0-无效 1-有效", trigger: "blur" }
        ]
      }
    };
  },
  watch:{
    'form.foodTypeName':{
      handler(){
        if(!this.isAdd)
        {
          if(this.form.foodTypeStr != null)
          {
            let typeList = this.form.foodTypeStr.split("/");
            let typeListLen = typeList.length;
            typeList[typeListLen - 1] = this.form.foodTypeName;
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
          if(this.form.foodTypeParentID !== null)
          {
            this.form.foodTypeStr = this.mergeFoodTypeStr();
          }
          else
          {
            this.form.foodTypeStr = '';
            if(this.form.foodTypeName !== '')
            {
              this.form.foodTypeStr = this.form.foodTypeName;
            }
          }
        }
      }
    }
  },
  created() {
    this.getList();
    this.getFoodTypeList();
    this.getFoodTypeOptions();
  },
  methods: {
    /** 查询样品类别管理列表 */
    getList() {
      this.loading = true;
      listFoodType(this.queryParams).then(response => {
        this.foodTypeList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    /** 查询样品类别表*/
    getFoodTypeList(){

      listFoodType(this.queryParams).then(response => {
        this.foodTypeAllList = response.rows;
      });
    },
    getFoodTypeOptions(){
      this.foodTypeLoading = true;
      listFoodType().then(response => {
        this.foodTypeOptions = response.rows;
        this.foodTypeLoading = false;
      });
    },
    mergeFoodTypeStr(){

      let foodTypeStrList = [];
      //获取样品类别层级用于在界面显示
      let result;
      let foodParentID = this.form.foodTypeParentID;

      while (foodParentID !== 0){
        result = this.foodTypeOptions.find(a=> {
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

      if(this.form.foodTypeName){
        str += this.form.foodTypeName + "/";
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
        foodTypeID: null,
        foodTypeParentID: null,
        foodTypeName: null,
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
      // console.log("=reset=",this.queryParams);
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.foodTypeID)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.form.isValid = 1;
      this.open = true;
      this.title = "添加样品类别管理";
      this.isAdd = true;
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const foodTypeID = row.foodTypeID || this.ids;
      getFoodType(foodTypeID).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改样品类别管理";
        this.isAdd = false;
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.foodTypeID != null) {
            updateFoodType(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addFoodType(this.form).then(response => {
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
      const foodTypeIDs = row.foodTypeID || this.ids;
      this.$confirm('是否确认删除样品类别管理编号为"' + foodTypeIDs + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delFoodType(foodTypeIDs);
        }).then(() => {
          this.getList();
          this.getFoodTypeOptions();
          this.msgSuccess("删除成功");
        }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有样品类别管理数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.exportLoading = true;
          return exportFoodType(queryParams);
        }).then(response => {
          this.download(response.msg);
          this.exportLoading = false;
        }).catch(() => {});
    },
    changeValid(row){
      // let status = row.valid === true ? '启用' : '禁用';
      let flag = row.isValid; //保存点击之后v-model的值(1，0)
      //保持switch点击前的状态
      row.isValid = row.isValid===1 ? 0 : 1;
      this.$confirm('是否改变'+row.foodTypeName+'的状态？', "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() =>{
        flag ? row.isValid = 1 : row.isValid = 0; // 这一步很重要，row.showState会根据flag的值开启或关闭开关
        updateFoodType(row).then(response => {
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
