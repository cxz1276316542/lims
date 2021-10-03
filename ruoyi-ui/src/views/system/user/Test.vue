<template>
  <el-row>
    <el-cascader
      size="small"
      :options="city.options"
      :props="props"
      v-model="cityValue"
      @active-item-change="handleItemChange"
      @change="cityChange">
    </el-cascader>
  </el-row>
</template>

<script>
  export default {
    name: 'addressSelector',
    props: {
      areaCode: null
    },

    model: {
      prop: 'areaCode',
      event: 'cityChange'
    },

    data () {
      return {
        // 所在省市
        city: {
          obj: {},
          options: []
        },
        props: { // 级联选择器的属性配置
          value: 'value',
          children: 'cities',
          checkStrictly: true
        },
        cityValue: [], // 城市代码
      }
    },
    computed: {
    },
    created () {
      this._initData()
    },
    mounted () {
    },
    methods: {
      _initData () {
        this.$http({
          method: 'get',
          url: this.API.province + '/156' // 中国
        }).then(res => {
          this.city.options = res.data.body.map(item => { // 所在省市
            return {
              value: item.provinceCode,
              label: item.provinceName,
              cities: []
            }
          })
        })
      },
      getCodeByAreaCode (code) {
        if (code == undefined) return false
        this.$http({
          method: 'get',
          url: this.API.addressCode + '/' + code
        })
          .then(res => {
            if (res.data.code === this.API.SUCCESS) {
              let provinceCode =  res.data.body.provinceCode
              let cityCode = res.data.body.cityCode
              let areaCode = res.data.body.areaCode
              this.cityValue = [provinceCode, cityCode, areaCode]
              this.handleItemChange([provinceCode, cityCode])
            }
          })
          .finally(res => {
          })
      },
      handleItemChange (value) {
        let a = (item) => {
          this.$http({
            method: 'get',
            url: this.API.city + '/' + value[0],
          }).then(res => {
            item.cities = res.data.body.map(ite => {
              return {
                value: ite.cityCode,
                label: ite.cityName,
                cities: []
              }
            })
            if(value.length === 2){ // 如果传入的value.length===2 && 先执行的a()，说明是传入了areaCode，需要初始化多选框
              b(item)
            }
          }).finally(_ => {
          })
        }
        let b = (item) => {
          if (value.length === 2) {
            item.cities.find(ite => {
              if (ite.value === value[1]) {
                if (!ite.cities.length) {
                  this.$http({
                    method: 'get',
                    url: this.API.area + '/' + value[1]
                  }).then(res => {
                    ite.cities = res.data.body.map(ite => {
                      return {
                        value: ite.areaCode,
                        label: ite.areaName,
                      }
                    })
                  }).finally(_ => {
                  })
                }
              }
            })
          }
        }
        this.city.options.find(item => {
          if (item.value === value[0]) {
            if (item.cities.length) {
              b(item)
            } else {
              a(item)
            }
            return true
          }
        })
      },
      getCityCode () {
        return this.cityValue[2]
      },
      reset () {
        this.cityValue = []
      },
      cityChange (value) {
        if (value.length === 3) {
          this.$emit('cityChange', value[2])
        } else {
          this.$emit('cityChange', null)
        }
      }
    },
    watch: {
      areaCode: {
        deep: true,
        immediate: true,
        handler (newVal) {
          if (newVal) {
            this.getCodeByAreaCode(newVal)
          } else {
            this.$nextTick(() => {
              this.reset()
            })
          }
        }
      }
    }
  }
</script>

<style lang="less" scoped>
</style>
