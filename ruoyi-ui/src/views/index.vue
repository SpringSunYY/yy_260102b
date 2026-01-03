<template>
  <div class="app-container home">
    <PieRoseCharts :chart-data="passedStatisticsData" :chart-title="passedStatisticsName"/>
    <BarAxisRankingCharts :chart-name="rankStatisticsName" :chart-data="rankStatisticsData"/>
  </div>
</template>

<script setup name="Index">

import PieRoseCharts from "@/components/Echarts/PieRoseCharts.vue";
import BarAxisRankingCharts from "@/components/Echarts/BarAxisRankingCharts.vue";
import {passedStatistics, rankStatistics} from "@/api/manage/statistics.js";

const passedStatisticsData = ref([])
const passedStatisticsName = ref('成绩通过率')

const rankStatisticsData = ref({})
const rankStatisticsName = ref('成绩排行')
const query = ref({})

function getPassedStatistics() {
  passedStatistics(query.value).then(res => {
    passedStatisticsData.value = res.data
  })
}

function getRankStatistics() {
  rankStatistics(query.value).then(res => {
    rankStatisticsData.value = res.data
  })
}

function getStatistics() {
  getPassedStatistics()
  getRankStatistics()
}

onMounted(() => {
  getStatistics()
})

</script>

<style scoped lang="scss">
.home {
}
</style>

