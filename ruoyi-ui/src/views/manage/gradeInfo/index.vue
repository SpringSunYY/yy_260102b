<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="成绩编号" prop="gradeId">
        <el-input
            v-model="queryParams.gradeId"
            placeholder="请输入成绩编号"
            clearable
            @keyup.enter="handleQuery"
        />
      </el-form-item>
      <!--      <el-form-item label="课程名称" prop="courseId">-->
      <!--        <el-input-->
      <!--            v-model="queryParams.courseId"-->
      <!--            placeholder="请输入课程名称"-->
      <!--            clearable-->
      <!--            @keyup.enter="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="学生成绩" prop="score">-->
      <!--        <el-input-->
      <!--            v-model="queryParams.score"-->
      <!--            placeholder="请输入学生成绩"-->
      <!--            clearable-->
      <!--            @keyup.enter="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <el-form-item label="及格" prop="isPassed">
        <el-select v-model="queryParams.isPassed" placeholder="请选择及格" clearable>
          <el-option
              v-for="dict in is_passed"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="审核状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择审核状态" clearable>
          <el-option
              v-for="dict in grade_status"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <!--      <el-form-item label="老师" prop="teacherId">-->
      <!--        <el-input-->
      <!--            v-model="queryParams.teacherId"-->
      <!--            placeholder="请输入老师"-->
      <!--            clearable-->
      <!--            @keyup.enter="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="学生" prop="userId">-->
      <!--        <el-input-->
      <!--            v-model="queryParams.userId"-->
      <!--            placeholder="请输入学生"-->
      <!--            clearable-->
      <!--            @keyup.enter="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
            type="primary"
            plain
            icon="Plus"
            @click="handleAdd"
            v-hasPermi="['manage:gradeInfo:add']"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="success"
            plain
            icon="Edit"
            :disabled="single"
            @click="handleUpdate"
            v-hasPermi="['manage:gradeInfo:edit']"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="danger"
            plain
            icon="Delete"
            :disabled="multiple"
            @click="handleDelete"
            v-hasPermi="['manage:gradeInfo:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="warning"
            plain
            icon="Download"
            @click="handleExport"
            v-hasPermi="['manage:gradeInfo:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="gradeInfoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="成绩编号" align="center" prop="gradeId"/>
      <el-table-column label="课程名称" align="center" prop="courseName"/>
      <el-table-column label="学生成绩" align="center" prop="score"/>
      <el-table-column label="及格" align="center" prop="isPassed">
        <template #default="scope">
          <dict-tag :options="is_passed" :value="scope.row.isPassed"/>
        </template>
      </el-table-column>
      <el-table-column label="审核状态" align="center" prop="status">
        <template #default="scope">
          <dict-tag :options="grade_status" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="成绩描述" align="center" prop="gradeDesc"/>
      <el-table-column label="老师" align="center" prop="teacherName"/>
      <el-table-column label="学生" align="center" prop="userName"/>
      <el-table-column label="创建人" align="center" prop="createBy"/>
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新时间" align="center" prop="updateTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)"
                     v-hasPermi="['manage:gradeInfo:edit']">修改
          </el-button>
          <el-button link type="primary" icon="Plus" @click="handleAudit(scope.row)"
                     v-hasPermi="['manage:audit:add']">
            审核
          </el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)"
                     v-hasPermi="['manage:gradeInfo:remove']">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
        v-show="total>0"
        :total="total"
        v-model:page="queryParams.pageNum"
        v-model:limit="queryParams.pageSize"
        @pagination="getList"
    />

    <!-- 添加或修改学生成绩信息对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="gradeInfoRef" :model="form" :rules="rules" label-width="80px">
        <!--        <el-form-item label="课程名称" prop="courseId">-->
        <!--          <el-input v-model="form.courseId" placeholder="请输入课程名称"/>-->
        <!--        </el-form-item>-->
        <el-form-item label="学生成绩" prop="score">
          <el-input-number :max="100" :min="0" style="width: 100%" v-model="form.score" placeholder="请输入学生成绩"/>
        </el-form-item>
        <!--        <el-form-item label="及格" prop="isPassed">-->
        <!--          <el-radio-group v-model="form.isPassed">-->
        <!--            <el-radio-->
        <!--                v-for="dict in is_passed"-->
        <!--                :key="dict.value"-->
        <!--                :label="dict.value"-->
        <!--            >{{ dict.label }}-->
        <!--            </el-radio>-->
        <!--          </el-radio-group>-->
        <!--        </el-form-item>-->
        <!--        <el-form-item label="审核状态" prop="status" v-if="checkPermi(['manage:gradeInfo:audit'])">-->
        <!--          <el-radio-group v-model="form.status">-->
        <!--            <el-radio-->
        <!--                v-for="dict in grade_status"-->
        <!--                :key="dict.value"-->
        <!--                :label="dict.value"-->
        <!--            >{{ dict.label }}-->
        <!--            </el-radio>-->
        <!--          </el-radio-group>-->
        <!--        </el-form-item>-->
        <el-form-item label="成绩描述" prop="gradeDesc">
          <el-input v-model="form.gradeDesc" type="textarea" placeholder="请输入内容"/>
        </el-form-item>
        <!--        <el-form-item label="老师" prop="teacherId">-->
        <!--          <el-input v-model="form.teacherId" placeholder="请输入老师"/>-->
        <!--        </el-form-item>-->
        <!--        <el-form-item label="学生" prop="userId">-->
        <!--          <el-input v-model="form.userId" placeholder="请输入学生"/>-->
        <!--        </el-form-item>-->
        <!--        <el-form-item label="备注" prop="remark">-->
        <!--          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容"/>-->
        <!--        </el-form-item>-->
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>

    <!-- 添加或修改审核信息对话框 -->
    <el-dialog :title="title" v-model="openAudit" width="500px" append-to-body>
      <el-form ref="auditInfoRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="审核状态" prop="status">
          <el-radio-group v-model="form.status">
            <el-radio
                v-for="dict in grade_status"
                :key="dict.value"
                :label="dict.value"
            >{{ dict.label }}
            </el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="审核原因" prop="auditDesc">
          <el-input v-model="form.auditDesc" type="textarea" placeholder="请输入内容"/>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitFormAudit">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="GradeInfo">
