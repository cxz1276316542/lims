<template>
  <div class="app-container">


    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="100px">

      <el-form-item label="企业名称:" prop="enterpriseName">
        <el-input
          v-model="queryParams.enterpriseName"
          placeholder="请输入企业名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="企业地址:" prop="enterpriseAddress">
        <el-input
          v-model="queryParams.enterpriseAddress"
          placeholder="请输入企业地址"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="企业电话:" prop="enterprisePhone">
        <el-input
          v-model="queryParams.enterprisePhone"
          placeholder="请输入企业电话"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="企业负责人:" prop="Principal">
        <el-input
          v-model="queryParams.Principal"
          placeholder="请输入企业负责人"
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


    <h4 class="form-header h4">基本信息</h4>
    <el-form ref="form" :model="form" label-width="80px">
      <el-row>
        <el-col :span="8" :offset="2">
          <el-form-item label="用户昵称" prop="nickName">
            <el-input v-model="form.nickName" disabled />
          </el-form-item>
        </el-col>
        <el-col :span="8" :offset="2">
          <el-form-item label="登录账号" prop="phonenumber">
            <el-input  v-model="form.userName" disabled />
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>

    <h4 class="form-header h4">详细信息</h4>
    <div class="app-container">


      <el-row :gutter="10" class="mb8">
        <el-col :span="1.5">
          <el-button
            type="primary"
            plain
            icon="el-icon-plus"
            size="mini"
            @click="handleAdd"
            v-hasPermi="['detection:Enterprise:add']"
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
            v-hasPermi="['detection:Enterprise:edit']"
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
            v-hasPermi="['detection:Enterprise:remove']"
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
            v-hasPermi="['detection:Enterprise:export']"
          >导出</el-button>
        </el-col>
        <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
      </el-row>

      <el-table v-loading="loading" :data="EnterpriseList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
