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

      <el-form-item label="撰写人" prop="reportWriterID">
        <el-select v-model="queryParams.reportWriterID" placeholder="请选择报告撰写人" clearable size="medium">
          <el-option
            v-for="(value,key) in reportWriterList"
            :key="key"
            :label="value"
            :value="key">
          </el-option>
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
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!--未审核表格信息-->
    <el-table v-loading="loading" v-if="radio ==='1'"  :data="reviewList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!--      <el-table-column label="审核ID" align="center" prop="reviewID" />-->
      <el-table-column label="检品编号" align="center" prop="sampleNumber" />
      <el-table-column label="撰写人" align="center" prop="reportWriterName" />
<!--      <el-table-column label="审核类别" align="center" prop="reviewCategory" >-->
<!--        <template slot-scope="scope">-->
<!--          <span v-if="scope.row.reviewCategory == 33">初审</span>-->
<!--        </template>-->
<!--      </el-table-column>-->
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
      <el-table-column label="检品学名" align="center" prop="sampleScientificName" />
      <el-table-column label="检测标准" align="center" prop="subject.detectionStandard" />
      <el-table-column label="报告预览" align="center" class-name="small-padding fixed-width" >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-view"
            @click="previewReport(scope.row)"
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

    <!--    已审核表格信息-->
    <el-table v-loading="loading" v-if="radio ==='2'"  :data="reviewList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!--      <el-table-column label="审核ID" align="center" prop="reviewID" />-->
      <el-table-column label="检品编号" align="center" prop="sampleNumber" />
      <el-table-column label="撰写人" align="center" prop="reportWriterName" />
      <el-table-column label="审核类别" align="center" prop="reviewCategory" >
        <template slot-scope="scope">
          <span v-if="scope.row.reviewCategory == 33">初审</span>
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
          <el-tag size="mini" v-if="scope.row.circulation == 1">重新撰写</el-tag>
          <el-tag size="mini" v-else-if="scope.row.circulation == 2">复检</el-tag>
          <el-tag size="mini" v-else-if="scope.row.circulation == 3">重新分派</el-tag>
          <el-tag size="mini" type="success" v-else>报告签发</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="审核意见" align="center" prop="reviewOpinion" />
      <el-table-column label="审核员" align="center" prop="operatorName" >
      <template slot-scope="scope">
        <span>{{scope.row.operatorName }}</span>
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
      <el-table-column label="报告预览" align="center" class-name="small-padding fixed-width" >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-view"
            @click="previewReport(scope.row)"
          >查看详情</el-button>
        </template>
      </el-table-column>
      <el-table-column label="审核时间" align="center" prop="operationTime" width="180" sortable>
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.operationTime, '{y}-{m}-{d} {h}:{i}') }}</span>
        </template>
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

      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" >
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
        <el-dialog :title="title" :visible.sync="open" width="650px" append-to-body>
          <el-form ref="form" :model="form" :rules="rules" label-width="100px">

            <el-form-item label="审核意见" prop="reviewOpinion">
              <el-input v-model="form.reviewOpinion" placeholder="请输入审核意见" />
            </el-form-item>
          </el-form>
          <el-form ref="form" :model="form" >
            <el-form-item label=""  prop="" label-width="100px">
              <el-radio-group v-model="check">
                <el-radio  label="1" >通 过</el-radio>
                <el-radio  label="2" >退回撰写人修改</el-radio>
                <el-radio  label="3" >退回重新分配</el-radio>
                <el-radio  label="4" >退回复检</el-radio>
              </el-radio-group>
            </el-form-item>

            <div style="size: 30px;font-weight:bold">报告判定结果：</div>

            <el-form-item label=""  prop="" label-width="100px">
              <el-radio-group v-model="form.remarks">
                <el-radio  label="1" value="1">合 格</el-radio>
                <el-radio  label="2" value="2">不合格</el-radio>
                <el-radio  label="3" value="3">不做判定</el-radio>
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
            <el-button @click="cancel">取 消</el-button>
          </div>
        </el-dialog>
      </div>
      <div v-else-if="title == '提交'">
        <!-- 提交对话框 -->

        <el-dialog :title="title" :visible.sync="open" width="650px" append-to-body>
          <el-form ref="form" :model="form" :rules="rules" label-width="100px">

            <el-form-item label="审核意见" prop="reviewOpinion">
              <el-input v-model="form.reviewOpinion" placeholder="请输入审核意见" />
            </el-form-item>
          </el-form>
          <el-form ref="form" :model="form" >
            <el-form-item label=""  prop="" label-width="100px">
              <el-radio-group v-model="batchCheck">
                <el-radio  label="1" >通 过</el-radio>
                <el-radio  label="2" >退回撰写人修改</el-radio>
                <el-radio  label="3" >退回重新分配</el-radio>
                <el-radio  label="4" >退回复检</el-radio>
              </el-radio-group>
            </el-form-item>

            <div style="size: 30px;font-weight:bold">报告判定结果：</div>
            <el-form-item label=""  prop="" label-width="100px">
              <el-radio-group v-model="form.remarks">
                <el-radio  label="1" value="1">合 格</el-radio>
                <el-radio  label="2" value="2">不合格</el-radio>
                <el-radio  label="3" value="3">不做判定</el-radio>
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
          <div slot="footer" class="dialog-footer" >
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

      <div v-else-if="title === '检测报告详情预览'">
      <el-dialog style="height: 700px;width: 60%;margin: 10px auto;"
                 :close-on-click-modal="false"
                 :visible.sync="open"
                 :fullscreen="true"
                 title="文件预览">
        <div class="agreement_picture" style="height: 600px;width: 100%">
          <div class="pdf" style="height: 100%;width: 100%;position: relative;transform: translateY(-5%)">
            <iframe :src="pdfsrc" frameborder="0" style="width: 100%; height: 100%;">111</iframe>
          </div>
        </div>
      </el-dialog>
    </div>

      <div v-else-if="title === '检测报告详情'"   >
        <!-- 检品详情对话框 -->
        <el-dialog :title="title"  :visible.sync="open" width="500px" append-to-body>
          <el-form ref="form" :model="form"  label-width="120px" class="inputDeep">
            <el-form-item label="检品学名：" prop="sampleScientificName">
              <el-input v-model="form.sampleScientificName"  size="medium" />
            </el-form-item>
            <el-form-item label="优先级：" prop="priority">
              <el-input v-model="form.priority"  size="medium" />
            </el-form-item>
            <el-form-item label="检测标准：" prop="detectionStandard">
              <el-input v-model="form.detectionStandard"  size="medium" />
            </el-form-item>
            <el-form-item label="检测类别：" prop="detectionCategory">
              <el-input v-model="form.detectionCategory"  size="medium" />
            </el-form-item>
            <el-form-item label="检品描述：" prop="sampleDescription">
              <el-input v-model="form.sampleDescription"  size="medium" />
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

    </div>


  </div>
