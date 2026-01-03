<template>
  <div class="app-container home">
    <PieRoseCharts :chart-data="passedStatisticsData" :chart-title="passedStatisticsName"/>
    <BarAxisRankingCharts/>
  </div>
</template>

<script setup name="Index">

import PieRoseCharts from "@/components/Echarts/PieRoseCharts.vue";
import BarAxisRankingCharts from "@/components/Echarts/BarAxisRankingCharts.vue";
import {passedStatistics} from "@/api/manage/statistics.js";

const passedStatisticsData = ref([])
const passedStatisticsName = ref('成绩通过率')
const query = ref({})

function getPassedStatistics() {
  passedStatistics(query.value).then(res => {
    passedStatisticsData.value = res.data
  })
}

onMounted(() => {
  getPassedStatistics()
})

</script>

<style scoped lang="scss">
.home {
}
</style>

