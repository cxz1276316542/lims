<!-- index.vue -->
<template>
  <div class="main">
    <div  class="test">
      <echartscom :chartData="msg.chartData1" autoresize style="height: 100%;border: 2px solid #00afff" />

    </div>
    <div  class="test">
      <echartscom :chartData="msg.chartData1" autoresize style="height: 100%;border: 2px solid #00afff" />

    </div>
    <div class="test" >
      <order style="height: 100%;border: 2px solid #00afff"/>
    </div>
    <div class="test" >
      <order style="height: 100%;border: 2px solid #00afff"/>
    </div>
  </div>
</template>

<script>
import echartscom from '../components/echartscom.vue'
import order from '../components/order.vue'
export default {
  data() {
    return {
      msg:{
        chartData1: [
          {value:335, name:'直接访问'},
          {value:310, name:'邮件营销'},
          {value:234, name:'联盟广告'},
          {value:135, name:'视频广告'},
          {value:1548, name:'搜索引擎'}
        ],
        chartData2: [
          {value:335, name:'直接访问'},
          {value:390, name:'邮件营销'},
          {value:234, name:'联盟广告'},
          {value:135, name:'视频广告'},
          {value:1548, name:'搜索引擎'}
        ],
      }
    };
  },
  methods: {
  },
  mounted() {},
  components: {
    echartscom,
    order
  }
};
</script>

<style>
.test{
  float: left;
  width: 48%;
  height: 47%;
  margin-left: 1%;
  margin-right: 1%;
  margin-top: 1%;

}
*{
  margin: 0;
  padding: 0;
}

.main{

  width: 100%;
  height: 100%;
  position: absolute;
}
</style>
