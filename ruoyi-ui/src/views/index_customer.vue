<template>
<div>
  <img :src="logo" style="vertical-align: middle; width: 240px; height: 120px;margin-right:100px">
  <el-carousel :interval="5000" arrow="always" type="card" height="350px">
    <el-carousel-item v-for="item in imgData" :key="item.src">
      <img :src="item" style="width: 100%;  height: 100%;  " />
    </el-carousel-item>
  </el-carousel>

<!--  <el-card style="margin-left: 2%;margin-right: 2%;height: 70px">-->
<!--    <div>-->
<!--      <h2>单位简介</h2>-->
<!--    </div>-->
<!--  </el-card>-->
<!--  <el-card style="margin-left: 2%;margin-right: 2%;margin-top: 20px">-->
<!--  <div >-->
<!--    <p style="font-size: 18px">-->
<!--      实验室检验检测过程的客户服务管理、检测管理（样品登记、业务分配、报告编制、审核、批准）-->
<!--      、资源管理（人员、仪器、标准库、标准物质）、模版管理、数据应用、查询、系统操作、日志管理、-->
<!--      开放约定的第三方接口等信息化管理功能，充分发挥系统的质量控制和质量管理作用，节省质量体系管理中的人力和物力，-->
<!--      规范实验室工作操作程序，提高实验室整体的信息化水平。-->

<!--    </p>-->
<!--    <p  style="font-size: 18px">-->
<!--      农业环境检测实验室信息管理系统采用先进、科学的管理思想及先进、科学的计算机网络技术、数据存储技术、快速数据处理技术,-->
<!--      对实验室的分析测试业务实行全方位管理,并对实验室的产品质量实行严格的控制,-->
<!--      是针对农业环境和农产品检测业务从样品受理、分发、检测、审核到报告编制、签发、发出的全过程的计算机管理.-->
<!--      对人员、设备、物料、环境、质量文件等影响检测质量的各个要素进行规范化管理.-->
<!--    </p>-->
<!--  </div>-->
<!--  </el-card>-->
<!--  <el-collapse v-model="activeName" accordion>-->
<!--    <el-collapse-item title="单位介绍" name="1"  >-->
<!--      <div  style="font-size: 18px">实验室检验检测过程的客户服务管理、检测管理（样品登记、业务分配、报告编制、审核、批准）-->
<!--        、资源管理（人员、仪器、标准库、标准物质）、模版管理、数据应用、查询、系统操作、日志管理、-->
<!--        开放约定的第三方接口等信息化管理功能，充分发挥系统的质量控制和质量管理作用，节省质量体系管理中的人力和物力，-->
<!--        规范实验室工作操作程序，提高实验室整体的信息化水平。</div>-->
<!--    </el-collapse-item>-->
<!--    <el-collapse-item title="组织结构" name="2">-->
<!--      <div style="font-size: 18px">组织结构（organizational structure）是表明组织各部分排列顺序、空间位置、聚散状态、联系方式以及各要素之间相互关系的一种模式，是整个管理系统的“框架”。-->
<!--        组织结构是组织的全体成员为实现组织目标，在管理工作中进行分工协作，在职务范围、责任、权利方面所形成的结构体系。-->
<!--        组织结构是组织在职、责、权方面的动态结构体系，其本质是为实现组织战略目标而采取的一种分工协作体系，组织结构必须随着组织的重大战略调整而调整。</div>-->
<!--    </el-collapse-item>-->
<!--    <el-collapse-item title="领导班子" name="3">-->
<!--      <div style="font-size: 18px">班子领导的建设关系到我们祖国未来的发展，是一个关键点。而影响领导班子整体发挥的其中一个因素是领导班子的组成。我们应该选择正确的并且合适的党政正职来带领其他班子成员一起干事。他能驾驭全局、勇于担当、经验丰富、廉洁自律。他能引领整体带领整体共同奋进。如果只有班子成员，却没有带领人，势必会造成意见不统一，-->
<!--        遇到重大决策没有人能做出最后正确的决定。其次，应该调整领导班子的年龄结构。我们应该大力培养优秀青年干部，为我们的班子注入新鲜血液和力量，-->
<!--        同时也要将老中青干部合理结合，老中干部具有较多的经验供年轻干部进行吸取与学习。最后也要注意班子领导成员的专业结构。不同类型的领导班子，任务职责是不同的。要选取相对应的能力较强的干部合理搭配。</div>-->
<!--    </el-collapse-item>-->
<!--  </el-collapse>-->
  <el-tabs :tab-position="tabPosition" style="height: 200px;margin-top: 20px">
    <el-tab-pane label="单位介绍">实验室检验检测过程的客户服务管理、检测管理（样品登记、业务分配、报告编制、审核、批准）
      、资源管理（人员、仪器、标准库、标准物质）、模版管理、数据应用、查询、系统操作、日志管理、
      开放约定的第三方接口等信息化管理功能，充分发挥系统的质量控制和质量管理作用，节省质量体系管理中的人力和物力，
      规范实验室工作操作程序，提高实验室整体的信息化水平。</el-tab-pane>
    <el-tab-pane label="组织结构">组织结构（organizational structure）是表明组织各部分排列顺序、空间位置、聚散状态、联系方式以及各要素之间相互关系的一种模式，是整个管理系统的“框架”。
      组织结构是组织的全体成员为实现组织目标，在管理工作中进行分工协作，在职务范围、责任、权利方面所形成的结构体系。
      组织结构是组织在职、责、权方面的动态结构体系，其本质是为实现组织战略目标而采取的一种分工协作体系，组织结构必须随着组织的重大战略调整而调整。</el-tab-pane>
    <el-tab-pane label="领导班子">班子领导的建设关系到我们祖国未来的发展，是一个关键点。而影响领导班子整体发挥的其中一个因素是领导班子的组成。我们应该选择正确的并且合适的党政正职来带领其他班子成员一起干事。他能驾驭全局、勇于担当、经验丰富、廉洁自律。他能引领整体带领整体共同奋进。如果只有班子成员，却没有带领人，势必会造成意见不统一，
      遇到重大决策没有人能做出最后正确的决定。其次，应该调整领导班子的年龄结构。我们应该大力培养优秀青年干部，为我们的班子注入新鲜血液和力量，
      同时也要将老中青干部合理结合，老中干部具有较多的经验供年轻干部进行吸取与学习。最后也要注意班子领导成员的专业结构。不同类型的领导班子，任务职责是不同的。要选取相对应的能力较强的干部合理搭配。</el-tab-pane>
