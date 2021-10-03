<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch"  label-width="150px">
      <el-form-item label="检品编号" prop="sampleNumber">

        <el-input v-model="queryParams.sampleNumber" placeholder="请输入检品编号" clearable size="medium" />
      </el-form-item>

      <el-form-item label="审核员" prop="operatorID">
        <el-select v-model="queryParams.operatorID" placeholder="请选择审核员" clearable size="medium">
          <el-option
            v-for="(item, key) in optionName"
            :key="key"
            :label="item"
            :value="key" />
        </el-select>
      </el-form-item>

      <el-form-item label="检品学名" prop="sampleScientificName">
        <el-select v-model="queryParams.sampleScientificName" placeholder="请选择检品学名" clearable filterable>
          <el-option
            v-for="item in sampleScientificNameOptions"
            :key="item"
            :label="item"
            :value="item">
          </el-option>
        </el-select>
      </el-form-item>

<!--      <el-form-item label="操作时间" prop="operationTime">-->
<!--        <el-date-picker clearable size="medium"-->
<!--                        v-model="queryParams.operationTime"-->
<!--                        type="date"-->
<!--                        value-format="yyyy-MM-dd"-->
<!--                        placeholder="选择操作时间">-->
<!--        </el-date-picker>-->
<!--      </el-form-item>-->
<!--    </el-form>-->
<!--    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch"  label-width="150px">-->

      <!--    <el-form-item label="检测项目结果或任务分派ID" prop="assignOrResultID">-->
      <!--        <el-select v-model="queryParams.assignOrResultID" placeholder="请选择检测项目结果或任务分派ID" clearable size="small">-->
      <!--          <el-option label="请选择字典生成" value="" />-->
      <!--        </el-select>-->
      <!--      </el-form-item>-->

      <el-form-item label="优先级" prop="priority"  >
        <el-select v-model="queryParams.priority" placeholder="请选择优先级" clearable size="medium">
          <el-option
            v-for="item in option"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="审核类别" prop="reviewCategory">
        <el-select v-model="queryParams.reviewCategory" placeholder="请选择审核类别" clearable size="medium">
          <el-option
            v-for="item in reviewCategories"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="检测员" prop="inspectorID">
        <el-select v-model="queryParams.inspectorID" placeholder="请选择检测员" clearable size="medium">
          <el-option
            v-for="(item, key) in userOption"
            :key="key"
            :label="item"
            :value="key" />
        </el-select>
      </el-form-item>

      <el-form-item label="提交/审核时间">
        <el-date-picker clearable size="small"
                        v-model="operationTimeList"
                        type="daterange"
                        value-format="yyyy-MM-dd"
                        range-separator="至"
                        start-placeholder="开始日期"
                        end-placeholder="结束日期">
        </el-date-picker>
      </el-form-item>

      <el-form-item label=""  prop="" style="margin-left: 50px">
        <el-radio-group v-model="radio" @change="handleChange">
          <el-radio  label="1" >未审核</el-radio>
          <el-radio  label="2" >已审核</el-radio>
        </el-radio-group>
      </el-form-item>
      <!--      <el-form-item label="审核结论" prop="reviewConclusion">-->
      <!--        <el-select v-model="queryParams.reviewConclusion" placeholder="请选择审核结论" clearable size="small">-->
      <!--          <el-option label="请选择字典生成" value="" />-->
      <!--        </el-select>-->
      <!--      </el-form-item>-->
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery(true)">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">

      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-check"
          size="mini"
          :disabled="multiple"
          @click="handleCheck"
        >批量审核</el-button>
      </el-col>
      <!--<el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['review:review:remove']"
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
          v-hasPermi="['review:review:export']"
        >导出</el-button>
      </el-col>-->
      <right-toolbar :showSearch.sync="showSearch" @queryTable="handleQuery"></right-toolbar>
    </el-row>
