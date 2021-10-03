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
              @click="toCommissionSampleSearch"
              round
      >登记查询</el-button>
    </el-row>
    <el-row :gutter="10" style="height: 60px; margin-top: 30px">
      <el-radio-group v-model="searchSource" @change="getList">
        <el-radio :label="1">委托检品登记</el-radio>
        <el-radio :label="2">检品登记</el-radio>
        <el-radio :label="3">退回修改检品登记</el-radio>
      </el-radio-group>
    </el-row>
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
                type="primary"
                plain
                icon="el-icon-check"
                size="mini"
                :disabled="multiple"
                @click="handleSubmit"
                v-hasPermi="['detection:commissionSample:edit']"
        >提交</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['detection:commissionSample:add']"
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
          v-hasPermi="['detection:commissionSample:edit']"
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
          v-hasPermi="['detection:commissionSample:remove']"
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
          v-hasPermi="['detection:commissionSample:export']"
        >导出</el-button>
      </el-col>
<!--      <el-col :span="1.5">
        <el-button
                type="warning"
                plain
                size="mini"
                @click="getSubmitList(4)"
                v-hasPermi="['detection:commissionSample:list']"
        >未提交</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
                type="warning"
                plain
                size="mini"
                @click="getSubmitList(5)"
                v-hasPermi="['detection:commissionSample:list']"
        >已提交</el-button>
      </el-col>-->
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
      <el-table-column fixed="right" label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <span v-if="scope.row.status != 12 && scope.row.status != 21 &&scope.row.status != 20">
            <el-button
                    size="mini"
                    type="text"
                    icon="el-icon-edit"
                    @click="handleUpdate(scope.row)"
                    v-hasPermi="['detection:commissionSample:edit']"
            >修改</el-button>
          <el-button
                  size="mini"
                  type="text"
                  icon="el-icon-delete"
                  @click="handleDelete(scope.row)"
                  v-hasPermi="['detection:commissionSample:remove']"
          >删除</el-button>
          </span>
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

    <!-- 修改委托检品对话框 -->
    <el-dialog :title="title" :visible.sync="editOpen" width="1500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" :inline="true" label-width="150px">
        <el-card>
        <el-row :gutter="10" class="mb8">
          <h3>客户基本信息</h3>
        </el-row>
        <el-row :gutter="10" class="mb8">
          <el-form-item label="客户账号" prop="customerID">
            <el-select filterable allow-create v-model="form.customerID" placeholder="请选择" :rules="rules.customerID">
              <el-option
                v-for="item in customerList"
                :key="item.customerID"
                :label="item.name"
                :value="item.customerID">
              </el-option>
            </el-select>
          </el-form-item>