import {addGradeInfo, delGradeInfo, getGradeInfo, listGradeInfo, updateGradeInfo} from "@/api/manage/gradeInfo"
import {addAuditInfo} from "@/api/manage/auditInfo.js";

const {proxy} = getCurrentInstance()
const {grade_status, is_passed} = proxy.useDict('grade_status', 'is_passed')

const gradeInfoList = ref([])
const open = ref(false)
const loading = ref(true)
const showSearch = ref(true)
const ids = ref([])
const single = ref(true)
const multiple = ref(true)
const total = ref(0)
const title = ref("")


const openAudit = ref(false)

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    gradeId: null,
    courseId: null,
    score: null,
    isPassed: null,
    status: null,
    gradeDesc: null,
    teacherId: null,
    userId: null,
  },
  rules: {
    courseId: [
      {required: true, message: "课程名称不能为空", trigger: "blur"}
    ],
    score: [
      {required: true, message: "学生成绩不能为空", trigger: "blur"}
    ],
    isPassed: [
      {required: true, message: "及格不能为空", trigger: "change"}
    ],
    status: [
      {required: true, message: "审核状态不能为空", trigger: "change"}
    ],
    teacherId: [
      {required: true, message: "老师不能为空", trigger: "blur"}
    ],
    userId: [
      {required: true, message: "学生不能为空", trigger: "blur"}
    ],
    createBy: [
      {required: true, message: "创建人不能为空", trigger: "blur"}
    ],
    createTime: [
      {required: true, message: "创建时间不能为空", trigger: "blur"}
    ],
  }
})

const {queryParams, form, rules} = toRefs(data)

function handleAudit(row) {
  reset()
  form.value.gradeId = row.gradeId
  title.value = "审核成绩"
  openAudit.value = true
}

function submitFormAudit() {
  addAuditInfo(form.value).then(res => {
    proxy.$modal.msgSuccess("审核成功")
    openAudit.value = false
    getList()
  })
}

/** 查询学生成绩信息列表 */
function getList() {
  loading.value = true
  listGradeInfo(queryParams.value).then(response => {
    gradeInfoList.value = response.rows
    total.value = response.total
    loading.value = false
  })
}

// 取消按钮
function cancel() {
  openAudit.value = false
  open.value = false
  reset()
}

// 表单重置
function reset() {
  form.value = {
    gradeId: null,
    courseId: null,
    score: null,
    isPassed: null,
    status: null,
    gradeDesc: null,
    teacherId: null,
    userId: null,
    createBy: null,
    createTime: null,
    updateTime: null,
    remark: null
  }
  proxy.resetForm("gradeInfoRef")
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1
  getList()
}

/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef")
  handleQuery()
}

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.gradeId)
  single.value = selection.length != 1
  multiple.value = !selection.length
}

/** 新增按钮操作 */
function handleAdd() {
  reset()
  open.value = true
  title.value = "添加学生成绩信息"
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset()
  const _gradeId = row.gradeId || ids.value
  getGradeInfo(_gradeId).then(response => {
    form.value = response.data
    open.value = true
    title.value = "修改学生成绩信息"
  })
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["gradeInfoRef"].validate(valid => {
    if (valid) {
      if (form.value.gradeId != null) {
        updateGradeInfo(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        })
      } else {
        addGradeInfo(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功")
          open.value = false
          getList()
        })
      }
    }
  })
}

/** 删除按钮操作 */
function handleDelete(row) {
  const _gradeIds = row.gradeId || ids.value
  proxy.$modal.confirm('是否确认删除学生成绩信息编号为"' + _gradeIds + '"的数据项？').then(function () {
    return delGradeInfo(_gradeIds)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => {
  })
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('manage/gradeInfo/export', {
    ...queryParams.value
  }, `gradeInfo_${new Date().getTime()}.xlsx`)
}

getList()
</script>
