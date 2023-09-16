<script>
import {API_URL, showError, showSuccess} from "@/utils";
import axios from "axios";

export default {
  props: {
    inStudentId: String,
    inCourseId: String,
    inDataList: Array,
    gradeDialogVisible: Boolean
  },

  data() {
    return {
      dataList: null,

      editDialogVisible: false,
      insertDialogVisible: false,

      studentId: '',
      courseId: '',
      studentName: '',
      courseName: '',
      grade: 0,
    }
  },

  methods: {
    getGrade() {
      if(this.inStudentId != null) {
        axios.post(API_URL + '/admin/student/grade', {
          studentId: this.inStudentId
        }, {
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
            'Token': localStorage.getItem('Token')
          }
        }).then(({data}) => {
          if(!data)
            showError("后端服务器存在问题")
          if(data.status) {
            this.dataList = data.data
          } else
            showError(data.message)
        }).catch(showError)
      } else if(this.inCourseId != null) {
        axios.post(API_URL + '/admin/course/grade', {
          courseId: this.inCourseId
        }, {
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
            'Token': localStorage.getItem('Token')
          }
        }).then(({data}) => {
          if(!data)
            showError("后端服务器存在问题")
          if(data.status) {
            this.dataList = data.data
          } else
            showError(data.message)
        }).catch(showError)
      }
    },

    closeDialog() {
      this.$emit('closeDialog')
    },

    openGradeEditDialog(row) {
      this.studentId = row.studentId
      this.courseId = row.courseId
      this.studentName = row.studentName
      this.courseName = row.courseName
      this.grade = row.grade

      this.editDialogVisible = true
    },

    editGrade() {
      axios.post(API_URL + "/admin/grade/update", {
        courseId: this.courseId,
        studentId: this.studentId,
        grade: this.grade
      }, {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
          'Token': localStorage.getItem('Token')
        }
      }).then(({data}) => {
        if (!data)
          showError("后端服务器存在问题")
        if (data.status) {
          showSuccess("修改成功")
          this.getGrade()
          this.editDialogVisible = false
        } else
          showError(data.message)
      }).catch(showError)
    },

    deleteGrade(row) {
      axios.post(API_URL + '/admin/grade/delete', {
        courseId: row.courseId,
        studentId: row.studentId
      }, {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
          'Token': localStorage.getItem('Token')
        }
      }).then(({data}) => {
        if(!data)
          showError("后端服务器存在问题")
        if(data.status) {
          showSuccess("删除成功")
          this.getGrade()
        } else
          showError(data.message)
      }).catch(showError)
    },

    openInsertDialog() {
      this.insertDialogVisible = true
      this.studentId = this.inStudentId
      this.courseId = this.inCourseId
      this.grade = ''
    },

    insertGrade() {
      if(!this.studentId || !this.courseId) {
        showError("未填写完全")
        return
      }
      axios.post(API_URL + '/admin/grade/insert', {
        studentId: this.studentId,
        courseId: this.courseId,
        grade: this.grade
      }, {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
          'Token': localStorage.getItem('Token')
        }
      }).then(({data}) => {
        if (!data)
          showError("后端服务器存在问题")
        if (data.status) {
          showSuccess("新增成功")
          this.getGrade()
          this.insertDialogVisible = false
        } else
          showError(data.message)
      }).catch(showError)
    }
  }
}
</script>

<template>
<div>
  <el-dialog title="成绩" :visible="gradeDialogVisible"
             :before-close="closeDialog">

    <el-button style="float: right" type="text" @click="openInsertDialog">新增</el-button>

    <el-table :data="dataList == null ? inDataList : dataList" stripe style="width: 100%" border>
        <el-table-column v-if="this.inStudentId != null" prop="courseId" label="课程号" />
        <el-table-column v-if="this.inStudentId != null" prop="courseName" label="课程名" />
        <el-table-column v-if="this.inCourseId != null" prop="studentId" label="学号"/>
        <el-table-column v-if="this.inCourseId != null" prop="studentName" label="学生名"/>
        <el-table-column v-if="this.inStudentId != null" prop="credit" label="学分" sortable/>
        <el-table-column prop="grade" label="成绩" sortable/>

        <el-table-column label="操作" :width="210">
          <template v-slot="scope">
            <el-popconfirm title="确定要删除吗？" icon="" style="margin-right: 10px" @confirm="deleteGrade(scope.row)">
              <el-button flex icon="el-icon-delete" slot="reference">删除</el-button>
            </el-popconfirm>
            <el-button icon="el-icon-edit" @click="openGradeEditDialog(scope.row)">编辑</el-button>
          </template>
        </el-table-column>

      </el-table>
  </el-dialog>

  <el-dialog title="编辑" :visible.sync="editDialogVisible" width="30%">
    <div>修改成绩。</div><br/>
    <el-form id="userInput" label-width="80px">

      <div style="width: 30%; min-width: 300px; margin: 0 auto">
        <el-form-item label="学号">
          <el-input v-model="studentId" :disabled="true"/>
        </el-form-item>

        <el-form-item label="学生名">
          <el-input v-model="studentName" :disabled="true"/>
        </el-form-item>

        <el-form-item label="课程号">
          <el-input v-model="courseId" :disabled="true"/>
        </el-form-item>

        <el-form-item label="课程名">
          <el-input v-model="courseName" :disabled="true"/>
        </el-form-item>

        <el-form-item label="成绩">
          <el-input v-model="grade"/>
        </el-form-item>
      </div>

      <div style="height: 30px"/>
      <el-form-item style="text-align: center">
        <el-button icon="el-icon-check" @click="editGrade" circle/>
      </el-form-item>
    </el-form>
  </el-dialog>

  <el-dialog title="新增" :visible.sync="insertDialogVisible" width="30%">
    <div>新增成绩。</div><br/>
    <el-form id="userInput" label-width="80px">

      <div style="width: 30%; min-width: 300px; margin: 0 auto">
        <el-form-item label="学号">
          <el-input v-model="studentId"/>
        </el-form-item>

        <el-form-item label="课程号">
          <el-input v-model="courseId"/>
        </el-form-item>

        <el-form-item label="成绩">
          <el-input v-model="grade"/>
        </el-form-item>
      </div>

      <div style="height: 30px"/>
      <el-form-item style="text-align: center">
        <el-button icon="el-icon-check" @click="insertGrade" circle/>
      </el-form-item>
    </el-form>
  </el-dialog>
</div>
</template>

<style>

</style>