<!--        <el-table-column label="企业ID" align="center" prop="enterpriseID" />-->
<!--        <el-table-column label="客户ID" align="center" prop="userId" />-->
<!--        <el-table-column label="" align="center" prop="enterpriseType" />-->
        <el-table-column label="企业名称" align="center" prop="enterpriseName" />
        <el-table-column label="企业地址" align="center" prop="enterpriseAddress" />
        <el-table-column label="企业电话" align="center" prop="enterprisePhone" />
        <el-table-column label="企业邮政编码" align="center" prop="postalCode " />
        <el-table-column label="企业负责人" align="center" prop="Principal" />
        <el-table-column label="企业邮箱" align="center" prop="enterpriseEmail" />
        <el-table-column label="创建者" align="center" prop="createBy" />
        <el-table-column label="更新者" align="center" prop="updateBy" />
        <el-table-column label="创建时间" align="center" prop="createTime" width="180">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
          </template>
        </el-table-column>
        <el-table-column label="更新时间" align="center" prop="updateTime" width="180">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d}') }}</span>
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
              v-hasPermi="['detection:Enterprise:edit']"
            >修改</el-button>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
              v-hasPermi="['detection:Enterprise:remove']"
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

      <!-- 添加或修改企业信息对话框 -->
      <el-dialog :title="title" :visible.sync="open" width="550px" append-to-body>
        <el-form ref="form" :model="form" :rules="rules" label-width="120px">
          <el-form-item label="企业性质：" prop="enterpriseType">
            <el-select v-model="value" placeholder="请选择企业性质">
              <el-option
                v-for="item in option"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="企业名称：" prop="enterpriseName">
            <el-input v-model="form.enterpriseName" placeholder="请输入企业名称" />
          </el-form-item>
          <el-form-item label="企业地址：" prop="enterpriseAddress">
            <div>
              <el-cascader
                :options="options"
                ref="cascaderHandle"
                clearable
                v-model="selectedOptions"
                @change="handleChange">
              </el-cascader>
            </div>

            <el-input v-model="form.enterpriseAddress " placeholder="请输入企业详细地址" />
          </el-form-item>
          <el-form-item label="企业电话：" prop="enterprisePhone">
            <el-input v-model="form.enterprisePhone" placeholder="请输入企业电话" />
          </el-form-item>
          <el-form-item label="企业邮政编码：" prop="postalCode ">
            <el-input v-model="form.postalCode " placeholder="请输入企业邮政编码" />
          </el-form-item>
          <el-form-item label="企业负责人：" prop="Principal">
            <el-input v-model="form.Principal" placeholder="请输入企业负责人" />
          </el-form-item>
          <el-form-item label="企业邮箱：" prop="enterpriseEmail">
            <el-input v-model="form.enterpriseEmail" placeholder="请输入企业邮箱" />
          </el-form-item>
          <el-form-item label="备注：" prop="remark">
            <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </el-dialog>
    </div>



    <el-form label-width="100px">
      <el-form-item style="text-align: center;margin-left:-120px;margin-top:30px;">

        <el-button @click="close()">返回</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  import { getAuthRole } from "@/api/system/user";
  import { regionData, CodeToText } from 'element-china-area-data';
  import { listEnterprise, getEnterprise, delEnterprise, addEnterprise, updateEnterprise, exportEnterprise } from "@/api/detection/Enterprise";

  export default {
    name: "AuthRole",
    data() {
      return {
        options: regionData,
        selectedOptions: [],
        // 遮罩层
        loading: true,
        // 分页信息
        total: 0,
        pageNum: 1,
        pageSize: 10,
        // 选中角色编号
        roleIds:[],
        // 角色信息
        roles: [],
        // 用户信息
        form: {},
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
        // 企业信息表格数据
        EnterpriseList: [],
        //企业地址
        Address:null,
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        //企业性质下拉选择
        option: [{
          value: '0',
          label: '企业'
        }, {
          value: '1',
          label: '个人'
        }],
        value: '',
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          userId:null,
          enterpriseType: null,
          enterpriseName: null,
          enterpriseAddress: null,
          enterprisePhone: null,
          postalCode : null,
          Principal: null,
          enterpriseEmail: null,
          createBy: null,
          updateBy: null,
          createTime: null,
          updateTime: null,
          remark: null
        },
        // 表单校验
        rules: {
          enterpriseType: [
            { required: true, message: "不能为空", trigger: "blur" }
          ],
          enterpriseName: [
            { required: true, message: "企业名称不能为空", trigger: "blur" }
          ],
          EAddress: [
            { required: true, message: "企业地址不能为空", trigger: "blur" }
          ],
          enterprisePhone: [
            { required: true, message: "企业电话不能为空", trigger: "blur" }
          ],
          createBy: [
            { required: true, message: "创建者不能为空", trigger: "blur" }
          ],
          createTime: [
            { required: true, message: "创建时间不能为空", trigger: "blur" }
          ],
        }
      };
    },
    created() {
      const userId = this.$route.params && this.$route.params.userId;
      if (userId) {
        this.loading = true;
        getAuthRole(userId).then((response) => {
          this.form = response.user;
          this.roles = response.roles;
          this.total = this.roles.length;
          this.$nextTick(() => {
            this.roles.forEach((row) => {
              if (row.flag) {
                this.$refs.table.toggleRowSelection(row);
              }
            });
          });
          this.loading = false;
        });
      }
     this.getList();
    },
    methods: {
      handleChange(val) {
        this.Address = CodeToText[val[0]] + CodeToText[val[1]] + CodeToText[val[2]]
        this.form.enterpriseAddress = ''
        console.log(this.enterpriseAddress)
        // 将区域码转换成汉字
        console.log(CodeToText[val[0]])
        console.log(CodeToText[val[1]])
        console.log(CodeToText[val[2]])
      },
      /** 单击选中行数据 */
      clickRow(row) {
        this.$refs.table.toggleRowSelection(row);
      },
      // 保存选中的数据编号
      getRowKey(row) {
        return row.roleId;
      },

      /** 关闭按钮 */
      close() {
        this.$store.dispatch("tagsView/delView", this.$route);
        this.$router.push({ path: "/user/clientMan" });
      },
      /** 查询企业信息列表 */
      getList() {
        debugger
        this.loading = true;

        const userId = this.$route.params && this.$route.params.userId;
        this.queryParams.userId=userId;
        if (userId) {
          listEnterprise(this.queryParams).then(response => {
            this.EnterpriseList = response.rows;
            this.total = response.total;
            this.loading = false;
          });
        }
      },
      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      // 表单重置
      reset() {
        this.value = '',
        this.form = {
          enterpriseID: null,
          userId: null,
          enterpriseType: null,
          enterpriseName: null,
          enterpriseAddress: null,
          enterprisePhone: null,
          postalCode : null,
          Principal: null,
          enterpriseEmail: null,
          createBy: null,
          updateBy: null,
          createTime: null,
          updateTime: null,
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
        this.ids = selection.map(item => item.enterpriseID)
        this.single = selection.length!==1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加企业信息";

      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();

        const enterpriseID = row.enterpriseID || this.ids
        getEnterprise(enterpriseID).then(response => {
          this.form = response.data;

          this.open = true;
          this.title = "修改企业信息";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.form.enterpriseType = this.value

        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.enterpriseID != null) {
              console.log(this.selectedOptions)

              this.form.enterpriseAddress = this.Address + this.form.enterpriseAddress

              updateEnterprise(this.form).then(response => {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              this.selectedOptions = []
              this.form.enterpriseAddress = this.Address + this.form.enterpriseAddress

              console.log(this.value)
              console.log(this.form.enterpriseAddress)
              this.form.userId = this.$route.params && this.$route.params.userId;
              this.form.createBy = this.$route.params && this.$route.params.userId;
              this.form.updateBy = this.$route.params && this.$route.params.userId;

              addEnterprise(this.form).then(response => {
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
        const enterpriseIDs = row.enterpriseID || this.ids;
        this.$confirm('是否确认删除企业信息编号为"' + enterpriseIDs + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delEnterprise(enterpriseIDs);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(() => {});
      },
      /** 导出按钮操作 */
      handleExport() {
        const queryParams = this.queryParams;
        this.$confirm('是否确认导出所有企业信息数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.exportLoading = true;
          return exportEnterprise(queryParams);
        }).then(response => {
          this.download(response.msg);
          this.exportLoading = false;
        }).catch(() => {});
      }
    }
  };
</script>
