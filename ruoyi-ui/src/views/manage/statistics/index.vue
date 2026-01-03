<template>
  <div class="app-container home" v-if="checkPermi(['manage:statistics:statistics'])">
    <!-- 搜索区域 -->
    <el-card class="search-card" shadow="never">
      <template #header>
        <div class="card-header">
          <i class="el-icon-search"></i>
          <span>课程查询</span>
        </div>
      </template>
      <div class="search-form">
        <el-form :inline="true" :model="query" class="demo-form-inline">
          <el-form-item label="课程名称">
            <el-select
                v-model="query.courseId"
                filterable
                remote
                reserve-keyword
                placeholder="请输入课程名称"
                :remote-method="remoteCourseListMethod"
                :loading="courseLoading"
                style="width: 280px"
                clearable
            >
              <el-option
                  v-for="item in courseList"
                  :key="item.courseId"
                  :label="item.courseName"
                  :value="item.courseId"
              />
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
            <el-button icon="Refresh" @click="resetQuery">重置</el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-card>

    <!-- 统计数据展示区域 -->
    <div class="stats-section">
      <el-row :gutter="20">
        <el-col :span="24">
          <el-card class="stats-card" shadow="hover">
            <div class="stats-content">
              <div class="stats-icon">
                <i class="el-icon-data-line"></i>
              </div>
              <div class="stats-info">
                <div class="stats-title">平均成绩</div>
                <div class="stats-value">{{ averageStatisticsData }}</div>
              </div>
            </div>
          </el-card>
        </el-col>
      </el-row>
    </div>

    <!-- 图表展示区域 -->
    <div class="charts-section">
      <el-row :gutter="20">
        <el-col :span="12">
          <el-card class="chart-card" shadow="hover">
            <template #header>
              <div class="card-header">
                <i class="el-icon-pie-chart"></i>
                <span>{{ passedStatisticsName }}</span>
              </div>
            </template>
            <div class="chart-container">
              <PieRoseCharts :chart-data="passedStatisticsData" :chart-title="passedStatisticsName"/>
            </div>
          </el-card>
        </el-col>
        <el-col :span="12">
          <el-card class="chart-card" shadow="hover">
            <template #header>
              <div class="card-header">
                <i class="el-icon-s-data"></i>
                <span>{{ rankStatisticsName }}</span>
              </div>
            </template>
            <div class="chart-container">
              <BarAxisRankingCharts :chart-name="rankStatisticsName" :chart-data="rankStatisticsData"/>
            </div>
          </el-card>
        </el-col>
      </el-row>
    </div>
  </div>
  <div class="app-container home" v-else>
    <div class="no-permission-section">
      <el-card class="simple-card" shadow="hover">
        <div class="simple-content">
          <h2>成绩管理系统</h2>
        </div>
      </el-card>
    </div>
  </div>
</template>

<script setup name="Index">

import PieRoseCharts from "@/components/Echarts/PieRoseCharts.vue";
import BarAxisRankingCharts from "@/components/Echarts/BarAxisRankingCharts.vue";
import {averageStatistics, passedStatistics, rankStatistics} from "@/api/manage/statistics.js";
import {listCourseInfo} from "@/api/manage/courseInfo.js";
import {checkPermi} from "@/utils/permission.js";

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

function handleQuery() {
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
  if (checkPermi('manage:statistics:statistics')) {
    getList()
    getStatistics()
  }
})

</script>

<style scoped lang="scss">
.home {
  padding: 20px;
  background-color: #f5f5f5;
  min-height: calc(100vh - 84px);
}

// 搜索区域样式
.search-card {
  margin-bottom: 20px;

  .card-header {
    display: flex;
    align-items: center;
    font-weight: 600;
    color: #409eff;

    i {
      margin-right: 8px;
      font-size: 16px;
    }
  }

  .search-form {
    .el-form-item {
      margin-bottom: 0;

      &:last-child .el-form-item__content {
        display: flex;
        gap: 10px;
      }
    }
  }
}

// 统计数据展示区域
.stats-section {
  margin-bottom: 20px;

  .stats-card {
    .stats-content {
      display: flex;
      align-items: center;
      padding: 20px;

      .stats-icon {
        width: 60px;
        height: 60px;
        border-radius: 50%;
        background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
        display: flex;
        align-items: center;
        justify-content: center;
        margin-right: 20px;

        i {
          font-size: 24px;
          color: white;
        }
      }

      .stats-info {
        flex: 1;

        .stats-title {
          font-size: 14px;
          color: #909399;
          margin-bottom: 8px;
          font-weight: 500;
        }

        .stats-value {
          font-size: 32px;
          font-weight: bold;
          color: #303133;
          line-height: 1;
        }
      }
    }
  }
}

// 图表展示区域
.charts-section {
  background-color: #f8f9fa;
  padding: 20px;
  border-radius: 8px;

  .chart-card {
    height: 100%;

    .card-header {
      display: flex;
      align-items: center;
      font-weight: 600;
      color: #409eff;
      border-bottom: 1px solid #ebeef5;
      padding-bottom: 12px;

      i {
        margin-right: 8px;
        font-size: 16px;
      }
    }

    .chart-container {
      height: 400px;
      display: flex;
      align-items: center;
      justify-content: center;

      :deep(.echarts-container) {
        width: 100% !important;
        height: 100% !important;
      }
    }
  }

  .el-row {
    margin-bottom: 0;
  }
}

// 无权限页面样式
.no-permission-section {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 400px;

  .simple-card {
    max-width: 400px;
    text-align: center;

    .simple-content {
      h2 {
        color: #303133;
        font-size: 24px;
        font-weight: 600;
        margin: 0;
      }
    }
  }
}

// 响应式设计
@media screen and (max-width: 768px) {
  .home {
    padding: 10px;
  }

  .charts-section {
    padding: 10px;

    .el-col {
      margin-bottom: 20px;

      &:last-child {
        margin-bottom: 0;
      }
    }
  }

  .no-permission-section {
    .simple-card {
      .simple-content {
        h2 {
          font-size: 20px;
        }
      }
    }
  }
}
</style>