<!--    <el-tab-pane label="定时任务补偿">定时任务补偿</el-tab-pane>-->
  </el-tabs>

  <el-card class="footer">
    <div style="display: flex;justify-content: center;margin-top: -10px">
      Copyright ©2004-2021
      <a href="http://www.0755axecom.com/">深圳市安鑫宝科技发展有限公司</a>
    </div>
    <div style="display: flex;justify-content: center;margin-top: -10px">
      <p>地址：深圳市宝安区航城街道钟屋一路70栋大广发综合楼8楼 电话：4007888015/0755-27697166</p>
    </div>

  </el-card>

</div>
</template>

<script>
export default {
  name: "index_customer",
  data(){
      return {
        tabPosition: 'left',
      imgData:[
        require('../assets/images/p6.jpeg'),
        require('../assets/images/p7.jpeg'),
        require('../assets/images/banner.jpg'),
        require('../assets/images/banner2.jpg'),
        require('../assets/images/p1.jpeg'),
        require('../assets/images/p2.jpeg'),
        require('../assets/images/p3.jpeg'),
        require('../assets/images/p4.jpeg'),
      ],
        logo : require('../assets/images/logo.png'),
  };
  }
}
</script>

<style scoped>
.footer{
  height: 100px;
  width: 100%;
  position: fixed;
  bottom: 0;
  background-color: #ddd;

}
</style>
