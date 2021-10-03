<template>
  <div class="app-container">

    <el-card>
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="100px">

      <el-form-item label="采样优先级 " prop="priority">
        <el-select v-model="queryParams.priority" placeholder="请选择采样优先级" @keyup.enter.native="handleQuery">
          <el-option
            v-for="item in priorityOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="检测标准" prop="detectionStandardID" >
        <el-select v-model="queryParams.detectionStandardID" placeholder="请选择检测标准" @keyup.enter.native="handleQuery">
          <el-option
            v-for="item in standardsItem"
            :key="item.id"
            :label="item.standardCode"
            :value="item.id">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="报告领取方式" prop="reportReceiveWay">
        <el-select v-model="queryParams.reportReceiveWay" placeholder="请选择报告领取方式" @keyup.enter.native="handleQuery">
          <el-option
            v-for="item in reportReceiveWayOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="送样人电话" prop="sampleSenderPhone">
        <el-input
          v-model="queryParams.sampleSenderPhone"
          placeholder="请输入送样人电话 客户填写或业务员代写"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="送样人名称" prop="sampleSenderName">
        <el-input
          v-model="queryParams.sampleSenderName"
          placeholder="请输入送样人名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="样品名称" prop="sampleName">
        <el-input
          v-model="queryParams.sampleName"
          placeholder="请输入样品名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="样品学名" prop="sampleScientificName">
        <el-input
          v-model="queryParams.sampleScientificName"
          placeholder="请输入样品学名"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="样品状态" prop="sampleStatus" >
        <el-select v-model="queryParams.sampleStatus" placeholder="请选择样品状态" @keyup.enter.native="handleQuery">
          <el-option
            v-for="item in sampleStatusOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>


      <el-form-item label="样品生产单位" prop="productionUnit">
        <el-input
          v-model="queryParams.productionUnit"
          placeholder="请输入样品生产单位"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="生产单位地址" prop="productionUnitAddress">
        <el-input
          v-model="queryParams.productionUnitAddress"
          placeholder="请输入生产单位地址"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="委托时间" prop="operationTime">
        <el-date-picker clearable size="small"
                        v-model="samplingTimeArray"
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
    </el-card>
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-search"
          size="mini"
          :disabled="single"
          @click="handleSearch"
          v-hasPermi="['commission:commissionPrototype:add']"
        >查看详情</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-search"
          size="mini"
          :disabled="single"
          @click="handleProgress"
          v-hasPermi="['commission:commissionPrototype:edit']"
        >查看样品进度</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-search"
          size="mini"
          :disabled="single"
          @click="handleOrderProgress"
          v-hasPermi="['commission:commissionPrototype:edit']"
        >查看委托单进度</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="commissionProgressList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!--      <el-table-column label="委托样品ID" align="center" prop="sampleID" />-->
      <!--      <el-table-column label="客户ID " align="center" prop="customerID" />-->
      <!--      <el-table-column label="单位ID " align="center" prop="companyID" />-->

      <el-table-column label="样品编号 " align="center" prop="sampleNumber" />
      <el-table-column label="委托单编号 " sortable align="center" prop="commissionNumber" />
      <el-table-column label="采样优先级 " align="center" prop="priority" >
        <template slot-scope="scope">
          <span v-if="scope.row.priority == 1">正常</span>
          <span v-else-if="scope.row.priority == 2">急</span>
          <span v-else-if="scope.row.priority == 3">加急</span>
          <span v-else-if="scope.row.priority == 4">特急</span>
        </template>
      </el-table-column>
      <el-table-column label="检测类别 " align="center" prop="detectionCategory" >
        <template slot-scope="scope">
          <span v-if="scope.row.detectionCategory == 1">委托</span>
          <span v-else-if="scope.row.detectionCategory == 2">仲裁</span>
          <span v-else-if="scope.row.detectionCategory == 3">其他</span>
        </template>
      </el-table-column>
      <el-table-column label="检测标准" align="center" prop="detectionStandardID" >
        <template slot-scope="scope">
          <span v-if="scope.row.detectionStandardID == 1">GB 2763-2019</span>
          <!--        <span v-else-if="scope.row.detectionCategory == 2">仲裁</span>-->
          <!--        <span v-else-if="scope.row.detectionCategory == 3">其他</span>-->
        </template>
      </el-table-column>
      <el-table-column label="报告领取方式 " align="center" prop="reportReceiveWay" >
        <template slot-scope="scope">
          <span v-if="scope.row.reportReceiveWay == 0">邮寄</span>
          <span v-if="scope.row.reportReceiveWay == 1">自取</span>
        </template>
      </el-table-column>
      <el-table-column label="收件地址" align="center" prop="receiptAddress" />
      <el-table-column label="送样人电话 " align="center" prop="sampleSenderPhone" />
      <el-table-column label="送样人名称" align="center" prop="sampleSenderName" />