</template>

<script>
import {
  listReview,
  getReview,
  getReviewed,
  delReview,
  addReview,
  updateReview,
  exportReview,
  noReviewInfo,
  ReviewInfo,
  getWriterList,
  passReview,
  failReturnToWriter,
  returnToAssign,
  returnToRecheck,
  batchReturnToAssign,
  batchReturnToRecheck,
  batchReturnToWriter,
  batchPass,
} from "@/api/review/reportReview";
import {
  failReturnToModify,
  getReviewInfo,
  getUserElectronicSignature,
  reviewerList,
  uploadSignature, userList
} from "@/api/review/sampleReview";
import {getSampleScientificNameList} from "@/api/stock/sampleBank";

  export default {
    name: "Review",
    data() {
      return {
        // 遮罩层
        loading: true,
        pdfsrc:'',
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
        // 操作时间list
        operationTimeList: '',
        // 总条数
        total: 0,
        // 审核表格数据
        reviewList: [],
        // 弹出层标题
        title: "",
        //检品学名选项
        sampleScientificNameOptions: null,
        //电子签名
        electronicSignature: null,
        // 是否显示弹出层
        open: false,
        //审核单选框
        radio: '1',
        //审核操作单选框
        check:'1',
        //批量审核操作单选框
        batchCheck:'1',
        //审核人信息
        optionName: null,
        //系统用户下拉选择
        userOption:null,
        //报告撰写人信息
        reportWriterList: [],
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
        option1: [ {
          value: 33,
          label: '初次审核'
        }],
        value1: null,
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
          reportWriterID: null,
          sampleRegisterStartTime: '',
          sampleRegisterEndTime: ''
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
            { required: true, message: "审核意见不能为空", trigger: "blur" }
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
      this.getReportWriterList();
      this.sampleScientificNameList();
      this.getSignature();
      this.getUserName();
    },
    methods: {
      /** 查询审核列表 */
      getList() {
        this.loading = true;
        console.log(this.queryParams)
        listReview(this.queryParams).then(response => {
          this.reviewList = response.rows;
          for(let i in this.reviewList){
            if(this.reviewList[i].priority == 1){
              this.reviewList[i].priority = '正常'
            }else if(this.reviewList[i].priority == 2){
              this.reviewList[i].priority = '急'
            }else if(this.reviewList[i].priority == 3){
              this.reviewList[i].priority = '加急'
            }else{
              this.reviewList[i].priority = '特急'
            }
          }
          this.total = response.total;
          this.loading = false;
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
          remarks: null
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
        }else if(this.radio === '2'){
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
      /** 详情按钮操作 */
      handleSearch(row) {
        this.reset();
        const reviewID = row.reviewID || this.ids
        getReview(reviewID).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "检测报告详情";
        });
      },
      /** 报告预览 */
      previewReport(row) {
        const sampleNumber = row.sampleNumber || this.ids;
        this.pdfsrc=this.$store.state.baseUrl+sampleNumber+'.pdf';
        this.open = true;
        this.title = "检测报告详情预览";
      },
      /** 提交按钮操作 */
      handleCheck(row) {
        this.reset();
        this.open = true;
        this.title = "提交";
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
                this.getList();
              });
            } else {
              addReview(this.form).then(response => {
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
        const reviewIDs = row.reviewID || this.ids;
        this.$confirm('是否确认删除审核编号为"' + reviewIDs + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delReview(reviewIDs);
        }).then(() => {
          this.getList();
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
          if(this.form.reviewCategory === 33){
            this.form.reviewCategory = '初审'
          }else{
            this.form.reviewCategory = '复审'
          }
          console.log(this.form)
          this.open = true;
          this.title = "审核详情";
        });
      },

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
          console.log(this.reviewList)
        });
      },

      //查询操作员名称
      getOperatorName() {
        reviewerList().then(response => {
          this.optionName = response.data
        });
      },

      //单选框点击事件,查询已审核和未审核
      handleChange(val){
        this.queryParams.pageNum = 1;
        if(val === '1'){
          this.getNoReviewList();
        }else{
          this.getReviewList();
        }

      },
      //查询系统用户
      getUserName(){
        userList().then(response => {
          this.userOption = response.data
        });
      },
      //审核操作单选框
      submitCheck(){
        if(this.check === '1'){
          this.passReview();
        }else if(this.check === '2'){
          this.returnToWriter();
        }else if(this.check === '3'){
          this.returnToAssign();
        }else if(this.check === '4'){
          this.returnToRecheck();
        }
      },
      //批量审核操作单选框
      batchSubmitCheck(){
        if(this.batchCheck === '1'){
          this.batchPass();
        }else if(this.batchCheck === '2'){
          this.batchReturnToWriter();
        }else if(this.batchCheck === '3'){
          this.batchReturnToAssign();
        }else if(this.batchCheck === '4'){
          this.batchReturnToRecheck();
        }
      },

      /** 获取人员信息（报告撰写人） */
      getReportWriterList(){
        getWriterList().then(res=>{
          this.reportWriterList = res.data;
        })
      },

      //审核通过点击事件
      passReview(){
        console.log(this.form)
        let data =  this.form
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

      /** 审核不通过,退回修改*/
      returnToWriter(){
        this.$refs["form"].validate(valid => {
            let data =  this.form
            this.$confirm('是否确认该条数据退回撰写人修改？', "警告", {
              confirmButtonText: "确定",
              cancelButtonText: "取消",
              type: "warning"
            }).then(function() {
              return failReturnToWriter(data);
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

      /** 审核不通过，重新分派*/
      returnToAssign(){
        this.$refs["form"].validate(valid => {
            let data =  this.form
            this.$confirm('是否确认该条数据退回重新分配？', "警告", {
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

      /** 审核不通过，重新检测*/
      returnToRecheck(){
        this.$refs["form"].validate(valid => {
            let data =  this.form
            this.$confirm('是否确认该条数据退回复检？', "警告", {
              confirmButtonText: "确定",
              cancelButtonText: "取消",
              type: "warning"
            }).then(function() {
              return returnToRecheck(data);
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

      /** 获取检品学名选项 */
      sampleScientificNameList(){
        getSampleScientificNameList().then(res=>{
          this.sampleScientificNameOptions = res.data
        })
      },

      /** 批量提交审核*/
      batchPass(){
        const ids = this.ids
        const data = this.form
        // const electronicSignature = '电子签章地址'
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

      /** 批量报告审核不通过,退回重新分派*/
      batchReturnToAssign(){
        const ids = this.ids
        const data = this.form
        this.$confirm('是否确认批量退回重新分配？', "警告", {
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

      /** 批量报告审核不通过,退回报告撰写人*/
      batchReturnToWriter(){
        const ids = this.ids
        const data = this.form
        this.$confirm('是否确认批量退回报告撰写人？', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return batchReturnToWriter(ids, data);
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

      /** 批量报告审核不通过,退回复检*/
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
          this.reviewList = response.rows;
          // this.reviewList.operationTime = this.$moment(this.reviewList.operationTime).format('YYYY-MM-DD HH:mm')
          this.total = response.total;
          this.loading = false;
        });
        console.log(this.form)
      },

    }
  };
</script>
<style scoped>
  .inputDeep>>>.el-input__inner {
    border: 0;
  }
</style>