<!--          <el-form-item >
            <el-button type="primary" @click="searchLastCommissionRegister()">搜索</el-button>
          </el-form-item>-->
          <el-form-item label="单位地址" prop="companyID" :rules="rules.companyID">
            <el-select clearable v-model="form.companyID" placeholder="请选择单位地址">
              <el-option v-for="item in companyList"
                      :key="item.companyID"
                      :label="item.companyAddress"
                      :value="item.companyID"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="报告邮寄地址" prop="receiptAddress">
            <el-input v-model="form.receiptAddress" size="medium" placeholder="报告邮寄地址"></el-input>
          </el-form-item>
        </el-row>
        </el-card>
        <el-card>
        <el-row :gutter="10" class="mb8">
          <h3>检品基本信息</h3>
        </el-row>
        <el-row :gutter="10" class="mb8">
          <el-form-item label="送样人名称" prop="sampleSenderName" :rules="sampleInformationRules.sampleSenderName">
            <el-input v-model="form.sampleSenderName" size="medium" placeholder="请输入送样人名称" />
          </el-form-item>
          <el-form-item label="送样人电话" prop="sampleSenderPhone" :rules="sampleInformationRules.sampleSenderPhone">
            <el-input v-model="form.sampleSenderPhone" size="medium" placeholder="请输入送样人电话" />
          </el-form-item>
          <el-form-item label="检测标准" prop="detectionStandardID" :rules="sampleInformationRules.detectionStandardID">
            <el-select v-model="form.detectionStandardID" size="medium" placeholder="请输入检测标准" >
              <el-option v-for="item in detectionStandardOptions"
                         :key="item.detectionStandardID"
                         :label="item.detectionNumber"
                         :value="item.detectionStandardID"
              />
            </el-select>
          </el-form-item>
        </el-row>
        <el-row :gutter="10" class="mb8">
          <el-form-item label="检品名称" prop="sampleName" :rules="sampleInformationRules.sampleName">
            <el-input v-model="form.sampleName"size="medium" placeholder="请输入检品名称" />
          </el-form-item>
          <el-form-item label="检品学名" prop="sampleScientificName" :rules="sampleInformationRules.sampleScientificName">
            <el-select v-model="form.sampleScientificName" filterable size="medium" placeholder="请输入检品学名">
              <el-option v-for="item in sampleAcademicOptions"
                         :key="item"
                         :value="item"
                         :label="item"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="检品状态" prop="sampleStatus" :rules="sampleInformationRules.sampleStatus">
            <el-select v-model="form.sampleStatus" size="medium" placeholder="请选择检品状态">
              <el-option
                      v-for="item in sampleStatusList"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
        </el-row>
        <el-row :gutter="10" class="mb8">
          <el-form-item label="检品数量"  prop="sampleQuantity" :rules="sampleInformationRules.sampleQuantity">
            <el-input v-model="form.sampleQuantity" size="medium" placeholder="请输入检品数量" >
            </el-input>
          </el-form-item>
          <el-form-item label="检品计量单位" prop="measureUnit" :rules="sampleInformationRules.measureUnit">
            <el-select filterable allow-create v-model="form.measureUnit"
                       size="medium" default-first-option placeholder="请输入检品计量单位">
              <el-option v-for="item in measureUnitList"
                         :key="item.measureID" :label="item.measureName" :value="item.measureID">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="检品描述" prop="sampleDescriptionArray" :rules="sampleInformationRules.sampleDescriptionArray">
            <el-select filterable multiple v-model="form.sampleDescriptionArray"
                       size="medium"  placeholder="请选择检品描述">
              <el-option v-for="it in form.detectionDescriptionOption"
                         :key="it.name" :label="it.name" :value="it.name">
              </el-option>
            </el-select>
          </el-form-item>
        </el-row>

        <el-row :gutter="10" class="mb8">
          <!--:rules="sampleInformationRules.samplingBase"-->
          <el-form-item label="抽样基数" prop="samplingBase" >
            <el-input v-model="form.samplingBase" size="medium" placeholder="请输入抽样基数" />
          </el-form-item>
          <el-form-item label="检品生产单位" prop="productionUnit" :rules="sampleInformationRules.productionUnit">
            <el-input v-model="form.productionUnit" size="medium" placeholder="请输入检品生产单位" />
          </el-form-item>
          <el-form-item label="生产单位地址" prop="productionUnitAddress" :rules="sampleInformationRules.productionUnitAddress">
            <el-input v-model="form.productionUnitAddress" size="medium" placeholder="请输入生产单位地址" />
          </el-form-item>
        </el-row>
        <el-row :gutter="10" class="mb8">
          <el-form-item label="采样时间" prop="samplingTime">
            <el-date-picker clearable size="medium"
                            v-model="form.samplingTime"
                            type="date"
                            value-format="yyyy-MM-dd"
                            placeholder="选择采样时间">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="优先级" prop="priority">
            <el-select v-model="form.priority" size="medium" placeholder="请选择优先级">
              <el-option
                      v-for="item in priorities"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="采样方式" prop="samplingMode">
            <el-select v-model="form.samplingMode" placeholder="请选择采样方式">
              <el-option
                      v-for="item in samplingModes"
                      :key="item.value1"
                      :label="item.label"
                      :value="item.value1">
              </el-option>
            </el-select>
          </el-form-item>
        </el-row>
        <el-row :gutter="10" class="mb8">
          <el-form-item label="检测类别 " prop="detectionCategory" :rules="sampleInformationRules.detectionCategory">
            <el-select v-model="form.detectionCategory" size="medium" placeholder="请选择检测类别">
              <el-option
                      v-for="item in detectionCategories"
                      :key="item.value2"
                      :label="item.label"
                      :value="item.value2">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="报告领取方式" prop="reportReceiveWay" :rules="sampleInformationRules.reportReceiveWay">
            <el-select v-model="form.reportReceiveWay" size="medium" placeholder="请选择报告领取方式">
              <el-option
                      v-for="item in reportReceiveWays"
                      :key="item.value3"
                      :label="item.label"
                      :value="item.value3">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="是否回收剩余检品" prop="recycle" :rules="sampleInformationRules.recycle">
            <el-select v-model="form.recycle" size="medium" placeholder="请输入是否回收剩余检品">
              <el-option
                      v-for="item in recycles"
                      :key="item.value4"
                      :label="item.label"
                      :value="item.value4">
              </el-option>
            </el-select>
          </el-form-item>
        </el-row>
        <el-row :gutter="10" class="mb8">
          <el-form-item label="检品图像">
            <el-upload action="#" :before-upload = "sampleImageUpload">
              <el-button size="medium" type="primary" icon="el-icon-upload">点击上传</el-button>
            </el-upload>
          </el-form-item>
            <el-form-item label="检品编号">
              <el-input v-model="form.sampleNumber" size="medium" :disabled="true" placeholder="请输入检品编号" />
            </el-form-item>
        </el-row>
        </el-card>
      </el-form>
      <div slot="footer" class="dialog-footer" align="center">
        <el-button type="primary" @click="saveForm">保 存</el-button>
        <el-button type="success" @click="submitForm">提 交</el-button>
        <el-button @click="editCancel">取 消</el-button>
      </div>
    </el-dialog>
    <!-- 添加委托检品对话框 -->
    <el-dialog :title="title" :visible.sync="addOpen" width="1500px" append-to-body>
      <el-card>
      <el-form ref="addForm" :model="addForm" :inline="true" label-width="150px">
        <el-row :gutter="10" class="mb8">
          <h3>客户基本信息</h3>
        </el-row>
        <el-row :gutter="10" class="mb8">
          <el-form-item label="客户账号" prop="customerID" :rules="rules.customerID">
            <el-select filterable clearable allow-create v-model="addForm.customerID" placeholder="请选择">
              <el-option
                      v-for="item in customerList"
                      :key="item.customerID"
                      :label="item.name"
                      :value="item.customerID">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item >
            <el-button type="primary" @click="searchLastCommissionRegister()">复制</el-button>
          </el-form-item>
          <el-form-item label="单位地址" prop="companyID" :rules="rules.companyID">
            <el-select clearable v-model="addForm.companyID" placeholder="请选择单位地址">
              <el-option v-for="item in companyList"
                         :key="item.companyID"
                         :label="item.companyAddress"
                         :value="item.companyID"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="报告邮寄地址" prop="receiptAddress">
            <el-input v-model="addForm.receiptAddress" size="medium" placeholder="报告邮寄地址"></el-input>
          </el-form-item>
        </el-row>
      </el-form>
      </el-card>

      <el-form ref="sampleInfo" :model="addForm" :inline="true" label-width="150px">
        <el-row :gutter="20" v-for="(item,index) in addForm.sampleList" :key="index">
          <el-card>
          <el-row :gutter="10" class="mb8">
            <h3>检品基本信息</h3>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="7">
              <el-form-item label="送样人名称" :prop="'sampleList.'+index+'.sampleSenderName'" :rules="sampleInformationRules.sampleSenderName">
                <el-input v-model="item.sampleSenderName"  placeholder="请输入送样人名称" />
              </el-form-item>
            </el-col>
            <el-col :span="7">
              <el-form-item label="送样人电话" :prop="'sampleList.'+index+'.sampleSenderPhone'" :rules="sampleInformationRules.sampleSenderPhone">
                <el-input v-model="item.sampleSenderPhone"  placeholder="请输入送样人电话" />
              </el-form-item>
            </el-col>
            <el-col :span="7">
              <el-form-item label="检测标准" :prop="'sampleList.'+index+'.detectionStandardID'" :rules="sampleInformationRules.detectionStandardID">
                <el-select v-model="item.detectionStandardID"  placeholder="请输入检测标准" >
                  <el-option v-for="it in detectionStandardOptions"
                             :key="it.detectionStandardID"
                             :label="it.detectionNumber"
                             :value="it.detectionStandardID"
                  />
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
        <el-row :gutter="20">
          <el-col :span="7">
            <el-form-item label="检品名称" :prop="'sampleList.'+index+'.sampleName'" :rules="sampleInformationRules.sampleName">
              <el-input v-model="item.sampleName"size="medium" placeholder="请输入检品名称" />
            </el-form-item>
          </el-col>
          <el-col :span="7">
            <el-form-item label="检品学名" :prop="'sampleList.'+index+'.sampleScientificName'" :rules="sampleInformationRules.sampleScientificName">
              <el-select v-model="item.sampleScientificName" filterable size="medium" placeholder="请输入检品学名"  @change="searchIDAndName(index)">
                <el-option v-for="it in sampleAcademicOptions"
                           :key="it"
                           :value="it"
                           :label="it"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="7">
            <el-form-item label="检品状态" :prop="'sampleList.'+index+'.sampleStatus'" :rules="sampleInformationRules.sampleStatus">
              <el-select v-model="item.sampleStatus" size="medium" placeholder="请选择检品状态">
                <el-option
                        v-for="it in sampleStatusList"
                        :key="it.value"
                        :label="it.label"
                        :value="it.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20" >
          <el-col :span="7">
            <el-form-item label="检品数量"  :prop="'sampleList.'+index+'.sampleQuantity'" :rules="sampleInformationRules.sampleQuantity">
              <el-input v-model="item.sampleQuantity" size="medium" placeholder="请输入检品数量" >
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="7">
            <el-form-item label="检品计量单位" :prop="'sampleList.'+index+'.measureUnit'" :rules="sampleInformationRules.measureUnit">
              <el-select filterable allow-create v-model="item.measureUnit"
                         size="medium" default-first-option placeholder="请输入检品计量单位">
                <el-option v-for="it in measureUnitList"
                           :key="it.measureID" :label="it.measureName" :value="it.measureID">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="7">
            <el-form-item label="检品描述" :prop="'sampleList.'+index+'.sampleDescriptionArray'" :rules="sampleInformationRules.sampleDescriptionArray">
              <el-select filterable multiple v-model="item.sampleDescriptionArray"
                         size="medium"  placeholder="请选择检品描述">
                <el-option v-for="it in item.detectionDescriptionOption"
                           :key="it.name" :label="it.name" :value="it.name">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20" >
          <el-col :span="7">
            <!--:rules="sampleInformationRules.samplingBase"-->
            <el-form-item label="抽样基数" :prop="'sampleList.'+index+'.samplingBase'" >
              <el-input v-model="item.samplingBase" size="medium" placeholder="请输入抽样基数" />
            </el-form-item>
          </el-col>
          <el-col :span="7">
            <el-form-item label="检品生产单位" :prop="'sampleList.'+index+'.productionUnit'" :rules="sampleInformationRules.productionUnit">
              <el-input v-model="item.productionUnit" size="medium" placeholder="请输入检品生产单位" />
            </el-form-item>
          </el-col>
          <el-col :span="7">
            <el-form-item label="生产单位地址" :prop="'sampleList.'+index+'.productionUnitAddress'" :rules="sampleInformationRules.productionUnitAddress">
              <el-input v-model="item.productionUnitAddress" size="medium" placeholder="请输入生产单位地址" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="7">
            <el-form-item label="采样时间" :prop="'sampleList.'+index+'.samplingTime'">
              <el-date-picker clearable size="medium"
                              v-model="item.samplingTime"
                              type="date"
                              value-format="yyyy-MM-dd"
                              placeholder="选择采样时间">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="7">
            <el-form-item label="优先级" :prop="'sampleList.'+index+'.priority'">
              <el-select v-model="item.priority" size="medium" placeholder="请选择优先级">
                <el-option
                        v-for="it in priorities"
                        :key="it.value"
                        :label="it.label"
                        :value="it.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="7">
            <el-form-item label="采样方式" :prop="'sampleList.'+index+'.samplingMode'">
              <el-select v-model="item.samplingMode" placeholder="请选择采样方式">
                <el-option
                        v-for="it in samplingModes"
                        :key="it.value1"
                        :label="it.label"
                        :value="it.value1">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="7">
            <el-form-item label="检测类别 " :prop="'sampleList.'+index+'.detectionCategory'" :rules="sampleInformationRules.detectionCategory">
              <el-select v-model="item.detectionCategory" size="medium" placeholder="请选择检测类别">
                <el-option
                        v-for="it in detectionCategories"
                        :key="it.value2"
                        :label="it.label"
                        :value="it.value2">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="7">
            <el-form-item label="报告领取方式" :prop="'sampleList.'+index+'.reportReceiveWay'" :rules="sampleInformationRules.reportReceiveWay">
              <el-select v-model="item.reportReceiveWay" size="medium" placeholder="请选择报告领取方式">
                <el-option
                        v-for="it in reportReceiveWays"
                        :key="it.value3"
                        :label="it.label"
                        :value="it.value3">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="7">
            <el-form-item label="是否回收剩余检品" :prop="'sampleList.'+index+'.recycle'" :rules="sampleInformationRules.recycle">
              <el-select v-model="item.recycle" size="medium" placeholder="请输入是否回收剩余检品">
                <el-option
                        v-for="it in recycles"
                        :key="it.value4"
                        :label="it.label"
                        :value="it.value4">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>

        </el-row>
        <el-row :gutter="10">
          <el-col :span="7">
          <el-form-item label="检品图像" :prop="'sampleList.'+index+'sampleImage'">
            <el-upload action="#" :before-upload="addSampleImageUpload">
              <el-button size="medium" type="primary" icon="el-icon-upload" @click="getSampleImageIndex(index)">点击上传</el-button>
            </el-upload>
          </el-form-item>
          </el-col>
        </el-row>
        <el-form-item>
          <el-button type="danger" v-if="addForm.sampleList.length >1" size="small" plain
                     @click="removeRow(index)">
            删除</el-button>
        </el-form-item>
          </el-card>
      </el-row>
        <el-row :gutter="20" style="margin-top: 40px">
          <el-col :span="20" :push="18">
            <el-form-item>
              <el-button icon="el-icon-plus" size="small" @click="addSampleInfo">新增样品基本信息</el-button>
              <el-button icon="el-icon-refresh" size="small" @click="resetSampleInfo">重置所有样品信息</el-button>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer" align="center">
        <el-button type="primary" @click="saveAddForm">保 存</el-button>
        <el-button type="success" @click="submitAddForm">提 交</el-button>
        <el-button @click="addCancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listCommissionSample, getCommissionSample, delCommissionSample, addCommissionSample,
  updateCommissionSample, exportCommissionSample, uploadSampleImage,getDetectionStandards