<!--    已审核表格信息-->
    <el-table v-loading="loading" v-if="radio =='2'" :data="reviewList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!--      <el-table-column label="审核ID" align="center" prop="reviewID" />-->
      <el-table-column label="检品编号" align="center" prop="sampleNumber" />
      <el-table-column label="检测员" align="center" prop="inspectorName" />
      <el-table-column label="审核类别" align="center" prop="reviewCategory" >
        <template slot-scope="scope">
          <span v-if="scope.row.reviewCategory == 31">初审</span>
          <span v-else-if="scope.row.reviewCategory == 32">复审</span>
        </template>
      </el-table-column>
      <el-table-column label="审核结论" align="center" prop="reviewConclusion" >
        <template slot-scope="scope">
          <el-tag size="mini" type="success" v-if="scope.row.reviewConclusion == 1">通过</el-tag>
          <el-tag size="mini" type="danger" v-else-if="scope.row.reviewConclusion == 2">不通过</el-tag>
      </template>
      </el-table-column>
      <el-table-column label="流转状态" align="center" prop="circulation" >
        <template slot-scope="scope">
          <el-tag size="mini" v-if="scope.row.circulation == 1">业务员修改</el-tag>
          <el-tag size="mini" v-else-if="scope.row.circulation == 2">重新提交样品</el-tag>
          <el-tag size="mini" v-else-if="scope.row.circulation == 3">委托修改</el-tag>
          <el-tag size="mini" type="success" v-else>任务分派</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="优先级" align="center" prop="priority" sortable>
        <template slot-scope="scope">
          <el-tag size="mini"  v-if="scope.row.priority == 1">正常</el-tag>
          <el-tag size="mini" type="success" v-else-if="scope.row.priority == 2">急</el-tag>
          <el-tag size="mini" type="warning" v-else-if="scope.row.priority == 3">加急</el-tag>
          <el-tag size="mini" type="danger" v-else-if="scope.row.priority == 4">特急</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="审核意见" align="center" prop="reviewOpinion" />
      <el-table-column label="审核员" align="center" prop="operatorName" />
      <el-table-column label="审核时间" align="center" prop="operationTime" width="180" sortable>
       <!-- <template slot-scope="scope">
          <span>{{ parseTime(scope.row.operationTime, '{y}-{m}-{d} {h}:{i}') }}</span>
        </template>-->
      </el-table-column>
      <el-table-column label="详细信息" align="center" class-name="small-padding fixed-width" >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-view"
            @click="handleReviewSearch(scope.row)"
          >审核详情</el-button>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
          >修改</el-button>

        </template>
      </el-table-column>
    </el-table>
    <!--未审核表格信息-->
    <el-table v-loading="loading" v-if="radio ==='1'"  :data="reviewList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!--      <el-table-column label="审核ID" align="center" prop="reviewID" />-->
      <el-table-column label="检品编号" align="center" prop="sampleNumber" />
      <el-table-column label="检测员" align="center" prop="inspectorName" />
      <el-table-column label="审核类别" align="center" prop="reviewCategory" >
        <template slot-scope="scope">
          <span v-if="scope.row.reviewCategory == 31">初审</span>
          <span v-else-if="scope.row.reviewCategory == 32">复审</span>
        </template>
      </el-table-column>
      <el-table-column label="提交时间" align="center" prop="operationTime" width="150" sortable>
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.operationTime, '{y}-{m}-{d} {h}:{i}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="优先级" align="center" prop="priority" sortable>
        <template slot-scope="scope">
          <el-tag size="mini"  v-if="scope.row.priority == 1">正常</el-tag>
          <el-tag size="mini" type="success" v-else-if="scope.row.priority == 2">急</el-tag>
          <el-tag size="mini" type="warning" v-else-if="scope.row.priority == 3">加急</el-tag>
          <el-tag size="mini" type="danger" v-else-if="scope.row.priority == 4">特急</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="检测农药" align="center" prop="subject.pesticideName" />
      <el-table-column label="检测结果" align="center" prop="subject.detectionData" />
      <el-table-column label="参考标准" align="center" prop="subject.referenceStandard" />
      <el-table-column label="检测员" align="center" prop="inspectorName" sortable/>
      <el-table-column label="详细信息" align="center" class-name="small-padding fixed-width" >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-view"
            @click="handleSearch(scope.row)"
          >查看详情</el-button>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleReview(scope.row)"
          >审核</el-button>

        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="handleQuery"
    />


    <div>
      <div v-if="title == '审核'">
        <!-- 审核对话框 -->
        <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
          <el-form ref="form" :model="form" :rules="rules" label-width="100px">

            <el-form-item label="审核意见" prop="reviewOpinion">
              <el-input v-model="form.reviewOpinion" placeholder="请输入审核意见" />
            </el-form-item>
          </el-form>
          <el-form ref="form" :model="form" >
            <el-form-item label=""  prop="" label-width="100px">
              <el-radio-group v-model="check">
                <el-radio  label="1" >通 过</el-radio>
                <el-radio  label="2" >退回复检</el-radio>
                <el-radio  label="3" >退回重新分派</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-form>
          <el-form ref="form" :model="form">

            <div class="demo-image__error">
              <div style="font-weight: bold"> 电子签名：</div>
              <div class="block" style="text-align: center">
                <el-image :src="electronicSignature" style="height: 200px">
                  <div slot="placeholder" class="image-slot">
                    加载中<span class="dot">...</span>
                  </div>
                </el-image>
              </div>
            </div>

            <div class="demo-image__error">
              <div style="font-weight: bold"> 小程序上传电子签名：</div>
              <div class="block" style="text-align: center">
                <el-image :src="'https://lims.anluyun.com/weixinqrcode/'" style="height: 200px">
                  <div slot="placeholder" class="image-slot">
                    加载中<span class="dot">...</span>
                  </div>
                </el-image>
              </div>
            </div>

            <el-upload action="#" :before-upload = "signatureUpload">
              <el-button size="medium" type="primary" icon="el-icon-upload" @click="getIndex(index)">上传电子签名</el-button>
            </el-upload>

          </el-form>
          <div slot="footer" class="dialog-footer" align="center">
            <el-button type="primary" @click="submitCheck">提 交</el-button>
            <!--<el-button type="warning" @click="returnToModify">退回复检</el-button>
            <el-button type="warning" @click="returnToAssign">退回重新分派</el-button>-->
            <el-button @click="cancel">取 消</el-button>
          </div>
        </el-dialog>
      </div>
      <div v-else-if="title == '提交'">
        <!-- 批量审核对话框 -->
        <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>


            <el-form ref="form" :model="form" :rules="rules" label-width="100px">

              <el-form-item label="审核意见" prop="reviewOpinion">
                <el-input v-model="form.reviewOpinion" placeholder="请输入审核意见" />
              </el-form-item>
            </el-form>
            <el-form ref="form" :model="form" >
              <el-form-item label=""  prop="" label-width="100px">
                <el-radio-group v-model="batchCheck">
                  <el-radio  label="1" >通 过</el-radio>
                  <el-radio  label="2" >退回复检</el-radio>
                  <el-radio  label="3" >退回重新分派</el-radio>
                </el-radio-group>
              </el-form-item>
            </el-form>
          <div class="demo-image__error">
            <div style="font-weight: bold"> 电子签名：</div>
            <div class="block" style="text-align: center">
              <el-image :src="electronicSignature" style="height: 200px">
                <div slot="placeholder" class="image-slot">
                  加载中<span class="dot">...</span>
                </div>
              </el-image>
            </div>
          </div>

          <div class="demo-image__error">
            <div style="font-weight: bold"> 小程序上传电子签名：</div>
            <div class="block" style="text-align: center">
              <el-image :src="'https://lims.anluyun.com/weixinqrcode/'" style="height: 200px">
                <div slot="placeholder" class="image-slot">
                  加载中<span class="dot">...</span>
                </div>
              </el-image>
            </div>
          </div>

          <el-upload action="#" :before-upload = "signatureUpload">
            <el-button size="medium" type="primary" icon="el-icon-upload" @click="getIndex(index)">上传电子签名</el-button>
          </el-upload>

          <div slot="footer" class="dialog-footer">
            <el-button type="primary" @click="batchSubmitCheck">提 交</el-button>

            <el-button @click="cancel">取 消</el-button>
          </div>
        </el-dialog>
      </div>

      <div v-else-if ="title==='审核详情'">
        <!-- 审核详情对话框 -->
        <el-dialog :title="title"  :visible.sync="open" width="500px" append-to-body>
          <el-form ref="form" :model="form"  label-width="120px" class="inputDeep">
            <el-form-item label="检品编号：" prop="sampleNumber">
              <el-input v-model="form.sampleNumber"  size="medium" />
            </el-form-item>
            <el-form-item label="优先级：" prop="priority">
              <el-input v-model="form.priority"  size="medium" />
            </el-form-item>
            <el-form-item label="审核类别：" prop="reviewCategory">
              <el-input v-model="form.reviewCategory"  size="medium" />
            </el-form-item>
            <el-form-item label="审核结论：" prop="reviewConclusion">
              <el-input v-model="form.reviewConclusion"  size="medium" />
            </el-form-item>
            <el-form-item label="审核意见：" prop="reviewOpinion">
              <el-input v-model="form.reviewOpinion"  size="medium" />
            </el-form-item>
            <el-form-item label="操作员名称：" prop="operatorName">
              <el-input v-model="form.operatorName"  size="medium" />
            </el-form-item>


            <div style="size: 30px;text-align: center;font-weight:bold">审核内容</div>
            <el-form-item label="检测农药" prop="pesticideName">
              <el-input v-model="form.pesticideName" style="width: 220px" size="medium" />
            </el-form-item>
            <el-form-item label="检测结果" prop="detectionData">
              <el-input v-model="form.detectionData" style="width: 220px" size="medium" />
            </el-form-item>
            <el-form-item label="检测员" prop="inspectorName">
              <el-input v-model="form.inspectorName" style="width: 220px" size="medium" />
            </el-form-item>
            <el-form-item label="检测时间" prop="detectionTime">
              <el-input v-model="form.detectionTime" format="yyyy-MM-dd HH:mm:ss" style="width: 220px" size="medium" />
            </el-form-item>
            <el-form-item label="参考范围" prop="referenceStandard">
              <el-input v-model="form.referenceStandard" style="width: 220px" size="medium" />
            </el-form-item>

            <div style="size: 30px;text-align: center;font-weight:bold">检品详情</div>

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

      <div v-else-if ="title==='检验结果详情'">

        <el-dialog :title="title"  :visible.sync="open" width="500px" append-to-body>
          <el-form ref="form" :model="form" label-width="120px"  class="inputDeep">
            <el-form-item label="检品编号" prop="sampleNumber">
              <el-input v-model="form.sampleNumber" style="width: 220px" size="medium" />
            </el-form-item>
            <el-form-item label="检测农药" prop="pesticideName">
              <el-input v-model="form.pesticideName" style="width: 220px" size="medium" />
            </el-form-item>
            <el-form-item label="检测结果" prop="detectionData">
              <el-input v-model="form.detectionData" style="width: 220px" size="medium" />
            </el-form-item>
            <el-form-item label="检测员" prop="inspectorName">
              <el-input v-model="form.inspectorName" style="width: 220px" size="medium" />
            </el-form-item>
            <el-form-item label="检测时间" prop="detectionTime">
              <el-input v-model="form.detectionTime" format="yyyy-MM-dd HH:mm:ss" style="width: 220px" size="medium" />
            </el-form-item>
            <el-form-item label="参考范围" prop="referenceStandard">
              <el-input v-model="form.referenceStandard" style="width: 220px" size="medium" />
            </el-form-item>

            <div style="size: 30px;text-align: center;font-weight:bold">检品详情</div>

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

        <div v-else-if ="title==='审核结果详情'">
          <!-- 审核详情对话框 -->
          <el-dialog :title="title"  :visible.sync="open" width="500px" append-to-body>
            <el-form ref="form" :model="form"  label-width="120px" class="inputDeep">
              <el-form-item label="检品编号：" prop="sampleNumber">
                <el-input v-model="form.sampleNumber"  size="medium" />
              </el-form-item>
              <el-form-item label="优先级：" prop="priority">
                <el-input v-model="form.priority"  size="medium" />
              </el-form-item>
              <el-form-item label="审核类别：" prop="reviewCategory">
                <el-input v-model="form.reviewCategory"  size="medium" />
              </el-form-item>
              <el-form-item label="审核结论：" prop="reviewConclusion">
                <el-input v-model="form.reviewConclusion"  size="medium" />
              </el-form-item>
              <el-form-item label="审核意见：" prop="reviewOpinion">
                <el-input v-model="form.reviewOpinion"  size="medium" />
              </el-form-item>
              <el-form-item label="操作员名称：" prop="operatorName">
                <el-input v-model="form.operatorName"  size="medium" />
              </el-form-item>

            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button @click="cancel">取 消</el-button>
            </div>
          </el-dialog>
        </div>

    </div>
  </div>