<!--      <el-table-column label="采样时间" align="center" prop="samplingTime" width="180">-->
<!--        <template slot-scope="scope">-->
<!--          <span>{{ parseTime(scope.row.samplingTime, '{y}-{m}-{d}') }}</span>-->
<!--        </template>-->
<!--      </el-table-column>-->
      <el-table-column label="样品名称" align="center" prop="sampleName" />
      <el-table-column label="样品学名" align="center" prop="sampleScientificName" />
      <el-table-column label="样品状态" align="center" prop="sampleStatus" >
        <template slot-scope="scope">
          <span v-if="scope.row.sampleStatus == 1">固体</span>
          <span v-if="scope.row.sampleStatus == 2">液体</span>
          <span v-if="scope.row.sampleStatus == 3">胶体</span>
          <span v-if="scope.row.sampleStatus == 4">气体</span>
          <span v-if="scope.row.sampleStatus == 5">其他</span>
        </template>
      </el-table-column>
      <el-table-column label="样品数量" align="center" prop="sampleQuantity" />
      <el-table-column label="样品计量单位" align="center" prop="measureUnit" >
        <template slot-scope="scope">
          <span v-if="scope.row.measureUnit == 1">{{ unitsObject[0].unit }}</span>
          <span v-else-if="scope.row.measureUnit == 2">{{ unitsObject[1].unit }}</span>
          <span v-else-if="scope.row.measureUnit == 3">{{ unitsObject[2].unit }}</span>
          <span v-else-if="scope.row.measureUnit == 4">{{ unitsObject[3].unit }}</span>
          <span v-else-if="scope.row.measureUnit == 5">{{ unitsObject[4].unit }}</span>
          <span v-else-if="scope.row.measureUnit == 6">{{ unitsObject[5].unit }}</span>
          <span v-else-if="scope.row.measureUnit == 7">{{ unitsObject[6].unit }}</span>
          <span v-else-if="scope.row.measureUnit == 8">{{ unitsObject[7].unit }}</span>
          <span v-else-if="scope.row.measureUnit == 9">{{ unitsObject[8].unit }}</span>
          <span v-else-if="scope.row.measureUnit == 10">{{ unitsObject[9].unit }}</span>
          <span v-else-if="scope.row.measureUnit == 11">{{ unitsObject[10].unit }}</span>
          <span v-else-if="scope.row.measureUnit == 12">{{ unitsObject[11].unit }}</span>
        </template>
      </el-table-column>

      <el-table-column label="样品生产单位" align="center" prop="productionUnit" />
      <el-table-column label="生产单位地址" align="center" prop="productionUnitAddress" />

      <el-table-column label="是否回收剩余样品 " align="center" prop="recycle" >
        <template slot-scope="scope">
          <span v-if="scope.row.recycle == '0'">否</span>
          <span v-else-if="scope.row.recycle == '1'">是</span>
        </template>
      </el-table-column>
      <el-table-column label="样品描述" align="center" prop="sampleDescription" />
      <el-table-column label="检测描述" align="center" prop="detectionDescription" />
      <el-table-column label="操作时间" align="center" prop="operationTime" />
      <!--      <el-table-column label="样品图像地址" align="center" prop="sampleImage" />-->
      <!--      <el-table-column label="二维码地址" align="center" prop="QRcodeAddress" />-->
      <!--      <el-table-column label="电子签名地址" align="center" prop="electronicSignature" />-->