,getSampleAcademicNameList, submitSampleBatch,findValidMeasureUnit,
  searchAllCustomers,searchAllCompanies,searchLastRecord,findIDAndName} from "@/api/detection/commissionSample";
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
        sampleDescriptionArray: [],
        recycle: null,
        sampleImage: null,
        QRcodeAddress: null,
        electronicSignature: null,
        reviewStatus: null,
        sign: null,
        operatorID: null,
        operationTime: null,
        receiptAddress: null,
        detectionDescriptionOption: [],
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
            sampleDescriptionArray: [],
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
            // 检品描述选项
            detectionDescriptionOption: [],
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
        sampleSenderPhone:[
          { required: true, pattern: /^1[34578]\d{9}$/,message: "请输入正确的手机号码", trigger: "blur" }
        ],
        detectionStandardID:[
          { required: true, message: "检测标准不能为空", trigger: "change" }
        ],
        sampleName:[
          { required: true, message: "检品名称不能为空", trigger: "blur" }
        ],
        sampleScientificName:[
          { required: true, message: "检品学名不能为空", trigger: "change" }
        ],
        sampleStatus:[
          { required: true, message: "检品状态不能为空", trigger: "change" }
        ],
        sampleQuantity:[
          { required: true, message: "检品数量不能为空", trigger: "blur" }
        ],
        measureUnit:[
          { required: true, message: "计量单位不能为空", trigger: "change" }
        ],
        sampleDescriptionArray:[
          { required: true, message: "检品描述不能为空", trigger: "change" }
        ],
        samplingBase:[
          { required: true, message: "抽样基数不能为空", trigger: "blur" }
        ],
        productionUnit:[
          { required: true, message: "检品生产单位不能为空", trigger: "blur" }
        ],
        productionUnitAddress:[
          { required: true, message: "生产单位地址不能为空", trigger: "blur" }
        ],
        detectionCategory:[
          { required: true, message: "检测类别不能为空", trigger: "change" }
        ],
        reportReceiveWay:[
          { required: true, message: "报告领取方式不能为空", trigger: "change" }
        ],
        recycle:[
          { required: true, message: "是否回收剩余检品不能为空", trigger: "change" }
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
      this.queryParams.searchSource = this.searchSource
      listCommissionSample(this.queryParams).then(response => {
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
    /**提交按钮操作*/
    handleSubmit(){
      const sample = this.submitList;
      if(null == this.submitList || this.submitList.length == 0){
        this.msgError("请勾选检品提交")
        return;
      }
      const sampleIDList = [];
      for(let i = 0; i < sample.length; i++){
        if(sample[i].status == 12){
          this.msgError("委托检品ID为【"+sample[i].sampleID + "】 编号为【"+sample[i].sampleNumber+"】提交了")
          return;
        }else if(sample[i].status == 21){
          this.msgError("委托检品ID为【"+sample[i].sampleID + "】 编号为【"+sample[i].sampleNumber+"】受理审核通过")
          return;
        }else if(sample[i].status == 20){
          this.msgError("委托检品ID为【"+sample[i].sampleID + "】 编号为【"+sample[i].sampleNumber+"】受理不审核通过")
          return;
        }
        sampleIDList.push(sample[i].sampleID)
      }
      submitSampleBatch(sampleIDList).then(response => {
        this.msgSuccess("提交成功");
        this.getList();
      });
    },
    getSubmitList(flag){
      this.queryParams.reviewStatus = flag
      this.getList()
    }
    ,
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.addOpen = true;
      this.title = "添加检品登记";
      this.findAllCustomers();
    },
    // 修改按钮
    handleEdit(){
      this.reset();
      this.editOpen = true;
      this.title = "修改检品登记";
      this.findAllCustomers();
    }
    ,
    /** 修改按钮操作 */
    handleUpdate(row) {
      // 判断业务状态
      const status = row.status || this.submitList[0].status
      if(status == 12){
        this.msgError("委托检品ID为【"+this.submitList[0].sampleID + "】 编号为【"+this.submitList[0].sampleNumber+"】提交了")
        return;
      }else if(status == 21){
        this.msgError("委托检品ID为【"+this.submitList[0].sampleID + "】 编号为【"+this.submitList[0].sampleNumber+"】受理审核通过")
        return;
      }else if(status == 20){
        this.msgError("委托检品ID为【"+this.submitList[0].sampleID + "】 编号为【"+this.submitList[0].sampleNumber+"】受理不审核通过")
        return;
      }
      this.reset();
      this.findAllCustomers();
      const cID = row.customerID || this.customerIDs
      this.findAllCompanies(cID);
      const sampleID = row.sampleID || this.ids
      getCommissionSample(sampleID).then(response => {

        findIDAndName(response.data.sampleScientificName).then(res=>{
          response.data.detectionDescriptionOptiondetectionDescriptionOption= res.data
          this.form = response.data
          this.editOpen = true;
          this.title = "修改委托检品";
        });
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.$confirm('是否确认提交?', "提示", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "info"
          }).then(()=>{
            this.form.reviewStatus = '12'
            if (this.form.sampleID != null) {
              updateCommissionSample(this.form).then(response => {
                this.msgSuccess("提交成功");
                this.editOpen = false;
                this.resetSampleInfo()
                this.getList();
              });
            } else {
              addCommissionSample(this.addForm).then(response => {
                this.msgSuccess("提交成功");
                this.editOpen = false;
                this.resetSampleInfo()
                this.getList();
              });
            }
          })
        }
      });
    },
    // 匹配样品描述数组拼接成字符串
    changeArrayToString(){
      for(let i = 0; i < this.addForm.sampleList.length; ++i){
        let str = '';
        for(let j = 0; j < this.addForm.sampleList[i].sampleDescriptionArray.length; ++j){
           str += this.addForm.sampleList[i].sampleDescriptionArray[j];
           if(j < this.addForm.sampleList[i].sampleDescriptionArray.length - 1)
             str += ',';
        }
        this.addForm.sampleList[i].sampleDescription = str
      }
    },
    // 提交新增表单
    submitAddForm(){
      this.$refs["addForm"].validate(valid1=>{
        if(valid1){
          this.$refs["sampleInfo"].validate(valid2=>{
            if(valid2){
              this.$confirm('是否确认提交?', "提示", {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "info"
              }).then(() => {
                // 修改委托检品状态为12（业务提交状态）
                for(let i = 0; i < this.addForm.sampleList.length; i++){
                  this.addForm.sampleList[i].reviewStatus = '12'
                }
                addCommissionSample(this.addForm).then(response => {
                  this.msgSuccess("提交成功");
                  this.addOpen = false;
                  this.resetSampleInfo()
                  this.getList();
                });
              }).catch(() => {});
            }
          })
        }
      })
    },
    /** 保存按钮 */
    saveForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.$confirm('是否确认保存?', "提示", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "info"
          }).then(()=>{
            this.form.reviewStatus = 11
            updateCommissionSample(this.form).then(response => {
              this.msgSuccess("保存成功");
              this.editOpen = false;
              this.getList();
            });
          })
        }
      });
    },
    /**提交保存提交*/
    saveAddForm(){
      this.$refs["addForm"].validate(valid1=>{
        if(valid1){
          this.$refs["sampleInfo"].validate(valid2=>{
            if(valid2){
              this.$confirm('是否确认保存?', "提示", {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "info"
              }).then(() => {
                // 修改委托检品状态为11（业务保存状态）
                for(let i = 0; i < this.addForm.sampleList.length; i++){
                  this.addForm.sampleList[i].reviewStatus = '11'
                }
                return addCommissionSample(this.addForm)
              }).then(()=>{
                this.msgSuccess("保存成功");
                this.addOpen = false;
                this.getList();
              }).catch(() => {});
            }
          })
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const sampleIDs = row.sampleID || this.ids;
      this.$confirm('是否确认删除委托检品编号为"' + sampleIDs + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delCommissionSample(sampleIDs);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有委托检品数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.exportLoading = true;
          return exportCommissionSample(queryParams);
        }).then(response => {
          this.download(response.msg);
          this.exportLoading = false;
        }).catch(() => {});
    },
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
    // 修改上传图片
    sampleImageUpload(img){
      let formData = new FormData();
      if(null == img){
        return;
      }
      formData.append("sampleImage", img);
      uploadSampleImage(formData).then(res=>{
        this.form.sampleImage = res.data
        this.msgSuccess("图片上传成功")
      })
    },
    // 获取上传图片的检品索引
    getSampleImageIndex(id){
      this.sampleImageIndex = id
    },
    // 新增上传图片
    addSampleImageUpload(img){
      let formData = new FormData();
      if(null == img){
        return false;
      }
      formData.append("sampleImage", img);
      uploadSampleImage(formData).then(res=>{
        this.addForm.sampleList[this.sampleImageIndex].sampleImage = res.data
        this.msgSuccess("图片上传成功");
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
    // 根据客户ID查询企业ID和地址
    findAllCompanies(id){
      if(id == null || id == 'undefined' || id == ''){
        return;
      }
      searchAllCompanies(id).then(res=>{
        this.companyList = res.data
        if(res.data != null){
          searchLastRecord(id).then(resp=>{
            if(resp == null){
              return;
            }
            // 通过检品ID搜索到所有相关的检测项目
            findIDAndName(resp.data.sampleScientificName).then(respp=>{
              // 首先根据检品名称找到待检测的检测项目
              resp.data.detectionDescriptionOption = respp.data
              this.addForm.sampleList.pop()
              this.addForm.sampleList.push(resp.data)
              this.addForm.companyID = resp.data.companyID
              this.addForm.receiptAddress = resp.data.receiptAddress
            });
          })
        }
      })
    },
    // 搜索上一个提交的数据
    searchLastCommissionRegister(){
      if(this.addForm.customerID == null || this.addForm.customerID == ''){
        return;
      }
      this.findAllCompanies(this.addForm.customerID);
    },
    //删除样品基本信息
    removeRow(index) {
      if (index >= 0) {
        this.addForm.sampleList.splice(index, 1);
      }
    },
    // 新增样品信息
    addSampleInfo(){
      this.addForm.sampleList.push({});
    },
    // 重置样品信息
    resetSampleInfo(){
      this.resetForm("sampleInfo");
    },
    // 跳转到任务再次分派页面
    toCommissionSampleSearch(){
      this.$router.push({ path: "/detection/commissionSampleSearch"});
    },
    // 通过检品ID搜索到所有相关的检测项目
    searchIDAndName(index){
      findIDAndName(this.addForm.sampleList[index].sampleScientificName).then(res=>{
        this.addForm.sampleList[index].detectionDescriptionOption = res.data
      });
    },
  }
};
</script>