</template>

<script>
import {
  batchPass,
  returnToAssign,
  failReturnToRecheck,
  passReview,
  ReviewInfo,
  reviewerList,
  resultInfo,
  noReviewInfo,
  getReviewed,
  delReview,
  addReview,
  updateReview,
  exportReview,
  batchReturnToRecheck, batchReturnToAssign
} from "@/api/review/resultReview";
  import {getSampleScientificNameList} from "@/api/stock/sampleBank";
import {
  batchFailReview,
  getReviewInfo,
  getUserElectronicSignature,
  uploadSignature,
  userList
} from "@/api/review/sampleReview";

  export default {
    name: "Review",
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
        // 审核表格数据
        reviewList: [],
        // 操作时间list
        operationTimeList: '',
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        //检品学名选项
        sampleScientificNameOptions: null,
        //审核单选框
        radio: '1',
        //电子签名
        electronicSignature: null,
        //审核操作单选框
        check:'1',
        //批量审核操作单选框
        batchCheck:'1',
        //操作员下拉选择
        optionName: null,
        //系统用户下拉选择
        userOption:null,
        //优先级下拉选择
        option: [{
          value: 1,
          label: '正常'
        }, {
          value: 2,
          label: '急'
        },{
          value: 3,
          label: '加急'
        }, {
          value: 4,
          label: '特急'
        }],
        value: 1,
        //审核类别下拉选择
        reviewCategories: [ {
          value: 31,
          label: '初审'
        },{
          value: 32,
          label: '复审'
        }],
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          sampleID: null,
          assignOrResultID: null,
          reviewCategory: null,
          reviewConclusion: null,
          operatorID: null,
          operationTime: null,
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          sampleID: [
            { required: true, message: "检品编号不能为空", trigger: "change" }
          ],
          assignOrResultID: [
            { required: true, message: "检测项目结果或任务分派ID不能为空", trigger: "change" }
          ],
          reviewCategory: [
            { required: true, message: "审核类别不能为空", trigger: "change" }
          ],
          reviewConclusion: [
            { required: true, message: "审核结论不能为空", trigger: "blur" }
          ],
          reviewOpinion: [
            { required: true, message: "审核意见不能为空"}
          ],
          circulation: [
            { required: true, message: "流转标识不能为空", trigger: "blur" }
          ],
          operatorName: [
            { required: true, message: "操作员名称不能为空", trigger: "change" }
          ],
          operationTime: [
            { required: true, message: "操作时间不能为空", trigger: "blur" }
          ],
        }
      };
    },
    created() {
      this.getNoReviewList();
      this.getOperatorName();
      this.sampleScientificNameList();
      this.getSignature();
      this.getUserName();
    },
    methods: {
      /** 查询未审核列表 */
      getNoReviewList() {
        this.loading = true;
        if(this.operationTimeList != null && this.operationTimeList.length > 0){
          this.queryParams.reviewStartTime = this.operationTimeList[0]
          this.queryParams.reviewEndTime = this.operationTimeList[1]
        }else {
          this.queryParams.reviewStartTime = null
          this.queryParams.reviewEndTime = null
        }
        noReviewInfo(this.queryParams).then(response => {
          this.reviewList = response.rows;
          this.total = response.total;
          this.loading = false;
          console.log(this.reviewList);
        });
      },
      /** 获取电子签名 */
      getSignature(){
        getUserElectronicSignature().then(response => {
          this.electronicSignature = process.env.VUE_APP_BASE_API + response.data;
          console.log(this.electronicSignature);
        });
      },
      // 上传电子签名
      signatureUpload(img){
        let formData = new FormData();
        if(null == img){
          return;
        }
        formData.append("signature", img);
        uploadSignature(formData).then(res=>{
          console.log(res)
          this.getSignature();
          this.msgSuccess("电子签名上传成功")
        })
      },
      getIndex(index){
        this.imageIndex=index;
        console.log(this.imageIndex)
      },
      /** 查询审核列表 */
      getReviewList() {
        this.loading = true;
        if(this.operationTimeList != null && this.operationTimeList.length > 0){
          this.queryParams.reviewStartTime = this.operationTimeList[0]
          this.queryParams.reviewEndTime = this.operationTimeList[1]
        }else {
          this.queryParams.reviewStartTime = null
          this.queryParams.reviewEndTime = null
        }
        console.log(this.queryParams)
        ReviewInfo(this.queryParams).then(response => {
          console.log(response.rows)
          this.reviewList = response.rows;
          for(let i in this.reviewList) {
            this.reviewList[i].operationTime = this.$moment(this.reviewList[i].operationTime).format('YYYY-MM-DD HH:mm')
          }
            this.total = response.total;
          this.loading = false;
        });
      },
      //查询操作员名称
      getOperatorName() {
        reviewerList().then(response => {
          this.optionName = response.data
          console.log(this.optionName)
        });
      },
      //查询系统用户
      getUserName(){
        userList().then(response => {
          this.userOption = response.data
        });
      },
      //单选框点击事件
      handleChange(val){
        this.queryParams.pageNum = 1;
        if(val === '1'){
          this.getNoReviewList();
        }else{
          this.getReviewList();
        }
      },
      //审核操作单选框
      submitCheck(){
        if(this.check === '1'){
          this.passReview();
        }else if(this.check === '2'){
          this.returnToModify();
        }else if(this.check === '3'){
          this.returnToAssign();
        }
      },
      //审核通过点击事件
      passReview(){
        let data = this.form
        this.$confirm('是否确认该条数据审核通过？', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
        return passReview(data);
        }).then(response => {
          if (response.code === 200){
            this.msgSuccess("提交审核成功");
          }else{
            this.msgError("提交失败");
          }
          this.open = false;
          this.handleQuery();
          this.check = '1'

        });
      },
      /** 审核不通过*/
      returnToAssign(){
        this.$refs["form"].validate(valid => {
            let data = this.form
            this.$confirm('是否确认该条数据退回重新分派？', "警告", {
              confirmButtonText: "确定",
              cancelButtonText: "取消",
              type: "warning"
            }).then(function() {
              return returnToAssign(data);
            }).then(response => {
            if (response.code == 200){
              this.msgSuccess("提交审核成功");
            }else{
              this.msgError("提交失败");
            }
            this.open = false;
              this.handleQuery();
              this.check = '1'

            });
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
          reviewID: null,
          sampleID: null,
          assignOrResultID: null,
          reviewCategory: null,
          reviewConclusion: 0,
          reviewOpinion: null,
          circulation: null,
          electronicSignature: null,
          operatorID: null,
          operationTime: null,
          remarks: null,
          detectionTime:null,
          referenceStandard:null
        };
        this.resetForm("form");
      },
      /** 搜索按钮操作 */
      handleQuery(flag) {
        if (flag === true){
          this.queryParams.pageNum = 1;
        }
        if(this.radio === '1'){
          this.getNoReviewList();
        }else{
          this.getReviewList();
        }
      },
      /** 重置按钮操作 */
      resetQuery() {
        this.queryParams.pageNum = 1;
        this.resetForm("queryForm");
        this.operationTimeList = null;
        this.handleQuery();
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.reviewID)
        this.single = selection.length!==1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加审核";
      },
      /** 审核详情按钮操作 */
      handleReviewSearch(row) {
        this.reset();
        const reviewID = row.reviewID || this.ids
        getReviewed(reviewID).then(response => {
          this.form = response.data;
          if(this.form.priority === 1){
            this.form.priority = '正常'
          }else if(this.form.priority === 2){
            this.form.priority = '急'
          }else if(this.form.priority === 3){
            this.form.priority = '加急'
          }else if(this.form.priority === 4){
            this.form.priority = '特急'
          }
          if(this.form.reviewConclusion === 1){
            this.form.reviewConclusion = '通过'
          }else if(this.form.reviewConclusion === 2){
            this.form.reviewConclusion = '不通过'
          }
          if(this.form.reviewCategory === 31){
            this.form.reviewCategory = '初审'
          }else if(this.form.reviewCategory === 32){
            this.form.reviewCategory = '复审'
          }
          console.log(this.form)
          this.open = true;
          this.title = "审核详情";
        });
      },
      /** 检品详情按钮操作 */
      handleSearch(row) {
        this.reset();
        const reviewID = row.reviewID || this.ids
        resultInfo(reviewID).then(response => {
          console.log(response.data)
          this.form = response.data
          if(this.form.priority === 1){
            this.form.priority = '正常'
          }else if(this.form.priority === 2){
            this.form.priority = '急'
          }else if(this.form.priority === 3){
            this.form.priority = '加急'
          }else if(this.form.priority === 4){
            this.form.priority = '特急'
          }
          if(this.form.detectionCategory === 1){
            this.form.detectionCategory = '委托'
          }else if(this.form.detectionCategory === 2){
            this.form.detectionCategory = '仲裁'
          }else {
            this.form.detectionCategory = '其他'
          }
          this.open = true;
          this.title = "检验结果详情";
        });
      },
      /** 提交按钮操作 */
      handleCheck(row) {
        this.reset();
        this.open = true;
        this.title = "提交";
      },
      //批量审核操作单选框
      batchSubmitCheck(){
        if(this.batchCheck === '1'){
          this.batchPass();
        }else if(this.batchCheck === '2'){
          this.batchReturnToRecheck();
        }else if(this.batchCheck === '3'){
          this.batchReturnToAssign();
        }
      },
      /** 批量提交审核*/
      batchPass(){
        const ids = this.ids
        const data = this.form
        this.$confirm('是否确认批量提交审核通过？', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return batchPass(ids, data);
        }).then(response => {
          if (response.code === 200){
            this.msgSuccess("提交审核成功");
          }else{
            this.msgError("提交失败");
          }
          this.open = false;
          this.handleQuery();
          this.batchCheck = '1'

        });
      },
      /** 批量检验审核不通过,退回复检*/
      batchReturnToRecheck(){
        const ids = this.ids
        const data = this.form
        this.$confirm('是否确认批量退回复检？', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return batchReturnToRecheck(ids, data);
        }).then(response => {
          if (response.code === 200){
            this.msgSuccess("提交审核成功");
          }else{
            this.msgError("提交失败");
          }
          this.open = false;
          this.handleQuery();
          this.batchCheck = '1'

        });
      },
      /** 批量检验审核不通过,重新分派*/
      batchReturnToAssign(){
        const ids = this.ids
        const data = this.form
        this.$confirm('是否确认批量退回重新分派？', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return batchReturnToAssign(ids, data);
        }).then(response => {
          if (response.code === 200){
            this.msgSuccess("提交审核成功");
          }else{
            this.msgError("提交失败");
          }
          this.open = false;
          this.handleQuery();
          this.batchCheck = '1'

        });
      },

      /** 审核不通过,退回修改*/
      returnToModify(){
        this.$refs["form"].validate(valid => {
            let data = this.form
            this.$confirm('是否确认该条数据退回复检？', "警告", {
              confirmButtonText: "确定",
              cancelButtonText: "取消",
              type: "warning"
            }).then(function() {
              return failReturnToRecheck(data);
            }).then(response => {
            if (response.code === 200){
              this.msgSuccess("提交审核成功");
            }else{
              this.msgError("提交失败");
            }
            this.open = false;
              this.handleQuery();
              this.check = '1'

            });
        });
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        this.form = null;
        const reviewID = row.reviewID || this.ids
        getReviewInfo(reviewID).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "审核";
        });
      },
      /** 审核按钮操作 */
      handleReview(row) {
        this.reset();
        this.form.reviewID = row.reviewID
        this.form.sampleID = row.sampleID
        this.open = true;
        this.title = "审核";
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.reviewID != null) {
              updateReview(this.form).then(response => {
                this.msgSuccess("修改成功");
                this.open = false;
                this.handleQuery();
              });
            } else {
              addReview(this.form).then(response => {
                this.msgSuccess("新增成功");
                this.open = false;
                this.handleQuery();
              });
            }
          }
        });
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        const reviewIDs = row.reviewID || this.ids;
        this.$confirm('是否确认删除审核编号为"' + reviewIDs + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delReview(reviewIDs);
        }).then(() => {
          this.handleQuery();
          this.msgSuccess("删除成功");
        }).catch(() => {});
      },
      /** 导出按钮操作 */
      handleExport() {
        const queryParams = this.queryParams;
        this.$confirm('是否确认导出所有审核数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.exportLoading = true;
          return exportReview(queryParams);
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
    }
  };
</script>
<style scoped>
  .inputDeep>>>.el-input__inner {
    border: 0;
  }
</style>