<!--      <el-table-column label="审核状态" align="center" prop="reviewStatus" >-->
<!--        <template scope="scope">-->
<!--          <span v-if="scope.row.reviewStatus==2" style="color:red">审核打回</span>-->
<!--          <span v-else style="color: #37B328">未审核</span>-->
<!--        </template>-->
<!--      </el-table-column>-->


      <!--      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">-->
<!--        <template slot-scope="scope">-->
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-edit"-->
<!--            @click="handleUpdate(scope.row)"-->
<!--            v-hasPermi="['commissionProgress:commissionProgress:edit']"-->
<!--          >修改</el-button>-->
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-delete"-->
<!--            @click="handleDelete(scope.row)"-->
<!--            v-hasPermi="['commissionProgress:commissionProgress:remove']"-->
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
<!--    进度-->
    <el-dialog :title="title1" :visible.sync="open1" width="1000px" append-to-body>

      <el-steps :space="200" :active=progress   finish-status="success" style="margin-top: 30px">
        <el-step title="委托处理中"></el-step>
        <el-step title="委托审核中"></el-step>
        <!--        <el-step title="委托退出"></el-step>-->
        <el-step title="任务分派中"></el-step>
        <el-step title="任务审核中"></el-step>
        <el-step title="项目检测中"></el-step>
        <el-step title="项目审核中"></el-step>
        <el-step title="报告生成中"></el-step>
        <el-step title="报告签发中"></el-step>
        <el-step title="报告完成"></el-step>
      </el-steps>
    </el-dialog>
    <!--    进度-->
    <el-dialog :title="title3" :visible.sync="open3" width="1000px" append-to-body>
     <div v-for="(item,index) in orderData" >
       <el-card v-if="item.progress<11">
         <h4>样品编号:{{item.sampleNumber}}&nbsp&nbsp&nbsp{{item.sampleName}}&nbsp&nbsp&nbsp进度</h4>
         <el-steps :space="200" :active=item.progress  finish-status="success" style="margin-top: 30px">
           <el-step title="委托处理中"></el-step>
           <el-step title="委托审核中"></el-step>
           <el-step title="任务分派中"></el-step>
           <el-step title="任务审核中"></el-step>
           <el-step title="项目检测中"></el-step>
           <el-step title="项目审核中"></el-step>
           <el-step title="报告生成中"></el-step>
           <el-step title="报告签发中"></el-step>
           <el-step title="报告完成"></el-step>
         </el-steps>
       </el-card>
       <el-card v-if="item.progress>11">
         <h4>样品编号:{{item.sampleNumber}}&nbsp&nbsp&nbsp{{item.sampleName}}&nbsp&nbsp&nbsp进度</h4>
         <div v-if="item.progress==12">
           <el-steps :space="200" :active=1   finish-status="error" style="margin-top: 30px;">
             <el-step title="委托已退出" ></el-step>

           </el-steps>
         </div>
         <div v-if="item.progress==50">
           <el-steps :space="200" :active=1   finish-status="error" style="margin-top: 30px;">
             <el-step title="报告已退检" ></el-step>

           </el-steps>
         </div>
       </el-card>
     </div>
    </el-dialog>
<!--    进度退出-->
    <el-dialog :title="title1" :visible.sync="open2" width="1000px" append-to-body>
