<!-- echartscom.vue -->
<template>
  <div class="charttest">
    <div style="width:98%;height:98%;" :id="echarts" class="echarts" ref="echarts">
    </div>
  </div>
</template>

<script>
import echarts from 'echarts'
export default {
  name:'order',
  props: {
    chartData: {
      type: Array,
      default: () => []
    }
  },
  data() {
    return {
    };
  },
  methods: {
    drawCharts() {
      var myChart = echarts.init(document.getElementById(this.echarts));
      myChart.setOption({
        title: {
          text: 'ECharts 入门示例'
        },
        legend: {},
        tooltip: {},
        dataset: {
          source: [
            ['product', '2015', '2016'],
            ['Matcha Latte', 43.3, 85.8],
            ['Milk Tea', 83.1, 73.4],
            ['Cheese Cocoa', 86.4, 65.2],
            ['Walnut Brownie', 72.4, 53.9]
          ]
        },
        xAxis: {type: 'category'},
        yAxis: {},
        // Declare several bar series, each will be mapped
        // to a column of dataset.source by default.
        series: [
          {type: 'bar'},
          {type: 'bar'},

        ]
      });
    }
  },
  computed: {
    echarts() {
      return 'echarts' + Math.random() * 100000;
    }
  },
  mounted() {
    this.drawCharts();
  },
  components: {}
};
</script>

<style></style>
