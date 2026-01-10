<template>
  <div class="app-container home">
    <!-- 通知公告卡片 -->
    <el-card class="box-card" shadow="hover">
      <template #header>
        <div class="card-header">
          <span>通知公告</span>
        </div>
      </template>
      <div v-loading="loading" class="notice-cards">
        <div v-if="noticeList.length === 0" class="no-data">
          <el-empty description="暂无公告"></el-empty>
        </div>
        <el-row :gutter="20" v-else>
          <el-col :span="12" v-for="notice in noticeList.slice(0, 4)" :key="notice.noticeId" class="notice-card-col">
            <el-card class="notice-card" shadow="hover" @click="viewNotice(notice)">
              <template #header>
                <div class="notice-card-header">
                  <el-tag :type="getNoticeTypeColor(notice.noticeType)" size="small">
                    {{ getNoticeTypeLabel(notice.noticeType) }}
                  </el-tag>
                  <span class="notice-time">{{ parseTime(notice.createTime, '{m}-{d}') }}</span>
                </div>
              </template>
              <div class="notice-card-content">
                <div class="notice-title" :title="notice.noticeTitle">
                  {{ notice.noticeTitle }}
                </div>
                <div class="notice-creator">发布人：{{ notice.createBy }}</div>
              </div>
            </el-card>
          </el-col>
        </el-row>
      </div>
    </el-card>

    <!-- 公告详情对话框 -->
    <el-dialog :title="noticeDialog.title" v-model="noticeDialog.visible" width="900px" 
               append-to-body>
      <div class="notice-detail">
        <div class="notice-meta">
          <el-row :gutter="20">
            <el-col :span="12">
              <span class="meta-label">公告类型：</span>
              <el-tag :type="getNoticeTypeColor(noticeDialog.data.noticeType)" size="small">
                {{ getNoticeTypeLabel(noticeDialog.data.noticeType) }}
              </el-tag>
            </el-col>
            <el-col :span="12">
              <span class="meta-label">发布人：</span>{{ noticeDialog.data.createBy }}
            </el-col>
            <el-col :span="24">
              <span class="meta-label">发布时间：</span>{{ parseTime(noticeDialog.data.createTime) }}
            </el-col>
          </el-row>
        </div>
        <div class="ql-container ql-snow">
          <div class="ql-editor" v-html="noticeDialog.data.noticeContent"></div>
        </div>
      </div>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="noticeDialog.visible = false">关 闭</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="Index">
import {listNotice, getNotice} from "@/api/system/notice"

const {proxy} = getCurrentInstance()
const {sys_notice_type} = proxy.useDict("sys_notice_type")

const noticeList = ref([])
const loading = ref(true)

const noticeDialog = reactive({
  visible: false,
  title: '',
  data: {}
})

// 获取公告类型标签
function getNoticeTypeLabel(type) {
  const dict = sys_notice_type.value?.find(item => item.value === type)
  return dict?.label || type
}

// 获取公告类型颜色
function getNoticeTypeColor(type) {
  const colorMap = {
    '1': 'success', // 通知
    '2': 'warning'  // 公告
  }
  return colorMap[type] || 'info'
}

// 查看公告详情
function viewNotice(notice) {
  getNotice(notice.noticeId).then(response => {
    noticeDialog.data = response.data
    noticeDialog.title = notice.noticeTitle
    noticeDialog.visible = true
  })
}

// 获取公告列表
function getNoticeList() {
  loading.value = true
  listNotice({
    pageNum: 1,
    pageSize: 10,
    status: '0' // 只获取正常状态的公告
  }).then(response => {
    noticeList.value = response.rows
    loading.value = false
  }).catch(() => {
    loading.value = false
  })
}

// 组件挂载时获取数据
onMounted(() => {
  getNoticeList()
})
</script>

<style scoped lang="scss">
.home {
  padding: 20px;
}

.box-card {
  margin-bottom: 20px;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.notice-cards {
  .notice-card-col {
    margin-bottom: 20px;

    .notice-card {
      cursor: pointer;
      transition: all 0.3s ease;

      &:hover {
        transform: translateY(-2px);
        box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15) !important;
      }

      .notice-card-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding: 12px 20px;

        .notice-time {
          font-size: 12px;
          color: #999;
        }
      }

      .notice-card-content {
        padding: 0 20px 20px;

        .notice-title {
          font-size: 16px;
          font-weight: 500;
          color: #333;
          margin-bottom: 12px;
          line-height: 1.4;
          display: -webkit-box;
          -webkit-line-clamp: 2;
          line-clamp: 2;
          -webkit-box-orient: vertical;
          overflow: hidden;
          text-overflow: ellipsis;
        }

        .notice-creator {
          font-size: 12px;
          color: #666;
          display: flex;
          align-items: center;

          &:before {
            content: "👤";
            margin-right: 4px;
          }
        }
      }
    }
  }
}

.no-data {
  text-align: center;
  padding: 40px 0;
}


.notice-detail {
  .notice-meta {
    margin-bottom: 20px;
    padding: 15px;
    background: #f8f9fa;
    border-radius: 4px;

    .meta-label {
      font-weight: 500;
      color: #666;
    }
  }

  .notice-content-container {
    height: calc(80vh - 200px);
    overflow-y: auto;
    border: 1px solid #e4e7ed;
    border-radius: 4px;
    padding: 20px;
    background: #fff;

    .notice-content {
      line-height: 1.8;
      color: #333;
      word-wrap: break-word;

      img {
        max-width: 100%;
        height: auto;
        display: block;
        margin: 10px auto;
        border-radius: 4px;
        box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
      }

      p {
        margin-bottom: 12px;
      }

      table {
        width: 100%;
        border-collapse: collapse;
        margin: 15px 0;

        th, td {
          border: 1px solid #e4e7ed;
          padding: 8px 12px;
          text-align: left;
        }

        th {
          background-color: #f5f7fa;
          font-weight: 600;
        }
      }

      ul, ol {
        margin: 15px 0;
        padding-left: 30px;

        li {
          margin-bottom: 5px;
        }
      }

      blockquote {
        border-left: 4px solid #e4e7ed;
        padding: 10px 15px;
        margin: 15px 0;
        background-color: #f8f9fa;
        font-style: italic;
      }

      pre {
        background-color: #f6f8fa;
        border: 1px solid #e4e7ed;
        border-radius: 4px;
        padding: 15px;
        overflow-x: auto;
        margin: 15px 0;
        font-family: 'Consolas', 'Monaco', 'Courier New', monospace;
        font-size: 14px;
        line-height: 1.4;
      }

      code {
        background-color: #f6f8fa;
        padding: 2px 6px;
        border-radius: 3px;
        font-family: 'Consolas', 'Monaco', 'Courier New', monospace;
        font-size: 13px;
      }
    }
  }
}

.dialog-footer {
  text-align: center;
}
</style>