<!--      <el-card>-->
<!--        <el-form ref="form" :model="form" :rules="rules"  label-width="150px">-->
<!--          <el-row :gutter="20">-->
<!--            <el-col :span="10">-->
<!--              <el-form-item label="样品学名" prop="sampleScientificName">-->
<!--                <el-input v-model="form.sampleScientificName" placeholder="请输入样品学名" :disabled="inputDisplay" />-->
<!--              </el-form-item>-->
<!--            </el-col>-->
<!--            <el-col :span="10">-->
<!--              <el-form-item label="样品编号" prop="sampleNumber">-->
<!--                <el-input v-model="form.sampleNumber" placeholder="请样品编号" :disabled="inputDisplay" />-->
<!--              </el-form-item>-->
<!--            </el-col>-->
<!--          </el-row>-->
<!--        </el-form>-->
<!--      </el-card>-->
      <el-steps :space="200" :active=progress   finish-status="error" style="margin-top: 30px;">
        <el-step :title="titles" ></el-step>

      </el-steps>
    </el-dialog>

    <!-- 添加或修改委托进度对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules"  label-width="150px">
        <el-row :gutter="20">
          <el-col :span="7">
            <el-form-item label="采样优先级 " prop="priority" >
              <el-select v-model="form.priority" placeholder="请选择采样优先级":disabled="inputDisplay" >
                <el-option
                  v-for="item in priorityOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="7">
            <el-form-item label="检测标准" prop="detectionStandardID" >
              <el-select v-model="form.detectionStandardID" placeholder="请选择检测标准" :disabled="inputDisplay">
                <el-option
                  v-for="item in standardsItem"
                  :key="item.id"
                  :label="item.standardCode"
                  :value="item.id">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="7">
            <el-form-item label="报告领取方式" prop="reportReceiveWay">
              <el-select v-model="form.reportReceiveWay" placeholder="请选择报告领取方式" :disabled="inputDisplay">
                <el-option
                  v-for="item in reportReceiveWayOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">

          <el-col :span="7">
            <el-form-item label="收件地址" prop="receiptAddress">
              <el-input v-model="form.receiptAddress" placeholder="请输入收件地址" :disabled="inputDisplay" />
            </el-form-item>
          </el-col>
          <el-col :span="7">
            <el-form-item label="送样人电话" prop="sampleSenderPhone">
              <el-input v-model="form.sampleSenderPhone" placeholder="请输入送样人电话 客户填写或业务员代写" :disabled="inputDisplay" />
            </el-form-item>
          </el-col>
          <el-col :span="7">
            <el-form-item label="送样人名称" prop="sampleSenderName">
              <el-input v-model="form.sampleSenderName" placeholder="请输入送样人名称" :disabled="inputDisplay"  />
            </el-form-item>
          </el-col>

        </el-row>
        <el-row :gutter="20">
          <el-col :span="7">
            <el-form-item label="样品名称" prop="sampleName">
              <el-input v-model="form.sampleName" placeholder="请输入样品名称" :disabled="inputDisplay" />
            </el-form-item>
          </el-col>
          <el-col :span="7">
            <el-form-item label="样品学名" prop="sampleScientificName">
              <el-input v-model="form.sampleScientificName" placeholder="请输入样品学名" :disabled="inputDisplay" />
            </el-form-item>
          </el-col>
          <el-col :span="7">
            <el-form-item label="样品状态" prop="sampleStatus" >
              <el-select v-model="form.sampleStatus" placeholder="请选择样品状态" :disabled="inputDisplay">
                <el-option
                  v-for="item in sampleStatusOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="7">
            <el-form-item label="样品数量" prop="sampleQuantity">
              <el-input v-model="form.sampleQuantity" placeholder="请输入样品数量" :disabled="inputDisplay" />
            </el-form-item>
          </el-col>
          <el-col :span="7">
            <el-form-item label="样品计量单位" prop="measureUnit" >
              <el-select v-model="form.measureUnit" placeholder="请选择样品计量单位" :disabled="inputDisplay">
                <el-option
                  v-for="item in unitsObject"
                  :key="item.id"
                  :label="item.unit"
                  :value="item.id"
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="7">
            <el-form-item label="样品生产单位" prop="productionUnit">
              <el-input v-model="form.productionUnit" placeholder="请输入样品生产单位" :disabled="inputDisplay" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">

          <el-col :span="7">
            <el-form-item label="生产单位地址" prop="productionUnitAddress">
              <el-input v-model="form.productionUnitAddress" placeholder="请输入生产单位地址" :disabled="inputDisplay" />
            </el-form-item>
          </el-col>
          <el-col :span="7">
            <el-form-item label="样品描述" prop="sampleDescription">
              <el-input v-model="form.sampleDescription" placeholder="请输入样品描述" :disabled="inputDisplay" type="textarea"  />
            </el-form-item>
          </el-col>
          <el-col :span="7">
            <el-form-item label="检测描述" prop="detectionDescription">
              <el-input v-model="form.detectionDescription" placeholder="请输入检测描述" :disabled="inputDisplay" type="textarea"  />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="7">
            <el-form-item label="回收剩余样品" prop="recycle">
              <el-select v-model="form.recycle" placeholder="请选择是否回收样品" :disabled="inputDisplay">
                <el-option
                  v-for="item in recycleOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="7">
          </el-col>
        </el-row>


        <!--        <el-form-item label="采样时间" prop="samplingTime">-->
        <!--          <el-date-picker clearable size="small"-->
        <!--                          v-model="form.samplingTime"-->
        <!--                          type="date"-->
        <!--                          value-format="yyyy-MM-dd"-->
        <!--                          placeholder="选择采样时间"-->
        <!--                          :disabled="inputDisplay">-->
        <!--          </el-date-picker>-->
        <!--        </el-form-item>-->

      </el-form>
