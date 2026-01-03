<template>
  <div class="app-container home">
    <el-select
        v-model="query.courseId"
        filterable
        remote
        reserve-keyword
        placeholder="请输入课程名称"
        :remote-method="remoteCourseListMethod"
        :loading="courseLoading"
        style="width: 240px"
    >
      <el-option
          v-for="item in courseList"
          :key="item.courseId"
          :label="item.courseName"
          :value="item.courseId"
      />
    </el-select>
    <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
    <el-button icon="Refresh" @click="resetQuery">重置</el-button>
    <p>平均成绩：{{averageStatisticsData}}</p>
    <PieRoseCharts :chart-data="passedStatisticsData" :chart-title="passedStatisticsName"/>
    <BarAxisRankingCharts :chart-name="rankStatisticsName" :chart-data="rankStatisticsData"/>
  </div>
</template>

<script setup name="Index">

import PieRoseCharts from "@/components/Echarts/PieRoseCharts.vue";
import BarAxisRankingCharts from "@/components/Echarts/BarAxisRankingCharts.vue";
import {averageStatistics, passedStatistics, rankStatistics} from "@/api/manage/statistics.js";
import {listCourseInfo} from "@/api/manage/courseInfo.js";

const courseList = ref([])
const courseQuery = ref({
  courseId: '',
  courseName: '',
  pageNum: 1,
  pageSize: 10,
})
const courseLoading = ref(false)

function getList() {
  courseLoading.value = true
  listCourseInfo(courseQuery.value).then(res => {
    courseList.value = res.rows
    courseLoading.value = false
  })
}

function remoteCourseListMethod(keyword) {
  courseQuery.value.courseName = keyword
  getList()
}

function handleQuery(){
  getStatistics()
}
function resetQuery() {
  query.value.courseId = ''
  courseQuery.value.courseName = ''
  getList()
  getStatistics()
}

const passedStatisticsData = ref([])
const passedStatisticsName = ref('成绩通过率')

const rankStatisticsData = ref({})
const rankStatisticsName = ref('成绩排行')

const averageStatisticsData = ref(0)
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

function getAverageStatistics() {
  averageStatistics(query.value).then(res => {
    averageStatisticsData.value = res.data
  })
}

function getStatistics() {
  getPassedStatistics()
  getRankStatistics()
  getAverageStatistics()
}

onMounted(() => {
  getList()
  getStatistics()
})

</script>

<style scoped lang="scss">
.home {
}
</style>