<!--      <div slot="footer" class="dialog-footer" >-->
<!--        <el-button type="primary" @click="submitForm">确 定</el-button>-->
<!--        <el-button @click="cancel">取 消</el-button>-->
<!--      </div>-->
    </el-dialog>
  </div>
</template>

<script>
import { listCommissionProgress, getCommissionProgress, delCommissionProgress, addCommissionProgress, updateCommissionProgress, exportCommissionProgress,getProgress,getOrderProgress } from "@/api/commission/commissionProgress";
import {getStandards,getUnits} from "@/api/commission/commissionPrototype";
export default {
  name: "CommissionProgress",
  data() {
    return {
      buttonshow:false,
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
      // 委托进度表格数据
      commissionProgressList: [],
      inputDisplay:true,
      standardsItem:[],//检测标准
      unitsObject:[],//计量单位
      //采样优先级
      samplingTimeArray:[],
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
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      open1:false,
      open2:false,
      title2:'',
      title1:'',
      titles:'',
      progress:1,
      title3:'',
      open3:false,
      orderData: { },
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
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
        sampleDistribute: null,
        operationFromTime:null,
        operationToTime: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        customerID: [
          { required: true, message: "客户ID 外部送检、抽检单位或个人不能为空", trigger: "blur" }
        ],
        companyID: [
          { required: true, message: "单位ID 由单位和客户可确定邮寄地址不能为空", trigger: "blur" }
        ],
        priority: [
          { required: true, message: "采样方式 1-正常；2-急；3-加急；4-特急不能为空", trigger: "blur" }
        ],
        samplingMode: [
          { required: true, message: "不能为空", trigger: "blur" }
        ],
        detectionCategory: [
          { required: true, message: "检测类别 不能为空", trigger: "blur" }
        ],
        reportReceiveWay: [
          { required: true, message: "报告领取方式 0-邮寄，1-自取不能为空", trigger: "blur" }
        ],
        samplingTime: [
          { required: true, message: "采样时间不能为空", trigger: "blur" }
        ],
        sampleName: [
          { required: true, message: "样品名称不能为空", trigger: "blur" }
        ],
        sampleScientificName: [
          { required: true, message: "样品学名不能为空", trigger: "blur" }
        ],
        sampleQuantity: [
          { required: true, message: "样品数量不能为空", trigger: "blur" }
        ],
        measureUnit: [
          { required: true, message: "样品计量单位ID不能为空", trigger: "blur" }
        ],
        reviewStatus: [
          { required: true, message: "审核状态 0-委托待审核1-审核通过，2-审核不通过，3-委托单保存，4-样品登记保存，5登记成功,6-任务派发不能为空", trigger: "blur" }
        ],
        sign: [
          { required: true, message: "标志 本记录是否有效不能为空", trigger: "blur" }
        ],
      }


    };
  },
  created() {
    this.getList();
    this.getStandardsItem();
    this.getUnitsObject();
  },
  methods: {
    //获取检测标准
    getStandardsItem(){
      const that = this;
      getStandards().then(response => {
        console.log(response)
        that.standardsItem=response.data;
      });
    },
    //获取计量单位
    getUnitsObject(){
      const that = this;
      getUnits().then(response => {
        console.log(response)
        that.unitsObject=response.data;
      });
    },
    //查看详情
    handleSearch(row){
      this.reset();
      const sampleID = row.sampleID || this.ids
      getCommissionProgress(sampleID).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改委托样品";
      });
      this.inputDisplay=true;
    },
    handleOrderProgress(row){
      this.reset();
      const sampleID = row.sampleID || this.ids
      getOrderProgress(sampleID).then(response => {
          console.log(response.data)
        this.orderData=response.data
        this.open3 = true;
          this.title3=this.orderData[0].orderNumber+"    进度查询"
      })
    },
    //查看进度
    handleProgress(row){
      this.reset();
      // this.open1 = true;
      // this.title1 = "委托进度";
      const sampleID = row.sampleID || this.ids
      getCommissionProgress(sampleID).then(response => {
        this.form = response.data;
        getProgress(sampleID).then(response => {
          console.log(response.data)
          this.title1 = this.form.sampleNumber+"      "+this.form.sampleScientificName+"      "+"委托进度";
          if(response.data==10){
            this.progress =1
            this.open1 = true;
          }else if(response.data==11){
            this.progress =2
            this.open1 = true;
          }else if(response.data==20){
            this.progress =3
            this.open1 = true;
          }else if(response.data==21){
            this.progress =4
            this.open1 = true;
          }else if(response.data==30){
            this.progress =5
            this.open1 = true;
          }else if(response.data==31){
            this.progress =6
            this.open1 = true;
          }else if(response.data==40){
            this.progress =7
            this.open1 = true;
          }else if(response.data==41){
            this.progress =8
            this.open1 = true;
          }else if(response.data==42){
            this.progress =9
            this.open1 = true;
          }else if(response.data==43){
            this.progress =10
            this.open1 = true;
          }else if(response.data==50){
            this.progress =1
            this.open2 = true;
            this.titles="报告已退检"
          }
          else if(response.data==12){
            this.progress =1
            this.open2 = true;
            this.titles = "委托已退出";
          }

          // this.progress = response.data;

        });
      });
      this.inputDisplay=true;

      // this.inputDisplay=true;
      // const sampleID = row.sampleID || this.ids
      // getCommissionPrototype(sampleID).then(response => {
      //   this.form = response.data;
      //   this.open1 = true;
      //   this.title1 = "修改委托样品";
      // });

    },
    /** 查询委托进度列表 */
    getList() {
      this.loading = true;
      listCommissionProgress(this.queryParams).then(response => {
        console.log(response)
        this.commissionProgressList = response.rows;
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
      this.matchSamplingTime();
      this.getList();
    },
    // 委托时间匹配
    matchSamplingTime(){
      if(null != this.samplingTimeArray || this.samplingTimeArray.length != 0){
        this.queryParams.operationFromTime = this.samplingTimeArray[0];
        this.queryParams.operationToTime = this.samplingTimeArray[1];
      }
      console.log(this.queryParams.operationFromTime)
      console.log(this.queryParams.operationToTime)
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
      this.title = "添加委托进度";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const sampleID = row.sampleID || this.ids
      getCommissionProgress(sampleID).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改委托进度";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.sampleID != null) {
            updateCommissionProgress(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCommissionProgress(this.form).then(response => {
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
      const sampleIDs = row.sampleID || this.ids;
      this.$confirm('是否确认删除委托进度编号为"' + sampleIDs + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function() {
        return delCommissionProgress(sampleIDs);
      }).then(() => {
        this.getList();
        this.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有委托进度数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        this.exportLoading = true;
        return exportCommissionProgress(queryParams);
      }).then(response => {
        this.download(response.msg);
        this.exportLoading = false;
      }).catch(() => {});
    }
  }
};
</script>
